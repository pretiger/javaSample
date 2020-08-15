package ch60;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ch53.DB;


public class TransactionExam {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean success=false;
		try {
			conn=DB.getConn();
			conn.setAutoCommit(false);
//			String sql="insert into tblEmp2 (empno, ename, sal) values (?, ?, ?)";
			String sql="insert into emp (empno, ename) values (?, ?)";
			pstmt=conn.prepareStatement(sql);
			long start=System.currentTimeMillis();//시작 시간
			for(int i=1; i<=1501; i++) {
//				pstmt.setInt(1, i+7215);
//				pstmt.setString(2, "kim");
//				pstmt.setInt(3, 450);
				pstmt.setInt(1, i);
				pstmt.setString(2, i+"번 사원");
				pstmt.addBatch();//일괄처리작업 예약
			}
			//일괄처리 작업 시작
			pstmt.executeBatch();
			success=true;
			long end=System.currentTimeMillis();//종료시간
			System.out.println("작업수행시간:"+(end - start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(success) {
					System.out.println("커밋되었습니다.");
					conn.commit();//변경 사항 완료
				} else {
					System.out.println("롤백되었습니다.");
					conn.rollback();//변경 사항 취소
				}
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
