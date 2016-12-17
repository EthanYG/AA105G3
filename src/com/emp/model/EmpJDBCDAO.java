package com.emp.model;

import java.util.*;
import java.sql.*;

public class EmpJDBCDAO implements EmpDAO_interface
{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String psw = "foodtime";

	private static final String INSERT_STMT = 
			"INSERT INTO emp (emp_no,emp_name,emp_account,emp_password,emp_id,emp_email,emp_address,emp_phone,"
			+ "emp_hiredate,emp_job) VALUES (emp_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String Get_ALL_STMT = 
			"select emp_no,emp_name,emp_account,emp_password,emp_id,emp_email,emp_address,emp_phone,"
			+ "to_char(emp_hiredate,'yyyy-mm-dd') emp_hiredate,emp_job,emp_status from emp order by emp_no";
	private static final String GET_ONE_STMT = 
			"select emp_no,emp_name,emp_account,emp_password,emp_id,emp_email,emp_address,emp_phone,"
			+ "to_char(emp_hiredate,'yyyy-mm-dd') emp_hiredate,emp_job,emp_status from emp where emp_no = ?";
	private static final String DELETE = 
			"DELETE FROM emp where emp_no = ?";
	private static final String UPDATE = 
			"UPDATE emp set emp_name=?, emp_account=?, emp_password=?, emp_id=?, emp_email=?, emp_address=?"
			+ ", emp_phone=?, emp_hiredate=?, emp_job=?, emp_status=? where emp_no = ?";

	@Override
	public void insert(EmpVO empVO)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setString(2, empVO.getEmp_account());
			pstmt.setString(3, empVO.getEmp_password());
			pstmt.setString(4, empVO.getEmp_id());
			pstmt.setString(5, empVO.getEmp_email());
			pstmt.setString(6, empVO.getEmp_address());
			pstmt.setString(7, empVO.getEmp_phone());
			pstmt.setDate(8, empVO.getEmp_hiredate());
			pstmt.setString(9, empVO.getEmp_job());
			

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
	public void update(EmpVO empVO)
	{
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setString(2, empVO.getEmp_account());
			pstmt.setString(3, empVO.getEmp_password());
			pstmt.setString(4, empVO.getEmp_id());
			pstmt.setString(5, empVO.getEmp_email());
			pstmt.setString(6, empVO.getEmp_address());
			pstmt.setString(7, empVO.getEmp_phone());
			pstmt.setDate(8, empVO.getEmp_hiredate());
			pstmt.setString(9, empVO.getEmp_job());
			pstmt.setString(10, empVO.getEmp_status());
			pstmt.setString(11, empVO.getEmp_no());

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
	public EmpVO findByPrimaryKey(String emp_no)
	{
		EmpVO empVO =null;
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
				empVO = new EmpVO();
				empVO.setEmp_no(rs.getString("emp_no"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_account(rs.getString("emp_account"));
				empVO.setEmp_password(rs.getString("emp_password"));
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_address(rs.getString("emp_address"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_hiredate(rs.getDate("emp_hiredate"));
				empVO.setEmp_job(rs.getString("emp_job"));
				empVO.setEmp_status(rs.getString("emp_status"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll()
	{
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
		
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
				empVO = new EmpVO();
				empVO.setEmp_no(rs.getString("emp_no"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_account(rs.getString("emp_account"));
				empVO.setEmp_password(rs.getString("emp_password"));
				empVO.setEmp_id(rs.getString("emp_id"));
				empVO.setEmp_email(rs.getString("emp_email"));
				empVO.setEmp_address(rs.getString("emp_address"));
				empVO.setEmp_phone(rs.getString("emp_phone"));
				empVO.setEmp_hiredate(rs.getDate("emp_hiredate"));
				empVO.setEmp_job(rs.getString("emp_job"));
				empVO.setEmp_status(rs.getString("emp_status"));
				list.add(empVO);
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
		EmpJDBCDAO dao = new EmpJDBCDAO();
		
		//insert
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmp_name("員工6號");
//		empVO1.setEmp_account("test06");
//		empVO1.setEmp_password("psw05");
//		empVO1.setEmp_id("F123456789");
//		empVO1.setEmp_email("test06@gmail.com");
//		empVO1.setEmp_address("地球台灣");
//		empVO1.setEmp_phone("0988-666666");
//		empVO1.setEmp_hiredate(java.sql.Date.valueOf("2016-04-11"));
//		empVO1.setEmp_job("清潔員");
//		
//		
//		dao.insert(empVO1);
		
		//update
		
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmp_no("1001");
//		empVO2.setEmp_name("員工1號");
//		empVO2.setEmp_account("change01");
//		empVO2.setEmp_password("pswchange01");
//		empVO2.setEmp_id("S123456789");
//		empVO2.setEmp_email("change01@mars.com");
//		empVO2.setEmp_address("火星");
//		empVO2.setEmp_phone("6666-88888");
//		empVO2.setEmp_hiredate(java.sql.Date.valueOf("2016-11-26"));
//		empVO2.setEmp_job("網頁管理員");
//		empVO2.setEmp_status("0");
//		
//		dao.update(empVO2);
		
		//delete
//		dao.delete("1006");
		
		//search target
//		EmpVO empVO3 = dao.findByPrimaryKey(1002);
//		System.out.print("| "+empVO3.getEmp_no()+" | ");
//		System.out.print(empVO3.getEmp_name()+" | ");
//		System.out.print(empVO3.getEmp_account()+" | ");
//		System.out.print(empVO3.getEmp_password()+" | ");
//		System.out.print(empVO3.getEmp_id()+" | ");
//		System.out.print(empVO3.getEmp_email()+" | ");
//		System.out.print(empVO3.getEmp_address()+" | ");
//		System.out.print(empVO3.getEmp_phone()+" | ");
//		System.out.print(empVO3.getEmp_hiredate()+" | ");
//		System.out.print(empVO3.getEmp_job()+" | ");
//		System.out.println(empVO3.getEmp_status()+" | ");
		
		//search all
		List<EmpVO> list = dao.getAll();
		for(EmpVO empVO4: list){
			System.out.print("| "+empVO4.getEmp_no()+" | ");
			System.out.print(empVO4.getEmp_name()+" | ");
			System.out.print(empVO4.getEmp_account()+" | ");
			System.out.print(empVO4.getEmp_password()+" | ");
			System.out.print(empVO4.getEmp_id()+" | ");
			System.out.print(empVO4.getEmp_email()+" | ");
			System.out.print(empVO4.getEmp_address()+" | ");
			System.out.print(empVO4.getEmp_phone()+" | ");
			System.out.print(empVO4.getEmp_hiredate()+" | ");
			System.out.print(empVO4.getEmp_job()+" | ");
			System.out.println(empVO4.getEmp_status()+" | ");
		}
		
	}
}
