package io.htmlcss.api;

import io.htmlcss.db.*;
import io.htmlcss.model.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Employee
 */
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Tray> t = Tray.updateBakedTrays(); // Update the trays
		HttpSession sess = request.getSession();
		sess.setAttribute("orders", Cart.getActiveOrders());
		sess.setAttribute("inv", Tray.getInventoryTrays());
		sess.setAttribute("baking", Tray.getBakingTrays());
		String param = request.getParameter("command");
		if (param == null) {
			request.getRequestDispatcher("/WEB-INF/employee/Employee.jsp").forward(request, response);
			return;
		}
		if(param.equalsIgnoreCase("orderComplete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String date = request.getParameter("date");
			Cart.completeOrder(date, id);
			param = "order";
			sess.setAttribute("orders", Cart.getActiveOrders());
			
		}
		
		if(param.equalsIgnoreCase("trayAdd")) {
			String p = request.getParameter("donuts");
	
			if (p != null) {
				int id = Integer.parseInt(request.getParameter("donuts"));
				LocalDateTime date = LocalDateTime.now();
				System.out.println(date);
				Tray tray = new BakingTray(id, 20, date);
				
			}

			param="tray";
			sess.setAttribute("baking", Tray.getBakingTrays());
		}
		
		if(param.equalsIgnoreCase("order")) {
			request.getRequestDispatcher("/WEB-INF/employee/Orders.jsp").forward(request, response);
			return;
		}
		
		if(param.equalsIgnoreCase("tray")) {
			request.getRequestDispatcher("/WEB-INF/employee/Tray.jsp").forward(request, response);
			return ;
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
