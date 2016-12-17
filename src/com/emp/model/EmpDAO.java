package com.emp.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class EmpDAO implements EmpDAO_interface
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
			con = ds.getConnection();
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

			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, emp_no);
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
	public EmpVO findByPrimaryKey(String emp_no)
	{
		EmpVO empVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ResultSetMetaData rsmd = null; 
		try
		{
			con = ds.getConnection();
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
			con = ds.getConnection();
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
