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

<portlet:actionURL portletMode="VIEW" var="savePreferredNameURL">
  <portlet:param name="action" value="savePreferredName" />
</portlet:actionURL>
<form action="${savePreferredNameURL}" method="post">
	<spring:nestedPath path="PreferredName">
	First name: <form:input path="firstName" style="width: 50%;" class="uportal-input-text" />&nbsp;&nbsp;<form:errors path="firstName" cssClass="error"/>
	Middle Name: <form:input path="middleName" style="width: 50%;" class="uportal-input-text" />&nbsp;&nbsp;<form:errors path="middleName" cssClass="error"/>
	</spring:nestedPath>
</form>