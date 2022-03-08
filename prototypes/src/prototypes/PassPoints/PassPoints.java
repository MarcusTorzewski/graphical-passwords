package prototypes.PassPoints;

import java.util.*;

import prototypes.TuplePair;

public class PassPoints {
	public static int CAPACITY = 5;
	public static int BANK_STYLE_SIZE = 3; // number of points used in the bank-style login
	
	private int size = 0;
	private ArrayList<TuplePair<Integer>> points;
	private int imageCode = -1;
	private boolean isSet = false;
	
	public PassPoints() {
		this.points = null;
	}
	
	public int getSize() {
		return size;
	}

	public ArrayList<TuplePair<Integer>> getPoints() {
		return points;
	}
	

	public void setPoints(ArrayList<TuplePair<Integer>> points) {
		this.points = points;
		this.size = points.size();
		this.isSet = true;
	}
	
	public int getImageCode() {
		return imageCode;
	}
	
	public void setImageCode(int code) {
		imageCode = code;
	}
	
	public boolean isSet() {
		return isSet;
	}
	
	public void clearPassword() {
		this.points = null;
		this.size = 0;
		this.isSet = false;
		this.imageCode = -1;
	}
}
