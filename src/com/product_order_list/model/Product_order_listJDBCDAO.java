package com.product_order_list.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Product_order_listJDBCDAO implements Product_order_listDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
	private static final String INSERT_STMT = 
		"INSERT INTO product_order_list (prod_ord_no,"
		+ " prod_no,"
		+ " unit_price,"
		+ " prod_quantity,"
		+ " deli_status,"
		+ " deli_time) "
		+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT prod_ord_no,"
		+ " prod_no,"
		+ " unit_price,"
		+ " prod_quantity,"
		+ " deli_status,"
		+ " deli_time FROM product_order_list order by prod_ord_no";
	private static final String GET_ONE_STMT = 
		"SELECT prod_ord_no,"
		+ " prod_no,"
		+ " unit_price,"
		+ " prod_quantity,"
		+ " deli_status,"
		+ " deli_time FROM product_order_list where (prod_ord_no = ?) and (prod_no = ?)";
	private static final String DELETE = 
		"DELETE FROM product_order_list where prod_ord_no = ?";
	private static final String UPDATE = 
		"UPDATE product_order_list set unit_price=?,"
		+ " prod_quantity=?,"
		+ " deli_status=?,"
		+ " deli_time=? where (prod_ord_no = ?) and (prod_no = ?)";
	
	private static final String GET_ONE_STMT_BY_ONE_PK = 
		"SELECT prod_ord_no,"
		+ " prod_no,"
		+ " unit_price,"
		+ " prod_quantity,"
		+ " deli_status,"
		+ " deli_time FROM product_order_list where prod_ord_no = ?";
	
	private static final String GET_STMT_BY_ONE_PK = 
		"SELECT prod_ord_no,"
		+ " prod_no,"
		+ " unit_price,"
		+ " prod_quantity,"
		+ " deli_status,"
		+ " deli_time FROM product_order_list where prod_ord_no = ?";
	
	@Override
	public void insert(Product_order_listVO prod_ord_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, prod_ord_listVO.getProd_ord_no());
			pstmt.setString(2, prod_ord_listVO.getProd_no());
			pstmt.setInt(3, prod_ord_listVO.getUnit_price());
			pstmt.setInt(4, prod_ord_listVO.getProd_quantity());
			pstmt.setString(5, prod_ord_listVO.getDeli_status());
			pstmt.setDate(6, prod_ord_listVO.getDeli_time());

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
	public void update(Product_order_listVO prod_ord_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, prod_ord_listVO.getUnit_price());
			pstmt.setInt(2, prod_ord_listVO.getProd_quantity());
			pstmt.setString(3, prod_ord_listVO.getDeli_status());
			pstmt.setDate(4, prod_ord_listVO.getDeli_time());
			pstmt.setString(5, prod_ord_listVO.getProd_ord_no());
			pstmt.setString(6, prod_ord_listVO.getProd_no());

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
	public void delete(String prod_ord_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prod_ord_no);

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
	public Product_order_listVO findByPrimaryKey(String prod_ord_no, String prod_no) {

		Product_order_listVO prod_ord_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prod_ord_no);
			pstmt.setString(2, prod_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prod_ord_listVO 也稱為 Domain objects
				prod_ord_listVO = new Product_order_listVO();
				prod_ord_listVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ord_listVO.setProd_no(rs.getString("prod_no"));
				prod_ord_listVO.setUnit_price(rs.getInt("unit_price"));
				prod_ord_listVO.setProd_quantity(rs.getInt("prod_quantity"));
				prod_ord_listVO.setDeli_status(rs.getString("deli_status"));
				prod_ord_listVO.setDeli_time(rs.getDate("deli_time"));
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
		return prod_ord_listVO;
	}
	
	@Override
	public Product_order_listVO findByPrimaryKey(String prod_ord_no) {

		Product_order_listVO prod_ord_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_ONE_PK);

			pstmt.setString(1, prod_ord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prod_ord_listVO 也稱為 Domain objects
				prod_ord_listVO = new Product_order_listVO();
				prod_ord_listVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ord_listVO.setProd_no(rs.getString("prod_no"));
				prod_ord_listVO.setUnit_price(rs.getInt("unit_price"));
				prod_ord_listVO.setProd_quantity(rs.getInt("prod_quantity"));
				prod_ord_listVO.setDeli_status(rs.getString("deli_status"));
				prod_ord_listVO.setDeli_time(rs.getDate("deli_time"));
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
		return prod_ord_listVO;
	}

	@Override
	public List<Product_order_listVO> findByPK(String prod_ord_no) {
		List<Product_order_listVO> list2 = new ArrayList<Product_order_listVO>();
		Product_order_listVO prod_ord_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STMT_BY_ONE_PK);
			
			pstmt.setString(1, prod_ord_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prod_ord_listVO 也稱為 Domain objects
				prod_ord_listVO = new Product_order_listVO();
				prod_ord_listVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ord_listVO.setProd_no(rs.getString("prod_no"));
				prod_ord_listVO.setUnit_price(rs.getInt("unit_price"));
				prod_ord_listVO.setProd_quantity(rs.getInt("prod_quantity"));
				prod_ord_listVO.setDeli_status(rs.getString("deli_status"));
				prod_ord_listVO.setDeli_time(rs.getDate("deli_time"));
				list2.add(prod_ord_listVO); // Store the row in the list
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
		return list2;
	}
	
	@Override
	public List<Product_order_listVO> getAll() {
		List<Product_order_listVO> list = new ArrayList<Product_order_listVO>();
		Product_order_listVO prod_ord_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prod_ord_listVO 也稱為 Domain objects
				prod_ord_listVO = new Product_order_listVO();
				prod_ord_listVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ord_listVO.setProd_no(rs.getString("prod_no"));
				prod_ord_listVO.setUnit_price(rs.getInt("unit_price"));
				prod_ord_listVO.setProd_quantity(rs.getInt("prod_quantity"));
				prod_ord_listVO.setDeli_status(rs.getString("deli_status"));
				prod_ord_listVO.setDeli_time(rs.getDate("deli_time"));
				list.add(prod_ord_listVO); // Store the row in the list
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

		Product_order_listJDBCDAO dao = new Product_order_listJDBCDAO();
		
		//新增
		/*Product_order_listVO prod_ord_listVO1 = new Product_order_listVO();
		prod_ord_listVO1.setProd_ord_no("20150228-00000002");
		prod_ord_listVO1.setProd_no("1");
		prod_ord_listVO1.setUnit_price(50);
		prod_ord_listVO1.setProd_quantity(100);
		prod_ord_listVO1.setDeli_status("0");
		prod_ord_listVO1.setDeli_time(java.sql.Date.valueOf("2016-12-06"));
		dao.insert(prod_ord_listVO1);*/
		
		//修改
		/*Product_order_listVO prod_ord_listVO2 = new Product_order_listVO();
		prod_ord_listVO2.setProd_ord_no("20160401-00000004");
		prod_ord_listVO2.setProd_no("1");
		prod_ord_listVO2.setUnit_price(30);
		prod_ord_listVO2.setProd_quantity(150);
		prod_ord_listVO2.setDeli_status("1");
		prod_ord_listVO2.setDeli_time(java.sql.Date.valueOf("2016-12-06"));
		dao.update(prod_ord_listVO2);*/
		
		//刪除
		/*dao.delete("20150228-00000002");*/
		
		// 查詢 - 單一	(雙主鍵)
		/*Product_order_listVO prod_ord_listVO3 = dao.findByPrimaryKey("20160915-00000003", "3");
		System.out.print(prod_ord_listVO3.getProd_ord_no() + ",	");
		System.out.print(prod_ord_listVO3.getProd_no() + ",	");
		System.out.print(prod_ord_listVO3.getUnit_price() + ",	");
		System.out.print(prod_ord_listVO3.getProd_quantity() + ",	");
		System.out.print(prod_ord_listVO3.getDeli_status() + ",	");
		System.out.print(prod_ord_listVO3.getDeli_time());
		System.out.println();*/
		
		// 查詢 - 單一	(單一主鍵)
		/*Product_order_listVO prod_ord_listVO4 = dao.findByPrimaryKey("20160915-00000003");
		System.out.print(prod_ord_listVO4.getProd_ord_no() + ",	");
		System.out.print(prod_ord_listVO4.getProd_no() + ",	");
		System.out.print(prod_ord_listVO4.getUnit_price() + ",	");
		System.out.print(prod_ord_listVO4.getProd_quantity() + ",	");
		System.out.print(prod_ord_listVO4.getDeli_status() + ",	");
		System.out.print(prod_ord_listVO4.getDeli_time());
		System.out.println();*/
		
		// 查詢 
		List<Product_order_listVO> list2 = dao.findByPK("20160630-00000001");
		for (Product_order_listVO partProduct_order_list : list2) {
			System.out.print(partProduct_order_list.getProd_ord_no() + ",	");
			System.out.print(partProduct_order_list.getProd_no() + ",	");
			System.out.print(partProduct_order_list.getUnit_price() + ",	");
			System.out.print(partProduct_order_list.getProd_quantity() + ",	");
			System.out.print(partProduct_order_list.getDeli_status() + ",	");
			System.out.print(partProduct_order_list.getDeli_time());		
			System.out.println();
		}

		// 查詢 - 全部
		/*List<Product_order_listVO> list = dao.getAll();
		for (Product_order_listVO aProduct_order_list : list) {
			System.out.print(aProduct_order_list.getProd_ord_no() + ",	");
			System.out.print(aProduct_order_list.getProd_no() + ",	");
			System.out.print(aProduct_order_list.getUnit_price() + ",	");
			System.out.print(aProduct_order_list.getProd_quantity() + ",	");
			System.out.print(aProduct_order_list.getDeli_status() + ",	");
			System.out.print(aProduct_order_list.getDeli_time());
			System.out.println();
		}*/
	}

}
