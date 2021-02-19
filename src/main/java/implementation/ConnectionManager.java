package implementation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionManager {
	
	private static Connection conn;
	private static String connectionURLString;
  	private static Properties connectionProps;
	
	static {
		setUser();
		
		System.out.println(connectionURLString);

	}
	//return stored connection (if any) or establish new 
	public static Connection getConnection() {
		if(conn == null) {
			conn = establishConnection();
		}
		return conn;
	}

	public static void releaseConnection() {//release connection (if any)
		if(conn != null)
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				System.out.println("WARNING! Could not close connection to database! "
								 + "Potential resource leaks are possible");
				e.printStackTrace();
			}
	}
	//connect to DB with default user data (which is stored in project resources local.properties) 
	public static void setUser() {
		connectionProps = loadProperties();
		
		setUser(connectionProps.getProperty("user"),connectionProps.getProperty("password"));
	}
    //connect to DB with specified user data
	public static void setUser(String username, String password) {
		connectionProps.setProperty("user", username);
		connectionProps.setProperty("password", password);
		
		connectionURLString = getURLString(connectionProps);
		System.out.println("Setting user to " + connectionProps.getProperty("user"));
		releaseConnection();
		getConnection();
	}
	
	//connecting procedure
	private static Connection establishConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionURLString);
			System.out.println("Successfully connected to database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	} 
	//retrieve properties from file (local.properties) in project resources 
	private static Properties loadProperties (){
		Properties props = new Properties();
		try(InputStream instr = Thread.currentThread().getContextClassLoader().getResourceAsStream("local.properties")){
			props.load(instr);
		}catch(IOException e) {
			System.out.println("An error occured while loading properties");
			e.printStackTrace();
		}
		return props;
	}
	
	private static String getURLString(Properties props) {
		String resultURLString = "jdbc:mysql://"
				+ connectionProps.getProperty("server_adress") 
				+ ":" + connectionProps.getProperty("port_number") 
				+ "/" + connectionProps.getProperty("DB_name") 
				+ "?"
				+ "user=" + connectionProps.getProperty("user")
				+ "&password=" + connectionProps.getProperty("password")
				+ "&serverTimezone=UTC"; 
		return resultURLString;
	}
	
}

