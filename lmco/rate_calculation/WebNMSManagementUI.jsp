<%--$Id: WebNMSManagementUI.jsp,v 1.28.4.10 2013/08/29 11:54:52 nishanthini.k Exp $--%>
<%@ taglib uri = "http://www.adventnet.com/webclient/component-tags" prefix="component" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="html-el" uri="http://jakarta.apache.org/struts/tags-html-el" %>
<%@include file="/webclient/common/jspf/commonIncludes.jspf" %>	
<%@include file="/webclient/common/jspf/ModalPopup.jspf" %>	 
<%@include file="/webclient/common/jspf/genChart.jspf" %>	 

<html>
<head>
<title>Task Manager</title>
<style>


<SCRIPT LANGUAGE="JavaScript1.2" SRC="/webclient/common/js/LoadPageScript.js"></SCRIPT>
<SCRIPT LANGUAGE="javascript" SRC="/webclient/admin/js/jvmsorttable.js"></SCRIPT>
<script language="javascript" SRC="/webclient/common/js/navigation.js" type="text/javascript"></script> 

body {
	margin:0px;
	padding:0px;
}
</style>
	<script>
	var cpuUrl=null;
	var memUrl=null;
	var threadUrl=null;
function getGraph(graphType)
{
	
	var cpuSrc=document.getElementById("cpuframe");
	if(cpuUrl==null)
	{
		cpuUrl=cpuSrc.src;
	}	
	cpuSrc.src=cpuUrl+"&graphType="+graphType;

	var memSrc=document.getElementById("memframe");
	if(memUrl==null)
	{
		memUrl=memSrc.src;
	}
	memSrc.src=memUrl+"&graphType="+graphType;

	var threadSrc=document.getElementById("threadframe");
	if(threadUrl==null)
	{
		threadUrl=threadSrc.src;
	}	
	threadSrc.src=threadUrl+"&graphType="+graphType;

}
function tabSelection(tab)
{
	if(tab == "BE")
	{
		hidediv('tab1','tab2','tab3');
	}
	else if(tab == "FE")
	{
		hidediv('tab2','tab1','tab3');
	}
	else if(tab == "CLIENT")
	{
		hidediv('tab3','tab1','tab2');
	}	
}
var selectedTab="BE";
function hidediv(tab1,tab2,tab3)
{
		jQuery("#moheader li").each(function()
         {
               var id = jQuery(this).attr("id");
               if(tab1=='tab1' && id== 'beDetails')
               {
            	   jQuery(this).addClass("active");
            	   jQuery(this).find(".tabpointer").css("display","block");
               }
               else if(tab1=='tab2' && id== 'feDetails')
               {
            	   jQuery(this).addClass("active");
            	   jQuery(this).find(".tabpointer").css("display","block");
               }
               else if(tab1=='tab3' && id== 'clientDetails')
               {
            	   jQuery(this).addClass("active");
            	   jQuery(this).find(".tabpointer").css("display","block");
               }
               else{
            	   jQuery(this).removeClass("active");
            	   jQuery(this).find(".tabpointer").css("display","none");
                   }
               
         });
		document.getElementById('linegraph').className='lineGraph';
		document.getElementById('linetext').className='norTxt';	
		document.getElementById('areagraph').className='areaGraph';
		document.getElementById('areatext').className='norTxt';
		document.getElementById('periodtext').className='text';
		document.getElementById('graphPeriod').className='norTxt';
	if(tab1 == "tab1")
	{
	    selectedTab = "BE";
	}
	if(tab1 == "tab2")
	{
		selectedTab = "FE";
		<c:if test='${empty FEJVMs}'>		
		document.getElementById('linegraph').className='hide';
		document.getElementById('linetext').className='hide';	
		document.getElementById('areagraph').className='hide';
		document.getElementById('areatext').className='hide';
		document.getElementById('periodtext').className='hide';
		document.getElementById('graphPeriod').className='hide';
		</c:if>
	}
	if(tab1 == "tab3")
	{
		selectedTab = "CLIENT";
		<%-- To hide the graph for client.--%>
		document.getElementById('linegraph').className='hide';
		document.getElementById('linetext').className='hide';	
		document.getElementById('areagraph').className='hide';
		document.getElementById('areatext').className='hide';
		document.getElementById('periodtext').className='hide';
		document.getElementById('graphPeriod').className='hide';
	}
    document.getElementById(tab1).style.display = "";
    document.getElementById(tab2).style.display = "none";
    document.getElementById(tab3).style.display = "none";
    
}
function loadDetails(id)
{       
        var tempClassName = document.getElementById(id).className;
        if(tempClassName == 'hide')
        {
                document.getElementById(id).className='';
        }
        else
        {
                document.getElementById(id).className='hide';
        }
}
  
function unLoadDetails(id)
{       
        document.getElementById(id).className='hide';
}

function changeGraphPeriod(period)
{
	var graphPeriod = '';
	if(period == 'Last 1 day')
	{
		graphPeriod = '1';
	}
	if(period == 'Last 7 days')
	{
		graphPeriod = '1';
	}
	if(period == 'Last 30 days')
	{
		graphPeriod = '30';
	}
	alert(graphPeriod);
	changePreference('','',graphPeriod,'');
}

function changePreference(BESession,FESession,period,graphType)
{
        if(period != '')
        {
                period = document.getElementById('graphPeriod').value;
        }
        url = "/admin/SavePreferences.do?jvmType="+selectedTab+"&beSession="+BESession+"&feSession="+FESession+"&period="+period+"&graphType="+graphType;
        location.href = url;
}

function report(aElements)
{
	$("#running").hide();
       var id = document.getElementById(aElements);
       var runid = document.getElementById('running');

       if (id.style.display == "none")
       {
          if(runid !=null)
          {
             eval("runid.style.display = 'none';");
          }
          eval("id.style.display = 'block';");
       }

	window.location.href="/admin/ExportJVMDetails.do";
}

function confirmReportToSupport()
{
	var con = confirm('<fmt:message key="webclient.common.support.filecreation.confirmation"/> ');
        if(con!=false)
        {
		openWindow('/admin/StatusMessage.do','xyz','500','350');	
	}
}

function generateGraph(id,url)
{
	$.ajax({
		url: url,
		cache: false
		}).done(function(html) {
			 //alert("id"  + id);
		$('#'+id).html(html);
		//$("#zoomGraph_Modal").modal("show");
		});
}
function zoom_Graph(id,headTitle,url)
{	
	var header = "<img src='/webclient/common/images/<c:out value = "${selectedskin}"/>/pop-up-close.png' class='gbModalBoxClose' data-dismiss='modal' /><div id='modalBoxHeader' class='txt18Bold'>"+headTitle+"</div>";
	document.getElementById('header_Div').innerHTML = header;
	
	$.ajax({
		url: url,
		cache: false
		}).done(function(html) {
		$("#zoomGraph_Modal").show().modal("show");
		$("#body_Div").html(html);
		});
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body onLoad="tabSelection('<c:out value="${selectedJVMTab}"/>')">
 <div class="modal hide fade in" id="zoomGraph_Modal" style="width:650px !important; ">
      	<div class="modal-header" id="header_Div">
      	</div>
      	<div class="modal-body">
      	<div id="body_Div"></div>
      	</div>
      	<div class="modal-footer">
      	</div>
      </div>
<div class="compBlock dottedBorder">
		<span class="txt18Bold"><fmt:message key="webclient.admin.webnms.management"/></span>
		<span class="lvStatusMessage txt12Reg">
		<span id="Loading" style="display:none;position:absolute;margin-top:-20px;padding-left:500px;"><img class="loading" src="/webclient/common/images/LoadingTC.gif" /></span>
		<span Id="running">
		<c:if test="${!empty failure}" >
				<span class="alert alert-error"><c:out value='${failure}'/>
					<img class="closeListMsg" src="/webclient/common/images/<c:out value = "${selectedskin}"/>/deleteWidget.png" data-dismiss="alert">
				</span>
			</c:if>
			<c:if test="${!empty DetailsFile}" >
				<span class="alert alert-success">
					<fmt:message key='webclient.admin.mgmtgui.jvmfiles'/>
					<a href="#" onClick="javascript:openWindow('/nmsanalyzer/JVMDetails.txt','xyz','1000','700','scrollbars=yes')">
					<fmt:message key='JVMDetails.txt'/> </a>&nbsp;and&nbsp;
					<a href="#" onClick="javascript:openWindow('/nmsanalyzer/JVMThreadDump.txt','xyz','1000','700','scrollbars=yes')">
					<fmt:message key='JVMthreadDump.txt '/></a>
					<img class="closeListMsg" src="/webclient/common/images/<c:out value = "${selectedskin}"/>/deleteWidget.png" data-dismiss="alert" onclick="document.getElementById('running').style.display='none';">
				</span>
			</c:if>
			</span>
			</span>
		<span class="lvHeaderIncludes">
		<div class="btn-group">
			<button class="btn dropdown-toggle" data-toggle="dropdown"><fmt:message key='webclient.topo.objectdetails.actions'/>
			<img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/dropdown.png">
			</button>
			<ul class="dropdown-menu pull-right">
				<li><a href="javascript:report('Loading')"><fmt:message key='webclient.admin.mgmtgui.jvmdetails'/></a></li>
				<li><a href="javascript:confirmReportToSupport()"><fmt:message key='webclient.admin.mgmtgui.report.webnms'/></a></li>
			</ul>
		</div>
		</span>
</div>

<br>

			
<table width="100%" border="0" cellpadding="0" cellspacing="0">

  <tr>
  	<td class="morow" valign="top" colspan="3">
  		<div style="width:100%;float:left;height:45px;">
              <ul id="moheader" class="nav nav-pills">
              <li id="beDetails" class="active" onClick="javascript:hidediv('tab1','tab2','tab3')"><a><fmt:message key='webclient.admin.mgmtgui.bedetails'/></a>
              <span class="tabpointer" style="display:block;"></span>
              </li>
              <li id="feDetails" onClick="javascript:hidediv('tab2','tab1','tab3')"><a><fmt:message key='webclient.admin.mgmtgui.fedetails'/></a>
              <span class="tabpointer" style="display:none;"></span>
              </li>
              <li id="clientDetails" onClick="javascript:hidediv('tab3','tab1','tab2')"><a><fmt:message key='webclient.admin.mgmtgui.clientdetails'/></a>
              <span class="tabpointer" style="display:none;"></span>
              </li>
              </ul>
              </div>
  	</td>
  </tr>
  <tr>
    <td colspan="13" style="padding:5px 10px 10px 10px;">

    <table style="margin-bottom:15px;" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
          <td id='linegraph' width="15px"><img src ="/webclient/common/images/<c:out value = "${selectedskin}"/>/line_graph.png" class="lineGraph" /></td>
          <td id='linetext' width="75" nowrap class="norTxt" style="padding-left:3px;"><a href="javascript:changePreference('','','','line')"><fmt:message key='webclient.admin.mgmtgui.linegraph'/></a></td>
          <td id='areagraph' width="15px"><img src ="/webclient/common/images/<c:out value = "${selectedskin}"/>/area_graph.png" class="areaGraph" /></td>
          <td id='areatext' width="90" nowrap class="norTxt" style="padding-left:3px;"><a href="javascript:changePreference('','','','area')"><fmt:message key='webclient.admin.mgmtgui.areagraph'/></a></td>
          <td id='periodtext' width="80" style="padding-right:6px;" nowrap class="text"><fmt:message key='webclient.admin.mgmtgui.selectperiod'/></td>
	  <td width="50" align="right">
	    <select class="dropDownStyle" style="width:100px;" name="select" id="graphPeriod" onchange="javascript:changePreference('','',this.options[selectedIndex].text,'')">
	      <c:choose>
	      <c:when test="${selectedJVMTab=='BE' || empty selectedJVMTab}">
                 <option value="1" <c:if test="${JVMChartRefreshInterval.BE.Period == '1'}"> selected="true" </c:if> ><fmt:message key='webclient.admin.mgmtgui.oneday'/></option>
                 <option value="7" <c:if test="${JVMChartRefreshInterval.BE.Period == '7'}"> selected="true" </c:if> ><fmt:message key='webclient.admin.mgmtgui.sevendays'/></option>
                 <option value="30" <c:if test="${JVMChartRefreshInterval.BE.Period == '30'}"> selected="true" </c:if> ><fmt:message key='webclient.admin.mgmtgui.thirtydays'/></option>
	      </c:when>
	      <c:when test="${selectedJVMTab=='FE'}">
		 <option value="1" <c:if test="${JVMChartRefreshInterval.FE.Period == '1'}"> selected="true" </c:if> ><fmt:message key='webclient.admin.mgmtgui.oneday'/></option>
                 <option value="7" <c:if test="${JVMChartRefreshInterval.FE.Period == '7'}"> selected="true" </c:if> ><fmt:message key='webclient.admin.mgmtgui.sevendays'/></option>
                 <option value="30" <c:if test="${JVMChartRefreshInterval.FE.Period == '30'}"> selected="true" </c:if> ><fmt:message key='webclient.admin.mgmtgui.thirtydays'/></option>
	      </c:when>
	      <c:when test="${selectedJVMTab=='CLIENT'}">
		 <option value="1" <c:if test="${JVMChartRefreshInterval.CLIENT.Period == '1'}"> selected="true" </c:if> ><fmt:message key='webclient.admin.mgmtgui.oneday'/></option>
                 <option value="7" <c:if test="${JVMChartRefreshInterval.CLIENT.Period == '7'}"> selected="true" </c:if> ><fmt:message key='webclient.admin.mgmtgui.sevendays'/></option>
                 <option value="30" <c:if test="${JVMChartRefreshInterval.CLIENT.Period == '30'}"> selected="true" </c:if> ><fmt:message key='webclient.admin.mgmtgui.thirtydays'/></option>
	      </c:when>
	      </c:choose>
            </select>
          </td>
        </tr>
      </table>
     
      <table id="tab1" style="display:;" width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td> <!-- class="grayBox1" -->
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
   		<tr>
 		<td width="55%" align="right" nowrap class="darkText" style="padding-right:10px;"><a href="javascript:openWindow('/admin/ShowStandbyDetails.do','xyz','600','400')"><fmt:message key='webclient.admin.mgmtgui.standbydetails'/></a></td>	
 		</tr>
 	    </table>
	    <table style="margin-bottom:7px;" width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="50%" style="padding-right:15px;"><table border="0" cellspacing="0" cellpadding="3">
                    <tr class="proplabel">
                      <td style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.behostname'/>	</td>
                      <td nowrap>: <c:out value="${BEJVM.HOSTNAME}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.bestartuptime'/></td>
                      <td nowrap >: <c:out value="${BEJVM.StartTime}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.beosdetails'/></td>
                      <td nowrap>: <c:out value="${BEJVM.OSName}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.bevmversion'/></td>
                      <td nowrap>: <c:out value="${BEJVM.VMVersion}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.bememoryutil'/></td>
                      <td nowrap>: <c:out value="${BEJVM.MEMUTIL}"/></td>
                    </tr>
                  </table></td>
                <td valign="top" style="padding-left:15px;"><table border="0" cellspacing="0" cellpadding="3">
                    <tr class="proplabel">
                      <td style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.behostaddress'/></td>
                      <td nowrap>: <c:out value="${BEJVM.HOSTADDRESS}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.dbname'/></td>
                      <td nowrap >: <c:out value="${BEJVM.DBName}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.beosarch'/><br></td>
                      <td nowrap>: <c:out value="${BEJVM.OSArch}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.bevmvendor'/></td>
                      <td nowrap>: <c:out value="${BEJVM.VMVendor}"/></td>
                    </tr>
                  </table></td>
              </tr>
            </table>
            <table style="margin-bottom:20PX;" width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="25%" align="left" style="padding-right:10px;">
                <table border="0" cellspacing="0" cellpadding="0" class="GraphBorder">
                    <tr>
                      <td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.cpuutil" /></span></div>
                      <div id="cpu_util" style="width:220px;height:160px;">
                      <img onload="javascript:generateGraph('cpu_util','/admin/MonitorSPR.do?divId=cpu_util&rateType=CPUUtilization&hostName=<c:out value="${BEJVM.HOSTADDRESS}"/>&snmpPort=<c:out value="${BEJVM.SNMP_Port}"/>&jvmType=BE&graphType=area&osName=<c:out value="${BEJVM.OSName}"/>')" src="/webclient/common/images/spacer.gif">
                      </img></div>
                      <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('cpu_util_zoom','BE CPUUtilization Report : <c:out value="${BEJVM.HOSTADDRESS}"/> <c:out value="${BEJVM.SNMP_Port}"/>','/admin/MonitorSPR.do?divId=cpu_utilcpu_util_zoom&rateType=CPUUtilization&hostName=<c:out value="${BEJVM.HOSTADDRESS}"/>&snmpPort=<c:out value="${BEJVM.SNMP_Port}"/>&jvmType=BE&graphType=area&osName=<c:out value="${BEJVM.OSName}"/>')"></img>
                      </td>
                     </tr>
                     
                     
                     
                  </table></td>
                <td width="25%" align="left" style="padding-right:10px;"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.memoryutil" /></span></div>
                      <div id="memory_util" style="width:220px;height:160px;"><img onload="javascript:generateGraph('memory_util','/admin/MonitorSPR.do?divId=memory_util&rateType=MemoryUtilization&hostName=<c:out value="${BEJVM.HOSTADDRESS}"/>&snmpPort=<c:out value="${BEJVM.SNMP_Port}"/>&jvmType=BE&graphType=area')" src="/webclient/common/images/spacer.gif" ></img></div>
                      <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('memory_util_zoom','BE MemoryUtilization Report : <c:out value="${BEJVM.HOSTADDRESS}"/> <c:out value="${BEJVM.SNMP_Port}"/>','/admin/MonitorSPR.do?divId=memory_util_zoom&rateType=MemoryUtilization&hostName=<c:out value="${BEJVM.HOSTADDRESS}"/>&snmpPort=<c:out value="${BEJVM.SNMP_Port}"/>&jvmType=BE&graphType=area')"></img>
                      </td>
                    </tr>
                  </table></td>
                <td width="25%" align="left" style="padding-right:10px;"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                 	<td style="padding-top:3px;"  nowrap class="norTxt">
                 	<div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.threadcount" /></span></div>
                 	<div id="threadCnt" style="width:220px;height:160px;"><img onload="javascript:generateGraph('threadCnt','/admin/MonitorSPR.do?divId=threadCnt&rateType=ThreadCount&hostName=<c:out value="${BEJVM.HOSTADDRESS}"/>&snmpPort=<c:out value="${BEJVM.SNMP_Port}"/>&jvmType=BE&graphType=area')" src="/webclient/common/images/spacer.gif"></img></div>
                 	<img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('threadCnt_zoom','BE ThreadCount Report : <c:out value="${BEJVM.HOSTADDRESS}"/> <c:out value="${BEJVM.SNMP_Port}"/>','/admin/MonitorSPR.do?divId=threadCnt_zoom&rateType=ThreadCount&hostName=<c:out value="${BEJVM.HOSTADDRESS}"/>&snmpPort=<c:out value="${BEJVM.SNMP_Port}"/>&jvmType=BE&graphType=area')"></img>
                 	</td>
                 	</tr>
                  </table></td>
                <td align="left"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td style="padding-top:3px;" nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.datacollection" /></span></div>
                      <div id="dataCollect" style="width:220px;height:160px;"><img onload="javascript:generateGraph('dataCollect','/admin/MonitorSPR.do?divId=dataCollect&rateType=DataCollectionRate&hostName=<c:out value="${BEJVM.HOSTADDRESS}"/>&snmpPort=<c:out value="${BEJVM.SNMP_Port}"/>&jvmType=BE&graphType=area')" src="/webclient/common/images/spacer.gif"></img></div>
                      <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('dataCollect_zoom','DataCollectionRate in PerSec','/admin/MonitorSPR.do?divId=dataCollect_zoom&rateType=DataCollectionRate&hostName=<c:out value="${BEJVM.HOSTADDRESS}"/>&snmpPort=<c:out value="${BEJVM.SNMP_Port}"/>&jvmType=BE&graphType=area')"></img>
                      </td>
                      </tr>
                  </table></td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="25%" align="left" style="padding-right:10px;"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.statuspoll" /></span></div>
                      <div id="statusPol" style="width:220px;height:160px;">
                      <img onload="javascript:generateGraph('statusPol','/admin/MonitorSPR.do?divId=statusPol&rateType=StatusPollingRate&jvmType=BE&graphType=area')" src="/webclient/common/images/spacer.gif"></img></div>
                      <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('statusPol_zoom','StatusPollingRate','/admin/MonitorSPR.do?divId=statusPol_zoom&rateType=StatusPollingRate&jvmType=BE&graphType=area')"></img>
                      </td>
                    </tr>
                  </table></td>
                <td width="25%" align="left" style="padding-right:10px;"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
  					<td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.traprate" /></span></div>
  					<div id="Trap_Rate" style="width:220px;height:160px;"><img onload="javascript:generateGraph('Trap_Rate','/admin/MonitorSPR.do?divId=Trap_Rate&rateType=TrapRate&jvmType=BE&graphType=area')" src="/webclient/common/images/spacer.gif" ></img></div>
  					<img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('Trap_Rate_zoom','TrapRate','/admin/MonitorSPR.do?divId=Trap_Rate_zoom&rateType=TrapRate&jvmType=BE&graphType=area')"></img>
  					</td>
  					</tr>
                  </table></td>
                <td width="25%" align="left" style="padding-right:10px;"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
          		<td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.eventrate" /></span></div>
          		<div id="eventRate" style="width:220px;height:160px;"><img onload="javascript:generateGraph('eventRate','/admin/MonitorSPR.do?divId=eventRate&rateType=EventRate&jvmType=BE&graphType=area')" src="/webclient/common/images/spacer.gif" ></img></div>
          		<img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('eventRate_zoom','EventRate','/admin/MonitorSPR.do?divId=eventRate_zoom&rateType=EventRate&jvmType=BE&graphType=area')"></img>
          		</td></tr>
                  </table></td>
                <td align="left"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                <td style="padding-top:3px;"  nowrap class="norTxt">
                <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.alertrate" /></span></div>
                <div id="alertRate" style="width:220px;height:160px;"><img onload="javascript:generateGraph('alertRate','/admin/MonitorSPR.do?divId=alertRate&rateType=AlertRate&jvmType=BE&graphType=area')" src="/webclient/common/images/spacer.gif" ></img></div>
               <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('alertRate_zoom','AlertRate','/admin/MonitorSPR.do?divId=alertRate_zoom&rateType=AlertRate&jvmType=BE&graphType=area')"></img>
                </td></tr>
               </table></td>
              </tr>
            </table>
</td>
        </tr>
      </table> 
	  
	<table id="tab2" style="display:none;margin-top:-0px;" width="100%" border="0" cellpadding="0" cellspacing="0" height="510">
	<tr>
	<c:choose>
	<c:when test='${!empty FEJVMs}'>
	<td>
	<c:forEach var="feJVM" items="${FEJVMs}" varStatus="status">
		<table style="margin-bottom:20PX;" width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr><td>
		<table style="margin-bottom:7px;" width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="50%" style="padding-right:15px;"><table border="0" cellspacing="0" cellpadding="3">
                    <tr class="proplabel">
                      <td style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.fehostname'/></td>
                      <td nowrap>: <c:out value="${feJVM.value.HOSTNAME}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.feosdetails'/></td>
                      <td nowrap >: <c:out value="${feJVM.value.OSName}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.fevmversion'/></td>
                      <td nowrap>: <c:out value="Java ${feJVM.value.VMVersion}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.festartuptime'/></td>
                      <td nowrap>: <c:out value="${feJVM.value.StartTime}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.fememoryutil'/></td>
                      <td nowrap>: <c:out value="${feJVM.value.MEMUTIL}"/></td>
                    </tr>
                </table></td>
                <td valign="top" style="padding-left:15px;"><table border="0" cellspacing="0" cellpadding="3">
                    <tr class="proplabel">
                      <td style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.fehostaddress'/></td>
                      <td nowrap>: <c:out value="${feJVM.value.HOSTADDRESS}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.feosarch'/></td>
                      <td nowrap >: <c:out value="${feJVM.value.OSArch}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.fevmvendor'/><br></td>
                      <td nowrap>: <c:out value="${feJVM.value.VMVendor}"/></td>
                    </tr>
                    <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.fewebserverport'/></td>
                      <td nowrap>: <c:out value="${feJVM.value.WEBSERVER_PORT}"/></td>
                    </tr>
		   		 <tr class="proplabel">
                      <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.feservertype'/></td>
                      <td nowrap>: <c:out value="${feJVM.value.FE_SERVER_TYPE}"/></td>
                    </tr>
                </table></td>
              </tr>
            </table>
             <table style="margin-bottom:20PX;" width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="25%" align="left" style="padding-right:10px;"><table border="0" cellspacing="0" cellpadding="0">
         	     		<tr>
                      <td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.cpuutil"/></span></div>
                      <div id="cpu_utils<c:out value="${status.index}" />" style="width:220px;height:160px;">
                      <img onload="javascript:generateGraph('cpu_utils<c:out value="${status.index}" />','/admin/MonitorSPR.do?divId=cpu_utils<c:out value="${status.index}" />&rateType=CPUUtilization&hostName=<c:out value="${feJVM.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${feJVM.value.FE_JVM_SNMP_PORT}"/>&jvmType=FE&graphType=area&osName=<c:out value="${feJVM.value.OSName}"/>')" src="/webclient/common/images/spacer.gif">
                      </img></div>
                      <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('cpu_utils_zoom<c:out value="${status.index}" />','FE CPUUtilization Report : <c:out value="${feJVM.value.HOSTADDRESS}"/>, <c:out value="${feJVM.value.FE_JVM_SNMP_PORT}"/>','/admin/MonitorSPR.do?divId=cpu_utils_zoom<c:out value="${status.index}" />&rateType=CPUUtilization&hostName=<c:out value="${feJVM.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${feJVM.value.FE_JVM_SNMP_PORT}"/>&jvmType=FE&graphType=area&osName=<c:out value="${feJVM.value.OSName}"/>')"></img>
                      </td>
                     </tr>
                      
                      
                  </table></td>
                  <td width="25%" align="left" style="padding-right:10px;"><table border="0" cellspacing="0" cellpadding="0">
                  
                      <tr>
                      <td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.memoryutil" /></span></div>
                      <div id="memory_utilFE<c:out value="${status.index}" />" style="width:220px;height:160px;"><img onload="javascript:generateGraph('memory_utilFE<c:out value="${status.index}" />','/admin/MonitorSPR.do?divId=memory_utilFE<c:out value="${status.index}" />&rateType=MemoryUtilization&hostName=<c:out value="${feJVM.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${feJVM.value.FE_JVM_SNMP_PORT}"/>&jvmType=FE&graphType=area&feSession=<c:out value="${feJVM.value.FESESSIONID}"/>')" src="/webclient/common/images/spacer.gif" ></img></div>
                      <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('memory_utilFE_zoom<c:out value="${status.index}" />','FE MemoryUtilization Report : <c:out value="${feJVM.value.HOSTADDRESS}"/> <c:out value="${feJVM.value.FE_JVM_SNMP_PORT}"/>','/admin/MonitorSPR.do?divId=memory_utilFE_zoom<c:out value="${status.index}" />&rateType=MemoryUtilization&hostName=<c:out value="${feJVM.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${feJVM.value.FE_JVM_SNMP_PORT}"/>&jvmType=FE&graphType=area&feSession=<c:out value="${feJVM.value.FESESSIONID}"/>')"></img>
                      </td>
                    </tr>
                      
                      
                  </table></td>
                  <td width="25%" align="left" style="padding-right:10px;"><table border="0" cellspacing="0" cellpadding="0">
                  <tr>
				 	<td style="padding-top:3px;"  nowrap class="norTxt">
				 	<div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.threadcount" /></span></div>
				 	<div id="threadCntFE<c:out value="${status.index}" />" style="width:220px;height:160px;"><img onload="javascript:generateGraph('threadCntFE<c:out value="${status.index}" />','/admin/MonitorSPR.do?divId=threadCntFE<c:out value="${status.index}" />&rateType=ThreadCount&hostName=<c:out value="${feJVM.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${feJVM.value.FE_JVM_SNMP_PORT}"/>&jvmType=FE&graphType=area&feSession=<c:out value="${feJVM.value.FESESSIONID}"/>')" src="/webclient/common/images/spacer.gif"></img></div>
				 	<img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('threadCntFE_zoom<c:out value="${status.index}" />','BE ThreadCount Report : <c:out value="${feJVM.value.HOSTADDRESS}"/> <c:out value="${feJVM.value.FE_JVM_SNMP_PORT}"/>','/admin/MonitorSPR.do?divId=threadCntFE_zoom<c:out value="${status.index}" />&rateType=ThreadCount&hostName=<c:out value="${feJVM.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${feJVM.value.FE_JVM_SNMP_PORT}"/>&jvmType=FE&feSession=<c:out value="${feJVM.value.FESESSIONID}"/>&graphType=area')"></img>
				 	</td>
				 </tr>
                  </table></td>
                </tr>
              </table> 
	     </td></tr>
	    </table>
	   </c:forEach>
   </td>
   </c:when>
    <c:otherwise>
    <td align='center'>
    <table align="center" class="messageStyle" width="60%" border="0" cellspacing="0" style="margin-bottom:300px;" cellpadding="6">
		        <tbody><tr> 
		                <td align="center" valign="top" class="text"><fmt:message key='webclient.admin.mgmtgui.fegraph.empty'/></td>
		        </tr>
		       
		        </tbody></table>
        </td>
   </c:otherwise>
    </c:choose>
        </tr>
      </table> 
	  <table id="tab3" style="display:none;" width="100%" border="0" cellspacing="0" cellpadding="0" height="510">
  <tr>
    <td class="tableBdr1" valign="top">
    <table class="norTxt" width="100%" border="0" cellspacing="0" cellpadding="0" id="listviewtable">
    <thead>
      <tr class="listsRHeader">
        <th><fmt:message key='webclient.admin.mgmtgui.vmhost'/></th>
        <th nowrap><fmt:message key='webclient.admin.mgmtgui.vmtype'/></th>
        <th nowrap><fmt:message key='webclient.admin.mgmtgui.sessionid'/></th>
        <th nowrap><fmt:message key='webclient.admin.mgmtgui.memoryusage'/>(%)</th>	
        <th><fmt:message key='webclient.admin.mgmtgui.terminate'/></th>
        <th width="20%">&nbsp;</td>
      </tr>
      </thead>
     
	<c:forEach var="clients" items="${ClientJVMs}" varStatus="status" >
	 <c:choose>
           <c:when test="${status.count%2==0}">
              <TR height=22 id="<c:out value='${status.count}'/>" class="even">
           </c:when>
           <c:otherwise>
              <TR height=22 id="<c:out value='${status.count}'/>" class="odd">
           </c:otherwise>
        </c:choose>
        <td class="boldTxt" nowrap><a href="javascript:loadDetails('<c:out value="${clients.key}"/>','<c:out value="${status.count}"/>')"><c:out value="${clients.value.CLIENT_IPADDRESS}"/></a></td>
        <td ><c:out value="${clients.value.CLIENT_TYPE}"/></td>
	<c:choose>
           <c:when test="${clients.value.CLIENT_TYPE == 'WEBCLIENT'}">
         	<td align="center"><c:out value="${clients.value.CLIENT_ID}"/></td>               
           </c:when>
	   <c:otherwise>
        	<td align="center"><c:out value="${clients.value.BESESSION}${clients.value.FESESSION}"/></td>
           </c:otherwise>
           </c:choose>
        	<td align="center"><c:out value="${clients.value.MEMUTIL}"/>&nbsp;</td>
        	<td align="center">
           <c:choose>
	   <c:when test="${clients.value.TerminateClientFeature == 'Disable'}">
	   	<IMG border='0' align=center src='/webclient/common/images/delnode.gif'></IMG>
	   </c:when>
	   <c:otherwise>
	   	<a href="/admin/TerminateClient.do?fesession=<c:out value='${clients.value.FESESSION}'/>&besession=<c:out value='${clients.value.BESESSION}_${clients.value.FE_SERVER_PORT}'/>"><IMG border='0' align=center src='/webclient/common/images/delnode.gif'></IMG></a>
	   </c:otherwise>
	</c:choose>
	</td>
        <td >&nbsp;</td>
      </tr>
      <tr id="<c:out value='${clients.key}'/>"  class="hide" height=22 >
        <td colspan="6" valign="top" align="center">
	<table cellpadding="0" border="0" cellspacing="0" style="text-align: left; width: 100%;">
    <tbody>	

        <c:choose>
        <c:when test="${clients.value.CLIENT_TYPE == 'APPLICATIONCLIENT'}">
	<%-- Added graph for application client alone--%>
	<tr>
		
		<%-- <td>
		<td>
		<td align="right" width="90" nowrap class="norTxt" style="padding-left:3px;"> <img src="/webclient/admin/images/spacer.gif" class="lineGraph"/><a href="javascript:getGraph('linegraph')"><fmt:message key='webclient.admin.mgmtgui.linegraph'/></a></td>

		<td align="right" width="90" nowrap class="norTxt" style="padding-left:3px;"><img src="/webclient/admin/images/spacer.gif" class="areaGraph"/> <a href="javascript:getGraph('areagraph')"><fmt:message key='webclient.admin.mgmtgui.areagraph'/></a></td> --%>
		<!-- <td></td> -->
		<td></td>
		<td></td>
		<td colspan="3" style="float:right" align="right"><table><tr><td id='linegraph' width="15px"><img src ="/webclient/common/images/<c:out value = "${selectedskin}"/>/line_graph.png" class="lineGraph" /></td>
          <td id='linetext' width="75" nowrap class="norTxt" style="padding-left:3px;"><a href="javascript:changePreference('','','','line')"><fmt:message key='webclient.admin.mgmtgui.linegraph'/></a></td>
          <td id='areagraph' width="15px"><img src ="/webclient/common/images/<c:out value = "${selectedskin}"/>/area_graph.png" class="areaGraph" /></td>
          <td id='areatext' width="90" nowrap class="norTxt" style="padding-left:3px;"><a href="javascript:changePreference('','','','area')"><fmt:message key='webclient.admin.mgmtgui.areagraph'/></a></td></td></tr></table></td>
	      
		 
	</tr>
	<%-- Added graph ends here--%>
	<tr class="proplabel">
	<td width="50%" style="padding-right:15px;"><table border="0" cellspacing="0" cellpadding="3">
        <tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clienthostname'/></td>
	<td nowrap>:<c:out value="${clients.value.HOSTNAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.serverhostname'/></td>
	<td nowrap>: <c:out value="${clients.value.FE_SERVER_NAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clientusername'/></td>
	<td nowrap>: <c:out value="${clients.value.USER_NAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clienttype'/></td>
	<td nowrap>: <c:out value="${clients.value.CLIENT_TYPE}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.osdetails'/></td>
	<td nowrap>: <c:out value="${clients.value.OSName}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.vmdetails'/></td>
	<td nowrap>: <c:out value="Java ${clients.value.VMVersion}"/></td>
	</tr>
	<tr class="proplabel">
	<td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.memoryutil'/></td>
	<td nowrap>: <c:out value="${clients.value.MEMUTIL}"/></td>
        <td colspan="2"></td>      
        </tr>
        <tr><td>&nbsp;&nbsp;&nbsp;</td></tr>
	</table></td>	

	<td valign="top" style="padding-left:15px;"><table border="0" cellspacing="0" cellpadding="3">
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clienthostaddress'/></td>
	<td nowrap>: <c:out value="${clients.value.HOSTADDRESS}"/></td>
        </tr>
        <tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.serverport'/></td>
	<td nowrap>: <c:out value="${clients.value.FE_SERVER_PORT}"/></td>
        </tr>     
        <tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clientstarttime'/></td>
	<td nowrap>: <c:out value="${clients.value.CLIENT_TIME}"/>&nbsp;</td>
        </tr>
        <tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.connectedto'/></td>
	<td nowrap>: <c:out value="${clients.value.SERVER_TYPE}"/></td>
        </tr>
        <tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.osarch'/></td>
	<td nowrap>: <c:out value="${clients.value.OSArch}"/></td>
        </tr>     
 	<tr class="proplabel">       
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.totalmemory'/></td>
	<td nowrap>: <c:out value="${clients.value.TotalMemory}"/></td>
        </tr>
	</table></td>
        </tr>
	        
	        <td width="25%" align="left" style="padding-right:10px;">
	        
	        
	          <table border="0" cellspacing="0" cellpadding="0">
         	     		<tr>
                      <td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.cpuutil" /></span></div>
                      <div id="cpu_utilsApp" style="width:220px;height:160px;">
                      <img onload="javascript:generateGraph('cpu_utilsApp','/admin/MonitorSPR.do?divId=cpu_utilsApp&rateType=CPUUtilization&hostName=<c:out value="${clients.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${clients.value.CLIENT_JVM_SNMP_PORT}"/>&jvmType=CLIENT&feSession=<c:out value="${clients.value.FESESSION}"/>&beSession=<c:out value="${clients.value.BESESSION}"/>&osName=<c:out value="${clients.value.OSName}"/>&graphType=area')" src="/webclient/common/images/spacer.gif">
                      </img></div>
                      <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('cpu_utilApp_zoom','Client CPUUtilization Report : <c:out value="${clients.value.HOSTADDRESS}"/> <c:out value="${clients.value.CLIENT_JVM_SNMP_PORT}"/>','/admin/MonitorSPR.do?divId=cpu_utilApp_zoom&rateType=CPUUtilization&hostName=<c:out value="${clients.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${clients.value.CLIENT_JVM_SNMP_PORT}"/>&jvmType=CLIENT&graphType=area&feSession=<c:out value="${clients.value.FESESSION}"/>&beSession=<c:out value="${clients.value.BESESSION}"/>&osName=<c:out value="${clients.value.OSName}" />')"></img>
                      </td>
                     </tr>
                      
                      
                  </table>
	       </td>
                  
                  
                  <td width="25%" align="left" style="padding-right:10px;">
                  <table border="0" cellspacing="0" cellpadding="0">
         	     		<tr>
                      <td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.memoryutil" /></span></div>
                      <div id="memory_utilsApp" style="width:220px;height:160px;">
                      <img onload="javascript:generateGraph('memory_utilsApp','/admin/MonitorSPR.do?divId=memory_utilsApp&rateType=MemoryUtilization&hostName=<c:out value="${clients.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${clients.value.CLIENT_JVM_SNMP_PORT}"/>&feSession=<c:out value="${clients.value.FESESSION}"/>&beSession=<c:out value="${clients.value.BESESSION}"/>&jvmType=CLIENT&graphType=area')" src="/webclient/common/images/spacer.gif">
                      </img></div>
                      <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('memory_utilApp_zoom','Client MemoryUtilization Report : <c:out value="${clients.value.HOSTADDRESS}"/> <c:out value="${clients.value.CLIENT_JVM_SNMP_PORT}"/>','/admin/MonitorSPR.do?divId=memory_utilApp_zoom&rateType=MemoryUtilization&hostName=<c:out value="${clients.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${clients.value.CLIENT_JVM_SNMP_PORT}"/>&feSession=<c:out value="${clients.value.FESESSION}"/>&beSession=<c:out value="${clients.value.BESESSION}"/>&jvmType=CLIENT&graphType=area')"></img>
                      </td>
                     </tr>
</table></td>
                  
                  
                  <td width="25%" align="left" style="padding-right:10px;">
                  <table border="0" cellspacing="0" cellpadding="0">
         	     		<tr>
                      <td style="padding-top:3px;"  nowrap class="norTxt">
                      <div class="GraphheaderColor"><span class="GraphheaderTextBold"><fmt:message key="webclient.admin.mgmtgui.threadcount" /></span></div>
                      <div id="thread_utilsApp" style="width:220px;height:160px;">
                      <img onload="javascript:generateGraph('thread_utilsApp','/admin/MonitorSPR.do?divId=thread_utilsApp&rateType=ThreadCount&hostName=<c:out value="${clients.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${clients.value.CLIENT_JVM_SNMP_PORT}"/>&feSession=<c:out value="${clients.value.FESESSION}"/>&beSession=<c:out value="${clients.value.BESESSION}"/>&jvmType=CLIENT&graphType=area')" src="/webclient/common/images/spacer.gif">
                      </img></div>
                      <img src="/webclient/common/images/<c:out value = "${selectedskin}"/>/zoom_Graph.png" onclick="zoom_Graph('thread_utilApp_zoom','Client ThreadCount Report : <c:out value="${clients.value.HOSTADDRESS}"/> <c:out value="${clients.value.CLIENT_JVM_SNMP_PORT}"/>','/admin/MonitorSPR.do?divId=thread_utilApp_zoom&rateType=ThreadCount&hostName=<c:out value="${clients.value.HOSTADDRESS}"/>&snmpPort=<c:out value="${clients.value.CLIENT_JVM_SNMP_PORT}"/>&feSession=<c:out value="${clients.value.FESESSION}"/>&beSession=<c:out value="${clients.value.BESESSION}"/>&jvmType=CLIENT&graphType=area')"></img>
                      </td>
                     </tr>
				</table>
               </td>
               
               
             </tr> 
        </c:when>


        <c:when test="${clients.value.CLIENT_TYPE == 'JAVAWEBSTART'}">
        <tr>
	<td width="50%" style="padding-right:15px;"><table border="0" cellspacing="0" cellpadding="3">
        <tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='Client Host Name'/></td>
	<td nowrap>: <c:out value="${clients.value.HOSTNAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='Server Name'/></td>
	<td nowrap>: <c:out value="${clients.value.FE_SERVER_NAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='User Name'/></td>
	<td nowrap>: <c:out value="${clients.value.USER_NAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='Client Type'/></td>
	<td nowrap>: <c:out value="${clients.value.CLIENT_TYPE}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='OS Details'/></td>
	<td >: <c:out value="${clients.value.OSName}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='JDK Details'/></td>
	<td nowrap>: <c:out value="Java ${clients.value.VMVersion}"/></td>
	</tr>
	</table></td>
	
	<td valign="top" style="padding-left:15px;"><table border="0" cellspacing="0" cellpadding="3">
	<tr>
        <td nowrap style="padding-right:20px;"><fmt:message key='Client Host Address'/></td>
	<td nowrap>: <c:out value="${clients.value.HOSTADDRESS}"/></td>
        </tr>
	<tr>        
        <td nowrap style="padding-right:20px;"><fmt:message key='Server Port'/></td>
	<td nowrap>: <c:out value="${clients.value.FE_SERVER_PORT}"/></td>
        </tr>     
	<tr>        
        <td nowrap style="padding-right:20px;"><fmt:message key='Start Time'/></td>
	<td nowrap>: 
	<c:choose>
	<c:when test="${empty clients.value.CLIENT_TIME}">
		<c:out value="NA"/>
	</c:when>
	<c:otherwise>
		<c:out value="${clients.value.CLIENT_TIME}"/>
	</c:otherwise>
	</c:choose>
        </tr>
	<tr>        
        <td nowrap style="padding-right:20px;"><fmt:message key='Connected To'/></td>
	<td nowrap>: <c:out value="${clients.value.SERVER_TYPE}"/></td>
        </tr>
	<tr>        
        <td nowrap style="padding-right:20px;"><fmt:message key='OS Arch'/></td>
	<td nowrap>: <c:out value="${clients.value.OSArch}"/></td>
        </tr>     
	<tr>        
        <td nowrap style="padding-right:20px;"><fmt:message key='Total Memory'/></td>
	<td nowrap>: <c:out value="${clients.value.TotalMemory}"/></td>
        </tr>
	</table></td></tr>
        </c:when>

        <c:when test="${clients.value.CLIENT_TYPE == 'APPLETCLIENT'}">
	<tr>
	<td width="50%" style="padding-right:15px;"><table border="0" cellspacing="0" cellpadding="3">
        <tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clienthostname'/></span></td>
	<td nowrap>: <c:out value="${clients.value.HOSTNAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.serverhostname'/></td>
	<td nowrap>: <c:out value="${clients.value.FE_SERVER_NAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clientusername'/></td>
	<td nowrap>: <c:out value="${clients.value.USER_NAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clienttype'/></td>
	<td nowrap>: <c:out value="${clients.value.CLIENT_TYPE}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.osdetails'/></td>
	<td nowrap>: <c:out value="${clients.value.OSName}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.vmdetails'/></td>
	<td nowrap>: <c:out value="Java ${clients.value.VMVersion}"/></td>
	</tr>
	</table></td>

	<td valign="top" style="padding-left:15px;"><table border="0" cellspacing="0" cellpadding="3">
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clienthostaddress'/></td>
	<td nowrap>: <c:out value="${clients.value.HOSTADDRESS}"/></td>
        </tr>
	<tr class="proplabel">       
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.serverport'/></td>
	<td nowrap>: <c:out value="${clients.value.FE_SERVER_PORT}"/></td>
        </tr>     
	<tr class="proplabel">        
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clientstarttime'/></td>
	<td nowrap>: 
	<c:choose>
	<c:when test="${empty clients.value.CLIENT_TIME}">
		<c:out value="NA"/>
	</c:when>
	<c:otherwise>
		<c:out value="${clients.value.CLIENT_TIME}"/>
	</c:otherwise>
	</c:choose>
        </tr>
	<tr class="proplabel">        
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.connectedto'/></td>
	<td nowrap>: <c:out value="${clients.value.SERVER_TYPE}"/></td>
        </tr>
	<tr class="proplabel">        
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.osarch'/></td>
	<td nowrap>: <c:out value="${clients.value.OSArch}"/></td>
        </tr>     
	<tr class="proplabel">        
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.totalmemory'/></td>
	<td nowrap>: <c:out value="${clients.value.TotalMemory}"/></td>
        </tr>
	</table></td></tr>
        </c:when>


        <c:otherwise>
        <tr>
	<td width="50%" style="padding-right:15px;"><table border="0" cellspacing="0" cellpadding="3">
        <tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clienthostname'/></span></td>
	<td nowrap>: <c:out value="${clients.value.HOSTNAME}"/></td>
	</tr>
	<tr class="proplabel"> 
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.serverhostname'/></span></td>
	<td nowrap>: <c:out value="${clients.value.FE_SERVER_NAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clientusername'/></span></td>
	<td nowrap>: <c:out value="${clients.value.USER_NAME}"/></td>
	</tr>
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clienttype'/></span></td>
	<td nowrap>: <c:out value="${clients.value.CLIENT_TYPE}"/></td>
	</tr>
	</table></td>
	
	<td valign="top" style="padding-left:15px;"><table border="0" cellspacing="0" cellpadding="3">
	<tr class="proplabel">
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clienthostaddress'/></span></td>
	<td nowrap>: <c:out value="${clients.value.CLIENT_IPADDRESS}"/></td>
        </tr>
	<tr class="proplabel">        
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.serverport'/></span></td>
	<td nowrap>: <c:out value="${clients.value.FE_SERVER_PORT}"/></td>
        </tr>     
	<tr class="proplabel">        
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.clientstarttime'/></span></td>
	<td nowrap>: <c:out value="${clients.value.CLIENT_TIME}"/></td>
        </tr>
	<tr class="proplabel">        
        <td nowrap style="padding-right:20px;"><fmt:message key='webclient.admin.mgmtgui.servertype'/></span></td>
	<td nowrap>: <c:out value="${clients.value.SERVER_TYPE}"/></td>
        </tr>     
	</table></td></tr>
        </c:otherwise>
        </c:choose>

</table>
	</td>
	</c:forEach>
	</tbody>
        </tr>
      </table>
	</td>
  </tr>
</table>

	  
	  </td>
  </tr>
 </table>
</body>
</html>
