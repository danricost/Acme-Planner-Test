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

<h2>
	<acme:message code="administrator.dashboard.title"/>
</h2>

<table class="table table-sm">	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.totalNumberPublicTask"/>
		</th>
		<td>
			<acme:print value="${totalNumberPublicTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.totalNumberPrivateTask"/>
		</th>
		<td>
			<acme:print value="${totalNumberPrivateTask}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.totalNumberFinishedTask"/>
		</th>
		<td>
			<acme:print value="${totalNumberFinishedTask}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.totalNumberNonFinishedTask"/>
		</th>
		<td>
			<acme:print value="${totalNumberNonFinishedTask}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.averageTaskWorkload"/>
		</th>
		<td>
			<acme:print value="${averageTaskWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.maxTaskWorkload"/>
		</th>
		<td>
			<acme:print value="${maxTaskWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.minTaskWorkload"/>
		</th>
		<td>
			<acme:print value="${minTaskWorkload}"/>
		</td>
	</tr>
		<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.deviationTaskWorkload"/>
		</th>
		<td>
			<acme:print value="${deviationTaskWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.averageTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${averageTaskExecutionPeriods}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.maxTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${maxTaskExecutionPeriods}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.minTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${minTaskExecutionPeriods}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.deviationTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${deviationTaskExecutionPeriods}"/>
		</td>
	</tr>	

	
</table>

