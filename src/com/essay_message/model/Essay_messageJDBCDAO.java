package com.essay_message.model;

import java.util.*;
import java.sql.*;

public class Essay_messageJDBCDAO implements Essay_messageDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "foodtime";
	String psw = "foodtime";

	private static final String INSERT_STMT = "INSERT INTO essay_message (esamsg_no, mem_no, esa_no, esa_rel, esamsg_detail, esamsg_date) VALUES ('RE'||essay_message_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String Get_ALL_STMT = "select esamsg_no, mem_no, esa_no, esa_rel, esamsg_detail, esamsg_date from essay_message order by esamsg_no";
	private static final String GET_ONE_STMT = "select esamsg_no, mem_no, esa_no, esa_rel, esamsg_detail, esamsg_date from essay_message where esamsg_no = ?";
	private static final String DELETE = "DELETE FROM essay_message where esamsg_no = ?";
	private static final String UPDATE = "UPDATE essay_message set mem_no = ?, esa_no = ?, esa_rel = ?, esamsg_detail = ?, esamsg_date = sysdate where esamsg_no = ? ";

	@Override
	public void insert(Essay_messageVO essay_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			// pstmt.setString(1, essay_messageVO.getEsamsg_no());
			pstmt.setString(1, essay_messageVO.getMem_no());
			pstmt.setString(2, essay_messageVO.getEsa_no());
			pstmt.setString(3, essay_messageVO.getEsa_rel());
			pstmt.setString(4, essay_messageVO.getEsamsg_detail());
			pstmt.setTimestamp(5, essay_messageVO.getEsamsg_date());

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
	public void update(Essay_messageVO essay_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, essay_messageVO.getMem_no());
			pstmt.setString(2, essay_messageVO.getEsa_no());
			pstmt.setString(3, essay_messageVO.getEsa_rel());
			pstmt.setString(4, essay_messageVO.getEsamsg_detail());
			pstmt.setString(5, essay_messageVO.getEsamsg_no());
			// pstmt.setTimestamp(6, essay_messageVO.getEsamsg_date());

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
	public void delete(String esamsg_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, esamsg_no);
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
	public Essay_messageVO findByPrimaryKey(String esamsg_no) {
		Essay_messageVO essay_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, esamsg_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				essay_messageVO = new Essay_messageVO();
				essay_messageVO.setEsamsg_no(rs.getString("esamsg_no"));
				essay_messageVO.setMem_no(rs.getString("mem_no"));
				essay_messageVO.setEsa_no(rs.getString("esa_no"));
				essay_messageVO.setEsa_rel(rs.getString("esa_rel"));
				essay_messageVO.setEsamsg_detail(rs.getString("esamsg_detail"));
				essay_messageVO.setEsamsg_date(rs.getTimestamp("esamsg_date"));
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
		return essay_messageVO;
	}

	@Override
	public List<Essay_messageVO> getAll() {
		List<Essay_messageVO> list = new ArrayList<Essay_messageVO>();
		Essay_messageVO essay_messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				essay_messageVO = new Essay_messageVO();
				essay_messageVO.setEsamsg_no(rs.getString("esamsg_no"));
				essay_messageVO.setMem_no(rs.getString("mem_no"));
				essay_messageVO.setEsa_no(rs.getString("esa_no"));
				essay_messageVO.setEsa_rel(rs.getString("esa_rel"));
				essay_messageVO.setEsamsg_detail(rs.getString("esamsg_detail"));
				essay_messageVO.setEsamsg_date(rs.getTimestamp("esamsg_date"));
				list.add(essay_messageVO);
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
		Essay_messageJDBCDAO dao = new Essay_messageJDBCDAO();
		
		 // insert
//		 Essay_messageVO essay_messageVO1 = new Essay_messageVO();
//		
//		 essay_messageVO1.setMem_no("M00000005");
//		 essay_messageVO1.setEsa_no("E00000001");
//		 essay_messageVO1.setEsa_rel("RE1");
//		 essay_messageVO1.setEsamsg_detail("Qoo有種果汁真好喝6");
//		 essay_messageVO1.setEsamsg_date(java.sql.Timestamp.valueOf("2016-04-11 23:45:16"));
//		 dao.insert(essay_messageVO1);

		// update
//		 Essay_messageVO essay_messageVO2 = new Essay_messageVO();
//		 essay_messageVO2.setEsamsg_no("RE5");
//		 essay_messageVO2.setMem_no("M00000005");
//		 essay_messageVO2.setEsa_no("E00000001");
//		 essay_messageVO2.setEsa_rel("RE1");
//		 essay_messageVO2.setEsamsg_detail("修改8");
//		 dao.update(essay_messageVO2);

		// delete
//		dao.delete("RE6");
		
		// // search target
		 Essay_messageVO essay_messageVO3 = dao.findByPrimaryKey("RE2");
		 System.out.print("| " + essay_messageVO3.getEsamsg_no() + " | ");
		 System.out.print(essay_messageVO3.getEsamsg_no() + " | ");
		 System.out.print(essay_messageVO3.getMem_no() + " | ");
		 System.out.print(essay_messageVO3.getEsa_no() + " | ");
		 System.out.print(essay_messageVO3.getEsa_rel() + " | ");
		 System.out.print(essay_messageVO3.getEsamsg_detail() + " | ");
		 System.out.print(essay_messageVO3.getEsamsg_date() + " | ");

		// search all
//		 List<Essay_messageVO> list = dao.getAll();
//		 for (Essay_messageVO essay_messageVO4 : list) {
//		 System.out.print("| " + essay_messageVO4.getEsamsg_no() + " | ");
//		 System.out.print(essay_messageVO4.getMem_no() + " | ");
//		 System.out.print(essay_messageVO4.getEsa_no() + " | ");
//		 System.out.print(essay_messageVO4.getEsa_rel() + " | ");
//		 System.out.print(essay_messageVO4.getEsamsg_detail() + " | ");
//		 System.out.print(essay_messageVO4.getEsamsg_date() + " | ");
//		 System.out.println();
//		 }
	}
}
