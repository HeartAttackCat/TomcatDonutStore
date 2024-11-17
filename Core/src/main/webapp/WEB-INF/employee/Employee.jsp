<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Portal</title>
<style>
        /* General reset and body styles */
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
            background-color: #2D0055;
            color: #E0E0E0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: row;
            gap: 10px;
        }

        /* Container for centering and styling the panel */
        .admin-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 40px;
            background-color: #3A006F;
            border-radius: 12px;
            box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
            width: 90%;
            max-width: 400px;
            text-align: center;
        }

        /* Logo container styling */
        .logo-container {
            margin-bottom: 20px;
            text-align: center;
        }

        .logo-container img {
            height: 80px;
            cursor: pointer;
        }

        /* Title styling */
        h1 {
            font-size: 28px;
            color: #DECAB6;
            margin-bottom: 20px;
        }

        /* Link styling as buttons */
        .admin-links button {
            text-decoration: none;
            padding: 12px;
            font-size: 18px;
            font-weight: bold;
            color: #6F0081;
            background-color: #F2E6FF;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease;
            display: block;
            text-align: center;
            width: 100%;
            margin-top: 10px;
        }

        .admin-links button:hover {
            background-color: #57006A;
            color: #F2E6FF;
        }

        /* Form styling */
        .form-group {
            margin-bottom: 15px;
            width: 100%;
            text-align: left;
        }

        label {
            font-size: 18px;
            color: #DECAB6;
        }

        input[type="date"] {
            width: 95%;
            padding: 10px;
            font-size: 16px;
            border: 2px solid #A372D1;
            border-radius: 8px;
            background-color: #771145;
            color: #E0E0E0;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
            margin-top: 5px;
        }
    </style>
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