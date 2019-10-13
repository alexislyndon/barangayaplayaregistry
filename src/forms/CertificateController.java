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
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import barangay.UserAuth.User;
import db.SQLHandler;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class CertificateController extends SQLHandler implements Initializable {
    
    
    static Person person0;
    private TextField certnum;
    @FXML
    private ComboBox<String> type;
    private TextField signedby;
    @FXML
    private TextField applicant;
    @FXML
    private ComboBox<String> purpose;
    @FXML
    private Label id;
    @FXML
    private TextField oic;
    @FXML
    private ComboBox<String> signatory;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i = person0.getId();
        String name = person0.getNAME();
        id.setText(Integer.toString(person0.getId()));
        applicant.setText(person0.getNAME());
    }
    
    /**
     * Facilitates passing of variables.
     * @param person
     */
    public static void setPerson(Person person) {
        person0 = person;
    }
    /**
     * Handles the saving of a Certificate Record. Inserts into database.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void savecert(ActionEvent event) throws IOException {
        String query = "INSERT INTO certificates(applicant,cert_type,purpose,signedBy,issuedby) "
                    + "VALUES(?,?,?,?,?)";
        try {
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
//            ps.setString(1, certnum.getText()); //NOT NULL
            ps.setString(1, id.getText());//person id
            ps.setString(2, type.getValue());
            ps.setString(3, purpose.getValue());
            ps.setInt(4, signatory.getSelectionModel().getSelectedIndex()+1);
            ps.setInt(5, User.getStaff());
            
            ps.executeUpdate();
            DialogController.alert("Certificate", "Successfully Created a Certificate!s", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) id.getScene().getWindow();
                stage.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(CertificateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
