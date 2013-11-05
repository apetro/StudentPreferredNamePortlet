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

<portlet:renderURL portletMode="VIEW" var="searchPviURL">
  <portlet:param name="action" value="searchPvi" />
</portlet:renderURL>

<portlet:renderURL var="cancelAction" portletMode="VIEW" windowState="NORMAL" />

<spring:message code="search" var="search" text="Search"/>

<div id="${n}student-preferred-name-admin" class="student-preferred-name-admin">
	<div class='pvi-lookup'>
	<c:if test="${!empty error }">
		<div class="error">
			<span>${error}</span>
		</div>
	</c:if>
  	<form action="${searchPviURL}" method="post">
		<div class="admin-pvi-search ${n}-admin-pvi-search">
	  		<div>
		  		<div>
		  			<div class="pvi-search">
			  		<span class="uportal-channel-strong">
			  			<spring:message code="label.preferred.pvi"/>:
			  		</span>
			  		<span>
			  			<input type="text" name="pvi" class="uportal-input-text" maxlength="9" />
			  		</span>
			  		</div>
		  		</div>
		  		<div class="submit-buttons">
		  			<span>
			  			<input class="uportal-button fancy-button" value="${search}" type="submit">
			  		</span>
		  		</div>
		  		
	  		</div>
	  	</div>
	</form>
	</div>
</div>

<script type="text/javascript">
(function($) {
   $(document).ready(function() {
	   
   });			
})(studentPreferredNamePortlet.jQuery);	
</script>
