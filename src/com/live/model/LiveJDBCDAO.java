package com.live.model;
import java.util.*;

import java.sql.*;

public class LiveJDBCDAO implements LiveDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";

	private static final String INSERT_STMT = 
		"INSERT INTO live (mem_no,live_name,live_intro,live_status) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mem_no,live_name,live_intro,live_counts,live_follow,live_status FROM live order by mem_no";
	private static final String GET_ONE_STMT = 
		"SELECT mem_no,live_name,live_intro,live_counts,live_follow,live_status FROM live where mem_no = ?";
	private static final String DELETE = 
		"DELETE FROM live where mem_no = ?";
	private static final String UPDATE = 
		"UPDATE live set live_name=?,live_intro=?,live_counts=?,live_follow=?,live_status=? where mem_no = ?";

	@Override
	public void insert(LiveVO liveVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, liveVO.getMem_no());
			pstmt.setString(2, liveVO.getLive_name());
			pstmt.setString(3, liveVO.getLive_intro());
			pstmt.setString(4, liveVO.getLive_status());
		

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
	public void update(LiveVO liveVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, liveVO.getLive_name());
			pstmt.setString(2, liveVO.getLive_intro());
			pstmt.setInt(3, liveVO.getLive_counts());
			pstmt.setInt(4, liveVO.getLive_follow());
			pstmt.setString(5, liveVO.getLive_status());
			pstmt.setString(6, liveVO.getMem_no());

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
	public void delete(String mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);

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
	public LiveVO findByPrimaryKey(String mem_no) {

		LiveVO liveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// liveVO �]�٬� Domain objects
				liveVO = new LiveVO();
				liveVO.setMem_no(rs.getString("mem_no"));
				liveVO.setLive_name(rs.getString("live_name"));
				liveVO.setLive_intro(rs.getString("live_intro"));
				liveVO.setLive_counts(rs.getInt("live_counts"));
				liveVO.setLive_follow(rs.getInt("live_follow"));
				liveVO.setLive_status(rs.getString("live_status"));
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
		return liveVO;
	}

	@Override
	public List<LiveVO> getAll() {
		List<LiveVO> list = new ArrayList<LiveVO>();
		LiveVO liveVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// liveVO �]�٬� Domain objects
				liveVO = new LiveVO();
				liveVO.setMem_no(rs.getString("mem_no"));
				liveVO.setLive_name(rs.getString("live_name"));
				liveVO.setLive_intro(rs.getString("live_intro"));
				liveVO.setLive_counts(rs.getInt("live_counts"));
				liveVO.setLive_follow(rs.getInt("live_follow"));
				liveVO.setLive_status(rs.getString("live_status"));
				list.add(liveVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			se.printStackTrace();
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

		LiveJDBCDAO dao = new LiveJDBCDAO();

		// 新增
//		LiveVO liveVO1 = new LiveVO();
//		liveVO1.setMem_no("M00000005");
//		liveVO1.setLive_name("MANAGER");
//		liveVO1.setLive_intro("this is intro");
//		liveVO1.setLive_status("1");
//		
//		dao.insert(liveVO1);

//		 修改
		LiveVO liveVO2 = new LiveVO();
		liveVO2.setMem_no("M00000005");
		liveVO2.setLive_name("The new title");
		liveVO2.setLive_intro("this is new intro");
		liveVO2.setLive_counts(500);
		liveVO2.setLive_follow(5);
		liveVO2.setLive_status("0");
		
		
//		dao.update(liveVO2);

		// 刪除
//		dao.delete("M00000005");

		// 查詢
		LiveVO liveVO3 = dao.findByPrimaryKey("M00000001");
		System.out.print(liveVO3.getMem_no()+",");
		System.out.print(liveVO3.getLive_name() + ",");
		System.out.print(liveVO3.getLive_intro() + ",");
		System.out.print(liveVO3.getLive_counts() + ",");
		System.out.print(liveVO3.getLive_follow() + ",");
		System.out.print(liveVO3.getLive_status() + ",");
		System.out.println("---------------------");

		 //查詢
//		List<LiveVO> list = dao.getAll();
//		for (LiveVO liveVO4 : list) {
//			System.out.print(liveVO4.getMem_no()+",");
//			System.out.print(liveVO4.getLive_name() + ",");
//			System.out.print(liveVO4.getLive_intro() + ",");
//			System.out.print(liveVO4.getLive_counts() + ",");
//			System.out.print(liveVO4.getLive_follow() + ",");
//			System.out.print(liveVO4.getLive_status() + ",");
//			System.out.println();
//		}
	}
}