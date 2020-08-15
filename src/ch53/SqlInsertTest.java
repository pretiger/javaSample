package ch53;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class SqlInsertTest {
	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		try {
			//db접속에 필요한 정보들을 파일에 저장한 후 불러오는 방식
			FileInputStream fis=new FileInputStream("e:\\db.prop");
			Properties prop=new Properties();
			
			//파일에 저장된 내용들을 불러옴
			prop.load(fis);
			String driver=prop.getProperty("driver");
			String url=prop.getProperty("url");
			String id=prop.getProperty("id");
			String password=prop.getProperty("password");
			System.out.println("driver:"+driver);
			System.out.println("url:"+url);
			System.out.println("id:"+id);
			System.out.println("password:"+password);
			
			Class.forName(driver);
			conn=DriverManager.getConnection(url, id, password);
			
			String title="C언어";
			String publisher="금성출판사";
			int year=2017;
			int price=20030;
			
			String sql="insert into dbo.tblBooks (title, publisher, year, price)"
					+"values ('"+title+"', '"+publisher+"', "+year+", "+price+")";
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
			} catch(Exception e){
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
