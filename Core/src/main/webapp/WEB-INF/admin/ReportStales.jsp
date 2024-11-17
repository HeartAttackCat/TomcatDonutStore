<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="io.htmlcss.api.*, io.htmlcss.db.*, io.htmlcss.model.*, java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Stale Donut Report</title>
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

        /* Table styling */
        table {
            width: 90%;
            max-width: 800px;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #3A006F;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #A372D1;
            color: #E0E0E0;
        }

        th {
            background-color: #771145;
            font-weight: bold;
        }

        td {
            background-color: #571045;
        }

        /* Text styling */
        .report-summary {
            width: 90%;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #3A006F;
            border-radius: 12px;
            box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
            color: #DECAB6;
            font-size: 16px;
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
    <h1>Stale Donut Report</h1>

    <!-- Report Table -->
    <%
    int year = Integer.parseInt(request.getParameter("yr"));
    int month = Integer.parseInt(request.getParameter("mth"));
    int day = Integer.parseInt(request.getParameter("dy"));
    int back = Integer.parseInt(request.getParameter("bc"));

    float loss = 0.00f;

    DatabaseFetcher db = DBFactory.getDatabaseFetcher();
    ReportData stales = db.generateStaleReport(String.format("%d-%d-%d", year, month, day), back);
    %>

    <table>
        <tr>
            <th>Donut Type</th>
            <th>Donut Flavor</th>
            <th>Quantity Stale</th>
            <th>Subtotal</th>
        </tr>
        <%
        for (Enumeration<Integer> ids = stales.getDonutIds(); ids.hasMoreElements();) {
            int id = ids.nextElement();
            Donut donut = db.getDonut(id);

            out.write("<tr>");
            out.write("<td>" + donut.getType() + "</td>");
            out.write("<td>" + donut.getFlavor() + "</td>");
            out.write("<td>" + stales.getdQuant().get(id) + "</td>");
            float subtotal = stales.getdQuant().get(id) * donut.getPrice() * -1.0f;
            loss += subtotal;
            out.write("<td>" + String.format("%.2f", subtotal) + "</td>");
            out.write("</tr>");
        }
        %>
    </table>

    <!-- Report Summary -->
    <div class="report-summary">
        <p>Total Cost: <%= String.format("%.2f", loss) %></p>
        <p>Total Quantity: <%= stales.generateTotalQuantity() %></p>
    </div>
</body>
</html>
