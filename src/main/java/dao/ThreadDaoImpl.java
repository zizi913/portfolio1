package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Thread;

public class ThreadDaoImpl implements ThreadDao{
	
	private DataSource ds;

	public ThreadDaoImpl(DataSource ds) {
		this.ds = ds;
	}


	@Override
	public List<Thread> showAll(String start, String end, int limit, int offset) {
		String sql="SELECT thread.id, member.name, thread.login_id, thread.date, thread.post FROM thread LEFT JOIN member ON thread.login_id = member.login_id WHERE thread.date BETWEEN ? AND ? ORDER BY thread.date DESC LIMIT ? OFFSET ? ;";
		List<Thread> postList=new ArrayList<>();
		try(Connection con=ds.getConnection()){
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, start);
			stmt.setString(2, end);
			stmt.setInt(3, limit);
			stmt.setInt(4, offset);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				postList.add(mapToThread(rs));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return postList;
	}
	
	private Thread mapToThread(ResultSet rs) throws SQLException {
		Thread thread=new Thread();
		thread.setId(rs.getInt("thread.id"));
		thread.setLoginId(rs.getString("thread.login_id"));
		thread.setName(rs.getString("name"));
		thread.setDate(rs.getString("date"));
		thread.setPost(rs.getString("post"));
		return thread;
	}

	@Override
	public boolean insertThread(String loginId, String post) {
		String sql="insert into thread (login_id, post, date) value (?, ?, now())";
		try(Connection con=ds.getConnection()){
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, loginId);
			stmt.setString(2, post);
			stmt.executeUpdate();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<domain.Thread> getThread(int limit, int offset) {
		String sql ="select member.name, thread.login_id, date, post from thread left join member on thread.login_id = member.login_id order by date desc limit ? offset ?; ";
		List<domain.Thread> threadList=new ArrayList<>();
		try(Connection con=ds.getConnection()){
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				domain.Thread thread=new Thread();
				thread.setName(rs.getString("name"));
				thread.setLoginId(rs.getString("login_id"));
				thread.setDate(rs.getString("date"));
				thread.setPost(rs.getString("post"));
				threadList.add(thread);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return threadList;
	}


	@Override
	public int getTotalThreadCount(String start, String end) {
		String sql="select count(*) from thread where date between ? and ?;";
		try(Connection con=ds.getConnection()){
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, start);
			stmt.setString(2, end);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				return rs.getInt(1);//投稿の総数を返す
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public boolean deletePost(int id) {
		String safe0SQL="SET SESSION sql_safe_updates = 0;";
		String sql="delete from thread where id=? ;";
		String safe1SQL="SET SESSION sql_safe_updates = 1;";
		try(Connection con=ds.getConnection()){
			PreparedStatement stmt=con.prepareStatement(safe0SQL);
			stmt.executeQuery();
			stmt=con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt=con.prepareStatement(safe1SQL);
			stmt.executeQuery();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	
}
