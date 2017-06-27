//$Id: ApacheServerImpl.java,v 1.2 2007/04/30 17:21:28 tinku Exp $
/*
 * Copyright (c) 1999 Advent Network Management, Inc. All Rights Reserved.
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

package com.adventnet.adaptors.html;

import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;

/**
 * This class plugs in the Jetty WebServer to the HtmlAdaptor.
 *
 * @see HttpServerInterface
 * @since AdventNet Agent Toolkit (Java Edition) 4.2 release.
 */
public class ApacheServerImpl implements HttpServerInterface
{

    private boolean authentication = true;
    private int port = 8030;
    private boolean isStarted = false;

    /**
     * Primary constructor for the HttpServerImpl without any configuration options.
     * @see HttpServerImpl(int port, boolean authentication)
     */
    public ApacheServerImpl()
    {
        /*
                         // loads the properties from the configuration file.
                         props = new Properties();
                         try {
                         props.load(new BufferedInputStream(new FileInputStream(configFileName)));
                         }
                         catch (Exception e)
			 {
			 System.out.println("Exception while loading Properties from configFile"); //No I18N
			 }
        */
    }

    /**
     * Secondary constructor with configuration options.
     *
     * @param port The port to start the WebServer
     * @param authentication The boolean flag to turn ON/OFF Authentication of the Requests.
     *
     * @see HttpServerImpl()
     */
    public ApacheServerImpl(int port, boolean authentication)
    {
        this.port = port;
	this.authentication = authentication;

	/*	// loads the properties from the configuration file.
                props = new Properties();
                try
                {
                props.load(new BufferedInputStream(new FileInputStream(configFileName)));
                }
                catch (Exception e)
                {
                System.out.println("Exception while loading Properties from configFile");//No I18N
                }*/

	//      props.put("main.LISTENER.all.ADDRS","0.0.0.0:"+port);

        /*if (authentication)
          {
          String str = props.getProperty("main.root.HANDLERS");
          str = "Auth;"+str;//No I18N
          props.put("main.root.HANDLERS", str);
          }
        */
    }

    /**
     * Setter for the configuration file Name with location.
     *
     * @param configFileName The configuration file name with path.
     */
    public void setConfigFileName(String configFileName)
    {
        /*  // loads the properties from the configuration file.
            props = new Properties();
            try {
            props.load(new BufferedInputStream(new FileInputStream(configFileName)));
            } catch (Exception e)
            {
            System.out.println("Exception while loading Properties from configFile"); //No I18N
            }
            this.configFileName = configFileName;
            try {
            restartHttpServer();
            }
            catch (Exception ee)
            {
            ee.printStackTrace();
            }*/
    }

    /**
     * Getter for the configFileName.
     *
     * @return The configuration file Name by which the Server is running.
     */
    public String getConfigFileName()
    {
        //return this.configFileName;
        return null;
    }

    /**
     * Returns the port number in which the http server gets started.
     *
     * @return the port number.
     * @see setPort(Integer port)
     */
    public Integer getPort()
    {
        /** hard-coding the apache port of the NMS
         */
        return new Integer(9090);
    }

    /**
     * Sets the port number in which the http server to get started.
     *
     * @param the port number of the http web-server.
     * @see getPort()
     */
    public void setPort(Integer port) throws Exception
    {
        /*if (port.intValue() < 0 || port.intValue() > 65535)
          return;

          if(port.intValue() != this.port)
          {
          //this.port = port.intValue();
          //   String serverName = (String)props.get("SERVERS");
          //String addrs = (String)props.get(serverName+".LISTENER.all.ADDRS");
          //addrs = addrs.substring(0, addrs.indexOf(":"));
          //props.put(serverName+".LISTENER.all.ADDRS", addrs+":"+port);
          //restartHttpServer();
          }*/
    }
    public void setPort(int port)
    {

    }

    /**
     * Setter for enabling and disabling Authentication of the WebServer.
     *
     * @param auth The flag for turning ON/OFF the authentication of WebServer.
     */
    public void setAuthentication(boolean auth)
    {
	this.authentication = auth;
    }

    /**
     * To know whether the authentication is enabled in the WebServer.
     *
     * @return true if the authentication of the WebServer is turned ON else false.
     */
    public boolean isAuthentication()
    {
	return this.authentication;
    }

    /**
     * This method adds a new User with the userName and password
     * to the Authentication of the WebServer.
     *
     * @param userName The user name to be added.
     * @param password The password for the user.
     */
    public void addUser(String userName, String passwd)
    {

    }

    /**
     * Starts the HttpServer - Jetty WebServer.
     *
     * @see stopHttpServer()
     * @exception Exception on error while starting WebServer.
     */
    public void startHttpServer() throws Exception
    {
    }

    /**
     * Stops the WebServer.
     * @exception Exception on error while stopping WebServer.
     */
    public void stopHttpServer() throws Exception
    {
    }

    /**
     * Restarts the WebServer.
     * @exception Exception while error trying to restart the WebServer
     */
    public void restartHttpServer() throws Exception
    {
    }

}
