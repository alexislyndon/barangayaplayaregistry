/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author axis
 */
public class Person {

    private final IntegerProperty id = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public int getId() {
        return id.get();
    }

    /**
     *
     * @param value
     */
    public void setId(int value) {
        id.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty idProperty() {
        return id;
    }
    private final StringProperty last = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getLast() {
        return last.get();
    }

    /**
     *
     * @param value
     */
    public void setLast(String value) {
        last.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty lastProperty() {
        return last;
    }
    private final StringProperty middle = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getMiddle() {
        return middle.get();
    }

    /**
     *
     * @param value
     */
    public void setMiddle(String value) {
        middle.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty middleProperty() {
        return middle;
    }
    private final StringProperty first = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getFirst() {
        return first.get();
    }

    /**
     *
     * @param value
     */
    public void setFirst(String value) {
        first.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty firstProperty() {
        return first;
    }
    private final StringProperty sex = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getSex() {
        return sex.get();
    }

    /**
     *
     * @param value
     */
    public void setSex(String value) {
        sex.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty sexProperty() {
        return sex;
    }
    //date of birth
    private final ObjectProperty<Date> birth = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public Date getBirth() {
        return birth.get();
    }

    /**
     *
     * @param value
     */
    public void setBirth(Date value) {
        birth.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty birthProperty() {
        return birth;
    }

    private final StringProperty marital = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getMarital() {
        return marital.get();
    }

    /**
     *
     * @param value
     */
    public void setMarital(String value) {
        marital.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty maritalProperty() {
        return marital;
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
    
    /**
     *
     * @return
     */
    public String getNAME() {
        String fullname = getFirst()+" " + getMiddle()+ " " + getLast();
        return fullname;
    }
    private final StringProperty fullNAME = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getFullNAME() {
        return fullNAME.get();
    }

    /**
     *
     * @param value
     */
    public void setFullNAME(String value) {
        fullNAME.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty fullNAMEProperty() {
        return fullNAME;
    }
    
    

}
