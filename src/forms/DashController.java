/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import barangay.Barangay;
import barangay.Blotter;
import barangay.Document;
import barangay.Household;
import db.MySQLConnector;
import barangay.Person;
import barangay.Request;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import barangay.UserAuth.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import db.SQLHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import db.SQLHandler;
import java.util.Comparator;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author axis
 */
public class DashController extends SQLHandler implements Initializable {

    @FXML
    private Button residentButton;
    @FXML
    private Button householdButton;
    @FXML
    private Button zoneButton;
    @FXML
    private Button documentsButton;
    @FXML
    private Button blotterButton;
    @FXML
    private Button requestButton;
    @FXML
    private Pane searchPane;
    @FXML
    private Pane residentPane;
    @FXML
    private Pane householdPane;
    @FXML
    private Pane zonePane;
    @FXML
    private Pane documentsPane;
    @FXML
    private Pane blotterPane;
    @FXML
    private Pane requestPane;
    @FXML
    private Pane homePane;
    @FXML
    private Button home;

    private Barangay application;
    @FXML
    private Button searchButton;
    @FXML
    private Button logoutbutton;
    @FXML
    private Label welcome;
    @FXML
    private Label tile1;
    @FXML
    private Label tile2;
    @FXML
    private Label tile3;
    @FXML
    private Label tile4;
    @FXML
    private Label tile5;
    @FXML
    private Label tile6;
    @FXML
    private Label tile7;
    @FXML
    private Label tile8;
    SQLHandler sqlh;
    @FXML
    private TableView<Person> tableviewperson;
    @FXML
    private TableColumn<Person, ?> colid;
    @FXML
    private TableColumn<Person, ?> collast;
    @FXML
    private TableColumn<Person, ?> colfirst;
    @FXML
    private TableColumn<Person, ?> colmiddle;
    @FXML
    private TableColumn<Person, ?> colsex;
    @FXML
    private TableColumn<Person, ?> colbirth;
    @FXML
    private TableColumn<Person, ?> mstatus;

    private ObservableList<Person> list;
    private ObservableList<Blotter> blist;
    private ObservableList<Request> rlist;
    private ObservableList<Household> listhh;
    private ObservableList<String> listmembers;
    private ObservableList<Household> listmembers100;
    
    private ObservableList<Document> certlista;
    private ObservableList<Document> clearlista;
    private ObservableList<Document> permlista;
    @FXML
    private TextField searchhhid;
    @FXML
    private TableView<Household> tablehh;
    @FXML
    private TableColumn<Household, ?> hhid;
    @FXML
    private TableColumn<Household, ?> hhhead;
    private TableColumn<Household, ?> hhcount;
    @FXML
    private TableColumn<Household, ?> hhzone;
    @FXML
    private ListView<Household> hhmem;

    /**
     * Used in Dash to populate household members list
     */
    protected ListProperty<String> listProperty = new SimpleListProperty<>();

    String query1 = "SELECT COUNT(*) FROM person";
    String query2 = "SELECT COUNT(*) FROM household";
    String query3 = "SELECT COUNT(*) FROM zone";
    String query4 = "SELECT COUNT(*) FROM permits";
    String query5 = "SELECT COUNT(*) FROM clearance";
    String query6 = "SELECT COUNT(*) FROM certificates";
    String query7 = "SELECT COUNT(*) FROM incident";
    String query8 = "SELECT COUNT(*) FROM request";
    @FXML
    private TableView<Blotter> blottertable;
    @FXML
    private TableColumn<Blotter, ?> bID;
    @FXML
    private TableColumn<Blotter, ?> bstatus;
    @FXML
    private TableColumn<Blotter, ?> btype;
    @FXML
    private TableColumn<Blotter, ?> complainant;
    @FXML
    private TableColumn<Blotter, ?> respondent;
    @FXML
    private TableColumn<Blotter, ?> victim;
    @FXML
    private TextField filterresidentkeyword;
    @FXML
    private ChoiceBox<String> filterresidentby;
    
    @FXML
    private TextField firstnamekeyword;
    @FXML
    private Button a;
    @FXML
    private Button b;
    @FXML
    private Button c;
    @FXML
    private Button d;
    @FXML
    private Button e;
    @FXML
    private Button f;
    @FXML
    private Button g;
    @FXML
    private Button h;
    @FXML
    private PieChart zpie;
    @FXML
    private TextField reqkey;
    @FXML
    private TextField reqfirst;
    @FXML
    private ChoiceBox<String> reqfilterchoice;
    @FXML
    private TableView<Request> requesttable;
    @FXML
    private TableColumn<?, ?> rid;
    @FXML
    private TableColumn<?, ?> rdate;
    @FXML
    private TableColumn<?, ?> rrequester;
    @FXML
    private TableColumn<?, ?> rrequest;
    @FXML
    private TableColumn<?, ?> rneed;
    @FXML
    private TableColumn<?, ?> rstatus;
    @FXML
    private TextField blotterkey;
    @FXML
    private TextField blotterfirst;
    @FXML
    private ChoiceBox<String> blotterfilterchoice;
    @FXML
    private ChoiceBox<String> bcompresp;
    @FXML
    private TableView<Document> clearancetable;
    @FXML
    private TableView<Document> permittable;
    @FXML
    private TableView<Document> certificatestable;
    @FXML
    private Label logoutlabel;
    @FXML
    private TextField documentkeyword;
    @FXML
    private Button documentsearch;
    @FXML
    private TableColumn<?, ?> id2;
    @FXML
    private TableColumn<?, ?> a2;
    @FXML
    private TableColumn<?, ?> d3;
    @FXML
    private TableColumn<?, ?> id3;
    @FXML
    private TableColumn<?, ?> a3;
    @FXML
    private TableColumn<?, ?> id1;
    @FXML
    private TableColumn<?, ?> a1;
    @FXML
    private TableColumn<?, ?> d1;
    @FXML
    private TableColumn<?, ?> d2;
    @FXML
    private Button documentsearch1;
    @FXML
    private Button documentsearch2;
    @FXML
    private static AnchorPane dashboard;
    private static Window owner;
    public static void setWindow(Window w) {
        owner = w;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcome.setText("Welcome  <" + User.getUser() + ">");
        hide();
        homePane.setVisible(true);
        refresh();
        logoutlabel.setText("Copyright 2019");
        filterresidentby.getItems().add("id");
        filterresidentby.getItems().add("Last Name");
        filterresidentby.getItems().add("Last + First name");
        
        reqfilterchoice.getItems().add("id");
        reqfilterchoice.getItems().add("Last Name");
        reqfilterchoice.getItems().add("Last + First name");

        blotterfilterchoice.getItems().add("id");
        blotterfilterchoice.getItems().add("Last Name");
        blotterfilterchoice.getItems().add("Last + First name");
        
        bcompresp.getItems().add("Complainant");
        bcompresp.getItems().add("Respondent");
        bcompresp.getItems().add("Victim");
    }
    
    /**
     * Sets the text of the tiles to show the number of records per section. Residents/Households/Documents
     */
    private void tilesetter() {
        tile1.setText(Integer.toString(rowcounter(query1)));
        tile2.setText(Integer.toString(rowcounter(query2)));
        tile3.setText(Integer.toString(rowcounter(query3)));
        tile4.setText(Integer.toString(rowcounter(query4) + rowcounter(query5) + rowcounter(query6))); //query4+5+6 = doc count
        tile5.setText(Integer.toString(rowcounter(query7))); //blotter count is query7
        tile6.setText(Integer.toString(rowcounter(query8)));
        tile7.setText(Integer.toString(rowcounter(query1) + rowcounter(query2) + rowcounter(query3) + rowcounter(query4) + rowcounter(query5) + rowcounter(query6) + rowcounter(query7) + rowcounter(query8)));
//        tile8.setText(Integer.toString(rowcounter(query8)));
    } //homepane
    
    /**
     * Counts the rows in a table
     * @param query 
     * @return Returns the number of rows or records in a table.
     */
    private int rowcounter(String query) {
        int i;
        try {
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i = rs.getInt(1);
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex);
        }
        return -1;
    } //homepane
    
    @FXML
    private void residentMenu(ActionEvent event) {
        hide();
        residentPane.setVisible(true);
    }//dash

    @FXML
    private void householdMenu(ActionEvent event) {
        hide();
        householdPane.setVisible(true);
    }//dash

    @FXML
    private void zoneMenu(ActionEvent event) throws SQLException {
        hide();
        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
            new PieChart.Data("Zone 1a", zonecounter(1)),
            new PieChart.Data("Zone 1b", zonecounter(2)),
            new PieChart.Data("Zone 2", zonecounter(3)),
            new PieChart.Data("Zone 2u", zonecounter(4)),
            new PieChart.Data("Zone 3", zonecounter(5)),
            new PieChart.Data("Zone 3u", zonecounter(6)),
            new PieChart.Data("Zone 4", zonecounter(7)),
            new PieChart.Data("Zone 5", zonecounter(8)));
            zpie.setData(pieChartData);
            zonePane.setVisible(true);
    }//dash

    @FXML
    private void documentsMenu(ActionEvent event) {
        hide();
        documentsPane.setVisible(true);
    }//dash

    @FXML
    private void blotterMenu(ActionEvent event) {
        hide();
        blotterPane.setVisible(true);
    }//dash

    @FXML
    private void requestMenu(ActionEvent event) {
        hide();
        requestPane.setVisible(true);
    }//dash

    @FXML
    private void searchMenu(ActionEvent event) {
        hide();
        searchPane.setVisible(true);
    }//dash

    @FXML
    private void homeMenu(ActionEvent event) throws SQLException {
        refresh();
        hide();
        homePane.setVisible(true);
    }//dash

    /**
     * Hides all Stacked Panes. Facilitates showing of Panes 1 at a time.
     */
    private void hide() {
        residentPane.setVisible(false);
        householdPane.setVisible(false);
        zonePane.setVisible(false);
        documentsPane.setVisible(false);
        blotterPane.setVisible(false);
        requestPane.setVisible(false);
        searchPane.setVisible(false);
        homePane.setVisible(false);
    } //dash

    @FXML
    private void logouthandler(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }//dash

    @FXML
    private void newResHandler(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/CreateResident.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } //dash

    /**
     * Populates Resident table.
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
                String last = rs.getString("lname");
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
            mstatus.setCellValueFactory(new PropertyValueFactory<>("marital"));
            
            tableviewperson.setItems(list);

        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } //residentpane

    /**
     * Refreshes all tables in the Dashboard.
     */
    @FXML
    public void refresh() {
        populatehh();
        populateblottertable();
        populateTableView();
        tilesetter();
        populaterequesttable();
        populatepermit();
        populatecertificate();
        populateclearance();
    } //dash
    
    /**
     * Converts int to String.
     * @param x
     * @return A string converted from int
     */
    private String inttostring(int x) {
        String s = Integer.toString(x);
        return s;
    } //dash

    @FXML
    private void createcertificateforperson(ActionEvent event) throws IOException {
        Person person0 = tableviewperson.getSelectionModel().getSelectedItem();
        int id = person0.getId();
        String name = person0.getNAME();
        System.out.println("Selected item: " + name);
        System.out.println("Selected item: " + id);

        CertificateController.setPerson(person0);
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Certificate.fxml"));

        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } //residentpane

    @FXML
    private void createclearanceperson(ActionEvent event) throws IOException {
        Person person0 = tableviewperson.getSelectionModel().getSelectedItem();

        ClearanceController.setPerson(person0);
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Clearance.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } //residentpane

    @FXML
    private void createpermitperson(ActionEvent event) throws IOException {
        Person person0 = tableviewperson.getSelectionModel().getSelectedItem();

        PermitController.setPerson(person0);
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Permit.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } //residentpane

    @FXML
    private void createrequestperson(ActionEvent event) throws IOException {
        Person person0 = tableviewperson.getSelectionModel().getSelectedItem();

        RequestController.setPerson(person0);
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Request.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } //residentpane

    @FXML
    private void searchhhid(ActionEvent event) {
        tablehhsearch();
    } //householdpane

    @FXML
    private void newHouseHandler(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/AddHH.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } //householdpane

    @FXML
    private void addhhmember(ActionEvent event) throws IOException {//add household member button
        Household house0 = tablehh.getSelectionModel().getSelectedItem(); //add household member na window pero ang selection gikan sa tableview sa leftside

        AddMemberController.setHouse(house0);
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/AddMember.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } //householdpane

    @FXML
    private void selecthh(ActionEvent event) { //>>
        Household hh = tablehh.getSelectionModel().getSelectedItem();
        int hhid = hh.getHhid();
        String query = "SELECT * FROM hh_members where hh_id=?";
        String query2 = "SELECT * FROM person where id=?";
        try {
            listmembers = FXCollections.observableArrayList();
            listmembers100 = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setInt(1, hhid);
            ResultSet rs = ps.executeQuery(); //1 selected from query with many mem

            while (rs.next()) { //iterate 1 time kay 1 household
                //hh.setHhid(hhid);
                System.out.println("YAWA");
                PreparedStatement ps2 = conn.prepareStatement(query2);
                ResultSet rs2;
                int mID = rs.getInt("members");
                ps2.setInt(1, mID);
                rs2 = ps2.executeQuery(); //rs kay person
                String NAME;
                while (rs2.next()) { //iterate many times kay many members
                    System.out.println("pisti");
                    String first = rs2.getString("fname");
                    String middle = rs2.getString("mname");
                    String last = rs2.getString("lname");
                    NAME = first + " " + middle + " " + last;
                    listmembers.add(NAME);
                }
                hh.setMembers(listmembers);
                listmembers100.add(hh);
            }
            hhmem.itemsProperty().bind(hh.membersProperty());
            hh.membersProperty().set(FXCollections.observableArrayList(listmembers));
            System.out.println(hh.getMembers());
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } //householdpane household members populate
    
    /**
     * Populates the household table.
     */
    private void populatehh() { //populate household heads
        String query = "SELECT * FROM household";
        String query2 = "SELECT * FROM person where id=?";
        //String q = "SELECT COUNT(hh_id) FROM hh_members where hh_id=?";
        try {
            listhh = FXCollections.observableArrayList();
            ResultSet rs = SQLHandler.queryer(query);
            

            while (rs.next()) {
                PreparedStatement ps2 = MySQLConnector.getConnection().prepareStatement(query2);
                
                ResultSet rs2;
                Household hh = new Household();
                hh.setHhid(rs.getInt("house_ID"));
                hh.setZone(rs.getString("hzone"));
                hh.setZonename(rs.getInt("hzone"));
                //hh.setZonename(hh.zoneinttoString(rs.getInt("hzone")));
                int personid = rs.getInt("headoffamily");
                ps2.setInt(1, personid);
                rs2 = ps2.executeQuery();
                String NAME;
                while (rs2.next()) {
                    String first = rs2.getString("fname");
                    String middle = rs2.getString("mname");
                    String last = rs2.getString("lname");
                    NAME = first + " " + middle + " " + last;
                    hh.setHead(NAME);
                }
                listhh.add(hh);
            }
            
            hhid.setCellValueFactory(new PropertyValueFactory<>("hhid")); // " " are from person class property names
            hhhead.setCellValueFactory(new PropertyValueFactory<>("head"));
            hhzone.setCellValueFactory(new PropertyValueFactory<>("zonename"));

            tablehh.setItems(listhh);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } //householdpane
    
    /**
     * Filter household by family name.
     */
    private void tablehhsearch() { //filter household by chuchu
//        String query = "SELECT * FROM household";
//        String query2 = "SELECT id FROM person where lname=?";
//        String q = "SELECT person.id, household.house_ID, person.lname, person.fname, household.headoffamily, person.mname\n"
//                + "FROM person,household\n"
//                + "WHERE person.lname = ?";
        String q = "SELECT person.id, person.lname, person.fname, person.mname, household.house_ID, household.headoffamily\n" +
"FROM person,household\n" +
"WHERE household.headoffamily=person.id AND lname=? ";
        try {
            listhh = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(q);
            ps.setString(1, searchhhid.getText());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Household hh = new Household();
                hh.setHhid(rs.getInt("house_ID"));
                int personid = rs.getInt("headoffamily");
                String NAME;

                String first = rs.getString("fname");
                String middle = rs.getString("mname");
                String last = rs.getString("lname");
                NAME = first + " " + middle + " " + last;
                hh.setHead(NAME);
                listhh.add(hh);
            }

            hhid.setCellValueFactory(new PropertyValueFactory<>("hhid")); // " " are from person class property names
            hhhead.setCellValueFactory(new PropertyValueFactory<>("head"));
            hhcount.setCellValueFactory(new PropertyValueFactory<>("hcount"));
            hhzone.setCellValueFactory(new PropertyValueFactory<>("zone"));

            tablehh.setItems(listhh);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } //householdpane

    @FXML
    private void newBlotterHandler(ActionEvent event) throws IOException { //new blotter
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Blotter.fxml"));
        Parent blah = loader.load();//FXMLLoader.load(getClass().getResource("/fxml/Blotter.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } //blotterpane
    
    /**
     * Populates the blotter table.
     */
    private void populateblottertable() {

        String query = "SELECT * FROM incident";
        try {
            blist = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Blotter bl = new Blotter();
                bl.setBlotterID(rs.getInt("blotterID")); //the ones in " " are column names from db
                bl.setNarrative(rs.getString("narrative"));
                bl.setIncidentDate(rs.getDate("incident_when"));
                bl.setComplainant(SQLHandler.idtoNAME(rs.getInt("complainant")));
                bl.setRespondent(SQLHandler.idtoNAME(rs.getInt("respondent")));
                bl.setType(rs.getString("incident_type"));
                bl.setStatus(rs.getString("status"));
                bl.setVictim(SQLHandler.idtoNAME(rs.getInt("victim")));
                blist.add(bl);
            }

            bID.setCellValueFactory(new PropertyValueFactory<>("blotterID")); // " " are from person class property names
            bstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            btype.setCellValueFactory(new PropertyValueFactory<>("type"));
            complainant.setCellValueFactory(new PropertyValueFactory<>("complainant"));
            respondent.setCellValueFactory(new PropertyValueFactory<>("respondent"));
            victim.setCellValueFactory(new PropertyValueFactory<>("victim"));

            blottertable.setItems(blist);

        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } //blotterpane

    @FXML
    private void newrequest(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/Request.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } //requestpane

    @FXML
    private void deleteHousehold(ActionEvent event) throws SQLException {
        Household hh = tablehh.getSelectionModel().getSelectedItem();
        int hhid = hh.getHhid();
        String head = hh.getHead();
        if(DialogController.alert("Delete Household?", "Are you sure you want to delete this household? \nDeleting this household will also detach all members of this household.", "Household #" + Integer.toString(hhid) + " Head: " + head)) {
            String q = "DELETE FROM household WHERE house_ID = " + Integer.toString(hhid);
            SQLHandler.updater(q);
            refresh();
        } else DialogController.alert("Dialog", "Nothing was deleted", AlertType.INFORMATION);
    } //householdpane

    @FXML
    private void residentfilter(ActionEvent event) {
        try {
            String q="";
//            String query = "SELECT * FROM person WHERE lname=?";
//            String query2 = "SELECT * FROM person WHERE id=?";
            String where;
            if(filterresidentby.getValue() == "id") {
                q = "SELECT * FROM person WHERE id=?";
            }
            else if(filterresidentby.getValue() == "Last Name") {q = "SELECT * FROM person WHERE lname=?";}
            
            else if(filterresidentby.getValue() == "Last + First name") {q = "SELECT * FROM person WHERE lname=? AND fname LIKE ?"; } //if(filterresidentby.getValue() == "Last + First name") 
            
            String is = filterresidentkeyword.getText();
            PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(q);
            ps.setString(1, is);
            if(filterresidentby.getValue() == "Last + First name") ps.setString(2, "%" + firstnamekeyword.getText() + "%");
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
                mstatus.setCellValueFactory(new PropertyValueFactory<>("marital"));
                
                tableviewperson.setItems(list);
                
            } catch (SQLException ex) {
                System.out.print(ex);
                Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } //residentpane

    @FXML
    private void zonestats(ActionEvent event) {
        System.out.println(event.getSource());
        System.out.println(event.getTarget());
        System.out.println(event.getClass());
        System.out.println(event.getEventType());
    } //residentpane

    @FXML
    private void editperson(ActionEvent event) throws IOException {
        Person person0 = tableviewperson.getSelectionModel().getSelectedItem();

        RequestController.setPerson(person0);
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/EditResident.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void deleteperson(ActionEvent event) throws IOException, SQLException {
        Person person0 = tableviewperson.getSelectionModel().getSelectedItem();
        if(DialogController.alert("Delete Person", "Are you sure you want to delete this person? ",person0.getNAME() +" [" + person0.getId()+"]")) {
            String q = "DELETE FROM person where id=?";
            PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(q);
            ps.setInt(1, person0.getId());
            ps.executeUpdate();
            DialogController.alert("Delete Resident", "Successfully Deleted Resident.", AlertType.INFORMATION);
        } else DialogController.alert("Delete Resident", "Nothing was deleted.", AlertType.INFORMATION);
    }
    
    /**
     *
     * @param zoneid
     * @return Total number of inhabitants in a zone.
     * @throws SQLException
     */
    public int zonecounter(int zoneid) throws SQLException {
        String query = "SELECT count(*) FROM household INNER JOIN hh_members ON household.house_ID = hh_members.hh_id where hzone=?";
        PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(query);
        ps.setInt(1, zoneid);
        ResultSet rs = ps.executeQuery();
        int i;
        while(rs.next()) {
            
            i = rs.getInt(1);
            return i;
        } return -1;
    }

    @FXML
    private void setZone(ActionEvent event) throws SQLException {
        Household hh = tablehh.getSelectionModel().getSelectedItem();
        int hid = hh.getHhid();
        int hzon = DialogController.choicedialog();
        if(hzon != 0) {
            String qq = "UPDATE household SET hzone = ? WHERE house_ID = ?";
            PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(qq);
            ps.setInt(1, hzon);
            ps.setInt(2, hid);
            ps.executeUpdate();
            refresh();
        }
    }

    @FXML
    private void reqfilter(ActionEvent event) { //request filter
        try {
            String q=""; ///////////////////////////////////////
            String where;
            if(reqfilterchoice.getValue() == "id") {
                q = "SELECT * FROM request WHERE request_ID=?";
            }
            else if(reqfilterchoice.getValue() == "Last Name") {q = "select * from person inner join request ON request.requester = person.id where person.lname=?";}
            
            else if(reqfilterchoice.getValue() == "Last + First name") {q = "select * from person inner join request ON request.requester = person.id where person.lname=? AND person.fname like ?"; } //if(filterresidentby.getValue() == "Last + First name") 
            
            String is = reqkey.getText();
            PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(q);
            ps.setString(1, is);
            if(reqfilterchoice.getValue() == "Last + First name") ps.setString(2, "%" + reqfirst.getText() + "%");
            
            rlist = FXCollections.observableArrayList();
            ResultSet rs = ps.executeQuery(); /////////////////////////////////

            while (rs.next()) {
                Request r1 = new Request();
                r1.setReqID(rs.getInt("request_ID")); //the ones in " " are column names from db
                r1.setDateRequested(rs.getDate("date_requested"));
                r1.setRequesterNAME(SQLHandler.idtoNAME(rs.getInt("requester")));
                r1.setRequest(rs.getString("request_details"));
                r1.setDateneeded(rs.getDate("date_time_needed"));
                r1.setReqstatus(rs.getString("status"));
                rlist.add(r1);
            }

            rid.setCellValueFactory(new PropertyValueFactory<>("reqID")); // " " are from person class property names
            rdate.setCellValueFactory(new PropertyValueFactory<>("dateRequested"));
            rrequester.setCellValueFactory(new PropertyValueFactory<>("requesterNAME"));
            rrequest.setCellValueFactory(new PropertyValueFactory<>("request"));
            rneed.setCellValueFactory(new PropertyValueFactory<>("dateneeded"));
            rstatus.setCellValueFactory(new PropertyValueFactory<>("reqstatus"));

            requesttable.setItems(rlist);  
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    /**
     * Populates the request table.
     */
    private void populaterequesttable() {
        

        String query = "SELECT * FROM request";
        try {
            rlist = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Request r1 = new Request();
                r1.setReqID(rs.getInt("request_ID")); //the ones in " " are column names from db
                r1.setDateRequested(rs.getDate("date_requested"));
                r1.setRequesterNAME(SQLHandler.idtoNAME(rs.getInt("requester")));
                r1.setRequest(rs.getString("request_details"));
                r1.setDateneeded(rs.getDate("date_time_needed"));
                r1.setReqstatus(rs.getString("status"));
                rlist.add(r1);
            }

            rid.setCellValueFactory(new PropertyValueFactory<>("reqID")); // " " are from person class property names
            rdate.setCellValueFactory(new PropertyValueFactory<>("dateRequested"));
            rrequester.setCellValueFactory(new PropertyValueFactory<>("requesterNAME"));
            rrequest.setCellValueFactory(new PropertyValueFactory<>("request"));
            rneed.setCellValueFactory(new PropertyValueFactory<>("dateneeded"));
            rstatus.setCellValueFactory(new PropertyValueFactory<>("reqstatus"));

            requesttable.setItems(rlist);
            
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void blotterfilter(ActionEvent event) throws SQLException {
        
        String q=""; ///////////////////////////////////////
            
            if(blotterfilterchoice.getValue() == "id") {
                q = "SELECT * FROM incident WHERE blotterID=?";
            }
            else if(blotterfilterchoice.getValue() == "Last Name" && bcompresp.getValue()=="Complainant") {q = "select * from person inner join incident on incident.complainant = person.id where person.lname=?";}
            
            else if(blotterfilterchoice.getValue() == "Last + First name"&& bcompresp.getValue()=="Complainant") {q = "select * from person inner join incident on incident.complainant = person.id where person.lname=? AND person.fname like ?"; } //if(filterresidentby.getValue() == "Last + First name") 
            
            else if(blotterfilterchoice.getValue() == "Last Name" && bcompresp.getValue()=="Respondent") {q = "select * from person inner join incident on incident.respondent = person.id where person.lname=?";}
            
            else if(blotterfilterchoice.getValue() == "Last + First name"&& bcompresp.getValue()=="Respondent") {q = "select * from person inner join incident on incident.respondent = person.id where person.lname=? AND person.fname like ?"; } //if(filterresidentby.getValue() == "Last + First name") 

            else if(blotterfilterchoice.getValue() == "Last Name" && bcompresp.getValue()=="Victim") {q = "select * from person inner join incident on incident.victim = person.id where person.lname=?";}
            
            else if(blotterfilterchoice.getValue() == "Last + First name"&& bcompresp.getValue()=="Victim") {q = "select * from person inner join incident on incident.victim = person.id where person.lname=? AND person.fname like ?"; } //if(filterresidentby.getValue() == "Last + First name") 

            
            String is = blotterkey.getText();
            PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(q);
            ps.setString(1, is);
            if(blotterfilterchoice.getValue() == "Last + First name") ps.setString(2, "%" + blotterfirst.getText() + "%");
            
            blist = FXCollections.observableArrayList();
            ResultSet rs = ps.executeQuery(); /////////////////////////////////

        try {
            blist = FXCollections.observableArrayList();
            while (rs.next()) {
                Blotter bl = new Blotter();
                bl.setBlotterID(rs.getInt("blotterID")); //the ones in " " are column names from db
                bl.setNarrative(rs.getString("narrative"));
                bl.setIncidentDate(rs.getDate("incident_when"));
                bl.setComplainant(SQLHandler.idtoNAME(rs.getInt("complainant")));
                bl.setRespondent(SQLHandler.idtoNAME(rs.getInt("respondent")));
                bl.setType(rs.getString("incident_type"));
                bl.setStatus(rs.getString("status"));
                bl.setVictim(SQLHandler.idtoNAME(rs.getInt("victim")));
                blist.add(bl);
            }

            bID.setCellValueFactory(new PropertyValueFactory<>("blotterID")); // " " are from person class property names
            bstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            btype.setCellValueFactory(new PropertyValueFactory<>("type"));
            complainant.setCellValueFactory(new PropertyValueFactory<>("complainant"));
            respondent.setCellValueFactory(new PropertyValueFactory<>("respondent"));
            victim.setCellValueFactory(new PropertyValueFactory<>("victim"));

            blottertable.setItems(blist);

        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Populates the certificate table with all records from the certificate table.
     */
    public void populatecertificate() {
        String query = "SELECT * FROM certificates";
        try {
            certlista = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Document d1 = new Document();
                d1.setCertID(rs.getInt("certificate_ID")); //the ones in " " are column names from db
                d1.setApplicant(SQLHandler.idtoNAME(rs.getInt("applicant")));
                d1.setDate( rs.getDate("dateIssued"));
                certlista.add(d1);
            }

            id1.setCellValueFactory(new PropertyValueFactory<>("ID")); // " " are from person class property names
            a1.setCellValueFactory(new PropertyValueFactory<>("applicant"));
            d1.setCellValueFactory(new PropertyValueFactory<>("date"));
            

            certificatestable.setItems(certlista);
            
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Populates the clearance table with all records from the clearance table.
     */
    public void populateclearance() {
        String query = "SELECT * FROM clearance";
        try {
            clearlista = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Document d2 = new Document();
                d2.setCertID(rs.getInt("clearance_no")); //the ones in " " are column names from db
                d2.setApplicant(SQLHandler.idtoNAME(rs.getInt("applicant")));
                d2.setDate( rs.getDate("dateIssued"));
                clearlista.add(d2);
            }

            id2.setCellValueFactory(new PropertyValueFactory<>("ID")); // " " are from person class property names
            a2.setCellValueFactory(new PropertyValueFactory<>("applicant"));
            d2.setCellValueFactory(new PropertyValueFactory<>("date"));
            

            clearancetable.setItems(clearlista);
            
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Populates the permit table with all records from the permit table.
     */
    public void populatepermit() {
        
        String query = "SELECT * FROM permits";
        try {
            permlista = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Document d3 = new Document();
                d3.setCertID(rs.getInt("permit_no")); //the ones in " " are column names from db
                d3.setApplicant(SQLHandler.idtoNAME(rs.getInt("applicant")));
                d3.setDate( rs.getDate("dateIssued"));
                permlista.add(d3);
            }

            id3.setCellValueFactory(new PropertyValueFactory<>("ID")); // " " are from person class property names
            a3.setCellValueFactory(new PropertyValueFactory<>("applicant"));
            d3.setCellValueFactory(new PropertyValueFactory<>("date"));
            

            permittable.setItems(permlista);
            
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    /**
     * Searches the certificate table to match a person's id. Populates the certificate table in the Documents Pane
     */
    @FXML
    public void search1() {
        String q = "SELECT * FROM certificates where applicant=?";
        String id = documentkeyword.getText();
        try {
            certlista = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(q);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Document d1 = new Document();
                d1.setCertID(rs.getInt("certificate_ID")); //the ones in " " are column names from db
                d1.setApplicant(SQLHandler.idtoNAME(rs.getInt("applicant")));
                d1.setDate( rs.getDate("dateIssued"));
                certlista.add(d1);
            }

            id1.setCellValueFactory(new PropertyValueFactory<>("ID")); // " " are from person class property names
            a1.setCellValueFactory(new PropertyValueFactory<>("applicant"));
            d1.setCellValueFactory(new PropertyValueFactory<>("date"));
            

            certificatestable.setItems(certlista);
            
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Searches the clearance table to match a person's id. Populates the clearance table in the Documents Pane.
     */
    @FXML
    public void search2() {
        String q = "SELECT * FROM clearance where applicant=?";
        String id = documentkeyword.getText();
        try {
            clearlista = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(q);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Document d2 = new Document();
                d2.setCertID(rs.getInt("clearance_no")); //the ones in " " are column names from db
                d2.setApplicant(SQLHandler.idtoNAME(rs.getInt("applicant")));
                d2.setDate( rs.getDate("dateIssued"));
                clearlista.add(d2);
            }

            id2.setCellValueFactory(new PropertyValueFactory<>("ID")); // " " are from person class property names
            a2.setCellValueFactory(new PropertyValueFactory<>("applicant"));
            d2.setCellValueFactory(new PropertyValueFactory<>("date"));
            

            clearancetable.setItems(clearlista);
            
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Searches the permit table to match a person's id. Populates the permit table in the Documents Pane.
     */
    @FXML
    public void search3() {
        String q = "SELECT * FROM permits where applicant=?";
        String id = documentkeyword.getText();
        try {
            permlista = FXCollections.observableArrayList();
            Connection conn = MySQLConnector.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(q);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Document d3 = new Document();
                d3.setCertID(rs.getInt("permit_no")); //the ones in " " are column names from db
                d3.setApplicant(SQLHandler.idtoNAME(rs.getInt("applicant")));
                d3.setDate( rs.getDate("dateIssued"));
                permlista.add(d3);
            }

            id3.setCellValueFactory(new PropertyValueFactory<>("ID")); // " " are from person class property names
            a3.setCellValueFactory(new PropertyValueFactory<>("applicant"));
            d3.setCellValueFactory(new PropertyValueFactory<>("date"));
            

            permittable.setItems(permlista);
            
        } catch (SQLException ex) {
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void nonresidenthandler(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("/fxml/CreateNONResident.fxml"));
        Scene scene = new Scene(blah);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
