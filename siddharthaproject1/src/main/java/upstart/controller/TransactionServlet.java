package upstart.controller;

import upstart.dao.TransactionDAO;
import upstart.Transaction;
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
import java.time.LocalDateTime;

@SuppressWarnings("serial")
@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        double amount = Double.parseDouble(request.getParameter("amount"));
        Transaction transaction = new Transaction();
        transaction.setCustomerId(user.getId());
        transaction.setAmount(amount);
        transaction.setDateTime(LocalDateTime.now());

        try (Connection connection = DatabaseUtils.getConnection()) {
            TransactionDAO transactionDAO = new TransactionDAO(connection);
            boolean isSaved = transactionDAO.saveTransaction(transaction);
            if (isSaved) {
                response.sendRedirect("transaction.jsp?success=Transaction recorded");
            } else {
                response.sendRedirect("transaction.jsp?error=Transaction failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("transaction.jsp?error=Internal server error");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests or redirect to the transaction page
        response.sendRedirect("transaction.jsp");
    }
}

