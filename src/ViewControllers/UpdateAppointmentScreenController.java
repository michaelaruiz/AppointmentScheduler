/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewControllers;

import Model.Appointment;
import Model.Customer;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.util.StringConverter;
import utils.DBCalls;
import utils.DBConnection;
import static utils.DBConnection.conn;

/**
 * FXML Controller class
 *
 * @author michaelaruiz
 */
public class UpdateAppointmentScreenController implements Initializable {
/*
    private TextField updateAppStartText;
   
    private TextField updateAppCustText;
    */
    @FXML
    private TextField updateAppTitleText;
    @FXML
    private TextField updateAppDescriptionText;
    @FXML
    private ComboBox<String> updateAppLocationText;
    ObservableList<String> locationList = FXCollections.observableArrayList("Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6");
    @FXML
    private ComboBox<String> updateAppContactText;
    @FXML
    private ComboBox<String> updateAppTypeText;
    ObservableList<String> list = FXCollections.observableArrayList("Cardiology", "Dental", "Hygiene", "Orthodontal");
    ObservableList<String> contactList = FXCollections.observableArrayList("Dr. Jones", "Dr. Christensen", "Dr. Lee", "Dr. Lopez", "Sarah Thomas");
   // private TextField updateAppEndText;
    @FXML
    private ComboBox<Customer> updateAppCust;
    ObservableList<Customer>customersList = FXCollections.observableArrayList();
    @FXML
    private DatePicker updateAppDate;
    @FXML
    private ComboBox<String> updateHoursOptions;
    ObservableList<String> hourslist = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> updateMinutesOptions;
    ObservableList<String> minutesList = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> updateHoursOptions2;
    ObservableList<String> hourslist2 = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> updateMinutesOptions2;
    ObservableList<String> minuteslist2 = FXCollections.observableArrayList();
    
    private int appointmentId = 0;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        customersList.addAll(DBCalls.getAllCustomers());
       
        updateAppCust.setItems(customersList);
        
        updateAppTypeText.setItems(list);
        updateAppContactText.setItems(contactList);
        updateAppLocationText.setItems(locationList);
        
       hourslist.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutesList.addAll("00", "15", "30", "45");
        //end
        hourslist2.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minuteslist2.addAll("00", "15", "30", "45");
        
        updateHoursOptions2.setItems(hourslist2);
        updateMinutesOptions2.setItems(minuteslist2);
        
        
        updateHoursOptions.setItems(hourslist);
        updateMinutesOptions.setItems(minutesList);
        
        updateHoursOptions.getSelectionModel().selectFirst();
        updateMinutesOptions.getSelectionModel().selectFirst();
        //updateAppCust.getSelectionModel().selectFirst();
        updateAppTypeText.getSelectionModel().selectFirst();
        updateHoursOptions2.getSelectionModel().selectFirst();
        updateMinutesOptions2.getSelectionModel().selectFirst();
        updateAppCust.getSelectionModel().selectFirst();
       // updateAppCust.getSelectionModel().selectFirst();
      updateAppContactText.getSelectionModel().selectFirst();
        
        String pattern = "yyyy-MM-dd";

updateAppDate.setPromptText(pattern.toLowerCase());

updateAppDate.setConverter(new StringConverter<LocalDate>() {
     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

     @Override 
     public String toString(LocalDate date) {
         if (date != null) {
             return dateFormatter.format(date);
         } else {
             return "";
         }
     }

     @Override 
     public LocalDate fromString(String string) {
         if (string != null && !string.isEmpty()) {
             return LocalDate.parse(string, dateFormatter);
         } else {
             return null;
         }
     }
 });

    }   
     public void updateForm1(Appointment selectedAppointment){
      updateAppTitleText.setText(selectedAppointment.getTitle());
      updateAppDescriptionText.setText(selectedAppointment.getDescription());
      updateAppLocationText.setValue(selectedAppointment.getLocation());
      updateAppContactText.setValue(selectedAppointment.getContact());
      //updateAppCust.getSelectionModel().getSelectedItem().setCustomerId(selectedAppointment.getCustomerId());
       for (Customer c:customersList){
            if (c.getCustomerId() == selectedAppointment.getCustomerId()){
                updateAppCust.setValue(c);
                break;
            }
                
        }
      
      updateAppTypeText.setValue(selectedAppointment.getType());
      updateAppDate.setValue(selectedAppointment.getStart().toLocalDate());
      
      int x = selectedAppointment.getStart().getHour();
      if(x < 10){
          updateHoursOptions.setValue("0" + x);
      }
      else{
          updateHoursOptions.setValue(Integer.toString(x));  
      }
       x = selectedAppointment.getStart().getMinute();
       if(x < 10 ){
           updateMinutesOptions.setValue("0" + x);
       }
       else{
           updateMinutesOptions.setValue(Integer.toString(x));
       }
        x = selectedAppointment.getEnd().getHour();
      if(x < 10){
          updateHoursOptions2.setValue("0" + x);
      }
      else{
          updateHoursOptions2.setValue(Integer.toString(x));  
      }
       x = selectedAppointment.getEnd().getMinute();
       if(x < 10 ){
           updateMinutesOptions2.setValue("0" + x);
       }
       else{
           updateMinutesOptions2.setValue(Integer.toString(x));
       }
     
      appointmentId = selectedAppointment.getAppointmentId();
              
    }

 
    
        
       
    
    
    @FXML
    private void updateAppCancelHandler(ActionEvent event) throws IOException {
     Parent mainScreenParent = FXMLLoader.load(getClass().getResource("CalendarScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mainScreenScene);
        window.show();    
    }

    @FXML
    private void updateAppointment(ActionEvent event) throws IOException {
                 //Date/Time 
                 LocalDate date = updateAppDate.getValue();
                 String hourS = updateHoursOptions.getValue();
                 String minuteS = updateMinutesOptions.getValue();
                 int hour = Integer.parseInt(hourS);
                 int minute = Integer.parseInt(minuteS);
                 LocalTime ltS= LocalTime.of(hour, minute);
                 LocalDateTime ldtStart = LocalDateTime.of(date, ltS);
                 
                 LocalDate dateE = updateAppDate.getValue();
                 String hoursE = updateHoursOptions2.getValue();
                 String minutesE = updateMinutesOptions2.getValue();
                 int hour1 = Integer.parseInt(hoursE);
                 int minute1 = Integer.parseInt(minutesE);
                 LocalTime ltE = LocalTime.of(hour1, minute1);
                 LocalDateTime ldtEnd = LocalDateTime.of(dateE, ltE);
                 
               //Business Hours
                 LocalTime startTime = LocalTime.of(8, 00);
                 LocalTime endTime = LocalTime.of(18, 00);
         
                 PreparedStatement ps = null;
                 
                 
              if (checkForOverlappingApps(ldtStart, ldtEnd)){
                 System.out.println("Overlapping Appointments");
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("OverlappingAppointments");
                    alert.setHeaderText("error");
                    alert.setContentText("Please Change AppointmentTime");

                    alert.showAndWait();
                    return;     
                }
            
              else if (ltS.isBefore(startTime)|| ltS.isAfter(endTime) || ltE.isAfter(endTime) || ltE.isBefore(startTime)){
                    System.out.println("appointment time not in range");
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Appointment Time Outside Business Hours");
                    alert.setHeaderText("error");
                    alert.setContentText("Business Hours from 8AM to 6PM");

                    alert.showAndWait();
                    return;
              
               }
        try {
           
            
            
            
            String sql = "UPDATE appointment SET customerId = ?, title = ?, "
                    + "description = ?, location = ?, contact = ?, "
                    + "type = ?, start = ?, end = ?, lastUpdate = NOW(), lastUpdateBy = 'admin' WHERE appointmentId = ?  ";
            
            ps = DBConnection.conn.prepareStatement(sql);
            
            Customer value = updateAppCust.getSelectionModel().getSelectedItem();
            
            ps.setInt(1, value.getCustomerId());
            ps.setString(2, updateAppTitleText.getText());
            ps.setString(3, updateAppDescriptionText.getText());
            
            String value2 = updateAppLocationText.getSelectionModel().getSelectedItem();
            ps.setString(4, value2);
            
            String value3 = updateAppContactText.getSelectionModel().getSelectedItem();
                 //ps.setString(6, value3);
            ps.setString(5, value3);
            
            String value4 = updateAppTypeText.getSelectionModel().getSelectedItem();
            
            
            ps.setString(6, value4);
            
            //THIS IS WHERE TO PUT TIME ZONE!
            
          
            
            //Zone
            ps.setTimestamp(7, Timestamp.valueOf(DBCalls.toUTC(ldtStart)));
            ps.setTimestamp(8, Timestamp.valueOf(DBCalls.toUTC(ldtEnd)));
            ps.setInt(9, appointmentId);
            
                  
            // execute the preparedstatement
            System.out.println(ps);
            ps.executeUpdate();
            
            Parent mainParent = FXMLLoader.load(getClass().getResource("CalendarScreen.fxml"));
            Scene mainScene = new Scene(mainParent);
            
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(mainScene);
            window.show(); 

            
                            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateAppointmentScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
     public boolean checkForOverlappingApps(LocalDateTime ldtStart, LocalDateTime ldtEnd) {
        try {
            PreparedStatement ps = null;
            String sql =
                    "SELECT * FROM appointment WHERE ? BETWEEN start AND end OR ? BETWEEN start AND end OR ? < start AND ? > end";
            ps = DBConnection.conn.prepareStatement(sql);

            ps.setTimestamp(1, Timestamp.valueOf(DBCalls.toUTC(ldtStart)));
            ps.setTimestamp(2, Timestamp.valueOf(DBCalls.toUTC(ldtEnd)));
            ps.setTimestamp(3, Timestamp.valueOf(DBCalls.toUTC(ldtStart)));
            ps.setTimestamp(4, Timestamp.valueOf(DBCalls.toUTC(ldtEnd)));
            
            /*
            ps.setTimestamp(1, Timestamp.valueOf(newStart.toLocalDateTime()));
            ps.setTimestamp(2, Timestamp.valueOf(newEnd.toLocalDateTime()));
*/
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    
}
     
 
    
    }
    
    

