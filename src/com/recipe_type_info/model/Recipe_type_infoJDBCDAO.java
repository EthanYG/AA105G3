package com.recipe_type_info.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp_auth.model.Emp_authVO;

public class Recipe_type_infoJDBCDAO implements Recipe_type_infoDAO_interface
{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String psw = "foodtime";

	private static final String INSERT_STMT = 
			"INSERT INTO  recipe_type_info (recipe_no,recipe_type_no,type_range) VALUES (?, ?, ?)";
	private static final String Get_ALL_STMT = 
			"select recipe_no,recipe_type_no,type_range from recipe_type_info order by recipe_no";
	private static final String GET_ONE_STMT = 
			"select recipe_no,recipe_type_no,type_range from recipe_type_info where recipe_no = ?";
	private static final String DELETE = 
			"DELETE FROM recipe_type_info where recipe_no= ?";
	private static final String DELETE_ONE_TYPE =
			"DELETE FROM recipe_type_info where (recipe_no=?) and (recipe_type_no=?)";
	private static final String UPDATE = 
			"UPDATE recipe_type_info set recipe_type_no = ? ,type_range = ? where (recipe_no = ? ) and (recipe_type_no = ?)";
	
	@Override
	public void insert(Recipe_type_infoVO recipe_type_infoVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, recipe_type_infoVO.getRecipe_no());
			pstmt.setString(2, recipe_type_infoVO.getRecipe_type_no());
			pstmt.setString(3, recipe_type_infoVO.getType_range());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException se)
		{
			se.printStackTrace();
		} finally
		{
			if (pstmt != null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException se)
				{
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}
			if (con != null)
			{
				try
				{
					con.close();
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Recipe_type_infoVO recipe_type_infoVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipe_type_infoVO.getRecipe_type_no());
			pstmt.setString(2, recipe_type_infoVO.getType_range());
			pstmt.setString(3, recipe_type_infoVO.getRecipe_no());
			pstmt.setString(4, recipe_type_infoVO.getTarget_recipe_type_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String recipe_no)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, recipe_no);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(pstmt !=null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con !=null){
				try
				{
					con.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void deleteOneType(String recipe_no, String recipe_type_no)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(DELETE_ONE_TYPE);
			
			pstmt.setString(1, recipe_no);
			pstmt.setString(2, recipe_type_no);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(pstmt !=null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con !=null){
				try
				{
					con.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Recipe_type_infoVO> findByPrimaryKey(String recipe_no)
	{
		// TODO Auto-generated method stub
		List<Recipe_type_infoVO> list = new ArrayList<Recipe_type_infoVO>();
		Recipe_type_infoVO recipe_type_infoVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ResultSetMetaData rsmd = null; 
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,recipe_no);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				recipe_type_infoVO = new Recipe_type_infoVO();
				recipe_type_infoVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_type_infoVO.setRecipe_type_no(rs.getString("recipe_type_no"));
				recipe_type_infoVO.setType_range(rs.getString("type_range"));
				
				list.add(recipe_type_infoVO);
			}
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(pstmt !=null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con !=null){
				try
				{
					con.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<Recipe_type_infoVO> getAll()
	{
		// TODO Auto-generated method stub
		List<Recipe_type_infoVO> list = new ArrayList<Recipe_type_infoVO>();
		Recipe_type_infoVO recipe_type_infoVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(Get_ALL_STMT);
			
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				recipe_type_infoVO = new Recipe_type_infoVO();
				recipe_type_infoVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_type_infoVO.setRecipe_type_no(rs.getString("recipe_type_no"));
				recipe_type_infoVO.setType_range(rs.getString("type_range"));
				
				list.add(recipe_type_infoVO);
			}
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(pstmt !=null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con !=null){
				try
				{
					con.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static void main(String[] args)
	{
		Recipe_type_infoJDBCDAO dao = new Recipe_type_infoJDBCDAO();
		
		//insert
		
//		Recipe_type_infoVO recipe_type_infoVO1 =new Recipe_type_infoVO();
//		recipe_type_infoVO1.setRecipe_no("R00000002");
//		recipe_type_infoVO1.setRecipe_type_no("RM0003");
//		recipe_type_infoVO1.setType_range("1");
//		
//		dao.insert(recipe_type_infoVO1);
		
		//update
//		Recipe_type_infoVO recipe_type_infoVO2 =new Recipe_type_infoVO();
//		recipe_type_infoVO2.setRecipe_type_no("RS0006");
//		recipe_type_infoVO2.setType_range("2");
//		recipe_type_infoVO2.setRecipe_no("R00000001");
//		recipe_type_infoVO2.setTarget_recipe_type_no("RS0001");
//		
//		dao.update(recipe_type_infoVO2);
		
		
		//delete
//		dao.delete("R00000002");
		
		//delete one type
//		dao.deleteOneType("R00000001", "RS0006");
		
		//search target
//		List<Recipe_type_infoVO> list =dao.findByPrimaryKey("R00000001");
//		for(Recipe_type_infoVO recipe_type_infoVO3: list){
//			System.out.print("| "+recipe_type_infoVO3.getRecipe_no()+" | ");
//			System.out.print(recipe_type_infoVO3.getRecipe_type_no()+" | ");
//			System.out.print(recipe_type_infoVO3.getType_range()+" | ");
//			System.out.println();
//		}
		
		//search all
//		List<Recipe_type_infoVO> list =dao.getAll();
//		for(Recipe_type_infoVO recipe_type_infoVO4: list){
//			System.out.print("| "+recipe_type_infoVO4.getRecipe_no()+" | ");
//			System.out.print(recipe_type_infoVO4.getRecipe_type_no()+" | ");
//			System.out.print(recipe_type_infoVO4.getType_range()+" | ");
//			System.out.println();
//		}
		
	}
}
