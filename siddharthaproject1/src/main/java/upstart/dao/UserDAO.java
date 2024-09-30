package upstart.dao;


import upstart.User;

import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registerUser(User user) throws SQLException {
        String query = "INSERT INTO users (id,phone_number, password, name) VALUES (?,?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
        	stmt.setInt(1,user.getId());
            stmt.setString(2, user.getPhoneNumber());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getName());

            int rowsInserted = stmt.executeUpdate();
            System.out.println("Rows Inserted: " + rowsInserted); // Check how many rows were inserted

            return rowsInserted > 0;
        }
    }


    public User loginUser(String phoneNumber, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE phone_number = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, phoneNumber);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                return user;
            }
            return null;
        }
    }
}


