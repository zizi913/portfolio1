package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class Member {

	private int id;
	private String name;
	private String loginId;
	private String email;
	private String pass;
	private String tel;
	private int statusId;
	private String status;

	public Member(int id, String loginId) {
		this.id = id;
		this.loginId = loginId;
	}

	public Member() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
