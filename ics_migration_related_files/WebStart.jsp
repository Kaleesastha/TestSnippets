<%-- $Id: WebStart.jsp,v 1.2.4.1.10.4 2008/01/29 04:35:46 jegannathan Exp $ --%>
<%@ page import="com.adventnet.nms.util.JnlpFileUpdater" %>
<%@page import="java.util.Properties" %>
<%@ page import="com.adventnet.security.authorization.Coding" %>



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
               // Added by Balan for SSL 
	         String	strProtocol   = request.getScheme();
             strProtocol   = strProtocol.trim(); 
		   //Add Ends
                String hostName =   request.getServerName();

                  //Added by Balan for SSL 
			/* JnlpFileUpdater parser = new JnlpFileUpdater(null);*/
			Properties userProps = new Properties();
			if (request.getParameter("username") != null) 
			{
				String userName = (String) request.getParameter("username");
				userProps.put("username", userName);
			}

			if (request.getParameter("password") != null) 
			{
				String password = (String) request.getParameter("password");
				try
				{
					password = Coding.convertToNewBase(password);
				}
				catch(Exception ex)
				{
					System.err.println("Error in encoding the password="+password);
					ex.printStackTrace();
				}
				userProps.put("password", password);
			}

            JnlpFileUpdater parser = new JnlpFileUpdater(null,strProtocol,hostName,userProps);
		    //Add Ends
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
