package atm1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDao {
	//连接
	public Connection getCon(){
		Connection conn=null;
		try {
			//1.驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.连接
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr", "Oracle");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	//关闭
		public void closeCon(Statement stat,Connection conn){
			try {
				stat.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		public void closeCon(Statement stat,Connection conn,ResultSet rs){
			try {
				rs.close();
				stat.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		public int excuteUpdate(String sql, Object[] obj) {
			int ret=0;
			Connection conn=null;
			PreparedStatement stat=null;
			conn=getCon();
			try {
				stat=conn.prepareStatement(sql);
				for (int i = 0; i < obj.length; i++) {
					stat.setObject(i+1,obj[i]);
				}
				ret=stat.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return ret;
		}
}
