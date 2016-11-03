<%-- $Id: ProcessLogin.jsp,v 1.17.2.17 2002/10/26 06:10:08 araman Exp $ --%>

<%@page import="java.net.URLEncoder" %>
<%@page import="java.util.Enumeration" %>
<%@page import="java.util.List" %>
<%@page import="java.text.MessageFormat" %>
<%@page import="java.util.Properties" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.adventnet.nms.db.util.DBXmlUpdate" %>
<%@ page import="com.adventnet.nms.startnms.NmsMainFE" %>

<%@page import="com.adventnet.nms.util.PureServerUtilsFE" %>
<%@page import="com.adventnet.nms.util.NmsUtil" %>
<%@page import="com.adventnet.nms.util.RunProcessInterface" %>
<%@page import="com.adventnet.nms.util.NmsLogMgr" %>
<%@page import="com.adventnet.management.log.Log" %>
<%@page import="com.adventnet.nms.jsp.JspUtility" %>
<%@page import="com.adventnet.nms.commonfe.GenericFEAPIImpl" %>
<%@page import="com.adventnet.security.authentication.AuthenticationException" %>
<%@page import="com.adventnet.nms.jsp.SessionListener" %> 

<%
// Added by Shanmugam on 11/7/2002 for I18N
String Lang = (String)session.getAttribute("Language");
String Coun = (String)session.getAttribute("Country");
String contentType=  "text/html;charset="+JspUtility.getContentType(Lang,Coun);
response.setContentType(contentType);
// End of fix by Shanmugam
boolean isPasswordExpired = false;
boolean firstLogin = false;
String message = null;
String loginURL = "/jsp/Login.jsp?";
String uri = request.getRequestURI();
String queryStr =  null;
String requestMethod = request.getMethod();

// get the query string if the request method is "GET" 
if( requestMethod.equalsIgnoreCase("GET") ) {

	queryStr = request.getQueryString();

}
// form the query string in case of "POST" method
// if the amount of data is large, this may fail..
else if ( requestMethod.equalsIgnoreCase("POST") ) {
	boolean isSingleButton=false;
	if(request.getParameter("buttonCount")!=null)
	{
		isSingleButton=true;
	}
		

	Enumeration parameterNames = request.getParameterNames();
	StringBuffer temp = new StringBuffer();
	while ( parameterNames.hasMoreElements() ) {
		String paramName = (String) parameterNames.nextElement();
			if(!paramName.equals("userName") && !paramName.equals("password") && !paramName.equals("login") && !paramName.equals("requestfrom")  && !paramName.equals("javaui") && (  isSingleButton ||(!isSingleButton && !paramName.equals("htmlui")) )  ){
				String paramValue = request.getParameter(paramName);
				if(paramValue != null)
				{
					paramValue = URLEncoder.encode(paramValue);
				}
				temp = temp.append(paramName).append("=").append(paramValue).append("&");
			}
	}
	queryStr = temp.toString();

}
String requestfrom = request.getParameter("requestfrom");

// when the user goes back to the login page and enters some junk values 
// for username and password and clicks HTML UI, he is allowed to see the client.
// this check is made to overcome that invalid input.
/*
if( requestfrom != null && requestfrom.equals("loginPage") ) {
	session.removeAttribute("userName");
	session.removeAttribute("password");
}
*/
if(session.getAttribute("userName") == null || ( requestfrom != null && requestfrom.equals("loginPage") )  ){


	String userName = request.getParameter("userName");
	if(userName == null || userName.equals("null") || userName.trim().equals("")){
		if(requestfrom == null){
			loginURL +="message="+URLEncoder.encode(NmsUtil.GetString("Please authenticate yourself"))+"&buttonCount=singleButton&uri="+uri;
		} else {
			loginURL +="message="+URLEncoder.encode(NmsUtil.GetString("Please enter user name"));
		}
		if(queryStr != null){
			loginURL += "&"+queryStr;
		}
		
		%>
		<script>
		window.open("<%=loginURL%>","_top");
		</script>
		<%
		return;
	}
	String password=request.getParameter("password");
	if(password==null || password.equals("null") || password.trim().equals("")){
		if(requestfrom == null){
			loginURL +="message="+URLEncoder.encode(NmsUtil.GetString("Please authenticate yourself"))+"&buttonCount=singleButton&uri="+uri;
		} else {
			loginURL +="message="+URLEncoder.encode(NmsUtil.GetString("Please enter password"));
		}
		if(queryStr != null){
			loginURL += "&"+queryStr;
		}
		%>
		<script>
		window.open("<%=loginURL%>","_top");
		</script>
		<%
		return;
	}
	String serverNotInitialized = new String("<HTML><HEAD><TITLE>Please try again...</TITLE><LINK HREF=../template/nmshtmlui.css rel=stylesheet></HEAD><BODY><I><B><FONT id='leftcap'>" + NmsUtil.GetString("AdventNet") + " " + NmsUtil.GetString("WebNMS Version x.x") + "</FONT></B></I><P>&nbsp;</P><P>&nbsp;</P><HR><P>&nbsp;</P>&nbsp;<P></P>&nbsp;<P></P>&nbsp;<P></P><FONT class='redFont'><CENTER>The server is not yet initialized. It will take a while to do so. Please try again...</CENTER></FONT></BODY></HTML>");
	if(!NmsUtil.webNMSModulesStarted) {
			out.println(serverNotInitialized);
			out.flush();
            out.close();
			return;
	}
	for(Enumeration e = NmsUtil.runProcessModules.keys();e.hasMoreElements();) {
		String key = (String)e.nextElement();
		RunProcessInterface rpi = (RunProcessInterface)NmsUtil.runProcessModules.get(key);
		if(!rpi.isInitialized()) {
			out.println(serverNotInitialized);
			System.err.println(key + NmsUtil.GetString(" module not initialized"));
			out.flush();
			out.close();
			return;
		}
	}

	if ( !com.adventnet.nms.startnms.NmsMainFE.isStarted ) {

		System.err.println(NmsUtil.GetString("FATAL : Front End Server is not yet started"));
		%>
			<HTML>
			<TITLE>
                             <%= NmsUtil.GetString("Front End Server is not Started/Initialized")%>
			</TITLE>
			<LINK HREF=../template/nmshtmlui.css rel=stylesheet>
			<BODY>
			<I><B>
			<FONT id=leftcap><%= NmsUtil.GetString("AdventNet") %> <%= NmsUtil.GetString("WebNMS Version x.x") %></FONT>
			</B></I>
			<hr>
			<br><br><br><br><br><br>
			<center><font class=redFont><%= NmsUtil.GetString("Front End Server is not yet started or initialized !!.")%></font>
			</BODY>
			</HTML>
		<%
		return;
	}
	// match the password with the that in the db.
	boolean resultOfVerification = false;

	// getting the remote host name for passing it
	// to the api. This will be used for logging.
	String remoteHost = request.getRemoteHost();

	// There are some problems in getRemoteHost with Tomcat 3.2 
	// It returns null in ajp13 and empty string in ajp12.
	if(remoteHost == null || remoteHost.trim().equals(""))
	{
		// In this case, we use IP Address.
		remoteHost = request.getRemoteAddr();
	}

	Properties userProps = new Properties();
	// the key should be hostname.
	userProps.setProperty("hostname", remoteHost);

	try{
                int maxSession = 0;
                String count = NmsUtil.getParameter("MAX_CLIENT_SESSIONS");
                if(count != null) 
                {  
                     try
                     {
                         maxSession = Integer.parseInt(count);
                     }
                     catch(Exception ee)
                     {
                         maxSession = 0;
                     }
                }
                List actualUsers = GenericFEAPIImpl.getAPI().getActualUsers();
                //Verify for null check.... of actualUsers
                if(maxSession > 0 && (actualUsers.size() >= maxSession))
                {
                    loginURL += "message="+URLEncoder.encode(NmsUtil.GetString("Maximum Client Session exceeds, unable to create new session")); 

                     %>
                     <script>
                     window.open("<%=loginURL%>","_top");
                     </script>
                     <%
                     return;
                }

		// Passing the user properties to the API.
		resultOfVerification = PureServerUtilsFE.isPasswordCorrect(userName,password, userProps);
		//resultOfVerification = PureServerUtilsFE.isPasswordCorrect(userName,password);
	}
	catch(AuthenticationException e){
		int exceptionType = e.getExceptionType();
		if( exceptionType == AuthenticationException.PASSWORD_EXPIRED )
		{
			// setting the resultOfExpiration to true so that 
			// the userName is put in the session, 
			// which means he is authenticated.
			resultOfVerification = true;

			// setting isPasswordExpired to true so that 
			// the page is redirected to PasswordExpired.jsp 
			// for the user to change the password.
			isPasswordExpired = true;
		}
		else if( exceptionType == AuthenticationException.USER_EXPIRED )
		{
			message = NmsUtil.GetString("The User Account has EXPIRED. Please contact the Administrator.");
		}
		else if( exceptionType == AuthenticationException.LOGIN_FAILED )
		{
			message = NmsUtil.GetString("Maximum Login attempts reached. The User Account has been DISABLED. Please contact the Administrator.");
		}
		else if( exceptionType == AuthenticationException.USER_DISABLED )
		{
			message = "The User Account has been DISABLED. Please contact the Administrator.";
		}
		else if( exceptionType == AuthenticationException.USER_FORCED_OUT )
		{
		}
		else if( exceptionType == AuthenticationException.FIRST_LOGIN )
		{
			// setting the resultOfExpiration to true so that 
			// the userName is put in the session, 
			// which means he is authenticated.
			resultOfVerification = true;

			// setting firstLogin to true so that 
			// the page is redirected to PasswordExpired.jsp 
			// for the user to change the password.
			firstLogin = true;
		}
		else if( exceptionType == -1 )
		{
			message = e.getMessage();
		}
		if( message == null || message.trim().equals("") || message.trim().equals("null") )
		{
			message = NmsUtil.GetString("Login Failed. Please contact the Administrator"); 
		}
		if(!isPasswordExpired && !firstLogin)
		{
			loginURL += NmsUtil.GetString("message=")+URLEncoder.encode(message);

		
			%>
			<script>
			window.open("<%=loginURL%>","_top");
			</script>
			<%
			return;
		}
	}

	if(resultOfVerification){
		session.setAttribute("userName",userName);
		session.setAttribute("password",password);
		// session timeout period 30 minutes
		session.setMaxInactiveInterval(1800);

		// printing the userName, time and the ipAddress while logging in
        String remoteaddr = request.getRemoteAddr();
        NmsLogMgr.MISCUSER.log(NmsUtil.GetString("User ")+userName+NmsUtil.GetString(" logged into the Browser Client at ")+ JspUtility.gettime() +NmsUtil.GetString("from ")+ remoteaddr,Log.SUMMARY);
     
	 // code moved from index.jsp for SP7
	 if (!NmsMainFE.singleJVM)
	 {
		 try
		 {
			 //this has to be the first executable after getting the username
			 NmsMainFE.downloadClientFilesFromBE(userName);
		 }
		 catch (Exception e)
		 {
			 NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in downloading client related files from the BackEnd Server..."), e);
			 //we can also quit
		 }
	 } 
   //To Populate Database from Tree.xml for new user
	Connection con = null;
	try
	{
		con = NmsUtil.relapi.getConnection();
		DBXmlUpdate dbxmlupdate = new DBXmlUpdate(con);
		boolean ans =  dbxmlupdate.updateDB(userName,"Tree.xml");
		NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Populated database for User ")+userName+" "+String.valueOf(ans)+" ",Log.SUMMARY);

	}
    catch (Exception e)
	{
		NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in Populating the database for the user..."), e);
		out.println("<br><br><center>"+ e.getMessage()+"</center>");
		return;
	}
	// adding the user to active user list
	
	if(request.getParameter("htmlui")!=null)
	{
		GenericFEAPIImpl.getAPI().addActiveUser(userName);
	}

		Properties props = new Properties();
		props.setProperty("userName", userName);
		props.setProperty("hostname", remoteHost);
		SessionListener sessionListener = new SessionListener(props);
		session.setAttribute("sessionListener", sessionListener);

		// if the password is expired, the page is redirected to PasswordExpired.jsp
		// so that the user can change the password.
		if(isPasswordExpired){
			loginURL="/jsp/PasswordExpired.jsp";
			if(queryStr != null){
				queryStr = queryStr.replace(' ','+');
				loginURL += "?htmlui=&"+queryStr;
			}
			%>
				<script>
				window.open("<%=loginURL%>","_top");
				</script>
			<%
		}
		if(firstLogin){
			loginURL="/jsp/PasswordExpired.jsp";
			if(queryStr != null){
				queryStr = queryStr.replace(' ','+');
				loginURL += "?htmlui=&firstLogin=true&"+queryStr;
			}
			%>
				<script>
				window.open("<%=loginURL%>","_top");
				</script>
			<%
		}

	}else{
		loginURL+=NmsUtil.GetString("message=")+URLEncoder.encode(NmsUtil.GetString("Login Failed. Please verify your user name and password."));
		if(requestfrom == null){
			loginURL +="&buttonCount=singleButton&uri="+uri;
		}
		if(queryStr != null){
			loginURL += "&"+queryStr;
		}
		%>
		<script>
		window.open("<%=loginURL%>","_top");
		</script>
		<%
		return;
	}
} 
%>
