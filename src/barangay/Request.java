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
public class Request {

    private final IntegerProperty reqID = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public int getReqID() {
        return reqID.get();
    }

    /**
     *
     * @param value
     */
    public void setReqID(int value) {
        reqID.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty reqIDProperty() {
        return reqID;
    }
    private final ObjectProperty<Date> dateRequested = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public Date getDateRequested() {
        return dateRequested.get();
    }

    /**
     *
     * @param value
     */
    public void setDateRequested(Date value) {
        dateRequested.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty dateRequestedProperty() {
        return dateRequested;
    }
    private final IntegerProperty requester = new SimpleIntegerProperty();

    /**
     *
     * @return
     */
    public int getRequester() {
        return requester.get();
    }

    /**
     *
     * @param value
     */
    public void setRequester(int value) {
        requester.set(value);
    }

    /**
     *
     * @return
     */
    public IntegerProperty requesterProperty() {
        return requester;
    }
    private final StringProperty request = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getRequest() {
        return request.get();
    }

    /**
     *
     * @param value
     */
    public void setRequest(String value) {
        request.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty requestProperty() {
        return request;
    }
    private final ObjectProperty<Date> dateneeded = new SimpleObjectProperty<>();

    /**
     *
     * @return
     */
    public Date getDateneeded() {
        return dateneeded.get();
    }

    /**
     *
     * @param value
     */
    public void setDateneeded(Date value) {
        dateneeded.set(value);
    }

    /**
     *
     * @return
     */
    public ObjectProperty dateneededProperty() {
        return dateneeded;
    }
    private final StringProperty reqstatus = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getReqstatus() {
        return reqstatus.get();
    }

    /**
     *
     * @param value
     */
    public void setReqstatus(String value) {
        reqstatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty reqstatusProperty() {
        return reqstatus;
    }
    private final StringProperty requesterNAME = new SimpleStringProperty();

    /**
     *
     * @return
     */
    public String getRequesterNAME() {
        return requesterNAME.get();
    }

    /**
     *
     * @param value
     */
    public void setRequesterNAME(String value) {
        requesterNAME.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty requesterNAMEProperty() {
        return requesterNAME;
    }
    
    
}
