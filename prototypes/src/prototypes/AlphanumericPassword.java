package prototypes;

public class AlphanumericPassword {
	String password;
	boolean isSet = false;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		isSet = true;
	}
	
	public boolean isSet() {
		return isSet;
	}
	
	public boolean checkMatch(String password) {
		return (this.password.equals(password)) ? true : false;
	}
}
