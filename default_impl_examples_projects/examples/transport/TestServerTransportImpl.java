/* $Id: TestServerTransportImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

/*
 * @(#)TestServerTransportImpl.java	
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
import java.applet.*;

import com.adventnet.management.transport.TransportProvider;
import com.adventnet.management.transport.TransportException;
import com.adventnet.management.transport.SessionTransportProvider;
import com.adventnet.management.transport.LogInterface;
import com.adventnet.management.transport.TransportUtil;

/**
 * Server side implementation of the TransportProvider interface that 
 * provides TCP as the lower layer transport. 
 *
 * This class creates the ServerSocket that listens for incoming client requests
 * and creates a session for each client request.
 */

public class TestServerTransportImpl implements TransportProvider	{

	ServerSocket servSock = null;
	LogInterface log = null;
	boolean DEBUG=false;

	/** 
	 * Default constructor that creates an instance of this class.
	 */
	public TestServerTransportImpl()	{

	}

	/**
	 * Initializes the TCP ServerSocket over which the data is sent/received.
	 *
	 * @exception TransportException is thrown in case of an error during initializing 
	 * the transport interface.
	 */
	public void init(Object[] params) throws TransportException	{
    String dir = ""; // The path to be used when writing the port number
    File f = null;
    FileOutputStream fs = null;

		if (servSock == null)	{			
			try	{
				servSock = new ServerSocket(Integer.parseInt((String)params[0]),Integer.parseInt((String)params[1]));					
			}
			catch(IOException ie)	{
				throw new TransportException(ie.toString());
			}					
		}
		if(params[2] != null)	{
			dir = params[2].toString();
		}
		
		// Save the socket port number to the file name specified. 
		String filename = ((String)params[3]);		
		
		if(Boolean.valueOf(params[4].toString()).booleanValue() != false) {
			DEBUG = Boolean.valueOf(params[4].toString()).booleanValue();
		}					

		if(DEBUG) {
			log.dbg("Saving Port number to file: "+filename);
		}

		try {
	        TransportUtil.writePortToHtml(servSock.getLocalPort(),dir,filename);
			log.out("Server Started on port: "+ String.valueOf(servSock.getLocalPort()));
		} catch (Exception ie) {
		  	log.err("Cannot write to: " + filename + " " + ie.getMessage());
		  	return;
		}
	}
	
	/**
	 * Creates a session for communicating with each of the clients and returns 
	 * a handle (session instance) to the connection request received from the 
	 * client. 
	 *
	 * @param params Parameters that are required to create the session.
	 * @return a SessionTransportProvider  that represents a session for
	 * communication with each of the clients.
	 * @exception TransportException is thrown in case of an error during opening the
	 * transport interface.
	 */
	public SessionTransportProvider open(String[] params) throws TransportException	{		
		Socket socket = null;		
		try	{
			// Wait for connection requests from clients.
			socket = servSock.accept(); 	
		}
		catch(IOException io)	{
			return null;
		}		
		catch(Exception ie)	{			
			throw new TransportException(ie.toString());		
		}		
		
		TestSessionTransportImpl  sessionObj = new TestSessionTransportImpl (socket);
		return (sessionObj); 
	}
	
	/**
	 * Closes the transport interface after communication is over.
	 *
	 * @exception TransportException is thrown in case of an error during closing.
	 */
	public void close()	throws TransportException	{

		// Close the server sockets.
		if (servSock != null)	{			
			try	{
				servSock.close();			
			}			
			catch(IOException ie)	{				
				throw new TransportException(ie.toString());			
			}
			servSock = null;
		}
	}		
	
	/** Sets the LogInterface object. 
	 */
	public void setLogInterface(LogInterface logInterface)	{
		log = logInterface;
	}	
}
