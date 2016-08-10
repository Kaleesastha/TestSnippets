<%-- $Id: index.jsp,v 1.5 2007/07/26 16:51:12 johnpaul Exp $ --%>
<%@include file="/jsp/ProcessLogin.jsp" %>
<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<%@page import="java.rmi.*" %>
<%@page import="java.net.URLEncoder" %>
<%@page import="com.adventnet.nms.startnms.NmsMainFE" %>
<%@page import="com.adventnet.nms.util.NmsUtil" %>
<%@page import="com.adventnet.nms.util.NmsLogMgr" %>
<%@page import="com.adventnet.nms.util.ClientParameters" %>
<%@page import="com.adventnet.nms.util.PureServerUtilsFE" %>
<%@page import="com.adventnet.nms.commonbe.BEServerContext" %>                
<%@page import="com.adventnet.nms.util.GenericUtility" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.SQLException" %>
<%@page import="com.adventnet.nms.db.util.DBXmlUpdate" %>
<%@page import="com.adventnet.nms.authentication.UserConfigAPI" %>
<%@page import="com.adventnet.management.log.Log" %>
<%@page import="com.adventnet.nms.topodb.TopoAPI" %>
<%@page import="org.w3c.dom.Element"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="org.w3c.dom.Node"%>
<%@page import="com.adventnet.nms.db.util.TreeAPI"%>
<%@page import="com.adventnet.nms.jsp.JspUtility" %>
<%@page import="com.adventnet.security.AuthUtil" %>
<%!
    String nodesToOpen=null;
	//SP7 this configurable parameter will decide whether to show page in framed/nonframed version
	String htmlui_frames="false";
    private String nmsArcJar = null; 	
	private String userPassword = null;

	
   private String getRealmString(String realm) {
        realm = realm.substring(1,realm.length()-1);
        StringBuffer buff = new StringBuffer();
        StringTokenizer toks = new StringTokenizer(realm,",",true);
        while(toks.hasMoreTokens()) {
            buff.append(toks.nextToken().trim());
        }
       return buff.toString();
    }
        
    Hashtable paramList = new Hashtable(50);

    public void readClientParameters() {
        readClientParameters(null);
    }
	/** To get the values for the parameters list.
	 */
	public void readClientParameters(String userName) {
		paramList.clear();
		String s = NmsMainFE.proto;
		ClientParameters clientParams = new ClientParameters();
		clientParams.readClientParameters(paramList);
		if(userName !=null)
		{
			ClientParameters userSpecificClientParams = new ClientParameters(userName);
			Hashtable userSpecificParams = new Hashtable(20);
			userSpecificClientParams.readClientParameters(userSpecificParams);
			paramList.putAll(userSpecificParams);
		}
            //Commented by Balan on 21/03/03 for Issue Reported by Hettaras NetWork Both Archive and NMSArchive is loaded in
            //ARHIVE Tag
		// nmsArcJar = (String)paramList.get("ARCHIVE");
            //Comment Ends 
            //Added by Balan on 21/03/03 for Issue Reported by Hettaras NetWork Both Archive and NMSArchive is loaded in
            //ARHIVE Tag
              nmsArcJar = (String)paramList.get("cache_archive");
              if(nmsArcJar != null)
              {
                  String arcJar = (String)paramList.get("ARCHIVE");
                  arcJar = arcJar.substring(nmsArcJar.length()+1);
                  paramList.put("ARCHIVE",arcJar);
              }
            //Add Ends
		nodesToOpen=(String)paramList.get("INIT_PANEL");
		//added for SP7
		if(paramList.get("SHOW_SINGLE_PAGE_IN_FRAMES")!=null)
			htmlui_frames=(String)paramList.get("SHOW_SINGLE_PAGE_IN_FRAMES");
		if (nodesToOpen==null)
		{
			/*
			 * If no INIT_PANEL has been specified in the clientparameters.conf file, we are
			 * providing the localnetwork map as the default one to be selected.
			 */
			nodesToOpen = "ipnet.netmap";
			try
			{
				TopoAPI topoapi = GenericUtility.getTopoAPI();
				nodesToOpen = topoapi.getLocalNetworkAddrs()+".netmap";
			}
			catch(Exception ex)
			{
				System.err.println("Exception while obtaining the LocalNetworkAddr from the Server.");
				nodesToOpen = "ipnet.netmap";	
			}
			paramList.put("INIT_PANEL",nodesToOpen);
		}
		paramList.put("CLIENT_SERVER",s);
		String clientClassName = PureServerUtilsFE.getClientTransportClassName();
		paramList.put("CLIENT_CLASS_NAME",clientClassName);
		paramList.put("KEEPALIVE_WINDOW_SIZE", new Integer(NmsUtil.keepalive_window_size).toString());
		String feTimeZoneID = TimeZone.getDefault().getID();

		BEServerContext svrCntxt=PureServerUtilsFE.getBEServerContext();
		Properties prop=svrCntxt.getProperties();
		String beTimeZoneID=(String)prop.get("TimeZoneID");

		paramList.put("BE_TIMEZONE_ID",beTimeZoneID );
		paramList.put("FE_TIMEZONE_ID",feTimeZoneID );

		String cryptoClass = NmsUtil.CRYPTO_CLASS;
	
		if (cryptoClass != null)
		{
			paramList.put("CRYPTO_CLASS", cryptoClass);
		}
	}
	%>
	<%
		String queryString = "?";

		response.setStatus( HttpServletResponse.SC_OK );
        Hashtable ht = new Hashtable(50);
        for(Enumeration parameterNames = request.getParameterNames();
            parameterNames.hasMoreElements();) {
            String param = (String)parameterNames.nextElement();
            String value = (String)request.getParameter(param);

			// to pass all the request parameters ( except userName and password )
			// to the file which is requested from the application client. ( fromAppClient )
			// previously we used request.getQuerySting(), which fails in case of POST
			if(!param.equals("userName") && !param.equals("password"))
			{
				queryString += param + "="+ java.net.URLEncoder.encode(value) + "&";
			}

			if (param == null) param = "-";
			if( (value!=null) && (value.indexOf("(")!=-1) ) {
				StringTokenizer str=new StringTokenizer(value,"(");
				value=str.nextToken();
			}
			ht.put(param,value);
        }
		//get the Country and put it in the session
		if(session.getAttribute("Country") == null) {
            if(ht.get("Country")==null) ht.put("Country","null");
            session.setAttribute("Country",((String)ht.get("Country")).trim());
	    //NmsJspUtil.country=(String)ht.get("Country");
        }
		//get the Language and put it in the session
		if(session.getAttribute("Language") == null) {
            if(ht.get("Language")==null) ht.put("Language","null");
            session.setAttribute("Language",((String)ht.get("Language")).trim());
	    //NmsJspUtil.language=(String)ht.get("Language");
        }
		// The below two lines are commented and the functionality is moved to
		// ProcessLogin.jsp by Shanmugam.PL on   11/7/2002
		//String contentType=  "text/html;charset="+JspUtility.getContentType((String)session.getAttribute("Language"),(String)session.getAttribute("Country")) ;
        //response.setContentType(contentType);
		PrintWriter p= response.getWriter();
        String remotehost = request.getRemoteHost();
        String requested_document = request.getPathTranslated();
        String userName = (String)session.getAttribute("userName");
/*
        if (!NmsMainFE.singleJVM)
        {
            try
                {
                    NmsMainFE.downloadClientFilesFromBE(userName);
                }
            catch (Exception e)
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in downloading client related files from the BackEnd Server..."), e);
                }
        } 

*/
      //To Populate Database from Tree.xml for new user

       //Connection con = null;
	   /*
       try{
        con = NmsUtil.relapi.getConnection();
        DBXmlUpdate dbxmlupdate = new DBXmlUpdate(con);
       boolean ans =  dbxmlupdate.updateDB(userName,"Tree.xml");
        NmsLogMgr.MISCUSER.log(NmsUtil.GetString("Populated database for User ")+userName+" "+String.valueOf(ans)+" ",Log.SUMMARY);
       
         }
   catch (Exception e)
                {
                    NmsLogMgr.MISCERR.fail(NmsUtil.GetString("Exception in Populating the database for the user..."), e);

                }

		*/

        String mode;
        UserConfigAPI userConfigAPI = (UserConfigAPI)NmsUtil.getAPI("UserConfigAPI");
        String realms = null;
     	if(userConfigAPI != null)
		{
			Vector realm = userConfigAPI.getAllGroupNames(userName);
			if(realm != null)
			{
				realms = getRealmString(realm.toString().trim());
			}
		}
		readClientParameters(userName);
// For htmlui alone we also provide INIT_NODE to be given as a request parameter (nodeToOpen). 
// If given this parameter will override the value of INIT_NODE which is given in clientparameters.conf.
        nodesToOpen = request.getParameter("nodeToOpen")==null?nodesToOpen : request.getParameter("nodeToOpen");
        if(session.getAttribute("increments") == null) {
            session.setAttribute("increments",GenericUtility.populateIncrementsVector(userName));
        }
        NmsUtil.current_keys.addElement(request.getRemoteAddr());

        String commonFilterAction = NmsUtil.getParameter("COMMON_NOTIFICATION");
        if(commonFilterAction != null && commonFilterAction.trim().equalsIgnoreCase("false"))
        {
            commonFilterAction = "false";
        }
        else
        {
            commonFilterAction = "true";
        }
        String passwordLength = NmsUtil.getParameter("PASSWD_MAX_LENGTH");
              if(passwordLength == null || passwordLength.trim().equals(""))
              {
	            passwordLength ="8" ;
              }
        String isDeviceView = NmsUtil.getParameter("DEVICE_VIEW");
              if(isDeviceView != null && isDeviceView.trim().equals("true"))
              {
	            isDeviceView ="true" ;
              }
%>
<HTML><HEAD>
<meta http-equiv="Content-Type" content="<%=contentType%>">
<TITLE><%=NmsUtil.GetString("AdventNet")+" " + NmsUtil.GetString("WebNMS Version x.x") %></TITLE></HEAD>
<%
		String jsessionid = request.getRequestedSessionId();
		if(jsessionid != null && request.getParameter("requestfrom") == null && request.getParameter("login") == null && ( ht.containsKey("htmlui") ||ht.containsKey("fromAppClient") )) 
{
	Cookie cook=new Cookie("JSESSIONID",jsessionid);
	cook.setPath("/");
	response.addCookie(cook);
}
if(ht.containsKey("javaui")) 
{
	if(ht.containsKey("showapplet")) {
		%>
			<!-- Added by Ramar for issue 1535012 -->
			<!-- codebase="http://java.sun.com/products/plugin/1.2/jinstall-12-win32.cab#Version=1,1,0,0">-->
			<OBJECT classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
			WIDTH="1" HEIGHT="1" ALIGN="baseline"
			codebase="http://java.sun.com/products/plugin/1.3.1/jinstall-131-win32.cab#Version=1,3,1,0">

			<!-- for applet caching -->
			<%
			if( nmsArcJar != null )
			{
				%>
                        <!-- Commented by Balan on 21/03/03 for Issue Reported by Hettaras NetWork Both Archive and 
				     NMSArchive is loaded in ARHIVE Tag
					<PARAM NAME="cache_archive" VALUE= "<%= nmsArcJar %>" >
                         -->
					<PARAM NAME="cache_option" VALUE="Plugin">
					<%
			}
		%>

			<PARAM NAME="jsessionid" VALUE="<%= session.getId() %>">
			<PARAM NAME="code" VALUE="com.adventnet.nms.startclient.NmsMainApplet.class">
			<PARAM NAME="type" VALUE="application/x-java-applet;version=1.2">
			<PARAM name=MAP_FILE value=ipnet.netmap>
			<PARAM name=SHOW_BUTTONS value=false>
			<PARAM name=PASSWORD_KEY value="<%=userName%>">
			<PARAM name=HANDLE value="<%=userName%>">
			<PARAM name=USE_MAS value=true>
			<PARAM name=USER_NAME value="<%=userName%>">
			<PARAM name=LANGUAGE value="<%=(String)ht.get("Language")%>">
			<PARAM name=COUNTRY value="<%=(String)ht.get("Country")%>">  
			<PARAM name=NMS_FE_SECONDARY_PORT value="<%= PureServerUtilsFE.nms_fe_secondary_port%>">
			<PARAM name=NMS_FE_SECONDARY_PORT_DIR value="<%= PureServerUtilsFE.nms_fe_secondary_port_dir%>">
            <PARAM name=COMMON_NOTIFICATION value="<%=commonFilterAction%>">
			<PARAM name=PASSWD_MAX_LENGTH value="<%=passwordLength%>">
			<PARAM name=DEVICE_VIEW value="<%=isDeviceView%>">
<%
        if(paramList.get("EXPOSE_PASSWORD") != null)
        {
                String val = paramList.get("EXPOSE_PASSWORD").toString();
                if("true".equals(val))
                {
					String temp = "RtoPUX";
                    String temp_password = (String)session.getAttribute("password");
					String encryptpass = AuthUtil.encryptString(temp_password,temp);
					userPassword = URLEncoder.encode(encryptpass);
%>
                    <PARAM name=PASSWORD value="<%= userPassword%>">
<%              }
        } 
%>


			
			<%
			if(!isSAServerRunning())
			{
				%>
					<PARAM name="TRANSPORT_PROVIDER" value="com.adventnet.nms.client.sas.SASClientTransporter" >
					<%
			}
		%>
			<%
			if(realms != null) {
				%>
					<PARAM name=REALMS value="<%= realms %>" >
					<%
			}

		// To pass the BE_HOST_NAME to the client!!. Actually such a thing is not required at the
		// Client side b'cos anyway the Clients' cannot contact the BE at all (Applet Security 
		// restrictions). But this is being sent JFI on the Client side.
		String be_hostName = PureServerUtilsFE.be_host;
		if(be_hostName.equals("localhost"))
		{
			try
			{
				be_hostName = java.net.InetAddress.getLocalHost().getHostName();
			}
			catch(Exception e)
			{
			}
		}
              //Added by Balan on 18/03/03 to get IPAddress of BE
             String be_address = PureServerUtilsFE.be_host;
                if(be_address.equals("localhost"))
                {
                      try
                      {
                           be_address = java.net.InetAddress.getLocalHost().getHostAddress();
                      }
                      catch(Exception e)
                      {
                      }
                }
                else
                {
                     be_address = java.net.InetAddress.getByName(be_address).getHostAddress();
                }
              //Add Ends
		%>
			<PARAM name=BE_HOST_NAME value="<%=be_hostName%>">
                  <PARAM name=BE_HOST_ADDRESS value="<%=be_address%>">
			<%
			int regPort = NmsUtil.getRegistryPort();
		//Need to do this b'cos the NmsUtil.getRegistryPort() returns -1 incase of RMI_REGISTRY_PORT not specified in the ServerParameters.conf !!
		if(regPort == -1)
		{
			regPort = 1099;
		}
		%>
			<PARAM name=RMI_REG_PORT value="<%=regPort%>">

			<%
			Calendar cal = Calendar.getInstance();
		cal.set(1998,0,1,0,0,0);
		Date d = cal.getTime();
		String s=String.valueOf(TimeZone.getDefault().getRawOffset());
		%>
			<PARAM name=jan1_98 value="<%= d.getTime() %>">
			<PARAM name=TIME_ZONE value="<%= s %>">
			<PARAM name=CHECK value="<%= request.getHeader("Authorization") %>">
			<PARAM name=osname value="<%= System.getProperty("os.name") %>">
			<%
			for(Enumeration e = paramList.keys();e.hasMoreElements();) {
				String key = (String)e.nextElement();
				%>
					<PARAM name="<%=key%>" value='<%=(String)paramList.get(key)%>'>
					<%
			}
		%>
			<COMMENT>
			<EMBED type="application/x-java-applet"
			width="1" height="1"
			code="com.adventnet.nms.startclient.NmsMainApplet.class"
			pluginspage="http://java.sun.com/products/plugin/1.3.1/"
			MAP_FILE="ipnet.netmap"
			SHOW_BUTTONS="false"
			PASSWORD_KEY='<%=userName %>'
			HANDLE='<%= userName %>'
			USE_MAS=true
			USER_NAME='<%=userName%>'
			jan1_98='<%= d.getTime() %>'
			LANGUAGE='<%=ht.get("Language")%>'
			COUNTRY='<%=ht.get("Country")%>'
			NMS_FE_SECONDARY_PORT='<%= PureServerUtilsFE.nms_fe_secondary_port%>'
			NMS_FE_SECONDARY_PORT_DIR='<%= PureServerUtilsFE.nms_fe_secondary_port_dir%>'
			PASSWORD='<%= userPassword%>'
			
			<%
			if(!isSAServerRunning())
			{
				%>
					TRANSPORT_PROVIDER="com.adventnet.nms.client.sas.SASClientTransporter"
					<%
			}
		%>


			BE_HOST_NAME='<%=be_hostName %>'
                  BE_HOST_ADDRESS='<%=be_address %>'  
			RMI_REG_PORT='<%=regPort%>'
			PASSWD_MAX_LENGTH='<%=passwordLength%>'
			DEVICE_VIEW='<%=isDeviceView%>'
			
			TIME_ZONE='<%= s %>'
			CHECK='<%=request.getHeader("Authorization") %>'
			osname='<%= System.getProperty("os.name") %>'
			<%
			if(realms != null) {
				%>
					REALMS='<%= realms %>'
					<%
			}
		for(Enumeration e = paramList.keys();e.hasMoreElements();) {
			String key = (String)e.nextElement();
			%>
				<%= key.replace('"',' ') %>='<%= paramList.get(key) %>'
				<%
		}
		%>
			jsessionid='<%= session.getId() %>'
			>

			<!-- for applet caching -->
			<%
			if( nmsArcJar != null )
			{
				%>
                            <!-- Commented by Balan on 21/03/03 for Issue Reported by Hettaras NetWork Both Archive and 
				     NMSArchive is loaded in ARHIVE Tag
					 <PARAM NAME="cache_archive" VALUE= "<%= nmsArcJar %>" >
                              --> 
					<PARAM NAME="cache_option" VALUE="Plugin">
					<%
			}
		%>



			</EMBED>
			</COMMENT>
			</OBJECT>
			</BODY>
			<%
	} else {
		/*
		   To avoid problems caused by netscape due to spaces 
		   in the Strings passed  
		 */
		String tempCountry=(String)session.getAttribute("Country");
		tempCountry=GenericUtility.replace(tempCountry," ","+");
		tempCountry=GenericUtility.replace(tempCountry,"(","%28");
		tempCountry=GenericUtility.replace(tempCountry,")","%29");

		String tempLang=(String)session.getAttribute("Language");
		tempLang=GenericUtility.replace(tempLang," ","+");
		tempLang=GenericUtility.replace(tempLang,"(","%28");
		tempLang=GenericUtility.replace(tempLang,")","%29");
		%>		
			<FRAMESET ROWS="100%,*" BORDER="0" FRAMEBORDER="NO">
			<FRAME NAME="Main Page" FRAMEBORDER="NO" MARGINHEIGHT="0" MARGINWIDTH="0" SRC="../html/splash.html">
			<FRAME NAME="contents" FRAMEBORDER="NO" MARGINHEIGHT="0" MARGINWIDTH="0" SRC="index.jsp?javaui=&showapplet=&Country=<%=tempCountry%>&Language=<%=tempLang%>" SCROLLING="NO" NORESIZE >
			</FRAMESET>
			<%
	}
}

        out.flush();
%>
<%!
    public String getNodeToOpen(String name,Element element)throws IOException
	{
        String request=null;
        if(element==null)
        {
            return null;
        }
		String url="";
		if(element.getAttribute("URL")!=null)
		url=element.getAttribute("URL");

		if(url.equals(name))
		{
		return element.getAttribute("NODEID");
		}
		NodeList nodeList =element.getChildNodes();
        if(nodeList==null)
        {
            return null;
        }
        int size = nodeList.getLength();
        for( int i=0;i<size;i++)
        {
            Element child = (Element)nodeList.item(i);
            if(child==null)
            {
                continue;
            }
			request= getNodeToOpen(name,child); 
			if(request!=null)
			return request;
        }
        return request; 
	}
	
    public Element searchForElement(String name,Element element, JspWriter out)throws IOException
    {
        String menuid = element.getAttribute("ID");
        if(element==null)
        {
            return null;
        }
        if (menuid.equals(name))
        {
            return element;
        }
        NodeList nodeList =element.getChildNodes();
        if(nodeList==null)
        {
            return null;
        }
        int size = nodeList.getLength();
        for( int i=0;i<size;i++)
        {
            Element request=null;
            Element child = (Element)nodeList.item(i);
            if(child==null)
            {
                continue;
            }
            request= searchForElement(name,child,out); 
            if (request!=null)
                return request;
        }
        return null; 
    }


	/*
		Method for getting the first leaf node for the given node, if the INIT_PANEL in ClientParameters.conf contains 
		invaild map name
	*/
	private Element getLeafNode(Element node)
	{
		Element leafNode = null;
		NodeList children = node.getChildNodes();
		int length = children.getLength();
		for(int i=0; i<length; i++)
		{
			Element childNode = (Element) children.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE) continue;
			NodeList childList = childNode.getChildNodes();
			int childCount = childList.getLength();

			// for checking whether the childNode is the leaf or not 
			Element firstChild = null;
			for(int j=0;j<childCount;j++)
			{	
				Node cNode = childList.item(j);
				if (cNode.getNodeType() == Node.ELEMENT_NODE)
				{
					firstChild = (Element) cNode;
					break;
				}
			}
			if (firstChild == null)
				return childNode;
			else 
				return getLeafNode(childNode);
		}
		return null;
	}

    private String getReqString(Element node)
    {
        if(node==null)
        {
            return "../jsp/ShowImage.jsp?imageName=../images/screen.png";
        }
        String menuid=node.getAttribute("ID");
        if(menuid!=null)
	{
            menuid=java.net.URLEncoder.encode(menuid);
        }
        String menuTreeName=node.getAttribute("TREE-NAME");
        String menuFileName= node.getAttribute("MENU-FILE-NAME");
        StringBuffer buffer = new StringBuffer(node.getAttribute("URL"));
        if(buffer == null || buffer.length()==0)
        {
            buffer = new StringBuffer();
            buffer.append("jsp/ShowImage.jsp?imageName=../images/screen.png");
        }
        if(buffer.toString().indexOf(":") == -1)
        {
            buffer.insert(0,"../");
            if(buffer.toString().indexOf("?") == -1)
            {
                buffer.append("?");
            }
            else
            {
                buffer.append("&");
            }
            buffer.append("type=").append(menuid);
            buffer.append("&displayName=").append(java.net.URLEncoder.encode(menuTreeName));
            buffer.append("&menuFileName=").append(menuFileName);
        }
        return buffer.toString();
    }

    private boolean isSAServerRunning()
    {
          return   com.adventnet.nms.fe.sas.NmsSAServerFE.isSAServerRunning();
    }

%>


