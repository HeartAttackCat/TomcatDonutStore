package p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/store-names")
public class StoreNamesServlet extends HttpServlet {

    // Handle POST request
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] groupMembers = request.getParameterValues("groupMembers");
        HttpSession session = request.getSession();
        session.setAttribute("groupMembers", groupMembers);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Group members saved successfully!</h2>");
        out.println("</body></html>");
    }

    // Handle GET request (optional)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>This servlet only supports POST requests for storing group members.</h2>");
        out.println("</body></html>");
    }
}
`