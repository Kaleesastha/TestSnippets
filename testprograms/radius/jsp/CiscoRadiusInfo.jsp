<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@page import="test.*"%>

<%
String aphost = "ciscoap";
String serverName = request.getParameter("serverName");
String status = request.getParameter("serverType");
String authPort = request.getParameter("authPort");
String retryValue = request.getParameter("retryValue");
String secret = request.getParameter("secret");
String responseTime = request.getParameter("responseTime");

int eapauth;
int macauth;
int userauth;
int mipauth;

String checkType = request.getParameter("checkbox1");
String checkType1 = request.getParameter("checkbox2");
String checkType2 = request.getParameter("checkbox3");
String checkType3 = request.getParameter("checkbox4");


String authType = request.getParameter("selected");

String ok = request.getParameter("OK");



if(ok !=null)
{

	if(checkType!=null)
	{
   	eapauth = 1;
	}
	else { eapauth =2;}

	if(checkType1!=null)
	{
	macauth = 1;
	}
	else{ macauth =2;}

	if(checkType2!=null)
	{
	userauth = 1;
	}
	else{ userauth =2;}

	if(checkType3!=null)
	{
	mipauth = 1;
	}
	else{mipauth = 2;}




	try{
	int aPort = Integer.parseInt(authPort);
	int rTime = Integer.parseInt(responseTime);
	int rValue = Integer.parseInt(retryValue);
	test.radius.CiscoRadiusInfo cri = new test.radius.CiscoRadiusInfo();	
	//cri.init("ciscoap");	
	//cri.getAuthType(authType);
        cri.updateRadiusInfoForCiscoAP(authType,serverName,aPort,rTime,rValue,secret,eapauth,macauth,userauth,mipauth);	

	out.println("Updated the radius server information        ");
        out.println("          authtype  " + authType);	
	
	
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
        <td height="30" align="center" valign="bottom"><span class="header1"><fmt:message key="Configure Radius Server Information -- Cisco"/></span></td>
      </tr>
      <tr> 
        <td width="10" height="1"><img src="./adminaddusericon.gif" width="1" height="1"></td>
        <td height="5" class="hline"><img src="./adminaddusericon.gif" width="1" height="1"></td>
      </tr>      
      <tr>
        <td>&nbsp;</td>
        <td class="text" align="center">
        <table width="1200" border="0" align="left" cellpadding="15" cellspacing="0" class="botBorder">
		<td><input type="radio" value="RadiusServerOne" name="selected" >
                <span class="text"><fmt:message key="1: Radius Server "/></span></td>
		<td><input type="radio" value="RadiusServerTwo" name="selected" >
                <span class="text"><fmt:message key="2: Radius Server Two"/></span></td>
		<td><input type="radio" value="RadiusServerThree" name="selected" >
                <span class="text"><fmt:message key="3: RadiusServer"/></span></td>
		<td><input type="radio" value="RadiusServerFour" name="selected" >
                <span class="text"><fmt:message key="4: Radius Server"/></span></td>

              <tr align="left" valign="top"> 
                <td width="217" class="text"><fmt:message key="Radius Server Host/IP"/></td>
                <td width="264"> <input name="serverName" type="text" class="formStyle"><span class="mandatory">&nbsp</span>
                </td>
		<tr align="left" valign="top">
                <td width="217" class="text"><fmt:message key="Server Type (Radius/Tacacs)"/></td>
                <td width="264"> <input name="serverType" type="text" class="formStyle"><span class="mandatory">&nbsp</span>
                </td>

              </tr>
		<tr align="left" valign="top">
                <td width="217" class="text"><fmt:message key="Radius Auth Port"/></td>
                <td width="264"> <input name="authPort" type="text" class="formStyle"><span class="mandatory">&nbsp</span>
                </td>
              </tr>
              <tr align="left" valign="top"> 
                <td class="text"><fmt:message key="Retry Value"/></td>
                <td><input type="textfield" name="retryValue" class="formStyle"><span class="mandatory">&nbsp</span></td>
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
		<td><input type="checkbox" value="1" name="checkbox1"  >
                <span class="text"><fmt:message key="EAP Authentication"/></span></td>
		<td><input type="checkbox" value="1" name="checkbox2">
                <span class="text"><fmt:message key="Mac Authentication"/></span></td>
		<td><input type="checkbox" value="1" name="checkbox3" >
                <span class="text"><fmt:message key="User Authentication"/></span></td>
		<td><input type="checkbox" value="1" name="checkbox4">
                <span class="text"><fmt:message key="Mip Authentication"/></span></td>


		<tr align="left" valign="top">
                <td height="100" colspan="2" align="right" valign="top" class="text"><span class="mandatory"></span><fmt:message key=" "/></td>
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
