
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

<%
TopoSessionBean topoSession=null;
TopoSessionBean tsb =TopoFE.getTopoSession();    

boolean adnw =tsb.addNetwork("root","192.168.5.0","255.255.255.0");
boolean val22=tsb.addNodesToTopoDB("root","192.168.4.164","192.168.4.166","255.255.255.0");
//boolean val22=tsb.addNodeToTopoDB("root","192.168.4.65","255.255.255.0");
boolean inv22 =tsb.addNodesToTopoDB("root","","192.168.4.45","255.255.255.0");
boolean inv27 =tsb.addNodesToTopoDB("root","192.168.4.46","192.168.4.18","");
boolean addn1 =tsb.addNodeToTopoDB("root","192.168.4.167","255.255.255.0");
boolean chg =tsb.changeDiscInterval("root","192.168.4.0",3);
boolean cpng =tsb.changePingRetries("root","192.168.4.0",20);
boolean csnmp =tsb.changeSnmpRetries("root","192.168.4.0",20);
boolean dont = tsb.dontDiscoverNetwork("root","192.168.4.0","255.255.255.0");

%>

<br><b>instance  : </b> <%=tsb%>
<br><b>add Network: </b> <%=adnw%>
<br><b>add Node: </b> <%=val22%>
<br><b>null IP: </b> <%=inv22%>
<br><b>null Network: </b> <%=inv27%>
<br><b>add Node: </b> <%=addn1%>
<br><b>change Discovery Interval: </b> <%=chg%>
<br><b>change Ping retries: </b> <%=cpng%>
<br><b>change snmp retries: </b> <%=csnmp%>
<br><b>dont add Network: </b> <%=dont%>
