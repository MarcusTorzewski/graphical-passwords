package prototypes.Alphanumeric;

public class AlphanumericPassword {
	public static final int BANK_STYLE_SIZE = 4;
	public static final int MINIMUM_SIZE = 6;
	
	private String password;
	private boolean isSet = false;
	
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
	
	public void clearPassword() {
		this.password = null;
		this.isSet = false;
	}
}
