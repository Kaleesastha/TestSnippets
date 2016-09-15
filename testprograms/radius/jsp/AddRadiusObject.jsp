<%-- $Id: AddRadiusObject.jsp,v 1.1 2004/04/28 09:27:45 vasus Exp $ --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@page import="test.*"%>

<%


try{
DummyAuth da = new DummyAuth();
System.out.println(da.dummyAuth());
}catch(Exception e)
{
e.printStackTrace();
}


String serverName = request.getParameter("serverName");
String ipAddress = request.getParameter("hostIpAddress"); 
String authPort = request.getParameter("authPort");
String acctPort = request.getParameter("acctPort");
String secret = request.getParameter("secret");
String ok = request.getParameter("OK");

if(ok !=null)
{
	try{
	
	int aPort = Integer.parseInt(authPort);
	int acPort = Integer.parseInt(acctPort);
	test.radius.AddRadiusServer ars = new test.radius.AddRadiusServer();
	ars.addRadiusServer(serverName,ipAddress,aPort,acPort,secret);
	
	}catch(Exception error)
	{
	error.printStackTrace();
	}
}
%>

<html>
<head>
<script language="javascript" src=""></script>
<script language="javascript" src=""></script>
</head>

<body class="" onload="init()">
<form name="" action="" method="POST" onSubmit="return ">  
  <table width="80%" border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td width="10"><img src="" width="1" height="5"></td>
        <td height="30" align="left" valign="bottom"><span class="header1"><fmt:message key="Add Radius server"/></span></td>
      </tr>
      <tr> 
        <td width="10" height="1"><img src="./adminaddusericon.gif" width="1" height="1"></td>
        <td height="5" class="hline"><img src="./adminaddusericon.gif" width="1" height="1"></td>
      </tr>      
      <tr>
        <td>&nbsp;</td>
        <td class="text" align="center">
        <table width="500" border="0" align="left" cellpadding="5" cellspacing="0" class="botBorder">
              <tr align="left" valign="top"> 
                <td width="217" class="text"><fmt:message key="Radius Server Host Name"/></td>
                <td width="264"> <input name="serverName" type="text" class="formStyle"><span class="mandatory">&nbsp</span>
                </td>
              </tr>
		<tr align="left" valign="top">
                <td width="217" class="text"><fmt:message key="Host IP Address"/></td>
                <td width="264"> <input name="hostIpAddress" type="text" class="formStyle"><span class="mandatory">&nbsp</span>
                </td>
              </tr>
              <tr align="left" valign="top"> 
                <td class="text"><fmt:message key="Authentication Port"/></td>
                <td><input type="textfield" name="authPort" class="formStyle"><span class="mandatory">&nbsp</span></td>
              </tr>
		<tr align="left" valign="top">
                <td class="text"><fmt:message key="Accounting Port"/></td>
                <td> <input name="acctPort" type="textfield" class="formStyle" ><span class="mandatory">&nbsp</span></td>
              </tr>
		<tr align="left" valign="top">
                <td class="text"><fmt:message key="Shared Secret"/></td>
                <td> <input name="secret" type="textfield" class="formStyle" ><span class="mandatory">&nbsp</span></td>
	      </tr>
		<tr align="left" valign="top">
                <td class="text"><fmt:message key="Retry Value"/></td>
                <td> <input name="retryvalue" type="textfield" class="formStyle" ><span class="mandatory">&nbsp</span></td>
              </tr>
		<tr align="left" valign="top">
                <td class="text"><fmt:message key="Response Timeout"/></td>
                <td> <input name="responsetimeout " type="textfield" class="formStyle" ><span class="mandatory">&nbsp</span></td>
              </tr>
		<tr align="left" valign="top">
                <td height="2" colspan="2" align="right" valign="top" class="text"><span class="mandatory"></span><fmt:message key="All fields are mandatory"/></td>
              </tr>
	
		<td align="center">
          <input name="OK" type="submit" class="button" value="<fmt:message key=' OK  '/>">
          <input name="Cancel" type="reset" class="button" value="<fmt:message key='  Cancel  '/>">


          </table></td>
      </tr>
  </table>
</form>
</body>
</html>
