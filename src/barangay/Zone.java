/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author axis
 */
public class Zone {

    private final StringProperty zoneID = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getZoneID() {
        return zoneID.get();
    }

    /**
     *
     * @param value
     */
    public void setZoneID(String value) {
        zoneID.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty zoneIDProperty() {
        return zoneID;
    }
    private final ObjectProperty<Person> zlead = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public Person getZlead() {
        return zlead.get();
    }

    /**
     *
     * @param value
     */
    public void setZlead(Person value) {
        zlead.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty zleadProperty() {
        return zlead;
    }
    private final StringProperty zalias = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getZalias() {
        return zalias.get();
    }

    /**
     *
     * @param value
     */
    public void setZalias(String value) {
        zalias.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty zaliasProperty() {
        return zalias;
    }
    private final StringProperty zloc = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getZloc() {
        return zloc.get();
    }

    /**
     *
     * @param value
     */
    public void setZloc(String value) {
        zloc.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty zlocProperty() {
        return zloc;
    }
    
    
}
