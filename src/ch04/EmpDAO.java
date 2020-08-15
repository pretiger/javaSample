package ch04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

class EmpDAO {
	public EmpDTO listEmp1(int empno) {
		EmpDTO dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=ch02.DB.getConn();
			String sql="select empno, ename, job, hiredate, sal, dname, img_path "
					+" from tblEmp2 e, tblDept d "
					+" where e.deptno=d.deptno "
					+" and empno=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setHiredate(rs.getDate("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setDname(rs.getString("dname"));
				dto.setImg_path(rs.getString("img_path"));
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
		
		return dto;
	}
	
	public ArrayList<String> listEmp2() {
		ArrayList<String> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=ch02.DB.getConn();
			String sql="select CONCAT(empno,' ', ename) name from tblEmp2";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("name"));
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
	
	public Vector listVectorEmp(int empno) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=ch02.DB.getConn();
			String sql="select empno, ename, job, hiredate, sal, dname, img_path "
					+" from tblEmp2 e, tblDept d "
					+" where e.deptno=d.deptno "
					+" and empno=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				Vector data=new Vector();
				data.add(rs.getInt("empno"));
				data.add(rs.getString("ename"));
				data.add(rs.getString("job"));
				data.add(rs.getDate("hiredate"));
				data.add(rs.getInt("sal"));
				data.add(rs.getString("dname"));
				data.add(rs.getString("img_path"));
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
