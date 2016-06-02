/* $Id: SshAsyncApp.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $*/
/*
 * @(#)SshAsyncApp.java
 * Copyright (c) 2003 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

import com.adventnet.cli.*;
import com.adventnet.cli.transport.*;
import com.adventnet.cli.transport.ssh.SshProtocolOptionsImpl;
import com.adventnet.cli.transport.ssh.SshTransportProviderImpl;
import java.io.*;

/** 
 * This Sample application makes use of CLI API's to create either a Dedicated 
 * or NonDedicated CLISession and send Asynchronous CLI Commands to the Device 
 * according to the options provided. In the Asynchronous mode the message is 
 * sent to the Device and the response is received in the callback method 
 * implementation. The send() method in CLISession is used which returns 
 * immediately without blocking. The message id is returned from this method.
 *
* Options:
 * [-s]                        - Dedicated(d) or Nondedicated(nd) session. Default is Dedicated.
 * [-n]			       - Remote port. The default is the ssh port(22).
 * [-cp] 		       - Command Prompt. This is the prompt displayed by the device 
                                 for each command. Default is '$'
 * RemoteHost	  Mandatory    - The host to which the session is to be established.
 * [-l]                        - One of the user name/login name  present in the remotehost.
 * [-p]                        - Password for the user.
 * [-d]                        - Set Debugger.The option -d enables setDebug true. Default is false. 
 * Command 	  Mandatory    - This represents the actual command sent to the Host.
 *
 * Example Usage: java SshAsyncApp localhost -l guest -p guest "ls -l" 
 */  
 

public class SshAsyncApp implements CLIClient{
    
    public static void main(String args[]){
			
	// Take care of getting options
        
	String usage = "java SshAsyncApp [-s session(d/nd)] [-n RemotePort] [-cp CmdPrompt] [-l LoginName ] [-p Password ] [-d Debug ] RemoteHost Command";
        String options[] = { "-s", "-n", "-cp", "-l", "-p", "-d" };
        String values[] = { null, null, null, null, null, "None" };

	String cmdData = null;
	boolean enablePooling = false;
	boolean isDebug = false;

	ParseOptions opt = null;
        try{
            opt = new ParseOptions(args,options,values, usage);
            if (opt.remArgs.length < 2) {
                opt.usage_error();
            }
        }catch(Exception e){
            System.exit(1);
        }
	
	
	/* Instantiating SshProtocolOptionsImpl */
	/** 
	 * Constructs ProtocolOptions for Ssh Connections. Contains the
	 * RemoteHost/RemotePort and other parameters.
	 */
	SshProtocolOptionsImpl spoi=new SshProtocolOptionsImpl();

	if(values[0]!=null && values[0].toLowerCase().equals("nd")){
			enablePooling=true;
	}

	if(values[1]!=null){
	    try{
		spoi.setRemotePort(Integer.parseInt(values[1]));
	    }catch(Exception e){
		System.out.println("Invalid Port: " +e);
		System.exit(1);
	    }
	}
	else{
	    spoi.setRemotePort(22);
	}

	if(values[2]!=null){
	    spoi.setPrompt(values[2]);
	}
	else{
	    spoi.setPrompt("$");
	}

	if(values[3]!=null){
	    spoi.setLoginName(values[3]);
	} 

	if(values[4]!=null){
	    spoi.setPassword(values[4]);
	}
	
	if(values[5].equals("Set")){
	    isDebug = true ;
	}

	/* Instantiating CLIMessage */
	/**
	 * This class contains the CLI command(message) to be sent. Other parameters 
	 * like the CLIProtocolOptions can be set so that the message is sent to the 
	 * appropriate device. API users have to instantiate this class with the 
	 * message to be sent and pass it to send() or syncSend() in CLISession class
	 * to send the message.
	 */
	
	CLIMessage climsg=new CLIMessage("");
	
	CLISession clisession=null;

	/* Setting the host in SshProtocolOptionsImpl according to the user's input */	

	spoi.setRemoteHost(opt.remArgs[0]);
	
	if(opt.remArgs.length>1){	
	    cmdData = opt.remArgs[1];
	}
	
	// setting the command if the command contains multiple strings.
		
	if( opt.remArgs.length >= 2 ){
	    for(int i=2;i<opt.remArgs.length;i++){
		cmdData = cmdData + " " + opt.remArgs[i];
	    }
	}

	climsg.setData(cmdData);
		
	/* Creating the CLISession */
	try{
	    clisession=new CLISession(spoi,enablePooling);
	    if( isDebug ) {
		clisession.setDebug(true);
		clisession.setDebugLevel(2);
	    } else {
		clisession.setDebug(false);
	    }
            // setting the ssh transport provider before opening session.
            clisession.setTransportProviderClassName
                ("com.adventnet.cli.transport.ssh.SshTransportProviderImpl");
	    clisession.open();
	    System.out.println("Initial log: " + clisession.getInitialMessage());
	}catch(Exception e){
	    if(e instanceof LoginException){
		System.out.println("Login Failure: "); 
		e.printStackTrace();
		System.exit(1);
	    }
	    else if(e instanceof ConnectException){
		System.out.println("Connect Failure: ");
		e.printStackTrace();
		System.exit(1);
	    }
	    else{
		e.printStackTrace();
		System.exit(1);
	    }
			
	}

	SshAsyncApp sshApp =  new SshAsyncApp();
	clisession.addCLIClient(sshApp);
		
	/* Sending the Message...Asynchronously */
	try {
	    climsg.setCLIPrompt(spoi.getPrompt());
	    clisession.send(climsg);
	} catch(Exception e) { 
	    System.out.println("IOException....Read timed out");
	    e.printStackTrace();
            try{
            clisession.close();
            }catch (Exception ce){System.out.println(ce);}
	    System.exit(1);
	}
		
    } // end of main method

    public boolean callback(CLISession session,CLIMessage msg, int msgId) {
	if(msg!=null){
	    System.out.println(msg.getData());
	    System.exit(1);
	} else {
	    System.out.println(" No Response received ");
	    System.exit(1);
	}
	return true;
    } //end of callback method


} // end of class SshAsyncApp
