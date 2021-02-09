/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.LoginManager;

/**
 *
 * @author michaelaruiz
 */
public class City  {
    private int cityId;
    private SimpleStringProperty city;
    private int countryId;
   
   
 
    

    public City() {
        this.cityId = 0;
        this.city = new SimpleStringProperty();
        this.countryId = 0;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

   
     public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    
    
     public StringProperty CityProperty()
    {return city;}
     
     @Override
    public String toString(){
     return city.get(); 
    } 
}
