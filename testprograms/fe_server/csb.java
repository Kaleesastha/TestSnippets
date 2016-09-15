import java.io.*; 
import java.util.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

import com.adventnet.nms.fe.alert.AlertFE; 
import com.adventnet.nms.fe.alert.AlertSessionBean; 
import com.adventnet.nms.fe.common.*;
import com.adventnet.nms.fe.common.ViewCriteria; 

public class csb extends HttpServlet 
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
			 // AlertSessionBean asb = AlertFE.getAlertSession(); 
 		        CustomSessionBean csb = AlertFE.getAlertSession(); 	
			 // CustomSessionBean csb = EventFE.getAlertSession(); 	
			out.println("the handle is obtained" + csb);

		TableColumn[] tc = {new TableColumn("severity", "Severity", 50), new TableColumn("source","SOURCE",60)}; 
	 	 Properties PanelProps = new Properties(); 
	 	 PanelProps.put("parent","Alerts");  
	  	// PanelProps.put("TREE-NAME","TestAlertCustomView");   
	  	 PanelProps.put("PANEL-NAME", "com.adventnet.nms.alertui.AlertApplet"); 
	   	 Properties criteria = new Properties(); 
		// criteria.put("sysOID","*");
		 criteria.put("severity","2");
		 criteria.put("source","r*");         
		// criteria.put("category","Topology");
		    
	boolean vid = csb.createCustomView("root","cvid","TestAlert","Level-1",tc,PanelProps,criteria);      


//	boolean vidm  = csb.modifyCustomView("root","Alerts",tc,PanelProps,criteria); 
//	boolean remove = csb.removeCustomView("root","cvid"); 
//	boolean rename = csb.renameCustomView("root","Alerts","raj");
	
	CustomViewProperties v= csb.getViewProperties("root","cvid"); 
	Properties temp=v.getCriteriaProperties();
	out.println("for the ID " + vid + "  the criteria properties are "+temp);
//	out.println("for the ID " + vidm + "  the criteria properties are ");
//	out.println("the cv is removed" +remove);
//	out.println(" the cv is renamed" +rename);
  	
	
 String userName = "root"; 
 String module = "Alerts"; 
 Properties props = new Properties(); 
 p.setProperty("entity", "IF*"); //To get 
 p.setProperty("severity", "2"); 
 ViewCriteria criteria = new ViewCriteria(userName, module); 
 criteria.setViewLength(100); 
 criteria.setFromIndex(40); 
 criteria.setOrderByColumn("entity"); 
 criteria.setCriteria(p); 
 criteria.setPerformOR(true); 
 ViewData vd = asb.getData(userName, id, viewLen, fromInt,orderByName,p); 
 vd.getData();

	}
				catch(Exception e)
				{
					out.println("Exception is" + e);
				}

				} 
}        //End of Program..... 
 
