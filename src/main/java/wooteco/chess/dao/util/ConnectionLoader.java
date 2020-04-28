package wooteco.chess.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionLoader {
	private static final String SERVER = "127.0.0.1:13306";
	private static final String DB_NAME = "db_name";
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String NAME = "root";
	private static final String PASSWORD = "root";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	public static Connection load() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DB_NAME + OPTION, NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
		}
		return con;
	}
}
