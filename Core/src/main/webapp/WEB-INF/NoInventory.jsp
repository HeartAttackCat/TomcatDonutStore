<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Donut Factory - Out of Stock</title>
    <meta name="description" content="Your shopping cart at Grill's Donut Factory.">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        /* Reset and base styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #2D0055;
            color: #DECAB6;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .header {
            position: relative;
            width: 100%;
            max-width: 1000px;
            margin-bottom: 40px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .title {
            width: 100%;
            border-radius: 12px;
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.5);
        }

        .home-icon {
            position: absolute;
            top: 10px;
            right: 20px;
            width: 60px;
            height: 60px;
            cursor: pointer;
            transition: transform 0.2s ease;
        }

        .home-icon:hover {
            transform: scale(1.1);
        }

        .outer {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            max-width: 1200px;
            padding: 40px;
            background-color: #3A006F;
            border-radius: 12px;
            box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
            text-align: center;
        }

        .message {
            font-size: 24px;
            font-weight: bold;
            color: #DECAB6;
            margin-bottom: 20px;
        }

        .home-button {
            background-color: #6F0081;
            color: #F2E6FF;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
        }

        .home-button:hover {
            background-color: #57006A;
        }
    </style>
</head>
<body>
    <!-- Header with Title Image and Home Icon -->
    <div class="header">
        <img decoding="async" class="title" src="./images/logo.png" alt="Donut Factory Title">
        <a href="index.jsp">
            <img src="./images/home-icon-white-11.png" alt="Home Icon" class="home-icon">
        </a>
    </div>

    <!-- Outer container for error message -->
    <div class="outer">
        <div class="message">
            We are currently out of stock or don't enough inventory.
        </div>
    </div>
</body>
</html>