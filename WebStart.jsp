<%-- $Id: WebStart.jsp,v 1.2 2007/07/12 06:46:55 srajeswari Exp $ --%>
<%@ page import="com.adventnet.nms.util.JnlpFileUpdater" %>

<html>
<head> 
<title>
    Checking the presence of Java Web Start in the Local Machine 
</title>
<LINK REL=STYLESHEET TYPE="text/css" HREF="../template/nmshtmlui.css">
</head>

<body>


<%
	String webstart = request.getParameter("webstart");
	if(webstart != null)
	{
	         String	strProtocol   = request.getScheme();
                        strProtocol   = strProtocol.trim(); 
		String hostName =   request.getServerName();
		      JnlpFileUpdater parser = new JnlpFileUpdater(null,strProtocol,hostName);
                  response.sendRedirect("../conf/WebNMS.jnlp");
	}
	else
	{
		%>
			Sorry, required parameters missing
		<%
	}
%>
</body>
</html>

