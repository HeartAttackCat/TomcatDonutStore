<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product Details</title>
		<style>
			/* General reset and body styles */
			body {
				margin: 0;
				padding: 0;
				font-family: 'Arial', sans-serif;
				background-color: #2D0055; /* Dark purple background */
				color: #E0E0E0; /* Light text for contrast */
				height: 100vh;
				display: flex;
				justify-content: center;
				align-items: center;
			}

			/* Main container for layout */
			.container {
				display: flex;
				justify-content: space-between;
				align-items: flex-start;
				gap: 40px;
				padding: 40px;
				width: 90%;
				max-width: 1200px;
				background-color: #3A006F; /* Slightly lighter purple */
				border-radius: 12px;
				box-shadow: 0px 10px 25px rgba(0, 0, 0, 0.5);
			}
			
			/* Left section for name and image */
			.left-section {
				display: flex;
				flex-direction: column;
				gap: 20px;
				align-items: center;
				width: 50%;
			}

			/* Style for the product name */
			.product-name {
				width: 100%;
				padding: 15px;
				border: 2px solid #A372D1; /* Light purple border */
				text-align: center;
				font-size: 24px;
				font-weight: bold;
				background-color: #771145; /* Darker purple */
				color: #DECAB6;
				border-radius: 8px;
				box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
			}

			/* Style for the image section */
			.image-section {
				display: flex;
				align-items: center;
				justify-content: center;
				border-radius: 8px;
				width: 100%;
				max-width: 400px; /* Set a max width to avoid the div being larger than the image */
				overflow: hidden; /* Prevent the div from exceeding image size */
			}

			.image-section img {
				max-width: 100%;
				height: auto; /* Maintain the aspect ratio */
				border-radius: 8px;
			}

			/* Right section for the product details */
			.feature-section {
				width: 50%;
				border: 2px solid #A372D1;
				padding: 20px;
				background-color: #771145; /* Darker purple */
				border-radius: 8px;
				box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
			}

			.feature-section p {
				font-size: 18px;
				line-height: 1.5;
				margin: 10px 0;
				color: #DECAB6; /* Light gray text */
			}

			/* Typography styling for headings and paragraphs */
			h1, h2 {
				color: #DECAB6; /* Golden accent */
				margin: 0 0 15px 0;
			}

			/* Responsive adjustments */
			@media (max-width: 768px) {
				.container {
					flex-direction: column;
					align-items: center;
					width: 95%;
				}

				.left-section, .feature-section {
					width: 100%;
				}

				.image-section {
					max-width: 100%;
				}
			}
		</style>
	</head>
	<body>
		<%! io.htmlcss.db.DatabaseFetcher db = io.htmlcss.db.DBFactory.getDatabaseFetcher(); %>
		<%! io.htmlcss.model.Donut donut; %>
		<%
		donut = db.getDonut(Integer.parseInt(request.getParameter("product")));
		%>
		
		<!-- Main Container -->
		<div class="container">
			
			<!-- Left section holding name and image vertically stacked -->
			<div class="left-section">
				<!-- Product name -->
				<div class="product-name">
					<h1><%= donut.getType() %> Donut</h1>
				</div>

				<!-- Product image -->
				<div class="image-section">
					<img src="./images/donut.png" alt="<%= donut.getType() %> Image">
				</div>
			</div>
			
			<!-- Right section for product features and description -->
			<div class="feature-section">
				<h2>Features & Details</h2>
				<p>Price: $<%= donut.getPrice() %> </p>
				<p>Flavor: <%= donut.getFlavor() %></p>
				<p>Calories: -5 kCal</p>
				<p>Description: <%= donut.getDescription() %></p>
			</div>
		</div>
	</body>
</html>
