package com.report_case.model;
import java.util.*;

import java.sql.*;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Report_caseJNDIDAO implements Report_caseDAO_interface{




	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO REPORT_CASE (rep_no,rep_mem_no,rep_tar_no,rep_type,rep_reason,rep_date,rep_chk_con) VALUES ('RE'||lpad(report_case_seq.NEXTVAL,8,0),?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT rep_no,rep_mem_no,rep_tar_no,rep_type,rep_reason,rep_date,rep_chk_con FROM REPORT_CASE order by Rep_no";
	private static final String GET_ONE_STMT = "SELECT rep_no,rep_mem_no,rep_tar_no,rep_type,rep_reason,rep_date,rep_chk_con FROM REPORT_CASE where Rep_no = ?";
	private static final String DELETE = "DELETE FROM REPORT_CASE where Rep_no = ?";
	private static final String UPDATE = "UPDATE REPORT_CASE set rep_mem_no=?,rep_tar_no=?,rep_type=?,rep_reason=?,rep_date=?,rep_chk_con=? where rep_no = ?";

	@Override
	public void insert(Report_caseVO report_caseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, report_caseVO.getRep_mem_no());
			pstmt.setString(2, report_caseVO.getRep_tar_no());
			pstmt.setString(3, report_caseVO.getRep_type());
			pstmt.setString(4, report_caseVO.getRep_reason());
			pstmt.setDate(5,  report_caseVO.getRep_date());
			pstmt.setString(6, report_caseVO.getRep_chk_con());
			
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
	public void update(Report_caseVO report_caseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, report_caseVO.getRep_mem_no());
			pstmt.setString(2, report_caseVO.getRep_tar_no());
			pstmt.setString(3, report_caseVO.getRep_type());
			pstmt.setString(4, report_caseVO.getRep_reason());
			pstmt.setDate(5, report_caseVO.getRep_date());
			pstmt.setString(6, report_caseVO.getRep_chk_con());
			pstmt.setString(7, report_caseVO.getRep_no());

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
	public void delete(String rep_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rep_no);

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
	public Report_caseVO findByPrimaryKey(String rep_no) {

		Report_caseVO report_caseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rep_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// report_caseVO 也稱為 Domain objects
				report_caseVO = new Report_caseVO();
				report_caseVO.setRep_mem_no(rs.getString("rep_mem_no"));
				report_caseVO.setRep_no(rs.getString("rep_no"));
				report_caseVO.setRep_tar_no(rs.getString("rep_tar_no"));
				report_caseVO.setRep_type(rs.getString("rep_type"));
				report_caseVO.setRep_reason(rs.getString("rep_reason"));
				report_caseVO.setRep_date(rs.getDate("rep_date"));
				report_caseVO.setRep_chk_con(rs.getString("rep_chk_con"));
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
		return report_caseVO;
	}

	@Override
	public List<Report_caseVO> getAll() {
		List<Report_caseVO> list = new ArrayList<Report_caseVO>();
		Report_caseVO report_caseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// report_caseVO 也稱為 Domain objects
				report_caseVO = new Report_caseVO();
				report_caseVO.setRep_mem_no(rs.getString("rep_mem_no"));
				report_caseVO.setRep_no(rs.getString("rep_no"));
				report_caseVO.setRep_tar_no(rs.getString("rep_tar_no"));
				report_caseVO.setRep_type(rs.getString("rep_type"));
				report_caseVO.setRep_reason(rs.getString("rep_reason"));
				report_caseVO.setRep_date(rs.getDate("rep_date"));
				report_caseVO.setRep_chk_con(rs.getString("rep_chk_con"));
				list.add(report_caseVO); // Store the row in the list
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