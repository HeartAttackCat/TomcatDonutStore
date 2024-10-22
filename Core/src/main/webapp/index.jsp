<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,io.htmlcss.model.Donut,io.htmlcss.db.DatabaseFetcher,io.htmlcss.db.DBFactory"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Donut Factory</title>
        <meta name="description" content="Order donuts from Grill's Donut Factory.">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="./index.css">
    </head>
    <body>
        <div class = "mnb">
        	<picture>
        		<img decoding="async"
        		class = "title"
        		src="./images/title.png">
        	</picture>
        </div>
        <div class="outer">
        	<div class="wrapper" id="content">
        		<%! DatabaseFetcher db = DBFactory.getDatabaseFetcher();%>  
        		<%
        		ArrayList<Donut> donuts = db.getMenu();
        		for (Donut donut : donuts) {
        			out.println("<div class=\"donut\">");
        			out.println("<h3>");
        			out.write(donut.getType());
        			out.println("</h3>");
        			out.println("</div>");
        		}
        		%>
        	</div>
        </div>
    </body>
</html>
