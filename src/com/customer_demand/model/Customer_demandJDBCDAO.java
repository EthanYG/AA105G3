package com.customer_demand.model;

import java.util.*;

import java.sql.*;

public class Customer_demandJDBCDAO implements Customer_demandDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "foodtime";
	String psw = "foodtime";

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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, customer_demandVO.getMem_no());
			pstmt.setString(2, customer_demandVO.getCusde_detail());
			pstmt.setTimestamp(3, customer_demandVO.getCusde_date());
			pstmt.setTimestamp(4, customer_demandVO.getCusde_create_date());
			pstmt.setString(5, customer_demandVO.getCusde_title());

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, customer_demandVO.getMem_no());
			pstmt.setString(2, customer_demandVO.getCusde_detail());
			pstmt.setTimestamp(3, customer_demandVO.getCusde_date());
			pstmt.setString(4, customer_demandVO.getCusde_title());
			pstmt.setString(5, customer_demandVO.getCusde_no());

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
	public void delete(String cusde_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cusde_no);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
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

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public static void main(String[] args) {
		Customer_demandJDBCDAO dao = new Customer_demandJDBCDAO();

		// insert
		// Customer_demandVO customer_demandVO1 = new Customer_demandVO();
		// customer_demandVO1.setMem_no("M00000004");
		// customer_demandVO1.setCusde_detail("這是客戶需求新增測試嘿嘿嘿");
		// customer_demandVO1.setCusde_date(java.sql.Timestamp.valueOf("2016-04-11
		// 22:33:44"));
		// customer_demandVO1.setCusde_create_date(java.sql.Timestamp.valueOf("2016-04-11
		// 11:22:33"));
		// customer_demandVO1.setCusde_title("客戶需求標題");
		// dao.insert(customer_demandVO1);

		// update
		 Customer_demandVO customer_demandVO2 = new Customer_demandVO();
		 customer_demandVO2.setCusde_no("CN4");
		 customer_demandVO2.setMem_no("M00000005");
		 customer_demandVO2.setCusde_detail("這是客戶需求修改測試內容");
		 customer_demandVO2.setCusde_date(java.sql.Timestamp.valueOf("2016-04-12 10:55:33"));
		 customer_demandVO2.setCusde_title("修改標題測試");
		 dao.update(customer_demandVO2);

		// delete
		// dao.delete("CN5");

		// search target
//		 Customer_demandVO customer_demandVO3 = dao.findByPrimaryKey("CN5");
//		 System.out.print("| " + customer_demandVO3.getCusde_no() + " | ");
//		 System.out.print(customer_demandVO3.getCusde_no() + " | ");
//		 System.out.print(customer_demandVO3.getMem_no() + " | ");
//		 System.out.print(customer_demandVO3.getCusde_detail() + " | ");
//		 System.out.print(customer_demandVO3.getCusde_date() + " | ");
//		 System.out.print(customer_demandVO3.getCusde_create_date() + " | ");
//		 System.out.print(customer_demandVO3.getCusde_title() + " | ");

		// search all by mem_no
//		List<Customer_demandVO> list = dao.findByMem_no("M00000002");
//		for (Customer_demandVO customer_demandVO4 : list) {
//			System.out.print(customer_demandVO4.getCusde_no() + " | ");
//			System.out.print(customer_demandVO4.getMem_no() + " | ");
//			System.out.print(customer_demandVO4.getCusde_detail() + " | ");
//			System.out.print(customer_demandVO4.getCusde_date() + " | ");
//			System.out.print(customer_demandVO4.getCusde_create_date() + " | ");
//			System.out.print(customer_demandVO4.getCusde_title() + " | ");
//			System.out.println();
//		}

		// search all
		// List<Customer_demandVO> list = dao.getAll();
		// for (Customer_demandVO customer_demandVO4 : list) {
		// System.out.print("| " + customer_demandVO4.getCusde_no() + " | ");
		// System.out.print(customer_demandVO4.getCusde_no() + " | ");
		// System.out.print(customer_demandVO4.getMem_no() + " | ");
		// System.out.print(customer_demandVO4.getCusde_detail() + " | ");
		// System.out.print(customer_demandVO4.getCusde_date() + " | ");
		// System.out.print(customer_demandVO4.getCusde_create_date() + " | ");
		// System.out.print(customer_demandVO4.getCusde_title() + " | ");
		// System.out.println();
		// }
	}
}
