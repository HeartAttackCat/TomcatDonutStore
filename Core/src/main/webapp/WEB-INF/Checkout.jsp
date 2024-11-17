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
			First Name <input type="text" name="fname" required><br>
			Last Name <input type="text" name="lname" required><br>
			Address <input type="text" name="addr" required>
			Zip Code <input type="text" name="zip" required><br>
			Phone Number <input type="text" name="pnum" required><br>
			Email <input type="text" name="email" required><br>
			Card Number <input type="text" name="card" required><br>
			<br>
			<button type="submit">Submit</button>
		</form>
	</body>
</html>