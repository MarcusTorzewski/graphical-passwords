package prototypes.Alphanumeric;

import java.util.ArrayList;
import java.util.Random;

public class AlphanumericPassword {
	public static final int BANK_STYLE_SIZE = 4;
	public static final int MINIMUM_SIZE = 6;
	
	private String password;
	private boolean isSet = false;
	
	public AlphanumericPassword() {
		this.password = null;
	}
	
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
	
	public ArrayList<Integer> generateBankStyleDigits() {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		Random r = new Random();
		
		// Populates the digits array with every digit...
		for (int i = 0; i < password.length(); i++) {
			digits.add(i);
		}
		
		// ...then pops random numbers from the digits array until it is the size of BANK_STYLE_ARRAY
		while (digits.size() > BANK_STYLE_SIZE) {
			int x = r.nextInt(digits.size());
			digits.remove(x);
		}
		
		return digits;
	}
	
	public String generateBankStyleAnswer(ArrayList<Integer> digits) {
		String answer = "";
		for (int i = 0; i < digits.size(); i++) {
			answer += password.charAt(digits.get(i));
		}
		return answer;
	}
}
