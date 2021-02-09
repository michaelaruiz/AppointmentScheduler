/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.DBCalls;
import static utils.Logger.log;

/**
 * FXML Controller class
 *
 * @author michaelaruiz
 */
public class LoginScreenController implements Initializable {

    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private Button signInHandler;
    ResourceBundle rb;
    @FXML
    private Label signInTitle;
    @FXML
    private Label usernameTitle;
    @FXML
    private Label passwordTitle;
    // String key1="Error" , key2="x";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println(Locale.getDefault());
        /* signInTitle.setText(rb.getString("SignIn"));
        usernameTitle.setText(rb.getString("Username"));
        passwordTitle.setText(rb.getString("Password"));
        
         */

        //Locale.setDefault(new Locale("en","US"));
        try {
            rb = ResourceBundle.getBundle("schedulingprojectmichaelaruiz/Nat", Locale.getDefault());
            this.rb = rb;
            if (Locale.getDefault().getLanguage().equals("es")) {
                // key1=rb.getString("ErrorTitle");
                //key2=rb.getString("EnterU&P");

            }
        } catch (Exception e) {

        }

        // TODO
    }

    @FXML
    private void signInHandler(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

        Window owner = signInHandler.getScene().getWindow();
        System.out.println(usernameText.getText());
        System.out.println(passwordText.getText());

        if (passwordText.getText().isEmpty() && usernameText.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("ErrorTitle");
            alert.setContentText("EnterU&P");

            alert.showAndWait();
            return;
        } else if (usernameText.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(rb.getString("ErrorTitle"));
            alert.setContentText(rb.getString("EnterU"));

            alert.showAndWait();
            return;
        } else if (passwordText.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(rb.getString("ErrorTitle"));
            alert.setContentText(rb.getString("EnterP"));

            alert.showAndWait();
            return;

        }

        String userName = usernameText.getText();
        String password = usernameText.getText();

        boolean validate = DBCalls.validateLogin(userName, password);
        boolean checkIfAppIn15 = DBCalls.checkIfAppIn15();
        if (!validate) {
            infoBox(rb.getString("U&PNoMatch"), null, rb.getString("Failed"));

        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(rb.getString("SuccessTitle"));
            alert.setHeaderText(rb.getString("SuccessTitle"));
            alert.setContentText(rb.getString("U&PMatch"));
            log(userName);
            alert.showAndWait();

            if (checkIfAppIn15) {
                //boolean checkIfAppIn15 = DBCalls.checkIfAppIn15();       

                //Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(rb.getString("SuccessTitle"));
                alert.setHeaderText(rb.getString("SuccessTitle"));
                alert.setContentText(rb.getString("App15"));

                alert.showAndWait();
            }

            Parent mainParent = FXMLLoader.load(getClass().getResource("/ViewControllers/CalendarScreen.fxml"));
            Scene mainScene = new Scene(mainParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainScene);
            window.show();

           
        }
      

    }

    

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}
