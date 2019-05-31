package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "semi";
	private static final String PASSWORD = "project";
	
	//	DB 연결객체
	private static Connection conn = null;
	
	//	private 생성자 - 외부생성 불가
	private DBConn() { }
	
	//Connection 객체 반환 - Singleton Pattern 유지
	public static Connection getConnection() {
		
		//	DB연결된 적이 없을 경우
		if (conn == null) {
			try {
				Class.forName(DRIVER);	//	드라이버 로드
				
				//DB 연결객체 생성
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn;	//	DB연결객체 반환
	}

}
