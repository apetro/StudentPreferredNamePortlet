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

<portlet:actionURL portletMode="VIEW" var="savePreferredNameURL">
  <portlet:param name="action" value="savePreferredName" />
</portlet:actionURL>
<portlet:actionURL portletMode="VIEW" var="deletePreferredNameURL">
  <portlet:param name="action" value="delete" />
</portlet:actionURL>

<portlet:renderURL var="cancelAction" portletMode="VIEW" windowState="NORMAL" />

<spring:message code="savePreferredName" var="savePreferredName" text="Save"/>

<div id="${n}student-preferred-name" class="student-preferred-name">
	  <div class="contact-info-official-name">
  		<span class="uportal-channel-strong"><spring:message code="label.official.name"/>:</span>
  		<span>${displayName}</span>
  	  </div>
	  <div class="contact-info-pref-name-view ${n}view">
	  		<span class="uportal-channel-strong"><spring:message code="label.preferred.name"/>:</span>
	  		<span>${firstName}
		  		    <c:if test="${!empty middleName }">
		  		    	&nbsp;${middleName}
		  		    </c:if>
		  		    <c:if test="${!empty firstName }">
		  		    	&nbsp;${sirName}
		  		    </c:if>
		  		    &nbsp;<span class="uportal-channel-table-caption">${pendingStatus }</span>
		  		    &nbsp;<a href="#" onclick="studentPreferredNamePortlet.displayEdit(true);"><spring:message code="edit"/></a>
		  		    &nbsp;<a href="${deletePreferredNameURL}" onclick="return confirm('Are you sure you want to delete your preferred name?');"><spring:message code="delete"/></a>
		  	</span>
	  	</div>
	  	<div class='edit-area'>
	  	<form action="${savePreferredNameURL}" method="post">
			<spring:nestedPath path="preferredName">
			  	<div class='${n}edit-error pref-name-edit-error' style="display: none; padding: .5em;">
			  		<span><form:errors path="firstName" cssClass="portlet-msg-error"/>&nbsp;<form:errors path="middleName" cssClass="portlet-msg-error"/></span>
			  	</div>
			  	<div class="contact-info-pref-name-edit ${n}edit" style="display: none;">
			  		<span class="uportal-channel-strong">
			  			<spring:message code="label.preferred.name"/>:
			  		</span>
			  		<div style="margin-left: 1em;">
			  			<div>
				  			<spring:message code="text.enteryour" text="Enter your preferred first and middle names: " />
				  		</div>
				  		<div>
				  			<div class="edit-name">
					  		<span>
					  			<form:input path="firstName" class="uportal-input-text" maxlength="30" />
					  		</span>
					  		<br/>
					  		<span class='label'>First Name</span>
					  		</div>
					  		<div class="edit-name">
					  		<span>
					  			<form:input path="middleName" class="uportal-input-text" maxlength="30" />
					  		</span>
					  		<br/>
					  		<span class='label'>Middle Name</span>
					  		</div>
				  		</div>
				  		<div class="edit-buttons">
				  			<span>
					  			<a href="#" onclick='studentPreferredNamePortlet.displayEdit(false);' class="uportal-button fancy-cancel"><spring:message code="button.cancel" text="Cancel"/></a>
					  		</span>
					  		<span>
					  			<input class="uportal-button fancy-button" value="${savePreferredName}" type="submit">
					  		</span>
				  		</div>
				  		
			  		</div>
			  	</div>
		  	</spring:nestedPath>
		</form>
		</div>
		<div class='edit-notice'>
	  		<c:if test="${!empty prefs['notice'][0]}">
			  <p>
			  	 ${prefs['notice'][0]}
			  </p>
			</c:if>
		</div>
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
    		  $(".${n}edit-error").hide();
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