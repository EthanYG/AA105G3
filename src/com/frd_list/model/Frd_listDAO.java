package com.frd_list.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class Frd_listDAO implements Frd_listDAO_interface{
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			// pstmt.setString(1, frd_listVO.getColl_no());
			pstmt.setString(1, frd_listVO.getMem_no());
			pstmt.setString(2, frd_listVO.getFriend_no());
			pstmt.setString(3, frd_listVO.getFriend_chk());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, frd_listVO.getFriend_chk());
			pstmt.setString(2, frd_listVO.getMem_no());
			pstmt.setString(3, frd_listVO.getFriend_no());

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
	public void delete(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);
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
	public void deleteOne(Frd_listVO frd_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ONE);

			pstmt.setString(1, frd_listVO.getMem_no());
			pstmt.setString(2, frd_listVO.getFriend_no());
			
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
	public Frd_listVO findByPrimaryKey(String mem_no) {
		Frd_listVO frd_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				frd_listVO = new Frd_listVO();
				
				frd_listVO.setFriend_no(rs.getString("friend_no"));
				frd_listVO.setFriend_chk(rs.getString("friend_chk"));
				frd_listVO.setMem_no(rs.getString("mem_no"));
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
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				frd_listVO = new Frd_listVO();
				frd_listVO.setMem_no(rs.getString("mem_no"));
				frd_listVO.setFriend_no(rs.getString("friend_no"));
				frd_listVO.setFriend_chk(rs.getString("friend_chk"));
				list.add(frd_listVO);
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
	public List<Frd_listVO> findByMem_no(String mem_no) {
		List<Frd_listVO> list = new ArrayList<Frd_listVO>();
		Frd_listVO frd_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
