package io.htmlcss.api;

import java.io.IOException;

import io.htmlcss.db.DBFactory;
import io.htmlcss.db.DatabaseFetcher;
import io.htmlcss.model.Cart;
import io.htmlcss.model.Customer;
import io.htmlcss.model.Order;
import io.htmlcss.model.Tray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Checkout
 */
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
	     * Basic constructor for class.
	     * @param firstName
	     * @param lastName
	     * @param address
	     * @param phoneNumber
	     * @param email
	     * @param cardID
	     * @param zipCode
	     */
		
		String command = request.getParameter("command");
		
		if(command == null) {
			placeOrder(request, response);
			return;
		}
		
		if(command.equalsIgnoreCase("checkout")) {
			request.getRequestDispatcher("WEB-INF/Checkout.jsp").forward(request, response);
			return;
		}
	}
	
	public void placeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String firstName, lastName, address, phoneNumber, email, cardID, zip;
		DatabaseFetcher db = DBFactory.getDatabaseFetcher();
		firstName = request.getParameter("fname");
		lastName = request.getParameter("lname");
		address = request.getParameter("addr");
		phoneNumber = request.getParameter("pnum");
		email = request.getParameter("email");
		cardID = request.getParameter("card");
		zip = request.getParameter("zip");
		
		HttpSession sess = request.getSession();
		Cart cart = (Cart) sess.getAttribute("cart");
		
		if(firstName == null || lastName == null || address == null || phoneNumber == null || email == null || cardID == null || zip == null) {
			displayError(response, "Missing feilds...");
			return;
		}
		
		if(cart == null) {
			String path = "/WEB-INF/empty.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		
		int zipCode = Integer.parseInt(zip);
		
		Customer customer = new Customer(firstName, lastName, address, phoneNumber, email, cardID, zipCode);
		
		sess.setAttribute("customer", customer);
		
		cart.setCustomer(customer);
		
		if(!this.checkInventory(cart)) {
			String path = "/WEB-INF/NoInventory.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		
		db.insertCart(cart);
		// Pass this vital information onto the customer
        String path = String.format("/WEB-INF/Receipt.jsp?oID=%d&date=%s", cart.getOrderID(), cart.getDate());

		this.updateInventory(cart);
        
        request.setAttribute("oID", cart.getOrderID());
        request.setAttribute("date", cart.getDate());
        
        sess.setAttribute("customer", null);
        sess.setAttribute("cart", null);
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	private boolean checkInventory(Cart cart) {
		boolean status = true;
		for(Order i: cart.getItems()) {
			status = status && Tray.hasEnoughDonutsInInventory(i.getItem(), i.getQuantity());
		}
		return status;
	}
	
	private void updateInventory(Cart cart) {
		for(Order i: cart.getItems()) {
			Tray.takeDonutsFromInventory(i.getItem(), i.getQuantity());
		}
	}
	
	public void displayError(HttpServletResponse response, String cause) throws IOException {
		response.setContentType("text/html");
		response.getWriter().append(String.format("""
				<html>
					<head>
						<title>Checkout Error</title>
					</head>
					<body>
						<h1> %s <h1>
					</body>
				</html>
				""",
				cause));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
