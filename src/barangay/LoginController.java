/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import barangay.UserAuth.User;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class LoginController extends AnchorPane implements Initializable {

    User currentuser;

    private Barangay application;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorMessage;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        button.setDefaultButton(true);
    }
    /**
     * Logs into the app if username and password are correct. Will pop out the Dashboard
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        errorMessage.setText("");
        if (username.getText() == "" || password.getText() == "") {
            errorMessage.setText("username and password required!");
        } else {
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password =?");
                ps.setString(1, username.getText());
                ps.setString(2, String.valueOf(password.getText()));

                ResultSet rs = ps.executeQuery();

                if (rs.next() || true) { //Temporarily set to true for testing and demonstration.
                    currentuser.setUser(username.getText());
                    errorMessage.setText("yay but nay?");
                    Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Dash.fxml"));
                    Scene scene = new Scene(blah);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                } else {
                    errorMessage.setText("incorrect username or password");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    private void goDash(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("Dash.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//    }
}
