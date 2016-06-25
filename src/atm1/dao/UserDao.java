package atm1.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import atm1.entity.User;

public class UserDao extends BaseDao{
	//连接
	
     //添加
	public int save(User user){
		return excuteUpdate("insert into t_user(id,username, password) values (?,?,?)", new Object[]{user.getId(),user.getUsername(),user.getPassword()});
	}
        //删除
	public int delete(int id){
		return excuteUpdate("delete t_user where id=?",new Object[]{id});
	}
        //更改
	public int update(int id1,int id2){
		return excuteUpdate("update t_user set id=? where id=?", new Object[]{id1,id2});
	}
	public List<User> search(){
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		List<User>  userList = new ArrayList<User>();
		try {
			conn=getCon();
			stat=conn.createStatement();
			rs=stat.executeQuery("select * from t_user");
			while(rs.next()){
				//把数据库的行导入user类
				User user=new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString(3));
				//把类加入到集合中
				userList.add(user);
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			closeCon(stat, conn,rs);
		}
		return userList;
	}
	public static void main(String[] args) {
		UserDao userDao=new UserDao();
	userDao.save(new User(1,"q","235"));
		userDao.delete(1);
		userDao.update(6,10);
		List<User> list=userDao.search();
		for (User user : list) {
			System.out.println(user);
		}		
	}
}

