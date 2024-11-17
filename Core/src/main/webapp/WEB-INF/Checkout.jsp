<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout Form</title>
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
            flex-direction: column;
            align-items: center;
        }

        /* Logo container styling */
        .logo-container {
            margin-top: 20px;
            margin-bottom: 20px;
            text-align: center;
        }

        .logo-container img {
            height: 80px;
            cursor: pointer;
        }

        /* Container for the form */
        .form-container {
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

        /* Title styling */
        h1 {
            font-size: 28px;
            color: #DECAB6;
            margin-bottom: 20px;
        }

        /* Form and label styling */
        form {
            width: 100%;
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-size: 16px;
            color: #DECAB6;
            text-align: left;
        }

        /* Input field styling */
        input[type="text"] {
            width: 95%;
            padding: 10px;
            font-size: 16px;
            border: 2px solid #A372D1;
            border-radius: 8px;
            background-color: #771145;
            color: #E0E0E0;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
        }

        /* Button styling */
        button {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            font-weight: bold;
            color: #6F0081;
            background-color: #F2E6FF;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s ease;
            margin-top: 10px;
        }

        button:hover {
            background-color: #57006A;
            color: #F2E6FF;
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

    <!-- Form Container -->
    <div class="form-container">
        <h1>Checkout Form</h1>
        <form method="GET" action="./Checkout">
            <label for="fname">First Name:</label>
            <input type="text" id="fname" name="fname" required>

            <label for="lname">Last Name:</label>
            <input type="text" id="lname" name="lname" required>

            <label for="addr">Address:</label>
            <input type="text" id="addr" name="addr" required>

            <label for="zip">Zip Code:</label>
            <input type="text" id="zip" name="zip" required>

            <label for="pnum">Phone Number:</label>
            <input type="text" id="pnum" name="pnum" required>

            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required>

            <label for="card">Card Number:</label>
            <input type="text" id="card" name="card" required>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
