package prototypes.PassPoints;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class PassPoints {
	public static int CAPACITY = 5;
	
	private int size;
	private ArrayList<TuplePair> points;
	private int imageCode;
	
	public PassPoints() {
		this.size = 0;
		this.points = null;
		this.imageCode = -1;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<TuplePair> getPoints() {
		return points;
	}
	

	public void setPoints(ArrayList<TuplePair> points) {
		this.points = points;
	}
	
	public int getImageCode() {
		return imageCode;
	}
	
	public void setImageCode(int code) {
		imageCode = code;
	}
	
	boolean loginAttemptWithSelectionOfPoints() {
//		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
//		ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>();
//		
//		
//		// generating the number and location of the points that will be selected
//		int n = ThreadLocalRandom.current().nextInt(3, capacity); // generates a number between 3 and the capacity
//		ArrayList<Integer> i = new ArrayList<Integer>(); // array containing the locations of the points to be selected
//		
//		for (int j = 0; j <= n; j++) {
//			while (true) {
//				int position = ThreadLocalRandom.current().nextInt(0, capacity + 1); // +1 necessary?
//				if (i.contains(position)) {
//					continue;
//				} else {
//					i.add(position);
//					break;
//				}
//			}
//		}
//		Collections.sort(i);
//		
//		// creating the answer list
//		for (int j = 0; j <= n; j++) {
//			solution.add(points.get(i.get(j)));
//		}
//		
//		ArrayList<Integer> p = new ArrayList<Integer>();
//		System.out.println("Please input points " + i + " of your password:");
//		for (int j = 0; j <= n; j++) {       
//            input.add(p);
//		}
//		
//		if (input == solution) {
//			return true;
//		} else {
//		return false;
//		}
		return false;
	}
}
