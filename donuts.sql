Create Database donutFactory; 
Use donutFactory;
Create table donuts (id int NOT NULL AUTO_INCREMENT, dType varchar (256), flavor varchar(256), price float, donutDesc varchar(512), img varchar(256), primary key (id));

Create table bakingDonuts (id int NOT NULL AUTO_INCREMENT, quantity int, donutID int, startBakingTime datetime, endBakingTime datetime, primary key (id));

Create table inventory (id int, quantity int, donutID int, expireTime date);

Create table dOrder (orderID int, itemID int,purchaseDate date, customerID int, quantity int, price float, totalQuant int, totalPrice float, complete boolean);

Create table customerInfo (customerID int NOT NULL AUTO_INCREMENT, firstName varchar(256), lastName varchar(256), zipCode int, customerAddress varchar(256), phoneNumber varchar(256), email varchar(256), cardID varchar(256), primary key (customerID)) ;

INSERT INTO customerInfo (firstName, lastName, zipCode, customerAddress, phoneNumber, email, cardID) VALUES 
( "John", "Goettsche", 88220, "Dirt Lane", "(505)-262-1460", "Sandwhich@bread.com", "1234567890123456"),
( "Ramaya", "Ramaya", 88324, "Road", "(505)-314-1518", "Induction@nmt.com", "0852582479531379"),
( "Goku", "Son", 63472, "7 Dragon Balls Road", "(505)-267-7235", "Goku@gmail.com", "94341372362177345"),
( "Churro", "Churrito", 83472, "Socorro Highway", "(623)-262-0634", "Cat@Ethernet.com", "63233723723723"),
( "Luigi", "Mario", 37234, "Pizza Lane", "(505)-236-1523", "Luigi@Pipes.com", "2346236226758456"),
( "Jack", "Donuts", 26236, "Donut Champion Road", "(505)-269-1362", "MrDonut@gmail.com", "2472383467346");

INSERT INTO donuts (id, dType, flavor, price, donutDesc, img) VALUES 
(1, "Raised" , "Glazed", 0.69, "Our classic choice! The glazed donut, what is there not to like. The classic good guy of every story. Not too adventurous but always with that glazed goodness. For all newcomers, we recommend starting here as we know our donuts can be overbearing on the taste buds. Think of it as a form of training for our future challenges (The filled lemon donut.). However, the glaze donut is perfect, some scholars even argue that it was gifted from the Heavens above. It's just too perfect to be man made.", "raisedGlazed"),
(2, "Raised" , "Sugar", 0.79, "A bit of a wild choice. Your friends may have not ordered this donut before but trust me the sugary goodness is always worth the temptations. Never deny yourself the privilege of enjoying a Grilly's Sugar Donut. Our sugar is a notch above the rest, sourced from Cuban sugar cane farms (supply varies). A bit of a wild choice, but we believe the Cuban Sugar is worth the expensive price we must pay to produce such a delight. Buy it while supplies last.", "raisedSugar"),
(3, "Raised" , "Chocolate", 0.99, "Our raised chocolate donuts made for those who suffer from enjoying chocolate. We source chocolate ILLEGALLY and of questionable source (It may be the company known as Nestle.). You brave soul who wishes to devour the chocolate, go forth and enjoy our worst menu item. May it be enjoyable until the end, however I doubt it'll be so. One of the few donuts, the writer of descriptions wishes we could purge from our menu, but the donut owner refuses to listen to my pleas.", "raiseChocolate"),
(4, "Cake" , "Plain", 0.99, "The cake donut but plain. Another typical choice of a donut beginner. It may seem a bit wild, but really it's a cake donut, there's nothing special where's the lemon or grape? Perhaps you may not wish to be wild, then the cake plain is there for you. When the raised donut is not enough to satisfy you, think of the cake plain. Baked with our trade secret cake donut recipe. Under constant threat of theft but don't worry we guard hard, and we cook these harder.", "cakePlain"),
(5, "Cake" , "Chocolate", 2.99, "The failure of cake donuts. No, the failure of donuts itself. Nobody could have made a worse donut, was the chocolate not bad enough? Yet you come here after reading about the Chocolate, knowing the dangers. I can't stop you if this is the path you wish to take. No amounts of pleading can save you punishing our bakers when we must prepare such a batch. This is another donut I wish we could remove, but boss said no.", "cakeChocolate"),
(6, "Cake" , "Sugar", 0.99, "If our Cuban Sugar Cane Donut was not enough, don't worry, we have the sugar cake donut. It's not just a raised donut now it's combined with our powerful cake recipe. Making a deliciously deadly combo for anyone who wishes to eat a Grilly Donuts. Too delicious, some say, our taste tester has not been the same since. He's came back every day since. This has been happening with other customers who try Cake Sugar Donut. Try them while supplies last.", "cakeSugar"),
(7, "Filled" , "Lemon", 1.99, "The ultimate donut nobody can deny our lemon donuts the praise they deserve. Made not lemons but limes instead. A much more flavorful alternative. Why name it the lemon donut because only a true lemon aficionado can figure out we used lime. This donut is an award-winning donut, it is the reason we exist. Humanity was in a dark age until the release of the lemon filled donut and ours is so good it's brought upon another golden age. Try it before its too late.", "filledLemon"),
(8, "Filled" , "Grape", 1.99 , "The grape filled donut sourced from the most premium hydrated raisins. Ask anybody what the top 3 donuts at Grilly's donuts are, and number 2 or 3 are almost always sometimes listed as the grape filled donut. While it's not the lemon filled donut, it still a good donut. We spent years studying the way of the grape donut. Perfecting the recipe to achieve its consistently heavenly taste, as our patreons commonly describe. Purchase it before someone else does, they sell fast.", "filledGrape"),
(9, "Filled" , "Custard", 1.99, "The custard donut for those who want the danger. Those who want the adventure of what makes a donut. Our ingredients are questionable, but our recipe is bold. The custard flavor will hit your taste buds like nothing before it. This donut when the recipe was finalized was a revolutionary recipe in Flavor Town. We're the only place that is brave enough to bring this recipe out of Flavor Town and directly to our store. Be bold and buy our custard.", "filledCustard");

INSERT INTO bakingDonuts (id, quantity, donutID, startBakingTime, endBakingTime) VALUES (8, 20, 4, "2024-10-12 5:00:00", "2024-10-12 7:00:00"),
(9, 20, 7, "2024-10-12 5:00:00", "2024-10-12 7:00:00"), (10, 20, 1, "2024-10-12 5:00:00","2024-10-12 7:00:00");

INSERT INTO inventory (id, quantity, donutID, expireTime) VALUES (1, 10, 1, "2024-10-11"), 
(2, 5, 2, "2024-10-11"), (3, 7, 1, "2024-11-30"), (4, 7, 2, "2024-11-30"), (5, 7, 4, "2024-11-30"),
(6, 7, 8, "2024-11-30"), (7, 7, 9, "2024-11-30");

Insert Into dOrder (orderID, itemID, purchaseDate, customerID, quantity, price, totalQuant, totalPrice, complete) VALUES (0, 2, "2024-10-10", 1, 10,  10.00, 20, 15.00, 1), 
(0, 4, "2024-10-10", 1, 10,  5.00, 20, 15.00, 1), (0, 3, "2024-10-9", 2, 1,  2.00, 1, 2.0, 1),
(0, 3, "2024-11-11", 3, 2, 0.99, 2, 1.98, 1), (1, 2, "2024-11-11", 4, 1, 0.79, 1, 0.79, 1),
(0, 9, "2024-11-15", 3, 1, 1.99, 1, 1.99, 0), (1, 8, "2024-11-15", 5, 1, 1.99, 1, 1.99, 0);



select * from donuts;

select * from inventory;

select * from dOrder;

select * from customerInfo;