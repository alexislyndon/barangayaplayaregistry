/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author axis
 */
public class Household {

    private final IntegerProperty hhid = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public int getHhid() {
        return hhid.get();
    }

    /**
     *
     * @param value
     */
    public void setHhid(int value) {
        hhid.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty hhidProperty() {
        return hhid;
    }
    private final StringProperty head = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getHead() {
        return head.get();
    }

    /**
     *
     * @param value
     */
    public void setHead(String value) {
        head.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty headProperty() {
        return head;
    }
    private final StringProperty zone = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getZone() {
        return zone.get();
    }

    /**
     *
     * @param value
     */
    public void setZone(String value) {
        zone.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty zoneProperty() {
        return zone;
    }
    
    private final ListProperty<String> members = new SimpleListProperty<>();
    
    /**
     *
     * @return
     */
    public ObservableList getMembers() {
        return members.get();
    }

    /**
     *
     * @param value
     */
    public void setMembers(ObservableList value) {
        members.set(value);
    }

    /**
     *
     * @return
     */
    public ListProperty membersProperty() {
        return members;
    }
    
    
    private final StringProperty member = new SimpleStringProperty();
    
    /**
     *
     * @return
     */
    public String getMember() {
        return member.get();
    }

    /**
     *
     * @param value
     */
    public void setMember(String value) {
        member.set(value);
    }
    
    /**
     *
     * @return
     */
    public StringProperty memberProperty() {
        return member;
    }
    private final StringProperty zonename = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getZonename() {
        return zonename.get();
    }

    /**
     *
     * @param i
     */
    public void setZonename(int i) {
        if(i == 1)zonename.set("1a");
        else if(i == 2) zonename.set("1b");
        else if(i == 3) zonename.set("2");
        else if(i == 4) zonename.set("2u");
        else if(i == 5) zonename.set("3");
        else if(i == 6) zonename.set("3u");
        else if(i == 7) zonename.set("4");
        else if(i == 8) zonename.set("5");
        //else zonename.set("1a");
    }

    /**
     *
     * @return
     */
    public StringProperty zonenameProperty() {
        return zonename;
    }
    
    /**
     *
     * @param i
     * @return
     */
    public String zoneinttoString(int i) {
        if(i == 1) return "1a";
        else if (i == 1) return "1b";
        else if (i == 1) return "2";
        else if (i == 1) return "2u";
        else if (i == 1) return "3";
        else if (i == 1) return "3u";
        else if (i == 1) return "4";
        else if (i == 1) return "5";
        else return "";
    }
}
