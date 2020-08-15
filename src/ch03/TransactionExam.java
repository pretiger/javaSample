package ch03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionExam {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		boolean success=false;
		try {
			conn=ch02.DB.getConn();
			conn.setAutoCommit(false);
			String sql="insert tblEmp (empno, ename, sal) values (?,?,?)";
			pstmt=conn.prepareStatement(sql);
			long start=System.currentTimeMillis();
			for(int i=1; i<100; i++) {
				pstmt.setInt(1, i*100);
				pstmt.setString(2, "kim");
				pstmt.setInt(3, 5000);
				pstmt.addBatch();
//				pstmt.executeUpdate();
			}
			pstmt.executeBatch();
			success=true;
			long end=System.currentTimeMillis();
			System.out.println("작업 수행 시간 : "+(end-start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(success) {
					System.out.println("커밋 되었습니다.");
					conn.commit();
				}else {
					System.out.println("롤백 되었습니다.");
					conn.rollback();
				}
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
