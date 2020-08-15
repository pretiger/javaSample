package ch01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	public List<EmpDTO> listEmp() {
		List<EmpDTO> items=new ArrayList<>();
		Connection conn=ch53.DB.getConn();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from tblEMP";
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
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
		return items;
	}
	
	public void insertEmp(EmpDTO dto) {
		Connection conn=ch53.DB.getConn();
		PreparedStatement pstmt=null;
		String sql="insert into tblEmp values(?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			pstmt.setString(2, dto.getEname());
			pstmt.setDate(3, dto.getHiredate());
			pstmt.setInt(4, dto.getSal());
			pstmt.executeUpdate();
			System.out.println("추가 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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
	
	public int deleteEmp(int empno) {
		int result=0;
		Connection conn=ch53.DB.getConn();
		PreparedStatement pstmt=null;
		String sql="delete from tblEmp where empno=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			result=pstmt.executeUpdate();
			System.out.println("삭제 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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
		return result;
	}
}
