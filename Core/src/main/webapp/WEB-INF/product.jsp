<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product Details</title>
	</head>
	<body>
		<%!io.htmlcss.db.DatabaseFetcher db = io.htmlcss.db.DBFactory.getDatabaseFetcher(); %>
		<%!io.htmlcss.model.Donut donut; %>
		<%
		donut = db.getDonut(Integer.parseInt(request.getParameter("product")));
		%>
		
		<h1> <%= donut.getType() %> </h1>
		
	</body>
</html>