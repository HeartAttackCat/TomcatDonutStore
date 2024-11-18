<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="io.htmlcss.api.*, io.htmlcss.db.*, io.htmlcss.model.*, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tray Management</title>
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

    /* Page title styling */
    h1, h2 {
        text-align: center;
        color: #DECAB6;
        margin: 20px 0;
    }

    h1 {
        font-size: 28px;
    }

    h2 {
        font-size: 22px;
    }

    /* Form styling */
    form {
        text-align: center;
        margin: 20px 0;
    }

    select {
        background-color: #3A006F;
        color: #DECAB6;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #DECAB6;
        border-radius: 8px;
        outline: none;
        width: 250px;
    }

    select:hover {
        background-color: #57006A;
        color: #F2E6FF;
    }

    button {
        background-color: #6F0081;
        color: #F2E6FF;
        border: none;
        padding: 12px 20px;
        font-size: 16px;
        font-weight: bold;
        border-radius: 8px;
        cursor: pointer;
        box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
        transition: background-color 0.3s ease;
        margin-top: 10px;
    }

    button:hover {
        background-color: #57006A;
    }

    /* Outer container for trays */
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
        margin: 20px auto;
    }

    /* Content wrapper for trays */
    .wrapper {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 30px;
        width: 100%;
    }

    /* Individual tray styling */
    .donut {
        background-color: #771145;
        padding: 20px;
        border-radius: 12px;
        box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.3);
        text-align: center;
        transition: transform 0.2s ease-in-out;
        border: 2px solid #A372D1;
    }

    .donut h3, .donut h4 {
        font-size: 18px;
        color: #DECAB6;
    }

    .donut:hover {
        transform: translateY(-10px);
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

    <!-- Page Titles -->
    <h1>Tray Management</h1>
    <h2>Add Tray</h2>

    <!-- Form to Add Tray -->
    <form method="get" action="./employee?command=tray">
        <select name="donuts" id="donuts">
            <option disabled selected value>Pick a donut</option>
            <% for (Donut i : Donut.getMenu()) { %>
                <option value="<%= i.getId() %>">
                    <%= i.getType() %>, <%= i.getFlavor() %>
                </option>
            <% } %>
        </select>
        <input type="hidden" value="trayAdd" name="command">
        <button type="submit">Add Tray</button>
    </form>

    <!-- Ready Trays Section -->
    <%
        List<Tray> trays = (List<Tray>) request.getSession().getAttribute("inv");
        if (trays != null && !trays.isEmpty()) {
    %>
    <h2>Ready Trays</h2>
    <div class="outer">
        <div class="wrapper">
            <% for (Tray i : trays) { %>
                <div class="donut">
                    <% Donut donut = Donut.getDonut(i.getDonutID()); %>
                    <h3><%= donut.getType() %>, <%= donut.getFlavor() %></h3>
                    <h4>Quantity: <%= i.getQuantity() %></h4>
                </div>
            <% } %>
        </div>
    </div>
    <% } else { %>
        <h2>No Ready Trays Available</h2>
    <% } %>

    <!-- Baking Trays Section -->
    <%
        List<Tray> bakingTrays = (List<Tray>) request.getSession().getAttribute("baking");
        if (bakingTrays != null && !bakingTrays.isEmpty()) {
    %>
    <h2>Baking Trays</h2>
    <div class="outer">
        <div class="wrapper">
            <% for (Tray i : bakingTrays) { %>
                <div class="donut">
                    <% Donut donut = Donut.getDonut(i.getDonutID()); %>
                    <h3><%= donut.getType() %>, <%= donut.getFlavor() %></h3>
                    <h4>Quantity: <%= i.getQuantity() %></h4>
                </div>
            <% } %>
        </div>
    </div>
    <% } else { %>  
        <h2>No Baking Trays Available</h2>
    <% } %>

</body>
</html>
