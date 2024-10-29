package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Thread {

	public Thread() {
	}

	private int Id;
	private String loginId;
	private String name;
	private String date;
	private String post;

}
