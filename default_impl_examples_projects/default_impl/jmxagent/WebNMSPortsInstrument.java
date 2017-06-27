//$Id: WebNMSPortsInstrument.java,v 1.3 2007/07/03 07:08:05 barathv Exp $
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
import com.adventnet.nms.util.PureUtils;

import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import java.io.File;
import javax.xml.parsers.*;
// User code starts here
/*
import com.adventnet.nms.util.*;
// User code ends here

/**
 * Handles all the requests under WebNMSPorts group
 */
public class WebNMSPortsInstrument {



	protected Integer httpPort = new Integer(1) ;
	protected Integer nmsSocketPort = new Integer(1) ;
	protected Integer rmiRegistryPort = new Integer(1) ;

 // User code starts here
    //by senthil
    AdventNet_WebNMS_MIB_JMX agentRef = null;

    //constructor with agentRef
    public void setAgentReference(AdventNet_WebNMS_MIB_JMX agent)
    {
	this.agentRef = agent;
    }
    // User code ends here


	/**
	 * Handles the  JMX  Get Request for httpPort
	 */
    public Integer  getHttpPort()
	    throws AgentException
    {
	    // Fill up the stub with required processing
	    // User code starts here
	    try
	    {
		    /*
		     * Getting the Http port from the file httpd.conf in the directory apache/conf.
		     */
		    String fileName = PureUtils.rootDir+ "apache" + File.separator + "tomcat" + File.separator + "conf" + File.separator + "server.xml"; // no i18n
		    File serverFile = new File(fileName);
		    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    Document doc = db.parse(serverFile);
		    Element root = doc.getDocumentElement();
		    Node detailsNod = (Node)(root.getElementsByTagName("Connector")).item(0); // no i18n
		    System.err.println(detailsNod);
		    NamedNodeMap nnm = detailsNod.getAttributes();
		    String webcontainerport = nnm.getNamedItem("port").getNodeValue().trim(); // no i18n
		    Integer ins = new Integer(webcontainerport);
		    httpPort = ins;
	    }
	    catch(Exception e)
	    {
		    agentRef.agentErr.fail("Exception" ,e);//No I18N
		    throw new AgentException("", CommonUtils.GENERR);
	    }
	    // User code ends here

	    return httpPort;
    }


	/**
	 * Handles the  JMX  Set Request for httpPort
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setHttpPort (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		httpPort = value;
	}


	/**
	 * Handles the  JMX  Get Request for nmsSocketPort
	 */
	public Integer  getNmsSocketPort()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	/*
	 * Getting the NMS Socket port from the file NMSSocketPort.html in the directory html.
	 */
	nmsSocketPort = new Integer(agentRef.getFromHtmlFile(com.adventnet.nms.util.PureUtils.rootDir + "html" + File.separator + "NMSSocketPort.html","NMSSocketPort:"));//No I18N
	// User code ends here

		return nmsSocketPort;
	}


	/**
	 * Handles the  JMX  Set Request for nmsSocketPort
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setNmsSocketPort (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		nmsSocketPort = value;
	}


	/**
	 * Handles the  JMX  Get Request for rmiRegistryPort
	 */
	public Integer  getRmiRegistryPort()
				 throws AgentException
	{
		// Fill up the stub with required processing
		// User code starts here
	/*
	 * Getting the RMI Registry port.
	 */
	rmiRegistryPort = new Integer(com.adventnet.nms.util.NmsUtil.getRegistryPort());
	// User code ends here

		return rmiRegistryPort;
	}


	/**
	 * Handles the  JMX  Set Request for rmiRegistryPort
	 * @param Integer
	 * @throws AgentException on error
	 */
	public synchronized void setRmiRegistryPort (Integer  value )
				 throws AgentException {
		// Fill up the stub with required processing

		if(value == null)
			throw new AgentException("", CommonUtils.WRONGVALUE);
		rmiRegistryPort = value;
	}
}


