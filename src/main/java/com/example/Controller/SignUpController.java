package com.example.Controller;

import com.example.Utilities.dbConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {


    public TextField PasswordField;
    public TextField accountNameField;

    public void gobacktoLoginForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Loginz.fxml"));
        Parent root = loader.load();
        Stage signup = new Stage();
        signup.setTitle("Loginz Form");
        signup.setScene(new Scene(root, 530, 402));
        signup.show();


    }

    public void logtodatabase(ActionEvent actionEvent) {
        String accountName = accountNameField.getText();
        String password = PasswordField.getText();

        //  database connection from DBConnector class
        try (Connection connection = dbConnector.getConnection()) {
            String sql = "INSERT INTO Hotel_Manage (username, password) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, accountName);
                preparedStatement.setString(2, password);

                Alert alert;
                // Execute the query
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Account Created Successfully!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to create account!");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();


        }
    }
}
