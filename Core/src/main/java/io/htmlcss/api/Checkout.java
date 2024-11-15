package io.htmlcss.api;

import java.io.IOException;

import io.htmlcss.db.DBFactory;
import io.htmlcss.db.DatabaseFetcher;
import io.htmlcss.model.Cart;
import io.htmlcss.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
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
	
	public void placeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
			response.setContentType("text/html");
			response.getWriter().append("""
					<html>
						<head>
							<title>Error</title>
						</head>
						<body>
							<h1> Missing fields... <h1>
						</body>
					</html>
					""");
			return;
		}
		
		if(cart == null) {
			response.setContentType("text/html");
			response.getWriter().append("""
					<html>
						<h1> Missing cart... <h1>
					</html>
					""");
			return;
		}
		
		int zipCode = Integer.parseInt(zip);
		
		Customer customer = new Customer(firstName, lastName, address, phoneNumber, email, cardID, zipCode);
		
		sess.setAttribute("customer", customer);
		
		cart.setCustomer(customer);
		
		db.insertCart(cart);
		
		response.setContentType("text/html");
		response.getWriter().append("""
				<html>
					<h1> Order placed! <h1>
				</html>
				""");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
