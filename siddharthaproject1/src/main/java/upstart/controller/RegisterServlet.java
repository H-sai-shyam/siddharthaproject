package upstart.controller;

import upstart.dao.UserDAO;
import upstart.User;
import upstart.utils.DatabaseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@SuppressWarnings("serial")
@WebServlet("/register") // Ensure that this matches the action in your register.jsp form
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        try (Connection connection = DatabaseUtils.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);
            User user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);
            user.setName(name);
            
            
            boolean isRegistered = userDAO.registerUser(user);
            if (isRegistered) {
                response.sendRedirect("login.jsp?success=Registration successful");
            } else {
                response.sendRedirect("register.jsp?error=Registration failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Internal server error");
        }
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Password: " + password);
        System.out.println("Name: " + name);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}
