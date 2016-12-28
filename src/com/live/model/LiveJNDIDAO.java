package com.live.model;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class LiveJNDIDAO implements LiveDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, liveVO.getMem_no());
			pstmt.setString(2, liveVO.getLive_name());
			pstmt.setString(3, liveVO.getLive_intro());
			pstmt.setString(4, liveVO.getLive_status());
		

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
	public void update(LiveVO liveVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, liveVO.getLive_name());
			pstmt.setString(2, liveVO.getLive_intro());
			pstmt.setInt(3, liveVO.getLive_counts());
			pstmt.setInt(4, liveVO.getLive_follow());
			pstmt.setString(5, liveVO.getLive_status());
			pstmt.setString(6, liveVO.getMem_no());

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
	public void delete(String mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);

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
	public LiveVO findByPrimaryKey(String mem_no) {

		LiveVO liveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
}