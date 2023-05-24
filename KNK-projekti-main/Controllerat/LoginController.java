package Controllerat;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;


public class LoginController {

    @FXML
    private Button Loginbtn;
    @FXML
    private Label loginMessage;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button albbtn;
    @FXML
    private Button engbtn;

    private static Connection connDB = null;
    
    @FXML
    public void  onalbbtnclick(ActionEvent e){
        try {
           Locale currentLocale=Locale.getDefault();
           Locale locale = new Locale("Al_AL");
           ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
            Parent parent = FXMLLoader.load(getClass().getResource("/Application/Login.fxml"),bundle);
            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            primaryStage.setTitle("Menaxhimi I Konsultimeve");
            primaryStage.setScene(new Scene(parent));
            primaryStage.show();
            }catch(Exception e1) {
               e1.printStackTrace();
            }
    }
    
    @FXML
    public void  onengbtnclick(ActionEvent e){
        try {
           Locale currentLocale=Locale.getDefault();
           Locale locale = new Locale("en_US");
           ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
            Parent parent = FXMLLoader.load(getClass().getResource("/Application/Login.fxml"),bundle);
            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            primaryStage.setTitle("Menaxhimi I Konsultimeve");
            primaryStage.setScene(new Scene(parent));
            primaryStage.show();
            }catch(Exception e1) {
               e1.printStackTrace();
            }
    }
       
    
 

    public void loginButton(javafx.event.ActionEvent e){
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank()){
            DatabaseConnection connect = new DatabaseConnection();
            connDB = connect.getConn();

            String verifyLogin = "select count(1) from users where username ='" + usernameField.getText() + "' and password ='" + passwordField.getText() + "'";
            try{
                Statement statement = connDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);

                while(queryResult.next()){
                    if (queryResult.getInt(1) == 1) {
                        try {
                           Locale currentLocale=Locale.getDefault();
                           Locale locale = new Locale("en_US");
                           ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
                            Parent parent = FXMLLoader.load(getClass().getResource("/Application/Student.fxml"),bundle);
                            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            primaryStage.setTitle("Menaxhimi I Konsultimeve");
                            primaryStage.setScene(new Scene(parent));
                            primaryStage.show();
                            }catch(Exception e1) {
                               e1.printStackTrace();
                            }
                    }else{
                        loginMessage.setText("Invalid login!");
                    }
                    }
                }catch(Exception ex){
                ex.printStackTrace();
            }
            }else{
            loginMessage.setText("Please enter username and password");
        }
        }
    
    public void  cancelButton(ActionEvent e){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }


}
