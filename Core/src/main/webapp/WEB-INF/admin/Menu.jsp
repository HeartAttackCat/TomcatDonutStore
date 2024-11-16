<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="io.htmlcss.api.*, io.htmlcss.db.*, io.htmlcss.model.*, java.util.Enumeration, java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edit Menu</title>
	</head>
	<body>
		<div>
		<h1>Add Item</h1>
		<form method="get" action="./admin?command=menu">
		<label>Donut type:</label>
		<input type="text" name="type" >
		<label>Donut flavor:</label>
		<input type="text" name="flavor">
		<label>Donut price:</label>
		<input type="text" name="price"><br>
		<label>Donut description:</label><br>
		<textarea name="desc" rows=5 cols=100></textarea><br>
		<input type="hidden" value="menu" name="command">
		<input type="hidden" value="add" name="modCommand">
		<input type="submit" value="Add Donut">
		</form>
		</div>
		<br>
		<br>
		<br>
		<div>
		Modify Menu<br>
                <%! DatabaseFetcher db = DBFactory.getDatabaseFetcher(); %>
                <%
                List<Donut> donuts = db.getMenu();
                for (Donut donut : donuts) {
                    %>
                    <!-- Individual donut container styled with purple theme -->
                    <div class="donut">
                        <h3><%= donut.getType() %>, <%= donut.getFlavor() %></h3>
                        
						<form method="get" action="./admin?command=menu">
						<label>Donut type:</label>
						<input type="hidden" value=<%= donut.getId() %>>
						<input type="text" name="type" value=<%= donut.getType() %>>
						<label>Donut flavor:</label>
						<input type="text" name="flavor" value=<%= donut.getFlavor() %>>
						<label>Donut price:</label>
						<input type="text" name="price" value=<%= donut.getPrice() %>>
						<br>
						<label>Donut description:</label><br>
						<textarea name="desc" rows=5 cols=100><%=donut.getDescription() %></textarea>
						<br>
						<input type="hidden" value="menu" name="command">
						<input type="hidden" value="modify" name="modCommand">
						<input type="submit" value="Modify Donut">
						</form>
						<form method="get" action="./admin?command=menu">
						
						<input type="hidden" value="menu" name="command">
						<input type="hidden" value="delete" name="modCommand">
						<input type="hidden" value=<%= donut.getId() %>>
						<input type="submit" value="Delete Donut">
						</form>
                    </div>
                    <%
                }
                %>
		</div>
	</body>
</html>