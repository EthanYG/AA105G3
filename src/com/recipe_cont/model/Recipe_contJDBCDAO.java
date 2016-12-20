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

import com.emp_auth.model.Emp_authJDBCDAO;
import com.emp_auth.model.Emp_authVO;

public class Recipe_contJDBCDAO implements Recipe_contDAO_interface
{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String psw = "foodtime";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			byte[] step_pic = recipe_contVO.getStep_pic();
			long piclen = step_pic.length;
			InputStream bais = new ByteArrayInputStream(step_pic);
			
			pstmt.setString(1, recipe_contVO.getRecipe_no());
			pstmt.setInt(2, recipe_contVO.getStep());
			pstmt.setBinaryStream(3, bais, piclen);
			pstmt.setString(4, recipe_contVO.getStep_cont());
			
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
	public void update(Recipe_contVO recipe_contVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			byte[] step_pic = recipe_contVO.getStep_pic();
			long piclen = step_pic.length;
			InputStream bais = new ByteArrayInputStream(step_pic);
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, recipe_contVO.getStep_cont());
			pstmt.setBinaryStream(2, bais, piclen);
			pstmt.setString(3, recipe_contVO.getRecipe_no());
			pstmt.setInt(4, recipe_contVO.getStep());

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
	public void deleteOneStep(String recipe_no, Integer step)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(DELETE_ONE_STEP);
			
			pstmt.setString(1, recipe_no);
			pstmt.setInt(2, step);
			
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
	public Recipe_contVO getOneCont(String recipe_no, Integer step)
	{
		// TODO Auto-generated method stub
		Recipe_contVO recipe_contVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
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

	
	public static void main(String[] args) throws Exception
	{
		Recipe_contJDBCDAO dao = new Recipe_contJDBCDAO();
		
		//insert
//		File pic = new File("WebContent/images/recipe_cont","6.jpg");
//		InputStream fis = new FileInputStream(pic);
//		byte[] buffer = new byte[fis.available()];
		
//		Recipe_contVO recipe_contVO1 = new Recipe_contVO();
//		recipe_contVO1.setRecipe_no("R00000001");
//		recipe_contVO1.setStep(6);
//		recipe_contVO1.setStep_pic(buffer);
//		recipe_contVO1.setStep_cont("接著打開鍋蓋放入馬鈴薯和毛豆仁，略為攪拌一下，再繼續煮15分鐘即完成。");
//		
//		dao.insert(recipe_contVO1);
//		fis.close();
		
		//update
//		File pic = new File("WebContent/images","cat.jpg");
//		InputStream fis = new FileInputStream(pic);
//		byte[] buffer = new byte[fis.available()];
//		
//		
//		Recipe_contVO recipe_contVO2 = new Recipe_contVO();
//		recipe_contVO2.setRecipe_no("R00000001");
//		recipe_contVO2.setStep(6);
//		recipe_contVO2.setStep_pic(buffer);
//		recipe_contVO2.setStep_cont("更改的內容");
//		
//		dao.update(recipe_contVO2);
		
		//delete
//		dao.delete("R00000002");
		
		//delete one step
//		dao.deleteOneStep("R00000001",6);
		
		//search target
//		Set<Recipe_contVO> set = dao.findByPrimaryKey("R00000001");
//		for(Recipe_contVO recipe_contVO4: set){
//			System.out.print("| "+recipe_contVO4.getRecipe_no()+" | ");
//			System.out.print(recipe_contVO4.getStep()+" | ");
//			System.out.print(recipe_contVO4.getStep_pic()+" | ");
//			System.out.print(recipe_contVO4.getStep_cont()+" | ");
//			System.out.println();
//		}
		
		//search all
//		List<Recipe_contVO> list = dao.getAll();
//		for(Recipe_contVO recipe_contVO4: list){
//			System.out.print("| "+recipe_contVO4.getRecipe_no()+" | ");
//			System.out.print(recipe_contVO4.getStep()+" | ");
//			System.out.print(recipe_contVO4.getStep_pic()+" | ");
//			System.out.print(recipe_contVO4.getStep_cont()+" | ");
//			System.out.println();
//		}
		
		//getOneCont
//		Recipe_contVO recipe_contVO6 = dao.getOneCont("R00000001", 2);
//		System.out.print("| "+recipe_contVO6.getRecipe_no()+" | ");
//		System.out.print(recipe_contVO6.getStep()+" | ");
//		System.out.print(recipe_contVO6.getStep_pic()+" | ");
//		System.out.print(recipe_contVO6.getStep_cont()+" | ");
		
	}

	
}
