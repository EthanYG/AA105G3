package com.message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.message.model.MessageVO;

public class MessageJDBCDAO implements MessageDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "foodtime";
	String passwd = "foodtime";

	private static final String INSERT_STMT = "INSERT INTO message (msg_no,mem_no,msg_mem_no,msg_rel,msg_detail,msg_date) VALUES ('ME'||lpad(message_seq.NEXTVAL,8,0),?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT msg_no,mem_no,msg_mem_no,msg_rel,msg_detail,msg_date FROM message order by msg_no";
	private static final String GET_ONE_STMT = "SELECT msg_no,mem_no,msg_mem_no,msg_rel,msg_detail,msg_date FROM message where msg_no = ?";
	private static final String DELETE = "DELETE FROM message where msg_no = ?";
	private static final String UPDATE = "UPDATE message set msg_detail = ?, msg_date=sysdate where msg_no= ? ";

	@Override
	public void insert(MessageVO messageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, messageVO.getMem_no());
			pstmt.setString(2, messageVO.getMsg_mem_no());
			pstmt.setString(3, messageVO.getMsg_rel());
			pstmt.setString(4, messageVO.getMsg_detail());
			pstmt.setDate(5, messageVO.getMsg_date());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(MessageVO messageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, messageVO.getMsg_detail());
			pstmt.setString(2, messageVO.getMsg_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String msg_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, msg_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public MessageVO findByPrimaryKey(String msg_no) {

		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, msg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// messageVO �]�٬� Domain objects
				messageVO = new MessageVO();
				messageVO.setMsg_no(rs.getString("msg_no"));
				messageVO.setMem_no(rs.getString("mem_no"));
				messageVO.setMsg_mem_no(rs.getString("msg_mem_no"));
				messageVO.setMsg_rel(rs.getString("msg_rel"));
				messageVO.setMsg_detail(rs.getString("msg_detail"));
				messageVO.setMsg_date(rs.getDate("msg_date"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return messageVO;
	}

	@Override
	public List<MessageVO> getAll() {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// messageVO �]�٬� Domain objects
				messageVO = new MessageVO();
				messageVO.setMsg_no(rs.getString("msg_no"));
				messageVO.setMem_no(rs.getString("mem_no"));
				messageVO.setMsg_mem_no(rs.getString("msg_mem_no"));
				messageVO.setMsg_rel(rs.getString("msg_rel"));
				messageVO.setMsg_detail(rs.getString("msg_detail"));
				messageVO.setMsg_date(rs.getDate("msg_date"));
				list.add(messageVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		MessageJDBCDAO dao = new MessageJDBCDAO();

		// 新增
//		MessageVO messageVO1 = new MessageVO();
//		messageVO1.setMem_no("M00000002");
//		messageVO1.setMsg_mem_no("M00000004");
//		messageVO1.setMsg_rel("");
//		messageVO1.setMsg_detail("new massage for you");
//
//		dao.insert(messageVO1);

		// 修改
//		MessageVO messageVO2 = new MessageVO();
//		messageVO2.setMsg_detail("修改的內容");
//		messageVO2.setMsg_no("ME00000001");
//		
//
//		dao.update(messageVO2);

		// 刪除
//		dao.delete("ME00000002");

		// 查詢
		MessageVO messageVO3 = dao.findByPrimaryKey("ME00000001");
		System.out.print(messageVO3.getMsg_no() + ",");
		System.out.print(messageVO3.getMem_no() + ",");
		System.out.print(messageVO3.getMsg_mem_no() + ",");
		System.out.print(messageVO3.getMsg_rel() + ",");
		System.out.print(messageVO3.getMsg_detail() + ",");
		System.out.print(messageVO3.getMsg_date());
		System.out.println("---------------------");

		// 查詢
		List<MessageVO> list = dao.getAll();
		for (MessageVO messageVO4 : list) {
			System.out.print(messageVO4.getMsg_no() + ",");
			System.out.print(messageVO4.getMem_no() + ",");
			System.out.print(messageVO4.getMsg_mem_no() + ",");
			System.out.print(messageVO4.getMsg_rel() + ",");
			System.out.print(messageVO4.getMsg_detail() + ",");
			System.out.print(messageVO4.getMsg_date());
			System.out.println();
		}
	}
}