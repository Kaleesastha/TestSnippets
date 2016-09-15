<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@page import="test.*"%>

<%
String serverName = request.getParameter("serverName");
String status = request.getParameter("status");
System.out.println("########### status" + status);
String hostIpAddress =  request.getParameter("hostIpAddress");
System.out.println("########### hostIpAddress" + hostIpAddress);
String authPort = request.getParameter("authPort");
String secret = request.getParameter("secret");
String responseTime = request.getParameter("responseTime");
String retryValue = request.getParameter("retryValue");
String format = request.getParameter("format");
String ok = request.getParameter("OK");
if(ok !=null)
{
	try{
	int aPort = Integer.parseInt(authPort);
	int rTime = Integer.parseInt(responseTime);
	int rValue = Integer.parseInt(retryValue);
	UpdateRadiusInfo upr= new UpdateRadiusInfo();
	upr.init(serverName);	
	upr.updateRadiusInfoForProximAP(serverName,status,hostIpAddress,aPort,secret,rTime,rValue,format);
	out.println("Updated the radius server information");
	
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

<body class="adminaddusericon.gif" onload="init()">
<form name="RadiusServerInfo" action="" method="POST" onSubmit="return Validate()">  
  <table width="80%" border="1" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td width="10"><img src="/webclient/common/images/trans.gif" width="1" height="5"></td>
        <td height="30" align="left" valign="bottom"><span class="header1"><fmt:message key="AP Authentication Information"/></span></td>
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
                <td width="217" class="text"><fmt:message key="Access Point Host Name"/></td>
                <td width="264"> <input name="serverName" type="text" class="formStyle"><span class="mandatory">&nbsp</span>
                </td>
		<tr align="left" valign="top">
                <td width="217" class="text"><fmt:message key="Server Status (enable/disable)"/></td>
                <td width="264"> <input name="status" type="text" class="formStyle"><span class="mandatory">&nbsp</span>
                </td>

              </tr>
		<tr align="left" valign="top">
                <td width="217" class="text"><fmt:message key="Radius Server HostName/IP"/></td>
                <td width="264"> <input name="hostIpAddress" type="text" class="formStyle"><span class="mandatory">&nbsp</span>
                </td>
              </tr>
              <tr align="left" valign="top"> 
                <td class="text"><fmt:message key="Authentication Port"/></td>
                <td><input type="textfield" name="authPort" class="formStyle"><span class="mandatory">&nbsp</span></td>
              </tr>
		<tr align="left" valign="top">
                <td class="text"><fmt:message key="Shared Secret"/></td>
                <td> <input name="secret" type="textfield" class="formStyle" ><span class="mandatory">&nbsp</span></td>
              </tr>
		</tr>
                <tr align="left" valign="top">
                <td class="text"><fmt:message key="ResponseTime"/></td>
                <td> <input name="responseTime" type="textfield" class="formStyle" ><span class="mandatory">&nbsp</span></td>
              </tr>
		</tr>
                <tr align="left" valign="top">
                <td class="text"><fmt:message key="Retry Value"/></td>
                <td> <input name="retryValue" type="textfield" class="formStyle" ><span class="mandatory">&nbsp</span></td>
              </tr>
		</tr>
		<tr align="left" valign="top">
                <td class="text"><fmt:message key="Server format"/></td>
                <td> <input name="format" type="textfield" class="formStyle" ><span class="mandatory">&nbsp</span></td>
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
