//$Id: TestSession.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $

/*
 * @(#)TestSession.java
 */
package com.adventnet.nms.tester;

import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.startnms.*;
import com.adventnet.nms.poll.*;

/**
 *
 * The Test client will request a session via the common socket
 * and TestSession will be invoked to handle the needs of that session.
 * The idea here is that on the server side this will liase with the
 * modules do the necessary actions and send the data
 * Another thing to note is data can be sent freely both ways now 
 * either on a request or as an update or anything else.
 * This class also implements the PollObserver interface which allows it
 * to get a real time update of collected statistic for the required
 * stat. In this example we will register for some polls and send the data
 * across to the client.
 * This TestSession is used in conjunction with the ProcessTest.java found
 * in examples/new_module and in TestClient.java found in 
 * examples/client_server_communication 
 *
 *       CLIENT                                  SERVER 
 *       ~~~~~~~~                                ~~~~~~~
 *      TestClient ------ commonSocket ------TestSession
 */
public class TestSession implements SocketSessionConnectionBE,PollObserver {
  boolean DEBUG = false;
  ProcessTest pTest = null;

  // A signature between the TestSession and TestClient for identification
  static String TESTID = "TEST_CLIENT";//No Internationalisation

  MainSocketSessionBE mainSession;

    // Each request from the server will come with an ID . It
    // is urged that you respond with the same ID .
  String uniqueID;
  Vector pdatasRegisteredFor = new Vector();
    
  static int READY = 100;

    //
  static int TEST_CODE1 = 5;
  static int TEST_REGISTER = 6;
  static int TEST_DEREGISTER = 7;
  static int TEST_UPDATE = 8;
  static int TEST_ERROR = 9;
  static int TEST_CLOSE = 10;
  static int TEST_GET_ALL_POLL_KEYS = 11;
  static int TEST_DEREGISTER_AGENT = 12 ;
  static int TEST_REGISTRATION_CONFIRM = 13;
  static int TEST_GET_MULTIPLE = 14;
  static int TEST_GET_POLLED_DATA = 15;   
  public TestSession() {  
  }
  public TestSession(ProcessTest pT,MainSocketSessionBE mss) {
    pTest = pT;
    mainSession = mss;
    // The refrence to the socket connection for this session . 
    //Register for responses whose ID is as given .The client side will
    // do the same and the socket connection can now provide two way 
    //communication between this class and TestClient 
    mainSession.registerForResponses(this,TESTID);
  }
  // this is the method which the SocketSessionConnectionBE has to implement
  // The MainSocketSessionBE which handles the Socket will route the
  // traffic for this Session through this method

  public void receive(String uid,byte[] data) {
    // This variable is for proper identification internally
    // do not tamper with them
    uniqueID = uid;

    try {
      DataInputStream inp = new DataInputStream(new ByteArrayInputStream(data));
      // Send what action to do as an int . This is a predefined agreement between
      // TestSession and TestClient
      int req_type = inp.readInt();
      if(req_type == READY) {
	// This is preferred way to start off .
	// here you can have some initialization stuff to cater
	// to the needs of the session
	// Typically this is the first message you will receive from the client
	System.out.println(NmsUtil.GetString("In Test Session receive . got READY signal from client"));
      }
      else if (req_type == TEST_CODE1) {
	//	In this one and in each of the subsequent carry out the action
	// as required and send the response back to the client

	// To know how to send data see the next Method
	exampleMethodForSending() ;

      }
      else if (req_type == TEST_GET_ALL_POLL_KEYS) {
	  String agentnam =inp.readUTF();
	  sendAllPollKeys(agentnam);
      }
      else if (req_type == TEST_REGISTER) {
	String pollkee = inp.readUTF();
	registerForData(pollkee);
      }
      else if (req_type == TEST_DEREGISTER) {
	String pollkey = inp.readUTF();
	try {
	  int i =pdatasRegisteredFor.indexOf(pollkey);
	  if (i>=0) {
	      pdatasRegisteredFor.removeElementAt(i);
	      pTest.pollapi.deregisterForData(pollkey,this);
	  }
	} catch (Exception ee) {
	  System.err.println(NmsUtil.GetString("Error deregistering for data ")+pollkey);
	}
      }
      else if (req_type == TEST_DEREGISTER_AGENT) {
	String agentname = inp.readUTF();
	deregisterForAgent(agentname);
      }
	else if (req_type == TEST_GET_MULTIPLE) {
	String pollkey = inp.readUTF();
	try {
		PolledData pd = pTest.pollapi.getPolledData(pollkey);
		boolean mul = pd.getIsMultiplePolledData();
		
		String multiple = null;
		if(mul)
			multiple = "true";//No Internationalisation
		else
			multiple = "false";//No Internationalisation
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	    DataOutputStream outp = new DataOutputStream(byteStream);
	    outp.writeInt(TEST_GET_MULTIPLE);
		outp.writeUTF(pd.getAgent());
	    outp.writeUTF(multiple);
	    outp.flush();
	    mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
	}   

				catch(com.adventnet.nms.poll.NmsPollException nmse)
                                    {
					System.err.println(NmsUtil.GetString("Error in retrieving PolledData. ") + pollkey);
                                        nmse.printStackTrace();	
				}


	 catch (Exception ee) {
	  System.err.println(NmsUtil.GetString("Error deregistering for data ")+pollkey);
	}
      }
      else if (req_type == TEST_CLOSE) {
	close();
      }
      else { 
	// Don't know what request type we got
	// here maybe print a message . so that you will know something has gone wrong

      }
    } catch (IOException e) {
      System.err.println(NmsUtil.GetString("Error in Client Test Session. Aborting  Session.")+e);
      System.err.println(e.toString());
    }

  }
  //below is an example of how to send data . One can pretty much keep the
  //the start and end portions of the method same. In the outp.write
  // you can have as much flexibility as one likes. check the DataOutputStream
  //methods for sending different kinds of data.

  // A helpful tip .Be sure to read the data in the same order . So the client
  // and server side for a particular function write it together so that you do not
  // end up in trouble

  void exampleMethodForSending() {
    try {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      DataOutputStream outp = new DataOutputStream(byteStream);
      // This is the secret agreement between the client and server
      // by looking at this client will be able to know what to do.
      outp.writeInt(TEST_CODE1);
      // if you wanted to send a string use UTF
      // remember in the client you have to read in the same order
      outp.writeUTF("This is the first message ");//No Internationalisation
      outp.writeUTF("This is the second message. My favourite number is ");//No Internationalisation
      outp.writeInt(45);
      outp.flush();
      mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
    } catch (IOException e) {
      System.err.println(NmsUtil.GetString("IO Error sending Response to applet.")+e);
    }
  }

  public void close() {
    // when the client closes the session say by shutting down netscape
    // then this method is called for any cleanup required .This is required
    // because the session can hold up lot of memory and if we do not clean up
    // here over a period of time as sessions are opened and closed lot of times
    // we can have a memory leak
    try {
      for (int i =0;i<pdatasRegisteredFor.size();i++) {
	String pollkey = (String) pdatasRegisteredFor.elementAt(i);
	if (pollkey==null) continue;
	pTest.pollapi.deregisterForData(pollkey,this);
      } 
      pdatasRegisteredFor.removeAllElements();
    } catch (Exception ee) {
      System.err.println(NmsUtil.GetString("Error deregistering with pollapi ")+ee);
      error("Exception deregistering with pollapi"+ee);
    }
  }

    // deregister for all the polls registered for a particular agent
  void deregisterForAgent(String agentname) {
    try {
      Vector v = new Vector();	
      for (int i =0;i<pdatasRegisteredFor.size();i++) {
	String pollkey = (String) pdatasRegisteredFor.elementAt(i);
	if (pollkey.indexOf(agentname)<0) continue;
	pTest.pollapi.deregisterForData(pollkey,this);
	v.addElement(pollkey);
      } 
      for (int i = 0;i<v.size();i++) {
	  pdatasRegisteredFor.removeElement(v.elementAt(i));
      }
    } catch (Exception ee) {
      System.err.println(NmsUtil.GetString("Error deregistering with pollapi ")+ee);
      error("Exception deregistering with pollapi"+ee);
    }
  }
    private String getAgent(String pollkey) {
	int i1=pollkey.indexOf("\t");//No Internationalisation
	int i2=pollkey.lastIndexOf("\t");//No Internationalisation
	String agentname= null;
	try {
	    agentname=pollkey.substring(i1+1,i2).trim();
	} catch (Exception anye) {
	    anye.printStackTrace();
	}
	return agentname;
    }  

    public void dataUpdate(String pollkey,long time, long value) {
	try {
	    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	    DataOutputStream outp = new DataOutputStream(byteStream);
	    outp.writeInt(TEST_UPDATE);
	    String s=getAgent(pollkey);
	    if (s!=null) 
		outp.writeUTF(s);
	    else
		outp.writeUTF("null");//No Internationalisation
	    outp.writeUTF(pollkey);
	    outp.writeUTF(String.valueOf(time));
	    outp.writeUTF(String.valueOf(value));
	    outp.flush();
	    mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
	}	catch (IOException e) {
	    System.err.println(NmsUtil.GetString("IO Error sending Response to applet.")+e);
	}
  }

  public void dataUpdate(String pollkey,long time, String value) {
      try {
	  ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	  DataOutputStream outp = new DataOutputStream(byteStream);
	  outp.writeInt(TEST_UPDATE);
	  String s=getAgent(pollkey);
	  if (s!=null) 
	      outp.writeUTF(s);
	  else
	      outp.writeUTF("null");//No Internationalisation
	  outp.writeUTF(pollkey);
	  outp.writeUTF(String.valueOf(time));
	  outp.writeUTF(value);
	  outp.flush();
	  mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
      }	catch (IOException e) {
	  System.err.println(NmsUtil.GetString("IO Error sending Response to applet.")+e);
      }
  }
  	
	
	public void dataUpdate(CollectedInfo colinfo)
	{
		try {
		Date d = new Date();
		long time = colinfo.getTime();
		
		if(time == 0) time = d.getTime();
		
		Vector keys=colinfo.getKeys();
		String s=colinfo.getAgent();
				
		for(Enumeration e=keys.elements();e.hasMoreElements();)
		{
			String key=(String)e.nextElement();
			Vector instances=colinfo.getInstances(key);
			if(instances==null) continue;
			for(Enumeration e1=instances.elements();e1.hasMoreElements();)
			{
				String inst=(String)e1.nextElement();
				Object obj=colinfo.getValue(key,inst);
				
				ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
				DataOutputStream outp = new DataOutputStream(byteStream);
				outp.writeInt(TEST_UPDATE);
				if (s!=null) 
						outp.writeUTF(s);
					else
						outp.writeUTF("null");//No Internationalisation
				if(obj instanceof String){
					String stringVal = (String)obj;
					outp.writeUTF(key);
					outp.writeUTF(String.valueOf(time));
					outp.writeUTF(stringVal);
					outp.flush();
					mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
				}	
				else{
					long longVal = ((Long)obj).longValue();
					outp.writeUTF(key);
					outp.writeUTF(String.valueOf(time));
					outp.writeUTF(String.valueOf(longVal));
					outp.flush();
					mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
				}
				
			}
		}
	 }
	 catch (IOException e) {
	  System.err.println(NmsUtil.GetString("IO Error sending Response to applet.")+e);
      }
	
	}
	
  
    void sendAllPollKeys(String agentname) 
    {
	if (agentname == null) 
                return;
	Vector v = null;
	try 
        {
	    v=pTest.pollapi.getPollsForAgent(agentname);
            System.out.println(NmsUtil.GetString("Sending ")+ v.size() +  NmsUtil.GetString(" Poll Keys for ") + agentname);
            sendPolledDataProp(agentname, v);       
	} 
        catch (Exception ee) 
        {
	    System.err.println(NmsUtil.GetString("Exception getting polled data list for agent :")+agentname+" "+ee);//No Internationalisation
	    error("Exception trying to get poll keys "+ee);
	}
	try 
        {
	    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	    DataOutputStream outp = new DataOutputStream(byteStream);
	    outp.writeInt(TEST_GET_ALL_POLL_KEYS);
	    outp.writeUTF(agentname);
	    int siz= 0;
	    if (v!=null) siz=v.size();
	    outp.writeInt(siz);
	    for (int i=0;i<siz;i++) 
            {
		outp.writeUTF((String)v.elementAt(i));
	    }
	    outp.flush();
	    mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
	}   
        catch (IOException e) 
        {
	    System.err.println(NmsUtil.GetString("IO Error sending Response to applet.")+e);
	}
    }

    void sendPolledDataProp(String agentname, Vector v)
    {
        if (agentname == null || v==null) 
            return;

        Vector vecProp = new Vector();

        try 
        {
            if(v!=null)
            {
                for(int i=0;i<v.size();i++)
                {
                    String pollkey = (String)v.elementAt(i);
                    Properties prop = pTest.pollapi.getPolledData(pollkey).getProperties();
                    vecProp.add(NmsUtil.serializeProperties(prop));
                }
            }
        }
        catch (Exception ee) 
        {
	    System.err.println(NmsUtil.GetString("Exception getting polled data for agent :")+agentname+" "+ee);//No Internationalisation
	    error("Exception trying to get polled data "+ee);
	}
	try 
        {
	    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	    DataOutputStream outp = new DataOutputStream(byteStream);
	    outp.writeInt(TEST_GET_POLLED_DATA);
	    outp.writeUTF(agentname);

	    if (vecProp!=null) 
            {
                byte[] dt = NmsUtil.serializeVector(vecProp);
                int vsize = dt.length;
                outp.writeInt(vsize);
                outp.write(dt,0,vsize);
                outp.flush();
                mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
            }
	}   
        catch (IOException e) 
        {
	    System.err.println(NmsUtil.GetString("IO Error sending Response to applet.")+e);
	}
    } 
    
    void sendConfirmation(PolledData pd) {
	try {
	    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	    DataOutputStream outp = new DataOutputStream(byteStream);
	    outp.writeInt(TEST_REGISTRATION_CONFIRM);
	    outp.writeUTF(pd.getAgent());
	    outp.writeUTF(pd.getKey());
	    outp.writeInt(pd.getPeriod());
	    outp.flush();
	    mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
	}   catch (IOException e) {
	    System.err.println(NmsUtil.GetString("IO Error sending Response to applet.")+e);
	}
    }

  void registerForData(String pollkey) {
    if (pollkey == null) return;
    if (pdatasRegisteredFor.contains(pollkey)) return ;
    try {
	PolledData pd =pTest.pollapi.getPolledData(pollkey);
	if (pd==null) {
	    System.err.println(NmsUtil.GetString("Cannot register for collected data of ")+pollkey+" . "+NmsUtil.GetString("No such Polled Data ")); 
	    return;
	}
	pTest.pollapi.registerForData(pollkey,this);
	pdatasRegisteredFor.addElement(pollkey);
	// tell the client what data it is polling
	sendConfirmation(pd);
	long timenow = (new Date()).getTime();
	dataUpdate(pollkey,0,"");//No Internationalisation
    } 


	catch(com.adventnet.nms.poll.NmsPollException ne)
	{
		System.err.println(NmsUtil.GetString("Error in retrieving PolledData. ") + pollkey);
		ne.printStackTrace();
	}

		catch (Exception ee) {
      System.err.println(NmsUtil.GetString("Exception registering with polleddata for data-")+pollkey+" "+ee);//No Internationalisation
      
      error("Exception registering with polleddata for data");
    }
  } 


  void sendOneString(int id,String s) {
    try {
     ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
     DataOutputStream outp = new DataOutputStream(byteStream);
     outp.writeInt(id);
     outp.writeUTF(s);
     outp.flush();
     mainSession.send(TESTID,uniqueID,byteStream.toByteArray());
    } catch (Exception ee) {
      System.err.println(NmsUtil.GetString("Error communicating to ")+TESTID+ee);
    }
  }

/** Send error back to applet */
  void error(String s) {
    sendOneString(TEST_ERROR,s);    
  }

}















