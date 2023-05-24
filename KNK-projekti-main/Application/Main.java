package Application;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;





public class Main extends Application {
    @Override
   public void start(Stage primaryStage) {
    	  try {
    		  Locale currentLocale=Locale.getDefault();
    		  Locale locale = new Locale("en_US");
    		  ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
              Parent root = FXMLLoader.load(getClass().getResource("prova.fxml"),bundle);
              primaryStage.setTitle("Menaxhimi I Konsultimeve");
              primaryStage.setScene(new Scene(root));
              primaryStage.show(); 
          } catch (Exception e) {
              e.printStackTrace();
          }
   
       }
   
   public static void main(String[] args) {
       launch(args);
   }
} 
