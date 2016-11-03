/* $Id: ParserTableExample.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
/*
 * @(#)ParserTableExample
 * Copyright (c) 2001-2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  ADVENTNET, INC. SHALL
 * NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE  OR ITS DERIVATIVES.
 */

/**
 * This sample application makes use of the CLI Parser Framework to parse 
 * Responses for commands that ouput data in a Table format. Examples of 
 * such commands would be ls, df, route etc on Unix systems and some show 
 * commands on IOS routers. The parser makes use of an XML which defines 
 * the rules about how to parse the response for a particular command. 
 * A Sample XML is provided for some commands (like df, route etc)on Unix 
 * systems. The Example application will connect to a device based on the 
 * input options (parameters) provided on the command line. It sends the 
 * command (specified as one of the command line argument) to the device and
 * with the help of the XML parses the response returned by the device. The 
 * command should be one of those which has a corresponding parsing rule in the
 * XML. 
 * 
 * Options:
 * [-n]						- The Target Port to connect. The default is port
 *							  23
 * [-cp] 				    - Command Prompt. This is the prompt displayed
 							  by the device for each command.Default is '$'
 * [-lp]				    - The login Prompt. This is the prompt that 
 							  the device issues for getting the username.
							  Default is 'login:'
 * [-pp]					- The Password Prompt. This is the prompt that 
 							  is issued by the device for getting the 
							  password. Default is 'Password:'
 * [-l]						- The Login or User Name for the login. If this 
 							  is not provided its assumed that a username
							  is not required for login
 * [-p]						- The Password for the login. If this is not 
 							  specified its assumed that the password is
							  not required
 *[ columnNames ]			- The list of column names whose value is to be
 							  extracted and displayed.
 Mandatory arguments:

 * TargetHost				- The hostname or IP address to which the 
 							  connection has to be established.
 * command					- The command to be executed and whose response
 							  needs to be parsed. This should be one of those in
							  the ParserTableExample.xml file
 
 * Example Usage:
 * java ParserTableExample -l guest -p guest test df Use% Mounted
 *
 * java ParserTableExample -l guest -p guest test /sbin/route  Destination
 *  Interface
 * 
 * 
 */


import com.adventnet.cli.*;
import com.adventnet.cli.transport.*;
import com.adventnet.util.parser.*;
import com.adventnet.util.parser.line.*;
import java.util.*;

public class ParserTableExample {

public static void main(String args[]) throws Exception{
	
	String command = null;
	String commandName = null;
	String[] columns = null;
	String usage = "java ParserTableExample [ -n RemotePort] [ -cp CmdPrompt]  [-lp LoginPrompt ] [-pp PasswordPrompt ] [ -l LoginName ] [ -p Password ] TargetHost command [columnNames] \n\n Example Usage: \n java ParserTableExample -l guest -p guest <hostname> df Use% Mounted \n java ParserTableExample -l guest -p guest <hostname> /sbin/route  Destination Interface \n";
    String options[] = 
		{"-n", "-cp", "-lp", "-pp", "-l", "-p"};
    String values[] = 
		{ null, null, null, null, null, null};
	
	// This parses the options and arguments provided at the command line
	ParseOptions opt = null;
	try{
		opt = new ParseOptions(args,options,values,usage);
	}catch(Exception e){
		System.exit(1);
	}
	if(opt.remArgs.length<2){
		System.out.println("Usage: " + usage);
		System.exit(1);
	}


	
	int i=0;

	// Here we pick up the column names that needs to be displayed
	if(opt.remArgs.length>2){
		columns = new String[opt.remArgs.length-2];
		for(i=0;i<columns.length;i++){
				columns[i] = opt.remArgs[2+i];
		}
	}
	
	// We create a TelnetProtocolOptionsImpl for connecting with the device
	TelnetProtocolOptionsImpl tpoi=new TelnetProtocolOptionsImpl();
	// An empty CLIMessage
	CLIMessage msg = new CLIMessage("");

	// Here we set the varous Telnet Options.
	if(values[0]!=null){
		tpoi.setRemotePort(Integer.parseInt(values[0]));
	}
	else {
		tpoi.setRemotePort(23);
	}

	if(values[1]!=null){
		tpoi.setPrompt(values[1]);
		msg.setCLIPrompt(values[1]);
	}
	else {
		tpoi.setPrompt("$");
		msg.setCLIPrompt("$");
	}

	if(values[2]!=null){
		tpoi.setLoginPrompt(values[2]);
	}
	else {
		tpoi.setLoginPrompt("login");
	}

	if(values[3]!=null){
		tpoi.setPasswdPrompt(values[3]);
	}
	else {
		tpoi.setPasswdPrompt("Password");
	}

	tpoi.setLoginName(values[4]);

	tpoi.setPassword(values[5]);
	
	tpoi.setRemoteHost(opt.remArgs[0]);
	command = opt.remArgs[1];
	// We set the command provided at the command line
	msg.setData(command);

	// This will just pick up the command name alone leaving the arguments
	// so that the parser picks up the appropriate rule 
	commandName = (new StringTokenizer(command)).nextToken();

	// This creates the ParserAPI instance with the example
	// XML file
	ParserAPI api = new ParserAPI("ParserTableExample.xml");
	
	try{
		CLISession sess  = new CLISession(tpoi);
		sess.open();
		// Sends the command to the device
		CLIMessage response = sess.syncSend(msg);

		// The response is passed to the parser with the rule type and the 
		// command name. This will return the ParsedResult object which 
		// contains the parsed table.
		ParsedResult result = 
			(ParsedResult) api.parse(response.getData(),"rule1",commandName);
		// We pick up the table from the ParsedResult
		Hashtable table = result.getTableResult();
		
		// If no column names are specified at the command line all the 
		// Columns and their values are displayed
		if(columns==null){
			Enumeration en = table.keys();
			while(en.hasMoreElements()){
				String parameter = (String) en.nextElement();
				System.out.println("Column: " + parameter);
				System.out.println("Values: ");
				ArrayList paramValues = (ArrayList)table.get(parameter);
				for(i=0;i<paramValues.size();i++){
					System.out.println(paramValues.get(i));
				}
				System.out.println();
			}
		// Here only values of those columns that are specified in the command
		// line are displayed
		}else{
			for(i=0;i<columns.length;i++){
				ArrayList paramValues =  (ArrayList)table.get(columns[i]);
				if(paramValues!=null){
					System.out.println("Column: " + columns[i]);
					System.out.println("Values: ");
					for(int k=0;k<paramValues.size();k++){
						System.out.println(paramValues.get(k));
					}
					System.out.println();
				}
			}
		}
		// We close the session and exit
		sess.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	System.exit(1);
	}
}
