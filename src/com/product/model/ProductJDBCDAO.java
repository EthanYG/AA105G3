package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductJDBCDAO implements ProductDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
	private static final String INSERT_STMT = 
		"INSERT INTO product (prod_no,"
		+ " prod_name,"
		+ " prod_type,"
		+ " sales_volume,"
		+ " stor_capacity,"
		+ " unit_price,"
		+ " prod_description,"
		+ " prod_status,"
		+ " disc_status,"
		+ " sell_status,"
		+ " shelf_date,"
		+ " remove_date,"
		+ " disc_price,"
		+ " disc_start_date,"
		+ " disc_end_date) "
		+ "VALUES (prod_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT prod_no,"
		+ " prod_name,"
		+ " prod_type,"
		+ " sales_volume,"
		+ " stor_capacity,"
		+ " unit_price,"
		+ " prod_description,"
		+ " prod_status,"
		+ " disc_status,"
		+ " sell_status,"
		+ " shelf_date,"
		+ " remove_date,"
		+ " disc_price,"
		+ " disc_start_date,"
		+ " disc_end_date FROM product order by prod_no";
	private static final String GET_ONE_STMT = 
		"SELECT prod_no,"
		+ " prod_name,"
		+ " prod_type,"
		+ " sales_volume,"
		+ " stor_capacity,"
		+ " unit_price,"
		+ " prod_description,"
		+ " prod_status,"
		+ " disc_status,"
		+ " sell_status,"
		+ " shelf_date,"
		+ " remove_date,"
		+ " disc_price,"
		+ " disc_start_date,"
		+ " disc_end_date FROM product where prod_no = ?";
	private static final String DELETE = 
		"DELETE FROM product where prod_no = ?";
	private static final String UPDATE = 
		"UPDATE product set prod_name=?,"
		+ " prod_type=?,"
		+ " sales_volume=?,"
		+ " stor_capacity=?,"
		+ " unit_price=?,"
		+ " prod_description=?,"
		+ " prod_status=?,"
		+ " disc_status=?,"
		+ " sell_status=?,"
		+ " shelf_date=?,"
		+ " remove_date=?,"
		+ " disc_price=?,"
		+ " disc_start_date=?,"
		+ " disc_end_date=? where prod_no = ?";
	
	@Override
	public void insert(ProductVO prodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, prodVO.getProd_name());
			pstmt.setString(2, prodVO.getProd_type());
			pstmt.setInt(3, prodVO.getSales_volume());
			pstmt.setInt(4, prodVO.getStor_capacity());
			pstmt.setInt(5, prodVO.getUnit_price());
			pstmt.setString(6, prodVO.getProd_description());
			pstmt.setString(7, prodVO.getProd_status());
			pstmt.setString(8, prodVO.getDisc_status());
			pstmt.setString(9, prodVO.getSell_status());
			pstmt.setDate(10, prodVO.getShelf_date());
			pstmt.setDate(11, prodVO.getRemove_date());
			pstmt.setInt(12, prodVO.getDisc_price());
			pstmt.setDate(13, prodVO.getDisc_start_date());
			pstmt.setDate(14, prodVO.getDisc_end_date());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void update(ProductVO prodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, prodVO.getProd_name());
			pstmt.setString(2, prodVO.getProd_type());
			pstmt.setInt(3, prodVO.getSales_volume());
			pstmt.setInt(4, prodVO.getStor_capacity());
			pstmt.setInt(5, prodVO.getUnit_price());
			pstmt.setString(6, prodVO.getProd_description());
			pstmt.setString(7, prodVO.getProd_status());
			pstmt.setString(8, prodVO.getDisc_status());
			pstmt.setString(9, prodVO.getSell_status());
			pstmt.setDate(10, prodVO.getShelf_date());
			pstmt.setDate(11, prodVO.getRemove_date());
			pstmt.setInt(12, prodVO.getDisc_price());
			pstmt.setDate(13, prodVO.getDisc_start_date());
			pstmt.setDate(14, prodVO.getDisc_end_date());
			pstmt.setString(15, prodVO.getProd_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void delete(String prod_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prod_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ProductVO findByPrimaryKey(String prod_no) {

		ProductVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prodVO 也稱為 Domain objects
				prodVO = new ProductVO();
				prodVO.setProd_no(rs.getString("prod_no"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setProd_type(rs.getString("prod_type"));
				prodVO.setSales_volume(rs.getInt("sales_volume"));
				prodVO.setStor_capacity(rs.getInt("stor_capacity"));
				prodVO.setUnit_price(rs.getInt("unit_price"));
				prodVO.setProd_description(rs.getString("prod_description"));
				prodVO.setProd_status(rs.getString("prod_status"));
				prodVO.setDisc_status(rs.getString("disc_status"));
				prodVO.setSell_status(rs.getString("sell_status"));
				prodVO.setShelf_date(rs.getDate("shelf_date"));
				prodVO.setRemove_date(rs.getDate("remove_date"));
				prodVO.setDisc_price(rs.getInt("disc_price"));
				prodVO.setDisc_start_date(rs.getDate("disc_start_date"));
				prodVO.setDisc_end_date(rs.getDate("disc_end_date"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return prodVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prodVO 也稱為 Domain objects
				prodVO = new ProductVO();
				prodVO.setProd_no(rs.getString("prod_no"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setProd_type(rs.getString("prod_type"));
				prodVO.setSales_volume(rs.getInt("sales_volume"));
				prodVO.setStor_capacity(rs.getInt("stor_capacity"));
				prodVO.setUnit_price(rs.getInt("unit_price"));
				prodVO.setProd_description(rs.getString("prod_description"));
				prodVO.setProd_status(rs.getString("prod_status"));
				prodVO.setDisc_status(rs.getString("disc_status"));
				prodVO.setSell_status(rs.getString("sell_status"));
				prodVO.setShelf_date(rs.getDate("shelf_date"));
				prodVO.setRemove_date(rs.getDate("remove_date"));
				prodVO.setDisc_price(rs.getInt("disc_price"));
				prodVO.setDisc_start_date(rs.getDate("disc_start_date"));
				prodVO.setDisc_end_date(rs.getDate("disc_end_date"));
				list.add(prodVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {

		ProductJDBCDAO dao = new ProductJDBCDAO();
		
		//新增
		/*ProductVO prodVO1 = new ProductVO();
		prodVO1.setProd_name("PIZZA");
		prodVO1.setProd_type("SPACE BAG");
		prodVO1.setSales_volume(70);
		prodVO1.setStor_capacity(5);
		prodVO1.setUnit_price(600);
		prodVO1.setProd_description("THIS IS A PIZZA");
		prodVO1.setProd_status("1");
		prodVO1.setDisc_status("1");
		prodVO1.setSell_status("1");
		prodVO1.setShelf_date(java.sql.Date.valueOf("2015-01-01"));
		prodVO1.setRemove_date(java.sql.Date.valueOf("2018-12-31"));
		prodVO1.setDisc_price(500);
		prodVO1.setDisc_start_date(java.sql.Date.valueOf("2017-06-01"));
		prodVO1.setDisc_end_date(java.sql.Date.valueOf("2017-12-31"));
		dao.insert(prodVO1);*/
		
		//修改
		/*ProductVO prodVO2 = new ProductVO();
		prodVO2.setProd_no("6");
		prodVO2.setProd_name("HOTPIZZA");
		prodVO2.setProd_type("SPACE BAG");
		prodVO2.setSales_volume(100);
		prodVO2.setStor_capacity(35);
		prodVO2.setUnit_price(600);
		prodVO2.setProd_description("THIS IS A HOTPIZZA");
		prodVO2.setProd_status("1");
		prodVO2.setDisc_status("1");
		prodVO2.setSell_status("1");
		prodVO2.setShelf_date(java.sql.Date.valueOf("2015-01-01"));
		prodVO2.setRemove_date(java.sql.Date.valueOf("2018-12-31"));
		prodVO2.setDisc_price(400);
		prodVO2.setDisc_start_date(java.sql.Date.valueOf("2017-06-01"));
		prodVO2.setDisc_end_date(java.sql.Date.valueOf("2017-12-31"));
		dao.update(prodVO2);*/

		//刪除
		/*dao.delete("6");*/
		
		// 查詢 - 單一
		/*ProductVO prodVO3 = dao.findByPrimaryKey("3");
		System.out.print(prodVO3.getProd_no() + ",	");
		System.out.print(prodVO3.getProd_name() + ",	");
		System.out.print(prodVO3.getProd_type() + ",	");
		System.out.print(prodVO3.getSales_volume() + ",	");
		System.out.print(prodVO3.getStor_capacity() + ",	");
		System.out.print(prodVO3.getUnit_price() + ",	");
		System.out.print(prodVO3.getProd_description() + ",	");
		System.out.print(prodVO3.getProd_status() + ",	");
		System.out.print(prodVO3.getDisc_status() + ",	");
		System.out.print(prodVO3.getSell_status() + ",	");
		System.out.print(prodVO3.getShelf_date() + ",	");
		System.out.print(prodVO3.getRemove_date() + ",	");
		System.out.print(prodVO3.getDisc_price() + ",	");
		System.out.print(prodVO3.getDisc_start_date() + ",	");
		System.out.print(prodVO3.getDisc_end_date());
		System.out.println();*/

		// 查詢 - 全部
		List<ProductVO> list = dao.getAll();
		for (ProductVO aProd : list) {
			System.out.print(aProd.getProd_no() + ",	");
			System.out.print(aProd.getProd_name() + ",	");
			System.out.print(aProd.getProd_type() + ",	");
			System.out.print(aProd.getSales_volume() + ",	");
			System.out.print(aProd.getStor_capacity() + ",	");
			System.out.print(aProd.getUnit_price() + ",	");
			System.out.print(aProd.getProd_description() + ",	");
			System.out.print(aProd.getProd_status() + ",	");
			System.out.print(aProd.getDisc_status() + ",	");
			System.out.print(aProd.getSell_status() + ",	");
			System.out.print(aProd.getShelf_date() + ",	");
			System.out.print(aProd.getRemove_date() + ",	");
			System.out.print(aProd.getDisc_price() + ",	");
			System.out.print(aProd.getDisc_start_date() + ",	");
			System.out.print(aProd.getDisc_end_date());
			System.out.println();
		}
	}

}
