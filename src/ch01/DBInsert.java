package ch01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBInsert {
	public static void main(String[] args) {
		Connection conn=ch53.DB.getConn();
		PreparedStatement pstmt=null;
		String sql="Insert into tblEmp values (?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 16);
			pstmt.setString(2, "김기남");
			pstmt.setDate(3, Date.valueOf("2011-12-03"));
			pstmt.setInt(4, 4000000);
			pstmt.executeUpdate();
			System.out.println("작업이 종료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
