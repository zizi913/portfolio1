package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Shift {
	
	private String loginId;
	private int shift;
	private String strShift;
	private Date date;
	private Date checkin;
	private Date checkout;
	
	public Shift() {}
	
	public Shift(String loginId, Date shift) {}

}
