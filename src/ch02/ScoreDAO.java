package ch02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ScoreDAO {
	int result;
	
	public Vector listScore() {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.getConn();
			String sql="select * from tblScore";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getString("student_no"));
				row.add(rs.getString("name"));
				row.add(rs.getInt("kor"));
				row.add(rs.getInt("eng"));
				row.add(rs.getInt("mat"));
				row.add(rs.getInt("tot"));
				row.add(rs.getDouble("avg"));
				items.add(row);
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
	
	public Vector searchScore(String name) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from tblScore where name like ?";
		conn=DB.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getString("student_no"));
				row.add(rs.getString("name"));
				row.add(rs.getInt("kor"));
				row.add(rs.getInt("eng"));
				row.add(rs.getInt("mat"));
				row.add(rs.getInt("tot"));
				row.add(rs.getDouble("avg"));
				items.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
	}
	
	public int insertScore(ScoreDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into tblScore values(?,?,?,?,?,?,?)";
		conn=DB.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getStudent_no());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMat());
			pstmt.setInt(6, dto.getTot());
			pstmt.setDouble(7, dto.getAvg());
			result=pstmt.executeUpdate();
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
	
	public int deleteScore(String student_no) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from tblScore where student_no=?";
		conn=DB.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, student_no);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int updateScore(ScoreDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update tblScore set name=?, kor=?, eng=?, mat=?, tot=?, avg=? "
				+ "where student_no=?";
		conn=DB.getConn();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getKor());
			pstmt.setInt(3, dto.getEng());
			pstmt.setInt(4, dto.getMat());
			pstmt.setInt(5, dto.getTot());
			pstmt.setDouble(6, dto.getAvg());
			pstmt.setString(7, dto.getStudent_no());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
