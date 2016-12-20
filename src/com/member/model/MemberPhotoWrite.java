package com.member.model;

import java.sql.*;
import java.io.*;

public class MemberPhotoWrite {
	
	static {
        try {
             Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    public static void main(String argv[]) {
          Connection con = null;
          PreparedStatement pstmt = null;
          String url = "jdbc:oracle:thin:@localhost:1521:XE";
          String userid = "foodtime";
          String passwd = "foodtime";
        
          for(int i = 1; i <= 5; i++){
        	  
        	  String photo = "0" + i + ".png";
        	  
        	  try {
        		  con = DriverManager.getConnection(url, userid, passwd);
        		  File pic = new File("C:\\Users\\cuser\\Desktop\\DBimages\\Member", photo); //相對路徑- picFrom
                                                     //絕對路徑- 譬如:
                                                     //File pic = new File("x:\\aa\\bb\\picFrom", picName);
        		  long flen = pic.length();
            
        		  InputStream fin = new FileInputStream(pic);  

        		  pstmt = con.prepareStatement("update Member set mem_image=? where mem_no=?");

        		  pstmt.setBinaryStream(1, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
        		  pstmt.setString(2, "M0000000" + i);
        		  int count = pstmt.executeUpdate();
        		  System.out.println(count);
        		  fin.close();
        		  pstmt.close();

        	  } catch (Exception e) {
        		  e.printStackTrace();
        	  } finally {
        		  try {
        			  con.close();
        		  } catch (SQLException e) {
        		  }
        	  }
          }
	}

}
