/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

import static barangay.PermitController.person0;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class RequestController implements Initializable {

    @FXML
    private TextField requester;
    @FXML
    private TextField requestloc;
    @FXML
    private TextArea details;
    @FXML
    private DatePicker date;
    @FXML
    private TextField priority;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setText(Integer.toString(person0.getId()));
        requester.setText(person0.getNAME());
    }

    @FXML
    private void saverequest(ActionEvent event) throws IOException {

        try {
            String query = "INSERT INTO request(requester,request_details,date_time_needed,priority,request_location) "
                    + "values(?,?,?,?,?)";

            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setString(1, id.getText());
            ps.setString(2, details.getText());
            ps.setObject(3, date.getValue());
            ps.setString(4, priority.getText());
            ps.setString(5, requestloc.getText());
            ps.executeUpdate();
            DialogController.alert("Record Request", "Sucessfully Recorded Request", Alert.AlertType.INFORMATION);
        } catch (SQLException ex) {
            DialogController.alert("Record Request", "There was an error. Nothing was added.", Alert.AlertType.ERROR);
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param person
     */
    public static void setPerson(Person person) {
        person0 = person;
    }

}
