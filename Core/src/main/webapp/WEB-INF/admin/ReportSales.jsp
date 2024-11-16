<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="io.htmlcss.api.*, io.htmlcss.db.*, io.htmlcss.model.*, java.util.Enumeration" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Donut Sales Report</title>
	</head>
	<body>
		<%
		int year = Integer.parseInt(request.getParameter("yr"));
		int month = Integer.parseInt(request.getParameter("mth"));
		int day = Integer.parseInt(request.getParameter("dy"));
		
		int back = Integer.parseInt(request.getParameter("bc"));
		
		DatabaseFetcher db = DBFactory.getDatabaseFetcher();
		ReportDataSales sales = (ReportDataSales) db.generateSalesReport(String.format("%d-%d-%d", year, month, day), back);
		%>
		
		<table>
			<tr>
				<th>Donut Type</th>
				<th>Donut Flavor</th>
				<th>Quantity Sold</th>
				<th>Subtotal</th>
			</tr>
			
			<%
			for (Enumeration<Integer> ids = sales.getDonutIds(); ids.hasMoreElements();) {
				int id = ids.nextElement();
				Donut donut = db.getDonut(id);
				
				out.write("<tr>");
				out.write("<td>" + donut.getType() + "</td>");
				out.write("<td>" + donut.getFlavor() + "</td>");
				out.write("<td>" + sales.getdQuant().get(id) + "</td>");
				out.write("<td>" + sales.getdQuant().get(id) * donut.getPrice() + "</td>");
				out.write("</tr>");
			}
			%>
			
		</table>
		
		<br>
		<text>Total Profit: <% out.write(String.format("%.2f", sales.generateTotalSales())); %></text><br>
		<text>Total Quantity: <% out.write(sales.generateTotalQuantity() + ""); %></text>
	</body>
</html>