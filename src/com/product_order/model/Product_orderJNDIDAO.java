package com.product_order.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_order_list.model.Product_order_listVO;

public class Product_orderJNDIDAO implements Product_orderDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FoodTimeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
	
	private static final String getPart_For_Display_By_One_PK = 
		"SELECT prod_ord_no,"
		+ " prod_no,"
		+ " unit_price,"
		+ " prod_quantity,"
		+ " deli_status,"
		+ " deli_time FROM product_order_list where prod_ord_no = ?";
	
	@Override
	public void insert(Product_orderVO prod_ordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prod_ord_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	

}
