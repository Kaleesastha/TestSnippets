//$Id: TestClient.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
/*
 * @(#)TestClient.java
 */

import java.awt.*;
import java.lang.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.applet.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.startclient.*;
import com.adventnet.nms.pollui.JCStatistics;
import com.adventnet.nms.pollui.JCSnmpGraph;
import com.adventnet.snmp.beans.SnmpPoller;
import javax.swing.JApplet;

/**
 *The testclient is for showing how to use the mainsocket
 *
 * This TestClient is used in conjunction with the ProcessTest.java found
 * in examples/new_module and in TestSession.java found in 
 * examples/client_server_communication 
 *
 *       CLIENT                                  SERVER 
 *       ~~~~~~~~                                ~~~~~~~
 *      TestClient ------ commonSocket ------TestSession
 */
public class TestClient implements SocketConnection {
  

    // Since we can have many frames open which will use this testclient to 
    //connect to the server we need to be able to multiplex and demultiplex
    // for thye frames . We will store reference to all the frames
    // in this hashtable and access it by the agent name.

    Hashtable frames = new Hashtable();
 
    // A signature between the TestSession and TestClient for identification

    static String TESTID = "TEST_CLIENT";//No Internationalisation

    // The following is a set of codes for doing different tasks .
    // The server side counterpart too knows about this code

    static int READY = 100;
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

    boolean connected = true;

    public TestClient() {
        // register with the commonSocket on the client side with the given id
        // so that it transfer all messages with that id to you . also all messages
        // that you want to transmit to your counterpart on the server side
        // you can send with that id. The server side counterpart of this class
        // is the com.adventnet.nms.tester.TestSession which too knows
        // about this ID and will register with the server side with the
        // same id
        PureClientUtils.commonSocket.registerForResponses(this,TESTID);
        open();
    }

    // send a ready signal to the server module saying everthing is fine
    void open() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            DataOutputStream outp = new DataOutputStream(byteStream);
            outp.writeInt(READY);
            outp.flush();
        }
        catch (IOException ie) {
            System.err.println(NmsClientUtil.GetString("Could not open TestClient ")+ ie);
            return;
        }
        PureClientUtils.commonSocket.send(TESTID,byteStream.toByteArray());
        connected = true;
    }

    // Get all the polls configured for the agent
    void getAllPollKeys(String agentname,FrameExample frmex) {
	ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	try {
	    DataOutputStream outp = new DataOutputStream(byteStream);
	    outp.writeInt(TEST_GET_ALL_POLL_KEYS);
	    outp.writeUTF(agentname);
	    outp.flush();
	}
	catch (IOException ie) {
	    System.err.println(NmsClientUtil.GetString("Could not open TestClient ")+ ie);
	    return;
	}
	PureClientUtils.commonSocket.send(TESTID,byteStream.toByteArray());
	// store the agent reference so that u can pass on the updates 
	//correctly to the approriate agent
	frames.put(agentname,frmex);
    }
    //send request for registration for the particluar statistic

    void registerForData(String pollkey) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            DataOutputStream outp = new DataOutputStream(byteStream);
            outp.writeInt(TEST_REGISTER);
            outp.writeUTF(pollkey);
            outp.flush();
        } catch (IOException ie) {
            System.err.println(NmsClientUtil.GetString("Could not open TestClient ")+ ie);
            return;
        }
        PureClientUtils.commonSocket.send(TESTID,byteStream.toByteArray());
    }
    //send request for deregistration for the particluar statistic
    void deregisterForData(String pollkey) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            DataOutputStream outp = new DataOutputStream(byteStream);
            outp.writeInt(TEST_DEREGISTER);
            outp.writeUTF(pollkey);
            outp.flush();
        }
        catch (IOException ie) {
            System.err.println(NmsClientUtil.GetString("Could not open TestClient ")+ ie);
            return;
        }
        PureClientUtils.commonSocket.send(TESTID,byteStream.toByteArray());
    }
  
    // The main method of SocketConnection interface . Messages meant for this
    // client will be directed to this method
    public synchronized void receive(byte[] data) {
        DataInputStream inp = new DataInputStream(new ByteArrayInputStream(data));
        try {
            int req = inp.readInt();
            if (req == TEST_CODE1)  {
                // this is an example . reads what is given from exampleMethodForSending in TestSession
                String s1=inp.readUTF();
                String s2=inp.readUTF();
                int i1 = inp.readInt();
                System.err.println(s1+s2+i1);
            }
            else if (req == TEST_UPDATE)  {
                // An update about the latest value of a polled statistic
                String agentname=inp.readUTF();  
                String pollkey = inp.readUTF();
                String time = inp.readUTF();
                String value = inp.readUTF();
                FrameExample frmex = (FrameExample) frames.get(agentname);


                if (frmex!=null)
                    frmex.displayData(pollkey,time,value);
            }
            else if (req == TEST_REGISTRATION_CONFIRM)  {
                // confirmation that the request for registration has been successful
                // The poll period of the statistic is sent as proof
                String agentname =inp.readUTF();  
                String pkey = inp.readUTF();
                int period = inp.readInt();
                FrameExample frmex = (FrameExample) frames.get(agentname);
                if (frmex!=null)
                    frmex.putPollPeriod(pkey,period);
            }
            else if (req == TEST_ERROR)  {
                String errormess = inp.readUTF();
                System.err.println(NmsClientUtil.GetString(" Error message from server: ")+errormess);
            }
            else if (req == TEST_GET_ALL_POLL_KEYS) {
                // response for all poll keys of the agent . that is all the
                // statistics being collected for that agent
                String agentname=inp.readUTF();  
                int siz= inp.readInt();
                Vector v = new Vector();
                for (int i=0;i<siz;i++) {
                    v.addElement(inp.readUTF());
                }

                FrameExample frmex = (FrameExample) frames.get(agentname);

                if(siz == 0)
                {
                    NmsClientUtil.showError(NmsClientUtil.GetString("No poll keys available, hence performance cannot be monitored"));
                    if(frmex != null)frmex.dispose();
                    return;
                }

                if (frmex!=null)
                    frmex.displayPollKeyList(siz,v);
            }
            else if (req == TEST_GET_MULTIPLE)  {
                // confirmation that the request for registration has been successful
                // The poll period of the statistic is sent as proof
                String agent = inp.readUTF();	
                String multiple = inp.readUTF();
                FrameExample frmex = (FrameExample) frames.get(agent);
                if (frmex!=null)
                    frmex.setMultiple(multiple);
            }
             else if (req == TEST_GET_POLLED_DATA) {
                String agentname=inp.readUTF();  
                int siz= inp.readInt();
                Vector v = new Vector();
                Vector agentPollData = new Vector();
                
                byte dt[] = new byte[siz]; 
                inp.readFully(dt, 0, siz); 
                v = NmsClientUtil.deSerializeVector(dt);
                
                if(v!=null)
                {
                    for (int i=0;i<v.size();i++) 
                    {
                        byte[] pd = (byte [])v.get(i);
                        Properties prop = NmsClientUtil.deSerializeProperties(pd); 
                        agentPollData.add(prop);
                    }
                }

                FrameExample frmex = (FrameExample) frames.get(agentname);

                if(siz == 0)
                {
                    NmsClientUtil.showError(NmsClientUtil.GetString("No poll keys available, hence performance cannot be monitored"));
                    if(frmex != null)frmex.dispose();
                    return;
                }

                if (frmex!=null)
                    frmex.setPolledDataForAgent(agentPollData);
            }
            else {
                // this means some corruption
                System.err.println(NmsClientUtil.GetString(" unknown type received in testclient :")+req);
            }
        } catch (IOException ie) {
            System.err.println(NmsClientUtil.GetString("Receive error: ")+ie); 
        }
    } 
    // The other method of the interface . This is when the connection is 
    //closed , so that we can release held resources both client side and 
    //server side

    public void close() {
        if (connected) {
            try {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                DataOutputStream outp = new DataOutputStream(byteStream);
                outp.writeInt(TEST_CLOSE);
                outp.flush();
                PureClientUtils.commonSocket.send(TESTID,byteStream.toByteArray());
            } catch (IOException e) {
                System.err.println(NmsClientUtil.GetString("Receive error: ")+e);
            }
            connected = false;
        }
    }

    // when the window is closed for a particular frame release the resources
    // associated with that . Send a request to the server to do the same
    // for the given agent too.
    void removeFrame(String agentname) {
	if (agentname!=null) {
	    frames.remove(agentname);
	    try {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream outp = new DataOutputStream(byteStream);
		outp.writeInt(TEST_DEREGISTER_AGENT);
		outp.writeUTF(agentname);
		outp.flush();
		PureClientUtils.commonSocket.send(TESTID,byteStream.toByteArray());
	    } catch (IOException e) {
		System.err.println(NmsClientUtil.GetString("Send error in TestClient: ")+e);
	    }
	}
    }
	
    void showCollectedGraph(JApplet app,String key,String title, String multiple, Properties prop, String chartType)
    {
		if(chartType == null) //Assume chart type is jcchart.
		{
	        new JCStatistics(key,title,app,multiple);
		}
		else if(chartType.equals("JFreeChart"))
		{
			com.adventnet.nms.poll.graphs.CollectedGraphInvoker invoker = new com.adventnet.nms.poll.graphs.CollectedGraphInvoker();
			Properties p[] = {prop};
			invoker.setProperties(p);
		}    
	}

    void getMultiple(String key)
    {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            DataOutputStream outp = new DataOutputStream(byteStream);
            outp.writeInt(TEST_GET_MULTIPLE);
            outp.writeUTF(key);
            outp.flush();
        }
        catch (IOException ie) {
            System.err.println(NmsClientUtil.GetString("Could not open TestClient ")+ ie);
            return;
        }
        PureClientUtils.commonSocket.send(TESTID,byteStream.toByteArray());
    }
	
    void showCurrentGraph(String key, String title, SnmpPoller poller, JApplet a, Vector mulOid, Properties prop, String chartType)
	{
        if(chartType == null) //Assume chart type is jcchart
		{
			JCSnmpGraph graph = new JCSnmpGraph (title,poller,a,mulOid);
	        graph.setKey(key);
    	    graph.start ();
		}
		else if(chartType.equals("JFreeChart"))
		{
			com.adventnet.nms.poll.graphs.CurrentGraphInvoker invoker = new com.adventnet.nms.poll.graphs.CurrentGraphInvoker();
			Properties p[] = {prop};
			invoker.setProperties(p);
		}
    }

}



