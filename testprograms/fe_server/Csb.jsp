
<HTML>
<HEAD>
<meta http-equiv="Pragma" content="no-cache">
<TITLE>Reports</TITLE>
<!---------------------This file is Report.jsp------------------------------>
<LINK REL=STYLESHEET TYPE="text/css" HREF="../template/nmshtmlui.css">
<!-- this is the html file that gets called to all types  of reports-->
</HEAD>
<%@page  import="java.util.*" %>
<%@page  import="java.io.*" %>
<%@page  import="java.net.*" %> 
<%@page  import="javax.servlet.*" %>      
<%@page  import="javax.servlet.http.*" %>     
<%@page  import="com.adventnet.nms.fe.event.*" %>
<%@page  import="com.adventnet.nms.fe.topo.*" %>
<%@page  import="com.adventnet.nms.fe.alert.AlertFE" %>
<%@page  import="com.adventnet.nms.fe.perf.PollFE" %>
<%@page  import="com.adventnet.nms.fe.alert.AlertSessionBean" %>
<%@page  import="com.adventnet.nms.fe.common.*" %>

<%
// CustomSessionBean csb = AlertFE.getAlertSession();
// CustomSessionBean csb = EventFE.getEventSession();
// CustomSessionBean csb = TopoFE.getTopoSession();
 CustomSessionBean csb = PollFE.getPerfSession();

/* TableColumn[] tc = {new TableColumn("severity", "Severity", 50), new TableColumn("source","SOURCE",60)}; 
 Properties PanelProps = new Properties(); 
 PanelProps.put("parent","Alerts");  
// PanelProps.put("TREE-NAME","TestAlertCustomView");   
 PanelProps.put("PANEL-NAME", "com.adventnet.nms.alertui.AlertApplet"); 
 Properties criteria = new Properties(); 
 criteria.put("severity","2"); 
 criteria.put("source","IF*");         
 criteria.put("category","Topology");

 boolean vid = csb.createCustomView("root","cvid5","TestAlert5","Level-1",tc,PanelProps,criteria);       
CustomViewProperties v= csb.getViewProperties("root","cvid5"); 
Properties temp=v.getCriteriaProperties();
*/
 
String userName = "root"; 
String id = "Stats Admin"; 
Properties p = new Properties(); 
p.setProperty("name", "I*"); //To get 
//p.setProperty("severity", "2"); 
// TableColumn[] tct = {new TableColumn("who", "WHO", 50), new TableColumn("source","SOURCE",60)};
//TableColumn[] tct = {new TableColumn("node", "NODE", 50), new TableColumn("source","SOURCE",60)};
// TableColumn[] tct = {new TableColumn("isSNMP","ISSNMP", 50), new TableColumn("ipAddress","IPADDRESS",60)};
TableColumn[] tct = {new TableColumn("agent","Agent", 50), new TableColumn("oid","OID",60)};

ViewCriteria crit = new ViewCriteria(userName, id); 
crit.setViewLength(400); 
crit.setFromIndex(1);
crit.setOrderbyColumn("name"); 
crit.setCriteria(p); 
//crit.setTableColumns(tct);
crit.setPerformOR(true); 
crit.setTemporaryCustomView(false);
int total = csb.getTotalCount(crit); 
ViewData vd = csb.getData(crit); 
Vector vec = vd.getData();
int si = vec.size();
			
%>
<br><b>Get Obj. Names ok : </b> <!--%=vid%-->
<br><b>Get Obj. PROPS :   </b> <!--%=v%-->
<br><b>Get Obj. CPROPS :   </b> <!--%=temp%-->
<br><b>Get Obj. viewData :   </b> <%=vd%>
<br><b>Get Obj. vecotrOFvd :   </b> <%=vec%>
<br><b>Get Obj. vecotrSize :   </b> <%=si%>
<br><b>Get Obj. total :   </b> <%=total%>

 













