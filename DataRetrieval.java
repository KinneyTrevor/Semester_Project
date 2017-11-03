package Semester_Project;


import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.awt.Desktop;
import java.net.URI;

public class DataRetrieval {

    private void getDataOld(){
        if(Desktop.isDesktopSupported()){
            try{
                Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/16veLFPwHKZQUU59-TnRtfB0cFoxjOGq3mlP1C9xOIoA/export?format=doc"));
            }
            catch(java.net.URISyntaxException b){
                System.out.println("oof");
            }
            catch(java.io.IOException c){
                System.out.println("ouch");
            }
        }
    }

    private void getData(){
        //String url = "jdbc:mysql://raspy.ciopnus8w2eh.us-west-1.rds.amazonaws.com:3306/";
        String url = "jdbc:mysql://raspystudent.ciopnus8w2eh.us-west-1.rds.amazonaws.com:3306/";
        String userName = "raspystudent";
        String password = "weatherstation";
        String dbName = "raspystudent";
        String driver = "com.mysql.jdbc.Driver";
        Connection conn = null;
        Statement stmt = null;

        try{

            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Database Connection Successful!");
        }
        catch(Exception e){
            System.out.println(e);

        }

        System.out.println("Creating table in given database...");
        try{
            stmt = conn.createStatement();

            String sql = "CREATE TABLE REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
        }

        catch(SQLException e){
            System.out.println("Shits fucked");
        }

        finally{
            // Close resources
            try{
                if(stmt!=null){
                    conn.close();
                }
            }
            catch(SQLException e){
                //
            }

            try{
                if(conn!= null){
                    conn.close();
                }
            }
            catch(SQLException e){
                //
            }
        }
        System.out.println("We worked fam");


    }

    private void createTable(){


    }

    private void dropTable(){

    }

    // Read data in from file
    private void readData(){

    }

    public static void main(String[] args){
        DataRetrieval bob = new DataRetrieval();
        bob.getData();

    }

}
