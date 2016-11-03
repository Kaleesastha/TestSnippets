/* $Id: CorbaServer.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $ */
package com.adventnet.nms.example.southboundcorba;
import com.adventnet.nms.example.southboundcorba.device.*;

import com.adventnet.management.log.Log;

import com.adventnet.nms.util.NmsLogMgr;

import com.adventnet.nms.util.RunProcessInterface;

//import com.adventnet.nms.southbound.corba.*;
import java.rmi.*;
//import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtils;
import com.adventnet.nms.util.PureServerUtilsBE;


public class CorbaServer extends Thread implements RunProcessInterface
{
    private static String args[] = null;
    
    public CorbaServer()
    {            
    }
    
    public static void main(String argv[])
    {        
        CorbaServer server = new CorbaServer();
        args = argv;
        server.run();
    }
    
    public boolean isInitialized()
    {
        return true;
    }
    
    /** To optionally run as one JVM **/
    public void callMain(String argv[])
    {
        NmsLogMgr.MISCUSER.log("Main called : Southbound CORBA Server", Log.VERBOSE);
        args = argv;
        run();
    }
    
    public void run()
    {
        try 
        {
            SCORBAAPI api = new SCORBAAPI(args);
        }
        catch ( Exception re) 
        {
            //re.printStackTrace();
            NmsLogMgr.MISCERR.fail("Exception occured in Southbound CORBA error", re);	
        }
    }
    
    public void shutDown()
    {
        if (PureServerUtilsBE.rmiBind)
        {
            try
            {
                String url = PureServerUtils.getRMIURL("ORB");
                if (url != null)
                {
                    Naming.unbind(url);
                    NmsLogMgr.MISCUSER.log("CORBA ORB successfully unbound from registry",Log.SUMMARY);
                }
            }
            catch ( Exception e)
            {
                NmsLogMgr.MISCERR.fail("Exception in unbinding CORBA ORB : " + e, null);
            }
        }
    }
    
}

