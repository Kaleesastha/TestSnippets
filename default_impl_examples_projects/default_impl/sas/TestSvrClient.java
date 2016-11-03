/*
  $Id: TestSvrClient.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 */

/*
 * @(#)TestSvrClient.java
 * Copyright (c) 1997 Advent Network Management, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 * 
 * ADVENT NETWORK MANAGEMENT, INC. MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENT NETWORK 
 * MANAGEMENT, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE 
 * OR ITS DERIVATIVES.
 */

package test;


import java.net.*;
import java.io.*;

import com.adventnet.snmp.sas.*;
import com.adventnet.snmp.snmp2.*;

/**
 * This is a test class for the SAServerClient interface
 */

public class TestSvrClient implements SAServerClient {

  String appletDir = "";
  String webServerRoot = "./";

  /** This will be called when SAS starts */
  public void init(String applet_dir,String webRoot) {
    //System.out.println("TestSvrClient: SAS started: "+applet_dir);
    appletDir = applet_dir;
    if(!webRoot.equals(""))
            webServerRoot = webRoot;
  }


  /** This function will be called when a new applet session is opened.
   */
  public void initSession(InetAddress applet_addr, int applet_port, SASession session) {
    //System.out.println("TestSvrClient: Session started with client "
	//		+applet_addr+ " on port "+ applet_port);
  }
		
  int num_sessions = 0;
  int MAX_SNMP_SESSIONS = 100;

  /** This function will be called when the client opens a UDP port.
   */
  public int open(int port) {
    //System.out.println("TestSvrClient: SNMP port open request: "+port);
// Allows control of # of sessions. < 0 will not open UDP port, disabling SNMP
    if (num_sessions++ > MAX_SNMP_SESSIONS) return -1;  
    return port;
  }
		

  /** This function is called when the PDU being sent to the agent. 
	The PDU can be modified as required.
   */
  public SnmpPDU requestPDU(SnmpPDU pdu) {	  
    /*try { pdu.decode(); }
    catch (SnmpException se) { System.err.println("Decode error: "+se); }
	*/

    //System.out.println("TestSvrClient: Send PDU request: "
	//		+pdu.address+ " on port "+ pdu.remotePort);

    // pdu.encode(); // needed if PDU is changed
    return pdu;
  }
		

  /** This function is called when the response PDU from the agent. 
		The PDU can be modified as required.
   */
  public SnmpPDU responsePDU(SnmpPDU pdu) {	  
    /*try { pdu.decode(); }
    catch (SnmpException se) { System.err.println("Decode error: "+se); }
	*/

    //System.out.println("TestSvrClient: PDU response: "
	//		+pdu.address+ " on port "+ pdu.remotePort);

    // pdu.encode(); // needed if PDU is changed
    return pdu;
  }


// some constants for clientCall types
  static int LIST_DIR_REQ = 1;
  static int LIST_DIR_RESP = 2;
  static int GET_FILE_REQ = 3;
  static int GET_FILE_RESP = 4;
  
  /** This for methods called in the applet.
   */
  public byte[] clientCall(byte data[]) {
    //System.out.println("TestSvrClient: clientCall: ");
    if (data == null) return data;

    try {
      DataInputStream dI = new DataInputStream(
				new ByteArrayInputStream(data));

      int type = dI.readInt();

      if (type == LIST_DIR_REQ) data = listDir(dI);  // pass of to function
      else if(type == GET_FILE_REQ)data = getFile(dI);

    }  catch (IOException e) { 
	System.err.println("Error in client call request: "+e);
    e.printStackTrace();
    }
    return data;
  }
		

  /** This function is called when an applet session is closed.
   */
  public void closeSession(InetAddress applet_addr, int applet_port) {
    num_sessions--;
    //System.out.println("TestSvrClient: Session closed with client "
	//		+applet_addr+ " on port "+ applet_port);
  }
	
	
  /** This implements the list directory client request
   */
  byte[] listDir(DataInputStream dI) throws IOException {
    
    String dirname = dI.readUTF();
    if(!webServerRoot.equals("")) {
       if(webServerRoot.endsWith("/"))
            dirname = webServerRoot.substring(0,webServerRoot.length()-1) + dirname;
       else
            dirname = webServerRoot + dirname;
    }
    if(dirname.equals("/"))dirname = dirname + ".";
    if(dirname.equals(""))dirname = dirname + "/.";
    String s = "";

    //File dir = new File(appletDir + "/SASusers/" + dirname);
    //if (appletDir.equals("")) dir = new File("SASusers/" + dirname);

    File dir = new File(dirname);

    if (!dir.exists()) s = "Cannot list. Directory does not exist.";
    else if (!dir.isDirectory()) s = "Cannot list. Not a directory.";
    else {
      String list[] = dir.list();
      
      if(list != null)
      {
        for(int i = 0;i<list.length;i++)
        {
            File listFile = new File(dir.getPath() + "/" + list[i]);
            if(listFile.isDirectory())
            {
                list[i] = new String("<DIR>" + list[i]);
            }
        }
      }

      if (list == null)s = "";
      else {
	StringBuffer sb = new StringBuffer();
	for (int i=0;i<list.length;i++) sb.append(list[i]+"\n");
	s = sb.toString();
      }
    }

    ByteArrayOutputStream baO = new ByteArrayOutputStream(100);
    DataOutputStream dO = new DataOutputStream(baO);
    dO.writeInt(LIST_DIR_RESP);
    dO.writeUTF(s);
    return baO.toByteArray();

  }// end listDir()


    /** This implements the get file client request
   */
  byte[] getFile(DataInputStream dI) throws IOException {

    String filename = dI.readUTF();
    String s = "";
    byte data[] = null;

    File file = new File(appletDir + "/SASusers/" + filename);
    if (appletDir.equals("")) file = new File("SASusers/" + filename);
    //File file = new File(filename);

    if (!file.exists()) s = "Cannot get. File does not exist.";
    else if (!file.isFile()) s = "Cannot get. Not a File.";
    else {
        FileInputStream fis = new FileInputStream(file);
        data = new byte[fis.available()];
        fis.read(data);
        s = new String(data);
    }

    ByteArrayOutputStream baO = new ByteArrayOutputStream();
    DataOutputStream dO = new DataOutputStream(baO);
    dO.writeInt(GET_FILE_RESP);
    dO.writeUTF(s);
    return baO.toByteArray();

  }// end listDir()


}
