package domain;

import java.util.ArrayList;
import java.util.List;

//@AllArgsConstructor
//@Data
public class Name {
	
	private List<String> nameList;

	public Name() {
		this.nameList=new ArrayList<>();
	}

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	
	

}
