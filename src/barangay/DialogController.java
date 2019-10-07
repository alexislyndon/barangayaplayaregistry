/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class DialogController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    /**
     * Creates a new alertdialog.
     * @param title
     * @param header
     * @param message
     * @param a
     */
    public static void alert(String title, String header, String message, AlertType a) {
        Alert alert = new Alert(a);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        //Optional<ButtonType> result = alert.showAndWait();
        alert.showAndWait();
    }
    
    /**
     * Creates a new alertdialog with null header value.
     * @param title
     * @param message
     * @param a
     */
    public static void alert(String title, String message, AlertType a) {
        Alert alert = new Alert(a);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        //Optional<ButtonType> result = alert.showAndWait();
        alert.showAndWait();
    }
    
    /**
     * Pops an alertconfirm dialog. A choice will have to be made. Used to confirm actions before updating the database.
     * @param title
     * @param header
     * @param message
     * @return
     */
    public static boolean alertconfirm(String title, String header, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Pops a zone selector. Alters the zone where a household belongs.
     * @return
     */
    public static int choicedialog() {
        List<String> choices = new ArrayList<>();
        choices.add("1a");
        choices.add("1b");
        choices.add("2");
        choices.add("2u");
        choices.add("3");
        choices.add("3u");
        choices.add("4");
        choices.add("5");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("SET ZONE HERE", choices);
        dialog.setTitle("Zone Selector");
        dialog.setHeaderText(null);
        dialog.setContentText("Select Zone:");
        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if(result.get() == "1a") return 1;
            else if (result.get() == "1b") return 2;
            else if (result.get() == "2") return 3;
            else if (result.get() == "2u") return 4;
            else if (result.get() == "3") return 5;
            else if (result.get() == "3u") return 6;
            else if (result.get() == "4") return 7;
            else if (result.get() == "5") return 8;
            else return 0;
        } else return 0;
    }
}
