<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="io.htmlcss.api.*, io.htmlcss.db.*, io.htmlcss.model.*, java.util.Enumeration, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Orders</h1><br>

                <%! List<Cart> orders = Cart.getActiveOrders(); %>
                
                <%
                for (Cart order : orders) {
                    %>
                    <!-- Individual donut container styled with purple theme -->
                    <div class="donut">
                    
                        <h3><%= order.getBuyer().getFirstName() %>, <%= order.getBuyer().getLastName() %></h3>
                        	Status: <%=order.getStatus() %>
                            <%
                			for (Order item: order.getItems()) {
                            %>
                            <div>
                            <%= item.getItem().getType() %>, <%= item.getItem().getFlavor() %><br> Quantity: <%= item.getQuantity()%>
                            <br>
                            </div>
						<%} %>
						<form method="get" action="./employee">
						
						<input type="hidden" value="orderComplete" name="command">
						<input type="hidden" name="id" value=<%= order.getOrderID() %>>
						<input type="hidden" name="date" value=<%= order.getDate() %>>
						<input type="submit" value="Complete Order">
						</form>
                    </div>
                    <%
                }
                %>
		</div>
</body>
</html>