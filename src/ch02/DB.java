package ch02;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {
	public static Connection getConn() {
		Connection conn=null;
		try {
			FileInputStream fis=new FileInputStream("e:\\work\\db.prop");
			Properties prop=new Properties();
			prop.load(fis);
			String url=prop.getProperty("url");
			String id=prop.getProperty("id");
			String password=prop.getProperty("password");
			String driver=prop.getProperty("driver");
			Class.forName(driver);
			conn=DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return conn;
	}
}
