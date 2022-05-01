package prototypes.PassPoints;

import java.util.*;

import prototypes.TuplePair;

public class PassPoints {
	public static final int CAPACITY = 5;
	public static final int HYBRID_CAPACITY = 3; // reduced because images have less detail
	public static final String[] PASS_POINTS_IMAGES = {"fluid","crowd","newyork"};
	public static final String[] HYBRID_KEY_IMAGES = {"bird","cube","tree"};
	public static final String[][] HYBRID_DECOY_IMAGES = {{"beach","flowers","gauge","lime","plane"},
			{"gauge","lime","plane","salad","street"},
			{"cat","fence","flowers","landscape","lime"}};

	
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
	
	/**
	 * Checks the input against points attribute coordinate by coordinate giving a leeway of 10 pixels
	 * @param input
	 * @return 0 - incorrect match (but requirements met), 1 - match, -1 - incorrect size
	 */
	public int checkMatch(ArrayList<TuplePair<Integer>> input) {
		if (input.size() != points.size()) {
			return -1;
		}
		
		int correctCounter = 0;
    	if (input.size() == points.size()) {
    		// trad for loop over for each because i is needed to fetch both sets
    		for (int i = 0; i < input.size(); i ++) {
    			TuplePair<Integer> t = input.get(i);
    			TuplePair<Integer> u = points.get(i);
    			if ((u.getX() - 10 <= t.getX()) && (t.getX() <= u.getX() + 10)) {
    				if ((u.getY() - 10 <= t.getY()) && (t.getY() <= u.getY() + 10)) {
    					correctCounter++;
    					continue;
    				}
    				continue;
    			}
    		}
    	}
    	
		if (correctCounter == points.size()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Checks the input against points attribute coordinate by coordinate giving a leeway of 20 pixels
	 * @param input
	 * @return 0 - incorrect match (but requirements met), 1 - match, -1 - incorrect size
	 */
	public int checkHybridMatch(ArrayList<TuplePair<Integer>> input) {
		if (input.size() != points.size()) {
			return -1;
		}
		
		int correctCounter = 0;
    	if (input.size() == points.size()) {
    		// trad for loop over for each because i is needed to fetch both sets
    		for (int i = 0; i < input.size(); i ++) {
    			TuplePair<Integer> t = input.get(i);
    			TuplePair<Integer> u = points.get(i);
    			if ((u.getX() - 20 <= t.getX()) && (t.getX() <= u.getX() + 20)) {
    				if ((u.getY() - 20 <= t.getY()) && (t.getY() <= u.getY() + 20)) {
    					correctCounter++;
    					continue;
    				}
    				continue;
    			}
    		}
    	}
    	
		if (correctCounter == points.size()) {
			return 1;
		} else {
			return 0;
		}
	}
}
