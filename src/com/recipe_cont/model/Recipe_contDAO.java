package com.recipe_cont.model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Recipe_contDAO implements Recipe_contDAO_interface
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
			"INSERT INTO  recipe_cont (recipe_no,step,step_pic,step_cont) VALUES (?, ?, ?, ?)";
	private static final String Get_ALL_STMT = 
			"select recipe_no,step,step_pic,step_cont from recipe_cont order by recipe_no,step";
	private static final String GET_ONE_STMT = 
			"select recipe_no,step,step_pic,step_cont from recipe_cont where recipe_no = ? order by step";
	private static final String DELETE = 
			"DELETE FROM recipe_cont where recipe_no = ?";
	private static final String DELETE_ONE_STEP =
			"DELETE FROM recipe_cont where (recipe_no = ?) and (step = ?)";
	private static final String UPDATE = 
			"UPDATE recipe_cont set step_cont = ? ,step_pic = ? where (recipe_no = ?) and (step = ?)";
	private static final String GET_ONE_STEP = 
			"select recipe_no,step,step_pic,step_cont from recipe_cont where recipe_no = ? and step =?";
	
	@Override
	public void insert(Recipe_contVO recipe_contVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			byte[] step_pic = recipe_contVO.getStep_pic();
			long piclen = step_pic.length;
			InputStream bais = new ByteArrayInputStream(step_pic);
			
			pstmt.setString(1, recipe_contVO.getRecipe_no());
			pstmt.setInt(2, recipe_contVO.getStep());
			pstmt.setBinaryStream(3, bais, piclen);
			pstmt.setString(4, recipe_contVO.getStep_cont());
			
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
	public void update(Recipe_contVO recipe_contVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			byte[] step_pic = recipe_contVO.getStep_pic();
			long piclen = step_pic.length;
			InputStream bais = new ByteArrayInputStream(step_pic);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipe_contVO.getStep_cont());
			pstmt.setBinaryStream(2, bais, piclen);
			pstmt.setString(3, recipe_contVO.getRecipe_no());
			pstmt.setInt(4, recipe_contVO.getStep());

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
	public void deleteOneStep(String recipe_no, Integer step)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ONE_STEP);
			
			pstmt.setString(1, recipe_no);
			pstmt.setInt(2, step);
			
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

	
	public Recipe_contVO getOneCont(String recipe_no, Integer step)
	{
		// TODO Auto-generated method stub
		Recipe_contVO recipe_contVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STEP);
			
			pstmt.setString(1,recipe_no);
			pstmt.setInt(2,step);
			rs = pstmt.executeQuery();
		
			while(rs.next())
			{
			recipe_contVO = new Recipe_contVO();
			recipe_contVO.setRecipe_no(rs.getString("recipe_no"));
			recipe_contVO.setStep(rs.getInt("step"));
			recipe_contVO.setStep_pic(rs.getBytes("step_pic"));
			recipe_contVO.setStep_cont(rs.getString("step_cont"));
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
		
		
		
		return recipe_contVO;
	}
	
	
	@Override
	public Set<Recipe_contVO> findByPrimaryKey(String recipe_no)
	{
		// TODO Auto-generated method stub
		Set<Recipe_contVO> set = new LinkedHashSet<Recipe_contVO>();
		Recipe_contVO recipe_contVO =null;
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
				recipe_contVO = new Recipe_contVO();
				recipe_contVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_contVO.setStep(rs.getInt("step"));
				recipe_contVO.setStep_pic(rs.getBytes("step_pic"));
				recipe_contVO.setStep_cont(rs.getString("step_cont"));
				set.add(recipe_contVO);
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
		return set;
	}

	@Override
	public List<Recipe_contVO> getAll()
	{
		// TODO Auto-generated method stub
		List<Recipe_contVO> list = new ArrayList<Recipe_contVO>();
		Recipe_contVO recipe_contVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				recipe_contVO = new Recipe_contVO();
				recipe_contVO.setRecipe_no(rs.getString("recipe_no"));
				recipe_contVO.setStep(rs.getInt("step"));
				recipe_contVO.setStep_pic(rs.getBytes("step_pic"));
				recipe_contVO.setStep_cont(rs.getString("step_cont"));
				list.add(recipe_contVO);
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
	
}
