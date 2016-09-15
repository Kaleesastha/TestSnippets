/*
  $Id: PortChecker.java,v 1.1 2002/01/02 12:44:49 jeykarwatson Exp $
 */

/**
 * PortChecker.java
 *
 *
 * Created: Fri Jun 29 20:32:33 2001
 *
 * @author <a href="mailto: "</a>
 * @version
 */
/**
 *Utility class which can be used to check whether given Port is free
 */

package com.adventnet.util;
//package jeykar;

import java.net.Socket;
import java.net.DatagramSocket;
import java.net.SocketException;

import java.io.IOException;
public class PortChecker
{
    public PortChecker ()
    {

    }

    /**
     *This method is used to check the status of a given port number. For a given port number this method returns true 
     *if this port  can be used by the application to open socket for TCP/UDP communication. In case of UDP port status on
     *local machine is checked
     *@param portno Port no specified
     *@param host Host on which we have to check for availability
     *@return boolean true if the port is free , false if it already bound by some other application
     */
    public boolean isPortFree(String host ,int portno)
    {
        Socket socket = null;
        try
        {
            socket = new Socket(host,portno);
        }
        catch(Exception es)
        {
            // check for UDP sockets bound to port. In case of UDP we will try open a datagram socket
            // if successful then port is free otherwise port is occupied
            DatagramSocket datagramSocket = null;
            
            try 
            {
              datagramSocket = new DatagramSocket(portno);
              return true;
            } catch (SocketException e) 
            {
              return false;
            }
            finally
            {
                try
                {
                    if(datagramSocket != null)
                    {
                        datagramSocket.close();                    
                    }
                }catch(Exception e1)
                {
                    e1.printStackTrace();
                }
            }

        }
        if(socket != null)
        {
            try
            {
                socket.close();
            }catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }

        return false;
    }
}// PortChecker
