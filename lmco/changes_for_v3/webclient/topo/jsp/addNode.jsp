<%-- $Id: addNode.jsp,v 1.5.4.5 2013/08/20 05:13:26 nishanthini.k Exp $ --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<TITLE><fmt:message key='webclient.topo.addnode.title'/></TITLE>
<script type="text/javascript">

function toggleV3Properties(securityLevel){

  if(securityLevel=='AuthNoPriv'){

      document.AddNodeForm.authProtocol.disabled=false;
      document.AddNodeForm.authProtocol.className="formStyleSmall";
      document.AddNodeForm.authPassword.disabled=false;
      document.AddNodeForm.authPassword.className="formStyleSmall";

  }else if(securityLevel=='AuthPriv'){
      document.AddNodeForm.authProtocol.disabled=false;
      document.AddNodeForm.authProtocol.className="formStyleSmall";
      document.AddNodeForm.authPassword.disabled=false;
      document.AddNodeForm.authPassword.className="formStyleSmall";
      document.AddNodeForm.privProtocol.disabled=false;
      document.AddNodeForm.privProtocol.className="formStyleSmall";
      document.AddNodeForm.privPassword.disabled=false;
      document.AddNodeForm.privPassword.className="formStyleSmall";

  }else{
      document.AddNodeForm.authProtocol.disabled=true;
      document.AddNodeForm.authProtocol.className="formStyleSmallDisabled";
      document.AddNodeForm.authPassword.disabled=true;
      document.AddNodeForm.authPassword.className="formStyleSmallDisabled";
      document.AddNodeForm.privProtocol.disabled=true;
      document.AddNodeForm.privProtocol.className="formStyleSmallDisabled";
      document.AddNodeForm.privPassword.disabled=true;
      document.AddNodeForm.privPassword.className="formStyleSmallDisabled";
  }

}


function ToggleStates()
{
  if (document.AddNodeForm.version.checked)
      {
    	document.AddNodeForm.userName.disabled= false;
    	document.AddNodeForm.userName.className="formStyleSmall";
    	document.AddNodeForm.contextName.disabled=false;
    	document.AddNodeForm.contextName.className="formStyleSmall";

      //lmco-v3 changes starts
      document.AddNodeForm.securityLevel.disabled=false;
      document.AddNodeForm.securityLevel.className="formStyleSmall";

      toggleV3Properties(document.AddNodeForm.securityLevel.value);
      //lmco-v3 changes ends
      }
     else
      {
        document.AddNodeForm.userName.disabled=true;
    	document.AddNodeForm.userName.className="formStyleSmallDisabled";
    	document.AddNodeForm.contextName.disabled=true;
    	document.AddNodeForm.contextName.className="formStyleSmallDisabled";

//lmco-v3 changes starts
      document.AddNodeForm.securityLevel.disabled=true;
      document.AddNodeForm.securityLevel.className="formStyleSmallDisabled";
      document.AddNodeForm.authProtocol.disabled=true;
      document.AddNodeForm.authProtocol.className="formStyleSmallDisabled";
      document.AddNodeForm.authPassword.disabled=true;
      document.AddNodeForm.authPassword.className="formStyleSmallDisabled";
      document.AddNodeForm.privProtocol.disabled=true;
      document.AddNodeForm.privProtocol.className="formStyleSmallDisabled";
      document.AddNodeForm.privPassword.disabled=true;
      document.AddNodeForm.privPassword.className="formStyleSmallDisabled";
//lmco-v3 changes ends

      }
}
function disableFields()
{
	document.AddNodeForm.userName.disabled=true;
	document.AddNodeForm.userName.className="formStyleSmallDisabled";
	document.AddNodeForm.contextName.disabled=true;
	document.AddNodeForm.contextName.className="formStyleSmallDisabled";

//lmco-v3 changes starts
  document.AddNodeForm.securityLevel.disabled=true;
  document.AddNodeForm.securityLevel.className="formStyleSmallDisabled";
  document.AddNodeForm.authProtocol.disabled=true;
  document.AddNodeForm.authProtocol.className="formStyleSmallDisabled";
  document.AddNodeForm.authPassword.disabled=true;
  document.AddNodeForm.authPassword.className="formStyleSmallDisabled";
  document.AddNodeForm.privProtocol.disabled=true;
  document.AddNodeForm.privProtocol.className="formStyleSmallDisabled";
  document.AddNodeForm.privPassword.disabled=true;
  document.AddNodeForm.privPassword.className="formStyleSmallDisabled";

  //lmco-v3 changes ends
}
function ToggleOthers()
{
   if(document.AddNodeForm.discovery.checked)
   {
      document.AddNodeForm.configFlag.disabled=true;
      document.AddNodeForm.discoverParentNet.disabled=true;
      document.AddNodeForm.reachFlag.disabled=true;
      document.AddNodeForm.writeToSeedFile.disabled=true;
   }

   else
   {
      document.AddNodeForm.configFlag.disabled=false;
      document.AddNodeForm.discoverParentNet.disabled=false;
      document.AddNodeForm.reachFlag.disabled=false;
      document.AddNodeForm.writeToSeedFile.disabled=false;
   } 
}
function validateAddNode()
{
   var node = document.AddNodeForm.ipAddress.value.trim();
   if(node=="")
   {
      alert('<fmt:message key="webclient.topo.addnodepage.noinput.error"/>');
      document.AddNodeForm.ipAddress.focus();
      return false;
   }
   if(isSpecialSymbol(node) == true)
   {
      alert('<fmt:message key="webclient.admin.ipaddr.specialChar.error"/>');
      document.AddNodeForm.ipAddress.focus();
      return false;
   }
   var numbers = /^[0-9]+$/;
   var port = document.AddNodeForm.snmpport.value.trim();	
   var comm = document.AddNodeForm.community.value.trim();
   if(port!=""|| comm!="")
   {     
     if(port=="")
     { 
	alert('<fmt:message key="webclient.topo.addnodepage.nosnmpport.error"/>');
	document.AddNodeForm.snmpport.focus();
     	return false;
     }
     else if(!port.match(numbers))
     {
	alert('<fmt:message key="webclient.topo.addnodepage.noaplhabets.error"/>');
	document.AddNodeForm.snmpport.value="";
        document.AddNodeForm.snmpport.focus();
        return false;
     }
     else if(comm=="")
     {
	alert('<fmt:message key="webclient.topo.addnodepage.nocommunity.error"/>');
	document.AddNodeForm.community.focus();
        return false;
     }
   }
  var snmpVersion=document.AddNodeForm.version.checked;
  if(snmpVersion==true && document.AddNodeForm.userName.value.trim()=='')
  {
	alert('<fmt:message key="webclient.topo.addnodepage.nousername.error"/>');
	document.AddNodeForm.userName.value="";
	document.AddNodeForm.userName.focus();
	return false;
  }
   return true;
}

function checkDiscoverParentNet()
{
	document.AddNodeForm.discoverParentNet.checked=true;
	document.AddNodeForm.ipAddress.focus();
}
</script>
<body onLoad="javascript:checkDiscoverParentNet()">
      <div class="compBlock dottedBorder">
		<span class="txt18Bold"><fmt:message key='webclient.topo.addnode.header'/></span>
		<span class="backBtn" onclick="javascript:history.back()" style="float:right;">
			<fmt:message key='webclient.admin.mailserver.back'/>
	    </span>	
        </div>

<html:form action="/AddNodeAction" onsubmit="return validateAddNode();">

<table style="margin-bottom:10px;" width="80%" border="0" cellpadding="0" cellspacing="0">
 
  <tr>
    <td valign="top">


<table cellSpacing=0 cellPadding=0 width=100% align="left" border=0 class="modetails"> 

 <tr>
   <td>&nbsp;</td>
   <td class="text" height="40" nowrap="nowrap"><fmt:message key='webclient.topo.addnode.helpmessage'/>
   </td>
 </tr>
 <tr>
    <td>&nbsp;</td>
    <td align=left height="25">
	<div class="greyHeader">
	<fmt:message key='webclient.topo.addnode.general'/></div>
	</td>
        
</tr>

<tr>
   <td>&nbsp;</td>
   <td align=left valign="top">
	<TABLE cellSpacing=0 cellPadding=4 width=50% border=0 style="margin-top:10px;margin-bottom:10px;">
		<TR>
		<TD height="25" align=left class="proplabel">
			<fmt:message key='webclient.topo.addnode.ipaddress'/>
		</TD>
		<TD>
			<html:text styleClass= "formStyleSmall" name="AddNodeForm" property="ipAddress" /> 
        </TD>
        </TR>
                                                
                <TR id=high> 
                <TD height="25"  align=left class="proplabel">
                 	<fmt:message key='webclient.topo.addnode.netmask'/>
                </TD>
                        
                <TD> 
                     <html:select  styleClass= "dropDownStyle" name="AddNodeForm" property="netmask"> 
				    <html:option value='255.192.0.0'>255.192.0.0</html:option> 
				    <html:option value='255.224.0.0'>255.224.0.0</html:option> 
				    <html:option value='255.240.0.0'>255.240.0.0</html:option> 
				    <html:option value='255.248.0.0'>255.248.0.0</html:option> 
				    <html:option value='255.252.0.0'>255.252.0.0</html:option> 
				    <html:option value='255.254.0.0'>255.254.0.0</html:option> 
				    <html:option value='255.255.0.0'>255.255.0.0</html:option> 
				    <html:option value='255.255.128.0'>255.255.128.0</html:option> 
				    <html:option value='255.255.192.0'>255.255.192.0</html:option> 
				    <html:option value='255.255.224.0'>255.255.224.0</html:option> 
				    <html:option value='255.255.240.0'>255.255.240.0</html:option> 
				    <html:option value='255.255.248.0'>255.255.248.0</html:option> 
				    <html:option value='255.255.252.0'>255.255.252.0</html:option> 
				    <html:option value='255.255.254.0'>255.255.254.0</html:option> 
				    <html:option value='255.255.255.0'>255.255.255.0</html:option> 
				    <html:option value='255.255.255.128'>255.255.255.128</html:option> 
				    <html:option value='255.255.255.192'>255.255.255.192</html:option> 
				    <html:option value='255.255.255.224'>255.255.255.224</html:option> 
				    <html:option value='255.255.255.240'>255.255.255.240</html:option> 
				    <html:option value='255.255.255.248'>255.255.255.248</html:option> 
				    <html:option value='255.255.255.252'>255.255.255.252</html:option> 
				    </html:select>
		</TD>
                </TR>
              
	</TABLE>
</td>
</tr>

<tr>
<td>&nbsp;</td>

<td align=left height="25" >
		<div class="greyHeader">
<fmt:message key='webclient.topo.addnode.snmp'/></div>
</td>
</tr>

<tr>
<td>&nbsp;</td>

<td vAlign=top align=left> 

	<TABLE cellSpacing=0 cellpadding=4 width=43% border=0 style="margin-top:10px;margin-bottom:10px;">

	<TR id=high> 
	<TD height="25"  align=left class="proplabel"><fmt:message key='webclient.topo.addnode.snmpport'/>
	</TD>
	
	<TD>
	<html:text size="30" styleClass= "formStyleSmall" name="AddNodeForm" property="snmpport"/> 
        </TD>
        </TR>
	<TR>
	
	<TR id=high> 
	<TD height="25"  align=left class="proplabel">
		<fmt:message key='webclient.topo.addnode.community'/>
	</TD>
	<TD>
		<html:text size="30" styleClass= "formStyleSmall" name="AddNodeForm" property="community"/> 
	</TD>
	</TR>
	<TR> 
	<TD height="25" class="proplabel"><html:checkbox property="version" onclick="ToggleStates()" styleClass="chkStyle" value="v3"/>
	&nbsp;&nbsp;&nbsp;<fmt:message key='webclient.topo.addnode.snmpv3'/>
	</TD>
	</tr>
	<tr>
	<TD height=25 class="proplabel"><fmt:message key='webclient.topo.addnode.username'/>	</TD>
    <TD height=30> 
    <c:choose> <c:when test='${AddNodeForm.map.version == "v3"}'> 
         <html:text styleClass= "formStyleSmall" disabled ="true" name="AddNodeForm" property="userName"/></TD></c:when><c:otherwise>
         <html:text styleClass= "formStyleSmallDisabled" disabled ="true" name="AddNodeForm" property="userName"/></TD> </c:otherwise>
    </c:choose>
    </tr>
    <tr>
    <TD height=25 class="proplabel"><fmt:message key='webclient.topo.addnode.contextname'/></TD>
    <TD height=30> 
    <c:choose> <c:when test='${AddNodeForm.map.version == "v3"}'> 
          <html:text styleClass= "formStyleSmall" disabled ="true" name="AddNodeForm" property="contextName"/></TD></c:when><c:otherwise>
          <html:text  styleClass= "formStyleSmallDisabled" disabled ="true" name="AddNodeForm" property="contextName"/></TD> </c:otherwise>
    </c:choose></TR>

    <!-- lmco-v3 changes starts -->
    <tr>
    <TD height=25 class="proplabel"><fmt:message key='securityLevel'/></TD>
    <TD height=30> 
      <c:choose> 
        <c:when test='${AddNodeForm.map.version == "v3"}'> 

            <html:select  styleClass= "dropDownStyle formStyleSmall" name="AddNodeForm" property="securityLevel" onchange="toggleV3Properties(this.value)"> 
              <html:option value='AuthNoPriv'>AuthNoPriv</html:option> 
              <html:option value='NoAuthNoPriv'>NoAuthNoPriv</html:option> 
              <html:option value='AuthPriv'>AuthPriv</html:option> 
            </html:select>
            
        </c:when>
        <c:otherwise>
              <html:select  styleClass= "dropDownStyle formStyleSmallDisabled" disabled ="true"  name="AddNodeForm" property="securityLevel" onchange="toggleV3Properties(this.value)"> 
                <html:option value='AuthNoPriv'>AuthNoPriv</html:option> 
                <html:option value='NoAuthNoPriv'>NoAuthNoPriv</html:option> 
                <html:option value='AuthPriv'>AuthPriv</html:option> 
            </html:select>
        </c:otherwise>
      </c:choose>
    </TD>
    </tr>

    <tr>
    <TD height=25 class="proplabel"><fmt:message key='authProtocol'/></TD>
    <TD height=30> 
      <c:choose> 
        <c:when test='${AddNodeForm.map.version == "v3"}'> 

            <html:select  styleClass= "dropDownStyle formStyleSmall" name="AddNodeForm" property="authProtocol"> 
              <html:option value='MD5'>MD5</html:option> 
              <html:option value='SHA'>SHA</html:option> 
            </html:select>
            
        </c:when>
        <c:otherwise>
              <html:select  styleClass= "dropDownStyle formStyleSmallDisabled" disabled ="true"  name="AddNodeForm" property="authProtocol"> 
                <html:option value='MD5'>MD5</html:option> 
                <html:option value='SHA'>SHA</html:option> 
            </html:select>
        </c:otherwise>
      </c:choose>
    </TD>
    </tr>

    <tr>
    <TD height=25 class="proplabel"><fmt:message key='authPassword'/></TD>
    <TD height=30> 
      <c:choose> 
        <c:when test='${AddNodeForm.map.version == "v3"}'> 

           <html:password styleClass= "formStyleSmall" disabled ="true" name="AddNodeForm" property="authPassword"/>
            
        </c:when>
        <c:otherwise>
              <html:password styleClass= "formStyleSmallDisabled" disabled ="true" name="AddNodeForm" property="authPassword"/>
        </c:otherwise>
      </c:choose>
    </TD>
    </tr>

    <tr>
    <TD height=25 class="proplabel"><fmt:message key='privProtocol'/></TD>
    <TD height=30> 
      <c:choose> 
        <c:when test='${AddNodeForm.map.version == "v3"}'> 

            <html:select  styleClass= "dropDownStyle formStyleSmall" name="AddNodeForm" property="privProtocol"> 
              <html:option value='CBC-DES'>CBC-DES</html:option> 
              <html:option value='CFB-AES-128'>CFB-AES-128</html:option> 
            </html:select>
            
        </c:when>
        <c:otherwise>
              <html:select  styleClass= "dropDownStyle formStyleSmallDisabled" disabled ="true"  name="AddNodeForm" property="privProtocol"> 
                <html:option value='CBC-DES'>CBC-DES</html:option> 
                <html:option value='CFB-AES-128'>CFB-AES-128</html:option> 
            </html:select>
        </c:otherwise>
      </c:choose>
    </TD>
    </tr>

    <tr>
    <TD height=25 class="proplabel"><fmt:message key='privPassword'/></TD>
    <TD height=30> 
      <c:choose> 
        <c:when test='${AddNodeForm.map.version == "v3"}'> 

           <html:password styleClass= "formStyleSmall" disabled ="true" name="AddNodeForm" property="privPassword"/>
            
        </c:when>
        <c:otherwise>
              <html:password styleClass= "formStyleSmallDisabled" disabled ="true" name="AddNodeForm" property="privPassword"/>
        </c:otherwise>
      </c:choose>
    </TD>
    </tr>

    <!-- lmco-v3 changes ends -->


    </TABLE></TD></TR>

<TR id=high> 
   <td>&nbsp;</td>
                  <TD height="25" align=left>
		<div class="greyHeader">
                  <fmt:message key='webclient.topo.addnode.discoveryconfig'/></div></TD>
                </TR>
<TR id=high> 
   <td>&nbsp;</td>

                  
                <TD vAlign=top align=left>
                <TABLE width="100%" border=0  cellPadding=4 cellSpacing=0 style="margin-top:10px;margin-bottom:10px;">
                <TR>
                <TD width=20 height=25 class="proplabel"><html:checkbox property="discovery" onclick="ToggleOthers()" styleClass="chkStyle"/>
                 &nbsp;&nbsp;&nbsp;
                <fmt:message key='webclient.topo.addnode.discovery'/></TD>
                </TR>
  
                <TR>
                <TD width=20 height=25 class="proplabel"><html:checkbox property="reachFlag" styleClass="chkStyle"/>
                &nbsp;&nbsp;&nbsp;
                <fmt:message key='webclient.topo.addnode.reachflag'/></TD>
                </TR>
                
                <TR vAlign=center align=left> 
                <TD height=25 class="proplabel"><html:checkbox property="discoverParentNet" styleClass="chkStyle"/>
                &nbsp;&nbsp;&nbsp;
                <fmt:message key='webclient.topo.addnode.discflag'/></TD>
                </TR>
                
                <TR vAlign=center align=left> 
				<TD height=25 class="proplabel"><html:checkbox property="configFlag" styleClass="chkStyle"/>
				&nbsp;&nbsp;&nbsp;
				<fmt:message key='webclient.topo.addnode.configflag'/></TD>
				</TR>
                <TR vAlign=center align=left> 
                <TD height=25 class="proplabel"><html:checkbox property="writeToSeedFile" styleClass="chkStyle"/>
                &nbsp;&nbsp;&nbsp;
                <fmt:message key='webclient.topo.addnode.writetoseedfile'/></TD>
                </TR>
                <TR vAlign=center align=left> 
                <TD height=25 class="proplabel"><html:checkbox property="asynFlag" styleClass="chkStyle"/>
                &nbsp;&nbsp;&nbsp;
                <fmt:message key='webclient.topo.addnode.background'/></TD>
                </TR>
                    </TABLE>
                    </TD>
                </TR>
				<TR id=high>
   				<td>&nbsp;</td>
                  <TD height=50>                  	
                  	<TABLE cellSpacing=0 cellPadding=4  border=0 align="left">
                        <TR> 
                        <TD valign="middle" style="padding-left:134px;"> <INPUT class="btn btn-small" type=submit value="<fmt:message key='webclient.topo.addnode.addnodebutton'/>" name=submit>
                           <INPUT class="btn btn-small" type=reset value="<fmt:message key='webclient.topo.addnode.clearbutton'/>" name=reset onClick="disableFields()">
						   <input name="cancel" type="button" class="btn btn-small" value="<fmt:message key='webclient.fault.event.customview.button.cancel'/>" onclick="javascript:history.back(-1)"> </TD>
                        </TR>
                    </TABLE></TD>
                </TR>
              </TABLE>
              
                </td>
  </tr>
</table>
              <!--/TD>
              <TD align="left" valign="top"-->

                <table width="151" height="293" border="0" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td width="1" height="36" align="left" valign="top"><img src="/webclient/topo/images/spacer.gif" width="1" height="36"></td>
                    <td width="131" align="left" valign="top">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="1" height="30" align="left" valign="top" class="messtext"><img src="/webclient/topo/images/spacer.gif" width="1" height="30"></td>
                    <td align="left" valign="top" class="messtext"><html:errors property="ipAddress"/></td>
                  </tr>
                  <tr> 
                    <td width="1" height="21" align="left" valign="top" class="messtext"><img src="/webclient/topo/images/spacer.gif" width="1" height="20"></td>
                    <td align="left" valign="top" class="messtext">&nbsp;</td>
                  </tr>
                  <tr> 
                    <td width="1" height="65" align="left" valign="top" class="messtext"><img src="/webclient/topo/images/spacer.gif" width="1" height="65"></td>
                    <td align="left" valign="top" class="messtext"></td>
                  </tr>
                  <tr> 
                    <td width="1" height="28" align="left" valign="top" class="messtext"><img src="/webclient/topo/images/spacer.gif" width="1" height="20"></td>
                    <td align="left" valign="top" class="messtext"><html:errors property="snmpport"/></td>
                  </tr>
                  <tr> 
                    <td width="1" height="33" align="left" valign="top" class="messtext"><img src="/webclient/topo/images/spacer.gif" width="1" height="20"></td>
                    <td align="left" valign="top" class="messtext"><html:errors property="community"/></td>
                  </tr>
                  <tr> 
                    <td colspan="2" align="left" valign="top" class="messtext"><img src="/webclient/topo/images/spacer.gif" width="1" height="28"></td>
                  </tr>
                  <tr> 
                    <td width="1" height="20" align="left" valign="top" class="messtext"><img src="/webclient/topo/images/spacer.gif" width="1" height="20"></td>
                    <td align="left" valign="top" nowrap class="messtext"><html:errors property="userName"/></td>
                  </tr>
                  <tr> 
                    <td colspan="2" align="left" valign="top" class="messtext"><img src="/webclient/topo/images/spacer.gif" width="1" height="10"></td>
                  </tr>
                  <tr> 
                    <td width="1" height="30" align="left" valign="top" class="messtext"><img src="/webclient/topo/images/spacer.gif" width="1" height="20"></td>
                    <td align="left" valign="top" class="messtext"><html:errors property="contextName"/></td>
                  </tr>
</table>


<BR>
</html:form> <html:javascript formName="AddNodeForm"/> 

</body>
