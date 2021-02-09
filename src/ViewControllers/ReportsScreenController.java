/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewControllers;

import Model.Appointment;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DBCalls;

/**
 * FXML Controller class
 *
 * @author michaelaruiz
 */
public class ReportsScreenController implements Initializable {

    @FXML
    private TableView<Appointment> appointmentReport;
    
    @FXML
    private TableView<Customer> customerReport;
    @FXML
    private TableColumn<Appointment, String> monthCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
   
    ObservableList<Appointment>appReportList;
    ObservableList<Customer>custReportList;
    ObservableList<Appointment>conReportList;
    @FXML
    private TableColumn<Appointment, String> consultantCol;
    @FXML
    private TableColumn<Customer, String> cityCol;
    @FXML
    private TableColumn<Appointment, String> amountCol;
    @FXML
    private TableColumn<Customer, String> cityNumberCol;
    @FXML
    private TableView<Appointment> consultantReport;
    @FXML
    private TableColumn<Appointment, String> customerCol;
    @FXML
    private TableColumn<Appointment, String> appStartCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                // TODO
                appReportList = FXCollections.observableArrayList();
                monthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
                typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                amountCol.setCellValueFactory(cellData -> {
                    return new ReadOnlyStringWrapper(cellData.getValue().getNumberApps());
                });
                //lambda expression needed because without it, number column will not display properly
                
                
                ObservableList<Appointment> appReportList = DBCalls.getAppsReport();
                appointmentReport.setItems(appReportList);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReportsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ReportsScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            custReportList = FXCollections.observableArrayList();
            cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
            cityNumberCol.setCellValueFactory(cellData -> {
                    return new ReadOnlyStringWrapper(cellData.getValue().getNumberCustomers());
                });
            ObservableList<Customer> custReportList = DBCalls.getCustomerReport();
            customerReport.setItems(custReportList);
            
            conReportList = FXCollections.observableArrayList();
            consultantCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
            customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName")); 
            appStartCol.setCellValueFactory(new PropertyValueFactory<>("start")); 
            ObservableList<Appointment> conReportList = DBCalls.getConsultantReport();
            consultantReport.setItems(conReportList);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }    

    @FXML
    private void mainHandler(ActionEvent event) throws IOException {
    Parent customerRecordsParent = FXMLLoader.load(getClass().getResource("/ViewControllers/CalendarScreen.fxml"));
    Scene customerRecordsScene = new Scene(customerRecordsParent);
        
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    window.setScene(customerRecordsScene);
    window.show();     
    }
    
}
