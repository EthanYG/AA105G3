package com.collection.model;

import java.util.*;
import java.sql.*;

public class CollectionJDBCDAO implements CollectionDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "foodtime";
	String psw = "foodtime";

	private static final String INSERT_STMT = "INSERT INTO collection (coll_no, mem_no, all_no, class_no) VALUES ('CO'||collection_seq.NEXTVAL, ?, ?, ?)";
	private static final String Get_ALL_STMT = "select coll_no, mem_no, all_no, class_no from collection order by coll_no";
	private static final String GET_ONE_STMT = "select coll_no, mem_no, all_no, class_no from collection where coll_no = ?";
	private static final String DELETE = "DELETE FROM collection where coll_no = ?";
	private static final String UPDATE = "UPDATE collection set mem_no = ?, all_no = ?, class_no = ? where coll_no = ? ";
	private static final String GET_ALL_BY_MEM_NO = "select coll_no, mem_no, all_no, class_no from collection where mem_no = ?";

	@Override
	public void insert(CollectionVO collectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(INSERT_STMT);

			// pstmt.setString(1, collectionVO.getColl_no());
			pstmt.setString(1, collectionVO.getMem_no());
			pstmt.setString(2, collectionVO.getAll_no());
			pstmt.setString(3, collectionVO.getClass_no());

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
	public void update(CollectionVO collectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, collectionVO.getMem_no());
			pstmt.setString(2, collectionVO.getAll_no());
			pstmt.setString(3, collectionVO.getClass_no());
			pstmt.setString(4, collectionVO.getColl_no());

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
	public void delete(String coll_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coll_no);
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
	public CollectionVO findByPrimaryKey(String coll_no) {
		CollectionVO collectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, coll_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				collectionVO = new CollectionVO();
				collectionVO.setColl_no(rs.getString("coll_no"));
				collectionVO.setMem_no(rs.getString("mem_no"));
				collectionVO.setAll_no(rs.getString("all_no"));
				collectionVO.setClass_no(rs.getString("class_no"));
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
		return collectionVO;
	}
	
	@Override
	public List<CollectionVO> findByMem_no(String mem_no) {
		List<CollectionVO> list = new ArrayList<CollectionVO>();
		CollectionVO collectionVO = null;
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
				collectionVO = new CollectionVO();
				collectionVO.setColl_no(rs.getString("coll_no"));
				collectionVO.setMem_no(rs.getString("mem_no"));
				collectionVO.setAll_no(rs.getString("all_no"));
				collectionVO.setClass_no(rs.getString("class_no"));
				list.add(collectionVO);
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
	public List<CollectionVO> getAll() {
		List<CollectionVO> list = new ArrayList<CollectionVO>();
		CollectionVO collectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, psw);
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				collectionVO = new CollectionVO();
				collectionVO.setColl_no(rs.getString("coll_no"));
				collectionVO.setMem_no(rs.getString("mem_no"));
				collectionVO.setAll_no(rs.getString("all_no"));
				collectionVO.setClass_no(rs.getString("class_no"));
				list.add(collectionVO);
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
		CollectionJDBCDAO dao = new CollectionJDBCDAO();
		
		 // insert
//		 CollectionVO collectionVO1 = new CollectionVO();
//		
//		 collectionVO1.setMem_no("M00000002");
//		 collectionVO1.setAll_no("R00000003");
//		 collectionVO1.setClass_no("R");
//		 dao.insert(collectionVO1);

		// update
//		 CollectionVO collectionVO2 = new CollectionVO();
//		 collectionVO2.setColl_no("CO5");
//		 collectionVO2.setMem_no("M00000001");
//		 collectionVO2.setAll_no("R00000002");
//		 collectionVO2.setClass_no("R");
//		 dao.update(collectionVO2);

		// delete
//		 dao.delete("CO6");

		// // search target
//		 CollectionVO collectionVO3 = dao.findByPrimaryKey("CO5");
//		 System.out.print("| " + collectionVO3.getColl_no() + " | ");
//		 System.out.print(collectionVO3.getColl_no() + " | ");
//		 System.out.print(collectionVO3.getMem_no() + " | ");
//		 System.out.print(collectionVO3.getAll_no() + " | ");
//		 System.out.print(collectionVO3.getClass_no() + " | ");
		
		// search all by mem_no
//		List<CollectionVO> list = dao.findByMem_no("M00000001");
//		for (CollectionVO collectionVO4 : list) {
//			System.out.print(collectionVO4.getColl_no() + " | ");
//			System.out.print(collectionVO4.getMem_no() + " | ");
//			System.out.print(collectionVO4.getAll_no() + " | ");
//			System.out.print(collectionVO4.getClass_no() + " | ");
//			System.out.println();
//		}

		// search all
//		List<CollectionVO> list = dao.getAll();
//		for (CollectionVO collectionVO4 : list) {
//			System.out.print("| " + collectionVO4.getColl_no() + " | ");
//			System.out.print(collectionVO4.getColl_no() + " | ");
//			System.out.print(collectionVO4.getMem_no() + " | ");
//			System.out.print(collectionVO4.getAll_no() + " | ");
//			System.out.print(collectionVO4.getClass_no() + " | ");
//			System.out.println();
//		}
	}
}
