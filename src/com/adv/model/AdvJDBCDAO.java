package com.adv.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdvJDBCDAO implements AdvDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
	private static final String INSERT_STMT = 
		"INSERT INTO adv (adv_no,"
		+ " emp_no,"
		+ " adv_name,"
		+ " adv_image_name,"
		+ " adv_url) "
		+ "VALUES (adv_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT adv_no,"
		+ " emp_no,"
		+ " adv_name,"
		+ " adv_image_name,"
		+ " adv_url FROM adv order by adv_no";
	private static final String GET_ONE_STMT = 
		"SELECT adv_no,"
		+ " emp_no,"
		+ " adv_name,"
		+ " adv_image_name,"
		+ " adv_url FROM adv where adv_no = ?";
	private static final String DELETE = 
		"DELETE FROM adv where adv_no = ?";
	private static final String UPDATE = 
		"UPDATE adv set emp_no=?,"
		+ " adv_name=?,"
		+ " adv_image_name=?,"
		+ " adv_url=? where adv_no = ?";
	
	@Override
	public void insert(AdvVO advVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, advVO.getEmp_no());
			pstmt.setString(2, advVO.getAdv_name());
			pstmt.setString(3, advVO.getAdv_image_name());
			pstmt.setString(4, advVO.getAdv_url());

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
	public void update(AdvVO advVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, advVO.getEmp_no());
			pstmt.setString(2, advVO.getAdv_name());
			pstmt.setString(3, advVO.getAdv_image_name());
			pstmt.setString(4, advVO.getAdv_url());
			pstmt.setString(5, advVO.getAdv_no());

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
	public void delete(String adv_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adv_no);

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
	public AdvVO findByPrimaryKey(String adv_no) {

		AdvVO advVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adv_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// advVO 也稱為 Domain objects
				advVO = new AdvVO();
				advVO.setAdv_no(rs.getString("adv_no"));
				advVO.setEmp_no(rs.getString("emp_no"));
				advVO.setAdv_name(rs.getString("adv_name"));
				advVO.setAdv_image_name(rs.getString("adv_image_name"));
				advVO.setAdv_url(rs.getString("adv_url"));
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
		return advVO;
	}

	@Override
	public List<AdvVO> getAll() {
		List<AdvVO> list = new ArrayList<AdvVO>();
		AdvVO advVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// advVO 也稱為 Domain objects
				advVO = new AdvVO();
				advVO.setAdv_no(rs.getString("adv_no"));
				advVO.setEmp_no(rs.getString("emp_no"));
				advVO.setAdv_name(rs.getString("adv_name"));
				advVO.setAdv_image_name(rs.getString("adv_image_name"));
				advVO.setAdv_url(rs.getString("adv_url"));
				list.add(advVO); // Store the row in the list
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

		AdvJDBCDAO dao = new AdvJDBCDAO();
		
		//新增
		/*AdvVO advVO1 = new AdvVO();
		advVO1.setEmp_no("1002");
		advVO1.setAdv_name("新的廣告");
		advVO1.setAdv_image_name("來路不明的廣告");
		advVO1.setAdv_url("WWW.YOUTUBE.COM");
		dao.insert(advVO1);*/
		
		//修改
		/*AdvVO advVO2 = new AdvVO();
		advVO2.setAdv_no("6");
		advVO2.setEmp_no("1001");
		advVO2.setAdv_name("新的廣告");
		advVO2.setAdv_image_name("不明覺厲的廣告");
		advVO2.setAdv_url("WWW.TWITCH.COM");
		dao.update(advVO2);*/
		
		//刪除
		/*dao.delete("6");*/
		
		// 查詢 - 單一
		/*AdvVO advVO3 = dao.findByPrimaryKey("3");
		System.out.print(advVO3.getAdv_no() + ",	");
		System.out.print(advVO3.getEmp_no() + ",	");
		System.out.print(advVO3.getAdv_name() + ",	");
		System.out.print(advVO3.getAdv_image_name() + ",	");
		System.out.print(advVO3.getAdv_url());
		System.out.println();*/

		// 查詢 - 全部
		List<AdvVO> list = dao.getAll();
		for (AdvVO aAdv : list) {
			System.out.print(aAdv.getAdv_no() + ",	");
			System.out.print(aAdv.getEmp_no() + ",	");
			System.out.print(aAdv.getAdv_name() + ",	");
			System.out.print(aAdv.getAdv_image_name() + ",	");
			System.out.print(aAdv.getAdv_url());
			System.out.println();
		}
	}

}
