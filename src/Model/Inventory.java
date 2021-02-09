/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBCalls;

/**
 *
 * @author michaelaruiz
 */
public class Inventory {
    private static final ObservableList<User> allUsers = FXCollections.observableArrayList();
private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
private static final ObservableList<City> allCities = FXCollections.observableArrayList();
private static final ObservableList<Country> allCountries = FXCollections.observableArrayList();
private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
private static final ObservableList<Address> allAddresses = FXCollections.observableArrayList();


    


public static ObservableList<User> getAllUsers(){
   
    return allUsers;

}

public static ObservableList<Customer> getAllCustomers(){
    return allCustomers;
}

public static ObservableList<Appointment> getAllAppointments(){
    return allAppointments;
}

public static ObservableList<City> getAllCities(){
    return allCities;
}

public static ObservableList<Country> getAllCountries(){
    return allCountries;
}    

public static ObservableList<Address> getAllAddresses(){
    return allAddresses;
}
    
}
