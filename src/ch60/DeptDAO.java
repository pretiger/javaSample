package ch60;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch53.DB;

public class DeptDAO {
	//부서목록을 리턴하는 코드
	public ArrayList<DeptDTO> listDept() {
		ArrayList<DeptDTO> items=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.getConn();
			String sql="select * from tblDept order by dname";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int deptno=rs.getInt("deptno");
				String dname=rs.getString("dname");
				String loc=rs.getString("loc");
				DeptDTO dto=new DeptDTO(deptno, dname, loc);
				items.add(dto);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}
}
