<%-- $Id: userInput.jsp,v 1.1.2.2 2013/11/11 10:20:32 sivaganesh.r Exp $ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<c:if test="${field.properties.editable eq false}">
	<c:set var="readonly" value="readonly"></c:set>
</c:if>
<c:if  test="${field.properties.editable ne false}">
	<c:set var="readonly" value=""></c:set>
</c:if>
<c:if test="${field.properties.required eq true}">
	<c:set var="required" value="required"></c:set>
</c:if>
<c:if test="${field.properties.required ne true}">
	<c:set var="required" value=""></c:set>
</c:if>
<c:choose>
	<c:when test="${field.qualifier eq null}">
		<input onblur="validateFields(this,'<c:out value='${field.label}'/>',true)" onchange="validateFields(this,'<c:out value='${field.label}'/>',true)" title="<c:out value='${field.description}'/>" type="text" <c:out value='${readonly}'/> class="<c:out value='${required}'/> provisionFormFields "
		 name="$UserInput$<c:out value='${field.ID}'/>" id="<c:out value='${field.ID}'/>" value="<c:out value='${field.defaultValue}'/>"/>
	</c:when>
	<c:otherwise>
		<c:set var="range" value=""></c:set>
		<c:choose>
			<c:when test="${field.qualifier.type eq 'choice'}">
			
				<select onblur="validateFields(this,'<c:out value='${field.label}'/>',true)" onchange="validateFields(this,'<c:out value='${field.label}'/>',true)" title="<c:out value='${field.description}'/>" style="width: 210px;height: 27px !important;margin:0 0 10px 0;" class="provisionFormFields selectStyle <c:out value='${required}'/>" name="$UserInput$<c:out value='${field.ID}'/>" id="<c:out value='${field.ID}'/>">
					<c:forEach var="option" items="${field.qualifier.enums}">
						<option value="<c:out value='${option.value}'/>" <c:if test="${option.value eq field.defaultValue}">selected="selected"</c:if>>
						<c:out value='${option.name}'/>
						</option>
					</c:forEach>
				</select>
			</c:when>
			<c:when test="${field.qualifier.type eq 'textarea'}">
				<textarea onblur="validateFields(this,'<c:out value='${field.label}'/>',true)" onchange="validateFields(this,'<c:out value='${field.label}'/>',true)" title="<c:out value='${field.description}'/>" <c:out value='${readonly}'/> class="<c:out value='${required}'/> provisionFormFields"
			 	name="$UserInput$<c:out value='${field.ID}'/>" id="<c:out value='${field.ID}'/>"><c:out value='${field.defaultValue}'/></textarea>
			</c:when>
			<c:otherwise>

<c:choose>

<c:when test="${field.qualifier.type eq 'checkbox' && field.properties.editable eq false}">

<input type="hidden" value="<c:out value='${field.defaultValue}'/>" name="$UserInput$<c:out value='${field.ID}'/>" />

				<input disabled="disabled" onblur="validateFields(this,'<c:out value='${field.label}'/>',true)" onchange="validateFields(this,'<c:out value='${field.label}'/>',true)" title="<c:out value='${field.description}'/>" name="disabled_checkbox" <c:out value='${readonly}'/>
				<c:choose>
					<c:when test="${field.qualifier.type eq 'passwordfield'}">
						type="password" class="<c:out value='${required}'/> provisionFormFields" style=" padding: 4px 2px;height:17px;"
					</c:when>
					<c:when test="${field.qualifier.type eq 'checkbox'}">
						type="checkbox" class="<c:out value='${required}'/> provisionFormFields"
							<c:if test="${field.defaultValue eq 'true'}">
								checked
							</c:if>
					</c:when>
					<c:otherwise>
						type="text" class="<c:out value='${required}'/> provisionFormFields <c:out value='${field.qualifier.type}'/>"
					</c:otherwise>
				</c:choose>
				/>

</c:when>

<c:otherwise>


				<input onblur="validateFields(this,'<c:out value='${field.label}'/>',true)" onchange="validateFields(this,'<c:out value='${field.label}'/>',true)" title="<c:out value='${field.description}'/>" name="$UserInput$<c:out value='${field.ID}'/>"  <c:out value='${readonly}'/>
                                maxlength="<c:out value='${range}'/>"
                                <c:choose>
                                        <c:when test="${field.qualifier.type eq 'passwordfield'}">
                                                type="password" class="<c:out value='${required}'/> provisionFormFields" style=" padding: 4px 2px;height:17px;"
						value="<c:out value='${field.defaultValue}'/>" 
                                        </c:when>
                                        <c:when test="${field.qualifier.type eq 'checkbox'}">
                                                type="checkbox" class="<c:out value='${required}'/> provisionFormFields"
                                                        <c:if test="${field.defaultValue eq 'true'}">
                                                                checked
                                                        </c:if>
                                        </c:when>
                                        <c:otherwise>
                                                type="text" class="<c:out value='${required}'/> provisionFormFields <c:out value='${field.qualifier.type}'/>"
                                        </c:otherwise>
                                </c:choose>
                                />

</c:otherwise>

</c:choose>



			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
<div id="errorPanelDiv"><span id="errorPanel" style="display:none"></span></div>
