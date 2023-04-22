package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private void loginClick(ActionEvent event){
        System.out.println("Login button clicked!");
        String username = this.username.getText();
        String password = this.password.getText();
        System.out.printf("Username: %s, Password: %s \n", username,password);
    };

    @FXML
    private void goToSignUp(ActionEvent event){

    }
}
