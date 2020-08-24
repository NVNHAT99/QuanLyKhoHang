package ultils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DBUtils {
	private final String DB_URL = "jdbc:mysql://localhost:3306/qlkhohang";

	public Connection Get_connection() {
		Properties props = new Properties();
		props.put("user", "root");
		props.put("password", "");
		props.put("useUnicode", "true");
		props.put("useServerPrepStmts", "false"); // use client-side prepared statement
		props.put("characterEncoding", "UTF-8");
		
		// connection
		Connection cnn ;
		try {
			cnn = DriverManager.getConnection(DB_URL, props);
			if (cnn == null) {
                System.out.println("Connection cannot be established");
            }
			return cnn;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
