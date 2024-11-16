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
<h1>Add Tray</h1>
<div>
           	<form method="get" action = "./employee?command=tray">
				<select name="donuts" id="donuts">
				    <option disabled selected value>Pick a donut</option>
		            <%
                	for (Donut i : Donut.getMenu()) {
                    %>
                    <option value=<%=i.getId() %>><%=i.getType() %>, <%=i.getFlavor() %></option>
                    <%} %>
				</select>
           		<input type="hidden" value="trayAdd" name="command">
           		<button type="submit" >Add Tray</button>
           	</form>
</div>
<h1>Ready Trays</h1><br>

                <%! List<Tray> trays = Tray.getInventoryTrays(); %>
                
                <%
                for (Tray i : trays) {
                    %>
                    <!-- Individual donut container styled with purple theme -->
                    <div class="donut">
                    	<% Donut donut = Donut.getDonut(i.getDonutID()); %>
                        <h3><%=donut.getType() %>, <%= donut.getFlavor() %> </h3>
                        Quantity<%=i.getQuantity()%>
                            
                    </div>
                    <%
                }
                %>
<h1>Baking Trays</h1>
                <%! List<Tray> bakingTrays = Tray.getBakingTrays(); %>
                
                <%
                for (Tray i : bakingTrays) {
                    %>
                    <!-- Individual donut container styled with purple theme -->
                    <div class="donut">
                    	<% Donut donut = Donut.getDonut(i.getDonutID()); %>
                        <h3><%=donut.getType() %>, <%= donut.getFlavor() %> </h3>
                        <h4><%=i.getQuantity()%></h4>
                            
                    </div>
                    <%
                }
                %>
</body>
</html>