/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

//import static barangay.CertificateController.person0;
//import static barangay.CertificateController.person0;
import db.MySQLConnector;
import barangay.Person;
import barangay.UserAuth.User;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
public class ClearanceController extends SQLHandler implements Initializable {
    static Person person0;
    private TextField clearance_no;
    private TextField issuedby;
    private TextField ORnum;
    private TextField amount;
    @FXML
    private TextField status;
    @FXML
    private TextField findings;
    @FXML
    private TextField remarks;
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
     * Facilitates the passing of objects.
     * @param person
     */
    public static void setPerson(Person person) {
        person0 = person;
    }
    /**
     * Handles the saving of clearance to database.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void saveclear(ActionEvent event) throws IOException {
        
        String query = "INSERT INTO clearance(applicant,purpose,issuedBy,status,findings,remarks,signedby) "
                    + "VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setString(1, id.getText());//person id
            ps.setString(2, purpose.getValue());
            ps.setInt(3, User.getStaff());//staff id
            ps.setString(4, status.getText());
            ps.setString(5, findings.getText());
            ps.setString(6, remarks.getText());
            ps.setInt(7, signatory.getSelectionModel().getSelectedIndex()+1);
            
            ps.executeUpdate();
            DialogController.alert("Clearance", "Successfully created Clearance!", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) id.getScene().getWindow();
                stage.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(CertificateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
