/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.LoginManager;

/**
 *
 * @author michaelaruiz
 */
public class Customer {// LoginManager {
    private int customerId;
    private StringProperty customerName;
    private int addressId;
    private StringProperty active;
    private StringProperty address;
    private StringProperty address2;
    private int cityId;
    private StringProperty postalCode;
    private StringProperty phone;
    private StringProperty country;
    private StringProperty city;
    private StringProperty createDate;
    private StringProperty createdBy;
    private StringProperty lastUpdate;
    private StringProperty lastUpdatedBy;
    private StringProperty numberCustomers;
   
   /*
  
    
    public Customer(int customerId, String customerName, int addressId, int active,
            String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
        super(createDate, createdBy, lastUpdate, lastUpdateBy);

*/public Customer(){
       
        
        this.customerId = 0;
        this.customerName = new SimpleStringProperty();
        this.addressId = 0;
        this.active = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.address2 = new SimpleStringProperty();
        this.cityId = 0;
        this.postalCode = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.country = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.createDate = new SimpleStringProperty();
        this.createdBy = new SimpleStringProperty();
        this.lastUpdate = new SimpleStringProperty();
        this.lastUpdatedBy = new SimpleStringProperty();
        this.numberCustomers = new SimpleStringProperty();
        
        
        
        
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        //this.appointmentId = appointmentId;
        this.customerId = customerId;
        
    }   

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId =addressId;
    }

    public String getActive() {
        return active.get();
    }

    public void setActive(String active) {
        this.active.set(active);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
    
     public String getAddress2() {
        return address2.get();
    }

    public void setAddress2(String address2) {
        this.address2.set(address2);
    }
    
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    
     public String getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }
    
     public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    
    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }
    
     public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }
    
      public String getCreateDate() {
        return createDate.get();
    }

    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }
    
      public String getCreatedBy() {
        return createdBy.get();
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }
    
      public String getLastUpdate() {
        return lastUpdate.get();
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate.set(lastUpdate);
    }
    
      public String getLastUpdatedBy() {
        return lastUpdatedBy.get();
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy.set(lastUpdatedBy);
    }
    public String getNumberCustomers() {
        return numberCustomers.get();
    }

    public void setNumberCustomers(String numberCustomers) {
        //this.appointmentId = appointmentId;
        this.numberCustomers.set(numberCustomers);
        
    }   
    
    
    

    
    public StringProperty customerNameProperty()
    {return customerName;}
    
  
    
    public StringProperty activeProperty()
    {return active;}
    
    public StringProperty AddressProperty()
    {return address;}
    
    public StringProperty Address2Property()
    {return address2;}
    
 
    
    public StringProperty PostalCodeProperty()
    {return postalCode;}
    
     public StringProperty PhoneProperty()
    {return phone;}
     
    public StringProperty CountryProperty()
    {return country;}
    
     public StringProperty CityProperty()
    {return city;}
    
    public StringProperty createDateProperty()
    {return createDate;}
    
    public StringProperty createdByProperty()
    {return createdBy;}
    
    public StringProperty lastUpdateProperty()
    {return lastUpdate;}
    
    public StringProperty lastUpdatedByProperty()
    {return lastUpdatedBy;}
     public StringProperty numberCustomersProperty()
    {return numberCustomers;}
    @Override
    public String toString(){
     return customerId + " : "+ customerName.get(); 
    }
    
    
}
