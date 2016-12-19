package com.recipe.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class RecipeDAO implements RecipeDAO_interface
{
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
			"INSERT INTO  recipe (recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic) "
			+ "VALUES ('R'||lpad(recipe_seq.NEXTVAL,8,0),?,?,?,?,?)";
	private static final String Get_ALL_STMT = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
			+ ",recipe_week_views,recipe_time from recipe order by recipe_no";
	private static final String GET_ONE_STMT = 
			"select recipe_no,mem_no,recipe_name,recipe_intro,food_mater,recipe_pic,recipe_like,recipe_total_views"
			+ ",recipe_week_views,recipe_time from recipe where recipe_no = ?";
	private static final String DELETE = 
			"DELETE FROM recipe where recipe_no = ?";
	private static final String UPDATE = 
			"UPDATE recipe set recipe_name=?,recipe_intro=?,food_mater=?,recipe_pic=?,recipe_time=sysdate where recipe_no = ?";
	private static final String UPDATEVIEWS = 
			"UPDATE recipe set recipe_total_views=?,recipe_week_views=? where recipe_no = ?";
	private static final String UPDATELIKE =
			"UPDATE recipe set recipe_like=? where recipe_no = ?";
	private static final String WeekViewsZero =
			"UPDATE recipe set recipe_week_views = 0 where recipe_no = ?";
	
	
	@Override
	public void insert(RecipeVO recipeVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			byte[] recipe_pic = recipeVO.getRecipe_pic();
			long piclen = recipe_pic.length;
			InputStream bais = new ByteArrayInputStream(recipe_pic);

			
			pstmt.setString(1, recipeVO.getMem_no());
			pstmt.setString(2, recipeVO.getRecipe_name());
			pstmt.setString(3, recipeVO.getRecipe_intro());
			pstmt.setString(4, recipeVO.getFood_mater());
			pstmt.setBinaryStream(5, bais, piclen);


			
			pstmt.executeUpdate();
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
	public void update(RecipeVO recipeVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			byte[] recipe_pic = recipeVO.getRecipe_pic();
			long piclen = recipe_pic.length;
			InputStream bais = new ByteArrayInputStream(recipe_pic);
			
			
			pstmt.setString(1, recipeVO.getRecipe_name());
			pstmt.setString(2, recipeVO.getRecipe_intro());
			pstmt.setString(3, recipeVO.getFood_mater());
			pstmt.setBinaryStream(4, bais, piclen);
			pstmt.setString(5, recipeVO.getRecipe_no());
			

			pstmt.executeUpdate();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, recipe_no);
			pstmt.executeUpdate();
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
	public RecipeVO findByPrimaryKey(String recipe_no)
	{
		// TODO Auto-generated method stub
		RecipeVO recipeVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ResultSetMetaData rsmd = null; 
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,recipe_no);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				recipeVO = new RecipeVO();
				recipeVO.setRecipe_no(rs.getString("recipe_no"));
				recipeVO.setMem_no(rs.getString("mem_no"));
				recipeVO.setRecipe_name(rs.getString("recipe_name"));
				recipeVO.setRecipe_intro(rs.getString("recipe_intro"));
				recipeVO.setFood_mater(rs.getString("food_mater"));
				recipeVO.setRecipe_pic(rs.getBytes("recipe_pic"));
				recipeVO.setRecipe_like(rs.getInt("recipe_like"));
				recipeVO.setRecipe_total_views(rs.getInt("recipe_total_views"));
				recipeVO.setRecipe_week_views(rs.getInt("recipe_week_views"));
				recipeVO.setRecipe_time(rs.getTimestamp("recipe_time"));
				
				
			}
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
		return recipeVO;
	}

	@Override
	public List<RecipeVO> getAll()
	{
		// TODO Auto-generated method stub
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		RecipeVO recipeVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ResultSetMetaData rsmd = null; 
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				recipeVO = new RecipeVO();
				recipeVO.setRecipe_no(rs.getString("recipe_no"));
				recipeVO.setMem_no(rs.getString("mem_no"));
				recipeVO.setRecipe_name(rs.getString("recipe_name"));
				recipeVO.setRecipe_intro(rs.getString("recipe_intro"));
				recipeVO.setFood_mater(rs.getString("food_mater"));
				recipeVO.setRecipe_pic(rs.getBytes("recipe_pic"));
				recipeVO.setRecipe_like(rs.getInt("recipe_like"));
				recipeVO.setRecipe_total_views(rs.getInt("recipe_total_views"));
				recipeVO.setRecipe_week_views(rs.getInt("recipe_week_views"));
				recipeVO.setRecipe_time(rs.getTimestamp("recipe_time"));
				
				list.add(recipeVO);
			}
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
	public void updateViews(RecipeVO recipeVO)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEVIEWS);

			pstmt.setInt(1, recipeVO.getRecipe_total_views());
			pstmt.setInt(2, recipeVO.getRecipe_week_views());
			pstmt.setString(3, recipeVO.getRecipe_no());
			

			pstmt.executeUpdate();

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
	public void updateLike(RecipeVO recipeVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATELIKE);

			pstmt.setInt(1, recipeVO.getRecipe_like());
			pstmt.setString(2, recipeVO.getRecipe_no());
			

			pstmt.executeUpdate();

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
	public void changeWeekViewsZero(String recipe_no)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(WeekViewsZero);

			pstmt.setString(1,recipe_no );
			

			pstmt.executeUpdate();

		
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
	
	
}
