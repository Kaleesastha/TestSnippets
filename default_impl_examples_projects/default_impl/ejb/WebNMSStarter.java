//$Id: WebNMSStarter.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
// Copyright (C) 2001 by AdventNet Inc.
package com.adventnet.nms.ejb.weblogic;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.StringTokenizer;
import weblogic.common.T3StartupDef;
import weblogic.common.T3ServicesDef;
import javax.naming.*;
import weblogic.jndi.*;

import com.adventnet.nms.fe.common.naming.NameLookupImpl;
import com.adventnet.nms.fe.common.naming.NameLookup;
import com.adventnet.nms.startnms.StartNmsJdbcFE;

/**
 *  This class is used to start the Web NMS Server in Weblogic.
 *  It registers in JNDI with the name specified in the argument.
 * @author T.S. Srikanth
 * @version 1.0
 * @since Web NMS 2.3
 * @see T3StartupDef
 */
public class WebNMSStarter implements T3StartupDef
{
    public WebNMSStarter()
    {
        System.out.println("WebNMSStarter instantiated.................");
    }

     private T3ServicesDef services;

    /**
     * Sets the services stub for the T3StartupDef object. The StartupDef can use the services 
     * reference to obtain access to services in the WebLogic Server. 
     * This method is invoked by the WebLogic Server prior to the startup() method.
     *
     */
    public void setServices(T3ServicesDef services)
     {
         this.services = services;
     }

    /**
     * This startup method is called by WebLogic server when the bean is launched. 
     * This is used to start the Web NMS FE server with the arguments given in startupArgs.
     *
     * @param name : Virtual name by which the class is registered as a startupClass in WebLogic.
     * @param args : A Hashtable that is made up of the name-value pairs supplied from the 
     *               startupArgs property
     * @return 'String' that is to be sent to the log file
     * @exception Exception : if there is an exception
     */
    public String startup(String name, Hashtable args) throws Exception
    {
        System.out.println("Starting the Web NMS Starter : "+name);
        try
        {
            //String arg = (String)args.get("Arguments");
            String[] argArr = getStringArr(args);
            StartNmsJdbcFE start = new StartNmsJdbcFE(argArr);
            NameLookup lookup = new NameLookupImpl();
            Environment env = new Environment();
            Context ctx = env.getInitialContext();
            ctx.bind(name, lookup);
            System.out.println( "Web NMS Factory is ready" );
            return "ok";
        }
        catch( Exception e )
        {
            throw e;
        }
    }

    /**
     * Converts Hashtable object into a String array. String array would contain key & values of
     * <code>arg</code> in the alternate positions i.e. String[0]=key1, String[1]=val1, 
     * String[2]=key2, String[3]=val2,etc.
     *
     * @param arg : Hashtable object to be converted to String array
     * @return 'String[]' containing key & value in alternate positions
     */
    private String[] getStringArr(Hashtable arg)
    {
        String[] retArr = null;
        if(arg == null)
        {
            retArr = new String[0];
            return retArr;
        }
        int count = arg.size();
        retArr = new String[count * 2];
        int i = 0;
        for(Enumeration en = arg.keys(); en.hasMoreElements();)
        {
            String key = (String)en.nextElement();
            String val = (String)arg.get(key);
            retArr[i] = key;
            retArr[i+1] = val;
            i += 2;
        }
        return retArr;
    }
}
