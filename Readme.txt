--- README ---

Contributors and Roles:

Kassidy Maberry  -- Backend Dev
Korbin Shelley   -- Backend Dev
Hunter Lane      -- Frontend Dev
Benjamin Mannl   -- Frontend Dev

--------------

We were tasked to create a website for a donut store. The store was meant to be run on a tomcat server.

The store requirements included the following...

Customer Functionality:

Allow customers to explore the menu, select products, and place an order.
Employee Functionality:

Present the orders to the employees.
Enable employees to update the order status to "processed."
Allow employees to enter inventory information into the application.
Administrator Functionality:

Update the menu (add, edit, delete, and view items).
View administration reports.
Doughnut Types:

Raised:
	Glazed
	Sugar
	Chocolate
Cake:
	Plain
	Chocolate
	Sugar
Filled:
	Lemon
	Grape
	Custard

Preparation Process:

Employees prepare 20 doughnuts of each type at 5:00 am daily.
Counter trays are numbered for tracking baking time and available quantity.
Each tray is filled with a single doughnut type, and baking time is marked.
Order Management:

Orders begin at 7:00 am.
Each order includes:
Automatically generated number
Date
Item number
Name
Quantity
Price
Total quantity
Total price
The application generates a formatted receipt.
Order numbers must be unique for the same date.
The factory serves up to 200 doughnuts daily (limit is flexible and can be updated by the administrator).
Stale Doughnut Management:

Stale doughnuts (over two days old) are removed from the store at the end of each day.
The inventory is updated accordingly.

Reports:
Weekly, monthly, and yearly sales summaries, including:
Doughnut types
Price
Quantity
Total quantity
Total price
Weekly, monthly, and yearly summaries of stale doughnuts.

----------------

We used HTML, CSS, Java, JSPs, and mySQL to bring the website to life.

Each Java class holds the backend functionality to move, add, remove, and more to affect things like trays, donuts, carts, and more.
 
The majority of the backend code was written by Kassidy and Korbin. Kassidy worked a lot with database updating and backend code, while Korbin worked on a large chunk of variety backend code.

The frontend side was aimed to give a pleasant and easy to understand visualization. The majority of the front end was written by Ben Mannal and Hunter Lane. Ben Mannal worked on most of the JSPs and styling for all pages. Hunter Lane worked on creating the Servlets and some JSPs.

All together it culminates into Grillys Donut Shop

Please Enjoy!


