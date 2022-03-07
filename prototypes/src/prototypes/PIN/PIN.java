package prototypes.PIN;

import java.util.ArrayList;

public class PIN {
	public static final int LENGTH = 4;
	
	private ArrayList<Integer> password;
	private boolean isSet = false;
	
	public PIN() {
		this.password = null;
	}
	
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
	
	public void clearPassword() {
		this.password = null;
		this.isSet = false;
	}
}
