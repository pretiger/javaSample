package ch53;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {
	public static Connection getConn() {
		Connection conn=null;
		try {
			FileInputStream fis=new FileInputStream("e:\\db.prop");
			Properties prop=new Properties();
			
			prop.load(fis);
			String driver=prop.getProperty("driver");
			String url=prop.getProperty("url");
			String id=prop.getProperty("id");
			String password=prop.getProperty("password");
			Class.forName(driver);//생략가능
			
			conn=DriverManager.getConnection(url, id, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
