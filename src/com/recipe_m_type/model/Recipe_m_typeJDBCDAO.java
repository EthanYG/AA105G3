package com.recipe_m_type.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Recipe_m_typeJDBCDAO implements Recipe_m_typeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";
	
	private static final String INSERT_STMT = 
		"INSERT INTO recipe_m_type (recipe_m_type_no,"
		+ " m_type_name,"
		+ " parent_type) "
		+ "VALUES ('RM'||LPAD(recipe_m_type_seq.NEXTVAL,4,0), ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT recipe_m_type_no,"
		+ " m_type_name,"
		+ " parent_type FROM recipe_m_type order by recipe_m_type_no";
	private static final String GET_ONE_STMT = 
		"SELECT recipe_m_type_no,"
		+ " m_type_name,"
		+ " parent_type FROM recipe_m_type where recipe_m_type_no = ?";
	private static final String DELETE = 
		"DELETE FROM recipe_m_type where recipe_m_type_no = ?";
	private static final String UPDATE = 
		"UPDATE recipe_m_type set m_type_name=?,"
		+ " parent_type=? where recipe_m_type_no = ?";
	
	@Override
	public void insert(Recipe_m_typeVO recipe_m_typeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recipe_m_typeVO.getM_type_name());
			pstmt.setString(2, recipe_m_typeVO.getParent_type());

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
	public void update(Recipe_m_typeVO recipe_m_typeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipe_m_typeVO.getM_type_name());
			pstmt.setString(2, recipe_m_typeVO.getParent_type());
			pstmt.setString(3, recipe_m_typeVO.getRecipe_m_type_no());
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
	public void delete(String recipe_m_type_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, recipe_m_type_no);

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
	public Recipe_m_typeVO findByPrimaryKey(String recipe_m_type_no) {

		Recipe_m_typeVO recipe_m_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, recipe_m_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_m_typeVO 也稱為 Domain objects
				recipe_m_typeVO = new Recipe_m_typeVO();
				recipe_m_typeVO.setRecipe_m_type_no(rs.getString("recipe_m_type_no"));
				recipe_m_typeVO.setM_type_name(rs.getString("m_type_name"));
				recipe_m_typeVO.setParent_type(rs.getString("parent_type"));
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
		return recipe_m_typeVO;
	}

	@Override
	public List<Recipe_m_typeVO> getAll() {
		List<Recipe_m_typeVO> list = new ArrayList<Recipe_m_typeVO>();
		Recipe_m_typeVO recipe_m_typeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// recipe_m_typeVO 也稱為 Domain objects
				recipe_m_typeVO = new Recipe_m_typeVO();
				recipe_m_typeVO.setRecipe_m_type_no(rs.getString("recipe_m_type_no"));
				recipe_m_typeVO.setM_type_name(rs.getString("m_type_name"));
				recipe_m_typeVO.setParent_type(rs.getString("parent_type"));
				list.add(recipe_m_typeVO); // Store the row in the list
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

		Recipe_m_typeJDBCDAO dao = new Recipe_m_typeJDBCDAO();
		
		//新增
		/*Recipe_m_typeVO recipe_m_typeVO1 = new Recipe_m_typeVO();
		recipe_m_typeVO1.setM_type_name("新的中類別");
		recipe_m_typeVO1.setParent_type("RL0001");
		dao.insert(recipe_m_typeVO1);*/
		
		//修改
		/*Recipe_m_typeVO recipe_m_typeVO2 = new Recipe_m_typeVO();
		recipe_m_typeVO2.setRecipe_m_type_no("RM0009");
		recipe_m_typeVO2.setM_type_name("修改中類別");
		recipe_m_typeVO2.setParent_type("RL0003");
		dao.update(recipe_m_typeVO2);*/
		
		//刪除
		/*dao.delete("RM0009");*/
		
		// 查詢 - 單一
		/*Recipe_m_typeVO recipe_m_typeVO3 = dao.findByPrimaryKey("RM0003");
		System.out.print(recipe_m_typeVO3.getRecipe_m_type_no() + ",	");
		System.out.print(recipe_m_typeVO3.getM_type_name() + ",	");
		System.out.print(recipe_m_typeVO3.getParent_type());
		System.out.println();*/

		// 查詢 - 全部
		List<Recipe_m_typeVO> list = dao.getAll();
		for (Recipe_m_typeVO arecipe_m_type : list) {
			System.out.print(arecipe_m_type.getRecipe_m_type_no() + ",	");
			System.out.print(arecipe_m_type.getM_type_name() + ",	");
			System.out.print(arecipe_m_type.getParent_type());
			System.out.println();
		}
	}

}
