<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="io.htmlcss.api.*, io.htmlcss.db.*, io.htmlcss.model.*, java.util.Enumeration, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Menu</title>
    <style>
    /* General reset and body styles */
    body {
        margin: 0;
        padding: 0;
        font-family: 'Arial', sans-serif;
        background-color: #2D0055;
        color: #E0E0E0;
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

    /* Title styling */
    h1 {
        font-size: 28px;
        color: #DECAB6;
        margin-bottom: 20px;
        text-align: center;
    }

    /* Form styling */
    form {
        width: 90%;
        max-width: 800px;
        margin: 20px auto;
        background-color: #3A006F;
        padding: 20px;
        border-radius: 12px;
        box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
        display: flex;
        flex-direction: column;
        gap: 15px;
    }

    label {
        font-size: 16px;
        color: #DECAB6;
    }

    input[type="text"] {
        width: 95%; /* Reduced width */
        padding: 10px;
        font-size: 14px;
        border: 2px solid #A372D1;
        border-radius: 8px;
        background-color: #771145;
        color: #E0E0E0;
    }

    textarea {
        width: 95%; /* Adjusted width */
        padding: 10px;
        font-size: 14px;
        border: 2px solid #A372D1;
        border-radius: 8px;
        background-color: #771145;
        color: #E0E0E0;
    }

    input[type="submit"] {
        width: auto;
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
    }

    input[type="submit"]:hover {
        background-color: #57006A;
        color: #F2E6FF;
    }

    /* Donut container styling */
    .donut {
        width: 90%;
        max-width: 800px;
        background-color: #3A006F;
        margin: 20px auto;
        padding: 20px;
        border-radius: 12px;
        box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
    }

    .donut h3 {
        color: #DECAB6;
        margin-bottom: 10px;
    }

    .donut form {
        margin-bottom: 10px;
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
    <h1>Edit Menu</h1>

    <!-- Add Item Section -->
    <div>
        <h1>Add Item</h1>
        <form method="get" action="./admin?command=menu">
            <label>Donut type:</label>
            <input type="text" name="type" required>
            <label>Donut flavor:</label>
            <input type="text" name="flavor" required>
            <label>Donut price:</label>
            <input type="text" name="price" required>
            <label>Donut description:</label>
            <textarea name="desc" rows="5" cols="100" required></textarea>
            <input type="hidden" value="menu" name="command">
            <input type="hidden" value="add" name="modCommand">
            <input type="submit" value="Add Donut">
        </form>
    </div>

    <!-- Modify Menu Section -->
    <div>
        <h1>Modify Menu</h1>
        <%
        DatabaseFetcher db = DBFactory.getDatabaseFetcher();
        List<Donut> donuts = db.getMenu();
        for (Donut donut : donuts) {
        %>
        <div class="donut">
            <h3><%= donut.getType() %>, <%= donut.getFlavor() %></h3>
            <form method="get" action="./admin?command=menu">
                <label>Donut type:</label>
                <input type="hidden" name="id" value="<%= donut.getId() %>">
                <input type="text" name="type" value="<%= donut.getType() %>" required>
                <label>Donut flavor:</label>
                <input type="text" name="flavor" value="<%= donut.getFlavor() %>" required>
                <label>Donut price:</label>
                <input type="text" name="price" value="<%= donut.getPrice() %>" required>
                <label>Donut description:</label>
                <textarea name="desc" rows="5" cols="100" required><%= donut.getDescription() %></textarea>
                <input type="hidden" value="menu" name="command">
                <input type="hidden" value="modify" name="modCommand">
                <input type="submit" value="Modify Donut">
            </form>
            <form method="get" action="./admin?command=menu">
                <input type="hidden" value="menu" name="command">
                <input type="hidden" value="delete" name="modCommand">
                <input type="hidden" name="id" value="<%= donut.getId() %>">
                <input type="submit" value="Delete Donut">
            </form>
        </div>
        <%
        }
        %>
    </div>
</body>
</html>
