/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import db.MySQLConnector;
import barangay.Person;
import db.SQLHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class BlotterController extends SQLHandler implements Initializable {

    public static String field;
    public static String fff;
    static Person person0;
    @FXML
    private TextField complainant;
    @FXML
    private TextField respondent;
    @FXML
    private TextField victim;
    @FXML
    private TextField status;
    @FXML
    private TextField location;
    @FXML
    private TextField type;
    @FXML
    private TextField narrative;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    /**
     * Handles the saving of a blotter record. Inserts data to database.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void saveblotter(ActionEvent event) throws IOException {
        String query = "INSERT INTO incident(narrative,incident_when,complainant,respondent,victim,incident_place,incident_type,status) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setString(1, narrative.getText()); //NOT NULL
            ps.setObject(2, date.getValue());//person id
            ps.setString(3, complainant.getText());
            ps.setString(4, respondent.getText());
            ps.setString(5, victim.getText());
            ps.setString(6, location.getText());
            ps.setString(7, type.getText());
            ps.setString(8, status.getText());

            ps.executeUpdate();
            DialogController.alert("Blotter", "Blotter Record Successfully Created", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) complainant.getScene().getWindow();
                stage.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(BlotterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Facilitates passing of objects.
     * @param person
     */
    public static void setPerson(Person person) {
        person0 = person;
    }
    /**
     * Pops a person picker window. This helps the user enter the right data for Complainant, Respondent, and Victim since a valid entry would only be a person id.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void pickperson(ActionEvent event) throws IOException { //press enter on a textfield to pop out
        
        final Node source = (Node) event.getSource();
        System.out.println(source);
        field = source.getId();
        System.out.println(field);
        FXMLLoader loader = new FXMLLoader();
        Parent blah = loader.load(getClass().getResource("/fxml/personpicker.fxml"));
        PersonpickerController ppick = loader.getController();
        ppick.setEvent(source);
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    
}
