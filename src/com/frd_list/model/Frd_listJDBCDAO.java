package com.frd_list.model;

import java.util.*;
import java.sql.*;

public class Frd_listJDBCDAO implements Frd_listDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "foodtime";
	String psw = "foodtime";

	private static final String INSERT_STMT = "INSERT INTO frd_list (mem_no, friend_no, friend_chk) VALUES (?, ?, ?)";
	private static final String Get_ALL_STMT = "select mem_no, friend_no, friend_chk from frd_list order by mem_no";
	private static final String GET_ONE_STMT = "select mem_no, friend_no, friend_chk from frd_list where mem_no = ?";	
	private static final String DELETE = "DELETE FROM frd_list where mem_no = ?";
	private static final String UPDATE = "UPDATE frd_list set  friend_chk = ? where mem_no = ? and friend_no = ? ";
	private static final String DELETE_ONE = "DELETE FROM frd_list where mem_no = ? and friend_no = ?";
	private static final String GET_ONE_STMT_TWO_KEY = "select mem_no, friend_no, friend_chk from frd_list where mem_no = ? and friend_no = ?";
	private static final String GET_ALL_BY_MEM_NO = "select mem_no, friend_no, friend_chk from frd_list where mem_no = ?";

	@Override
	public void insert(Frd_listVO frd_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			// pstmt.setString(1, frd_listVO.getColl_no());
			pstmt.setString(1, frd_listVO.getMem_no());
			pstmt.setString(2, frd_listVO.getFriend_no());
			pstmt.setString(3, frd_listVO.getFriend_chk());

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
	public void update(Frd_listVO frd_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, frd_listVO.getFriend_chk());
			pstmt.setString(2, frd_listVO.getMem_no());
			pstmt.setString(3, frd_listVO.getFriend_no());

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
	public void delete(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);
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
	public void deleteOne(Frd_listVO frd_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(DELETE_ONE);

			pstmt.setString(1, frd_listVO.getMem_no());
			pstmt.setString(2, frd_listVO.getFriend_no());
			
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
	public Frd_listVO findByPrimaryKey(String mem_no) {
		Frd_listVO frd_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				frd_listVO = new Frd_listVO();
				
				frd_listVO.setFriend_no(rs.getString("friend_no"));
				frd_listVO.setFriend_chk(rs.getString("friend_chk"));
				frd_listVO.setMem_no(rs.getString("mem_no"));
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
		return frd_listVO;
	}
	
	@Override
	public Frd_listVO findByTwoPrimaryKey(String mem_no, String friend_no) {
		Frd_listVO frd_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT_TWO_KEY);

			pstmt.setString(1, mem_no);
			pstmt.setString(2, friend_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				frd_listVO = new Frd_listVO();
				
				frd_listVO.setFriend_no(rs.getString("friend_no"));
				frd_listVO.setFriend_chk(rs.getString("friend_chk"));
				frd_listVO.setMem_no(rs.getString("mem_no"));
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
		return frd_listVO;
	}


	@Override
	public List<Frd_listVO> getAll() {
		List<Frd_listVO> list = new ArrayList<Frd_listVO>();
		Frd_listVO frd_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				frd_listVO = new Frd_listVO();
				frd_listVO.setMem_no(rs.getString("mem_no"));
				frd_listVO.setFriend_no(rs.getString("friend_no"));
				frd_listVO.setFriend_chk(rs.getString("friend_chk"));
				list.add(frd_listVO);
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
	public List<Frd_listVO> findByMem_no(String mem_no) {
		List<Frd_listVO> list = new ArrayList<Frd_listVO>();
		Frd_listVO frd_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ALL_BY_MEM_NO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				frd_listVO = new Frd_listVO();
				frd_listVO.setMem_no(rs.getString("mem_no"));
				frd_listVO.setFriend_no(rs.getString("friend_no"));
				frd_listVO.setFriend_chk(rs.getString("friend_chk"));
				list.add(frd_listVO);
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
		Frd_listJDBCDAO dao = new Frd_listJDBCDAO();
		//
		// // insert
//		 Frd_listVO frd_listVO1 = new Frd_listVO();
//		
//		 frd_listVO1.setMem_no("M00000006");
//		 frd_listVO1.setFriend_no("C00000003");
//		 frd_listVO1.setFriend_chk("1");
//		 dao.insert(frd_listVO1);

		// update
//		 Frd_listVO frd_listVO2 = new Frd_listVO();
//		 frd_listVO2.setMem_no("M00000001");
//		 frd_listVO2.setFriend_no("M00000003");
//		 frd_listVO2.setFriend_chk("1");
//		 dao.update(frd_listVO2);

		// delete
//		 dao.delete("M00000001");
		 
		 //delete one
//		 Frd_listVO frd_listVO2 = new Frd_listVO();
//		 frd_listVO2.setMem_no("M00000001");
//		 frd_listVO2.setFriend_no("M00000002");
//		 dao.deleteOne(frd_listVO2);

		// // search target
//		 Frd_listVO frd_listVO3 = dao.findByPrimaryKey("M00000001");
//		 System.out.print(frd_listVO3.getMem_no() + " | ");
//		 System.out.print(frd_listVO3.getFriend_no() + " | ");
//		 System.out.print(frd_listVO3.getFriend_chk() + " | ");
		
		// // search two target
//		 Frd_listVO frd_listVO5 = dao.findByTwoPrimaryKey("M00000001","M00000003");
//		 System.out.print(frd_listVO5.getMem_no() + " | ");
//		 System.out.print(frd_listVO5.getFriend_no() + " | ");
//		 System.out.print(frd_listVO5.getFriend_chk() + " | ");

		
		// search all by one key
//		List<Frd_listVO> list = dao.findByMem_no("M00000001");
//		for (Frd_listVO frd_listVO4 : list) {
//			System.out.print(frd_listVO4.getMem_no() + " | ");
//			System.out.print(frd_listVO4.getFriend_no() + " | ");
//			System.out.print(frd_listVO4.getFriend_chk() + " | ");
//			System.out.println();
//		}
		
		// search all
//		List<Frd_listVO> list = dao.getAll();
//		for (Frd_listVO frd_listVO4 : list) {
//			System.out.print(frd_listVO4.getMem_no() + " | ");
//			System.out.print(frd_listVO4.getFriend_no() + " | ");
//			System.out.print(frd_listVO4.getFriend_chk() + " | ");
//			System.out.println();
//		}
	}

}
