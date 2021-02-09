/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import utils.DBCalls;
import utils.LoginManager;

/**
 *
 * @author michaelaruiz
 */
public class Appointment { //extends LoginManager

    ObservableList<Appointment> appList;
    ObservableList<Appointment> allAppointments;
    private int appointmentId;
    private int customerId;
    //private int customerId;
    private int userId;
    private StringProperty title;
    private StringProperty description;
    private StringProperty location;
    private StringProperty contact;
    private StringProperty type;
    private StringProperty url;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime date;
    //private LocalDateTime date;
    private LocalDateTime value;

    private StringProperty month;
    private String numberApps;
    private LocalTime startHours;
    private StringProperty customerName;

   
    public Appointment() {
        this.appointmentId = 0;
        this.customerId = 0;
        this.userId = 0;
        this.title = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.location = new SimpleStringProperty();
        this.contact = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.url = new SimpleStringProperty();
        this.start = LocalDateTime.now();
        this.end = LocalDateTime.now();
        this.date = date;
        this.value = value;
        this.startHours = startHours;
        this.month = new SimpleStringProperty();
        //this.numberApps = null;
        this.customerName = new SimpleStringProperty();

       
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        //this.appointmentId = appointmentId;
        this.appointmentId = appointmentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getValue() {
        return value;
    }

    public void setValue(LocalDateTime value) {
        this.value = value;
    }

    public String getMonth() {
        return month.get();
    }

    public void setMonth(String month) {
        this.month.set(month);
    }

    public String getNumberApps() {
        return numberApps;
    }

    public void setNumberApps(String numberApps) {
        this.numberApps = numberApps;
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }
      public LocalTime getStartHours() {
        return startHours;
    }

    public void setStartHours(LocalTime startHours) {
        this.startHours = startHours;
    }

   

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty contactProperty() {
        return contact;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty urlProperty() {
        return url;
    }

    public StringProperty monthProperty() {
        return month;
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }
    /*  @Override
    public String toString(){
     return customerId + " : "+ customerName.get(); 

    }
     */

}
