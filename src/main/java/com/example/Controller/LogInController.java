package com.example.Controller;

import com.example.Logic.login;
import com.example.Model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LogInController {



    public TextField usernameField;
    public Text actionTarget;
    public PasswordField passwordLoginField;


    @FXML
    public void onLoginButtonClick(ActionEvent actionEvent) throws SQLException, IOException {
        String username = usernameField.getText();
        String password = passwordLoginField.getText();
        Login hotel = new Login(username, password);
        Alert alert;
        login logiclogin = new login();
        if (logiclogin.loginQuery(hotel)) {

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Created");
            alert.showAndWait();


            // Open the main form if login is successful
            openMainForm();
        }else {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Message");
            alert.setHeaderText(null);
            alert.setContentText(" Account Does Exist!");
            alert.showAndWait();

        }
    }

    private void openMainForm() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Mainpage.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Main Page Form");
        stage.setScene(new Scene(root, 750, 600));
        stage.show();

    }

        public void SignUpbutton (ActionEvent actionEvent) throws IOException {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SignUp.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Signup Form");
            stage.setScene(new Scene(root, 530, 402));
            stage.show();


        }
    }


