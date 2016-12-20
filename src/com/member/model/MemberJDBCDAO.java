package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberJDBCDAO implements MemberDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
	private static final String INSERT_STMT = 
		"INSERT INTO member (mem_no,"
		+ " mem_name,"
		+ " mem_ac,"
		+ " mem_pw,"
		+ " mem_sex,"
		+ " mem_phone,"
		+ " mem_email,"
		+ " mem_adrs,"
		+ " mem_own,"
		+ " mem_history,"
		+ " mem_online) "
		+ "VALUES ('M'||LPAD(mem_seq.NEXTVAL,8,0), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mem_no,"
		+ " mem_name,"
		+ " mem_ac,"
		+ " mem_pw,"
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_ac());
			pstmt.setString(3, memVO.getMem_pw());
			pstmt.setString(4, memVO.getMem_sex());
			pstmt.setString(5, memVO.getMem_phone());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_adrs());
			pstmt.setString(8, memVO.getMem_own());
			pstmt.setString(9, memVO.getMem_history());
			pstmt.setString(10, memVO.getMem_online());

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
	public void update(MemberVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_ac());
			pstmt.setString(3, memVO.getMem_pw());
			pstmt.setString(4, memVO.getMem_sex());
			pstmt.setString(5, memVO.getMem_phone());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_adrs());
			pstmt.setString(8, memVO.getMem_own());
			pstmt.setString(9, memVO.getMem_history());
			pstmt.setString(10, memVO.getMem_online());
			pstmt.setString(11, memVO.getMem_no());

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
	public MemberVO findByPrimaryKey(String mem_no) {

		MemberVO memVO = null;
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
				// memVO 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_ac(rs.getString("mem_ac"));
				memVO.setMem_pw(rs.getString("mem_pw"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_adrs(rs.getString("mem_adrs"));
				memVO.setMem_own(rs.getString("mem_own"));
				memVO.setMem_history(rs.getString("mem_history"));
				memVO.setMem_online(rs.getString("mem_online"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMem_no(rs.getString("mem_no"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_ac(rs.getString("mem_ac"));
				memVO.setMem_pw(rs.getString("mem_pw"));
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

		MemberJDBCDAO dao = new MemberJDBCDAO();
		
		//新增
		/*MemberVO memVO1 = new MemberVO();
		memVO1.setMem_name("KITTEN");
		memVO1.setMem_ac("KITTEN!!!");
		memVO1.setMem_pw("258");
		memVO1.setMem_sex("0");
		memVO1.setMem_phone("0988776655");
		memVO1.setMem_email("KITTEN@GMAIL.COM");
		memVO1.setMem_adrs("KITTENS HOME");
		memVO1.setMem_own("0");
		memVO1.setMem_history("HAHA");
		memVO1.setMem_online("1");
		dao.insert(memVO1);*/
		
		//修改
		/*MemberVO memVO2 = new MemberVO();
		memVO2.setMem_no("M00000006");
		memVO2.setMem_name("HELLOKITTEN");
		memVO2.setMem_ac("KITTEN!!!");
		memVO2.setMem_pw("258");
		memVO2.setMem_sex("1");
		memVO2.setMem_phone("0911223344");
		memVO2.setMem_email("KITTEN@YAHOO.COM.TW");
		memVO2.setMem_adrs("KITTENS HOME");
		memVO2.setMem_own("0");
		memVO2.setMem_history("HAHA");
		memVO2.setMem_online("1");
		dao.update(memVO2);*/
		
		//刪除
		/*dao.delete("M00000006");*/
		
		// 查詢 - 單一
		/*MemberVO memVO3 = dao.findByPrimaryKey("M00000003");
		System.out.print(memVO3.getMem_no() + ",	");
		System.out.print(memVO3.getMem_name() + ",	");
		System.out.print(memVO3.getMem_ac() + ",	");
		System.out.print(memVO3.getMem_pw() + ",	");
		System.out.print(memVO3.getMem_sex() + ",	");
		System.out.print(memVO3.getMem_phone() + ",	");
		System.out.print(memVO3.getMem_email() + ",	");
		System.out.print(memVO3.getMem_adrs() + ",	");
		System.out.print(memVO3.getMem_own() + ",	");
		System.out.print(memVO3.getMem_history() + ",	");
		System.out.print(memVO3.getMem_online());
		System.out.println();*/

		// 查詢 - 全部
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMem : list) {
			System.out.print(aMem.getMem_no() + ",	");
			System.out.print(aMem.getMem_name() + ",	");
			System.out.print(aMem.getMem_ac() + ",	");
			System.out.print(aMem.getMem_pw() + ",	");
			System.out.print(aMem.getMem_sex() + ",	");
			System.out.print(aMem.getMem_phone() + ",	");
			System.out.print(aMem.getMem_email() + ",	");
			System.out.print(aMem.getMem_adrs() + ",	");
			System.out.print(aMem.getMem_own() + ",	");
			System.out.print(aMem.getMem_history() + ",	");
			System.out.print(aMem.getMem_online());
			System.out.println();
		}
	}

}
