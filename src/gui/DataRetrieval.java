package Semester_Project;
//package MySQLCon;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class DataRetrieval {

    private int columnCount= 0;
    private Connection conn;
    private ResultSet rs;


    private ArrayList<java.sql.Date> dateArrayList;
    private ArrayList<java.sql.Time> timeArrayList;
    private ArrayList<Double> rainArrayList;
    private ArrayList<Double> windArrayList;
    private ArrayList<Double> humidityArrayList;
    private ArrayList<Double> temperatureArrayList;




    private void establishConnection(){
        //String url = "jdbc:mysql://raspy.ciopnus8w2eh.us-west-1.rds.amazonaws.com:3306/";
        String url = "jdbc:mysql://raspystudent.ciopnus8w2eh.us-west-1.rds.amazonaws.com:3306/";

        //;sendStringParametersAsUnicode=false"
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

        this.conn = conn;
    }

    private DataRetrieval(){
        establishConnection();

        try {

            this.rs = getWeather(conn);
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
            dateArrayList = new ArrayList<>(columnCount);
            timeArrayList = new ArrayList<>(columnCount);
            rainArrayList = new ArrayList<>(columnCount);
            windArrayList = new ArrayList<>(columnCount);
            humidityArrayList = new ArrayList<>(columnCount);
            temperatureArrayList = new ArrayList<>(columnCount);

            while(rs.next()){
                dateArrayList.add(rs.getDate(1));
                timeArrayList.add(rs.getTime(2));
                temperatureArrayList.add(rs.getDouble(3));
                rainArrayList.add(rs.getDouble(4));
                windArrayList.add(rs.getDouble(5));
                humidityArrayList.add(rs.getDouble(6));

            }
        }
        catch(Exception e){
            //
        }

    }


    private void updateDB(Connection conn, java.sql.Date inputDate, java.sql.Time inputTime,
                          double temperature, double rainInches, double windSpeed, int humidityPercent) {

        Statement stmt = null;

        try {
            stmt = conn.createStatement();

//            String sql = "insert into raspystudent.WEATHER(Date, Time, Temp, Rainfall, Wind,Humidity) " +
//                         "values(date,time,Temp,Rainfall,Wind,Humidity)";

            PreparedStatement pst = conn.prepareStatement("insert into WEATHER(" +
                    "Date, Time, Temperature, rainInches, windSpeed,humidityPercent) values(?,?,?,?,?,?)");
            pst.setDate(1, inputDate);
            pst.setTime(2, inputTime);
            pst.setDouble(3, temperature);
            pst.setDouble(4, rainInches);
            pst.setDouble(5, windSpeed);
            pst.setDouble(6, humidityPercent);
            pst.execute();
            //pst.close();

            //stmt.executeUpdate(sql);
        }

        catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
    }

    public void closeConnection(Connection conn, Statement stmt){

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

    // Get all data
    public static ResultSet getWeather(Connection conn)throws Exception {
        return getResultSet(conn,"SELECT * FROM WEATHER");

    }

    public static ResultSet getResultSet(Connection conn, String sql)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Statement stmt = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_FORWARD_ONLY);
        return stmt.executeQuery(sql);

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

    private void deleteAll(Connection conn){
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("TRUNCATE WEATHER");
            pst.execute();


        }
        catch(SQLException e){

        }
    }

    public static void main(String[] args){


        // Unit tests
        DataRetrieval bob = new DataRetrieval();

        java.sql.Date myDate = stringToSQLDate("11-27-2017");
        java.sql.Time myTime = stringToSQLTime("05:45:00");


//        java.sql.Date myDate = stringToSQLDate(args[0]);
//        java.sql.Time myTime = stringToSQLTime(args[1]);
//        double temp = Double.parseDouble(args[2]);
//        double rain = Double.parseDouble(args[3]);
//        double wind = Double.parseDouble(args[4]);
//        int percent = Integer.parseInt(args[5]);
//        bob.updateDB(bob.conn,myDate,myTime,4,4,4,4);
//        bob.updateDB(connection,myDate,myTime,temp,rain,wind,percent);

        bob.closeConnection(bob.conn, null);

        System.out.println(Arrays.toString(bob.timeArrayList.toArray()));
        System.out.println("------");
        System.out.println(Arrays.toString(bob.dateArrayList.toArray()));
        System.out.println("------");
        System.out.println(Arrays.toString(bob.temperatureArrayList.toArray()));
        System.out.println("------");
        System.out.println(Arrays.toString(bob.rainArrayList.toArray()));
        System.out.println("------");
        System.out.println(Arrays.toString(bob.windArrayList.toArray()));
        System.out.println("------");
        System.out.println(Arrays.toString(bob.humidityArrayList.toArray()));

    }
}
















/*

    long startTime = System.currentTimeMillis();
    long endTime = System.currentTimeMillis();
    float elapse = ((endTime-startTime)/1000);
        System.out.println("That took like " + elapse + " seconds");
 */