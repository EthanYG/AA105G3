package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberJNDIDAO implements MemberDAO_interface {
	
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
		"INSERT INTO member (mem_no,"
		+ " mem_name,"
		+ " mem_ac,"
		+ " mem_pw,"
		+ " mem_image,"
		+ " mem_sex,"
		+ " mem_phone,"
		+ " mem_email,"
		+ " mem_adrs,"
		+ " mem_own,"
		+ " mem_history,"
		+ " mem_online) "
		+ "VALUES ('M'||LPAD(mem_seq.NEXTVAL,8,0), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mem_no,"
		+ " mem_name,"
		+ " mem_ac,"
		+ " mem_pw,"
		+ " mem_image,"
		+ " mem_sex,"
		+ " mem_phone,"
		+ " mem_email,"
		+ " mem_adrs,"
		+ " mem_own,"
		+ " mem_history,"
		+ " mem_online FROM member order by mem_no";
	private static final String GET_ONE_STMT = 
		"SELECT mem_no,"
		+ " mem_name,"
		+ " mem_ac,"
		+ " mem_pw,"
		+ " mem_image,"
		+ " mem_sex,"
		+ " mem_phone,"
		+ " mem_email,"
		+ " mem_adrs,"
		+ " mem_own,"
		+ " mem_history,"
		+ " mem_online FROM member where mem_no = ?";
	private static final String DELETE = 
		"DELETE FROM member where mem_no = ?";
	private static final String UPDATE = 
		"UPDATE member set mem_name=?,"
		+ " mem_ac=?,"
		+ " mem_pw=?,"
		+ " mem_image=?,"
		+ " mem_sex=?,"
		+ " mem_phone=?,"
		+ " mem_email=?,"
		+ " mem_adrs=?,"
		+ " mem_own=?,"
		+ " mem_history=?,"
		+ " mem_online=? where mem_no = ?";
	
	@Override
	public void insert(MemberVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_ac());
			pstmt.setString(3, memVO.getMem_pw());
			pstmt.setBytes(4, memVO.getMem_image());
			pstmt.setString(5, memVO.getMem_sex());
			pstmt.setString(6, memVO.getMem_phone());
			pstmt.setString(7, memVO.getMem_email());
			pstmt.setString(8, memVO.getMem_adrs());
			pstmt.setString(9, memVO.getMem_own());
			pstmt.setString(10, memVO.getMem_history());
			pstmt.setString(11, memVO.getMem_online());

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
	public void update(MemberVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_ac());
			pstmt.setString(3, memVO.getMem_pw());
			pstmt.setBytes(4, memVO.getMem_image());
			pstmt.setString(5, memVO.getMem_sex());
			pstmt.setString(6, memVO.getMem_phone());
			pstmt.setString(7, memVO.getMem_email());
			pstmt.setString(8, memVO.getMem_adrs());
			pstmt.setString(9, memVO.getMem_own());
			pstmt.setString(10, memVO.getMem_history());
			pstmt.setString(11, memVO.getMem_online());
			pstmt.setString(12, memVO.getMem_no());

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
	public MemberVO findByPrimaryKey(String mem_no) {

		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_ac(rs.getString("mem_ac"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_image(rs.getBytes("mem_image"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_adrs(rs.getString("mem_adrs"));
				memVO.setMem_own(rs.getString("mem_own"));
				memVO.setMem_history(rs.getString("mem_history"));
				memVO.setMem_online(rs.getString("mem_online"));
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
		return memVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_ac(rs.getString("mem_ac"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_image(rs.getBytes("mem_image"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_adrs(rs.getString("mem_adrs"));
				memVO.setMem_own(rs.getString("mem_own"));
				memVO.setMem_history(rs.getString("mem_history"));
				memVO.setMem_online(rs.getString("mem_online"));
				list.add(memVO); // Store the row in the list
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
