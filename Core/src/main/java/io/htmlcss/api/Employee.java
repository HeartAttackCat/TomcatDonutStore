package io.htmlcss.api;

import io.htmlcss.db.*;
import io.htmlcss.model.*;
import java.io.IOException;

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
		
		String param = request.getParameter("command");
		
		if (param == null) {
			request.getRequestDispatcher("/WEB-INF/employee/Employee.jsp").forward(request, response);
			return;
		}
		if(param.equalsIgnoreCase("orderComplete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String date = request.getParameter("date");
			DBFactory.getDatabaseFetcher().updateOrder(date, id);
			System.out.println("hit");
//			Cart.completeOrder(date, id);
			param = "order";
		}
		
		if(param.equalsIgnoreCase("order")) {
			request.getRequestDispatcher("/WEB-INF/employee/Orders.jsp").forward(request, response);
			return;
		}
		
		if(param.equalsIgnoreCase("tray")) {
			request.getRequestDispatcher("/WEB-INF/employee/Tray.jsp").forward(request, response);
			return ;
		}
		
//		if(param.equalsIgnoreCase("menu")) {
//			String subAction = request.getParameter("modCommand");
//			if (subAction == null) {
//				
//			}
//				
//			else if (subAction.equalsIgnoreCase("add")) {
//				String type = request.getParameter("type");
//				String flavor = request.getParameter("flavor");
//				Float price = Float.parseFloat(request.getParameter("price"));
//				String desc = request.getParameter("desc");
//				Donut newDonut = new Donut(-1, type, flavor, desc, type+flavor);
//				newDonut.setPrice(price);
//				DBFactory.getDatabaseFetcher().insertDonut(newDonut);
//			}
//			else if (subAction.equalsIgnoreCase("delete")) {
//				int id = Integer.parseInt(request.getParameter("id"));
//				DBFactory.getDatabaseFetcher().deleteDonut(id);
//			}
//			else if (subAction.equalsIgnoreCase("modify")) {
//				int id = Integer.parseInt(request.getParameter("id"));
//				String type = request.getParameter("type");
//				String flavor = request.getParameter("flavor");
//				Float price = Float.parseFloat(request.getParameter("price"));
//				String desc = request.getParameter("desc");
//				Donut donut = new Donut(id, type, flavor, desc, type+flavor);
//				donut.setPrice(price);
//				DBFactory.getDatabaseFetcher().modifyDonut(donut);
//			}
//			request.getRequestDispatcher("/WEB-INF/admin/Menu.jsp").forward(request, response);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
