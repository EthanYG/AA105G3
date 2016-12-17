package com.emp_auth.model;

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



public class Emp_authJDBCDAO implements Emp_authDAO_interface
{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String psw = "foodtime";

	private static final String INSERT_STMT = 
			"INSERT INTO emp_auth (emp_no,auth_no) VALUES (?, ?)";
	private static final String Get_ALL_STMT = 
			"select emp_no,auth_no from emp_auth order by emp_no";
	private static final String GET_ONE_STMT = 
			"select emp_no,auth_no from emp_auth where emp_no = ?";
	private static final String DELETE = 
			"DELETE FROM emp_auth where emp_no= ?";
	private static final String DELETE_ONE_AUTH =
			"DELETE FROM emp_auth where (emp_no=?) and (auth_no=?)";
	private static final String UPDATE = 
			"UPDATE emp_auth set auth_no=? where (emp_no=?) and (auth_no=?)";
	
	
	

	@Override
	public void insert(Emp_authVO emp_authVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, emp_authVO.getEmp_no());
			pstmt.setString(2, emp_authVO.getAuth_no());
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
	public void update(Emp_authVO emp_authVO)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, emp_authVO.getAuth_no());
			pstmt.setString(2, emp_authVO.getEmp_no());
			pstmt.setString(3, emp_authVO.getTarget_auth_no());

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
	public void delete(String emp_no)
	{
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, emp_no);
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
	public void deleteOneAuth(String emp_no,String auth_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(DELETE_ONE_AUTH);
			
			pstmt.setString(1, emp_no);
			pstmt.setString(2, auth_no);
			
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
	public Set<Emp_authVO> findByPrimaryKey(String emp_no)
	{
		// TODO Auto-generated method stub
		Set<Emp_authVO> list = new LinkedHashSet<Emp_authVO>();
		Emp_authVO emp_authVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ResultSetMetaData rsmd = null; 
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,emp_no);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				emp_authVO = new Emp_authVO();
				emp_authVO.setEmp_no(rs.getString("emp_no"));
				emp_authVO.setAuth_no(rs.getString("auth_no"));
				list.add(emp_authVO);
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

	public List<String> getAuthsbyPrimaryKey(String emp_no)
	{
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,emp_no);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				list.add(rs.getString("auth_no"));
			}
		}  catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
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
	public List<Emp_authVO> getAll()
	{
		// TODO Auto-generated method stub
		List<Emp_authVO> list = new ArrayList<Emp_authVO>();
		Emp_authVO emp_authVO =null;
		
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
				emp_authVO = new Emp_authVO();
				emp_authVO.setEmp_no(rs.getString("emp_no"));
				emp_authVO.setAuth_no(rs.getString("auth_no"));
				
				list.add(emp_authVO);
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
		Emp_authJDBCDAO dao = new Emp_authJDBCDAO();
		
		//insert
//		Emp_authVO emp_authVO1 = new Emp_authVO();
//		emp_authVO1.setEmp_no("1004");
//		emp_authVO1.setAuth_no("005");
//		
//		dao.insert(emp_authVO1);
		
		//update
//		Emp_authVO emp_authVO2 = new Emp_authVO();
//		emp_authVO2.setTarget_auth_no("002");
//		emp_authVO2.setEmp_no("1001");
//		emp_authVO2.setAuth_no("005");
//		
//		dao.update(emp_authVO2);
		
		//delete
//		dao.delete("1002");
		
		//delete one auth
//		dao.deleteOneAuth("1001", "001");
		
		//search target
//		Set<Emp_authVO> list = dao.findByPrimaryKey("1001");
//		for(Emp_authVO emp_authVO3: list){
//			System.out.print("| "+emp_authVO3.getEmp_no()+" | ");
//			System.out.print(emp_authVO3.getAuth_no()+" | ");
//			System.out.println();
//		}
		
		//search all
//		List<Emp_authVO> list = dao.getAll();
//		for(Emp_authVO emp_authVO4: list){
//			System.out.print("| "+emp_authVO4.getEmp_no()+" | ");
//			System.out.print(emp_authVO4.getAuth_no()+" | ");
//			System.out.println();
//		}
	}
	
}
