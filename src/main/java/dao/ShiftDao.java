package dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import domain.Shift;

public interface ShiftDao {

	//一カ月のシフトを全員分表示
	Map<Date, Map<String, String>> findAllShift(String start, String end);
	public List<String> findNameList(String start, String end) ;
	//loginIdから一人分のシフトを一か月分表示
	List<Shift> findByShift(String loginId, String start, String end) throws Exception;
	//loginIdからシフトの追加
	boolean insertShift(String loginId, String date, int shift);
	//loginIdからシフトの削除
	void deleteShift(String loginId, String date);
	//loginIdからシフトの変更
	boolean updateShift(String loginId, int shift, String date);
	
	//出勤
	void checkin(String loginId, String date);
	//退勤
	void checkout(String loginId, String date);
	
	//checkin/out２重チェック
	String checkInCheck(String today, String loginId);
	String checkOutCheck(String today, String loginId);
	
	public Map<String,String> getNameId();
}
