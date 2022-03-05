package prototypes.PIN;

import java.util.ArrayList;

public class PIN {
	private ArrayList<Integer> password;
	private boolean isSet = false;
	
	public ArrayList<Integer> getPassword() {
		return password;
	}
	public void setPassword(ArrayList<Integer> password) {
		this.password = password;
		this.isSet = true;
	}
	public boolean isSet() {
		return isSet;
	}
	public void setSet(boolean isSet) {
		this.isSet = isSet;
	}
}
