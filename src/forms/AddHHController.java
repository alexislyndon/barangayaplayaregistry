/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import barangay.Household;
import db.MySQLConnector;
import barangay.Person;
import db.SQLHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class AddHHController extends SQLHandler implements Initializable {

    static Household house;
    private ObservableList<Person> list;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, ?> id;
    @FXML
    private TableColumn<Person, ?> last;
    @FXML
    private TableColumn<Person, ?> first;
    @FXML
    private TableColumn<Person, ?> middle;
    @FXML
    private TableColumn<Person, ?> birthdate;
    @FXML
    private TableColumn<Person, ?> sex;
    @FXML
    private TextField lastname;
    @FXML
    private ChoiceBox<String> zoneselector;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectall();
        zoneselector.getItems().add("Zone 1a");
        zoneselector.getItems().add("Zone 1b");
        zoneselector.getItems().add("Zone 2");
        zoneselector.getItems().add("Zone 2u");
        zoneselector.getItems().add("Zone 3");
        zoneselector.getItems().add("Zone 3u");
        zoneselector.getItems().add("Zone 4");
        zoneselector.getItems().add("Zone 5");
    }

    //<editor-fold defaultstate="collapsed" desc="filter by lastname">
    @FXML
    private void filter(ActionEvent event) {
        String query = lastname.getText();
        populateTableView(query);
    }
//</editor-fold>

    /**
     *
     * @param lname
     */
    protected void populateTableView(String lname) { //FILTER BY LASTNAME

        String q = "SELECT * FROM person where lname=?";

        try {
            list = FXCollections.observableArrayList();

//            Connection conn = MySQLConnector.getConnection();
//            PreparedStatement ps;
//            ps = conn.prepareStatement(q);
//            ps.setString(1, lname);
            ResultSet rs = SQLHandler.nameQueryer(q, lname);

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id")); //the ones in " " are column names from db
                person.setLast(rs.getString("lname"));
                person.setFirst(rs.getString("fname"));
                person.setMiddle(rs.getString("mname"));
                person.setSex(rs.getString("sex"));
                person.setBirth(rs.getDate("birthdate"));
                person.setMarital(rs.getString("mstatus"));
                //person.setZone(rs.getString("zone"));
                list.add(person);
            }

            id.setCellValueFactory(new PropertyValueFactory<>("id")); // " " are from person class property names
            last.setCellValueFactory(new PropertyValueFactory<>("last"));
            first.setCellValueFactory(new PropertyValueFactory<>("first"));
            middle.setCellValueFactory(new PropertyValueFactory<>("middle"));
            sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
            birthdate.setCellValueFactory(new PropertyValueFactory<>("birth"));

            table.setItems(list);

        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Populates the table minus those that are already part of a household.
     */
    protected void selectall() { //populates the table minus those that are already part of a household

        String query = "SELECT person.id,person.lname,person.fname,person.mname,person.sex,person.birthdate,person.mstatus FROM person WHERE NOT EXISTS (SELECT * FROM hh_members WHERE hh_members.members = person.id)";

        try {
            list = FXCollections.observableArrayList();
            ResultSet rs = SQLHandler.queryer(query);//ps.executeQuery();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id")); //the ones in " " are column names from db
                person.setLast(rs.getString("lname"));
                person.setFirst(rs.getString("fname"));
                person.setMiddle(rs.getString("mname"));
                person.setSex(rs.getString("sex"));
                person.setBirth(rs.getDate("birthdate"));
                person.setMarital(rs.getString("mstatus"));
                //person.setZone(rs.getString("zone"));
                list.add(person);
            }

            id.setCellValueFactory(new PropertyValueFactory<>("id")); // " " are from person class property names
            last.setCellValueFactory(new PropertyValueFactory<>("last"));
            first.setCellValueFactory(new PropertyValueFactory<>("first"));
            middle.setCellValueFactory(new PropertyValueFactory<>("middle"));
            sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
            birthdate.setCellValueFactory(new PropertyValueFactory<>("birth"));

            table.setItems(list);

        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates a new household from the selected person.
     * @param event 
     * @throws IOException 
     */
    @FXML
    private void selectHead(ActionEvent event) throws IOException {
        AlertType a;
        Person person = table.getSelectionModel().getSelectedItem();
        int i = person.getId();
        DialogController dc = new DialogController();
        
        try {
            String query = "INSERT INTO household(headoffamily,hzone) values(?,?)";
            String query2 = "INSERT INTO hh_members(hh_id, members) values(?,?)";
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps,ps2;
            ps = conn.prepareStatement(query);
            ps.setInt(1, i);
            ps.setInt(2, zonetoint(zoneselector.getValue()));
            ps.executeUpdate();
            
            int hh=-1;
            ResultSet rs = SQLHandler.queryer("household", "headoffamily", i); // get the household#
            while(rs.next()){
                hh = rs.getInt("house_ID");
                ps2 = conn.prepareStatement(query2);
                ps2.setInt(1,hh);
                ps2.setInt(2, i);
                
                ps2.executeUpdate();
            }
            String m = SQLHandler.idtoNAME(i) + "["+ i + "]"+ " Selected as new Household Head of Household # " + hh;
            dc.alert("Household Creation", "Success!", m, AlertType.INFORMATION);
            selectall();
        } catch (SQLException ex) {
            a = AlertType.ERROR;
            dc.alert("nop","nop","nop",a);
            Logger.getLogger(AddHHController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Resets the table results. Shows unfiltered results.
     * @param event 
     */
    @FXML
    private void reset(ActionEvent event) {
        selectall();
    }
    
    /**
     * Facilitates passing of objects.
     * @param house0 
     */
    static void setHouse(Household house0) {
        house = house0;
    }
    
    /**
     * Converts zone string to int. Database helper.
     * @param z
     * @return 
     */
    private int zonetoint(String z) {
        int i;
        switch(z) {
            case "Zone 1a":
                return i = 1;
                
            case "Zone 1b":
                return i = 2;
            case "Zone 2":
                return i = 3;
            case "Zone 2u":
                return i = 4;
            case "Zone 3":
                return i = 5;
            case "Zone 3u":
                return i = 6;
            case "Zone 4":
                return i = 7;
            case "Zone 5":
                return i = 8;
            default:
                return 0; 
        }
    }
}
