/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Model.Address;
import Model.Appointment;
import Model.City;
import Model.Country;
import Model.Customer;
import Model.User;
import ViewControllers.LoginScreenController;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import static utils.DBConnection.conn;

/**
 *
 * @author michaelaruiz
 */
public class DBCalls {

    static int loggedInUserId;
    //static int loginUserName;
    public static boolean validateLogin(String userName, String password) throws ClassNotFoundException, SQLException {
        try{
          
            PreparedStatement preparedStatement = DBConnection.conn.prepareStatement("SELECT * FROM user WHERE userName= ? and password= ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            

            
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();
            boolean ret = false;
            if (rs.next()){
                ret = true;
                loggedInUserId = rs.getInt("userId");
                //loginUserName = rs.getInt("userName");
            }
            return ret;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
           
      
    }   
   
     public static boolean checkIfAppIn15() {
        LocalDateTime now = LocalDateTime.now();
        try {
            PreparedStatement ps = null;
            //ResultSet rs = null;
            
            String sql
                    = "SELECT start FROM appointment";
            ps = DBConnection.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            System.out.println(rs.toString());
            
            while(rs.next()){
                Timestamp startTimeStamp = rs.getTimestamp("start");
                
                LocalDateTime startT = fromUTC(startTimeStamp.toLocalDateTime());
                //LocalDateTime endT = endTimeStamp.toLocalDateTime();
            
            if(startT.isBefore(now.plusMinutes(15)) && startT.isAfter(now)) {
            
            return true;   
            }
            
            
                }  
        } catch (SQLException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }   

    public static int getDbUserId() {
        return dbUserId;
    }
    
static int dbUserId;    
    

 public static boolean UserId(String userId) throws SQLException{
        try{
        PreparedStatement preparedStatement = DBConnection.conn.prepareStatement("SELECT * FROM user WHERE userId = ?");
        preparedStatement.setString(1, userId);
        
        System.out.println(preparedStatement);
            
        ResultSet rs = preparedStatement.executeQuery();
        
        boolean ret = false;
        if (rs.next()) {
            ret = true;
            dbUserId = rs.getInt("userId");
            
        }
        return ret;
       }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        } 
 }

static int dbAppointmentId;
   
    public static boolean AppointmentId(String title) throws SQLException{
        try{
        PreparedStatement preparedStatement = DBConnection.conn.prepareStatement("SELECT * FROM appointment WHERE title = ?");
        preparedStatement.setString(1, title);
        
        System.out.println(preparedStatement);
            
        ResultSet rs = preparedStatement.executeQuery();
        
        boolean ret = false;
        if (rs.next()) {
            ret = true;
            dbAppointmentId = rs.getInt("appointmentId");
            
        }
        return ret;
       }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        } 
    }
    
    static int dbAddressId;
    
        public static boolean AddressId(String address) throws SQLException{
        try{
        PreparedStatement preparedStatement = DBConnection.conn.prepareStatement("SELECT * FROM address WHERE address = ?");
        preparedStatement.setString(1, address);
        
        System.out.println(preparedStatement);
            
        ResultSet rs = preparedStatement.executeQuery();
        
        boolean ret = false;
        if (rs.next()) {
            ret = true;
            dbAddressId = rs.getInt("addressId");
            
        }
        return ret;
       }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        } 
    }
        
      static int dbCustomerId;
    
        public static boolean CustomerId(String customerName) throws SQLException{
        try{
        PreparedStatement preparedStatement = DBConnection.conn.prepareStatement("SELECT * FROM customer WHERE customerName = ?");
        preparedStatement.setString(1, customerName);
        
        System.out.println(preparedStatement);
            
        ResultSet rs = preparedStatement.executeQuery();
        
        boolean ret = false;
        if (rs.next()) {
            ret = true;
            dbCustomerId = rs.getInt("customerId");
            
        }
        return ret;
       }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        } 
    } 
        
        static int dbCityId;
    
        public static boolean CityId(String city) throws SQLException{
        try{
        PreparedStatement preparedStatement = DBConnection.conn.prepareStatement("SELECT cityId FROM city WHERE city = ?");
        preparedStatement.setString(1, city);
        
        System.out.println(preparedStatement);
            
        ResultSet rs = preparedStatement.executeQuery();
        
        boolean ret = false;
        if (rs.next()) {
            ret = true;
            dbCityId = rs.getInt("cityId");
            
        }
        return ret;
       }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        } 
    }       

    public static int getDbCustomerId() {
        return dbCustomerId;
    }

    public static int getDbAddressId() {
        return dbAddressId;
    }
    
    public static int getDbAppointmentId() {
        return dbAppointmentId;
    }

   
    
    public static int getLoggedInUserId() {
        return loggedInUserId;
    }
    
    

    public static void getUsers(ObservableList<User> allUsers) {
    
    }

    public static void getCustomers(ObservableList<Customer> allCustomers) {
        
    }

    public static void getCities(ObservableList<City> allCities) {
        
    }

    public static void getCountries(ObservableList<Country> allCountries) {
        
    }
    
    public static ObservableList<Customer> getAllCustomers() {
        String sql = 
                "SELECT customer.customerName, customer.customerId, address.addressId, city.cityId, address.phone, address.address, address.address2, address.postalCode, city.city, country.country "
                + "FROM customer INNER JOIN address ON customer.addressId = address.addressId INNER JOIN city ON address.cityId = city.cityId "
                + "INNER JOIN country ON city.countryId = country.countryId";
                
             
        
        try{
            ResultSet rsSet = DBConnection.DBExecute(sql);
            ObservableList<Customer> custList = getCustomerObject(rsSet);
            return custList;
                 
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            
        }
        return FXCollections.observableArrayList();
    }
    private static ObservableList<Customer> getCustomerObject(ResultSet rsSet) throws SQLException {
        try{
        ObservableList<Customer>custList = FXCollections.observableArrayList();
       //put in customerId, addressId
          while(rsSet.next()){
            Customer cust = new Customer();
            cust.setCustomerId(rsSet.getInt("customerId"));
            cust.setAddressId(rsSet.getInt("addressId"));
            cust.setCityId(rsSet.getInt("cityId"));
            cust.setCustomerName(rsSet.getString("customerName"));
            cust.setPhone(rsSet.getString("phone"));
            cust.setAddress(rsSet.getString("address"));
            cust.setAddress2(rsSet.getString("address2"));
            cust.setCity(rsSet.getString("city"));
            cust.setCountry(rsSet.getString("country"));
            cust.setPostalCode(rsSet.getString("postalCode"));
            
            custList.add(cust);
        }
        return custList;
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            throw e;
    }   
    }


    public static ObservableList<Appointment> getAllAppointments() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM appointment";
        
        try{
            ResultSet rsSet = DBConnection.DBExecute(sql);
            ObservableList<Appointment> appList = getAppointmentObject(rsSet);
            return appList;
                 
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            throw e;
        }
        
    }
    
    private static ObservableList<Appointment> getAppointmentObject(ResultSet rsSet) throws ClassNotFoundException, SQLException{
        try{
        ObservableList<Appointment>appList = FXCollections.observableArrayList();
       
          while(rsSet.next()){
            Appointment app = new Appointment();
            app.setAppointmentId(rsSet.getInt("appointmentId"));
            app.setCustomerId(rsSet.getInt("customerId"));
            app.setUserId(rsSet.getInt("userId"));
            app.setTitle(rsSet.getString("title"));
            app.setDescription(rsSet.getString("description"));
            app.setLocation(rsSet.getString("location"));
            app.setContact(rsSet.getString("contact"));
            app.setType(rsSet.getString("type"));
            Timestamp startT = rsSet.getTimestamp("start");
            Timestamp endT = rsSet.getTimestamp("end");
            
           // WHERE To put timezone conversion - utc default 
                
           LocalDateTime newLocalStart = fromUTC(startT.toLocalDateTime()); 
           LocalDateTime newLocalEnd = fromUTC(endT.toLocalDateTime());
        app.setStart(newLocalStart);
        app.setEnd(newLocalEnd);
            
            appList.add(app);
        }
        return appList;
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            throw e;
    }
    }
    
    public static ObservableList<City> getAllCities() throws ClassNotFoundException, SQLException {
        String sql = 
                "SELECT * FROM city";
       
        try{
            ResultSet rsSet = DBConnection.DBExecute(sql);
            ObservableList<City> cityList = getCityObject(rsSet);
            return cityList;
                 
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            
        }
        return FXCollections.observableArrayList();
    }
    private static ObservableList<City> getCityObject(ResultSet rsSet) throws ClassNotFoundException, SQLException {
        try{
        ObservableList<City>cityList = FXCollections.observableArrayList();
       //put in customerId, addressId
          while(rsSet.next()){
            City city = new City();
            city.setCityId(rsSet.getInt("cityId"));
            city.setCity(rsSet.getString("city"));
            city.setCountryId(rsSet.getInt("countryId"));
            
            
            cityList.add(city);
        }
        return cityList;
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
          throw e;  
    }   
        
    }


    public static ObservableList<Appointment> getAppsReport() throws ClassNotFoundException, SQLException{
//        String sql = "SELECT type, MONTHNAME(start) as 'Month', COUNT(*) as 'Number' FROM appointment GROUP BY type, MONTH(START);";
        PreparedStatement preparedStatement = DBConnection.conn.prepareStatement("SELECT type, monthname(start) as " +
                "'Month', count(*) as 'Number' FROM appointment GROUP BY type, monthname(start), 'Number';");

        System.out.println(preparedStatement);


        try{
//            ResultSet rsSet = DBConnection.DBExecute(sql);
            ResultSet rsSet = preparedStatement.executeQuery();
            ObservableList<Appointment> appReportList = getAppsReportObject(rsSet);
            return appReportList;

        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            throw e;
        }

    }
    
    private static ObservableList<Appointment> getAppsReportObject(ResultSet rsSet) throws ClassNotFoundException, SQLException{
        try{
        ObservableList<Appointment>appReportList = FXCollections.observableArrayList();

          while(rsSet.next()){
            Appointment appReport = new Appointment();
              appReport.setNumberApps(rsSet.getString("Number"));
              appReport.setMonth(rsSet.getString("Month"));
              appReport.setType(rsSet.getString("type"));

            appReportList.add(appReport);
        }
        return appReportList;
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            throw e;
    }
    }

    
    public static ObservableList<Appointment> getConsultantReport() throws ClassNotFoundException, SQLException{
        PreparedStatement preparedStatement = DBConnection.conn.prepareStatement("SELECT appointment.contact, start, " +
                "customer.customerName FROM appointment JOIN customer ON customer.customerId = appointment.customerId");

        System.out.println(preparedStatement);


        try{
//            ResultSet rsSet = DBConnection.DBExecute(sql);
            ResultSet rsSet = preparedStatement.executeQuery();
            ObservableList<Appointment> conReportList = getConsultantReportObject(rsSet);
            return conReportList;

        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Appointment> getConsultantReportObject(ResultSet rsSet) throws ClassNotFoundException, SQLException{
        try{
        ObservableList<Appointment>conReportList = FXCollections.observableArrayList();

          while(rsSet.next()){
            Appointment conReport = new Appointment();
            Timestamp startT = rsSet.getTimestamp("start");
            conReport.setStart(fromUTC(startT.toLocalDateTime()));
            conReport.setCustomerName(rsSet.getString("customerName"));
            conReport.setContact(rsSet.getString("contact"));


            conReportList.add(conReport);
        }
        return conReportList;
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            throw e;
    }
    }
    
      public static ObservableList<Customer> getCustomerReport() throws ClassNotFoundException, SQLException{
          PreparedStatement preparedStatement = DBConnection.conn.prepareStatement("SELECT city.city, count(*) as 'Number' FROM customer, address, city WHERE customer.addressId = address.addressId AND address.cityId = city.cityId GROUP BY city, 'Number'");

          System.out.println(preparedStatement);


          try{
//            ResultSet rsSet = DBConnection.DBExecute(sql);
              ResultSet rsSet = preparedStatement.executeQuery();
            ObservableList<Customer> custReportList = getCustomerReportObject(rsSet);
            return custReportList;
                 
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            throw e;
        }
        
    }

      
    private static ObservableList<Customer> getCustomerReportObject(ResultSet rsSet) throws ClassNotFoundException, SQLException{
        try{
        ObservableList<Customer>custReportList = FXCollections.observableArrayList();
       
          while(rsSet.next()){
            Customer custReport = new Customer();
            custReport.setNumberCustomers(rsSet.getString("Number"));
            custReport.setCity(rsSet.getString("city"));
            
            
            
            custReportList.add(custReport);
        }
        return custReportList;
        }
        catch(SQLException e){
            System.out.println("Error occured"+e);
            e.printStackTrace();
            throw e;
    }
    }
    
       public static LocalDateTime toUTC(LocalDateTime t){
       ZoneId zoneId = ZoneId.systemDefault();

            ZonedDateTime newStart = t.atZone(zoneId);
            ZonedDateTime newLocalStart = newStart.withZoneSameInstant(ZoneId.of("UTC"));
            
            return newLocalStart.toLocalDateTime();

   } 
       public static LocalDateTime fromUTC(LocalDateTime t) {
           ZoneId zoneId = ZoneId.systemDefault();
           
           ZonedDateTime newStart = t.atZone(ZoneId.of("UTC"));
           ZonedDateTime newLocalStart = newStart.withZoneSameInstant(zoneId);
           
           return newLocalStart.toLocalDateTime();
       }
    
    
  
    
    
            
}
