/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import db.SQLHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class AddMemberController implements Initializable {

    static Household house;
    private ObservableList<Person> list;
    private ObservableList<Person> list2;
    
    /**
     * Facilitates passing of objects.
     * @param house0 
     */
    static void setHouse(Household house0) {
        house = house0;
    }

    @FXML
    private TextField mem1;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, ?> id;
    @FXML
    private TableColumn<Person, ?> birthdate;
    @FXML
    private TableColumn<?, ?> last;
    @FXML
    private TableColumn<?, ?> first;
    @FXML
    private TableColumn<?, ?> middle;
    @FXML
    private TableColumn<?, ?> sex;
    @FXML
    private TableView<Person> housemembers;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private Label lblhhid;
    @FXML
    private TableColumn<?, ?> memberid;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblhhid.setText(house.getHhid()+ "");
        lefttable();
        memberpop();
    }
    /**
     * Handles the event when a selected person is added to a household.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void addnewmemberhandler(ActionEvent event) throws IOException {
        
        Person person = table.getSelectionModel().getSelectedItem();
        int i = person.getId();
        String query = "INSERT INTO hh_members(hh_id,members) values(?,?)";
        int hhid = house.getHhid();
        try {

            //listhh = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setInt(1, hhid);
            ps.setInt(2, i);
            ps.executeUpdate();
            DialogController.alert("Add Household Member", "Successfully Added " + SQLHandler.idtoNAME(i) + " to Household #" + hhid, Alert.AlertType.INFORMATION);
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
        memberpop();
        lefttable();
    }
    
    /**
     * Handles the button press for filter by last name.
     * @param event 
     */
    @FXML
    private void filter(ActionEvent event) {
        String query = mem1.getText();
        lnamequery(query);
    }

    /**
     * Populates the person table. Persons that don't have households. Filtered by last name.
     * @param lname
     */
    protected void lnamequery(String lname) { //FILTER BY LAST NAME SA ADD-ABLE PERSONS

        String q = "SELECT person.id, person.lname,person.fname, person.mname FROM person WHERE person.lname=? AND NOT EXISTS (SELECT * FROM hh_members WHERE hh_members.members = person.id)";
        //"INSERT INTO hh_members(hh_id,members) values(?,?)";

        try {
            list = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(q);
            ps.setString(1, lname);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id")); //the ones in " " are column names from db
                person.setLast(rs.getString("lname"));
                person.setFirst(rs.getString("fname"));
                person.setMiddle(rs.getString("mname"));
                person.setSex(rs.getString("sex"));
                person.setBirth(rs.getDate("birthdate"));
                person.setMarital(rs.getString("mstatus"));
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
     * Populates the household members table.
     */
    protected void memberpop() { //populate table of members RIGHT TABLE THIS right table

        String query = "select person.id,person.lname,person.fname,person.mname from person left join hh_members on person.id = hh_members.members where hh_members.hh_id = ?";

        try {
            PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(query);
            ps.setInt(1, house.getHhid());
            list2 = FXCollections.observableArrayList();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id")); //the ones in " " are column names from db
                person.setLast(rs.getString("lname"));
                person.setFirst(rs.getString("fname"));
                person.setMiddle(rs.getString("mname"));
                person.setFullNAME(person.getNAME());
                list2.add(person);
            }

            memberid.setCellValueFactory(new PropertyValueFactory<>("id")); // " " are from person class property names
            name.setCellValueFactory(new PropertyValueFactory<>("fullNAME"));
            

            housemembers.setItems(list2);

        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Re-populates the tables. Removes the filters.
     * @param event 
     */
    @FXML
    private void reset(ActionEvent event) {
        lefttable();
        memberpop();
    }
    
    /**
     * Populates the table of persons. Persons that are not attached to a household.
     */
    protected void lefttable() { //choose from this table

        String query = "SELECT person.id,person.lname,person.fname,person.mname,person.sex,person.birthdate,person.mstatus FROM person WHERE NOT EXISTS (SELECT * FROM hh_members WHERE hh_members.members = person.id)";

        try {
            list = FXCollections.observableArrayList();
            ResultSet rs = SQLHandler.queryer(query);

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id")); //the ones in " " are column names from db
                person.setLast(rs.getString("lname"));
                person.setFirst(rs.getString("fname"));
                person.setMiddle(rs.getString("mname"));
                person.setSex(rs.getString("sex"));
                person.setBirth(rs.getDate("birthdate"));
                person.setMarital(rs.getString("mstatus"));
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
     * Handles the deletion of a household member.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Person person = housemembers.getSelectionModel().getSelectedItem();
        int i = person.getId();
        int hhid = house.getHhid();
        String query = "DELETE FROM hh_members WHERE members=?";
        PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(query);
        ps.setInt(1, i);
        if(DialogController.alertconfirm("Delete Household Member", "Are you sure you want to delete this member?", person.getNAME() + " from Household# "+ hhid)) {
            ps.executeUpdate();
            DialogController.alert("Delete Household Member", "Successfully Deleted "+ person.getNAME() + " from Household# "+ hhid, Alert.AlertType.INFORMATION);
            lefttable();
            memberpop();
        } else DialogController.alert("Delete Household Member", "Nothing was deleted.", Alert.AlertType.INFORMATION);
    }

}
