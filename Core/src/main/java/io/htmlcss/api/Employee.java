package io.htmlcss.api;

import java.io.IOException;
import java.time.LocalDateTime;

import io.htmlcss.model.BakingTray;
import io.htmlcss.model.Cart;
import io.htmlcss.model.Tray;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		Tray.updateBakedTrays(); // Update the trays
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
		}
		
		if(param.equalsIgnoreCase("trayAdd")) {
			String p = request.getParameter("donuts");
			System.out.println("Hello");
			System.out.println(p);
	
			if (p != null) {
				int id = Integer.parseInt(request.getParameter("donuts"));
				LocalDateTime date = LocalDateTime.now();
				System.out.println(date);
				@SuppressWarnings("unused")
				Tray tray = new BakingTray(id, 20, date);
				
			}

			param="tray";
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
