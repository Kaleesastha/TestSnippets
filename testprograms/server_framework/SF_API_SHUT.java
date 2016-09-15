//$id$
//$Date: 2003/06/03 06:56:40 $
//$Author: asivakumar $

package test; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.io.*; 
import com.adventnet.nms.admin.*; 
import java.rmi.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.startnms.NmsCorbaMain;
//import com.adventnet.snmp.mibs.MibOperations;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtils;

public class SF_API_SHUT extends HttpServlet 
{ 

    PrintWriter out=null;
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException 
    { 
            
        res.setContentType("text/html"); 
        out = res.getWriter(); 
        //sf_api_shut001();               
        // sf_api_shut002();
        // sf_api_shut003();
        sf_api_shut004();


    } 

public void sf_api_shut004()
    {
        sf_api_shut001();


    }


 public void sf_api_shut001()
    {
        try{
            ShutDownAPIImpl sh = ShutDownAPIImpl.getInstance(); 
            sh.shutDownNMSServer(false); 
            out.println("shutting down the server"); 
        } 

        catch (ShutDownException se) 
            { 
                se.printStackTrace(); 
            } 
        catch (Exception e) 
            { 
                e.printStackTrace(); 
            } 
    }

public void sf_api_shut002()
    {
        String list[]={"ThinAlertAPI", "TopoCustomViewAPI", "TftpAPI", "NmsPolicyAPI", "ThinPerfAPI", "EventCustomViewAPI", "ShutDownAPI", "GenericBEAPI", "NmsAuthAdminAPI", "TELNET", "EventFilterAPI", "TreeAPI", "GenericFEAPI", "PerfCustomViewAPI", "EventAPI", "UserStorageAPI", "ProvisioningAPI", "MapAPI", "AdventnetRMIAdaptor", "TopoAPI", "InventoryAPI", "APIHandler", "AlertFilterAPI", "PollAPI", "AdventnetCLIFactory", "ThinTopoAPI", "LogAPI", "ThinDeviceConfigAPI", "NmsAuditAPI", "ThinEventAPI", "TrapAPI", "SeverityFEAPI", "UserConfigAPI", "ThinMapAPI", "AlertCustomViewAPI", "NmsAuthEngineAPI", "EventParserAPI", "RMIAccessAPI", "NmsAuthenticationAPI", "ConfigurationAPI", "JdbcAPI", "SeverityAPI", "AlertAPI", "AuditCustomViewAPI"};
   
        sf_api_shut001();

        try{

        for(int i = 0; i < list.length; i++)
            {
                out.println("<br>Is the "+list[i]+" bound in registry      :"+NmsUtil.getAPI(list[i]));

            }
        }
        catch(Exception ee)
            {
                out.println("The exception is "+ee);
            }
    }
    
    
public void sf_api_shut003()
    {
        try{
            String  host="1099", portOption="localhost";
           
            //sf_api_shut001();
            String[] list = Naming.list("rmi://" + host +portOption);
            for(int i = 0; i < list.length; i++)
		{
                    out.println("" + i + ": " + list[i]);
                    list[i]=list[i].substring(12);
                    out.println("\""+list[i]+"\",");
                   
		}
            if(list.length==0)
                out.println(" there is no thread binding");
            else 
                out.println(" There is some object binded in this process");

         
        } 
        catch (Exception e) 
            { 
                e.printStackTrace(); 
            } 

      }



   
} 

