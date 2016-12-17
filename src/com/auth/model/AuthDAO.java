package com.auth.model;

import java.sql.Connection;
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

import com.auth.model.AuthVO;


public class AuthDAO implements AuthDAO_interface
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
			"INSERT INTO  auth (auth_no,auth_name) VALUES (lpad(auth_seq.NEXTVAL,3,0), ?)";
	private static final String Get_ALL_STMT = 
			"select auth_no,auth_name from auth order by auth_no";
	private static final String GET_ONE_STMT = 
			"select auth_no,auth_name from auth where auth_no = ?";
	private static final String DELETE = 
			"DELETE FROM auth where auth_no = ?";
	private static final String UPDATE = 
			"UPDATE auth set auth_name=? where auth_no = ?";
	
	@Override
	public void insert(AuthVO authVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, authVO.getAuth_name());
			pstmt.executeUpdate();

		}  catch (SQLException se)
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
	public void update(AuthVO authVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, authVO.getAuth_name());
			pstmt.setString(2, authVO.getAuth_no());

			pstmt.executeUpdate();

		}  catch (SQLException se) {
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
	public void delete(String auth_no)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, auth_no);
			pstmt.executeUpdate();
		}  catch (SQLException e)
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
	public AuthVO findByPrimaryKey(String auth_no)
	{
		// TODO Auto-generated method stub
		AuthVO authVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ResultSetMetaData rsmd = null; 
		try
		{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,auth_no);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				authVO = new AuthVO();
				authVO.setAuth_no(rs.getString("auth_no"));
				authVO.setAuth_name(rs.getString("auth_name"));
				
			}
		}  catch (SQLException e)
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
		return authVO;
	}

	@Override
	public List<AuthVO> getAll()
	{
		// TODO Auto-generated method stub
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;
		
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
				authVO = new AuthVO();
				authVO.setAuth_no(rs.getString("auth_no"));
				authVO.setAuth_name(rs.getString("auth_name"));
				
				list.add(authVO);
			}
			
		}  catch (SQLException e)
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
		AuthJDBCDAO dao = new AuthJDBCDAO();
		
		//insert
//		AuthVO authVO1 = new AuthVO();
//		authVO1.setAuth_name("前端網頁管理");
//		
//		dao.insert(authVO1);
		
		//update
//		AuthVO authVO2 = new AuthVO();
//		authVO2.setAuth_no("006");
//		authVO2.setAuth_name("會員管理");
//		
//		dao.update(authVO2);
		
		//delete
//		dao.delete("006");
		
		//search target
//		AuthVO authVO3 = dao.findByPrimaryKey("002");
//		System.out.print("| "+authVO3.getAuth_no()+" | ");
//		System.out.print(authVO3.getAuth_name()+" | ");
		
				
				//search all
//		List<AuthVO> list = dao.getAll();
//		for(AuthVO authVO4: list){
//			System.out.print("| "+authVO4.getAuth_no()+" | ");
//			System.out.print(authVO4.getAuth_name()+" | ");
//			System.out.println();
//		}
				
		
	}
}
