/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewControllers;

import Model.Appointment;
import Model.Customer;
import Model.Inventory;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DBCalls;
import utils.DBConnection;
import static utils.DBConnection.conn;
import static utils.Logger.log;

/**
 * FXML Controller class
 *
 * @author michaelaruiz
 */
public class CalendarScreenController implements Initializable {

    @FXML
    private TableColumn<Appointment, String> customerCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> contactCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, String> startCol;
    @FXML
    private TableColumn<Appointment, String> endCol;
   

    
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    @FXML
    private TableView<Appointment> tableAppointments;
    
    private int appointmentId = 0;
    
   
    @FXML
    private Label weekText;
    @FXML
    private Label monthText;
    ObservableList<Appointment>appList;
    //private ToggleGroup tg;
    @FXML
    private Button monthButton;
    @FXML
    private Button weekButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        try {
            appList = FXCollections.observableArrayList();
            
            //idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            customerCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            
            appList = DBCalls.getAllAppointments();
            tableAppointments.setItems(appList);
            //populateTable(appList);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CalendarScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CalendarScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
            
    }

/*
    public void populateTable(ObservableList<Appointment> appList) {
        tableAppointments.setItems(appList);
        
    }*/
       
    
          
     

    @FXML
    private void addAppHandler(ActionEvent event) throws IOException {
    Parent addAppointmentParent = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
    Scene addAppointmentScene = new Scene(addAppointmentParent);
        
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    window.setScene(addAppointmentScene);
    window.show();    
    }

   /* 
    private void handleDeleteApp(Appointment selectedAppointment) {
        
    tableAppointments.getSelectionModel().getSelectedIndex();
    tableAppointments.getItems().remove(selectedAppointment);
    
    }
    */
    @FXML
    private void deleteAppHandler(ActionEvent event) throws SQLException, IOException{
      Appointment selectedAppointment = tableAppointments.getSelectionModel().getSelectedItem();
      appointmentId = selectedAppointment.getAppointmentId();
        if (selectedAppointment==null){
            infoBox("Please select appointment", null, "Please select appointment");
        }
        else{
          deleteAppointment(selectedAppointment);
          
          infoBox("Delete Successful", null, "Press Refresh to See Changes");
      }
    }
        
        
    private void deleteAppointment(Appointment selectedAppointment){
        PreparedStatement ps = null;
           
       
       try{
        String sql = "DELETE FROM appointment WHERE appointmentId = ?";   
        ps = DBConnection.conn.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ps.executeUpdate();
        
       // conn.commit();
       }
      catch (SQLException ex) {
            Logger.getLogger(CustomerRecordsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
   }
        
   

    @FXML
    private void updateAppHandler(ActionEvent event) throws IOException {
  
    Appointment selectedAppointment =  tableAppointments.getSelectionModel().getSelectedItem();
    if (selectedAppointment == null){
        infoBox("Please select Appointment", null, "Please select Appointment");
        }
    else {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewControllers/UpdateAppointmentScreen.fxml"));


        Parent calendarScreenParent = loader.load();
        Scene calendarScreenScene = new Scene(calendarScreenParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(calendarScreenScene);
        window.show();
        UpdateAppointmentScreenController controller = loader.getController();
        controller.updateForm1(selectedAppointment);
    }
    }
        
        
        /*
        
    Parent addAppointmentParent = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
    Scene addAppointmentScene = new Scene(addAppointmentParent);
        
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    window.setScene(addAppointmentScene);
    window.show(); 
*/

    @FXML
    private void customerHandler(ActionEvent event) throws IOException {
    Parent customerRecordsParent = FXMLLoader.load(getClass().getResource("CustomerRecordsScreen.fxml"));
    Scene customerRecordsScene = new Scene(customerRecordsParent);
        
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    window.setScene(customerRecordsScene);
    window.show();    
    }

    @FXML
    private void monthHandler(ActionEvent event) {
      
   LocalDateTime now = LocalDateTime.now();  
    LocalDateTime nowPlusMonth = now.plusMonths(1);
    FilteredList<Appointment>filteredData = new FilteredList<>(appList);//appList?
    filteredData.setPredicate(row ->{  //lambda allows row conditions to be shown
        //LocalDateTime rowDate1 = row.getValue();
        LocalDateTime rowDate = row.getStart();
        return rowDate.isAfter(now) && rowDate.isBefore(nowPlusMonth);
        
    });
       
    tableAppointments.setItems(filteredData);
     
    }


    @FXML
    private void weekHandler(ActionEvent event){
   LocalDateTime now = LocalDateTime.now();
    LocalDateTime nowPlus7 = now.plusDays(7);
    FilteredList<Appointment>filteredData= new FilteredList<>(appList);//appList?
    filteredData.setPredicate(row ->{   //lambda allows row conditions to be shown
      
        LocalDateTime rowDate = row.getStart();
       return rowDate.isAfter(now.minusDays(1)) && rowDate.isBefore(nowPlus7);
     
      }    );     
    tableAppointments.setItems(filteredData);
            
            

}
 public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    private void refreshHandler(ActionEvent event) throws ClassNotFoundException, SQLException {
    ObservableList<Appointment> appList = DBCalls.getAllAppointments();
            //populateTable(appList);
            tableAppointments.setItems(appList);
    }

    @FXML
    private void reportsHandler(ActionEvent event) throws IOException {
    Parent customerRecordsParent = FXMLLoader.load(getClass().getResource("/ViewControllers/ReportsScreen.fxml"));
    Scene customerRecordsScene = new Scene(customerRecordsParent);
        
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
    window.setScene(customerRecordsScene);
    window.show();       
        
    }
    
}