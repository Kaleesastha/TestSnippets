/*
 * @(#)TestSsnClient.java
 * Copyright (c) 1998 AdventNet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 * 
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC.
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package test;

import java.net.*;
import java.io.*;

import com.adventnet.snmp.sas.*;
import com.adventnet.snmp.snmp2.*;

/**
 * This is a test class for the SASessionClient interface
 */

public class TestSsnClient implements SASessionClient {

  InetAddress applet_host = null;
  int remote_port = 0;

  /** This will be called when an applet session is opened */
  public void init(InetAddress applet_addr, int applet_port, SASession session) {
    System.out.println("TestSsnClient: Session started with client "
			+applet_addr+ " on port "+ applet_port);
    applet_host = applet_addr;
    remote_port = applet_port;
  }
		
  /** This function will be called when the client opens a UDP port.
   */
  public int open(int port) {
    System.out.println("TestSsnClient: SNMP port open request: "+port);
    return port;
  }
		

  /** This function is called when the PDU being sent to the agent. 
	The PDU can be modified as required.
   */
  public SnmpPDU requestPDU(SnmpPDU pdu) {
    /*try { pdu.decode(); }
    catch (SnmpException se) { System.err.println("Decode error: "+se); }

    System.out.println("TestSsnClient: Send PDU request: "
		+pdu.address+ " on port "+ pdu.remotePort);
	*/

    // pdu.encode(); // needed if PDU is changed
    return pdu;
  }
		

  /** This function is called when the response PDU from the agent. 
		The PDU can be modified as required.
   */
  public SnmpPDU responsePDU(SnmpPDU pdu) {
    /*try { pdu.decode(); }
    catch (SnmpException se) { System.err.println("Decode error: "+se); }

    System.out.println("TestSsnClient: PDU response: "
			+pdu.address+ " on port "+ pdu.remotePort);
	*/

    // pdu.encode(); // needed if PDU is changed
    return pdu;
  }


  /** This for methods called in the applet.
   */
  public byte[] clientCall(byte data[]) {
    System.out.println("TestSsnClient: clientCall: ");
    return data;
  }
		

  /** This function is called when an applet session is closed.
   */
  public void closeSession() {
    System.out.println("TestSsnClient: Session closed with client "
			+applet_host+ " on port "+ remote_port);
  }

	/** This method has been implemented by Users
  */
  public byte[] userCall(int userType, byte data[])	{
    System.out.println("TestSsnClient:userCall: ");
    System.out.println("UserRequestType: "+ userType);
    return data;
  }
}
