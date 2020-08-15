package ch53;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
	public static void main(String[] args) {
		String url="jdbc:sqlserver://211.208.171.205:1433;database=java;";
		String id="sa";
		String password="12345";
		//DB접속
		Connection conn=null;
		//SQL명령어 실행
		Statement stmt=null;
		//select 명령어의 결과셋을 조회
		ResultSet rs=null;
		try {//DB연동 작업은 반드시 예외처리
			//mssql에 접속하기 위한 드라이버 로딩
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("드라이버 로딩 성공");
			//db에 접속
			conn=DriverManager.getConnection(url, id, password);
			//sql명령어 작성
			String sql="select * from dbo.tblBooks";
			//sql실행을 위한 객체 생성
			stmt=conn.createStatement();
			//sql을 실행한 후 실행결과(결과셋)를 읽을 객체 생성
			rs=stmt.executeQuery(sql);
			System.out.println("도서코드\t도서이름");
			while(rs.next()) { //다음 레코드가 있으면
				int book_id=rs.getInt("book_id");
				String title=rs.getString("title");
				System.out.println(book_id+"\t"+title);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//DB연동 관련 리소스 정리 
			//오픈한 역순으로 ResultSet => Statement => Connection 
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
