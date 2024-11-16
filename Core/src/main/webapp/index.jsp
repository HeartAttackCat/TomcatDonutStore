<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.util.List,io.htmlcss.model.Donut"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Donut Factory</title>
        <meta name="description" content="Order donuts from Grill's Donut Factory.">
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
                justify-content: flex-start;
                align-items: center;
                padding: 20px;
            }

            /* Header with Title Image and Cart Icon */
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
                width: 100%; /* Adjust width to allow space for cart icon */
                border-radius: 12px;
                box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.5);
            }

            /* Cart Icon Styling */
            .cart-icon {
                position: absolute;
                top: 10px; /* Adjusted for vertical alignment */
                right: 20px; /* Adjusted to move it closer to the right side */
                width: 60px;
                height: 60px;
                cursor: pointer;
                transition: transform 0.2s ease;
            }

            .cart-icon:hover {
                transform: scale(1.1); /* Slight zoom effect on hover */
            }
            
            /* Admin Icon Styling */
            .admin-icon {
                position: absolute;
                top: 10px; /* Adjusted for vertical alignment */
                right: 920px; /* Adjusted to move it closer to the right side */
                width: 60px;
                height: 60px;
                cursor: pointer;
                transition: transform 0.2s ease;
            }

            .admin-icon:hover {
                transform: scale(1.1); /* Slight zoom effect on hover */
            }

            /* Outer container to hold all content */
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
            }

            /* Content wrapper for donut list */
            .wrapper {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                gap: 30px;
                width: 100%;
            }

            /* Styling for each donut item */
            .donut {
                background-color: #771145;
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.3);
                text-align: center;
                transition: transform 0.2s ease-in-out;
                border: 2px solid #A372D1;
            }

            .donut h3 {
                font-size: 24px;
                font-weight: bold;
                color: #DECAB6;
                margin-bottom: 20px;
            }

            .donut h4 {
                font-size: 15px;
                font-weight: bold;
                color: #DECAB6;
                margin-bottom: 20px;
            }

            /* Button for product details */
            .donut button1{
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

            .donut button1:hover {
                background-color: #57006A;
            }
            
            .donut button2{
                background-color: #F2E6FF ;
                color: #6F0081;
                border: none;
                padding: 10px 20px;
                border-radius: 8px;
                font-size: 16px;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
                box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
            }

            .donut button2:hover {
                background-color: #57006A;
            }

            /* Hover effect for donut cards */
            .donut:hover {
                transform: translateY(-10px);
            }
            
            /* Responsive adjustments */
            @media (max-width: 768px) {
                .title {
                    width: 100%;
                }

                .wrapper {
                    grid-template-columns: 1fr;
                }
            }
        </style>
    </head>
    <body>
        <!-- Header with Title Image and Cart Icon -->
        <div class="header">
            <img decoding="async" class="title" src="./images/logo.png" alt="Donut Factory Title">
            <a href="Cart">
                <img src="./images/shopping-cart.svg" alt="Cart Icon" class="cart-icon">
            </a>
            
            <a href="admin">
                <img src="./images/profile-icon-avatar-icon-user-icon-person-icon-free-png.png" alt="Admin Icon" class="admin-icon">
            </a>
            
        </div>

        <!-- Outer container for content -->
        <div class="outer">
            <div class="wrapper" id="content">
                <%
                List<Donut> donuts = Donut.getMenu();
                for (Donut donut : donuts) {
                    %>
                    <!-- Individual donut container styled with purple theme -->
                    <div class="donut">
                        <h3><%= donut.getType() %></h3>
                        <h4><%= donut.getFlavor() %> - $ <%= donut.getPrice() %></h4>

                        <!-- Button for product details redirection -->
                        <button1 onclick="window.location.href='product?product=<%= donut.getId() %>'">
                            View Details
                        </button1>
                        
                        <br>
                        <br>
                        <br>
                        
                        <button2 onclick="window.location.href='Cart?command=add&did=<%= donut.getId()%>&quantity=1' " >
                        	Add to Cart
                        </button2>
                    </div>
                    <%
                }
                %>
            </div>
        </div>
    </body>
</html>
