//$Id: ProcessTest.java,v 1.2 2010/10/29 13:45:39 swaminathap Exp $
/*
 * ProcessTest.java
 */

package com.adventnet.nms.tester;

import java.lang.*;
import java.io.*;
import java.util.*;
import com.adventnet.nms.util.PureServerUtilsBE;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.startnms.MainSocketSessionBE;
import com.adventnet.nms.startnms.SocketServerConnectionBE;
import com.adventnet.nms.util.RunProcessInterface;
import java.rmi.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.poll.*;

/**
 *  The main program for the data tester . This is written as an example
 * for starting a module along with the webnms core modules by implementing
 * the RunProcessInterface and specifying in the conf file
 * NmsProcessesBE.conf / NmsProcessesFE.conf depending on
 * where we want to start the module.
 * In this example let us start the new moudle in the Backend.
 *
 * This class also serves as an example for establishing communication
 * between the user's client and server modules through the common socket.

 * Since this is running in the Backennd it needs to implement the 
 * SocketServerConnectionBE interface .
 * When a FrontEnd connects to the Back end server , a socket is opened and 
 * intimation is  sent to the init method of the SocketServerConnectionBE 
 * interfacce . 
 * This init method can then be used to start a session to take care of that
 * particular Frontend's need . Here the session is TestSession. The common
 * socket allows multiple sessions (each representing one module) to 
 * communicate with their respective client side modules.

 * This ProcessTest is used in conjunction with the TestSession.java found
 * in examples/client_server_communication and with TestSession.java found in 
 * examples/client_server_communication 
 *
 *       CLIENT                                  SERVER 
 *       ~~~~~~~~                                ~~~~~~~
 *      TestClient ------ commonSocket ------TestSession( Used by ProcessTest)

 * To start it as a separate moudle add the following lines to 
 *                conf/NmsProcessesBE.conf
 *
 *
 *     PROCESS                 com.adventnet.nms.tester.ProcessTest
 *     ARGS                      NULL
 *
 *
 *
 * 


 * Note that if this module  had been started with the FE  then it  needs to
 *  implement  SocketServerConnectionFE interface . And when a client connects
 * the init methods would be invoked.
 */

public class ProcessTest extends Thread implements RunProcessInterface, SocketServerConnectionBE {

  PollAPI pollapi = null;



  // As mentioned earlier  the name of the class implementing 
  // RunProcessInterface will be mentioned in  the  procesSmall_begin.conf_BE 
  // configuratino file. This file is read by the WebNms sytem on startup
  // An object of that class is instantiated and the method callMain is
  //  invoked on that object. So it is  better that the class implementing
  // RunProcessInterface does  not do anyting in its constructor.

  public ProcessTest() {

	// all required things will be done in the call Main Method.
  }	



  public void callMain(String argv[]) {
    System.out.println(NmsUtil.GetString("Main called : ProcessTest"));

    //this is for registering with common Socket .  Whenever a  new 
    // request for connection  comes the  init method will be invoked
    // that time you have to instantiate a new Test Session

    PureServerUtilsBE.serverSocketBE.registerForResponses(this);
    System.out.println(NmsUtil.GetString("About to start processTest thread "));
    initialized=true;
    System.out.println(NmsUtil.GetString("ProcessTest module Initialized"));
    start();


    // If this module had been started with the Front end then we would
    // have extended SocketServerConnectionFE  and we would have registered
    // with PureServerUtilsFE.serverSocketFE.registerForResponses
  }

  static boolean initialized = false;	

  /**
   * Returns whether the ProcessTest has been initialized or not.
   * It is very important that this returns true as soon as it is initialized
   * as otherwise it will unneccesarily delay the discovery module . 
   * The dicovery module waits for all other modules which are started 
   * through the RunProcessInterface route to get initialized and only then
   * does discovery start
   * In fact even the connectivity to the server via the webserver will be
   * blocked till all modules get initialized .
   */
  public boolean isInitialized()
    {
      return initialized;
    }



  public static void main(String args[]) {
    ProcessTest pTest = new ProcessTest();
	pTest.callMain(args);
  }

    /*
     * When a new client connects to the server , the server instantiates
     * a MainSocketSessionBE to take care of that client's needs . It then calls
     * the following method so that this module can establish contact with its
     * client-side counterpart . In this code we just instantiate a new
     * instance of TestSession which will take care of that client's needs
     */ 
  public void init(MainSocketSessionBE mss) {
    System.out.println(NmsUtil.GetString("In processTest : about to start a test session "));
    TestSession session = new TestSession(this,mss);  // open new session
  }

  public void run() {
    System.out.println(NmsUtil.GetString("In run method of processTest "));
    try {
      sleep(60000);
    } catch (InterruptedException ie){}
    pollapi = getPollAPI();
  }

/** This gets the PollAPI for use  **/
  public PollAPI getPollAPI() {
    PollAPI api=null;
    try
		{   
			api = (PollAPI)NmsUtil.getAPI("PollAPI");//No I18N
			while( api == null)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception e)
				{}
				api =(PollAPI)NmsUtil.getAPI("PollAPI");//No I18N
			}
						
		}
		catch ( Exception e)
		{
			System.err.println(NmsUtil.GetString("From ProcessTest, in getting the PollAPI Instance"));
			e.printStackTrace();			
		}
    return api;
  } // end  getPollAPI()
  
  /**
   * When the WebNMS server is being shutdown by the user
   * this method will be called. So, the implementation of this
   * method could take care to save the state if any.
   */
  public void shutDown()
  {
  }
}

