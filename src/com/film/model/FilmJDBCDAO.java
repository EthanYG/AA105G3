package com.film.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmJDBCDAO implements FilmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, filmVO.getMem_no());
			pstmt.setString(2, filmVO.getFilm_title());
			pstmt.setString(3, filmVO.getFilm_content());
			pstmt.setDate(4, filmVO.getFilm_date());
			pstmt.setInt(5, filmVO.getFilm_like());
			pstmt.setInt(6, filmVO.getFilm_popular());
			

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
	public void update(FilmVO filmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String film_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, film_no);

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
	public FilmVO findByPrimaryKey(String film_no) {

		FilmVO filmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		FilmJDBCDAO dao = new FilmJDBCDAO();

//		 新增
		FilmVO filmVO1 = new FilmVO();
		filmVO1.setMem_no("M00000002");
		filmVO1.setFilm_title("宮保雞丁");
		filmVO1.setFilm_content("正宗四川極品宮保雞丁！");

		dao.insert(filmVO1);

		// 修改
		FilmVO filmVO2 = new FilmVO();

		filmVO2.setFilm_no("F00000002");
		filmVO2.setFilm_title("咖哩烏龍麵");
		filmVO2.setFilm_content("美味的日式咖哩烏龍麵！");
		filmVO2.setFilm_like(2);
		filmVO2.setFilm_popular(2);

		dao.update(filmVO2);

//		// 刪除
//		dao.delete("F00000006");
//
////		查詢
//		FilmVO filmVO3 = dao.findByPrimaryKey("F00000002");
//		System.out.print(filmVO3.getFilm_no() + ",");
//		System.out.print(filmVO3.getMem_no() + ",");
//		System.out.print(filmVO3.getFilm_title() + ",");
//		System.out.print(filmVO3.getFilm_content() + ",");
//		System.out.print(filmVO3.getFilm_title() + ",");
//		System.out.print(filmVO3.getFilm_date() + ",");
//		System.out.print(filmVO3.getFilm_file() + ",");
//		System.out.print(filmVO3.getFilm_like() + ",");
//		System.out.print(filmVO3.getFilm_popular());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<FilmVO> list = dao.getAll();
//		for (FilmVO filmVO4 : list) {
//			System.out.print(filmVO4.getFilm_no() + ",");
//			System.out.print(filmVO4.getMem_no() + ",");
//			System.out.print(filmVO4.getFilm_title() + ",");
//			System.out.print(filmVO4.getFilm_content() + ",");
//			System.out.print(filmVO4.getFilm_title() + ",");
//			System.out.print(filmVO4.getFilm_date() + ",");
//			System.out.print(filmVO4.getFilm_file() + ",");
//			System.out.print(filmVO4.getFilm_like() + ",");
//			System.out.print(filmVO4.getFilm_popular());
//			System.out.println();
//		}
	}
}