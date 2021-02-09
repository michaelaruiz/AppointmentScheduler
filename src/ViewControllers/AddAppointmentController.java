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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JComboBox;

import utils.DBCalls;
import utils.DBConnection;
import static utils.DBConnection.conn;

/**
 * FXML Controller class
 *
 * @author michaelaruiz
 */
public class AddAppointmentController implements Initializable {

    // private DatePicker newAppStartText;
    //final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML
    private ComboBox<Customer> newAppCustText;
    @FXML
    private TextField newAppTitleText;

    @FXML
    private TextField newAppDescriptionText;
    @FXML
    private ComboBox<String> newAppLocationText;
    ObservableList<String> locationList = FXCollections.observableArrayList("Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6");
    @FXML
    private ComboBox<String> newAppContactText;
    ObservableList<String> contactList = FXCollections.observableArrayList("Dr. Jones", "Dr. Christensen", "Dr. Lee", "Dr. Lopez", "Sarah Thomas");
    @FXML
    private ComboBox<String> newAppTypeText;

    ObservableList<String> list = FXCollections.observableArrayList("Cardiology", "Dental", "Hygiene", "Orthodontal");
    //private TextField newAppEndText;
    ObservableList<Customer> customersList = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> hoursOptions;
    ObservableList<String> hourslist = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> minutesOptions;
    ObservableList<String> minutesList = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> hoursOptions2;
    ObservableList<String> hourslist2 = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> minutesOptions2;
    ObservableList<String> minuteslist2 = FXCollections.observableArrayList();
    @FXML
    private DatePicker appDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        customersList.addAll(DBCalls.getAllCustomers());
        newAppCustText.setItems(customersList);

        newAppTypeText.setItems(list);
        newAppContactText.setItems(contactList);
        newAppLocationText.setItems(locationList);

        //start
        hourslist.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutesList.addAll("00", "15", "30", "45");
        //end
        hourslist2.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minuteslist2.addAll("00", "15", "30", "45");

        hoursOptions2.setItems(hourslist2);
        minutesOptions2.setItems(minuteslist2);

        hoursOptions.setItems(hourslist);
        minutesOptions.setItems(minutesList);

        hoursOptions.getSelectionModel().selectFirst();
        minutesOptions.getSelectionModel().selectFirst();
        newAppCustText.getSelectionModel().selectFirst();
        newAppTypeText.getSelectionModel().selectFirst();
        hoursOptions2.getSelectionModel().selectFirst();
        minutesOptions2.getSelectionModel().selectFirst();
        newAppContactText.getSelectionModel().selectFirst();
        newAppLocationText.getSelectionModel().selectFirst();

        String pattern = "yyyy-MM-dd";

        appDate.setPromptText(pattern.toLowerCase());

        appDate.setConverter(new StringConverter<LocalDate>() {
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

    @FXML
    public void addAppointment(ActionEvent event) throws IOException, SQLException {
        //Date/Time 
        LocalDate date = appDate.getValue();
        String hourS = hoursOptions.getValue();
        String minuteS = minutesOptions.getValue();
        int hour = Integer.parseInt(hourS);
        int minute = Integer.parseInt(minuteS);
        LocalTime ltS = LocalTime.of(hour, minute);
        LocalDateTime ldtStart = LocalDateTime.of(date, ltS);

        //LocalDate dateE = appDate.getValue();
        String hoursE = hoursOptions2.getValue();
        String minutesE = minutesOptions2.getValue();
        int hour1 = Integer.parseInt(hoursE);
        int minute1 = Integer.parseInt(minutesE);
        LocalTime ltE = LocalTime.of(hour1, minute1);
        LocalDateTime ldtEnd = LocalDateTime.of(date, ltE);

        //Business Hours
        LocalTime startTime = LocalTime.of(8, 00);
        LocalTime endTime = LocalTime.of(18, 00);
        
         if (checkForOverlappingApps(ldtStart, ldtEnd)) {
                System.out.println("Overlapping Appointments");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("OverlappingAppointments");
                alert.setHeaderText("error");
                alert.setContentText("Please Change AppointmentTime");

                alert.showAndWait();
                return;
            } else if (ltS.isBefore(startTime) || ltS.isAfter(endTime) || ltE.isAfter(endTime) || ltE.isBefore(startTime)) {
                System.out.println("appointment time not in range");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Appointment Time Outside Business Hours");
                alert.setHeaderText("error");
                alert.setContentText("Business Hours from 8AM to 6PM");

                alert.showAndWait();
                return;
            }

        try {
            PreparedStatement ps = null;
            // for time zone

            String sql
                    = "INSERT INTO appointment(appointmentId, customerId, userId, title, description,"
                    + "location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) "
                    + "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ' ', ?, ?, NOW(), 'admin', NOW(), 'admin') ";

            ps = DBConnection.conn.prepareStatement(sql);

            Customer value = newAppCustText.getSelectionModel().getSelectedItem();
            ps.setInt(1, value.getCustomerId());
            ps.setInt(2, DBCalls.getLoggedInUserId());
            ps.setString(3, newAppTitleText.getText());
            ps.setString(4, newAppDescriptionText.getText());
            
            String value2 = newAppLocationText.getSelectionModel().getSelectedItem();
            ps.setString(5, value2);
            String value3 = newAppContactText.getSelectionModel().getSelectedItem();
            ps.setString(6, value3);

            String value4 = newAppTypeText.getSelectionModel().getSelectedItem();
            ps.setString(7, value4);

            //THIS IS WHERE TO PUT TIME ZONE!
         
            ps.setTimestamp(8, Timestamp.valueOf(DBCalls.toUTC(ldtStart)));
            ps.setTimestamp(9, Timestamp.valueOf(DBCalls.toUTC(ldtEnd)));

 
           
            
                ps.execute();

                if (ps.getUpdateCount() > 0) {
                    System.out.println(ps.getUpdateCount() + " rows affected");
                } else {
                    System.out.println("no rows changed");
                }

                //ResultSet rs = ps.getResultSet();
                //System.out.println(rs.next());
                Parent mainParent = FXMLLoader.load(getClass().getResource("/ViewControllers/CalendarScreen.fxml"));
                Scene mainScene = new Scene(mainParent);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(mainScene);
                window.show();

            

        } catch (SQLException ex) {
            Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public boolean checkForOverlappingApps(LocalDateTime ldtStart, LocalDateTime ldtEnd) {
        try {
            PreparedStatement ps = null;
            String sql
                    = "SELECT * FROM appointment WHERE ? BETWEEN start AND end OR ? BETWEEN start AND end OR ? < start AND ? > end";
            ps = DBConnection.conn.prepareStatement(sql);
            /*
            ps.setTimestamp(1, Timestamp.valueOf(ldt));
            ps.setTimestamp(2, Timestamp.valueOf(ldt2));
            ps.setTimestamp(3, Timestamp.valueOf(ldt));
            ps.setTimestamp(4, Timestamp.valueOf(ldt2));
*/
            
            ps.setTimestamp(1, Timestamp.valueOf(DBCalls.toUTC(ldtStart)));
            ps.setTimestamp(2, Timestamp.valueOf(DBCalls.toUTC(ldtEnd)));
            ps.setTimestamp(3, Timestamp.valueOf(DBCalls.toUTC(ldtStart)));
            ps.setTimestamp(4, Timestamp.valueOf(DBCalls.toUTC(ldtEnd)));
             
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @FXML
    private void newAppCancelHandler(ActionEvent event) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("CalendarScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(mainScreenScene);
        window.show();

    }
}
