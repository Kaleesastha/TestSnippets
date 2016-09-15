<%-- $Id: eventList.jsp,v 1.5.8.3 2013/07/11 08:41:37 saminathan.b Exp $ --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<form name="listView">
	<table width="100%" align="left" class="htableborder" id="listviewtable">
	<thead>
		<tr class="listsRHeader">
			<th id="severity" valign="center"  nowrap="">
			<c:if test="${empty ComplexSearchView}">     
				<input name = "toggleAll"  type = "checkbox" onclick = "javascript:ToggleAll(this,this.form)"/>
			</c:if>
				<a href="javascript:doSorting('<c:out value="${isAscending}"/>','severity')">
					<fmt:message key="webclient.fault.event.severity"/>
				</a>
			</th>
			<c:forEach var="value" items="${headerList}">
				<c:set var="val" value="${value.columnName}"/>
				<c:if test="${val != 'severity'}">
					<TH class="sortableTH  cursorMove" nowrap id='<c:out value="${val}"/>'>
				<c:choose>
					<c:when test="${!empty ComplexSearchView}">     
						<a><fmt:message key='webclient.fault.event.${value.columnName}'/></a>
					</c:when>
					<c:otherwise>
						<a href="javascript:doSorting('<c:out value="${isAscending}"/>','<c:out value="${val}"/>')"><fmt:message key='webclient.fault.event.${value.columnName}'/>
						<c:if test='${value.columnName == orderByColumn}'>
							<c:choose>
								<c:when test="${isAscending == 'true'}">
								<img src="/webclient/common/images/<c:out value = '${selectedskin}'/>/sortupgrey.gif">
								</c:when>
								<c:otherwise>
								<img src="/webclient/common/images/<c:out value = '${selectedskin}'/>/sortdowngrey.gif">
								</c:otherwise>
							</c:choose>
							</c:if>
							<c:if test="${empty orderByColumn}"> 
								<c:if test="${value.columnName == 'id'}">
									<img src="/webclient/common/images/<c:out value = '${selectedskin}'/>/sortdowngrey.gif" border=0 width="11" height="7" hspace="2" vspace="1">
								</c:if>
							</c:if>
						</a>
					</c:otherwise>
				</c:choose>
				</TH>
				</c:if>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="prop" items="${viewData}" varStatus="status" >
		<tr  class="tabledata">
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
				<td class="<c:out value='${severity_class}'/>" nowrap="" width="105"> 
		<c:if test="${empty ComplexSearchView}"> 
				<input name="rowSelection" type = "checkbox" value="<c:out value='${prop.id}'/>,<c:out value="${prop.entity}"/>" onclick = "javascript:Toggle(this,this.form,event)">
			</c:if>
				<span nowrap="" class="txt13Bold2Black"><c:out value="${prop.strSeverity}"/></span>
				</td>	   
			<c:forEach var="value1" items="${headerList}">
				<c:set var="val" value="${value1.columnName}"/>
				<c:if test="${val != 'severity'}">
				<c:url var="url" value="/fault/EventDetails.do?id=${prop.id} "></c:url>
				<c:choose>
				<c:when test="${val == 'text'}"> 
				<c:set var="message" value="${prop.text}" scope="request" />
				<% 
				String name  = (String) request.getAttribute("message");
				if (name.length() > 35)
				{
				request.setAttribute("trimmsg",(name.substring(0,33)+".."));
				}
				else
				{
				request.setAttribute("trimmsg",name);
				}
				%>
				<td nowrap data-colname='<c:out value='${val}'/>'><a class="darkText" href="<c:out value='${url}'/>" ><c:out value="${trimmsg}"/></a></td>
				</c:when>
			        <c:when test="${val == 'source'}">
				<td nowrap data-colname='<c:out value='${val}'/>'>
				<c:choose>
				<c:when test="${prop.source == 'KeyPerformanceIndicator'}">
				<p class="darkText"><c:out value="${prop.source}"/></p>
				</c:when>
				<c:otherwise>
				<a class="darkText" href="/topo/objectdetails.do?requestid=SNAPSHOT&name=<c:out value='${prop.source}'/>&selectedTab=Network Database" title="<c:out value='${prop.Name}'/>" ><c:out value="${prop.source}"/></a>
				</c:otherwise>
				</c:choose>
				</td>
				</c:when> 

				<c:when test="${val == 'time'}">
				<td  nowrap class="liteText" data-colname='<c:out value='${val}'/>'>
				<c:choose>
				<c:when test='${prop.formattedTime != ""}'>
				<c:out value="${prop.formattedTime}"/>&nbsp;
				</c:when>
				<c:otherwise>
				&nbsp;
				</c:otherwise>
				</c:choose>
				</td>
				</c:when>
				<c:otherwise>
				<td  nowrap class="liteText" data-colname='<c:out value='${val}'/>'>
				<c:choose>
				<c:when test='${prop[val] != ""}'>
				<c:out value="${prop[val]}"/>&nbsp;
				</c:when>
				<c:otherwise>
				&nbsp;
				</c:otherwise>
				</c:choose>
				</td>
				</c:otherwise> 
				</c:choose>
				</c:if>
			</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
	</table>
</form>
<script>viewPageName="eventlist";</script>
<script src="/webclient/common/js/greytheme.js"></script>
<script>
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
