//$Id: HttpServerImpl.java,v 1.2 2007/04/30 17:21:28 tinku Exp $

package com.adventnet.adaptors.http;

import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;

import com.adventnet.adaptors.html.HttpServerInterface;
import javax.management.*;

/**
 * This class plugs in the Jetty WebServer to the HTTP Connector.
 *
 * @see HttpServerInterface
 * @since AdventNet Agent Toolkit (Java Edition) 4.2 release.
 */
public class HttpServerImpl implements HttpServerInterface
{
    private boolean authentication = false;
    private int port = 8050;
    private boolean isStarted = false;
    private MBeanServer server = null;
    //private HttpConnectorConfiguration config = null;
    //private com.mortbay.HTTP.HttpServer httpServer = null;

    /**
     * Primary constructor with configuration options.
     *
     * @param port The port of the HTTP Server - Jetty WebServer.
     * @param server The MBeanServer.
     */
    public HttpServerImpl(int port, MBeanServer server)
    {
        this.port = port;
        this.server = server;
        //config = new HttpConnectorConfiguration(server, port);
    }

    /**
     * Setter for the configuration file Name with location.
     * Dummy Implementation for HtmlAdaptor
     *
     * @param configFileName The configuration file name with path.
     */
    public void setConfigFileName(String configFileName)
    {
	// Dummy Implementation for HtmlAdaptor
    }

    /**
     * Getter for the configFileName.
     * Dummy Implementation for HtmlAdaptor.
     *
     * @return The configuration file Name by which the Server is running.
     */
    public String getConfigFileName()
    {
	// Dummy Implementation for HtmlAdaptor
	return null;
    }

    /**
     * Returns the port number in which the http server gets started.
     *
     * @return the port number.
     * @see setPort(int port)
     */
    public Integer getPort()
    {
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
        if (port.intValue() < 0 || port.intValue() > 65535)
            return;
        /** hard-coding the apache server port
         */
        this.port = 9090;
        //if(port.intValue() != this.port) {
        //this.port = port.intValue();
        //restartHttpServer();
    }
    public void setPort(int port)
    {

        if (port < 0 || port > 65535)
            return;
        /** hard-coding the apache server port
         */
        this.port = 9090;
    }


    /**
     * Setter for enabling and disabling Authentication of the WebServer.
     * Dummy Implementation for HtmlAdaptor.
     *
     * @param auth The flag for turning ON/OFF the authentication of WebServer.
     */
    public void setAuthentication(boolean auth)
    {
	// Dummy Implementation for HtmlAdaptor
    }

    /**
     * To know whether the authentication is enabled in the WebServer.
     * Dummy Implementation for HtmlAdaptor.
     *
     * @return true if the authentication of the WebServer is turned ON else false.
     */
    public boolean isAuthentication()
    {
	// Dummy Implementation for HtmlAdaptor
	return false;
    }

    /**
     * This method adds a new User with the userName and password
     * to the Authentication of the WebServer.
     * Dummy Implementation for HtmlAdaptor.
     *
     * @param userName The user name to be added.
     * @param password The password for the user.
     */
    public void addUser(String userName, String passwd)
    {
	// Dummy Implementation for HtmlAdaptor
    }

    /**
     * Starts the HttpServer - Jetty WebServer.
     * @see stopHttpServer()
     * @exception Exception on error while starting WebServer.
     */
    public void startHttpServer() throws Exception
    {
	/*	try {
		//System.out.println("The starting of HttpServer ");//No I18N
		httpServer = new com.mortbay.HTTP.HttpServer(config);
		//System.out.println("The starting of HttpServer done");//No I18N
		httpServer.join();
		//System.out.println("The starting of HttpServer after joining");//No I18N
		isStarted = true;
		} catch (Exception ee) {
		//ee.printStackTrace();
		throw new Exception("Exception while starting HttpServer");//No I18N
		}*/
    }

    /**
     * Stops the WebServer.
     * @exception Exception on error while stopping WebServer.
     */
    public void stopHttpServer() throws Exception
    {
	/*	httpServer.stop();
		httpServer = null;
		isStarted = false;*/
    }

    /**
     * Restarts the WebServer.
     * @exception Exception while error trying to restart the WebServer
     */
    public void restartHttpServer() throws Exception
    {
	/*	try {
                stopHttpServer();
		} catch (Exception ee) {
                throw new Exception("Exception while stopping WebServer in restart");//No I18N
		}

		try {
                startHttpServer();
		} catch (Exception eee) {
                throw new Exception("Exception while starting WebServer in restart");//No I18N
                }*/

    }

}
