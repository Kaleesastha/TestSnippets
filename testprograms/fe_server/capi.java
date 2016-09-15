import java.io.*; 
import java.util.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

import com.adventnet.nms.fe.alert.AlertFE; 
import com.adventnet.nms.fe.alert.AlertSessionBean; 
import com.adventnet.nms.fe.common.*;
import com.adventnet.nms.fe.common.ViewCriteria; 
import com.adventnet.nms.client.*;
import com.adventnet.nms.util.*;
import com.adventnet.nms.alertui.*;
import com.adventnet.nms.eventui.*;
import com.adventnet.nms.topoui.*;
import com.adventnet.nms.pollui.*;
import com.adventnet.nms.config.*;

public class capi extends HttpServlet 
{ 
    HttpServletRequest req = null;
    PrintWriter out=null;
    AlertSessionBean asb = null;
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    { 
        doGet(req, res); 
    } 
    
    public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException   
    { 
        out = res.getWriter();	
        try{
            //  ClientAlertAPI capi = new ClientAlertAPIImpl("localhost","root"); 
            // ClientEventAPI capi = new ClientEventAPIImpl("localhost","root");
            ClientTopoAPI capi = new ClientTopoAPIImpl("localhost",5000,"root");
            //  ClientPollAPI capi = new ClientPollAPIImpl("localhost","root");
            //  ClientAuditAPI capi = new ClientAuditAPIImpl("localhost","root");
            
            String viewId = "top2";
            Properties criteria = new Properties(); 
            // criteria.put("severity","2"); 
            // criteria.put("source","a*");         
            // criteria.put("category","Topology");
            criteria.put("name","r*");
            criteria.put("type","Node"); 
            int increment= 10;
            String[] displayFields={"name","type"};
            String[] displayLables={"Name","Type"};
            Properties PanelProps = new Properties();
            PanelProps.put("parent","Network Database");  
            //PanelProps.put("temporaryCustomView","true"); 
            //NmsClientTableModel cv = capi.createCustomView("customviewname",criteria); 
            NmsClientTableModel cv =capi.createCustomView(viewId,criteria,increment,displayFields,displayLables,PanelProps);
            //	out.println("saved"+capi.saveCustomView(viewId));
            out.println("------------------***-------------------");
            out.println("--------the cv is created-----"+cv);
            String str[] = capi.getDefaultDisplayFields();
            for (int i=0;i<str.length;i++)
            {
                out.println(" default display field - "+str[i]);
            } 
            try{
                out.println("__________________+++___________________");
                out.println("the getAllProperties() returns"+cv.getAllProperties(false)); 
                out.println(" the properties returned now are === " + cv.getCacheData());
            }
            catch(Exception e)
            {
                out.println("-----------------------------"+e);
                e.printStackTrace();
            }
            /*	Properties vec = cv.getCriteriaProperties();
		
		out.println("-------The properties from cv is------" + vec);
		int si = cv.getTotalRowCount();
		out.println("-------the total row count is----"+si);
		out.println("........................Newly added Methods....................");
		String[] df = capi.getDefaultDisplayFields();
		out.println("  getDefaultDisplayFields()  = ");
		for(int i=0;i<df.length;i++)
		{
                out.println(".."+df[i]);
		}
		out.println("  getDefaultDisplayLabels()  = "+ capi.getDefaultDisplayLabels()); 
            */
            out.println("   -----*  THE END  *------   ");
            
            
        }
        catch(Exception e)
        {
            out.println("Exception is" + e);
        	e.printStackTrace();
		}
        
    } 
}        //End of Program..... 

