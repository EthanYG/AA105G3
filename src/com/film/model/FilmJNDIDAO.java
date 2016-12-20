package com.film.model;

import java.util.*;

import java.sql.*;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FilmJNDIDAO implements FilmDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO film (film_no,mem_no,film_title,film_content,film_date,film_like,film_popular) VALUES ('F'||lpad(film_seq.NEXTVAL,8,0), ?, ?, ? ,?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT film_no,mem_no,film_title,film_content,film_date,film_file,film_like,film_popular FROM film order by mem_no";
	private static final String GET_ONE_STMT = "SELECT film_no,mem_no,film_title,film_content,film_date,film_file,film_like,film_popular FROM film where film_no = ?";
	private static final String DELETE = "DELETE FROM film where film_no = ?";
	private static final String UPDATE = "UPDATE film set mem_no=?, film_title=?, film_content=?, film_date=?, film_like=?, film_popular=?  where film_no = ?";

	@Override
	public void insert(FilmVO filmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, filmVO.getMem_no());
			pstmt.setString(2, filmVO.getFilm_title());
			pstmt.setString(3, filmVO.getFilm_content());
			pstmt.setDate(4, filmVO.getFilm_date());
			pstmt.setInt(5, filmVO.getFilm_like());
			pstmt.setInt(6, filmVO.getFilm_popular());
			

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
	public void update(FilmVO filmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, filmVO.getMem_no());
			pstmt.setString(2, filmVO.getFilm_title());
			pstmt.setString(3, filmVO.getFilm_content());
			pstmt.setDate(4, filmVO.getFilm_date());
			pstmt.setInt(5, filmVO.getFilm_like());
			pstmt.setInt(6, filmVO.getFilm_popular());
			pstmt.setString(7, filmVO.getFilm_no());
			
			
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
	public void delete(String film_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, film_no);

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
	public FilmVO findByPrimaryKey(String film_no) {

		FilmVO filmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, film_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// filmVO 嚙稽嚙誶穿蕭 Domain objects
				filmVO = new FilmVO();
				filmVO.setFilm_no(rs.getString("film_no"));
				filmVO.setMem_no(rs.getString("mem_no"));
				filmVO.setFilm_title(rs.getString("film_title"));
				filmVO.setFilm_date(rs.getDate("film_date"));
				filmVO.setFilm_content(rs.getString("film_content"));
				filmVO.setFilm_file(rs.getBytes("film_file"));
				filmVO.setFilm_like(rs.getInt("film_like"));
				filmVO.setFilm_popular(rs.getInt("film_popular"));
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
		return filmVO;
	}

	@Override
	public List<FilmVO> getAll() {
		List<FilmVO> list = new ArrayList<FilmVO>();
		FilmVO filmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// filmVO 嚙稽嚙誶穿蕭 Domain objects
				filmVO = new FilmVO();
				filmVO.setFilm_no(rs.getString("film_no"));
				filmVO.setMem_no(rs.getString("mem_no"));
				filmVO.setFilm_title(rs.getString("film_title"));
				filmVO.setFilm_date(rs.getDate("film_date"));
				filmVO.setFilm_content(rs.getString("film_content"));
				filmVO.setFilm_file(rs.getBytes("film_file"));
				filmVO.setFilm_like(rs.getInt("film_like"));
				filmVO.setFilm_popular(rs.getInt("film_popular"));
				list.add(filmVO); // Store the row in the list
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