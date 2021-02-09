/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewControllers;

import Model.City;
import Model.Country;
import Model.Customer;
import static ViewControllers.CalendarScreenController.infoBox;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DBCalls;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author michaelaruiz
 */
public class CustomerRecordsScreenController implements Initializable {

    @FXML
    private TableColumn<Customer, String> customerName;
    @FXML
    private TableColumn<Customer, String> customerPhone;
    @FXML
    private TableColumn<Customer, String> customerAddress;
    @FXML
    private TableColumn<Customer, String> customerAddress2;
    @FXML
    private TableColumn<Customer, String> customerCity;
    private TableColumn<Customer, String> customerCountry;
    @FXML
    private TableColumn<Customer, String> customerPostal;
    @FXML
    private TableView<Customer> customerTable;
ObservableList<Customer> allAppointments = FXCollections.observableArrayList();
    private int customerId;
    private int addressId;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //This lambda sets the cell values for each row more efficiently than in the calendar screen controller
            customerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));
            customerPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
            customerAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
            customerAddress2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress2()));
            customerCity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCity()));
            customerPostal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostalCode()));
            
            //Lambda Expression 
            
        
            ObservableList<Customer> custList = DBCalls.getAllCustomers();
            
            populateTable(custList);
       
        }
        
        public void populateTable(ObservableList<Customer> custList) {
        customerTable.setItems(custList);
    }
       


    @FXML
    private void customerAddHandler(ActionEvent event) throws IOException {
        Parent customerRecordsParent = FXMLLoader.load(getClass().getResource("AddCustomerScreen.fxml"));
    Scene customerRecordsScene = new Scene(customerRecordsParent);
        
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    window.setScene(customerRecordsScene);
    window.show(); 
    }
    
    

    @FXML
    private void customerUpdateHandler(ActionEvent event) throws IOException {
    Customer selectedCustomer =  customerTable.getSelectionModel().getSelectedItem();
    if (selectedCustomer == null){
        infoBox("Please select customer", null, "Please select customer");
    }
    else {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewControllers/UpdateCustomerScreen.fxml"));


        Parent customerRecordsParent = loader.load();
        Scene customerRecordsScene = new Scene(customerRecordsParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(customerRecordsScene);
        window.show();
        UpdateCustomerScreenController controller = loader.getController();
        controller.updateForm(selectedCustomer);
    }
    }
    
    
    

    @FXML
    private void customerDeleteHandler(ActionEvent event) throws SQLException, IOException{
      Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
//      addressId = selectedCustomer.getAddressId();
//      customerId = selectedCustomer.getCustomerId();
      if(selectedCustomer==null){
          infoBox("Please select a customer", null, "Please select a customer");
      }
      else{
          deleteCustomer(selectedCustomer);
          infoBox("Delete Successful", null, "Press Refresh to See Changes");
          
      }
        
       }
    private void deleteCustomer(Customer selectedCustomer){
        PreparedStatement apps = null;
        PreparedStatement cps = null;
        PreparedStatement aps = null;
        String apRemove = "DELETE FROM appointment WHERE customerId = ?";
        String cRemove = "DELETE FROM customer WHERE customerId = ?";
        String aRemove = "DELETE FROM address WHERE addressId = ?";
        addressId = selectedCustomer.getAddressId();
        customerId = selectedCustomer.getCustomerId();
        
        try {
            apps = DBConnection.conn.prepareStatement(apRemove);
            apps.setInt(1, customerId);
            apps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerRecordsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cps = DBConnection.conn.prepareStatement(cRemove);
            cps.setInt(1, customerId);
            cps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerRecordsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            aps = DBConnection.conn.prepareStatement(aRemove);
            aps.setInt(1, addressId);
            aps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerRecordsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void cancelHandler(ActionEvent event) throws IOException {
         Parent mainParent = FXMLLoader.load(getClass().getResource("CalendarScreen.fxml"));
        Scene mainScene = new Scene(mainParent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mainScene);
        window.show(); 
    }
 
     public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    private void refreshHandler(ActionEvent event) {
        ObservableList<Customer> custList = DBCalls.getAllCustomers();
            
            populateTable(custList);
    }
}
