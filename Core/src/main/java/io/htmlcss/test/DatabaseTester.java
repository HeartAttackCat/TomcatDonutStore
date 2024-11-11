package test;

import io.htmlcss.model.*;
import io.htmlcss.db.*;
import java.util.ArrayList;

public class DatabaseTester {
	static DatabaseFetcher db = null;
	public static void main(String[] args) {
		ArrayList<Order> items = new ArrayList<Order>();
		Donut donut = new Donut(1, "Raised", "Glazed", "I'm not writing that", "raisedGlazed");
		donut.setPrice((float) 0.69);
		items.add(new Order(donut, 1));
		Customer customer = new Customer("Goku", "Dragon Ball", "162 Vegeta Lane", "505-236-2362","Goku@gmail.com",278347,96021);
		Cart cart = new Cart(customer, items);
		System.out.println("SUCCESSFUL CREATION!");
		db = DBFactory.getDatabaseFetcher();
		db.insertCart(cart);
		System.out.println("Success?");
	}
}
