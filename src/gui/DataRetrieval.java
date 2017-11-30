package Semester_Project;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class DataRetrieval
{

<<<<<<< HEAD
	int ID = 0;
=======
    private int columnCount= 0;
    private Connection conn;
    private ResultSet rs;
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git

<<<<<<< HEAD
	private Connection establishConnection()
	{
		// String url =
		// "jdbc:mysql://raspy.ciopnus8w2eh.us-west-1.rds.amazonaws.com:3306/";
		String url = "jdbc:mysql://raspystudent.ciopnus8w2eh.us-west-1.rds.amazonaws.com:3306/";
=======
    private ArrayList<java.sql.Date> dateArrayList;
    private ArrayList<java.sql.Time> timeArrayList;
    private ArrayList<Double> rainArrayList;
    private ArrayList<Double> windArrayList;
    private ArrayList<Double> humidityArrayList;
    private ArrayList<Double> temperatureArrayList;

    private void establishConnection(){
        //String url = "jdbc:mysql://raspy.ciopnus8w2eh.us-west-1.rds.amazonaws.com:3306/";
        String url = "jdbc:mysql://raspystudent.ciopnus8w2eh.us-west-1.rds.amazonaws.com:3306/";
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git

		// ;sendStringParametersAsUnicode=false"
		String userName = "raspystudent";
		String password = "weatherstation";
		String dbName = "raspystudent";
		String driver = "com.mysql.jdbc.Driver";
		Connection conn = null;
		Statement stmt = null;

		try
		{

			conn = DriverManager.getConnection(url + dbName, userName, password);
			System.out.println("Database Connection Successful!");
		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println("Oops something went wrong when trying to connect to the database");

<<<<<<< HEAD
		}
		return conn;
	}
=======
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
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git

	private void updateDB(Connection conn, java.sql.Date inputDate, java.sql.Time inputTime, double temperature,
			double rainInches, double windSpeed, int humidityPercent, int ID)
	{

<<<<<<< HEAD
		Statement stmt = null;
=======
    private void updateDB(Connection conn, java.sql.Date inputDate, java.sql.Time inputTime,
                          double temperature, double rainInches, double windSpeed, double humidityPercent) {
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git

		try
		{
			stmt = conn.createStatement();

			// String sql = "insert into raspystudent.WEATHER(Date, Time, Temp, Rainfall,
			// Wind,Humidity) " +
			// "values(date,time,Temp,Rainfall,Wind,Humidity)";

			PreparedStatement pst = conn.prepareStatement("insert into WEATHER("
					+ "Date, Time, Temperature, rainInches, windSpeed,humidityPercent,ID) values(?,?,?,?,?,?,?)");
			pst.setDate(1, inputDate);
			pst.setTime(2, inputTime);
			pst.setDouble(3, temperature);
			pst.setDouble(4, rainInches);
			pst.setDouble(5, windSpeed);
			pst.setDouble(6, humidityPercent);
			pst.setInt(7, ID);
			pst.execute();
			// pst.close();

<<<<<<< HEAD
			// stmt.executeUpdate(sql);
		}
=======
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
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git

		catch (SQLException e)
		{
			System.out.println("SQL Exception: " + e);
		}
	}

	public void closeConnection(Connection conn, Statement stmt)
	{

		try
		{
			if (stmt != null)
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			System.out.println("SQL Exception: " + e);
			//
		}

		try
		{
			if (conn != null)
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			System.out.println("SQL Exception: " + e);
			//
		}
	}

	// Get all data
	public static ResultSet getWeather(Connection conn) throws Exception
	{
		return getResultSet(conn, "SELECT * FROM WEATHER");

	}

	public static ResultSet getResultSet(Connection conn, String sql) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Statement stmt = conn.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_FORWARD_ONLY);
		return stmt.executeQuery(sql);

	}

	private static Date stringToSQLDate(String string)
	{
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		java.sql.Date sqlDate = null;

		try
		{
			java.util.Date date = format.parse(string);
			sqlDate = new Date(date.getTime());

		} catch (Exception e)
		{
			System.out.println("Error parsing date string: " + e);
		}
		return sqlDate;
	}

	private static Time stringToSQLTime(String string)
	{
		java.sql.Time sqlTime = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");

		try
		{
			java.util.Date time = simpleDateFormat.parse(string);
			sqlTime = new Time(time.getTime());
		} catch (Exception e)
		{
			System.out.println("Error parsing time string: " + e);
		}

		return sqlTime;
	}

	private void deleteAll(Connection conn)
	{
		Statement stmt = null;

		try
		{
			stmt = conn.createStatement();
			PreparedStatement pst = conn.prepareStatement("TRUNCATE WEATHER");
			pst.execute();

		} catch (SQLException e)
		{

		}
	}

	// TODO - Set up so main method accepts args for each line as args
	public static void main(String[] args)
	{

		long startTime = System.currentTimeMillis();

<<<<<<< HEAD
		// Unit tests
		DataRetrieval bob = new DataRetrieval();
		Connection connection = bob.establishConnection();
=======
    public static void main(String[] args){
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git

<<<<<<< HEAD
		bob.deleteAll(connection);

		java.sql.Date myDate = stringToSQLDate("01-01-2011");
		java.sql.Time myTime = stringToSQLTime("07:34:33");
=======
        // Unit tests
        DataRetrieval bob = new DataRetrieval();
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git

<<<<<<< HEAD
		bob.updateDB(connection, myDate, myTime, 4, 3, 2, 1, 50);
=======
        java.sql.Date myDate = stringToSQLDate(args[0]);
        java.sql.Time myTime = stringToSQLTime(args[1]);
        double temp = Double.parseDouble(args[2]);
        double rain = Double.parseDouble(args[3]);
        double wind = Double.parseDouble(args[4]);
        double percent = Integer.parseInt(args[5]);
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git

<<<<<<< HEAD
		ResultSet rs = null;
=======
        bob.updateDB(bob.conn,myDate,myTime,temp,rain,wind,percent);
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git

<<<<<<< HEAD
		// TODO - Turn me into a method
		try
		{
			rs = getWeather(connection);
			while (rs.next())
			{
				System.out.println("Date: " + rs.getString(1) + " \nTime: " + rs.getString(2) + " \nTemp: "
						+ rs.getString(3) + " \nRain: " + rs.getFloat(4) + " \nWind: " + rs.getString(5)
						+ " \nHumidity: " + rs.getString(6));
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}

		long endTime = System.currentTimeMillis();
		float elapse = ((endTime - startTime) / 1000);
		System.out.println("That took like " + elapse + " seconds");

	}
}
=======
        bob.closeConnection(bob.conn, null);

    }
}
>>>>>>> branch 'master' of https://github.com/KinneyTrevor/Semester_Project.git
