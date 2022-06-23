package com.kh.mvc.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC API를 사용하면서 중복된 코드를 static 메소드로 작성
 *
 */
public class JdbcTemplate {
	
	static String driverClass;
	static String url; // db접속프로토콜@ip:포트:db명(sid)
	static String user;
	static String password;
	
	static {
		// datasource.properties의 내용을 Properties 객체로 불러오기
		Properties prop = new Properties();
		try {
			// buildpath(/WEB-INF/classes) 하위에 있는 datasource.properties의 절대경로
			// getResource메소드에 전달된 path의 /는 /WEB-INF/classes를 의미
			String filename = JdbcTemplate.class.getResource("/datasource.properties").getPath();
			System.out.println("filename@JdbcTemplete = " + filename);
			prop.load(new FileReader(filename));
			
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// driver class 등록 - application 실행 시 최초 1회만!
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Connection 객체 생성
	 * setAutoCommit(false) - 트랙잭션을 직접 관리 (DQM, DML)
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && !pstmt.isClosed())
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			//conn이 null이 아니고, 닫혀있지 않다면 커밋처리
			if(conn != null && !conn.isClosed())	// isClosed() : 반환되었다면 true리턴
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
