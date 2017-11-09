package Semester_Project;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class DataRetrieval {

    private Connection establishConnection(){
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
            System.out.println("Oops something went wrong when trying to connect to the database");

        }
        return conn;
    }

    // TODO - test me
    private void updateDB(Connection conn, java.sql.Date inputDate, java.sql.Time inputTime,
                          double temperature, double rainInches, double windSpeed, int humidityPercent){


        Statement stmt = null;

        try{
            stmt = conn.createStatement();

//            String sql = "insert into raspystudent.WEATHER(Date, Time, Temp, Rainfall, Wind,Humidity) " +
//                         "values(date,time,Temp,Rainfall,Wind,Humidity)";

            PreparedStatement pst = conn.prepareStatement("insert into WEATHER(Date, Time, Temp, Rainfall, Wind,Humidity) values(?,?,?,?,?,?)");
            pst.setDate(1,inputDate);
            pst.setTime(2,inputTime);
            pst.setDouble(3,temperature);
            pst.setDouble(4,rainInches);
            pst.setDouble(5,windSpeed);
            pst.setDouble(6,humidityPercent);
            pst.execute();
            pst.close();

            //stmt.executeUpdate(sql);
        }

        catch(SQLException e){
            System.out.println("SQL Exception: " + e);
        }

        finally{
            // Close resources
            try{
                if(stmt!=null){
                    conn.close();
                }
            }
            catch(SQLException e){
                System.out.println("SQL Exception: " + e);
                //
            }

            try{
                if(conn!= null){
                    conn.close();
                }
            }
            catch(SQLException e){
                System.out.println("SQL Exception: " + e);
                //
            }
        }


    }


    // TODO - write me

    private void readDB(Connection conn){

    }
    private static Date stringToSQLDate(String string){
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        java.sql.Date sqlDate = null;

        try{
            java.util.Date date = format.parse(string);
            sqlDate = new Date(date.getTime());


        }
        catch(Exception e){
            System.out.println("Error parsing date string: " + e);
        }
        return sqlDate;
    }

    private static Time stringToSQLTime(String string){
        java.sql.Time sqlTime = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");


        try{
            java.util.Date time = simpleDateFormat.parse(string);
            sqlTime = new Time(time.getTime());
        }
        catch(Exception e){
            System.out.println("Error parsing time string: " + e);
        }


        return sqlTime;
    }

    public static void main(String[] args){
        
        // Unit Testing
        DataRetrieval bob = new DataRetrieval();
        Connection connection = bob.establishConnection();

        java.sql.Date myDate = stringToSQLDate("01-01-2011");
        java.sql.Time myTime = stringToSQLTime("07:34:33");

        bob.updateDB(connection,myDate,myTime,4,3,2,4);

    }

}
