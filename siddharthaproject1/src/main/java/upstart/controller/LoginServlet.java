package upstart.controller;

import upstart.dao.UserDAO;
import upstart.User;
import upstart.utils.DatabaseUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseUtils.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User user = userDAO.loginUser(phoneNumber, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("transaction.jsp");
            } else {
                response.sendRedirect("login.jsp?error=Invalid credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Internal server error");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests or redirect to the login page
        response.sendRedirect("login.jsp");
    }
}



