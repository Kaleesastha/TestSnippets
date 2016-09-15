package test; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.io.*; 
import com.adventnet.nms.admin.*; 
import java.rmi.*;
import com.adventnet.nms.util.*;
import com.adventnet.management.log.Log;
import com.adventnet.nms.startnms.NmsCorbaMain;
import com.adventnet.nms.topodb.*;
import com.adventnet.nms.store.*;
import com.adventnet.nms.store.relational.*;
import com.adventnet.nms.util.NmsLogMgr;
import com.adventnet.nms.util.NmsUtil;
import com.adventnet.nms.util.PureServerUtils;

import com.adventnet.nms.severity.*; 
import java.rmi.Naming; 
import java.util.*; 

public class SF_API_Severity extends HttpServlet 
{ 
    SeverityAPI sevAPI=null; 
	SeverityInfo si = null;
    PrintWriter out=null;
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException 
    { 
            
        res.setContentType("text/html"); 
        out = res.getWriter(); 
        
        try 
            { 
                //considering the BE is working remote machine and the FE is running in localhost
                String apiString = "//localhost:1099/SeverityAPI"; 
                // hostName specifies name of the host in which Web NMS Server is running. 
                sevAPI = (SeverityAPI) Naming.lookup(apiString); 
                if(sevAPI != null) 
				{
					out.println("Succeeded in getting the handle of localhost"); 
					si=  SeverityInfo.getInstance();
				}
            } catch(Exception e) 
                { 
                    out.println("Exception in accesssing the handle to SeverityInfo API " ); 
                } 

        sf_api_severity023();
        //sf_api_severity045();
        sf_api_severity046();
		sf_api_severity047();
		sf_api_severity048();
    } 

	//TestCase 023 and for Testcase 024 - change the severity column values and check

    public void sf_api_severity023()
    {
        
		int cnt = si.getDirectionOfHighestSeverity();
		out.println("<b>RESULT for TESTCASE ID SF-SI-API-024</b>");
        out.println("<br>The highest severity number for this conf  :"+si.getDirectionOfHighestSeverity());
		String str = si.getName(cnt);
		out.println("<br>NAME : " + str);

    }
	
	public void sf_api_severity045()
    {
        try{
        if(sevAPI.getInstance()!=null)
            {
                out.println("It have executed successfully");
            }
        else
            {
                out.println("Oops !!!!  There is a serious Error ");
            }
        }
        catch(Exception ee){
            out.println("the err is "+ee);
        }
    }
   
 public void sf_api_severity046()
    {
		out.println("<br><b>RESULT for TESTCASE ID SF-SI-API-046</b>");
        out.println("<br>The Iterator is   :"+si.getIterator());
    }
 
	public void sf_api_severity047()
    {
	    SeverityIterator iterator = si.getIterator();
		out.println("<br><b>RESULT for TESTCASE ID SF-SI-API-047</b>");
		int currvalue = iterator. getCurrent();
		out.println("<br>GetCurrent : " + currvalue);
		int nextval = iterator.getNext();
		out.println("<br>GetNext : " + nextval);
    }
	public void sf_api_severity048()
    {
		Vector vec = si.getNames(2);
		if (vec == null) out.println("<br><b>RESULT for TESTCASE ID SF-SI-API-047 : PASSED</b>");
		else out.println("<br><b>RESULT for TESTCASE ID SF-SI-API-047 : FAILED</b>");
			

		
	}
   
}
