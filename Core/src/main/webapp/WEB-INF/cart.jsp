<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, io.htmlcss.model.Donut, io.htmlcss.model.Cart, io.htmlcss.model.Order"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Donut Factory - Cart</title>
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
			
			/*checkout button stuff */
			.checkout {
				background-color: #771145;
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.3);
                text-align: center;
                transition: transform 0.2s ease-in-out;
                border: 2px solid #A372D1;
			}
			
			.checkout h3 {
                font-size: 24px;
                font-weight: bold;
                color: #DECAB6;
                margin-bottom: 20px;
            }

            .checkout h4 {
                font-size: 15px;
                font-weight: bold;
                color: #DECAB6;
                margin-bottom: 20px;
            }

            /* Button for product details */
            .checkout button {
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

            .checkout button:hover {
                background-color: #57006A;
            }

            /* Hover effect for donut cards */
            .checkout:hover {
                transform: translateY(-10px);
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
            .donut button {
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

            .donut button:hover {
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
        </div>

        <!-- Outer container for cart content -->
        <div class="outer">
            <div class="wrapper" id="cart-content">
                <h2>Your Cart</h2>
                <%
                Cart cart = (Cart) session.getAttribute("cart");
        		List<Order> cartItems = cart.getItems();
                
                double total = cart.getTotalCost();
                for (Order order : cartItems) {
                    %>
                    <div class="donut">
                        <h3><%= order.getItem().getType() %> - <%= order.getItem().getFlavor() %>  </h3>
                        <h4><%= order.getQuantity()%> - $ <%= String.format("%.2f", order.getItem().getPrice() * order.getQuantity()) %></h4>
                    </div>
                    <%
                }
                    %>
                    <div class="checkout">
	                    <h3>Total: $<%= String.format("%.2f", total) %></h3>
	                    <button onclick="window.location.href='checkout.jsp'">Proceed to Checkout</button>
                    </div>
                    
            </div>
        </div>
    </body>
</html>
