package com.product_order.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Product_orderJDBCDAO implements Product_orderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
	private static final String INSERT_STMT = 
		"INSERT INTO product_order (prod_ord_no,"
		+ " mem_no,"
		+ " prod_ord_time,"
		+ " cred_card_no,"
		+ " valid_date,"
		+ " valid_no,"
		+ " cred_card_type,"
		+ " total_money,"
		+ " ship_name,"
		+ " post_code,"
		+ " mem_adrs,"
		+ " cell_phone,"
		+ " tel_phone) "
		+ "VALUES ('20161208'||'-'||LPAD(prod_ord_seq.NEXTVAL,8,0), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		//+ "VALUES (TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(prod_ord_seq.NEXTVAL,8,0), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT prod_ord_no,"
		+ " mem_no,"
		+ " prod_ord_time,"
		+ " cred_card_no,"
		+ " valid_date,"
		+ " valid_no,"
		+ " cred_card_type,"
		+ " total_money,"
		+ " ship_name,"
		+ " post_code,"
		+ " mem_adrs,"
		+ " cell_phone,"
		+ " tel_phone FROM product_order order by prod_ord_no";
	private static final String GET_ONE_STMT = 
		"SELECT prod_ord_no,"
		+ " mem_no,"
		+ " prod_ord_time,"
		+ " cred_card_no,"
		+ " valid_date,"
		+ " valid_no,"
		+ " cred_card_type,"
		+ " total_money,"
		+ " ship_name,"
		+ " post_code,"
		+ " mem_adrs,"
		+ " cell_phone,"
		+ " tel_phone FROM product_order where prod_ord_no = ?";
	private static final String DELETE = 
		"DELETE FROM product_order where prod_ord_no = ?";
	private static final String UPDATE = 
		"UPDATE product_order set mem_no=?,"
		+ " prod_ord_time=?,"
		+ " cred_card_no=?,"
		+ " valid_date=?,"
		+ " valid_no=?,"
		+ " cred_card_type=?,"
		+ " total_money=?,"
		+ " ship_name=?,"
		+ " post_code=?,"
		+ " mem_adrs=?,"
		+ " cell_phone=?,"
		+ " tel_phone=? where prod_ord_no = ?";
	
	@Override
	public void insert(Product_orderVO prod_ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, prod_ordVO.getMem_no());
			pstmt.setDate(2, prod_ordVO.getProd_ord_time());
			pstmt.setString(3, prod_ordVO.getCred_card_no());
			pstmt.setDate(4, prod_ordVO.getValid_date());
			pstmt.setString(5, prod_ordVO.getValid_no());
			pstmt.setString(6, prod_ordVO.getCred_card_type());
			pstmt.setInt(7, prod_ordVO.getTotal_money());
			pstmt.setString(8, prod_ordVO.getShip_name());
			pstmt.setString(9, prod_ordVO.getPost_code());
			pstmt.setString(10, prod_ordVO.getMem_adrs());
			pstmt.setString(11, prod_ordVO.getCell_phone());
			pstmt.setString(12, prod_ordVO.getTel_phone());

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
	public void update(Product_orderVO prod_ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, prod_ordVO.getMem_no());
			pstmt.setDate(2, prod_ordVO.getProd_ord_time());
			pstmt.setString(3, prod_ordVO.getCred_card_no());
			pstmt.setDate(4, prod_ordVO.getValid_date());
			pstmt.setString(5, prod_ordVO.getValid_no());
			pstmt.setString(6, prod_ordVO.getCred_card_type());
			pstmt.setInt(7, prod_ordVO.getTotal_money());
			pstmt.setString(8, prod_ordVO.getShip_name());
			pstmt.setString(9, prod_ordVO.getPost_code());
			pstmt.setString(10, prod_ordVO.getMem_adrs());
			pstmt.setString(11, prod_ordVO.getCell_phone());
			pstmt.setString(12, prod_ordVO.getTel_phone());
			pstmt.setString(13, prod_ordVO.getProd_ord_no());


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
	public Product_orderVO findByPrimaryKey(String prod_ord_no) {

		Product_orderVO prod_ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prod_ord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prod_ordVO 也稱為 Domain objects
				prod_ordVO = new Product_orderVO();
				prod_ordVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ordVO.setMem_no(rs.getString("mem_no"));
				prod_ordVO.setProd_ord_time(rs.getDate("prod_ord_time"));
				prod_ordVO.setCred_card_no(rs.getString("cred_card_no"));
				prod_ordVO.setValid_date(rs.getDate("valid_date"));
				prod_ordVO.setValid_no(rs.getString("valid_no"));
				prod_ordVO.setCred_card_type(rs.getString("cred_card_type"));
				prod_ordVO.setTotal_money(rs.getInt("total_money"));
				prod_ordVO.setShip_name(rs.getString("ship_name"));
				prod_ordVO.setPost_code(rs.getString("post_code"));
				prod_ordVO.setMem_adrs(rs.getString("mem_adrs"));
				prod_ordVO.setCell_phone(rs.getString("cell_phone"));
				prod_ordVO.setTel_phone(rs.getString("tel_phone"));
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
		return prod_ordVO;
	}

	@Override
	public List<Product_orderVO> getAll() {
		List<Product_orderVO> list = new ArrayList<Product_orderVO>();
		Product_orderVO prod_ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// prod_ordVO 也稱為 Domain objects
				prod_ordVO = new Product_orderVO();
				prod_ordVO.setProd_ord_no(rs.getString("prod_ord_no"));
				prod_ordVO.setMem_no(rs.getString("mem_no"));
				prod_ordVO.setProd_ord_time(rs.getDate("prod_ord_time"));
				prod_ordVO.setCred_card_no(rs.getString("cred_card_no"));
				prod_ordVO.setValid_date(rs.getDate("valid_date"));
				prod_ordVO.setValid_no(rs.getString("valid_no"));
				prod_ordVO.setCred_card_type(rs.getString("cred_card_type"));
				prod_ordVO.setTotal_money(rs.getInt("total_money"));
				prod_ordVO.setShip_name(rs.getString("ship_name"));
				prod_ordVO.setPost_code(rs.getString("post_code"));
				prod_ordVO.setMem_adrs(rs.getString("mem_adrs"));
				prod_ordVO.setCell_phone(rs.getString("cell_phone"));
				prod_ordVO.setTel_phone(rs.getString("tel_phone"));
				list.add(prod_ordVO); // Store the row in the list
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

		Product_orderJDBCDAO dao = new Product_orderJDBCDAO();
		
		//新增
		/*Product_orderVO prod_ordVO1 = new Product_orderVO();
		prod_ordVO1.setMem_no("M00000002");
		prod_ordVO1.setProd_ord_time(java.sql.Date.valueOf("2016-09-19"));
		prod_ordVO1.setCred_card_no("1A2B3C4D5E6F7G8H");
		prod_ordVO1.setValid_date(java.sql.Date.valueOf("2022-12-31"));
		prod_ordVO1.setValid_no("147");
		prod_ordVO1.setCred_card_type("2");
		prod_ordVO1.setTotal_money(8700);
		prod_ordVO1.setShip_name("KITTEN");
		prod_ordVO1.setPost_code("37066");
		prod_ordVO1.setMem_adrs("YOUR HOUSE");
		prod_ordVO1.setCell_phone("0963258741");
		prod_ordVO1.setTel_phone("07-8912345");
		dao.insert(prod_ordVO1);*/
		
		//修改
		/*Product_orderVO prod_ordVO2 = new Product_orderVO();
		prod_ordVO2.setProd_ord_no("20161208-00000006");
		prod_ordVO2.setMem_no("M00000002");
		prod_ordVO2.setProd_ord_time(java.sql.Date.valueOf("2016-09-19"));
		prod_ordVO2.setCred_card_no("1A2B3C4D5E6F7G8H");
		prod_ordVO2.setValid_date(java.sql.Date.valueOf("2022-12-31"));
		prod_ordVO2.setValid_no("147");
		prod_ordVO2.setCred_card_type("2");
		prod_ordVO2.setTotal_money(78000);
		prod_ordVO2.setShip_name("KITTEN");
		prod_ordVO2.setPost_code("37066");
		prod_ordVO2.setMem_adrs("YOUR HOUSE");
		prod_ordVO2.setCell_phone("0963258741");
		prod_ordVO2.setTel_phone("07-8912345");
		dao.update(prod_ordVO2);*/
		
		//刪除
		/*dao.delete("20161208-00000006");*/
		
		// 查詢 - 單一
		/*Product_orderVO prod_ordVO3 = dao.findByPrimaryKey("20161010-00000003");
		System.out.print(prod_ordVO3.getProd_ord_no() + ",	");
		System.out.print(prod_ordVO3.getMem_no() + ",	");
		System.out.print(prod_ordVO3.getProd_ord_time() + ",	");
		System.out.print(prod_ordVO3.getCred_card_no() + ",	");
		System.out.print(prod_ordVO3.getValid_date() + ",	");
		System.out.print(prod_ordVO3.getValid_no() + ",	");
		System.out.print(prod_ordVO3.getCred_card_type() + ",	");
		System.out.print(prod_ordVO3.getTotal_money() + ",	");
		System.out.print(prod_ordVO3.getShip_name() + ",	");
		System.out.print(prod_ordVO3.getPost_code() + ",	");
		System.out.print(prod_ordVO3.getMem_adrs() + ",	");
		System.out.print(prod_ordVO3.getCell_phone() + ",	");
		System.out.print(prod_ordVO3.getTel_phone());
		System.out.println();*/

		// 查詢 - 全部
		List<Product_orderVO> list = dao.getAll();
		for (Product_orderVO aProd : list) {
			System.out.print(aProd.getProd_ord_no() + ",	");
			System.out.print(aProd.getMem_no() + ",	");
			System.out.print(aProd.getProd_ord_time() + ",	");
			System.out.print(aProd.getCred_card_no() + ",	");
			System.out.print(aProd.getValid_date() + ",	");
			System.out.print(aProd.getValid_no() + ",	");
			System.out.print(aProd.getCred_card_type() + ",	");
			System.out.print(aProd.getTotal_money() + ",	");
			System.out.print(aProd.getShip_name() + ",	");
			System.out.print(aProd.getPost_code() + ",	");
			System.out.print(aProd.getMem_adrs() + ",	");
			System.out.print(aProd.getCell_phone() + ",	");
			System.out.print(aProd.getTel_phone());
			System.out.println();
		}
	}
	
}
