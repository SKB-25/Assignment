package com.admin.assignment.web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VALID_LOGIN_ID = "test@sunbasedata.com";
    private static final String VALID_PASSWORD = "Test@123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginId = request.getParameter("login_id");
        String password = request.getParameter("password");

        if (loginId.equals(VALID_LOGIN_ID) && password.equals(VALID_PASSWORD)) {
            // Authentication successful, store a session attribute for further API calls
            HttpSession session = request.getSession();
            session.setAttribute("authenticated", true);

            // Redirect to the dashboard or another protected page after successful login
            response.sendRedirect("customer-list.jsp"); // Replace with the desired page
        } else {
            // Handle authentication failure, show error message, etc.
            response.sendRedirect("login.jsp?error=true"); // Redirect back to the login page with an error flag
        }
    }
}
