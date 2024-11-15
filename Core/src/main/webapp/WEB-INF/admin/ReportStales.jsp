<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="io.htmlcss.api.*, io.htmlcss.db.*, io.htmlcss.model.*, java.util.Enumeration" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Stale Donut Report</title>
	</head>
	<body>
		<%
		int year = Integer.parseInt(request.getParameter("yr"));
		int month = Integer.parseInt(request.getParameter("mth"));
		int day = Integer.parseInt(request.getParameter("dy"));
		
		int back = Integer.parseInt(request.getParameter("bc"));
		
		float loss = 0.00f;
		
		DatabaseFetcher db = DBFactory.getDatabaseFetcher();
		ReportData stales = db.generateStaleReport(String.format("%d-%d-%d", year, month, day), back);
		out.write("");
		%>
		
		<table>
			<tr>
				<th>Donut Type</th>
				<th>Donut Flavor</th>
				<th>Quantity Sold</th>
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
				loss += stales.getdQuant().get(id) * donut.getPrice() * -1.0f;
				out.write("<td>" + String.format("%.2f", stales.getdQuant().get(id) * donut.getPrice() * -1.0f) + "</td>");
				out.write("</tr>");
			}
			%>
			
		</table>
		<br>
		<text>Total Cost: <% out.write(loss + ""); %></text><br>
		<text>Total Quantity: <% out.write(stales.generateTotalQuantity() + ""); %></text>
	</body>
</html>