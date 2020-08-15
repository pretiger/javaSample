package ch03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class EmpDAO {
	public ArrayList<EmpDTO> listEmp(int deptno) {
		ArrayList<EmpDTO> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=ch02.DB.getConn();
			String sql="select empno, ename, job, hiredate, sal, dname "
					+" from tblEmp2 e, tblDept d "
					+" where e.deptno=d.deptno "
					+" and e.deptno=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				EmpDTO dto=new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setHiredate(rs.getDate("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setDname(rs.getString("dname"));
				list.add(dto);
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
		
		return list;
	}
	
	public Vector listAllEmp(String dname) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=ch02.DB.getConn();
			String sql="select empno, ename, job, hiredate, sal, dname "
					+" from tblEmp2 e, tblDept d "
					+" where e.deptno=d.deptno "
					+" and dname like ? order by dname";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector data=new Vector();
				data.add(rs.getInt("empno"));
				data.add(rs.getString("ename"));
				data.add(rs.getString("job"));
				data.add(rs.getDate("hiredate"));
				data.add(rs.getInt("sal"));
				data.add(rs.getString("dname"));
				items.add(data);
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
	
}
