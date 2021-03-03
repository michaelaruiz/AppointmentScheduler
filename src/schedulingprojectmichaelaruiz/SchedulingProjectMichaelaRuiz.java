/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingprojectmichaelaruiz;


//import Model.Inventory;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBConnection;

/**
 *
 * @author michaelaruiz
 */
public class SchedulingProjectMichaelaRuiz extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/ViewControllers/LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        
        
        DBConnection.startConnection();
        
        //Inventory.init();
       
    
      launch(args);
        DBConnection.closeConnection();
    
    
      
     
  
    
    
    /*
    if(Locale.getDefault().getLanguage().equals("es"))
        System.out.println(rb.getString("the") + " " + rb.getString("username") + " " + rb.getString("and") + " "   
                + rb.getString("password") +  " " + rb.getString("don't") +  " " + rb.getString("match") + ".");
        System.out.println(rb.getString("error"));
        System.out.println(rb.getString("please") + " " + rb.getString("enter") + " " + rb.getString("username"));
        System.out.println(rb.getString("please") + " " + rb.getString("enter") + " " + rb.getString("password"));
        System.out.println(rb.getString("login") + " " + rb.getString("success"));
        System.out.println(rb.getString("success"));
   */
//inventory.init
}
}

 