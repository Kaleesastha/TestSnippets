
<HTML>
<HEAD>
<meta http-equiv="Pragma" content="no-cache">
<TITLE>Reports</TITLE>
<!---------------------This file is Report.jsp------------------------------>
<LINK REL=STYLESHEET TYPE="text/css" HREF="../template/nmshtmlui.css">
<!-- this is the html file that gets called to all types  of reports-->
</HEAD>
<%@page  import="java.util.*" %>
<%@page  import="java.net.*" %>
<%@page  import="com.adventnet.nms.fe.perf.*" %>
<%@page  import="com.adventnet.nms.fe.perf.ejb.*" %>
        <%@page  import="com.adventnet.nms.fe.common.*" %>

<%
PerfSessionBean perfSession=null;
PerfSessionBean psb=PollFE.getPerfSession();    
 CustomSessionBean csb = PollFE.getPerfSession();
 // CustomSessionBean csb = psb.getInstance();      
 TableColumn[] tc = {new TableColumn("severity", "Severity", 50), new TableColumn("source","SOURCE", 60)};  /   Properties criteria = new Properties(); 
  criteria.put("Severity","Major"); 
                     Properties PanelProps = new Properties(); 
                      PanelProps.put("parent","All");             
 String id = psb.createCustomView("root", "Bose","Level-",tc,PanelProps,criteria); 
//boolean modify = psb.modifyCustomView("root","Alerts",tc,PanelProps,criteria); 
 // boolean remove = psb.removeCustomView("root","eventtwo"); 
//boolean rename = psb.renameCustomView("root","Alerts","Alerts"); 
         //   String userName = "root"; 
          //  String module = ""; 
           // Properties p = new Properties(); 
           // p.setProperty("entity", "IF*"); //To get 
           // p.setProperty("severity", "5"); 
           // ViewCriteria criteria = new ViewCriteria("root","Stats Admin"); 
       // criteria.setUserName("root");
/*   TableColumn[] tc = {new TableColumn("agent", "Agent", 50), new TableColumn("oid","OID", 60)}; 
                      Properties PanelProps = new Properties(); 
                      PanelProps.put("parent","Stats Admin");   
                     PanelProps.put("TREE-NAME","Bose"); 
  Properties criteria = new Properties(); 
                      criteria.put("agent","I");  
                     criteria.put("isMultiplePolledData","false");
  boolean mdy = psb.modifyCustomView("root","perfone",tc,PanelProps,criteria); */
//  boolean cvid = psb.createCustomView("root","perfone","subash","Level-1",tc,PanelProps,criteria); 
  // boolean rnm = psb.renameCustomView("root","Stats Admin","subhash" );
 // boolean rmv = psb.removeCustomView("root","perfone"); 
//Throwable thr = new Throwable();  
//psb.error("Thirst",thr);  
 //  TableColumns[] tco = psb.getTableColumns(); 
  //    Properties crit = new Properties();        
      //       crit.put("agent","*");    
//crit.put("isMultiplePolledData","false");
     //   criteria.setCriteria(crit);
       // criteria.setViewId("Alerts");
       // criteria.setViewLength(100); 
       // criteria.setFromIndex(30); 
       // criteria.setOrderbyColumn("agent");
//criteria.getFromIndex();
//ViewData vd = psb.getData("root","Alerts",100,40,"entity",p); 
//ViewData vd = psb.getData(criteria);
//Vector v = vd.getData();
//int i = vd.getFromIndex();

//Properties p = new Properties();  
//p.put("id","3");  
                                                                               
     Vector tco = psb.getActivePollers("root"); 



%>
<br><b>Get Obj. Names ok : </b> <%=id%>


 













