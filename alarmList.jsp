<%-- $Id: alarmList.jsp,v 1.6.8.12 2013/09/02 05:44:35 nishanthini.k Exp $ --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<script>
/* function viewAlarmDetails(url)
{
	var uri = encodeURI(url);
	parent.document.location.href=uri;
} */
function viewAlarmDetails(entity,groupName)
{
	$('input:hidden[name="entity"]').val(entity);
	$('input:hidden[name="groupName"]').val(groupName);
	document.faultAlarmDetailsForm.submit();
}

function viewfailureObjDetails(entity)
{
	$("#entityFailure").val(entity);
	document.failureAlarmDetailsForm.submit();
}
</script>
<form name="listView">
<input type="hidden" value='<c:out value="${userName}"/>' id="loggedinuser">
<table width="100%" align="left" class="htableBorder" id="listviewtable">
	<thead>
		<tr class="listsRHeader">
			<th id="severity" valign="center" nowrap>
			<c:if test="${empty ComplexSearchView}">     
			<input name="toggleAll"  type="checkbox" onclick="javascript:ToggleAll(this,this.form)">
			</c:if>
						<a class="txt11Bold8" href="javascript:doSorting('<c:out value="${isAscending}"/>','severity')">
						<fmt:message key="webclient.fault.alarm.severity"/>
						</a>
			</th>
			<c:choose>
				<c:when test="${alertPickup=='true' && alertAnnotation!='true'}">
					<Th id="action" nowrap><a class="txt11Bold8" href="javascript:void(0);"><fmt:message key='webclient.fault.alarm.actions'/></a>
					</Th>
					</c:when>
					<c:when test="${ alertAnnotation=='true' && alertPickup!='true'}">
					<Th nowrap id="action"><a class="txt11Bold8" href="javascript:void(0);"><fmt:message key='webclient.fault.alarm.actions'/></a>
					</Th>
					</c:when>
					<c:when test="${alertPickup=='true' && alertAnnotation=='true'}">
					<Th nowrap id="action"><span style='padding-right:25px;'><fmt:message key='webclient.fault.alarm.actions'/></a></span>
					</Th>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<c:forEach var="value" items="${headerList}" varStatus="status">
				<c:set var="val" value="${value.columnName}"/>
				<c:if test="${(val != 'severity') and (val != 'action')}">
				<Th class="sortableTH cursorMove" nowrap="" width="100" id='<c:out value="${val}"/>'>
				<c:choose>
					<c:when test="${!empty ComplexSearchView}">     
					<a class="txt11Bold8"><fmt:message key='webclient.fault.alarm.${value.columnName}'/></a>
					</c:when>
					<c:otherwise>
					<a class="txt11Bold8" href="javascript:doSorting('<c:out value="${isAscending}"/>','<c:out value="${val}"/>')">
					<fmt:message key='webclient.fault.alarm.${value.columnName}'/>
					<c:if test='${value.columnName == orderByColumn}'>
						<c:choose>
							<c:when test="${isAscending == 'true'}">
							<img src="/webclient/common/images/<c:out value = '${selectedskin}'/>/sortupgrey.gif" border=0 width="11" height="7" hspace="2" vspace="1" alt="" title="">
							</c:when>
							<c:otherwise>
							<img src="/webclient/common/images/<c:out value = '${selectedskin}'/>/sortdowngrey.gif" border=0 width="11" height="7" hspace="2" vspace="1" alt="" title="">
							</c:otherwise>
						</c:choose>
					</c:if>
						<c:if test="${empty orderByColumn}"> 
							<c:if test="${value.columnName == 'modTime'}">
							<img src="/webclient/common/images/<c:out value = '${selectedskin}'/>/sortdowngrey.gif" border=0 width="11" height="7" hspace="2" vspace="1" alt="sortdown" title="sortdown">
							</c:if>
						</c:if>
					</a>
					</c:otherwise>    
				</c:choose>     
				</Th>
				</c:if>
			</c:forEach>
		</tr>		
</thead>
<tbody>
<c:forEach var="prop" items="${viewData}" varStatus="status">
		<tr class="tabledata">
		<c:choose>
			<c:when test="${prop.strSeverity == 'Clear'}">
				<c:set var="severity_class" value="status_clear"/>
			</c:when>
			<c:when test="${prop.strSeverity == 'Critical'}">
				<c:set var="severity_class" value="status_critical"/>
			</c:when>
			<c:when test="${prop.strSeverity == 'Info'}">
				<c:set var="severity_class" value="status_info"/>
			</c:when>
			<c:when test="${prop.strSeverity == 'Major'}">
				<c:set var="severity_class" value="status_major"/>
			</c:when>
			<c:when test="${prop.strSeverity == 'Minor'}">
				<c:set var="severity_class" value="status_minor"/>
			</c:when>
			<c:when test="${prop.strSeverity == 'Warning'}">
				<c:set var="severity_class" value="status_warning"/>
			</c:when>
			<c:otherwise>
				<c:set var="severity_class" value="status_unknown"/>
			</c:otherwise>
		</c:choose>	
		<td width="105"
		class="<c:out value="${severity_class}"/>" nowrap>
			<c:if test="${empty ComplexSearchView}">
				<input name="rowSelection"  type="checkbox" value="<c:out value='${prop.entity}'/>" onclick = "javascript:Toggle(this,this.form,event)"> 
			</c:if>	
			<span class="txt13Bold2Black"><c:out value="${prop.strSeverity}"/></span>
		</td>
		<c:if test="${alertPickup=='true'}">
			<c:choose>
				<c:when test="${empty prop.who}">
					<TD align='left' nowrap><div style='min-width:85px;'>&nbsp;
						<a class="darkText pickUpAlarm" data-entity="<c:out value="${prop.entity}"/>" href="javascript:void(0);"> <img src="/webclient/common/images/<c:out value='${sessionScope.selectedskin}'/>/alarm_unacknowleged.png" alt="Alarm Unacknowledged" title="<fmt:message key='webclient.fault.alarmdetails.unacknowleged' />"></a>
				</c:when>
				<c:otherwise>
					<TD align='left' nowrap><div style='min-width:85px;'>&nbsp;
						<a class="darkText unPickUpAlarm" data-entity="<c:out value="${prop.entity}"/>" href="javascript:void(0);"> <img src="/webclient/common/images/<c:out value='${sessionScope.selectedskin}'/>/alarm_actionack.png" alt="Alarm Acknowledged" title="<fmt:message key='webclient.fault.alarmdetails.acknowleged' />"></a>
				</c:otherwise>
			</c:choose>
			<span class="text">|</span>
		</c:if>
		<c:if test="${alertAnnotation=='true'}">
				<a class="darkText annotateAlarm" href="javascript:void(0);" data-entity="<c:out value="${prop.entity}"/>" data-modalname="annotateAlarmModal"> <img src="/webclient/common/images/<c:out value='${sessionScope.selectedskin}'/>/alarm_actionaddnote.png" alt="Annotate" title="<fmt:message key='webclient.fault.alarmdetails.annotatenotes.button.annotate' />"></a>
			
			</div>	
		</td>
		</c:if>
		<!-- <td style='width:50px;'></td> -->
		<%-- <c:if test="${prop.strSeverity != 'Clear'}">
		<td>hiiiii</td>
		</c:if> --%>
	<c:forEach var="value1" items="${headerList}"  varStatus="status1">
		<c:set var="val" value="${value1.columnName}"/>
		<c:if test="${(val != 'severity') and (val != 'action')}">
		<%-- <c:url var="url" value="/fault/AlarmDetails.do"></c:url> --%>
		
		<c:choose>
			<c:when test="${val == 'previousSeverity'}">
			<c:choose>
				<c:when test="${prop.previousStrSeverity == '-'}">
				<td data-colname='<c:out value='${val}'/>' nowrap width="100"><div align="center"> - </div></td>
				</c:when>
				<c:otherwise>
				<td data-colname='<c:out value='${val}'/>' nowrap width="100"><div align="center"><img src="<c:out value="${prop.preimgsrc}"/>" border=0 width="16" height="16" hspace="1" alt="<c:out value="${prop.previousStrSeverity}"/>" title="<c:out value="${prop.previousStrSeverity}"/>"></div></td>
				</c:otherwise>
			 </c:choose>
			</c:when>
			<c:when test="${val == 'createTime'}" > 
				<td nowrap class='liteText' data-colname='<c:out value='${val}'/>'>
					<c:choose>
					<c:when test='${prop.formattedCreateTime != ""}'>
					<c:out value="${prop.formattedCreateTime}"/>
					</c:when>
					<c:otherwise>
					&nbsp;
					</c:otherwise>
					</c:choose>
				</td>
			</c:when>
			<c:when test="${val == 'modTime'}" > 
				<td nowrap="" class="liteText" width="230" data-colname='<c:out value='${val}'/>'>
				<c:choose>
					<c:when test='${prop.formattedModTime != ""}'>
					<c:out value="${prop.formattedModTime}"/>
					</c:when>
					<c:otherwise>
					&nbsp;
					</c:otherwise>
				</c:choose>
				</td>
			</c:when>
			<c:when test="${val != 'message' && val != 'entity'}" > 
				<td nowrap class="liteText" data-colname='<c:out value='${val}'/>'>
				<c:choose>
				<c:when test='${prop[val] != ""}'>
				<c:out value="${prop[val]}"/>
				</c:when>
				<c:otherwise>
				</c:otherwise>
				</c:choose>
				</td>
			</c:when>
			<c:otherwise> 
				<c:choose>
				<c:when test="${val == 'message'}">
				<c:set var="msg" value="${prop.message}" scope="request" />
				<%
				String name  = (String) request.getAttribute("msg");
				if (name.length() > 35)
				{
				request.setAttribute("trimmsg",(name.substring(0,33)+".."));
				}
				else
				{
				request.setAttribute("trimmsg",name);
				}
				%>
				<td nowrap data-colname='<c:out value='${val}'/>'> 
				
				<a class="darkText" href="javascript:viewAlarmDetails('<c:out value='${prop.entity}'/>','<c:out value='${prop.groupName}'/>')" title="<c:out value='${prop.message}'/>"><c:out value="${trimmsg}"/></a></td>
				</c:when>
				<c:when test="${permittedToViewAlert == true}">
				<c:set var="entity" value="${prop.entity}" scope="request" />
				<%
				String temp = (String) request.getAttribute("entity");
				boolean flag = (temp.indexOf("KeyPerformanceIndicator") > -1);
				request.setAttribute("flag", flag);
				%>
				<c:choose>
				<c:when test="${flag == true}">
				<td><span class="darkText"><c:out value="${entity}"/></span></td>
				</c:when>
				<c:otherwise>
				<td nowrap data-colname='<c:out value='${val}'/>'><a class="darkText" href="javascript:viewfailureObjDetails('<c:out value='${prop.entity}'/>')" title="<c:out value='${prop.entity}'/>"><c:out value="${prop.entity}"/></a></td>
				</c:otherwise>
				</c:choose>
				</c:when>
				<c:otherwise>
				<td nowrap class="liteText" data-colname='<c:out value='${val}'/>'> <c:out value="${prop.source}"/></td>
				</c:otherwise>
				</c:choose>
			</c:otherwise> 
		</c:choose>
		</td> 
		</c:if>
	</c:forEach>
	</TR>
</c:forEach>
</tbody>
</table>
</form>
<form action="/fault/AlarmDetails.do" method="POST" name="faultAlarmDetailsForm">
	<input type="hidden" name="method" value="traversePage"/>
	<input type="hidden" name="tab" value="tabOne"/>
	<input type="hidden" name="entity"/>
	<input type="hidden" name="groupName"/>
</form>
<form action="/topo/objectdetails.do" method="POST" name="failureAlarmDetailsForm">
	<input type="hidden" name="requestid" value="SNAPSHOT"/>
	<input type="hidden" name="entity" id ="entityFailure" />
	<input type="hidden" name="selectedTab" value="Network Database"/>
</form>
<script>
	viewPageName="alarmlist";
	$(document).ready(function() {
    var num = $(".tabledata:nth-child(2) > td").length;
	var x = new Array();
	var i=0;
	$('.tabledata:nth-child(2) > td').each(function() {

		if($(this).width() > 10)
		x[i] = $(this).width()+30;		
		else
		x[i] = 200;
		i++;
	});
	
	var j;
	$('.tabledata').each(function() {
	j=0;
		$.each(this.cells, function(){
			if(j!=num-1)
			$(this).css("width", x[j]+"px");
			j++;
		});
	});
	
	i=0;
	$('.listsRHeader th').each(function () {
	
			if(i!=num-1)
			$(this).css("width", x[i]+"px");
			
			i++;		
	});
	
});
</script>
<script src="/webclient/common/js/greytheme.js"></script>

