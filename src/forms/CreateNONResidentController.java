/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import db.MySQLConnector;
import db.SQLHandler;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class CreateNONResidentController extends SQLHandler implements Initializable {

    @FXML
    private Label lbllast;
    @FXML
    private Label lblfirst;
    @FXML
    private Label lblmiddle;
    @FXML
    private Label lblsuffix;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField middleName;
    @FXML
    private TextField suffix;
    @FXML
    private ChoiceBox<String> choiceSex;
    @FXML
    private ChoiceBox<String> choiceCivil;
    @FXML
    private DatePicker birthdate;
    @FXML
    private TextField birthplace;
    @FXML
    private Label lblsex;
    @FXML
    private Label lblcivil;
    @FXML
    private Label lblbirth;
    @FXML
    private Label lblbirthplace;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField bloodtype;
    @FXML
    private TextField citizenship;
    @FXML
    private TextField occupation;
    @FXML
    private Label lblheight;
    @FXML
    private Label lblwight;
    @FXML
    private Label lblblood;
    @FXML
    private Label lblcitizenship;
    @FXML
    private Label lbloccupation;
    @FXML
    private TextField alias;
    @FXML
    private Label lblalias;
    @FXML
    private TextField idenmarks;
    @FXML
    private Label lblidenmarks;
    @FXML
    private ComboBox<String> religion;
    @FXML
    private Label lblreligion;
    @FXML
    private CheckBox pwd;
    @FXML
    private TextField defect;
    @FXML
    private CheckBox pregnant;
    @FXML
    private DatePicker duedate;
    @FXML
    private CheckBox soloparent;
    @FXML
    private Button btnsave;
    @FXML
    private TextField child1;
    @FXML
    private TextField child2;
    @FXML
    private TextField child3;
    @FXML
    private TextField child4;
    @FXML
    private TextField child5;
    @FXML
    private TextField child6;
    @FXML
    private TextField child7;
    @FXML
    private TextField child8;
    @FXML
    private TextField child9;
    @FXML
    private TextField child10;
    @FXML
    private TextField child11;
    @FXML
    private TextField child12;
    @FXML
    private TextField child13;
    @FXML
    private TextField child14;
    @FXML
    private TextField child15;
    @FXML
    private TextArea address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(lastName.getText().length()==0) {lbllast.setText("Last Name *"); lbllast.setTextFill(Color.web("#FF0000"));}
        if(firstName.getText().length()==0) {lblfirst.setText("First Name *"); lblfirst.setTextFill(Color.web("#FF0000"));}
        if(true) {lblsex.setText("Sex *"); lblsex.setTextFill(Color.web("#FF0000"));}
        if(true) {lblcivil.setText("Civil Status *"); lblcivil.setTextFill(Color.web("#FF0000"));}
        if(true) {lblbirth.setText("Birthdate *"); lblbirth.setTextFill(Color.web("#FF0000"));}
        
        lastName.focusedProperty().addListener((arg0, oldValue, newValue) -> { //
        if(lastName.getText().length()==0) {lbllast.setText("Last Name *"); lbllast.setTextFill(Color.web("#FF0000"));}
        if (!newValue) { //when focus lost
            if(!lastName.getText().matches("[a-zA-Z ]*")){
                lbllast.setTextFill(Color.web("#FF0000"));
            }
        }
        if(newValue) {lbllast.setTextFill(Color.web("#000000")); lbllast.setText("Last Name");} //when focused

    });
        firstName.focusedProperty().addListener((arg0, oldValue, newValue) -> {
        if(firstName.getText().length()==0) {lblfirst.setText("First Name *"); lblfirst.setTextFill(Color.web("#FF0000"));}
        if (!newValue) { //when focus lost
            if(!firstName.getText().matches("[a-zA-Z ]*")){
                lblfirst.setTextFill(Color.web("#FF0000"));
            }
        }
        if(newValue) {lblfirst.setTextFill(Color.web("#000000")); lblfirst.setText("First Name");} //when focused

    });
        middleName.focusedProperty().addListener((arg0, oldValue, newValue) -> {
        if (!newValue) { //when focus lost
            if(!middleName.getText().matches("[a-zA-Z ]*")){
                lblmiddle.setTextFill(Color.web("#FF0000"));
            }
        }
        if(newValue) {lblmiddle.setTextFill(Color.web("#000000"));} //when focused

    });
        choiceSex.focusedProperty().addListener((arg0, oldValue, newValue) -> { //sex
        //if(choiceSex.getValue().length()==0) {lblsex.setText("First Name *"); lblsex.setTextFill(Color.web("#FF0000"));}
        if (!newValue) { //when focus lost
            if(!choiceSex.getValue().matches("[a-zA-Z ]*")){
                lblsex.setTextFill(Color.web("#FF0000"));
            }
        }
        if(newValue) {lblsex.setTextFill(Color.web("#000000"));} //when focused

    });
        choiceCivil.focusedProperty().addListener((arg0, oldValue, newValue) -> { //civil
        if (!newValue) { //when focus lost
            if(!choiceCivil.getValue().matches("[a-zA-Z ]*")){
                lblcivil.setTextFill(Color.web("#FF0000"));
            }
        }
        if(newValue) {lblcivil.setTextFill(Color.web("#000000"));} //when focused

    });
        birthdate.focusedProperty().addListener((arg0, oldValue, newValue) -> { //birth
        if (!newValue) { //when focus lost
//            if(!birthdate.getValue().matches("[a-zA-Z ]*")){
//                lblbirth.setTextFill(Color.web("#FF0000"));
//            }
        }
        if(newValue) {lblbirth.setTextFill(Color.web("#000000"));} //when focused

    });
    }    

    @FXML
    private void pwdselected(ActionEvent event) {
    }

    @FXML
    private void pregnantselected(ActionEvent event) {
    }

    @FXML
    private void saveresident(ActionEvent event) {
        
        //Person person = new Person();
        String query = "INSERT INTO person"
                + "(lname,fname,mname,suffix,"
                + "sex,mstatus,birthdate,birthplace,height,weight,"
                + "bloodtype,citizenship,occupation,alias,religion,PWD,pregnant,soloparent,defect,duedate,idenmarks,address,resident)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        if(true) {
            try {
                Connection conn = MySQLConnector.getConnection();
                PreparedStatement ps;
                ps = conn.prepareStatement(query);
                ps.setString(1, lastName.getText()); //NOT NULL
                ps.setString(2, firstName.getText());//NOT NULL
                ps.setString(3, blanktoNULL(middleName.getText()));
                ps.setString(4, blanktoNULL(suffix.getText()));
                ps.setString(5, blanktoNULL(choiceSex.getValue()));
                ps.setString(6, choiceCivil.getValue());//NOT NULL
                ps.setObject(7, birthdate.getValue());//NOT NULL
                ps.setString(8, blanktoNULL(birthplace.getText()));
                ps.setString(9, blanktoNULL(height.getText()));
                ps.setString(10, blanktoNULL(weight.getText()));
                ps.setString(11, blanktoNULL(bloodtype.getText()));
                ps.setString(12, blanktoNULL(citizenship.getText()));
                ps.setString(13, blanktoNULL(occupation.getText()));
                ps.setString(14, blanktoNULL(alias.getText()));
                ps.setString(15, religion.getValue());
                ps.setInt(16, booleantoint(pwd.isSelected()));
                ps.setInt(17, booleantoint(pregnant.isSelected()));
                ps.setInt(18, booleantoint(soloparent.isSelected()));
                ps.setString(19, blanktoNULL(defect.getText()));
                ps.setObject(20, duedate.getValue());
                ps.setString(21, blanktoNULL(idenmarks.getText()));
                ps.setString(22, address.getText());
                ps.setInt(23, 0);

                ps.executeUpdate();
                DialogController.alert("Create Resident", "Successfully Created NON-Resident", Alert.AlertType.INFORMATION);
                Stage stage = (Stage) lastName.getScene().getWindow();
                stage.close();
            } catch (SQLException ella) {
                DialogController.alert("Create Resident", "There was an error. Nothing was changed.", Alert.AlertType.ERROR);
                System.out.println(ella);
            }
        } else {
            DialogController.alert("Error", "There was an error.\n Please check the fields. \n Nothing was changed.", Alert.AlertType.ERROR);
        }
    
    }
    
    private String blanktoNULL(String s) {
        //System.out.print(s);
        if (s.isEmpty()) {
            return null;
        } else {
            return s.toString();
        }
    }
    
    private int booleantoint(Boolean boo) {
        if (boo) {
            return 1;
        } else {
            return 0;
        }
    }
}
