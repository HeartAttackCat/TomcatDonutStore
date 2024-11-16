package io.htmlcss.api;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
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
			request.getRequestDispatcher("/WEB-INF/admin/Admin.jsp").forward(request, response);
			return;
		}
		
		if(param.equalsIgnoreCase("sales")) {
			request.getRequestDispatcher("/WEB-INF/admin/ReportSales.jsp").forward(request, response);
		}
		
		if(param.equalsIgnoreCase("waste")) {
			request.getRequestDispatcher("/WEB-INF/admin/ReportStales.jsp").forward(request, response);
		}
		
		if(param.equalsIgnoreCase("menu")) {
			request.getRequestDispatcher("/WEB-INF/admin/Menu.jsp").forward(request, response);
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
