/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

import db.SQLHandler;
import java.net.URL;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class PersonpickerController implements Initializable {
    
    private ObservableList<Person> list;
    @FXML
    private TableView<Person> table;
    @FXML
    private MenuItem copyid;
    @FXML
    private ChoiceBox<String> filterby;
    @FXML
    private TextField keyword;
    @FXML
    private TextField first;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> collast;
    @FXML
    private TableColumn<?, ?> colfirst;
    @FXML
    private TableColumn<?, ?> colmiddle;
    @FXML
    private TableColumn<?, ?> colsex;
    @FXML
    private TableColumn<?, ?> colbirth;
    @FXML
    private TableColumn<?, ?> colmarital;
    @FXML
    private Button useselected;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        filterby.getItems().add("id");
        filterby.getItems().add("Last Name");
        filterby.getItems().add("Last + First name");
        populateTableView();
    }    

    @FXML
    private void filter(ActionEvent event) {
        
        try {
            String q="";
//            String query = "SELECT * FROM person WHERE lname=?";
//            String query2 = "SELECT * FROM person WHERE id=?";
            String where;
            if(filterby.getValue() == "id") {
                q = "SELECT * FROM person WHERE id=?";
            }
            else if(filterby.getValue() == "Last Name") {q = "SELECT * FROM person WHERE lname=?";}
            
            else if(filterby.getValue() == "Last + First name") {q = "SELECT * FROM person WHERE lname=? AND fname LIKE ?"; } //if(filterresidentby.getValue() == "Last + First name") 
            
            String is = keyword.getText();
            PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(q);
            ps.setString(1, is);
            if(filterby.getValue() == "Last + First name") ps.setString(2, "%" + first.getText() + "%");
            try {
                ResultSet rs = ps.executeQuery();
                list = FXCollections.observableArrayList();
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
                
                colid.setCellValueFactory(new PropertyValueFactory<>("id")); // " " are from person class property names
                collast.setCellValueFactory(new PropertyValueFactory<>("last"));
                colfirst.setCellValueFactory(new PropertyValueFactory<>("first"));
                colmiddle.setCellValueFactory(new PropertyValueFactory<>("middle"));
                colsex.setCellValueFactory(new PropertyValueFactory<>("sex"));
                colbirth.setCellValueFactory(new PropertyValueFactory<>("birth"));
                colmarital.setCellValueFactory(new PropertyValueFactory<>("marital"));
                
                table.setItems(list);
                
            } catch (SQLException ex) {
                System.out.print(ex);
                Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void reset(ActionEvent event) {
        populateTableView();
    }

    @FXML
    private void use(ActionEvent event) {
        
        System.out.println(event.getSource());
        System.out.println(event.getTarget());
        Person person0 = table.getSelectionModel().getSelectedItem();
        int id = person0.getId();
        String name = person0.getNAME();
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(Integer.toString(id));
        Clipboard.getSystemClipboard().setContent(content);
        //BlotterController.setfields(Integer.toString(id));
        Stage stage = (Stage) useselected.getScene().getWindow();
        stage.close();
    }
    
    /**
     *
     */
    protected void populateTableView() {

        String query = "SELECT * FROM person";
        list = FXCollections.observableArrayList();
        
        
        try {
            ResultSet rs = SQLHandler.queryer(query);
            list = FXCollections.observableArrayList();
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

            colid.setCellValueFactory(new PropertyValueFactory<>("id")); // " " are from person class property names
            collast.setCellValueFactory(new PropertyValueFactory<>("last"));
            colfirst.setCellValueFactory(new PropertyValueFactory<>("first"));
            colmiddle.setCellValueFactory(new PropertyValueFactory<>("middle"));
            colsex.setCellValueFactory(new PropertyValueFactory<>("sex"));
            colbirth.setCellValueFactory(new PropertyValueFactory<>("birth"));
            colmarital.setCellValueFactory(new PropertyValueFactory<>("marital"));

            table.setItems(list);

        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
