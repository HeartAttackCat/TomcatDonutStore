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
    }

    /* Logo container styling */
    .logo-container {
        text-align: center;
        background-color: #3A006F;
        padding: 20px;
    }

    .logo-container img {
        height: 80px;
        cursor: pointer;
    }

    /* Title styling */
    h1 {
        font-size: 28px;
        color: #DECAB6;
        text-align: center;
        margin: 20px 0;
    }

    /* Center container styling */
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
        margin: 20px auto;
        text-align: center;
    }

    /* Link styling as buttons */
    button {
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
        width: 100%;
        margin-top: 10px;
    }

    button:hover {
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
</style>
</head>
<body>
    <!-- Logo at the top -->
    <div class="logo-container">
        <a href="index.jsp">
            <img src="./images/logo.png" alt="Home Logo">
        </a>
    </div>

    <!-- Page Title -->
    <h1>Employee Portal</h1>

    <!-- View Orders Section -->
    <div class="admin-container">
        <div class="form-group">
            <label for="reportDate">View Orders:</label>
            <form method="get" action="./employee?command=order">
                <input type="hidden" value="order" name="command">
                <button type="submit">View Orders</button>
            </form>
        </div>
    </div>

    <!-- View Trays Section -->
    <div class="admin-container">
        <div class="form-group">
            <label for="reportDate">View Trays:</label>
            <form method="get" action="./employee?command=tray">
                <input type="hidden" value="tray" name="command">
                <button type="submit">View Trays</button>
            </form>
        </div>
    </div>
</body>
</html>
