package p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/retrieve-names")
public class RetrieveNamesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession(false); // false means don't create a new session if it doesn't exist

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        if (session != null) {
            // Retrieve group members from session
            String[] groupMembers = (String[]) session.getAttribute("groupMembers");

            if (groupMembers != null) {
                out.println("<h2>Group Members:</h2>");
                out.println("<ul>");
                for (String member : groupMembers) {
                    out.println("<li>" + member + "</li>");
                }
                out.println("</ul>");
            } else {
                out.println("<h2>No group members found in session.</h2>");
            }
        } else {
            out.println("<h2>No session found.</h2>");
        }
        out.println("</body></html>");
    }
}
