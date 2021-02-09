/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import utils.LoginManager;

/**
 *
 * @author michaelaruiz
 */
public class Country extends LoginManager{
    private int countryId;
    private String country;
  
   
   
  
    
    public Country(int countryId, String country,
    String createDate, String createdBy, String lastUpdate, String lastUpdateBy) {
        super(createDate, createdBy, lastUpdate, lastUpdateBy);
        this.countryId = countryId;
        this.country = country;
        
        
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
