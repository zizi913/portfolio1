package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import domain.AddMember;
import domain.Member;

public class MemberDaoImpl implements MemberDao {

	private DataSource ds;

	public MemberDaoImpl(DataSource ds) {
		this.ds = ds;
	}

//	パスワードも全部含めて取得する

	@Override
	public List<Member> findAll() {
		String sql = "select * from member join status on member.status = status.id;";
		List<Member> members = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			// 取得したデータをリスト化する
			while (rs.next()) {
				members.add(mapToMember(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public Member findByLoginId(String loginId) {
		Member member = null;
		String sql = "select member.id, name, login_id, email, pass, tel, member.status, status.status from member join status on member.status = status.id where login_id = ?;";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next() == true) {
				member = new Member();
				member = mapToMember(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("findByLoginIdで例外発生です");
		}
		return member;
	}

	@Override
	public boolean insert(AddMember member) {
		try (Connection con = ds.getConnection()) {
			String sql = "insert into member (name, login_id, email, pass, tel, status)values(?, ?, ?, ?,?,?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getLoginId());
			stmt.setString(3, member.getEmail());
			stmt.setString(4, member.getPass());
			stmt.setString(5, member.getTel());
			stmt.setInt(6, member.getStatusId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void update(String loginId, String email, String tel) {
		String sql = "update member set email=?, tel=? where login_id=? ;";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, tel);
			stmt.setString(3, loginId);

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String loginId) {
		try (Connection con = ds.getConnection()) {
			String sql = "delete from member where login_id=?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			stmt.executeUpdate();
			sql = "set sql_safe_updates=0;";
			stmt = con.prepareStatement(sql);
			stmt.executeQuery();
			sql = "delete from shift_records where login_id = ?;";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Member findByLoginPass(String loginId, String pass) {

//		以下コードはJDBP10のコピペ
//		修正して使用

//		sqlの用意
		String sql = " select * from member where login_id=? ; ";
//		try-with-resourcesの記述なので自動的にcloseしてくれる
		try (Connection con = ds.getConnection()) {
//			SQLの準備
			PreparedStatement stmt = con.prepareStatement(sql);
//			SQLへパラメーターのセット
			stmt.setString(1, loginId);
			if (loginId == null) {
				System.out.println();
				return null;
			}
//			SQLの実行
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
//				ログインIDがヒットしたのでlogin_passを調べる
				if (BCrypt.checkpw(pass, rs.getString("pass"))) {
//					login_passもヒットしたのでユーザー情報を返す
//					passは返ってきても保存しない
					return new Member(rs.getInt("id"), loginId);
				}
				return null;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

//	DBからデータを抜き出すメソッド
	private Member mapToMember(ResultSet rs) throws Exception {
		Member member = new Member();
		member.setId(rs.getInt("id"));
		member.setName(rs.getString("name"));
		member.setLoginId(rs.getString("login_id"));
		member.setEmail(rs.getString("email"));
		member.setPass(rs.getString("pass")); // パスワードのフィールドがあるか確認
		member.setTel(rs.getString("tel"));
		member.setStatus(rs.getString("status.status"));
		member.setStatusId(rs.getInt("member.status"));

		return member;

	}

	@Override
	public Integer checkOwner(String loginId) {
		String sql = "select status from member where login_id = ?; ";
		Integer status = null;
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				status = rs.getInt("status");
				System.out.println(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean addShift(String loginId) {
		String sql = "INSERT INTO shift_records (login_id, date) SELECT ?, DATE_ADD(CURDATE(), INTERVAL seq DAY) FROM (SELECT @row := @row + 1 AS seq FROM (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS digits, (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS tens, (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS hundreds, (SELECT @row := 0) AS init LIMIT 365) seq_table;" ;
				
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
