<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.javacodegeeks.examples.jspexample.db.DbOperations"%>
<%@ page import="java.util.*"%>
<%@ page import="com.javacodegeeks.examples.jspexample.db.entity.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Issue Tracking Application</title>

<link rel="stylesheet" href="/jspexample/static/css/bootstrap.min.css" />
</head>
<body>
	<div style="width: 500px; margin-left: 50px; margin-top: 20px;">

		<div class="col-md-12 col-sm-6 col-xs-12">

			<div class="panel panel-default">

				<div class="panel-heading clearfix">
					<i class="icon-calendar"></i>
					<h3 class="panel-title">Add New Issue</h3>
				</div>

				<div class="panel-body">
					<form id="issueForm" method="post"
						class="form-horizontal row-border" action="/jspexample/addIssue">

						<div class="form-group">
							<label class="col-md-3 control-label">Title</label>
							<div class="col-md-9">
								<input type="text" name="title" class="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">Opened By</label>
							<div class="col-md-6">
								<select class="form-control" name="openedby">
									<option value="Bob">Bob</option>
									<option value="George">George</option>
									<option value="Ali">Ali</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">Priority</label>
							<div class="col-md-6">
								<select class="form-control" name="priority">
									<option value="High">High</option>
									<option value="Medium">Medium</option>
									<option value="Low">Low</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">Comments</label>
							<div class="col-md-9">
								<textarea rows="6" class="form-control" name="comments"></textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3"></label>
							<div class="col-md-9">
								<input type="submit" value="Submit" />
							</div>
						</div>
					</form>
				</div>

			</div>

		</div>
	</div>

	<%
		List< Issue > issueList = DbOperations.getTheInstance().getAllIssues();	
		pageContext.setAttribute("issueList", issueList);		
		
		Map< String, String > issueColors = new HashMap< String, String >();
		issueColors.put( "OPEN", "#E2C8C8" );
		issueColors.put( "FIX", "#C1E212" );
		
		pageContext.setAttribute("issueColors", issueColors );
	%>

	<div style="width: 800px; margin-left: 50px; margin-top: 30px;">

		<%
			if ( issueList.size() > 0 ) {
		%>

		<div class="col-md-11">
			<div class="panel panel-default">

				<div class="panel-heading">
					<i class="icon-calendar"></i>
					<h3 class="panel-title">Issue List</h3>
				</div>
				<div class="panel-body">
					<table class="table table-hover col-md-11">
						<thead>
							<tr>
								<th class="col-md-2">Title</th>
								<th class="col-md-2">Opened By</th>
								<th class="col-md-1">Priority</th>
								<th class="col-md-2">Create Time</th>
								<th class="col-md-1">Status</th>
								<th class="col-md-1">Fix</th>
								<th class="col-md-1">Close</th>
								<th class="col-md-1">Reopen</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${issueList}" var="item">
								<c:if test="${item.status ne 'CLOSE'}">						
									<tr style="background-color: ${issueColors[item.status]}">
										<td><c:out value="${item.title}"/></td>
										<td><c:out value="${item.openedby}"/></td>
										<td><c:out value="${item.priority}"/></td>
										<td><fmt:formatDate value="${item.createDate}" pattern="dd-MM-yyyy HH:mm" /></td>
										<td><c:out value="${item.status}"/></td>
										<td><input type="button" <c:if test="${item.status == 'FIX'}"> disabled="disabled" </c:if> onclick="location.href = '/jspexample/updateIssue?id=<c:out value="${item.id}"/>&newStatus=FIX'" value="Fix" /></td>
										<td><input type="button" onclick="location.href = '/jspexample/updateIssue?id=<c:out value="${item.id}"/>&newStatus=CLOSE'" value="Close" /></td>
										<td><input type="button" <c:if test="${item.status == 'OPEN'}"> disabled="disabled" </c:if> onclick="location.href = '/jspexample/updateIssue?id=<c:out value="${item.id}"/>&newStatus=OPEN'" value="Reopen" /></td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>				
				</div>
			</div>
		</div>																				
		<%
			}
		%>
	</div>	
	
	<div>
		<jsp:useBean id="companyBean" class="com.javacodegeeks.examples.jspexample.db.entity.Company" />
		
		<jsp:setProperty property="name" name="companyBean" value="A"/>
		<jsp:setProperty property="establishYear" name="companyBean" value="1998"/>
		
		<b><jsp:getProperty property="name" name="companyBean"/></b> company since 
		<b><jsp:getProperty property="establishYear" name="companyBean"/></b>
	</div>

	<script type="text/javascript"
		src="/jspexample/static/js/jquery-1.11.3.min.js" />	
	<script type="text/javascript"
		src="/jspexample/static/js/bootstrap.min.js" />	
			
</body>

</html>