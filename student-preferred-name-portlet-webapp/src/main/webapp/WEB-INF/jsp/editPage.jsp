<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
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

<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div class="PreferredName">
<portlet:actionURL portletMode="VIEW" var="savePreferredNameURL">
  <portlet:param name="action" value="savePreferredName" />
</portlet:actionURL>
<form action="${savePreferredNameURL}" method="post">
	<spring:nestedPath path="preferredName">
	<span class="uportal-channel-strong">First name:</span>&nbsp;&nbsp;<form:input path="firstName" class="uportal-input-text" maxlength="30" />&nbsp;&nbsp;<form:errors path="firstName" cssClass="portlet-msg-error"/>
	<br/>
	<br/>
	<span class="uportal-channel-strong">Middle Name:</span>&nbsp;<form:input path="middleName" class="uportal-input-text" maxlength="30" />&nbsp;&nbsp;<form:errors path="middleName" cssClass="portlet-msg-error"/>
	
	<br/><br/><br/>
	<spring:message code="savePreferredName" var="savePreferredName" text="Save"/>
    <input class="uportal-button" value="${savePreferredName}" type="submit">
    
    <portlet:renderURL var="cancelAction" portletMode="VIEW" windowState="NORMAL" />
    <a href="${cancelAction}" class="uportal-button"><spring:message code="cancel" text="cancel"/></a>
	</spring:nestedPath>
</form>
</div>