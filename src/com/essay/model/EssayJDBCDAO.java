package com.essay.model;

import java.util.*;
import java.sql.*;

public class EssayJDBCDAO implements EssayDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "foodtime";
	String psw = "foodtime";

	private static final String INSERT_STMT = "INSERT INTO essay (esa_no, mem_no, esa_detail, esa_title, esa_date, esa_pop) VALUES ('E'||lpad(essay_seq.NEXTVAL,8,0), ?, ?, ?, ?, ?)";
	private static final String Get_ALL_STMT = "select esa_no, mem_no, esa_detail, esa_title, esa_date, esa_pop from essay order by esa_no";
	private static final String GET_ONE_STMT = "select esa_no, mem_no, esa_detail, esa_title, esa_date, esa_pop from essay where esa_no = ?";
	private static final String DELETE = "DELETE FROM essay where esa_no = ?";
	private static final String UPDATE = "UPDATE essay set mem_no = ?, esa_detail = ?, esa_title = ?, esa_date = sysdate, esa_pop = ? where esa_no = ? ";

	@Override
	public void insert(EssayVO essayVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, essayVO.getMem_no());
			pstmt.setString(2, essayVO.getEsa_detail());
			pstmt.setString(3, essayVO.getEsa_title());
			pstmt.setTimestamp(4, essayVO.getEsa_date());
			pstmt.setInt(5, essayVO.getEsa_pop());

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
	public void update(EssayVO essayVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, essayVO.getMem_no());
			pstmt.setString(2, essayVO.getEsa_detail());
			pstmt.setString(3, essayVO.getEsa_title());
			pstmt.setInt(4, essayVO.getEsa_pop());
			pstmt.setString(5, essayVO.getEsa_no());

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
	public void delete(String esa_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, esa_no);
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
	public EssayVO findByPrimaryKey(String esa_no) {
		EssayVO essayVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, esa_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				essayVO = new EssayVO();
				essayVO.setEsa_no(rs.getString("esa_no"));
				essayVO.setMem_no(rs.getString("mem_no"));
				essayVO.setEsa_detail(rs.getString("esa_detail"));
				essayVO.setEsa_title(rs.getString("esa_title"));
				essayVO.setEsa_date(rs.getTimestamp("esa_date"));
				essayVO.setEsa_pop(rs.getInt("esa_pop"));
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
		return essayVO;
	}

	@Override
	public List<EssayVO> getAll() {
		List<EssayVO> list = new ArrayList<EssayVO>();
		EssayVO essayVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				essayVO = new EssayVO();
				essayVO.setEsa_no(rs.getString("esa_no"));
				essayVO.setMem_no(rs.getString("mem_no"));
				essayVO.setEsa_detail(rs.getString("esa_detail"));
				essayVO.setEsa_title(rs.getString("esa_title"));
				essayVO.setEsa_date(rs.getTimestamp("esa_date"));
				essayVO.setEsa_pop(rs.getInt("esa_pop"));
				list.add(essayVO);
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
		EssayJDBCDAO dao = new EssayJDBCDAO();
		
		// insert
//		 EssayVO essayVO1 = new EssayVO();		
//		 essayVO1.setMem_no("M00000001");
//		 essayVO1.setEsa_detail("這是插入的測試哈哈哈");
//		 essayVO1.setEsa_title("插入title試");
//		 essayVO1.setEsa_date(java.sql.Timestamp.valueOf("2016-04-11 01:23:45"));
//		 essayVO1.setEsa_pop(666);
//		 dao.insert(essayVO1);

		// update
//		 EssayVO essayVO2 = new EssayVO();
//		 essayVO2.setEsa_no("E00000005");
//		 essayVO2.setMem_no("M00000005");
//		 essayVO2.setEsa_detail("這是修改測試內容");
//		 essayVO2.setEsa_title("這是修改測試標題");
//		 essayVO2.setEsa_pop(8);
//		 dao.update(essayVO2);

		// delete
//		dao.delete("E00000005");
		
		// search target
		 EssayVO essayVO3 = dao.findByPrimaryKey("E00000001");
		 System.out.print("| " + essayVO3.getEsa_no() + " | ");
		 System.out.print(essayVO3.getEsa_no() + " | ");
		 System.out.print(essayVO3.getMem_no() + " | ");
		 System.out.print(essayVO3.getEsa_detail() + " | ");
		 System.out.print(essayVO3.getEsa_title() + " | ");
		 System.out.print(essayVO3.getEsa_date() + " | ");
		 System.out.print(essayVO3.getEsa_pop() + " | ");

		// search all
//		 List<EssayVO> list = dao.getAll();
//		 for (EssayVO essayVO4 : list) {
//		 System.out.print("| " + essayVO4.getEsa_no() + " | ");
//		 System.out.print(essayVO4.getEsa_no() + " | ");
//		 System.out.print(essayVO4.getMem_no() + " | ");
//		 System.out.print(essayVO4.getEsa_detail() + " | ");
//		 System.out.print(essayVO4.getEsa_title() + " | ");
//		 System.out.print(essayVO4.getEsa_date() + " | ");
//		 System.out.print(essayVO4.getEsa_pop() + " | ");
//		 System.out.println();
//		 }
	}
}