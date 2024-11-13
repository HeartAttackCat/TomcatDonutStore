<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,io.htmlcss.model.Donut,io.htmlcss.db.DatabaseFetcher,io.htmlcss.db.DBFactory"%>
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
                background-color: #2D0055; /* Dark purple background */
                color: #DECAB6; /* Light text for contrast */
                min-height: 100vh;
                display: flex;
                flex-direction: column;
                justify-content: flex-start;
                align-items: center;
                padding: 20px;
            }

            /* Title Image */
            .title {
                width: 90%;
                max-width: 900px;
                border-radius: 12px;
                box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.5); /* Dark shadow */
                margin-bottom: 40px;
            }

            /* Outer container to hold all content */
            .outer {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
                max-width: 1200px;
                padding: 40px;
                background-color: #3A006F; /* Slightly lighter purple */
                border-radius: 12px;
                box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
            }

            /* Content wrapper for donut list */
            .wrapper {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); /* Responsive grid */
                gap: 30px;
                width: 100%;
            }

            /* Styling for each donut item */
            .donut {
                background-color: #771145; /* Darker purple for donut cards */
                padding: 20px;
                border-radius: 12px;
                box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.3); /* Dark shadow */
                text-align: center;
                transition: transform 0.2s ease-in-out;
                border: 2px solid #A372D1; /* Light purple border */
            }

			.donut h3 {
                font-size: 24px;
                font-weight: bold;
                color: #DECAB6; /* Golden accent */
                margin-bottom: 20px;
            }
            .donut h4 {
                font-size: 15px;
                font-weight: bold;
                color: #DECAB60; /* Golden accent */
                margin-bottom: 20px;
            }

            /* Button for product details */
            .donut button {
                background-color: #6F0081; /* Medium purple button */
                color: #F2E6FF; /* Light purple text */
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
                background-color: #57006A; /* Darker purple on hover */
            }

            /* Hover effect for donut cards */
            .donut:hover {
                transform: translateY(-10px); /* Lift card slightly on hover */
            }

            /* Responsive adjustments */
            @media (max-width: 768px) {
                .title {
                    width: 100%; /* Make title image take full width on smaller screens */
                }

                .wrapper {
                    grid-template-columns: 1fr; /* Single column layout on small screens */
                }
            }
        </style>
    </head>
    <body>
        <!-- Main Navigation Bar for Title Image -->
            <img decoding="async" class="title" src="./images/logo.png" alt="Donut Factory Title">

        <!-- Outer container for content -->
        <div class="outer">
            <div class="wrapper" id="content">
                <%! DatabaseFetcher db = DBFactory.getDatabaseFetcher(); %>
                <%
                List<Donut> donuts = db.getMenu();
                for (Donut donut : donuts) {
                    %>
                    <!-- Individual donut container styled with purple theme -->
                    <div class="donut">
                        <h3><%= donut.getType() %></h3>
                        <h4><%= donut.getFlavor() %> - $ <%= donut.getPrice() %></h4>

                        <!-- Button for product details redirection -->
                        <button onclick="window.location.href='product?product=<%= donut.getId() %>'">
                            View Details
                        </button>
                    </div>
                    <%
                }
                %>
            </div>
        </div>
    </body>
</html>
