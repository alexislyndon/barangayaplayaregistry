/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import db.MySQLConnector;
import barangay.Person;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import barangay.UserAuth.User;
import db.SQLHandler;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class PermitController extends SQLHandler implements Initializable {

    static Person person0;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField applicant;
    private ComboBox<String> purpose;
    @FXML
    private Label id;
    @FXML
    private TextArea remarks;
    @FXML
    private ComboBox<String> signatory;
    @FXML
    private TextField oic;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String name = person0.getNAME();
        id.setText(Integer.toString(person0.getId()));
        applicant.setText(person0.getNAME());
    }

    @FXML
    private void savepermit(ActionEvent event) throws IOException {
        String query = "INSERT INTO permits(applicant,permitType,signedBy,remarks,issuedBy) "
                + "VALUES(?,?,?,?,?)";
        try {
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setString(1, id.getText());//person id
            ps.setString(2, type.getValue());
            ps.setInt(3, signatory.getSelectionModel().getSelectedIndex()+1);
            ps.setString(4, remarks.getText());
            ps.setInt(5, User.getStaff());

            ps.executeUpdate();
            DialogController.alert("Record Permit", "Sucessfully Recorded Permit", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) id.getScene().getWindow();
                stage.close();
        } catch (SQLException ex) {
            DialogController.alert("Record Permit", "There was an error. Nothing was added.", Alert.AlertType.ERROR);
            Logger.getLogger(PermitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Facilitates passing of objects.
     * @param person
     */
    public static void setPerson(Person person) {
        person0 = person;
    }
}
