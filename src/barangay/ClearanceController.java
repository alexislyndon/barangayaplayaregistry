/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

//import static barangay.CertificateController.person0;
//import static barangay.CertificateController.person0;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class ClearanceController implements Initializable {
    static Person person0;
    @FXML
    private TextField clearance_no;
    @FXML
    private TextField issuedby;
    @FXML
    private TextField ORnum;
    @FXML
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
    private TextField purpose;
    @FXML
    private Text id;

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
        
        String query = "INSERT INTO clearance(clearance_no,applicant,purpose,OR_no,ReceiptAmount,issuedBy,status,findings,remarks) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setString(1, clearance_no.getText()); //NOT NULL
            ps.setString(2, id.getText());//person id
            ps.setString(3, purpose.getText());
            ps.setString(4, ORnum.getText());
            ps.setString(5, amount.getText());
            ps.setString(6, issuedby.getText());//person id
            ps.setString(7, status.getText());
            ps.setString(8, findings.getText());
            ps.setString(9, remarks.getText());
            
            ps.executeUpdate();
            DialogController.alert("Clearance", "Successfully created Clearance!", Alert.AlertType.INFORMATION);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(CertificateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
