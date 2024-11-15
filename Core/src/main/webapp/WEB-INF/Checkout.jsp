<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form method=GET action="./Checkout">
			First Name <input type="text" name="fname"><br>
			Last Name <input type="text" name="lname"><br>
			Address <input type="text" name="addr">
			Zip Code <input type="text" name="zip"><br>
			Phone Number <input type="text" name="pnum"><br>
			Email <input type="text" name="email"><br>
			Card Number <input type="text" name="card"><br>
			<br>
			<button type="submit">Submit</button>
		</form>
	</body>
</html>