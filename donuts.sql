Create Database donutFactory; 
Use donutFactory;
Create table donuts (id int, dType varchar (255), flavor varchar(255), price float);

Create table inventory (id int, quantity int, donutID int, bakingTime datetime, expireTime date);

Create table dOrder (orderNumber int, itemID int,purchaseDate varchar(255), customerName varchar (255), quantity int, price float, totalQuant int, totalPrice float);

INSERT INTO donuts (id, dType, flavor, price) VALUES (1, "Raised" , "Glazed", 1.00), (2, "Raised" , "Sugar", 1.00), (3, "Raised" , "Chocolate", 2.00),
(4, "Cake" , "Plain", 0.50), (5, "Cake" , "Chocolate", 2.50), (6, "Cake" , "Sugar", 1.00), (7, "Filled" , "Lemon", 1.00), (8, "Filled" , "Grape", 1.50), (9, "Filled" , "Custard", 1.50);

INSERT INTO inventory (id, quantity, donutID, bakingTime, expireTime) VALUES (1, 20, 4, "2024-10-12 7:00:00", "2024-10-14"),
(2, 20, 7, "2024-10-12 7:00:00", "2024-10-14"), (3, 20, 1, "2024-10-12 7:00:00", "2024-10-14"), (4, 20, 6, "2024-10-12 7:00:00", "2024-10-14 7:00:00");


Insert Into dOrder (orderNumber, itemID, purchaseDate, customerName, quantity, price, totalQuant, totalPrice) VALUES (1, 2, "2024-10-10 13:00:00", "John Smith", 10,  10.00, 20, 15.00), 
(1, 4, "2024-10-10 13:00:00", "John Smith", 10,  5.00, 20, 15.00), (2, 3, "2024-10-9 13:00:00", "John Smith", 1,  2.00, 1, 2.0);

select * from donuts;

select * from inventory;

select * from dOrder;

drop table inventory, dOrder, donuts;
drop database donutFactory;