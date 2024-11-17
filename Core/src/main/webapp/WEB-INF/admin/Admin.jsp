<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Administrator Panel</title>
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

        /* Main container to align items side by side */
        .main-container {
            display: flex;
            justify-content: space-around;
            align-items: flex-start;
            flex-wrap: wrap;
            gap: 20px;
            padding: 20px;
            width: 90%;
            max-width: 1200px;
        }

        /* Container for centering and styling */
        .admin-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 40px;
            background-color: #3A006F;
            border-radius: 12px;
            box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
            width: 30%;
            min-width: 300px;
            text-align: center;
        }

        /* Title styling */
        h1 {
            font-size: 22px;
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
        input[type="number"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 2px solid #A372D1;
            border-radius: 8px;
            background-color: #771145;
            color: #E0E0E0;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
        }

        /* Button styling */
        button,
        input[type="submit"] {
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

        button:hover,
        input[type="submit"]:hover {
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

    <!-- Main Container -->
    <div class="main-container">
        <!-- Waste Report Container -->
        <div class="admin-container">
            <h1>Generate Waste Report</h1>
            <form method="get" action="./admin">
                <input type="hidden" value="waste" name="command">
                <label for="yr">Year:</label>
                <input type="number" id="yr" name="yr" required>
                <label for="mth">Month:</label>
                <input type="number" id="mth" name="mth" required>
                <label for="dy">Day:</label>
                <input type="number" id="dy" name="dy" required>
                <label for="bc">Range (Days):</label>
                <input type="number" id="bc" name="bc" required>
                <input type="submit" value="Generate Waste Report">
            </form>
        </div>

        <!-- Sales Report Container -->
        <div class="admin-container">
            <h1>Generate Sales Report</h1>
            <form method="get" action="./admin">
                <input type="hidden" value="sales" name="command">
                <label for="yr">Year:</label>
                <input type="number" id="yr" name="yr" required>
                <label for="mth">Month:</label>
                <input type="number" id="mth" name="mth" required>
                <label for="dy">Day:</label>
                <input type="number" id="dy" name="dy" required>
                <label for="bc">Range (Days):</label>
                <input type="number" id="bc" name="bc" required>
                <input type="submit" value="Generate Sales Report">
            </form>
        </div>

        <!-- Modify Menu Container -->
        <div class="admin-container">
            <h1>MOD POWERS</h1>
            <form method="get" action="./admin">
                <input type="hidden" value="menu" name="command">
                <button type="submit">Modify Menu</button>
            </form>
        </div>
    </div>
</body>
</html>