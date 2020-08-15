package ch01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {
	public static void main(String[] args) {
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
			System.out.println("드라이버 연결성공");
			conn=DriverManager.getConnection(url, id, password);
			System.out.println("MSSQL연결 성공");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
