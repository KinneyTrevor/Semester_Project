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

    // TODO
    private void updateDB(Connection conn, java.sql.Date date, java.sql.Time time, double temperature, double rainInches, double windSpeed, int humidityPercent){


        Statement stmt = null;

        System.out.println("Creating table in given database...");
        try{
            stmt = conn.createStatement();

            String sql = "";

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
                System.out.println("Something went wrong in SQL");
                //
            }

            try{
                if(conn!= null){
                    conn.close();
                }
            }
            catch(SQLException e){
                System.out.println("Something went wrong in SQL");
                //
            }
        }
        System.out.println("We worked fam");


    }

    private static Date stringToSQLDate(String string){
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        java.sql.Date sqlDate = null;

        try{
            java.util.Date date = format.parse(string);
            sqlDate = new Date(date.getTime());


        }
        catch(Exception e){
            System.out.println("Mr Parse no here");
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
            System.out.println("I don't know you, that's my parse!");
        }


        return sqlTime;
    }

    public static void main(String[] args){
        //DataRetrieval bob = new DataRetrieval();
        //Connection connection = bob.establishConnection();


    }

}


// unit tests
//        System.out.println("input: " + boar);
//        System.out.println("output: " + stringToSQLDate(boar));

//        String time = "03:40:33";
//        System.out.println("input: " + time);
//        System.out.println("output: " + stringToSQLTime(time));