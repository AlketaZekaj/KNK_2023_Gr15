package controllers;

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
import model.Professor;
import model.SessionManager;
import repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

public class proflogController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField useridField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button albbtn;
    @FXML
    private Button engbtn;

    Connection connDB = null;

    @FXML
    public void  onalbbtnclick(ActionEvent e){
        try {
            Locale currentLocale=Locale.getDefault();
            Locale locale = new Locale("Al_AL");
            ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
            Parent parent = FXMLLoader.load(getClass().getResource("/application/proflog.fxml"),bundle);
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
            Parent parent = FXMLLoader.load(getClass().getResource("/application/proflog.fxml"),bundle);
            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            primaryStage.setTitle("Menaxhimi I Konsultimeve");
            primaryStage.setScene(new Scene(parent));
            primaryStage.show();
        }catch(Exception e1) {
            e1.printStackTrace();
        }
    }

    public void loginButtonOnAction(ActionEvent e) throws Exception{

        if(useridField.getText().isBlank() == false && passwordField.getText().isBlank() == false){
            DatabaseConnection connect = new DatabaseConnection();
            connDB = connect.getConn();

            String verifyLogin = "select count(1) from profesoret where id ='" + useridField.getText() + "' and password ='" + passwordField.getText() + "'";
            try{
                Statement statement = connDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);

                while(queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        SessionManager.professor = getProfessor(useridField.getText());
                        try {
                            Locale currentLocale=Locale.getDefault();
                            Locale locale = new Locale("en_US");
                            ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
                            Parent parent = FXMLLoader.load(getClass().getResource("/application/professor.fxml"),bundle);
                            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            primaryStage.setTitle("Menaxhimi I Konsultimeve");
                            primaryStage.setScene(new Scene(parent));
                            primaryStage.show();
                        }catch(Exception e1) {
                            e1.printStackTrace();
                        }
                    } else{
                        loginMessageLabel.setText("Invalid login!");
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void  cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private Professor getProfessor(String id) throws Exception{
        DatabaseConnection connect = new DatabaseConnection();
        connDB = connect.getConn();

        String sql = "SELECT * from Profesoret where id = '" + id + "';";
        Statement statement = connDB.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        Professor p = new Professor(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("website")
        );
        return p;
    }
}
