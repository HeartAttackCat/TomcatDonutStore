<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Portal</title>
</head>
<body>
<div class="admin-container">

        <!-- Date Field -->
        <div class="form-group">
            <label for="reportDate">View Orders:</label>
           	<form method="get" action = "./employee?command=order">
           		<input type="hidden" value="order" name="command">
           		<button type="submit" >View Orders</button>
           	</form>
        </div>
    </div>
   <div class="admin-container">

        <!-- Date Field -->
        <div class="form-group">
            <label for="reportDate">View Trays:</label>
           	<form method="get" action = "./employee?command=tray">
           		<input type="hidden" value="tray" name="command">
           		<button type="submit" >View Trays</button>
           	</form>
        </div>
    </div>
</body>
</html>