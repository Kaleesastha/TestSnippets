//$Id: TftpMibInstrument.java,v 1.2 2007/04/30 17:39:31 tinku Exp $
/* Copyright (c)  1996 - 2004  Adventnet, Inc. All Rights Reserved.
 * PLEASE READ THE ASSOCIATED COPYRIGHTS FILE FOR MORE DETAILS.
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT.
 * ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.
 */

/**
 * @Version :  6.0.0 Wed Feb 14 00:34:06 IST 2007
 * @Author  :  AdventNet Agent Toolkit Java Edition
 */

// Any changes made to this file will be lost, if regenerated.
// User code should be added within user tags for code merging support, if regenerated.



// Package Name (Dont Delete this comment)
package com.adventnet.nms.jmxagent ;

import com.adventnet.utilities.common.*;
import com.adventnet.utils.agent.utils;

import java.io.*;
import java.util.*;



/**
 * Handles all the requests under TftpMib group
 */
public class TftpMibInstrument {



	protected String serverName = "serverName not initialized" ; //No I18N
	protected Integer portNum = new Integer(1) ;
	protected String sourceFile = "sourceFile not initialized" ;//No I18N
	protected String destinationFile = "destinationFile not initialized" ;//No I18N
	protected Integer tftpMode = new Integer(1) ;
	protected Integer request = new Integer(1) ;


   // User code starts here
    //by senthil
    AdventNet_WebNMS_MIB_JMX agentRef = null;

    //constructor with agentRef
    public void setAgentReference(AdventNet_WebNMS_MIB_JMX agent)
    {
	this.agentRef = agent;
	initDefValues();
    }

    /*
     * Getting the initial values from DataBase using UserStorageAPI
     */
    void initDefValues()
    {
	String temp;
	serverName = agentRef.getDefValue("serverName");//No I18N

	temp = agentRef.getDefValue("portNum");//No I18N
	portNum = (!temp.equals("")) ? new Integer(temp) : new Integer(-1) ;

	sourceFile = agentRef.getDefValue("sourceFile");//No I18N
	destinationFile = agentRef.getDefValue("destinationFile");//No I18N

	temp = agentRef.getDefValue("tftpMode");//No I18N
	tftpMode = (!temp.equals("")) ? new Integer(temp) : tftpMode ;
    }
    // User code ends here

	/**
	 * Handles the  JMX  Get Request for serverName
	 */
	public String  getServerName()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return serverName;
	}


	/**
	 * Handles the  JMX  Set Request for serverName
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setServerName (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		serverName = value;
		// User code starts here
	agentRef.updateDefValue("serverName",value);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for portNum
	 */
	public Integer  getPortNum()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return portNum;
	}


	/**
	 * Handles the  JMX  Set Request for portNum
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setPortNum (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		portNum = value;
			// User code starts here
	agentRef.updateDefValue("portNum",value.toString());//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for sourceFile
	 */
	public String  getSourceFile()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return sourceFile;
	}


	/**
	 * Handles the  JMX  Set Request for sourceFile
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setSourceFile (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		sourceFile = value;
		// User code starts here
	agentRef.updateDefValue("sourceFile",value);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for destinationFile
	 */
	public String  getDestinationFile()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return destinationFile;
	}


	/**
	 * Handles the  JMX  Set Request for destinationFile
	 * @param String
	 * @throws AgentException on error
	 */
	public synchronized void setDestinationFile (String  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!(((value.length()>=0)&&(value.length()<=255))))
			throw new AgentException("", CommonUtils.WRONGLENGTH);

		destinationFile = value;
			// User code starts here
	agentRef.updateDefValue("destinationFile",value);//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for tftpMode
	 */
	public Integer  getTftpMode()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return tftpMode;
	}


	/**
	 * Handles the  JMX  Set Request for tftpMode
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setTftpMode (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);

		tftpMode = value;
			// User code starts here
	agentRef.updateDefValue("tftpMode",value.toString());//No I18N
	// User code ends here
	}


	/**
	 * Handles the  JMX  Get Request for request
	 */
	public Integer  getRequest()
				 throws AgentException
	{
		// Fill up the stub with required processing

		return request;
	}


	/**
	 * Handles the  JMX  Set Request for request
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setRequest (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		if(!((value.intValue() == 1)||(value.intValue() == 2)))
			throw new AgentException("", CommonUtils.WRONGVALUE);

		request = value;
			// User code starts here
	//by senthil
	if( (serverName.trim().equals("")) ||
	    (sourceFile.trim().equals("")) ||
	    (destinationFile.trim().equals("")) ||
	    (portNum.intValue() == -1) )
	    throw new AgentException("", CommonUtils.BADVALUE);
	/*
	 * Performing the TFTP to the given server with the specified file
	 */
	try
	{
	    com.adventnet.nms.tftp.TftpClientAPI tftpclientapi = new com.adventnet.nms.tftp.TftpClientAPI();
	    tftpclientapi.setTftpPort(portNum.intValue());
	    if(tftpMode.intValue() == 1)
		tftpclientapi.setMode("netascii");//No I18N
	    else
		tftpclientapi.setMode("octet");//No I18N
	    if(value.intValue() == 1)
		tftpclientapi.get(serverName,sourceFile,destinationFile);
	    else
		tftpclientapi.put(serverName,sourceFile,destinationFile);
	}
	catch(Exception e)
	{
	    agentRef.agentErr.fail("Exception" ,e);//No I18N
	    throw new AgentException("", CommonUtils.BADVALUE);
	}
	// User code ends here
	}
}


