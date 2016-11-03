/* $Id: TestSessionTransportImpl.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */

/*
 * @(#)TestSessionTransportImpl.java	
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

import com.adventnet.management.transport.SessionTransportProvider;
import com.adventnet.management.transport.TransportException;

/**
 * Implementation of the SessionTransportProvider interface that provides the 
 * session communiation with TCP/IP as the lower layer transport. This class
 * implements all the IO operations (open, close, read, write) required by the
 * session communication.
 */

public class TestSessionTransportImpl implements SessionTransportProvider	{
	
	Socket socket = null;
	DataOutputStream out = null;
	DataInputStream in = null;	
	private String hostName = null;	
	/**
	 * Constructor for this class that takes a TCP Socket instance as an argument.
	 */
	public TestSessionTransportImpl(Socket sock) {

		socket = sock;
		hostName = sock.getInetAddress().getHostName();
		try {
			// Get the socket's input stream for reading
			in= new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			// Get the socket's output stream for writing
			out =new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		}
		catch (IOException e)	{			
		}
	}

	/**
	 * Opens the session over which the data is sent/received 
	 *
	 * Note: Presently this is not intended to be called by the API user since 
	 * the TcpClientTransportImpl internally creates the session object.
	 *
	 * @param params parameters required for opening the session interface.
	 * @return OK on success or ERROR in case of error.
	 * @exception TransportException in case of an error during opening the
	 * transport interface.
	 */
	public int open(String[] params) throws TransportException	{		
		return (SessionTransportProvider.OK);
	}
	
	/**
	 * Send data to the server over the TCP server socket interface.
	 *
	 * @param buf Buffer that contains the bytes to be sent.
	 * @param len number of bytes to send 
	 * @return OK on succesful write or ERROR on error
	 * @exception TransportException in case of an error during send.
	 */
	public int write(byte[] buf, int len) throws TransportException	{

		// Write the specified bytes to the socket.		
		try	{
			out.writeInt(len);
			out.write(buf, 0, len);
			out.flush();					
		}		
		catch(IOException ie)	{			
			ie.toString();
			return(SessionTransportProvider.ERROR);
		}		
		return(SessionTransportProvider.OK);		
	}

	
	/**
	 * Receive data from the peer over the transport interface.
	 * @return byte array in which the bytes are read into.
	 * @exception TransportException in case of an error during receive.
	 */
	public byte[] read() throws TransportException	{
				
		int len = 0;		
		byte[] bArr = null;		
		try	{
			len = in.readInt();			
			bArr = new byte[len];			
			in.readFully(bArr, 0, len);	
		}		
		catch(IOException e)	{			
			throw new TransportException(e.toString());			
		}
		return bArr;
	}
	
	/**
	 * Closes the transport interface after communication is over.
	 *
	 * @return OK in case of a successful close or returns ERROR on error.
	 * @exception TransportException in case of an error during closing.
	 */
	public int close()	throws TransportException	{

		// Close the sockets.
		if (socket != null)	{			
			try	{
				socket.close();			
			}catch(IOException ie)	
				{
				ie.printStackTrace();
				return(SessionTransportProvider.ERROR);
				}
			socket = null;		
		}				
		return(SessionTransportProvider.OK);
	}

	/**
	 * Returns the port of the TCP socket.
	 *
	 * @return the port number used by this socket interface.
	 */
	public int getPort ()	{

		return (socket.getLocalPort());
	}

	/**
	 * Returns the socket address of the TCP socket.
	 *
	 * @return the address used by this socket interface.
	 */
	public InetAddress getAddress ()	{
		return (socket.getInetAddress());
	}
	
	/**
	 * Returns the hostname of the TCP socket.
	 * @return the hostname associated with this Socket interface.
	 */
	public String getHostName()	{
		return hostName;
	}	
}
										  
