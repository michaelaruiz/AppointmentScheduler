/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewControllers;

import Model.City;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBCalls;
import utils.DBConnection;
import static utils.DBConnection.conn;

/**
 * FXML Controller class
 *
 * @author michaelaruiz
 */
public class AddCustomerScreenController implements Initializable {

    @FXML
    private TextField newCustomerPostalText;
    @FXML
    private TextField newCustomerNameText;
    @FXML
    private TextField NewCustomerPhoneText;
    @FXML
    private TextField newCustomerAddressText;
    @FXML
    private TextField newCustomerAddress2Text;
    @FXML
    private ComboBox<City> newCustomerCityText;
    private TextField newCustomerCountryText;

    ObservableList<City> cityList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            cityList.addAll(DBCalls.getAllCities());
            newCustomerCityText.setItems(cityList);
            // TODO
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void newCustomerSaveHandler(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        int address = 0;
        PreparedStatement ps = null;
        
        
 if  (newCustomerCityText.getSelectionModel().getSelectedItem().toString().isEmpty() || newCustomerAddressText.getText().isEmpty() ||
         NewCustomerPhoneText.getText().isEmpty() || newCustomerAddress2Text.getText().isEmpty() || newCustomerPostalText.getText().isEmpty() || 
         newCustomerNameText.getText().isEmpty()){
    Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("");
       alert.setHeaderText("error");
       alert.setContentText("One or more fields empty");

       alert.showAndWait();
       return; 
 }
 else{
        try {
            

            String sql
                    = //"SELECT address.addressId, city.cityId, country.countryId FROM address, city, country WHERE address.address = ? AND city.city = ? AND country.country = ?";
                    "INSERT INTO address(addressId, address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES"
                    + "(NULL, ?, ?, ?, ?, ?, NOW(), 'admin', NOW(), 'admin')";

            ps = DBConnection.conn.prepareStatement(sql);

            City value = newCustomerCityText.getSelectionModel().getSelectedItem();

            ps.setString(1, newCustomerAddressText.getText());
            ps.setString(2, newCustomerAddress2Text.getText());

            //ps.setString(2, NewCustomerPhoneText.getText());
            ps.setInt(3, value.getCityId()); //picking from drop down

            ps.setString(4, newCustomerPostalText.getText());
            ps.setString(5, NewCustomerPhoneText.getText());

            ps.execute();
            
            

            // System.out.println(rs.next());
            sql = "INSERT INTO customer (customerId, customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (NULL, ?, LAST_INSERT_ID(), 1, NOW(), 'admin', NOW(), 'admin')";
            ps = DBConnection.conn.prepareStatement(sql);

            ps.setString(1, newCustomerNameText.getText());

            ps.execute();
           /* if (ps.getUpdateCount() > 0) {
                System.out.println(ps.getUpdateCount() + " rows affected");
            } else {
                System.out.println("no rows changed");
            }
*/

        } catch (SQLException e) {
            e.printStackTrace();
            
                System.err.println("Exception occurred");
                System.err.println(e.getMessage());
            }
        
        Parent mainParent = FXMLLoader.load(getClass().getResource("/ViewControllers/CustomerRecordsScreen.fxml"));
        Scene mainScene = new Scene(mainParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(mainScene);
        window.show();
 }
}
   
                



    

    @FXML
    private void newCustomerCancelHandler(ActionEvent event) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/ViewControllers/CustomerRecordsScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(mainScreenScene);
        window.show();
    }

}
