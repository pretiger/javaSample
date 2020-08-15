package ch02;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB2 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			FileInputStream fis=new FileInputStream("e:\\work\\mariadb.prop");
			Properties prop=new Properties();
			prop.load(fis);
			String url=prop.getProperty("url");
			String id=prop.getProperty("id");
			String password=prop.getProperty("password");
			String driver=prop.getProperty("driver");
			Class.forName(driver);
			conn=DriverManager.getConnection(url, id, password);
			System.out.println("드라이버 로딩 성공");
		} catch (Exception e) {
			System.out.println("DB 접속 실패");
			e.getStackTrace();
		}
		
	}
}
