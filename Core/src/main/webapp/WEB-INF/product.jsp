<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product Details</title>
	</head>
	<body>
		<% 
		io.htmlcss.api.DatabaseFetcher db = io.htmlcss.api.DBFactory.getDatabaseFetcher();
		out.println(db.getDonuts());
		%>
	</body>
</html>