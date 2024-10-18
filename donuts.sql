Create Database donutFactory; 
use donutFactory;
Create table donuts (id int, dType varchar (255), dSubType varchar(255), price float);

Create table inv (id int, quantity int, donutID int, bakingTime varchar(255), expireTime varchar(255));

Create table dOrder (orderNumber int, itemID int ,purchaseDate varchar(255), customerName varchar (255), quantity int, price float, totalQuant int, totalPrice float);

INSERT INTO donutfactory.donuts (id, dType, dSubType, price) VALUES (1, "Raised" , "Glazed", 1.00), (2, "Raised" , "Sugar", 1.00), (3, "Raised" , "Chocolate", 2.00),
(4, "Cake" , "Plain", 0.50), (5, "Cake" , "Chocolate", 2.50), (6, "Cake" , "Sugar", 1.00), (7, "Filled" , "Lemon", 1.00), (8, "Filled" , "Grape", 1.50), (9, "Filled" , "Custard", 1.50);

INSERT INTO donutfactory.inv (id, quantity, donutID, bakingTime, expireTime) VALUES (1, 20, 4, "2024-10-12 7:00:00", "2024-10-14 7:00:00"),
(2, 20, 7, "2024-10-12 7:00:00", "2024-10-14 7:00:00"), (3, 20, 1, "2024-10-12 7:00:00", "2024-10-14 7:00:00"), (4, 20, 6, "2024-10-12 7:00:00", "2024-10-14 7:00:00");


INSERT INTO donutfactory.dOrder (orderNumber, itemID, purchaseDate, customerName, quantity, price, totalQuant, totalPrice) VALUES (1, 2, "2024-10-10 13:00:00", "John Smith", 10,  10.00, 20, 15.00), 
(1, 4, "2024-10-10 13:00:00", "John Smith", 10,  5.00, 20, 15.00), (2, 3, "2024-10-9 13:00:00", "John Smith", 1,  2.00, 1, 2.0);

DROP table inv, dOrder, donuts;
drop database donutfactory;