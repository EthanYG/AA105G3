package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductJNDIDAO implements ProductDAO_interface {
	
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
		+ " prod_picture,"
		+ " shelf_date,"
		+ " remove_date,"
		+ " disc_price,"
		+ " disc_start_date,"
		+ " disc_end_date) "
		+ "VALUES (prod_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		+ " prod_picture,"
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
		+ " prod_picture,"
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
		+ " prod_picture=?,"
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

			con = ds.getConnection();
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
			pstmt.setBytes(10, prodVO.getProd_picture());
			pstmt.setDate(11, prodVO.getShelf_date());
			pstmt.setDate(12, prodVO.getRemove_date());
			pstmt.setInt(13, prodVO.getDisc_price());
			pstmt.setDate(14, prodVO.getDisc_start_date());
			pstmt.setDate(15, prodVO.getDisc_end_date());

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
	public void update(ProductVO prodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
			pstmt.setBytes(10, prodVO.getProd_picture());
			pstmt.setDate(11, prodVO.getShelf_date());
			pstmt.setDate(12, prodVO.getRemove_date());
			pstmt.setInt(13, prodVO.getDisc_price());
			pstmt.setDate(14, prodVO.getDisc_start_date());
			pstmt.setDate(15, prodVO.getDisc_end_date());
			pstmt.setString(16, prodVO.getProd_no());

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
	public void delete(String prod_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prod_no);

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
	public ProductVO findByPrimaryKey(String prod_no) {

		ProductVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
				prodVO.setProd_picture(rs.getBytes("prod_picture"));
				prodVO.setShelf_date(rs.getDate("shelf_date"));
				prodVO.setRemove_date(rs.getDate("remove_date"));
				prodVO.setDisc_price(rs.getInt("disc_price"));
				prodVO.setDisc_start_date(rs.getDate("disc_start_date"));
				prodVO.setDisc_end_date(rs.getDate("disc_end_date"));
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

			con = ds.getConnection();
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
