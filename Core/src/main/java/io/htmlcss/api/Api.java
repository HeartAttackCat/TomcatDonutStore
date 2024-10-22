package io.htmlcss.api;

import java.io.IOException;
import java.io.PrintWriter;

import io.htmlcss.db.DBFactory;
import io.htmlcss.db.DatabaseFetcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Korbin
 * 
 * This class was designed to create a route to return the menu of donut information.
 * This route will return json and only json.
 * 
 * Usage:
 * 
 * Make sure that the settings for the database and its enviroment file are setup before using.
 */
@WebServlet("/Api")
public class Api extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseFetcher db = null;

    /**
     * Default constructor. 
     */
    public Api() {
        // TODO Auto-generated constructor stub
    	db = DBFactory.getDatabaseFetcher();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.append(DatabaseFetcher.toJSON(db.getDonuts()));
	}
}
