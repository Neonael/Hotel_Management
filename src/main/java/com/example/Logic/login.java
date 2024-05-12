package com.example.Logic;

import com.example.Model.Login;
import com.example.Utilities.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class login {


    public boolean loginQuery(Login hotel) throws SQLException {
        String username = hotel.getUsername();
        String password = hotel.getPassword();

        String query = "SELECT * FROM Hotel_Manage WHERE username = ? AND password = ?";

        // Get connection using existing getConnection method
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If a row is returned, the login is successful
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // If no row is returned, the login is unsuccessful
    }
    }










