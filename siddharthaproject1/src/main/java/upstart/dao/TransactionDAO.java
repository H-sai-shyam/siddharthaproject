package upstart.dao;

import upstart.Transaction;

import java.sql.*;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class TransactionDAO {
    private Connection connection;

    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean saveTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions (customer_id, amount, date_time) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, transaction.getCustomerId());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setTimestamp(3, Timestamp.valueOf(transaction.getDateTime()));
            return stmt.executeUpdate() > 0;
        }
    }
}


