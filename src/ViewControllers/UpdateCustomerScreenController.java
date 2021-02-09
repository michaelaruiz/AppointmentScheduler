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
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBCalls;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author michaelaruiz
 */
public class UpdateCustomerScreenController implements Initializable {

    @FXML
    private TextField updateCustomerPostalText;
    @FXML
    private TextField updateCustomerNameText;
    @FXML
    private TextField updateCustomerPhoneText;
    @FXML
    private TextField updateCustomerAddressText;
    @FXML
    private TextField updateCustomerAddress2Text;
    @FXML
    private ComboBox<City> updateCustomerCity;
    ObservableList<City> cityList1 = FXCollections.observableArrayList();
    private int addressId = 0;
    private int customerId = 0;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            cityList1.addAll(DBCalls.getAllCities());
            updateCustomerCity.setItems(cityList1);
            // TODO
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     updateCustomerCity.getSelectionModel().selectFirst();
    }   
    
        
        // TODO
    
    public void updateForm(Customer selectedCustomer){
      updateCustomerPostalText.setText(selectedCustomer.getPostalCode());
      updateCustomerNameText.setText(selectedCustomer.getCustomerName());
      updateCustomerPhoneText.setText(selectedCustomer.getPhone());
      updateCustomerAddressText.setText(selectedCustomer.getAddress());
      updateCustomerAddress2Text.setText(selectedCustomer.getAddress2());
      updateCustomerCity.getSelectionModel().getSelectedItem().setCity(selectedCustomer.getCity());
      
      
      addressId = selectedCustomer.getAddressId();
      customerId = selectedCustomer.getCustomerId();
       
      
              
    }
        

    @FXML
    private void updateCustomerSaveHandler(ActionEvent event) throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        
  /*      
 if  (updateCustomerCity.getSelectionModel().getSelectedItem() == null || updateCustomerAddressText.getText() == null ||
         updateCustomerPhoneText.getText() == null || updateCustomerAddress2Text.getText() == null || updateCustomerPostalText.getText() == null || 
         updateCustomerNameText.getText() == null){
        
*/
  if  (updateCustomerCity.getSelectionModel().getSelectedItem().toString().isEmpty() || updateCustomerAddressText.getText().isEmpty() ||
         updateCustomerPhoneText.getText().isEmpty() || updateCustomerAddress2Text.getText().isEmpty() || updateCustomerPostalText.getText().isEmpty()|| 
         updateCustomerNameText.getText().isEmpty()){ 
 
    Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("");
       alert.setHeaderText("error");
       alert.setContentText("One or more fields empty");

       alert.showAndWait();
       return;
 }
 else{
        try {
            
  
            String sql = "UPDATE address SET address = ?, address2 = ?, cityId = ?, postalCode = ?, phone = ?, lastUpdate =  NOW(), lastUpdateBy = 'admin' "
                    + "WHERE addressId = ?";
            ps = DBConnection.conn.prepareStatement(sql);
            ps.setString(1, updateCustomerAddressText.getText());
            ps.setString(2, updateCustomerAddress2Text.getText());
            
            City value = updateCustomerCity.getSelectionModel().getSelectedItem();
            ps.setInt(3, value.getCityId());
            
            
            ps.setString(4, updateCustomerPostalText.getText());
            ps.setString(5, updateCustomerPhoneText.getText());
            ps.setInt(6, addressId);
                    //DBCalls.getDbAddressId());
            ps.execute();
                    
                    
             sql = "UPDATE customer SET customerName = ?, lastUpdate = NOW(), lastUpdateBy = 'admin' WHERE customerId = ?";
            
            ps = DBConnection.conn.prepareStatement(sql);
            ps.setString(1, updateCustomerNameText.getText());
             
            ps.setInt(2, customerId);
            
            
            // execute the preparedstatement
            ps.execute();

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
    private void updateCustomerCancelHandler(ActionEvent event) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/ViewControllers/CustomerRecordsScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(mainScreenScene);
        window.show();
    }
}
    

