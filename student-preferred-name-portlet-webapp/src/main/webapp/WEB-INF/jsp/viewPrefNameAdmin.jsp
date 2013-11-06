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

<portlet:actionURL portletMode="VIEW" var="editPreferredNameURL">
  <portlet:param name="action" value="savePreferredName" />
</portlet:actionURL>

<portlet:actionURL portletMode="VIEW" var="deletePrefNameAdminURL">
	<portlet:param name="action" value="deleteAdmin"/>
	<portlet:param name="pvi" value="${preferredName.pvi }"/>
</portlet:actionURL>

<portlet:actionURL portletMode="VIEW" var="deletePrefNameURL">
	<portlet:param name="action" value="delete"/>
	<portlet:param name="pvi" value="${preferredName.pvi }"/>
</portlet:actionURL>

<portlet:renderURL var="cancelAction" portletMode="VIEW" windowState="NORMAL" />

<spring:message code="save" var="save" text="Save"/>

<div id="${n}student-preferred-name" class="student-preferred-name">

	<div class='modify-preferred-name'>
		<form action="${editPreferredNameURL}" method="post">
	  		<spring:nestedPath path="preferredName">
				<c:if test="${!empty error || !empty hasError}">
					<div class="error">
						<span>${error}</span>
						<span><form:errors path="firstName" cssClass="error"/>&nbsp;<form:errors path="middleName" cssClass="error"/></span>
					</div>
				</c:if>
	  			<form:hidden path="pvi"/>
				<div class="student-preferred-name ${n}-admin-edit-pref-name">
			  		<div>
				  		<div>
				  			<div class="edit-name">
						  		<span>
						  			<form:input path="firstName" class="uportal-input-text ${n}first-name" maxlength="30" />
						  		</span>
						  		<br/>
						  		<span class='label'>First Name</span>
						  		</div>
						  		<div class="edit-name">
						  		<span>
						  			<form:input path="middleName" class="uportal-input-text ${n}middle-name" maxlength="30" />
						  		</span>
						  		<br/>
						  		<span class='label'>Middle Name</span>
					  		</div>
				  		</div>
				  		<div class="edit-buttons">
				  			<span>
					  			<input class="uportal-button fancy-button" value="${save}" type="submit">
					  		</span>
					  		<span>
					  			<a href="${deletePrefNameAdminURL}" class="uportal-button" onclick="return confirm('Are you sure you want to delete this preferred name and mark it as inappropriate?');">Delete Inappropriate</a>
					  		</span>
					  		<span>
					  			<a href="${deletePrefNameURL}" class="uportal-button" onclick="return confirm('Are you sure you want to delete this preferred name?');">Delete</a>
					  		</span>
					  		<span>
					  			<a href="${ cancelAction }" class="uportal-button">Cancel</a>
					  		</span>
				  		</div>
				  		
			  		</div>
			  	</div>
		  	</spring:nestedPath>
		</form>
	</div>
</div>

<script type="text/javascript">
(function($) {
   $(document).ready(function() {
	   
   });			
})(studentPreferredNamePortlet.jQuery);	
</script>
