package Controllerat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repository.DatabaseConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox Profesoret;

    @FXML
    private ComboBox Lendet;

    @FXML
    private Button SubmitBtn;

    @FXML
    private Button CancelBtn;

    @FXML
    private TextField hrField;

    @FXML
    private TextField minField;

    @FXML
    private Button engbtn;

    @FXML
    private Button albbtn;

    @FXML
    public void  onalbbtnclick(ActionEvent e){
        try {
            Locale currentLocale=Locale.getDefault();
            Locale locale = new Locale("Al_AL");
            ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
            Parent parent = FXMLLoader.load(getClass().getResource("/Application/Student.fxml"),bundle);
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
            Parent parent = FXMLLoader.load(getClass().getResource("/Application/Student.fxml"),bundle);
            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            primaryStage.setTitle("Menaxhimi I Konsultimeve");
            primaryStage.setScene(new Scene(parent));
            primaryStage.show();
        }catch(Exception e1) {
            e1.printStackTrace();
        }
    }

    ObservableList<String> profesori  = FXCollections.observableArrayList("Dalina Vranovci", "Valon Raca", "Blend Arifaj","Isak Shabani","Qamil Kabashi");
    ObservableList<String> lenda  = FXCollections.observableArrayList("Programim ne Ueb2", "Arkitektura e Kompjutereve", "Komunikimi Njeri-Kompjuter","Baza e te dhenave","Elektronike");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Profesoret.setItems(profesori);
        Lendet.setItems(lenda);



    }
    public void clear() {
        nameField.setText("");
        Profesoret.setValue(null);
        Lendet.setValue(null);
        datePicker.getEditor().clear();
        hrField.setText("");
        minField.setText("");
    }

        @FXML
        private void submitButton(ActionEvent event) throws Exception {
            // Existing code...

            String emri = nameField.getText();
            String hr = hrField.getText();
            String min = minField.getText();
            String newDate = datePicker.getValue().toString();
            String profesori = Profesoret.getSelectionModel().getSelectedItem().toString();
            String lenda = Lendet.getSelectionModel().getSelectedItem().toString();


            DatabaseConnection connect = new DatabaseConnection();
            Connection connDB = connect.getConn();


            if(emri.isEmpty() || newDate.isEmpty() || profesori.isEmpty() || lenda.isEmpty() || hr.isEmpty() || min.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText("All fields are required. Please fill them out!");
                alert.showAndWait();
                return;
            }
                if (!validateHr(hr) || min.length() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Possible time is [08:00 - 18:59]");
                    alert.showAndWait();
                    return;
                }

                String time = hr + ":" + min;
                String dateTime = newDate + " " + time;

                // Update the SQL query to include the concatenated date and time value
                String sql = "INSERT INTO konsultimet (profesori, Studenti, lenda, Koha_fillimi) VALUES (?, ?, ?, ?);";
                PreparedStatement statement = connDB.prepareStatement(sql);
                statement.setString(1, profesori);
                statement.setString(2, emri);
                statement.setString(3, lenda);
                statement.setString(4, dateTime);

                try {
                    int affectedRows = statement.executeUpdate();
                    if (affectedRows != 1) {
                        throw new Exception("ERR_MULTIPLE_ROWS_AFFECTED");
                    }

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("SUCCESS!");
                    alert.setContentText("Appointment added!");
                    alert.showAndWait();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("FAILED!");
                    alert.setContentText("Failed to add appointment!");
                    alert.showAndWait();
                }

                clear();
            }

            private boolean validateHr(String hr){

                if(hr.length() == 0){
                    return false;
                }

                Integer intHr = Integer.parseInt(hr);

                if (intHr < 8 || intHr > 18){
                    return false;
                }

                return true;
            }


            @FXML
            public void cancelButton(ActionEvent e)throws Exception{

                try {
                    Locale currentLocale=Locale.getDefault();
                    Locale locale = new Locale("en_US");
                    ResourceBundle bundle=ResourceBundle.getBundle("resources.labelText",locale);
                    Parent parent = FXMLLoader.load(getClass().getResource("/Application/Hyrja.fxml"),bundle);
                    Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    primaryStage.setTitle("Cancel appointment");
                    primaryStage.setScene(new Scene(parent));
                    primaryStage.show();
                }catch(Exception e1) {
                    e1.printStackTrace();
                }
            }

        }
