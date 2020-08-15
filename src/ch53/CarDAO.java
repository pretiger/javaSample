package ch53;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarDAO {
	public Connection dbConn() {
		Connection conn=null;
		try {
			FileInputStream fis=new FileInputStream("e:\\db.prop");
			Properties prop=new Properties();
			prop.load(fis);
			String url=prop.getProperty("url");
			String id=prop.getProperty("id");
			String password=prop.getProperty("password");
			conn=DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<CarDTO> listCar(){
		List<CarDTO> item=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dbConn();
			String sql="select * from tblCar";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String license_number=rs.getString("license_number");
				String company=rs.getString("company");
				String type=rs.getString("type");
				int year=rs.getInt("year");
				int efficiency=rs.getInt("efficiency");
				CarDTO dto=new CarDTO(license_number, company, type, year, efficiency);
				item.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	public void insertCar(CarDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dbConn();
			String sql=
		"insert into tblCar (license_number, company, type, year, efficiency) "
					+"values(?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getLicense_number());
			pstmt.setString(2, dto.getCompany());
			pstmt.setString(3, dto.getType());
			pstmt.setInt(4, dto.getYear());
			pstmt.setInt(5, dto.getEfficiency());
			pstmt.executeUpdate();
		} catch (Exception e) {
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
	public int deleteCar(String license_number) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dbConn();
			String sql="delete from tblCar where license_number=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, license_number);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
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
