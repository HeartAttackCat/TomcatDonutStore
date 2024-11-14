package io.htmlcss.api;

import java.io.IOException;
import java.util.List;

import io.htmlcss.model.Cart;
import io.htmlcss.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CartDispatcher
 */
@WebServlet("/Cart")
public class CartDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		String command = request.getParameter("command");
		
		if(command == null)
			showCart(request, response, cart);
		
		else {
			if(command.equalsIgnoreCase("add")) {
				addToCart(request, response, cart);
			}
			else if (command.equalsIgnoreCase("remove")) {
				removeFromCart(request, response, cart);
			}
			else if (command.equalsIgnoreCase("show")) {
				showCart(request, response, cart);
			}
		}
	}
	
	public void showCart(HttpServletRequest request, HttpServletResponse response, Cart cart) throws IOException, ServletException {
		if (cart == null)
		{
			//TODO remove following once empty cart page is implemented.
			response.getWriter().append("EMPTY CART, :(");
			//TODO implement empty cart page.
			return;
		}
		
		//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-REMOVE BLOCK WHEN CART.JSP IS DONE-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
		List<Order> orders = cart.getItems();
		
		for(Order order: orders) {
			response.getWriter().append("[Cart Item] " + order.getItem().getType() + " " + order.getItem().getFlavor() + " | amount "+order.getQuantity()+", Subtotal: "+order.getQuantity()*order.getItem().getPrice()+"\n");
		}
		
		response.getWriter().append("[Cart Total] " + cart.getTotalCost() + "\n");
		//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
		
		
		//TODO uncomment next line when cart.jsp is needed
		// request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
	}
	
	public void addToCart(HttpServletRequest request, HttpServletResponse response, Cart cart) throws IOException, ServletException {
		String donutIDString = request.getParameter("did");
		String donutQuanString = request.getParameter("quantity");
		
		if (donutIDString == null || donutQuanString == null) {
			request.getRequestDispatcher("/WEB-INF/fof.jsp").forward(request, response);
			return;
		}
		
		if (cart == null) {
			Cart newCart = new Cart();
			cart = newCart;
		}
		
		int donutID = Integer.parseInt(donutIDString);
		int donutQuan = Integer.parseInt(donutQuanString); 
		
		if (!cart.addDonuts(donutID, donutQuan))
		{
			request.getRequestDispatcher("/WEB-INF/fof.jsp").forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("cart", cart);
		
		showCart(request, response, cart);
	}
	
	public void removeFromCart(HttpServletRequest request, HttpServletResponse response, Cart cart) throws IOException, ServletException {
		String donutIDString = request.getParameter("did");
		String donutQuanString = request.getParameter("quantity");
		
		if (donutIDString == null || donutQuanString == null) {
			request.getRequestDispatcher("/WEB-INF/fof.jsp").forward(request, response);
			return;
		}
		
		if (cart == null) {
			Cart newCart = new Cart();
			cart = newCart;
		}
		
		int donutID = Integer.parseInt(donutIDString);
		int donutQuan = Integer.parseInt(donutQuanString); 
		
		if (!cart.removeDonuts(donutID, donutQuan))
		{
			request.getRequestDispatcher("/WEB-INF/fof.jsp").forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("cart", cart);
		
		showCart(request, response, cart);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
