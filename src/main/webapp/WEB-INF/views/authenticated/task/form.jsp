<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.task.list.label.title" path="title"/>
	<acme:form-moment code="authenticated.task.list.label.initialMoment" path="initialMoment"/>
	<acme:form-moment code="authenticated.task.list.label.finalMoment" path="finalMoment"/>
	<acme:form-double code="authenticated.task.list.label.workload" path="workload"/>
	<acme:form-textarea code="authenticated.task.list.label.description" path="description"/>
	<acme:form-checkbox code="authenticated.task.list.label.isPublic" path="isPublic"/>
	
  	<acme:form-return code="authenticated.task.form.button.return"/>
</acme:form>
