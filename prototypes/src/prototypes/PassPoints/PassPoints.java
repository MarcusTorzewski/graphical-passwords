package prototypes.PassPoints;

import java.util.*;

import prototypes.TuplePair;

public class PassPoints {
	public static final int CAPACITY = 5;
	public static final int HYBRID_CAPACITY = 3; // reduced because images have less detail
	public static final String[] PASS_POINTS_IMAGES = {"fluid", "crowd"};
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
}
