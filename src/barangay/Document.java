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
public class Document {
    private final IntegerProperty ID = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public int getCertID() {
        return ID.get();
    }

    /**
     *
     * @param value
     */
    public void setCertID(int value) {
        ID.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty IDProperty() {
        return ID;
    }
    private final StringProperty applicant = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getApplicant() {
        return applicant.get();
    }

    /**
     *
     * @param value
     */
    public void setApplicant(String value) {
        applicant.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty applicantProperty() {
        return applicant;
    }
    private final ObjectProperty<Date> date = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public Date getDate() {
        return date.get();
    }

    /**
     *
     * @param value
     */
    public void setDate(Date value) {
        date.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty dateProperty() {
        return date;
    }
}
