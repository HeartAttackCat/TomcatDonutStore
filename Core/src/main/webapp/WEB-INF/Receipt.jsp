<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="io.htmlcss.api.*, io.htmlcss.db.*, io.htmlcss.model.*, java.util.Enumeration, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receipt</title>
</head>
<body>
		<%

		int oID = (int) request.getAttribute("oID");
		String date = (String) request.getAttribute("date");
		
		Cart cart = Cart.getOrder(oID, date);
		%>

	<h1>Your order!</h1>
	<p>Thank you <%= cart.getBuyer().getFirstName() %> <%=cart.getBuyer().getLastName() %>.
	Below is the break down of your perfect order. It will be ready soon and perhaps delievered to you.
	</p><br>
	<%
                for (Order order : cart.getItems()) {
                    %>
                    <% Donut donut = order.getItem(); %>
                    <!-- Individual donut container styled with purple theme -->
                    <div class="donut">
                        <h3><%= donut.getType() %> - <%= donut.getFlavor() %></h3>
                        <h4> Cost: $ <%= donut.getPrice() * order.getQuantity() %></h4>
                        <h4> Quantity: <%=order.getQuantity() %></h4>
                        
                    </div>
                    <%
                }
                %>
	<div>
	Total price: <%= cart.getTotalCost() %><br>
	Shipping to <%= cart.getBuyer().getZipCode() %> <%= cart.getBuyer().getAddress() %>
	</div>
</body>
</html>