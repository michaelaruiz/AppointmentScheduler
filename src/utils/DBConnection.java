/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
/*import com.mysql.jdbc.PreparedStatement;*/
import com.sun.rowset.CachedRowSetImpl;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author michaelaruiz
 */
public class DBConnection {
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U07CEz";
    
    private static final String jdbcURL = protocol + vendorName + ipAddress;
    
    
    //reference to driver
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    public static Connection conn = null;
    
    
    private static final String userName = "U07CEz";
    private static String passWord = "53688984765";
    
    public static Connection startConnection() 
    {
        if (conn != null){
           return conn; 
        }
            
          try{  
        Class.forName(MYSQLJDBCDriver);
        conn = (Connection) DriverManager.getConnection(jdbcURL, userName, passWord);
        System.out.println("Connection successful!");
        
        }
          catch(ClassNotFoundException e)
          {
              System.out.println(e.getMessage());
          }
          catch(SQLException e)
          {
              System.out.println("Error" + e.getMessage());
          }
          
          return conn;
                  }   
    
    public static void closeConnection() 
    {
        try {
            conn.close();
           System.out.println("Connection closed!");
        }
        catch(SQLException e)
        {
            System.out.println("Error" + e.getMessage());
        }
    }
    
   
    
    
//for insert, delete, update
   
    public static void DBExecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        try{
            startConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        }
       catch(SQLException e){
           System.out.println("Problem occured"+e);
           throw e;
       }
        /*finally{
            if(stmt!=null){
             stmt.close();   
            }
            closeConnection();
        }
        */
    }
    
//retrieving record from database
  
    public static ResultSet DBExecute(String sqlQuery) {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        
        try{
            startConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlQuery);
           crs = new CachedRowSetImpl();
           crs.populate(rs);
        }
        catch(SQLException e){
            System.out.println("Error occurred"+e);
           
        }    
      
     return crs;
     
    }

}
