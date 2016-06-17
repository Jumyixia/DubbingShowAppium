/**
 * 
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelperDubbingAccount {
	public static final String url = "jdbc:mysql://114.215.209.105/dubbing_account";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "dubbing";
	public static final String password = "dubbing_Dubbing";

	public Connection conn = null;
	public PreparedStatement pst = null;

	public DBHelperDubbingAccount(String sql) {
		try {
			Class.forName(name);//指定连接类型
			conn = DriverManager.getConnection(url, user, password);//获取连接
			pst = conn.prepareStatement(sql);//准备执行语句
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

