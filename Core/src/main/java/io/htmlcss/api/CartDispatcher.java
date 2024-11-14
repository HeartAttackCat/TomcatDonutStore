package io.htmlcss.api;

import java.io.IOException;

import io.htmlcss.model.Cart;
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
		
		request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
	}
	
	public void addToCart(HttpServletRequest request, HttpServletResponse response, Cart cart) throws IOException, ServletException {
		if (cart == null)
		{
			showCart(request, response, cart);
			return;
		}
		
		String donutID = request.getParameter("did");
		String donutQuan = request.getParameter("quan");
		
		
	}
	
	public void removeFromCart(HttpServletRequest request, HttpServletResponse response, Cart cart) throws IOException, ServletException {
		if (cart == null)
		{
			showCart(request, response, cart);
			return;
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
