package aboutDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ScoreRemakeDAO extends DefaultTableModel {
	
	public ScoreRemakeDAO() {
	}
	
	public ScoreRemakeDAO(Vector data, Vector col) {
		super(data, col);
	}
	
	public Vector listScore(){
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		conn=DB.getMssql();
		String sql="select * from tblScore ";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector col=new Vector();
				col.add(rs.getString("student_no"));
				col.add(rs.getString("name"));
				col.add(rs.getInt("kor"));
				col.add(rs.getInt("eng"));
				col.add(rs.getInt("mat"));
				col.add(rs.getInt("tot"));
				col.add(rs.getDouble("avg"));
				items.add(col);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
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
	
	public int insertScore(ScoreDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into tblScore (student_no,name,kor,eng,mat,tot,avg) values (?,?,?,?,?,?,?)";
		conn=DB.getMssql();
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
	
	public int deleteScore(String student_no) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		conn=DB.getMssql();
		String sql=" delete from tblScore where student_no=? ";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, student_no);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt !=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int updateScore(ScoreDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		conn=DB.getMssql();
		String sql="update tblScore set  name=?, kor=?, eng=?, mat=?, tot=?, avg=? where student_no=?";
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
	
	@Override
	public boolean isCellEditable(int row, int column) {
		if(column <1 || column > 4) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Class getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}
}
