<%--

    Copyright 2012, Board of Regents of the University of
    Wisconsin System. See the NOTICE file distributed with
    this work for additional information regarding copyright
    ownership. Board of Regents of the University of Wisconsin
    System licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<portlet:renderURL portletMode="VIEW" var="editPreferredNameURL">
  <portlet:param name="action" value="edit" />
</portlet:renderURL>

<portlet:actionURL portletMode="VIEW" var="savePreferredNameURL">
  <portlet:param name="action" value="savePreferredName" />
</portlet:actionURL>

<portlet:renderURL var="cancelAction" portletMode="VIEW" windowState="NORMAL" />

<spring:message code="savePreferredName" var="savePreferredName" text="Save"/>

<div id="${n}student-preferred-name" class="student-preferred-name">
<form action="${savePreferredNameURL}" method="post">
	<spring:nestedPath path="preferredName">
	  <table>
	  	<tr>
	  		<td><span class="uportal-channel-strong"><spring:message code="label.official.name"/></span></td>
	  		<td>${displayName}</td>
	  	</tr>
	  	<tr class='${n}view'>
	  		<td><span class="uportal-channel-strong"><spring:message code="label.preferred.name"/></span></td>
	  		<td>${firstName}&nbsp;${middleName}&nbsp;${sirName}&nbsp;<span class="uportal-channel-table-caption">${pendingStatus }</span>&nbsp;<a href="#" onclick="studentPreferredNamePortlet.displayEdit(true);"><spring:message code="edit"/></a></td>
	  	</tr>
	  	<tr class='${n}edit-error'>
	  	<td>&nbsp;</td>
	  	<td style='padding: .5em;'><form:errors path="firstName" cssClass="portlet-msg-error"/>&nbsp;<form:errors path="middleName" cssClass="portlet-msg-error"/></td>
	  	</tr>
	  	<tr class='${n}edit'>
	  		<td><span class="uportal-channel-strong"><spring:message code="label.preferred.name"/></span></td>
	  		<td>
	  			<form:input path="firstName" class="uportal-input-text" maxlength="30" />
	  			&nbsp;
	  			<form:input path="middleName" class="uportal-input-text" maxlength="30" />
	  			&nbsp;${sirName}
	  			&nbsp;<a href="#" onclick='studentPreferredNamePortlet.displayEdit(false);' class="uportal-button"><spring:message code="button.cancel" text="Cancel"/></a>
	  			&nbsp;<input class="uportal-button" value="${savePreferredName}" type="submit">
	  		</td>
	  	</tr>
	  	
	  </table>
	</spring:nestedPath>
</form>
  <c:if test="${!empty prefs['notice'][0]}">
	  <p>
	  	${prefs['notice'][0]}
	  </p>
  </c:if>
</div>

<script type="text/javascript">
(function($) {
   $(document).ready(function() {
      $(".${n}edit").hide();
      $(".${n}edit-error").hide();
      
      studentPreferredNamePortlet.displayEdit = function (enable) {
    	  if(enable) {
    		  $(".${n}edit").show();
    		  $(".${n}view").hide();
    	  } else {
    		  $(".${n}edit").hide();
    		  $(".${n}view").show();
    		  
    	  }
      }
   });			
})(studentPreferredNamePortlet.jQuery);
</script>

<c:if test="${!empty therewasanerror }">
<script type="text/javascript">
(function($) {
   $(document).ready(function() {
	   studentPreferredNamePortlet.displayEdit(true);
	   $(".${n}edit-error").show().delay();
   });			
})(studentPreferredNamePortlet.jQuery);	
</script>
</c:if>