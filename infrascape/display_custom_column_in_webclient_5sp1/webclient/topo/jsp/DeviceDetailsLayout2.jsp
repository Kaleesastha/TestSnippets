<%-- $Id: DeviceDetailsLayout2.jsp,v 1.1.2.7 2009/12/07 12:42:02 jayaprakashp Exp $ --%>

<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="html" uri="http://jakarta.apache.org/struts/tags-html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ include file="/webclient/common/jspf/commonIncludes.jspf" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="/webclient/common/js/dashboard.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="/webclient/common/js/general.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="/webclient/topo/js/moFunctions.js"></SCRIPT>
      
       
              <table  width="100%" border="0" cellspacing="0" cellpadding="5"> 
                <tr> 
                  <td width="39%"></td> 
                  <td width="61%"></td> 
                </tr> 
                <tr> 
                  <td><fmt:message key='webclient.topo.objectdetails.displayname'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.displayName}'/></td> 
                <tr class="oddRow" > 
                  <td><fmt:message key='webclient.topo.objectdetails.type'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.type}'/></td>
            <c:choose>
            <c:when test="${ObjectDetailsBean.map.type eq 'Port' || classname eq 'ManagedObject'}">
                 
            </c:when>
            <c:otherwise> 
                <tr> 
                  <td><fmt:message key='webclient.topo.objectdetails.ipaddress'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.ipAddress}'/></td>
            </c:otherwise>
            </c:choose>

	    <%-- For Test MO --%>
	    <tr> 
	    <td><fmt:message key='sysNode'/></td> 
	    <td>: <c:out value='${ObjectDetailsBean.map.sysNode}'/></td>
	    </tr> 
	    <%-- For Test MO ends --%>
            <c:choose>
            <c:when test="${ObjectDetailsBean.map.type eq 'Port' || classname eq 'ManagedObject'}">
                
            </c:when>
            <c:otherwise> 
                <tr class="oddRow"> 
                  <td><fmt:message key='webclient.topo.objectdetails.netmask'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.netmask}'/></td>
            </c:otherwise>
            </c:choose> 
                <tr> 
                  <td><fmt:message key='webclient.topo.objectdetails.managed'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.managed}'/></td> 
                <tr class="oddRow"> 
                  <td><fmt:message key='webclient.topo.objectdetails.status'/></td> 
                  <td><img src="<c:out value="${ObjectDetailsBean.map.statusImage}" />" border=0 width="16" alt="<c:out value='${ObjectDetailsBean.map.stringstatus}'/>" > <c:out value='${ObjectDetailsBean.map.stringstatus}'/></td> 
                <tr> 
                  <td><fmt:message key='webclient.topo.objectdetails.statuspollenabled'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.statusPollEnabled}'/></td> 
                <tr id="pollint" class="oddRow"> 
                  <td><fmt:message key='webclient.topo.objectdetails.pollinterval'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.pollInterval}'/> <fmt:message key='webclient.topo.objectdetails.pollinterval.seconds'/></td> 
                <tr> 
                  <td><fmt:message key='webclient.topo.objectdetails.tester'/> </td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.tester}'/></td> 
                <tr class="oddRow"> 
                  <td><fmt:message key='webclient.topo.objectdetails.userclass'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.uClass}'/></td> 
                <tr> 
                  <td><fmt:message key='webclient.topo.objectdetails.statusupdatetime'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.statusUpdateTime}'/></td> 
                <tr class="oddRow"> 
                  <td><fmt:message key='webclient.topo.objectdetails.statuschangetime'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.statusChangeTime}'/></td> 
                <tr> 
                  <td><fmt:message key='webclient.topo.objectdetails.failurecount'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.failureCount}'/></td> 
                <tr class="oddRow"> 
                  <td><fmt:message key='webclient.topo.objectdetails.failurethreshold'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.failureThreshold}'/></td>
            <c:choose>
            <c:when test="${ObjectDetailsBean.map.type eq 'Port' || classname eq 'ManagedObject'}">
                
            </c:when>
            <c:otherwise> 
                <tr> 
                  <td><fmt:message key='webclient.topo.objectdetails.isdhcp'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.isDHCP}'/></td> 
            </c:otherwise>
            </c:choose>
            <!-- Additional Details for Network,Node,Interface,Port,ManagedObject Starts here -->
            
            <c:choose>
            <c:when test="${classname eq 'Network' && ObjectDetailsBean.map.type != 'Interface' && ObjectDetailsBean.map.type != 'Port'}">
                <tr class="oddRow"> 
                  <td><fmt:message key='webclient.topo.objectdetails.discoverystatus'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.discoveryStatus}'/></td> 
                <tr> 
                  <td><fmt:message key='webclient.topo.objectdetails.discover'/></td> 
                  <td>: <c:out value='${ObjectDetailsBean.map.discover}'/></td> 
                
                 <tr class="oddRow">
                  <td> <fmt:message key='webclient.topo.objectdetails.network.subnets'/> </td>
                  <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.subNets}">
                           <select  id="subnetslist" class="formStyle" >
                              <c:forTokens items="${ObjectDetailsBean.map.subNets}" var="token" delims=" " varStatus="counter" >
                                <option  value ='<c:out value="${token}"/>'>
                                  <c:out value="${token}"/>
                                </option>
                               </c:forTokens> 
                           </select><a href="javascript:showSubNetworks()" ><fmt:message key='webclient.topo.objectdetails.network.subnets.details'/></a>
                         </c:when> <c:otherwise> -- </c:otherwise> </c:choose>
                  </td>
                 <tr>
                  <td> <fmt:message key='webclient.topo.objectdetails.network.subnetmasks'/> </td>
                  <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.subNetNetmasks}">
                           <select  id="subnetmaskslist" class="formStyle" >
                              <c:forTokens items="${ObjectDetailsBean.map.subNetNetmasks}" var="token" delims=" " varStatus="counter" >
                                <option value ='<c:out value="${token}"/>'>
                                  <c:out value="${token}"/>
                                </option>
                               </c:forTokens> 
                           </select>
                         </c:when> <c:otherwise> -- </c:otherwise> </c:choose>
                  </td>
                 <tr class="oddRow">
                  <td> <fmt:message key='webclient.topo.objectdetails.network.parentnetmask'/> </td>
                  
                  <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.parentNetmask and ObjectDetailsBean.map.parentNetmask != 'null'}">
                           <c:out value='${ObjectDetailsBean.map.parentNetmask}'/>
                        </c:when> <c:otherwise> -- </c:otherwise> </c:choose>
                  </td>
                 <tr>
                  <td> <fmt:message key='webclient.topo.objectdetails.network.parentnetwork'/> </td>
                  <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.parentNetwork and ObjectDetailsBean.map.parentNetwork != 'null'}">
                           <c:out value='${ObjectDetailsBean.map.parentNetwork}'/>
                        </c:when> <c:otherwise> -- </c:otherwise> </c:choose>
                  </td>
            </c:when>
            </c:choose>
            
            <c:choose>
            <c:when test="${classname eq 'Node' && ObjectDetailsBean.map.type != 'Interface' && ObjectDetailsBean.map.type != 'Port'}">
                  <tr class="oddRow">
                    <td> <fmt:message key='webclient.topo.objectdetails.network.interfacelist'/> </td>
                    <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.InterfaceList}">
                           <select  id="interfacelist" class="formStyle" >
                              <c:forTokens items="${ObjectDetailsBean.map.InterfaceList}" var="token" delims=" " varStatus="counter" >
                                <option value ='<c:out value="${token}"/>'>
                                  <c:out value="${token}"/>
                                </option>
                               </c:forTokens> 
                           </select>
                         </c:when> <c:otherwise> -- </c:otherwise> </c:choose>
                     </td>
                 <tr> 
                  <td nowrap><fmt:message key='webclient.topo.objectdetails.parentnet'/></td> 
                  <td nowrap>: <select id="parentnetslist2" class="formStyle">
                            <c:forTokens items="${ObjectDetailsBean.map.parentNet}" var="token" delims=" " >
                                <option  value ='<c:out value="${token}"/>'><c:out value="${token}"/></option>
                             </c:forTokens> 
                        </select><a href='javascript:showNetworkDetails()'><fmt:message key='webclient.topo.objectdetails.groupnames.details'/></a>
                        
                  </td>
                 <tr class="oddRow">
                     <td><fmt:message key='webclient.topo.objectdetails.parentkey'/></td>
                     <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.parentKey}">
                           <A href="/topo/objectdetails.do?name=<c:out value='${ObjectDetailsBean.map.parentKey}'/>" ><c:out value='${ObjectDetailsBean.map.parentKey}'/>
                           </A> </c:when> <c:otherwise> -- </c:otherwise> </c:choose>
                     </td>
                  <tr> 
                      <td nowrap><fmt:message key='webclient.topo.objectdetails.groupnames'/></td> 
                      <td nowrap>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.groupNames}">
                             <select id="groupslist"  class="formStyle">
                                 <c:forTokens items="${ObjectDetailsBean.map.groupNames}" var="token" delims=" " varStatus="counter" >
                                     <option value ='<c:out value="${token}"/>'>
                                        <c:out value="${token}"/>
                                     </option>
                                 </c:forTokens> 
                              </select><a href='javascript:showGroups()'><fmt:message key='webclient.topo.objectdetails.groupnames.details'/></a>
                            </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose> 
                       </td>
                   <tr class="oddRow"> 
                        <td><fmt:message key='webclient.topo.objectdetails.iscontainer'/></td> 
                        <td>: <c:out value='${ObjectDetailsBean.map.isContainer}'/></td> 
                   <c:choose>
                   <c:when test='${ObjectDetailsBean.map.isContainer == "true"}'> 
                   <tr class="oddRow">   
                          <td><fmt:message key='webclient.topo.objectdetails.childrenkeys'/></td>
                          <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.childrenKeys}">
                              <select name="childerenkeyslist" class="formStyle">
                                   <c:forTokens items="${ObjectDetailsBean.map.childrenKeys}" var="token" delims=" " varStatus="counter" >
                                        <option value ='<c:out value="${token}"/>'>
                                          <c:out value="${token}"/>
                                        </option>
                                    </c:forTokens> 
                              </select> 
                              </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose>
                       </td>
                     
                   </c:when>
                   </c:choose>
                   <tr> 
                             <td><fmt:message key='webclient.topo.objectdetails.isgroup'/></td> 
                             <td>: <c:out value='${ObjectDetailsBean.map.isGroup}'/></td>
                <c:choose>
                <c:when test='${ObjectDetailsBean.map.isGroup == "true"}'>
                 <tr>    
                         <td> <fmt:message key='webclient.topo.objectdetails.groupmembers'/> </td>
                          <td> <c:choose> <c:when test="${!empty ObjectDetailsBean.map.groupMembers}">
                                 <select name="groupmembers"  class="formStyle">
                                      <c:forTokens items="${ObjectDetailsBean.map.groupMembers}" var="token" delims=" " varStatus="counter" >
                                           <option value ='<c:out value="${token}"/>'>
                                               <c:out value="${token}"/>
                                           </option>
                                       </c:forTokens> 
                                 </select> 
                                 </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose>
                           </td>
                  </c:when>
                  </c:choose>
                   <tr class="oddRow">
                        <td><fmt:message key='webclient.topo.objectdetails.snmpport'/></td>
                        <td>: <c:out value='${ObjectDetailsBean.map.snmpport}'/></td>
                   <tr>
                        <td><fmt:message key='webclient.topo.objectdetails.basemibs'/></td>
                        <td>: <c:out value='${ObjectDetailsBean.map.baseMibs}'/></td>
                   <tr class="oddRow"> 
                        <td><fmt:message key='webclient.topo.objectdetails.readcommunity'/></td> 
                         <td>: <c:out value='${ObjectDetailsBean.map.community}'/></td> 
                   <tr> 
                         <td><fmt:message key='webclient.topo.objectdetails.writecommunity'/></td> 
                         <td>: <c:out value='${ObjectDetailsBean.map.writeCommunity}'/></td> 
                   <tr class="oddRow"> 
                         <td><fmt:message key='webclient.topo.objectdetails.version'/></td> 
                         <td>: <c:out value='${ObjectDetailsBean.map.version}'/></td> 
                   <c:choose>
                   <c:when test='${ObjectDetailsBean.map.version == "v3"}'>
                   <tr>
                       <td><fmt:message key='webclient.topo.objectdetails.contextname'/></td>
                       <td>: <c:out value='${ObjectDetailsBean.map.contextName}'/></td>
                   </tr>
                   <tr class="oddRow">
                        <td><fmt:message key='webclient.topo.objectdetails.username'/></td>
                        <td>: <c:out value='${ObjectDetailsBean.map.userName}'/></td>
                   </tr>
                   </c:when>
                   <c:otherwise>
                   </c:otherwise>
                   </c:choose>
            </c:when>
            </c:choose>
            
            <c:choose>
            <c:when test="${ObjectDetailsBean.map.type eq 'Interface'}">
            
                 <tr>
                     <td><fmt:message key='webclient.topo.objectdetails.parentnet'/></td>
                     <td>: <A href="/topo/objectdetails.do?name=<c:out value='${ObjectDetailsBean.map.parentNet}'/>" > <c:out value='${ObjectDetailsBean.map.parentNet}'/>  </A></td>
                </tr>
                <tr class="oddRow">
                     <td><fmt:message key='webclient.topo.objectdetails.parentnode'/></td>
                     <td>: <A href="/topo/objectdetails.do?name=<c:out value='${ObjectDetailsBean.map.parentNodeKey}'/>" > <c:out value='${ObjectDetailsBean.map.parentNodeKey}'/>  </A></td>
                </tr>
                <tr>
                     <td><fmt:message key='webclient.topo.objectdetails.parentkey'/></td>
                     <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.parentKey}">
                           <A href="/topo/objectdetails.do?name=<c:out value='${ObjectDetailsBean.map.parentKey}'/>" ><c:out value='${ObjectDetailsBean.map.parentKey}'/>
                           </A> </c:when> <c:otherwise> -- </c:otherwise> </c:choose>
                     </td>
                </tr>
                <tr class="oddRow">
                     <td><fmt:message key='webclient.topo.objectdetails.iscontainer'/></td>
                     <td>: <c:out value='${ObjectDetailsBean.map.isContainer}'/>
                     
                               
                </tr>
                <c:choose>
                <c:when test='${ObjectDetailsBean.map.isContainer == "true"}'> 
                <tr class="oddRow">   
                      <td><fmt:message key='webclient.topo.objectdetails.childrenkeys'/></td>
                      <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.childrenKeys}">
                              <select name="childerenkeyslist" class="formStyle">
                                   <c:forTokens items="${ObjectDetailsBean.map.childrenKeys}" var="token" delims=" " varStatus="counter" >
                                        <option value ='<c:out value="${token}"/>'>
                                          <c:out value="${token}"/>
                                        </option>
                                    </c:forTokens> 
                              </select> 
                              </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose>
                       </td>
                  </tr> 
                  </c:when>
                  </c:choose>
                 <tr>
                       <td><fmt:message key='webclient.topo.objectdetails.isgroup'/></td>
                       <td>: <c:out value='${ObjectDetailsBean.map.isGroup}'/></td>
                 </tr>
                 <c:choose>
                 <c:when test='${ObjectDetailsBean.map.isGroup == "true"}'>
                 <tr>    
                         <td> <fmt:message key='webclient.topo.objectdetails.groupmembers'/> </td>
                          <td> <c:choose> <c:when test="${!empty ObjectDetailsBean.map.groupMembers}">
                                 <select name="groupmembers"  class="formStyle">
                                      <c:forTokens items="${ObjectDetailsBean.map.groupMembers}" var="token" delims=" " varStatus="counter" >
                                           <option value ='<c:out value="${token}"/>'>
                                               <c:out value="${token}"/>
                                           </option>
                                       </c:forTokens> 
                                 </select> 
                                 </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose>
                           </td>
                  </tr>
                  </c:when>
                  </c:choose>
                
                <tr class="oddRow"> 
                  <td nowrap><fmt:message key='webclient.topo.objectdetails.groupnames'/></td> 
                  <td nowrap>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.groupNames}">
                             <select id="groupslist"  class="formStyle">
                                 <c:forTokens items="${ObjectDetailsBean.map.groupNames}" var="token" delims=" " varStatus="counter" >
                                     <option value ='<c:out value="${token}"/>'>
                                        <c:out value="${token}"/>
                                     </option>
                                 </c:forTokens> 
                              </select><a href='javascript:showGroups()'><fmt:message key='webclient.topo.objectdetails.groupnames.details'/></a>
                        </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose> 
                           
                    </td>
                 </tr>
            
            </c:when>
            </c:choose>
            
            <c:choose>
            <c:when test="${ObjectDetailsBean.map.type eq 'Port'}">
                
                   <tr>
                       <td><fmt:message key='webclient.topo.objectdetails.parentkey'/></td>
                       <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.parentKey}">
                         <a href="/topo/objectdetails.do?name=<c:out value='${ObjectDetailsBean.map.parentKey}'/>" ><c:out value='${ObjectDetailsBean.map.parentKey}'/>
                         </a> </c:when> <c:otherwise> -- </c:otherwise> </c:choose>
                        </td>
                  <tr class="oddRow">
                        <td><fmt:message key='webclient.topo.objectdetails.iscontainer'/></td>
                         <td>: <c:out value='${ObjectDetailsBean.map.isContainer}'/>
                   <c:choose>
                   <c:when test='${ObjectDetailsBean.map.isContainer == "true"}'> 
                   <tr class="oddRow">   
                      <td><fmt:message key='webclient.topo.objectdetails.childrenkeys'/></td>
                      <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.childrenKeys}">
                              <select name="childerenkeyslist" class="formStyle">
                                   <c:forTokens items="${ObjectDetailsBean.map.childrenKeys}" var="token" delims=" " varStatus="counter" >
                                        <option value ='<c:out value="${token}"/>'>
                                          <c:out value="${token}"/>
                                        </option>
                                    </c:forTokens> 
                              </select> 
                              </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose>
                       </td>
                   </tr> 
                  </c:when>
                  </c:choose>
                  <tr>
                   <td nowrap><fmt:message key='webclient.topo.objectdetails.groupnames'/></td> 
                   <td nowrap>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.groupNames}">
                             <select id="groupslist"  class="formStyle">
                                 <c:forTokens items="${ObjectDetailsBean.map.groupNames}" var="token" delims=" " varStatus="counter" >
                                     <option value ='<c:out value="${token}"/>'>
                                        <c:out value="${token}"/>
                                     </option>
                                 </c:forTokens> 
                              </select><a href='javascript:showGroups()'><fmt:message key='webclient.topo.objectdetails.groupnames.details'/></a>
                        </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose> 
                           
                    </td>
                  <tr class="oddRow">
                       <td><fmt:message key='webclient.topo.objectdetails.isgroup'/></td>
                       <td>: <c:out value='${ObjectDetailsBean.map.isGroup}'/></td>
                    </tr>
                    <c:choose>
                    <c:when test='${ObjectDetailsBean.map.isGroup == "true"}'>
                    <tr class="oddRow">    
                         <td> <fmt:message key='webclient.topo.objectdetails.groupmembers'/> </td>
                          <td> <c:choose> <c:when test="${!empty ObjectDetailsBean.map.groupMembers}">
                                 <select name="groupmembers"  class="formStyle">
                                      <c:forTokens items="${ObjectDetailsBean.map.groupMembers}" var="token" delims=" " varStatus="counter" >
                                           <option value ='<c:out value="${token}"/>'>
                                               <c:out value="${token}"/>
                                           </option>
                                       </c:forTokens> 
                                 </select> 
                                 </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose>
                           </td>
                     </tr>
                     </c:when>
                     </c:choose>
                    <tr>
                       <td><fmt:message key='webclient.topo.objectdetails.portifspeed'/></td>
                       <td>: <c:out value='${ObjectDetailsBean.map.portIfSpeed}'/> </td>
                    </tr>
                    <tr class="oddRow">
                       <td><fmt:message key='webclient.topo.objectdetails.portifdescr'/></td>
                       <td>: <c:out value='${ObjectDetailsBean.map.portIfDescr}'/> </td>
                    </tr>
                    <tr>
                       <td><fmt:message key='webclient.topo.objectdetails.portstate'/></td>
                       <td>: <c:out value='${ObjectDetailsBean.map.portState}'/> </td>
                    </tr>
                    <tr class="oddRow">
                       <td><fmt:message key='webclient.topo.objectdetails.portifindex'/></td>
                       <td>: <c:out value='${ObjectDetailsBean.map.portIfIndex}'/> </td>
                    </tr>
                    
                    
             </c:when>
             </c:choose>
             
             <c:choose>
            <c:when test="${classname eq 'ManagedObject'}">
                
                   <tr>
                       <td><fmt:message key='webclient.topo.objectdetails.parentkey'/></td>
                       <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.parentKey}">
                         <a href="/topo/objectdetails.do?name=<c:out value='${ObjectDetailsBean.map.parentKey}'/>" ><c:out value='${ObjectDetailsBean.map.parentKey}'/>
                         </a> </c:when> <c:otherwise> -- </c:otherwise> </c:choose>
                        </td>
                  <tr class="oddRow">
                        <td><fmt:message key='webclient.topo.objectdetails.iscontainer'/></td>
                         <td>: <c:out value='${ObjectDetailsBean.map.isContainer}'/>
                   <c:choose>
                   <c:when test='${ObjectDetailsBean.map.isContainer == "true"}'> 
                   <tr class="oddRow">   
                      <td><fmt:message key='webclient.topo.objectdetails.childrenkeys'/></td>
                      <td>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.childrenKeys}">
                              <select name="childerenkeyslist" class="formStyle">
                                   <c:forTokens items="${ObjectDetailsBean.map.childrenKeys}" var="token" delims=" " varStatus="counter" >
                                        <option value ='<c:out value="${token}"/>'>
                                          <c:out value="${token}"/>
                                        </option>
                                    </c:forTokens> 
                              </select> 
                              </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose>
                       </td>
                   </tr> 
                  </c:when>
                  </c:choose>
                  <tr>
                   <td nowrap><fmt:message key='webclient.topo.objectdetails.groupnames'/></td> 
                   <td nowrap>: <c:choose> <c:when test="${!empty ObjectDetailsBean.map.groupNames}">
                             <select id="groupslist"  class="formStyle">
                                 <c:forTokens items="${ObjectDetailsBean.map.groupNames}" var="token" delims=" " varStatus="counter" >
                                     <option value ='<c:out value="${token}"/>'>
                                        <c:out value="${token}"/>
                                     </option>
                                 </c:forTokens> 
                              </select><a href='javascript:showGroups()'><fmt:message key='webclient.topo.objectdetails.groupnames.details'/></a>
                        </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose> 
                           
                    </td>
                  <tr class="oddRow">
                       <td><fmt:message key='webclient.topo.objectdetails.isgroup'/></td>
                       <td>: <c:out value='${ObjectDetailsBean.map.isGroup}'/></td>
                    </tr>
                    <c:choose>
                    <c:when test='${ObjectDetailsBean.map.isGroup == "true"}'>
                    <tr class="oddRow">    
                         <td> <fmt:message key='webclient.topo.objectdetails.groupmembers'/> </td>
                          <td> <c:choose> <c:when test="${!empty ObjectDetailsBean.map.groupMembers}">
                                 <select name="groupmembers"  class="formStyle">
                                      <c:forTokens items="${ObjectDetailsBean.map.groupMembers}" var="token" delims=" " varStatus="counter" >
                                           <option value ='<c:out value="${token}"/>'>
                                               <c:out value="${token}"/>
                                           </option>
                                       </c:forTokens> 
                                 </select> 
                                 </c:when> <c:otherwise> <span >--</span> </c:otherwise></c:choose>
                           </td>
                     </tr>
                     </c:when>
                     </c:choose>
                    
                                        
             </c:when>
             </c:choose>
            <!-- Additional Details for Network,Node,Interface,Port,ManagedObject Ends here -->                 
              </table> 
      
			
		
		 
 
