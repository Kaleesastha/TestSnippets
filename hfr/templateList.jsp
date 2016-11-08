<%-- $Id: templateList.jsp,v 1.13 2015/04/17 05:52:59 shajahan Exp $ --%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script src="/webclient/prov/js/provision.js" type="text/javascript"></script>
<script language="javascript" src="/webclient/common/js/select2.min.js"></script>
<script>
$(document).ready(function(){
	var viewId = "<c:out value='${viewId}'/>";
	var ids = [];
	$("#xmlTemplateListUL li").each(function(idx, li) {
		var id = this.id;
		
		if(id!==null && id!=="")
		{
			ids.push(id);
			if(id=="soam" ||id=="LagMemberProvisioning" ||id=="ModifyLagProvisioning" || id=="Delete_Lag_Config"||id=="Delete_Lagmember_Config" ||id=="ModifyLagMemberProvisioning" ||  id=="LagProvisioning" || id=="PassiveLagMemberProvisioning" || id=="RFC2544Test" || id=="LinkMonitoring" || id=="LOamRemoteLoopback" || id=="LinkOAMRemoteTest" || id=="LinkOAMConfiguration" ||  id=="LinkOAMDisable" || id=="LinkOAMMonitoring" || id=="BwpProfile" || id=="CosProfile" || id=="ConfigVlan" || id=="Y1564SourceEnd" || id=="Y1564PeerTask" )
			{
				$(this).hide();
			}
			if(viewId=="Templates")
			{
				if(id!='TelnetTaskTemplate'&&(id!=="UNIProvisioning")&&(id!=="NNIProvisioning")&&(id!=="EVCProvisioning")&&(id!=="LinkOAM")&&(id!=="LinkOAMRemoteLoopback")&&(id!=="bwProfile") && (id!=="soam"))
				{
					$(this).hide();
				}	
				
			}else if(viewId=="DeviceConfig")
			{
				if((id!=="Device_Configuration")&&(id!=="Enable_PM") &&(id!=="Interface_Admin_Config")&&(id!=="ZeroTouchProvisioning"))
				{
					$(this).hide();
				}
			}else if(viewId=="ImageUpgrade")
			{
				if((id!=="multipleImageUpgrade" && id!=="multipleImageDowngrade"))
				{
					$(this).hide();
				}	
			}else if(viewId=="LOAM")
			{
				if((id!=="LinkOAM")&&(id!=="LinkOAMRemoteLoopback"))
				{
					$(this).hide();
				}	
			}else if(viewId=="EthernetService")
			{
				if((id!=="UNIProvisioning")&&(id!=="NNIProvisioning")&&(id!=="EVCProvisioning")&&(id!=="bwProfile"))
				{
					$(this).hide();
				}	
			}else if(viewId=="CFM")
			{
				if((id!=="Connectivity_Check_IEEE_802_1ag")&&(id!=="Loopback_Test_IEEE_802_1_ag")&&(id!=="Link_Trace_Test_IEEE_802_3ag"))
                                {
                                        $(this).hide();
                                }
			}else if(viewId=="LAG")
			{
				if( (id!=="modifylagconfig")&& (id!=="ModifyLagProvisioning")&&(id!=="lagactive")&&(id!=="lagstandby"))
				{
					$(this).hide();
				}	
			}
			else if(viewId=="Y1564")
			{
				if((id!=="SATLoopback")&&(id!=="StopSATLoopback")&&(id!=="Stop_Y1564Test")&&(id!=="Y1564Test")&&(id!=="Y1564Test_LoopbackMode"))
				{
					$(this).hide();
				}	
			}
			
		}	
	});
	console.log("ids",ids);
});


</script>
<style>
#provModal{margin-top:0 !important;}
.f-nav{  /* To fix main menu container */
    z-index: 9999;
    position: fixed;
	background-color:#fff;
	margin:0px;
	padding:0px;
	top:0;
	width:100%;
}
#listViewLayout {
    text-align: left; /* Assuming your main layout is centered */
	position:relative;
	width:100%;
	height:100%;
	margin:0px;
	padding:0px;
	}


#xmlTemplateListUL {
  list-style-type: none;
  width: 98%;
   margin-top: 20px;
   margin-left:0px;
   /* box-shadow:         -6px -6px 17px rgba(200, 200, 200, 0.49);
  -webkit-box-shadow: -6px -6px 17px rgba(200, 200, 200, 0.49);
-moz-box-shadow:    -6px -6px 17px rgba(200, 200, 200, 0.49); */
}
 

.xmlTemplateListLI {
  padding: 10px;
  position:relative;
  border-bottom:1px solid #E7E7E7; 
 -webkit-touch-callout: none;
-webkit-user-select: none;
-khtml-user-select: none;
-moz-user-select: none;
-ms-user-select: none;
user-select: none;

}
 
.xmlTemplateListLI:hover,.xmlTemplateListLIactive {
  background: #eee;
  cursor: pointer;
}

.xmlDescription,.xmlDescriptionExpand{
	height:20px;
	top:35px;
	position:absolute;
    display: inline-block;
    width:95%;
    vertical-align: top;
}

.xmlDescription{
	text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
}

.custom-menu {
    position: absolute;
    display:none;
}

.provMenuUL{
float:right;list-style:none;margin-top:-40px;display:none;
}

.xmlTemplateListLI:hover ul.provMenuUL{display:block;}

.provMenuUL li{
float:left;padding:0 15px;
}
.provMenuUL li a{color:#4F4F4F;font-weight:bold;}

#prov-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: #000;
    filter:alpha(opacity=50);
    -moz-opacity:0.5;
    -khtml-opacity: 0.5;
    opacity: 0.5;
    z-index: 10000;
}

#prov-overlay-loading{left:50%;top:50%;position: absolute;margin-left: -54px;margin-top: -30px;width: 60px;height: 60px;}
</style>


<div id="listViewLayout">
	<div id="headerFixed" style="margin:0px;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<div class="compBlock dottedBorder">
						<span class="txt18Bold"><c:out value='${displayName}'/></span>
					</div>
				</td>
				<td width="15">
					<img src="/webclient/common/images/spacer.gif" width="15"/>
				</td>
			</tr>
			
		</table>
	</div>

	<div class="listviewBlock" style="margin-top:80px;">
					
			<ul id="xmlTemplateListUL">

<li id="modifylagconfig" class="xmlTemplateListLI"  onclick="selectTemplate(this)" >
						<div style="float:left;">
						<img border="0" align="absmiddle" width="" height="" src="/webclient/common/images/<c:out value='${selectedskin}'/>/execute.png" title="webclient.config.task.executeTask">
						</div>
						<div style="margin-left: 30px;">
						<div class="lvdispName" style="margin:0 0 20px;font-size:15px;">
							LAG Configuration
							
						</div>
						<ul class="provMenuUL">
							<li>
								<a style="background-image: url('/webclient/common/images/provision-default.png');background-repeat:no-repeat;" href="javascript:doActions('modifyLag')">Provision</a>
							</li>
							
						</ul>
						<p class="xmlDescription">LAG Configuration</p>
						</div>
						<input  type="hidden" class="templateRadio " name="templateRadio" value="modifyLag" />
					</li>
					<%-- <li id='lagactive' class="xmlTemplateListLI"  onclick="selectTemplate(this)" >
						<div style="float:left;">
						<img border="0" align="absmiddle" width="" height="" src="/webclient/common/images/<c:out value='${selectedskin}'/>/execute.png" title="webclient.config.task.executeTask">
						</div>
						<div style="margin-left: 30px;">
						<div class="lvdispName" style="margin:0 0 20px;font-size:15px;">
							LAG Active-Active Configuration
							
						</div>
						<ul class="provMenuUL">
							<li>
								<a style="background-image: url('/webclient/common/images/provision-default.png');background-repeat:no-repeat;" href="javascript:doActions('active')">Provision</a>
							</li>
							
						</ul>
						<p class="xmlDescription">LAG Active-Active Configuration</p>
						</div>
						<input  type="hidden" class="templateRadio " name="templateRadio" value="active" />
					</li> --%>
<!--li class="xmlTemplateListLI"  onclick="selectTemplate(this)" >
                                                <div style="float:left;">
                                                <img border="0" align="absmiddle" width="" height="" src="/webclient/common/images/<c:out value='${selectedskin}'/>/execute.png" title="webclient.config.task.executeTask">
                                                </div>
                                                <div style="margin-left: 30px;">
                                                <div class="lvdispName" style="margin:0 0 20px;font-size:15px;">
                                                        Multiple NID image upgrade

                                                </div>
                                                <ul class="provMenuUL">
                                                        <li>
                                                                <a href="javascript:doActions('multiplenid')">Provision</a>
                                                        </li>

                                                </ul>
                                                <p class="xmlDescription">Image upgrade for multiple NID</p>
                                                </div>
                                                <input  type="hidden" class="templateRadio " name="templateRadio" value="multiplenid" />
                                        </li-->
					<%-- <li id='lagstandby' class="xmlTemplateListLI"  onclick="selectTemplate(this)" >
						<div style="float:left;">
						<img border="0" align="absmiddle" width="" height="" src="/webclient/common/images/<c:out value='${selectedskin}'/>/execute.png" title="webclient.config.task.executeTask">
						</div>
						<div style="margin-left: 30px;">
						<div class="lvdispName" style="margin:0 0 20px;font-size:15px;">
							LAG Active-Standby Configuration
							
						</div>
						<ul class="provMenuUL">
							<li>
								<a style="background-image: url('/webclient/common/images/provision-default.png');background-repeat:no-repeat;" href="javascript:doActions('standby')">Provision</a>
							</li>
							
						</ul>
						<p class="xmlDescription">LAG Active-Active Configuration</p>
						</div>
						<input  type="hidden" class="templateRadio " name="templateRadio" value="standby" />
					</li> --%>
					
<li id='bwProfile' class="xmlTemplateListLI"  onclick="selectTemplate(this)" >
						<div style="float:left;">
						<img border="0" align="absmiddle" width="" height="" src="/webclient/common/images/<c:out value='${selectedskin}'/>/execute.png" title="webclient.config.task.executeTask">
						</div>
						<div style="margin-left: 30px;">
						<div class="lvdispName" style="margin:0 0 20px;font-size:15px;">
							Bandwidth Profile
							
						</div>
						<ul class="provMenuUL">
							<li>
								<a style="background-image: url('/webclient/common/images/provision-default.png');background-repeat:no-repeat;" href="javascript:doActions('bandwidth')">Provision</a>
							</li>
							
						</ul>
						<p class="xmlDescription">BandWidth profile settings</p>
						</div>
						<input  type="hidden" class="templateRadio " name="templateRadio" value="bandwidth" />
					</li>
					<li id='soam' class="xmlTemplateListLI"  onclick="selectTemplate(this)" >
						<div style="float:left;">
						<img border="0" align="absmiddle" width="" height="" src="/webclient/common/images/<c:out value='${selectedskin}'/>/execute.png" title="webclient.config.task.executeTask">
						</div>
						<div style="margin-left: 30px;">
						<div class="lvdispName" style="margin:0 0 20px;font-size:15px;">
							SOAM Profile
							
						</div>
						<ul class="provMenuUL">
							<li>
								<a style="background-image: url('/webclient/common/images/provision-default.png');background-repeat:no-repeat;" href="javascript:doActions('soam')">Provision</a>
							</li>
							<li>
								<a id="activity" href="javascript:doActions('activity')">Activity List</a>
							</li>
							
						</ul>
						<p class="xmlDescription">SOAM profile settings</p>
						</div>
						<input  type="hidden" class="templateRadio " name="templateRadio" value="soam" />
					</li>
				<c:forEach varStatus="status" var="template" items="${templates}">
					<li id='<c:out value='${template.key}'/>' class="xmlTemplateListLI"  onclick="selectTemplate(this)" ondblclick="ajaxCall('/prov/xmlProvisioning.do?method=loadProvisioningTemplate&template=<c:out value='${template.key}'/>','POST','',openProv)">
						<div style="float:left;">
						<img border="0" align="absmiddle" width="" height="" src="/webclient/common/images/<c:out value='${selectedskin}'/>/execute.png" title="webclient.config.task.executeTask">
						</div>
						<div style="margin-left: 30px;">
						<div class="lvdispName" style="margin:0 0 20px;font-size:15px;">
							<c:choose>
							<c:when test="${template.key eq 'CVLANLoopback'}">
								Start SAT Loopback
							</c:when>
							<c:otherwise>
							<c:choose>
							<c:when test="${template.key eq 'StopCVLANLoopback'}">
                                                               Stop SAT Loopback
                                                        </c:when>
							<c:otherwise>
							<c:out value='${template.key}'/>
							</c:otherwise>
							</c:choose>
							</c:otherwise>
							</c:choose>
						</div>
						<ul class="provMenuUL">
							<li>
								<a id="provision" href="javascript:doActions('')"><fmt:message key='webclient.prov.action.provision'/></a>
							</li>
							<%-- <li>
								<a id="schedule" href="javascript:doActions('schedule')" ><fmt:message key='webclient.prov.action.schedule'/></a>
							</li> --%>
							<li>
								<a id="view" href="javascript:doActions('view')"><fmt:message key='webclient.prov.action.view'/></a>
							</li>
							<%-- <li>
								<a id="delete" href="javascript:doActions('delete')"><fmt:message key='webclient.prov.action.delete'/></a>
							</li> --%>
							<li>
								<a id="activity" href="javascript:doActions('activity')"><fmt:message key='webclient.prov.action.activity'/></a>
							</li>
						</ul>
						<p class="xmlDescription"><c:out value='${template.value}'/></p>
						</div>
						<input  type="hidden" class="templateRadio " name="templateRadio" value="<c:out value='${template.key}'/>" />
					</li>


				</c:forEach>
				<li id='multipleImageUpgrade' class="xmlTemplateListLI"  onclick="selectTemplate(this)" >
                                                <div style="float:left;">
                                                <img border="0" align="absmiddle" width="" height="" src="/webclient/common/images/<c:out value='${selectedskin}'/>/execute.png" title="webclient.config.task.executeTask">
                                                </div>
                                                <div style="margin-left: 30px;">
                                                <div class="lvdispName" style="margin:0 0 20px;font-size:15px;">
                                                        ImageUpgrade

                                                </div>
                                                <ul class="provMenuUL">
                                                        <li>
                                                                <a style="background-image: url('/webclient/common/images/provision-default.png');background-repeat:no-repeat;" href="javascript:doActions('multiplenid','upGrade')">Provision</a>
                                                        </li>
						

					</ul>
                                                <p class="xmlDescription">Upgrade the Image</p>
                                                </div>
                                                <input  type="hidden" class="templateRadio " name="templateRadio" value="multiplenid" />
                                        </li>
				<li id='multipleImageDowngrade' class="xmlTemplateListLI"  onclick="selectTemplate(this)" >
                                                <div style="float:left;">
                                                <img border="0" align="absmiddle" width="" height="" src="/webclient/common/images/<c:out value='${selectedskin}'/>/execute.png" title="webclient.config.task.executeTask">
                                                </div>
                                                <div style="margin-left: 30px;">
                                                <div class="lvdispName" style="margin:0 0 20px;font-size:15px;">
                                                        ImageDowngrade

                                                </div>
                                                <ul class="provMenuUL">
                                                        <li>
                                                                <a style="background-image: url('/webclient/common/images/provision-default.png');background-repeat:no-repeat;" href="javascript:doActions('multiplenid','downGrade')">Provision</a>
                                                        </li>
						

					</ul>
                                                <p class="xmlDescription">Downgrade the Image</p>
                                                </div>
                                                <input  type="hidden" class="templateRadio " name="templateRadio" value="multiplenid" />
                                        </li>
			</ul>
							
       </div>
       
       <div id="viewXMLModal" style="width:80%;height:80%;margin-top: -40%;margin-left: -40%;" class="modal hide fade in">
            <div class="modal-header">
                  <img src="/webclient/common/images/<c:out value='${sessionScope.selectedskin}'/>/pop-up-close.png" class="gbModalBoxClose"  data-dismiss="modal"></img>
                  <div style="padding: 10px 0px 0px;" class="compBlock">
						<span id="viewXMLModalHeader" class="txt18Bold"> 
								
						</span>
					</div>
                  
                  <!-- <div id="viewXMLModalHeader" style="min-height:15px;"></div> -->
            </div>
            <div class="modal-body" style="max-height:85%;height:85%;">
                <pre id="modalBody" style="max-height:93%;overflow:auto;font-family:inherit;"></pre>  
            </div>
           
		</div>
		
		<div id="provModal" class="modal hide fade in" style="width:80%;height:100%;margin-left: -40%;border-radius: 0px;padding:10px;top:0 !important;margin-top:0px !important;">
		<div class="modal-header"><div id="viewTemplateHeader" class="txt18Bold"> </div> </div>
            
            <div id="provModalBody" class="modal-body" style="max-height:96%;height:95%;">
                
            </div>
            <!-- <div id="provModalFooter" class="modal-footer" style="text-align:center;">
                  
            </div> -->
		</div>
		<div class="custom-menu">
			<ul class="dropdown-menu" style="display:block;">
				<li>
					<a href="javascript:doActions('')" class="menuText"><fmt:message key='webclient.prov.action.provision'/></a>
				</li>
				<li>
					<a href="javascript:doActions('schedule')" class="menuText"><fmt:message key='webclient.prov.action.schedule'/></a>
				</li>
				<li>
					<a href="javascript:doActions('view')" class="menuText"><fmt:message key='webclient.prov.action.view'/></a>
				</li>
				<li>
					<a href="javascript:doActions('delete')" class="menuText"><fmt:message key='webclient.prov.action.delete'/></a>
				</li>
				<li>
					<a href="javascript:doActions('activity')" class="menuText"><fmt:message key='webclient.prov.action.activity'/></a>
				</li>
			</ul>
		</div>
</div>
