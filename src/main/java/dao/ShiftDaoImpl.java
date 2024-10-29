package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import domain.Name;
import domain.Shift;

public class ShiftDaoImpl implements ShiftDao {

	private DataSource ds;

	public ShiftDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Map<Date, Map<String, String>> findAllShift(String start, String end) {
		String sql = "SELECT shift_records.login_id, shift_records.date, shift.name AS shift_name, shift_records.shift, shift_records.date, shift_records.checkin, shift_records.checkout, member.name AS member_name FROM shift_records LEFT JOIN shift ON shift.id = shift_records.shift LEFT JOIN member ON member.login_id = shift_records.login_id WHERE shift_records.date BETWEEN ? AND ? ORDER BY shift_records.date ASC;";
		Map<Date, Map<String, String>> shiftMap = new LinkedHashMap<>();
		List<String> nameList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, start);
			stmt.setString(2, end);
			ResultSet rs = stmt.executeQuery();
			// 取得したデータをリスト化する
			while (rs.next()) {
				Date date = rs.getDate("date");
				// String loginId=rs.getString("login_id");
				String shift = rs.getString("shift_name");
				String name = rs.getString("member_name");
				// 日付ごとのシフトをマップに格納
				Map<String, String> employeeShiftMap = shiftMap.computeIfAbsent(date, k -> new LinkedHashMap<>());
				employeeShiftMap.put(name, shift);
				// 社員番号リストに社員を追加
				if (!nameList.contains(name)) {
					nameList.add(name);
					System.out.println("DaoのnameList:" + nameList);// OK
					Name names = new Name();
					names.setNameList(nameList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shiftMap;
	}

	public List<String> findNameList(String start, String end) {
		String sql = "SELECT shift_records.login_id, shift_records.date, shift.name AS shift_name, shift_records.shift, shift_records.date, shift_records.checkin, shift_records.checkout, member.name AS member_name FROM shift_records LEFT JOIN shift ON shift.id = shift_records.shift LEFT JOIN member ON member.login_id = shift_records.login_id WHERE shift_records.date BETWEEN ? AND ? ORDER BY shift_records.date ASC;";
		Map<Date, Map<String, String>> shiftMap = new LinkedHashMap<>();
		List<String> nameList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, start);
			stmt.setString(2, end);
			ResultSet rs = stmt.executeQuery();
			// 取得したデータをリスト化する
			while (rs.next()) {
				Date date = rs.getDate("date");
				// String loginId=rs.getString("login_id");
				String shift = rs.getString("shift_name");
				String name = rs.getString("member_name");
				// 日付ごとのシフトをマップに格納
				Map<String, String> employeeShiftMap = shiftMap.computeIfAbsent(date, k -> new LinkedHashMap<>());
				employeeShiftMap.put(name, shift);
				// 社員番号リストに社員を追加
				if (!nameList.contains(name)) {
					nameList.add(name);
					System.out.println("DaoのnameList:" + nameList);// OK
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nameList;
	}

	@Override
	public List<Shift> findByShift(String loginId, String start, String end) throws Exception {
		String spl = "select login_id, date, shift.name, shift_records.shift, checkin, checkout from shift_records left join shift on shift_records.shift= shift.id where login_id = ? and date between ? and ? order by date asc; ";
		List<Shift> shiftList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(spl);
			stmt.setString(1, loginId);
			stmt.setString(2, start);
			stmt.setString(3, end);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				shiftList.add(mapToShift(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shiftList;
	}

	@Override
	public boolean insertShift(String loginId, String date, int shift) {
		String sql = "insert into shift_records (login_id, date, shift) values (?, ?, ?)  ;";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			// java.util.Dateをjava.sql.Dateに変換
			// TODO ここ動作不安
			// .util.Dateと.sql.Dateの型違い
			//java.sql.Date sqlShiftDate = new java.sql.Date(date.getTime());
			stmt.setString(1, loginId);
			stmt.setString(2, date);
			stmt.setInt(3, shift);
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("※SQLエラー");
			return false;
		}
	}

	@Override
	public void deleteShift(String loginId, String date) {
		String sql1="SET SQL_SAFE_UPDATES = 0;";
		String sql2 = "update shift_records set shift = null where login_id=? and date = ?;";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql1);
			stmt.executeQuery();
			stmt=con.prepareStatement(sql2);
			stmt.setString(1, loginId);
			stmt.setString(2, date);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean updateShift(String loginId, int shift, String date) {
		String sql = "update shift_records set shift=? where login_id=? and date = ?;";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, shift);
			stmt.setString(2, loginId);
			stmt.setString(3, date);
			
			int rowsAffected=stmt.executeUpdate();
			if(rowsAffected>0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void checkin(String loginId, String date) {
		String safe="SET SQL_SAFE_UPDATES = 0;";
		String sql = "update shift_records set checkin = now() where login_id = ? and date=?;";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt=con.prepareStatement(safe);
			stmt.executeQuery();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			stmt.setString(2, date);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void checkout(String loginId, String date) {
		String safe="SET SQL_SAFE_UPDATES = 0;";
		String sql = "update shift_records set checkout = now() where login_id = ? and date=?;";
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt=con.prepareStatement(safe);
			stmt.executeQuery();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			stmt.setString(2, date);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Shift mapToShift(ResultSet rs) throws Exception {
		Shift shift = new Shift();
		shift.setLoginId(rs.getString("login_id"));
		shift.setShift(rs.getInt("shift"));
		shift.setStrShift(rs.getString("shift.name"));
		shift.setDate(rs.getTimestamp("date"));
		shift.setCheckin(rs.getTimestamp("checkin"));
		shift.setCheckout(rs.getTimestamp("checkout"));
		return shift;

	}

	@Override
	public String checkInCheck(String today, String loginId) {
		String sql = "select checkin from shift_records where date = ? and login_id = ?;";
		String checkin = null;
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, today);
			stmt.setString(2, loginId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				checkin = rs.getString("checkin");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkin;
	}

	@Override
	public String checkOutCheck(String today, String loginId) {
		String sql = "select checkout from shift_records where date = ? and login_id = ?;";
		String checkout = null;
		try (Connection con = ds.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, today);
			stmt.setString(2, loginId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				checkout = rs.getString("checkout");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkout;
	}
	
	public Map<String,String> getNameId(){
		String sql="select login_id, name from member;";
		Map<String, String> nameIdMap= new HashMap<>();
		try(Connection con=ds.getConnection()){
			PreparedStatement stmt=con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				String loginId=rs.getString("login_id");
				String name=rs.getString("name");
				nameIdMap.put(loginId, name);//loginId->key/name->value
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return nameIdMap;
	}

}
