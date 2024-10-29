package dao;

import java.util.List;

import domain.AddMember;
import domain.Member;

public interface MemberDao {
	
//	メンバー一覧取得
	List<Member> findAll();
//	IDからメンバー取得
	Member findByLoginId(String loginId) throws Exception;
//	メンバー登録
	boolean insert(AddMember member);
//	メンバー更新
	void update(String loginId, String email, String tel);
//	メンバー削除
	void delete(String loginId);
	
//	ログイン認証
//	-> 正しいIDとパスワードの時はユーザー情報を返す
//	-> 不正の場合はnull
	Member findByLoginPass(String loginId,String LoginPass);
	
//フィルター用
	Integer checkOwner(String loginId);
	
	boolean addShift(String loginId);
	

}
