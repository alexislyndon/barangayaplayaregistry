/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

import static barangay.PermitController.person0;
import db.SQLHandler;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class EditResidentController implements Initializable {

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
    private TextField alias;
    @FXML
    private TextField idenmarks;
    @FXML
    private ComboBox<String> religion;
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
    private Label id;
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
    private TextField house;
    @FXML
    private TextField street;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setText(Integer.toString(person0.getId()));
        lastName.setText(person0.getLast());
        firstName.setText(person0.getFirst());
        middleName.setText(person0.getMiddle());
        String query = "SELECT * FROM person WHERE id=?";
        try {
            PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(query);
            ps.setInt(1, person0.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                choiceSex.setValue(rs.getString("sex"));
                choiceCivil.setValue(rs.getString("mstatus"));
                birthdate.setValue(LocalDate.parse(rs.getDate("birthdate").toString()));
                birthplace.setText(rs.getString("birthplace"));
                height.setText(rs.getString("height"));
                weight.setText(rs.getString("weight"));
                bloodtype.setText(rs.getString("bloodtype"));
                citizenship.setText(rs.getString("citizenship"));
                occupation.setText(rs.getString("occupation"));
                alias.setText(rs.getString("alias"));
                idenmarks.setText(rs.getString("idenmarks"));
                religion.setValue(rs.getString("religion"));
                pregnant.setSelected(intoboolean(rs.getInt("pregnant")));
                pwd.setSelected(intoboolean(rs.getInt("PWD")));
                soloparent.setSelected(intoboolean(rs.getInt("soloparent")));
                house.setText(rs.getString("house"));
                street.setText(rs.getString("street"));
                
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(EditResidentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saveresident(ActionEvent event) {
        //Person person = new Person();
        String query = "INSERT INTO person"
                + "(lname,fname,mname,suffix,"
                + "sex,mstatus,birthdate,birthplace,height,weight,"
                + "bloodtype,citizenship,occupation,alias,religion,PWD,pregnant,soloparent,defect,duedate,idenmarks,house,street)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setString(1, lastName.getText()); //NOT NULL
            ps.setString(2, firstName.getText());//NOT NULL
            ps.setString(3, middleName.getText());
            ps.setString(4, suffix.getText());
            ps.setString(5, choiceSex.getValue());
            ps.setString(6, choiceCivil.getValue());//NOT NULL
            ps.setObject(7, birthdate.getValue());//NOT NULL
            ps.setString(8, birthplace.getText());
            ps.setString(9, height.getText());
            ps.setString(10, weight.getText());
            ps.setString(11, bloodtype.getText());
            ps.setString(12, citizenship.getText());
            ps.setString(13, occupation.getText());
            ps.setString(14, alias.getText());
            ps.setString(15, religion.getValue());
            ps.setInt(16, booleantoint(pwd.isSelected()));
            ps.setInt(17, booleantoint(pregnant.isSelected()));
            ps.setInt(18, booleantoint(soloparent.isSelected()));
            ps.setString(19, defect.getText());
            ps.setObject(20, duedate.getValue());
            ps.setString(21, idenmarks.getText());
            ps.setString(22, house.getText());
            ps.setString(23, street.getText());

            ps.executeUpdate();
            DialogController.alert("Update Resident", "Successfully Updated Resident", Alert.AlertType.INFORMATION);
        } catch (SQLException ella) {
            DialogController.alert("Update Resident", "There was an error. Nothing was changed.", Alert.AlertType.ERROR);
            System.out.println(ella.getMessage());
        }
    }
    
    private int booleantoint(Boolean boo) {
        if (boo) {
            return 1;
        } else {
            return 0;
        }
    }
    
    private boolean intoboolean (int i) {
        if(i == 1) return true;
        else return false;
    }

    @FXML
    private void pwdselected(ActionEvent event) {
        if (pwd.isSelected()) {
            defect.setDisable(false);
        } else {
            defect.setDisable(true);
        }
    }
    
    @FXML
    private void pregnantselected(ActionEvent event) {
        if (pregnant.isSelected()) {
            duedate.setDisable(false);
        } else {
            duedate.setDisable(true);
        }
    }
    /**
     * Converts blank fields to null. Database helper.
     * @param s
     * @return 
     */
    private String blanktoNULL(String s) {
        //System.out.print(s);
        if (s.isEmpty()) {
            return null;
        } else {
            return s.toString();
        }
    }
    
    /**
     * Converts Date to LocalDate. Used by DatePicker javafx.
     * @param dateToConvert
     * @return
     */
    public LocalDate convDate(Date dateToConvert) {
    return dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
    }
    
}
