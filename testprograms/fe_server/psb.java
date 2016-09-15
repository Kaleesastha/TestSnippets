import java.io.*; 
import java.util.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

import com.adventnet.nms.fe.perf.*;
import com.adventnet.nms.fe.perf.PollFE;
import com.adventnet.nms.fe.common.*;
import com.adventnet.nms.fe.common.ViewCriteria; 
import com.adventnet.nms.fe.perf.PerfSessionBean;

public class psb extends HttpServlet 
{ 
   		HttpServletRequest req = null;
		PrintWriter out=null;

	    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
		    { 
			        doGet(req, res); 
				    } 

		public synchronized void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException   
			{ 
			  out = res.getWriter();	
			  out.println("__________TEST STARTED________________");
			  try{
			 PerfSessionBean psb = PollFE.getPerfSession(); 
 		     // CustomSessionBean csb = AlertFE.getAlertSession(); 	
			 CustomSessionBean csb = PollFE.getPerfSession(); 	
			  out.println("the handle is obtained" + psb);
			
/*		TableColumn[] tc = {new TableColumn("agent", "Agent", 50), new TableColumn("oid","OID", 60)};
		Properties PanelProps = new Properties(); 
		 PanelProps.put("parent","Stats Admin");   
		 panelProps.put("TREE-NAME","Policy One");
		 Properties criteria = new properties();
		  criteria.put("agent","vasus");
		  criteria.put("isMultiplePolledData","true");
		   criteria.put("oid","2.2.1.10");  
		//criteria.put("category","Topology");
		//criteria.put("groupviewmode","max");		 
	    //Properties crit  = new Properties(); 
        //crit.put("groupviewmode","max");		                                            
 		//crit.put("AlertAge"," RMi <= 20 8"); // Dy day 
		//criteria.put("AlertAge"," RHr <= 2 8"); 
	    // crit.put("AlertAge"," RDy <= 0 10"); 
		//criteria.put("AlertAge"," ADy <= 1 10"); 
*/
		Properties p = new Properties();  
        p.put("id","1"); 
		out.println("========"+psb.getObjectsProps("root","com.poll.PolledData",p)+"========="); 



/*	boolean vid = csb.createCustomView("root","cvid","TestPerf","Level-1",tc,PanelProps,criteria);
//	boolean avid = esb.createCustomView("root","avid1","TestPerf12","Level-1",tc,PanelProps,crit);
	boolean vid1 = csb.createCustomView("All","cvid2","TestPerf2","Level-1",tc,PanelProps,criteria);

//	boolean vidm  = csb.modifyCustomView("root","cvid",tc,PanelProps,criteria); 
//	boolean vidm1 = csb.modifyCustomView("root","Events",tc,PanelProps,criteria);
//	boolean remove = csb.removeCustomView("All","cvid1"); 
	boolean rename = csb.renameCustomView("root","Stats Admin","raj");
	
	 CustomViewProperties v= csb.getViewProperties("root","cvid"); 
 //	 CustomViewProperties v1= csb.getViewProperties("All","cvid1"); 

	 
	 Properties temp=v.getCriteriaProperties();
	 out.println("for the ID " + vid + "  the criteria properties are "+temp);
//	 out.println("for the ID " + vidm + "  the criteria properties are "+temp);
//	out.println("the cv is removed" +remove);
	out.println(" the cv is renamed" +rename);
*/

/*	
	String userName = "root"; 
	String module = "Events"; 
	Properties p = new Properties(); 
   	p.setProperty("entity", "IF*"); //To get 
    p.setProperty("severity", "2"); 
	ViewCriteria crit = new ViewCriteria(userName, module); 
	crit.setViewLength(100); 
	crit.setFromIndex(40); 
	crit.setOrderByColumn("entity"); 
	crit.setCriteria(p); 
	crit.setPerformOR(true); 
	ViewData vd = csb.getData(crit); 
	vd.getData();
	out.println("the result is "+ vd);
*/	
	}
				catch(Exception e)
				{
					out.println("Exception is" + e);
				}

				} 
}        //End of Program..... 
 
 
