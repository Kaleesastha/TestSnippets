
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
<%@page  import="com.adventnet.nms.fe.topo.*" %>
<%@page  import="com.adventnet.nms.fe.topo.ejb.*" %>
<%@page  import="com.adventnet.nms.fe.common.*" %>
<%@page  import="com.adventnet.nms.fe.common.CustomViewProperties" %>


<%
//TopoSessionBean topoSession=null;
//TopoSessionBean tsb =TopoFE.getTopoSession();    

CustomSessionBean csb = TopoFE.getTopoSession();
//Object obj = csb.getInstance();


TableColumn[] tc = {new TableColumn("name", "Name", 50), new TableColumn("pollInterval","PollInterval", 60)};
//	TableColumn[] tc = {new TableColumn("agent", "Agent", 50), new TableColumn("oid","OID", 60)}; 
Properties PanelProps = new Properties(); 
PanelProps.put("parent","Network Database");   
//PanelProps.put("TREE-NAME","Sai2"); 
Properties criteria = new Properties(); 
criteria.put("name","r*");  


//criteria.put("isMultiplePolledData","false");
//boolean mdy = csb.modifyCustomView("root","Network Database.Nodes",tc,PanelProps,criteria); 
String cv2 = csb.createCustomView("All","ABCD002","Level-1",tc,PanelProps,criteria); 
CustomViewProperties prop = csb.getViewProperties("All",cv2);

PanelProps.put("parent","Network Database");
String cv3 = csb.createCustomView("All","ABCD003","Level-1",tc,PanelProps,criteria); 

PanelProps.put("parent",cv3);
String cv4 = csb.createCustomView("All","ABCD004","Level-1",tc,PanelProps,criteria); 

// boolean rnm = csb.renameCustomView("root","Network Database.Networks","subash");
 boolean rmv = csb.removeCustomView("All",cv3); 


String userName = "root"; 
String id = "etwork Database"; 
TableColumn[] tct = {new TableColumn("name", "Name", 50), new TableColumn("pollInterval","PollInterval",60)}; 
Properties p = new Properties(); 
p.setProperty("name", "r*"); //To get 
ViewCriteria crit = new ViewCriteria(userName, id); 
crit.setViewLength(400); 
crit.setFromIndex(1); 
crit.setOrderbyColumn("name"); 
crit.setCriteria(p); 
//crit.setTableColumns(tct); 
crit.setPerformOR(true); 
crit.setTemporaryCustomView(true); 
ViewData vd = csb.getData(crit); 						  
Vector vec = vd.getData();
int si = vec.size();
int integ = csb.getTotalCount(crit);

String userName1 = "All"; 
String id1 = cv2; 
TableColumn[] tct1 = {new TableColumn("name", "Name", 50), new TableColumn("pollInterval","PollInterval",60)}; 
Properties p1 = new Properties(); 
p1.setProperty("name", "r*"); //To get 
ViewCriteria crit1 = new ViewCriteria(userName1, id1); 
crit1.setViewLength(400); 
crit1.setFromIndex(1); 
crit1.setOrderbyColumn("name"); 
crit1.setCriteria(p); 
crit1.setTableColumns(tct); 
crit1.setPerformOR(true); 
//crit1.setTemporaryCustomView(true); 
ViewData vd1 = csb.getData(crit1); 						  
Vector vec1 = vd1.getData();
int si1 = vec1.size();
int integ1 = csb.getTotalCount(crit1);

%>

<br><b>Get custom session bean : </b> <%=csb%>
<br><b>----- cv2 ----- </b> <%=cv2%>
<br><b>------- cv2 prop -----------= </b> <%=prop%>
<br><b>+++++ cv3  +++++ : </b> <%=cv3%>
<br><b>***** cv4    ******** : </b> <%=cv4%>
<br><b>------  remove   -------- </b> <%=rmv%>

<br><b>view data of getData method++++++++++ : </b> <%=vd%>
<br><b>vector from viewdata is************* : </b> <%=vec%>
<br><b>---------------SIZE of vector is------------------ </b> <%=si%>
<br><b>----------getTotalCount() method is called ---------</b> <%=integ%>

<br><b>view data of getData method++++++++++ : </b> <%=vd1%>
<br><b>vector from viewdata is************* : </b> <%=vec1%>
<br><b>---------------SIZE of vector is------------------ </b> <%=si1%>
<br><b>----------getTotalCount() method is called ---------</b> <%=integ1%>



