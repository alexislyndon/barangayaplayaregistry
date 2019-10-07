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
public class Blotter {

    private final IntegerProperty blotterID = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public int getBlotterID() {
        return blotterID.get();
    }

    /**
     *
     * @param value
     */
    public void setBlotterID(int value) {
        blotterID.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty blotterIDProperty() {
        return blotterID;
    }
    private final StringProperty narrative = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getNarrative() {
        return narrative.get();
    }

    /**
     *
     * @param value
     */
    public void setNarrative(String value) {
        narrative.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty narrativeProperty() {
        return narrative;
    }
    private final ObjectProperty<Date> incidentDate = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public Date getIncidentDate() {
        return incidentDate.get();
    }

    /**
     *
     * @param value
     */
    public void setIncidentDate(Date value) {
        incidentDate.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty incidentDateProperty() {
        return incidentDate;
    }
    private final StringProperty complainant = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getComplainant() {
        return complainant.get();
    }

    /**
     *
     * @param value
     */
    public void setComplainant(String value) {
        complainant.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty complainantProperty() {
        return complainant;
    }
    private final StringProperty respondent = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getRespondent() {
        return respondent.get();
    }

    /**
     *
     * @param value
     */
    public void setRespondent(String value) {
        respondent.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty respondentProperty() {
        return respondent;
    }
    private final StringProperty victim = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getVictim() {
        return victim.get();
    }

    /**
     *
     * @param value
     */
    public void setVictim(String value) {
        victim.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty victimProperty() {
        return victim;
    }
    private final StringProperty place = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getPlace() {
        return place.get();
    }

    /**
     *
     * @param value
     */
    public void setPlace(String value) {
        place.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty placeProperty() {
        return place;
    }
    private final StringProperty type = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getType() {
        return type.get();
    }

    /**
     *
     * @param value
     */
    public void setType(String value) {
        type.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty typeProperty() {
        return type;
    }
    private final StringProperty status = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getStatus() {
        return status.get();
    }

    /**
     *
     * @param value
     */
    public void setStatus(String value) {
        status.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty statusProperty() {
        return status;
    }
}
