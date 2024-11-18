<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="io.htmlcss.api.*, io.htmlcss.db.*, io.htmlcss.model.Cart, io.htmlcss.model.Order, io.htmlcss.model.Donut" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Receipt</title>
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

        /* Container for the receipt details */
        .receipt-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 40px;
            background-color: #3A006F;
            border-radius: 12px;
            box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
            width: 90%;
            max-width: 600px;
            text-align: center;
        }

        /* Title styling */
        h1 {
            font-size: 28px;
            color: #DECAB6;
            margin-bottom: 20px;
        }

        /* Donut item styling */
        .donut {
        	width: 85%
            border: 2px solid #A372D1;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 15px;
            background-color: #771145;
            color: #E0E0E0;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
            width: 100%;
        }

        /* Total details styling */
        .total-details {
            margin-top: 20px;
            font-size: 18px;
            font-weight: bold;
            color: #DECAB6;
        }
    </style>
</head>
<body>
    <!-- Logo at the top -->
    <div class="logo-container">
        <a href="index.jsp">
            <img src="./images/logo.png" alt="Company Logo">
        </a>
        <h1>Receipt</h1>
    </div>

    <!-- Receipt Container -->
    <div class="receipt-container">
        <%
            int oID = (int) request.getAttribute("oID");
            String date = (String) request.getAttribute("date");
            Cart cart = Cart.getOrder(oID, date);
        %>
        <p>
            Thank you <%= cart.getBuyer().getFirstName() %> <%= cart.getBuyer().getLastName() %>.
            Below is the breakdown of your perfect order. It will be ready soon and possibly delivered to you!
        </p>
        <div>
            <% for (Order order : cart.getItems()) { %>
                <% Donut donut = order.getItem(); %>
                <div class="donut">
                    <h3><%= donut.getType() %> - <%= donut.getFlavor() %></h3>
                    <h4>Cost: $<%= donut.getPrice() * order.getQuantity() %></h4>
                    <h4>Quantity: <%= order.getQuantity() %></h4>
                </div>
            <% } %>
        </div>
        <div class="total-details">
            Total Price: $<%= cart.getTotalCost() %><br>
            Shipping to: <%= cart.getBuyer().getAddress() %>, <%= cart.getBuyer().getZipCode() %>
        </div>
    </div>
</body>
</html>