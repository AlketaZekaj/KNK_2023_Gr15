package Controllerat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class provaController implements Initializable {
	@FXML
	private MenuItem closeButton;
	@FXML
	private CheckBox checkbox;
	@FXML
	private Label provalabel;
	@FXML
	private Button albbtn;
	@FXML
	private Button engbtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
    @FXML
    public void  onalbbtnclick(ActionEvent e){
    	try {
    		Locale currentLocale=Locale.getDefault();
   		    Locale locale = new Locale("Al_AL");
   		    ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
            Parent parent = FXMLLoader.load(getClass().getResource("/application/prova.fxml"),bundle);
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
            Parent parent = FXMLLoader.load(getClass().getResource("/application/prova.fxml"),bundle);
            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            primaryStage.setTitle("Menaxhimi I Konsultimeve");
            primaryStage.setScene(new Scene(parent));
            primaryStage.show();
        	}catch(Exception e1) {
        		e1.printStackTrace();
        	}
    }
	   
    @FXML
    void onStudentClick(ActionEvent e) throws Exception{
    	if(checkbox.isSelected()) {
    	try {
        Locale currentLocale=Locale.getDefault();
   		Locale locale = new Locale("en_US");
   		ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
        Parent parent = FXMLLoader.load(getClass().getResource("/application/login.fxml"),bundle);
        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        primaryStage.setTitle("Menaxhimi I Konsultimeve");
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    	}catch(Exception e1) {
    		e1.printStackTrace();
    	}
    	}else {
    		provalabel.setText("Please agree to our terms and conditions");
    	}
    }
    @FXML
    void onProffesorClick(ActionEvent e) throws Exception{
    	if(checkbox.isSelected()) {
    	try {
        Locale currentLocale=Locale.getDefault();
    	Locale locale = new Locale("en_US");
    	ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
        Parent parent = FXMLLoader.load(getClass().getResource("/application/proflog.fxml"),bundle);
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    	}catch (Exception e1) {
    		e1.printStackTrace();
    	}
    	}else {
    		provalabel.setText("Please agree to our terms and conditions");
    	}

    }
    
   
    
    @FXML
    void onHelpClick(ActionEvent e) throws Exception{
    try {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Ky eshte nje aplikacion per student-profesore menaxhim,ju lutemi kycuni si profesor apo student!");
    alert.showAndWait();
    }catch (Exception ex){
        ex.printStackTrace();
    }

}
    @FXML
    void  onCloseClick(ActionEvent e){
        System.exit(0);
    }
    
   
}
