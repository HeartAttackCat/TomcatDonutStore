package io.htmlcss.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import io.htmlcss.db.DBFactory;
import io.htmlcss.db.DatabaseFetcher;

/**
 * Servlet implementation class product
 * 
 * Dispatches product page if properties are correct.
 */
public class Product extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	DatabaseFetcher db = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        db = DBFactory.getDatabaseFetcher();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("product");
		
		System.out.println(sid);
		//db.checkDonut();
		try { 
			Integer id = Integer.parseInt(sid);
			
			if (db.checkDonut(id))
				request.getRequestDispatcher("/WEB-INF/product.jsp").forward(request, response);
			else
				request.getRequestDispatcher("/WEB-INF/fof.jsp").forward(request, response);
		}
		catch (NumberFormatException e) {
			request.getRequestDispatcher("/WEB-INF/fof.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
