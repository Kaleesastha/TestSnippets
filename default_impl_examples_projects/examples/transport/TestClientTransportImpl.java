/* $Id: TestClientTransportImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

/*
 * @(#)TestClientTransportImpl.java	
 *
 * Copyright (c) 1996-2000 Adventnet, Inc. All Rights Reserved.
 * Please read the included COPYRIGHTS file for more details.
 * 
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES 
 * ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET,
 * INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE 
 * OR ITS DERIVATIVES.
 */

package com.adventnet.nms.example.transport;

import java.net.*;
import java.util.*;
import java.io.*;
import java.applet.Applet;

import com.adventnet.management.transport.TransportProvider;
import com.adventnet.management.transport.TransportException;
import com.adventnet.management.transport.SessionTransportProvider;
import com.adventnet.management.transport.LogInterface;
import com.adventnet.management.transport.TransportUtil;

/**
 * Client side implementation of the TransportProvider interface that creates
 * a client session for communicating with the server. This implementation 
 * provides TCP/IP as the lower layer transport. 
 */

public class TestClientTransportImpl implements TransportProvider	{

	Socket sock = null;	String filename = null;

	/** 
	 * Default constructor that creates an instance of this class.
	 */
	public TestClientTransportImpl()	{
	}

	/**
	 * Initializes the TCP Socket over which the data is sent/received.
	 *
	 * @param params parameters that are used for creating the client session.
	 * @Exception TransportException in case of an error during initializing the 
	 * socket interface.
	 */
	public void init(Object[] params) throws TransportException	{

		String hostname=null;
		int port=0;
		String socketPortDir;
		URL url = null;				
		
		Applet applet = (Applet)params[0];				
		socketPortDir = (String) params[1];

		URL url1 = null;
		url1 = applet.getCodeBase();				
		filename = (String)params[2];
		
		if(url1 != null)	{
			hostname = url1.getHost();						
			if (hostname.equals("")) 	{
				// No applet host - local files
				hostname = "localhost"; 
			}
			
			if (socketPortDir != null) {				
				if(socketPortDir.startsWith("../"))	{
					int index = socketPortDir.lastIndexOf("../");
					// 3 stands for the length of the string "../"
					socketPortDir = socketPortDir.substring(index+3, socketPortDir.length()); 	
				}
				else if(socketPortDir.startsWith("./"))	{
					// 2 stands for the length of the string "./"
					socketPortDir = socketPortDir.substring(2, socketPortDir.length());	
				}
				if(!socketPortDir.endsWith("/"))	{
					socketPortDir = socketPortDir + "/";	
				}
                try	{	
					url = new URL(applet.getDocumentBase().getProtocol(),applet.getDocumentBase().getHost(),applet.getDocumentBase().getPort(), "/" + socketPortDir + filename);
				}catch(Exception ie)	{
					throw new TransportException (ie.toString());						
				}
				port = TransportUtil.readPort(url, filename);
			}
			else {
				try {
					// This will be used in the case of appletviewer.
					url= new URL(applet.getDocumentBase(),filename);					
					port = TransportUtil.readPort(url, filename);
					if(port == -1)
						throw new Exception();					
				}
				catch(Exception ex) {				
					try	{
						url = new URL(applet.getDocumentBase().getProtocol(),applet.getDocumentBase().getHost(),applet.getDocumentBase().getPort(),"/"+filename);										
					}					
					catch(Exception ie)	{						
						throw new TransportException(ie.toString());					
					}
					port = TransportUtil.readPort(url, filename);
				}
			}	
		}  // end if(url1 != null)		
		else	{						
			/* In case of applications take the host & port from the applet parameters*/			
			hostname = applet.getParameter("HOST");
			port = Integer.parseInt(applet.getParameter("PORT"));
		}

		if (sock == null)	{			
			try	{
				sock = new Socket(hostname,port);			
			}			
			catch(Exception ie)	{				
				throw new TransportException(ie.toString());			
			}
		}
	}
	
	/**
	 * Creates an instance of the client session object and returns a handle to 
	 * it.
	 *
	 * @param params parameters that are used for creating the client session.
	 * @return reference to the SessionTransportProvider object that represents
	 * the client session.
	 * @Exception TransportException in case of an error during opening the
	 * transport interface.
	 */
	public SessionTransportProvider open(String[] params) throws TransportException	{
	
		TestSessionTransportImpl sessionObj = new TestSessionTransportImpl(sock); 				
		return (sessionObj); 
	}
	
	/**
	 * Closes the transport interface after communication is over.
	 *
	 * @Exception TransportException in case of an error during closing.
	 */
	public void close()	 throws TransportException	{

		// Close the server sockets.
		if (sock != null)	{			
			try	{
				sock.close();
			}			
			catch(IOException ie)	{				
				throw new TransportException(ie.toString());			
			}
			sock = null;
		}
	}

	/** Sets the logInterface to the specified one if any.
	 * 
	 */	
	 public void setLogInterface(LogInterface logInterface)	{		
	 }

}
