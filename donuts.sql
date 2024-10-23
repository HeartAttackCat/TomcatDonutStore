Create Database donutFactory; 
Use donutFactory;
Create table donuts (id int, dType varchar (256), flavor varchar(256), price float, donutDesc varchar(512), img varchar(256));

Create table inventory (id int, quantity int, donutID int, startBakingTime datetime, endBakingTime datetime, expireTime date);

Create table dOrder (orderNumber int, itemID int,purchaseDate varchar(256), customerName varchar (256), quantity int, price float, totalQuant int, totalPrice float);

INSERT INTO donuts (id, dType, flavor, price, donutDesc, img) VALUES 
(1, "Raised" , "Glazed", 0.69, "A rather generic choice but the choice of heros. Topped with a Glazed coating.", "raisedGlazed"),
(2, "Raised" , "Sugar", 0.79, "A raised donut topped with sugar. Often considered the best donut.", "raisedSugar"),
(3, "Raised" , "Chocolate", 0.99, "An awful choice that we provide, but if you're a chocolate fan we got you.", "raiseChocolate"),
(4, "Cake" , "Plain", 0.99, "The cake donut a great choice. No toppings but that's okay the cake flavor is perfect!", "cakePlain"),
(5, "Cake" , "Chocolate", 2.99, "The failure of cake donuts...", "cakeChocolate"),
(6, "Cake" , "Sugar", 0.99, "The sugar donut 2.0 they say. Gifted from the heavens above now in an edible form.", "cakeSugar"),
(7, "Filled" , "Lemon", 1.99, "A classic donut with our classic lemon filling. Made from the most premium lemons.", "filledLemon"),
(8, "Filled" , "Grape", 1.99 , "A great donut if you enjoy the grape flavor.", "filledGrape"),
(9, "Filled" , "Custard", 1.99, "A donut for wild side of you. Our custard filling is definitely filling.", "filledCustard");

INSERT INTO inventory (id, quantity, donutID, startBakingTime, endBakingTime, expireTime) VALUES (1, 20, 4, "2024-10-12 5:00:00", "2024-10-12 7:00:00", "2024-10-14"),
(2, 20, 7, "2024-10-12 5:00:00", "2024-10-12 7:00:00", "2024-10-14"), (3, 20, 1, "2024-10-12 5:00:00","2024-10-12 7:00:00", "2024-10-14");


Insert Into dOrder (orderNumber, itemID, purchaseDate, customerName, quantity, price, totalQuant, totalPrice) VALUES (1, 2, "2024-10-10 13:00:00", "John Smith", 10,  10.00, 20, 15.00), 
(1, 4, "2024-10-10 13:00:00", "John Smith", 10,  5.00, 20, 15.00), (2, 3, "2024-10-9 13:00:00", "John Smith", 1,  2.00, 1, 2.0);

select * from donuts;

select * from inventory;

select * from dOrder;