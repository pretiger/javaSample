package ch60;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import ch53.DB;

public class EmpDAO {
		
	public Vector listEmp(String dname){
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.getConn();
			String sql="select empno, ename, job, hiredate, sal, dname "
					+ " from tblEmp2 e, tblDept d "
					+ " where e.deptno = d.deptno and d.dname like ? ";
			pstmt=conn.prepareStatement(sql);
			if(dname.equals("전체부서")) {
				dname="%";
			}
			pstmt.setString(1, dname);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getInt("empno"));
				row.add(rs.getString("ename"));
				row.add(rs.getString("job"));
				row.add(rs.getDate("hiredate"));
				row.add(rs.getInt("sal"));
				row.add(rs.getString("dname"));
				items.add(row);
			}
			System.out.println(items);
		} catch (Exception e) {
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
	
	public ArrayList<EmpDTO> listEmp(int deptno){
		ArrayList<EmpDTO> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.getConn();
			String sql="select empno, ename, job, hiredate, sal, dname "
					+ " from tblEmp2 e, tblDept d "
					+ " where e.deptno=d.deptno and e.deptno=? ";
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
		} catch (Exception e) {
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
		return list;
	}
}
