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
	int capacity;
	ArrayList<ArrayList<Integer>> points;
	
	public PassPoints() {
		this.capacity = 0;
		this.points = null;
	}
	
//	void registration() {
//		Display display = new Display();
//		Shell shell = new Shell(display);
//		
//		GridLayout gridLayout = new GridLayout();
//		gridLayout.numColumns = 1;
// 		gridLayout.marginLeft = 5;
// 		gridLayout.marginRight = 5;
// 		gridLayout.marginTop = 5;
// 		gridLayout.marginBottom = 5;
// 		gridLayout.verticalSpacing = 5;
//		
//		GridData gridData = new GridData();
// 		gridData.verticalAlignment = GridData.CENTER;
//		
//		Label infoLabel = new Label(shell, SWT.NONE);
//        infoLabel.setText("Click on 5 points in this image:");
//        infoLabel.setLayoutData(gridData);	
//		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
//		
//		System.out.println("Enter the length of your new password: ");
//		
//		ArrayList<Integer> p = new ArrayList<Integer>();
//		for (int i = 1; i <= capacity; i++) {
//
//            input.add(p);
//		}
//	
//		points = input;
//		return;
//	}
	
	boolean loginAttempt() {
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		
		


		
		ArrayList<Integer> p = new ArrayList<Integer>();
		System.out.println("Enter the points of your password: ");
		for (int i = 1; i <= capacity; i++) {

            
            input.add(p);
        }
		

		if (input == points) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean loginAttemptWithSelectionOfPoints() {
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>();
		
		
		// generating the number and location of the points that will be selected
		int n = ThreadLocalRandom.current().nextInt(3, capacity); // generates a number between 3 and the capacity
		ArrayList<Integer> i = new ArrayList<Integer>(); // array containing the locations of the points to be selected
		
		for (int j = 0; j <= n; j++) {
			while (true) {
				int position = ThreadLocalRandom.current().nextInt(0, capacity + 1); // +1 necessary?
				if (i.contains(position)) {
					continue;
				} else {
					i.add(position);
					break;
				}
			}
		}
		Collections.sort(i);
		
		// creating the answer list
		for (int j = 0; j <= n; j++) {
			solution.add(points.get(i.get(j)));
		}
		
		ArrayList<Integer> p = new ArrayList<Integer>();
		System.out.println("Please input points " + i + " of your password:");
		for (int j = 0; j <= n; j++) {       
            input.add(p);
		}
		
		if (input == solution) {
			return true;
		} else {
		return false;
		}
	}
}