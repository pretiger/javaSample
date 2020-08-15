package ch60;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import ch53.DB;

public class TableInfo {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSetMetaData meta=null;
		try {
			conn=DB.getConn();
			String sql="select * from tblEmp2";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			meta=rs.getMetaData();
			int cols=meta.getColumnCount();
			System.out.println("필드명\t자료형\tNull허용여부");
			for(int i=1; i<=cols; i++) {
				String colName=meta.getColumnName(i);
				String colType=meta.getColumnTypeName(i);
				int isNull=meta.isNullable(i);
				System.out.println(colName+"\t"+colType+"\t"+isNull);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null)	pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
