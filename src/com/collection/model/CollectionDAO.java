package com.collection.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class CollectionDAO implements CollectionDAO_interface{
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
			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);

			// pstmt.setString(1, collectionVO.getColl_no());
			pstmt.setString(1, collectionVO.getMem_no());
			pstmt.setString(2, collectionVO.getAll_no());
			pstmt.setString(3, collectionVO.getClass_no());

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
	public void update(CollectionVO collectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, collectionVO.getMem_no());
			pstmt.setString(2, collectionVO.getAll_no());
			pstmt.setString(3, collectionVO.getClass_no());
			pstmt.setString(4, collectionVO.getColl_no());

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
	public void delete(String coll_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coll_no);
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
	public CollectionVO findByPrimaryKey(String coll_no) {
		CollectionVO collectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			con = ds.getConnection();

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
		}  catch (SQLException e) {
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
			con = ds.getConnection();
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
			con = ds.getConnection();

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
