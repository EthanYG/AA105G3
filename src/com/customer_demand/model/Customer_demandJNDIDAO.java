package com.customer_demand.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class Customer_demandJNDIDAO implements Customer_demandDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FoodTimeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO customer_demand (cusde_no, mem_no, cusde_detail, cusde_date, cusde_create_date, cusde_title) VALUES ('CN'||customer_demand_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String Get_ALL_STMT = "select cusde_no, mem_no, cusde_detail, cusde_date, cusde_create_date, cusde_title from customer_demand order by cusde_no";
	private static final String GET_ONE_STMT = "select cusde_no, mem_no, cusde_detail, cusde_date, cusde_create_date, cusde_title from customer_demand where cusde_no = ?";
	private static final String DELETE = "DELETE FROM customer_demand where cusde_no = ?";
	private static final String UPDATE = "UPDATE customer_demand set mem_no = ?, cusde_detail = ?, cusde_date = ?, cusde_create_date = sysdate, cusde_title = ? where cusde_no = ? ";
	private static final String GET_ALL_BY_MEM_NO = "select cusde_no, mem_no, cusde_detail, cusde_date, cusde_create_date, cusde_title from customer_demand where mem_no = ?";

	@Override
	public void insert(Customer_demandVO customer_demandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, customer_demandVO.getMem_no());
			pstmt.setString(2, customer_demandVO.getCusde_detail());
			pstmt.setTimestamp(3, customer_demandVO.getCusde_date());
			pstmt.setTimestamp(4, customer_demandVO.getCusde_create_date());
			pstmt.setString(5, customer_demandVO.getCusde_title());

			pstmt.executeUpdate();

		}  catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(Customer_demandVO customer_demandVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, customer_demandVO.getMem_no());
			pstmt.setString(2, customer_demandVO.getCusde_detail());
			pstmt.setTimestamp(3, customer_demandVO.getCusde_date());
			pstmt.setString(4, customer_demandVO.getCusde_title());
			pstmt.setString(5, customer_demandVO.getCusde_no());

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
	public void delete(String cusde_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cusde_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Customer_demandVO findByPrimaryKey(String cusde_no) {
		Customer_demandVO customer_demandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, cusde_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				customer_demandVO = new Customer_demandVO();
				customer_demandVO.setCusde_no(rs.getString("cusde_no"));
				customer_demandVO.setMem_no(rs.getString("mem_no"));
				customer_demandVO.setCusde_detail(rs.getString("cusde_detail"));
				customer_demandVO.setCusde_date(rs.getTimestamp("cusde_date"));
				customer_demandVO.setCusde_create_date(rs.getTimestamp("cusde_create_date"));
				customer_demandVO.setCusde_title(rs.getString("cusde_title"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return customer_demandVO;
	}

	@Override
	public List<Customer_demandVO> findByMem_no(String mem_no) {
		List<Customer_demandVO> list = new ArrayList<Customer_demandVO>();
		Customer_demandVO customer_demandVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEM_NO);

			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				customer_demandVO = new Customer_demandVO();
				customer_demandVO.setCusde_no(rs.getString("Cusde_no"));
				customer_demandVO.setMem_no(rs.getString("mem_no"));
				customer_demandVO.setCusde_detail(rs.getString("Cusde_detail"));
				customer_demandVO.setCusde_date(rs.getTimestamp("Cusde_date"));
				customer_demandVO.setCusde_create_date(rs.getTimestamp("cusde_create_date"));
				customer_demandVO.setCusde_title(rs.getString("cusde_title"));
				list.add(customer_demandVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<Customer_demandVO> getAll() {
		List<Customer_demandVO> list = new ArrayList<Customer_demandVO>();
		Customer_demandVO customer_demandVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				customer_demandVO = new Customer_demandVO();
				customer_demandVO.setCusde_no(rs.getString("cusde_no"));
				customer_demandVO.setMem_no(rs.getString("mem_no"));
				customer_demandVO.setCusde_detail(rs.getString("cusde_detail"));
				customer_demandVO.setCusde_date(rs.getTimestamp("cusde_date"));
				customer_demandVO.setCusde_create_date(rs.getTimestamp("cusde_create_date"));
				customer_demandVO.setCusde_title(rs.getString("cusde_title"));
				list.add(customer_demandVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
