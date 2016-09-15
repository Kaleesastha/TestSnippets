
<HTML>
<HEAD>
<meta http-equiv="Pragma" content="no-cache">
<TITLE>Reports</TITLE>
<!---------------------This file is Report.jsp------------------------------>
<LINK REL=STYLESHEET TYPE="text/css" HREF="../template/nmshtmlui.css">
<!-- this is the html file that gets called to all types  of reports-->
</HEAD>
<%@page import=" javax.xml.parsers.DocumentBuilderFactory" %> 
<%@page import= "javax.xml.parsers.DocumentBuilder" %> 
<%@page import= "org.w3c.dom.Element"%> 
<%@page import= "com.adventnet.nms.util.NmsUtil" %> 
<%@page import= "com.adventnet.nms.fe.utils.*"%> 

<%
  try{ 
	    NmsMenuFileParser nmfp = new NmsMenuFileParser(); 
	    Element result=nmfp.getRootElement("root","framemenu",null,"DISABLED",null); 
	    out.println("<xmp>" + result + "</xmp>"); 
	   }
   catch(Exception e) 
      {
		System.err.println("Error occured createcustomview"); 
        e.printStackTrace(); 
	  } 

%>
<br><b>Get Obj. Customview TableModel :  </b> <%=nmfp%>


 













