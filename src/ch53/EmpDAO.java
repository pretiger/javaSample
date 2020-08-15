package ch53;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	public int deleteEmp(int empno) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.getConn();
			String sql="delete from tblEmp where empno=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return result;
	}
	
	public void insertEmp(EmpDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.getConn();
			String sql="insert into tblEmp values (?, ?, ?, ?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			pstmt.setString(2, dto.getEname());
			pstmt.setDate(3, dto.getHiredate());
			pstmt.setInt(4, dto.getSal());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<EmpDTO> listEmp(){
		List<EmpDTO> items=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.getConn();
			String sql="select * from tblEmp";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int empno=rs.getInt("empno");
				String ename=rs.getString("ename");
				Date hiredate=rs.getDate("hiredate");
				int sal=rs.getInt("sal");
				
				EmpDTO dto=new EmpDTO(empno, ename, hiredate, sal);
				items.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=rs) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt!=pstmt) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn!=conn) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}
}