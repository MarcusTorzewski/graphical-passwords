package prototypes;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PassPoints {
	int capacity;
	ArrayList<ArrayList<Integer>> points;
	
	PassPoints() {
		this.capacity = 0;
		this.points = null;
	}
	
	void registration() {
		Scanner sc = new Scanner(System.in);
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		
		System.out.println("Enter the length of your new password: ");
		capacity = Integer.parseInt(sc.next()); // -1?
		
		ArrayList<Integer> p = new ArrayList<Integer>();
		for (int i = 1; i <= capacity; i++) {
            System.out.println("Please enter point " + i + " x: ");
            p.add(Integer.parseInt(sc.next()));
            System.out.println("Please enter point " + i + " y : ");
            p.add(Integer.parseInt(sc.next()));
            
            input.add(p);
		}
	
		
		sc.close();
		points = input;
		return;
	}
	
	boolean loginAttempt() {
		Scanner sc = new Scanner(System.in);
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		
		
//		System.out.println("Enter the length of your new password: ");
//		int cap = Integer.parseInt(sc.next());
		
		ArrayList<Integer> p = new ArrayList<Integer>();
		System.out.println("Enter the points of your password: ");
		for (int i = 1; i <= capacity; i++) {
            System.out.println("Please enter point " + i + " x: ");
            p.add(Integer.parseInt(sc.next()));
            System.out.println("Please enter point " + i + " y : ");
            p.add(Integer.parseInt(sc.next()));
            
            input.add(p);
        }
		

		
		sc.close();
		if (input == points) {
			return true;
		} else {
			return false;
		}
	}
	
	boolean loginAttemptWithSelectionOfPoints() {
		Scanner sc = new Scanner(System.in);
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
            System.out.println("Please enter point " + i + " x: ");
            p.add(Integer.parseInt(sc.next()));
            System.out.println("Please enter point " + i + " y : ");
            p.add(Integer.parseInt(sc.next()));
            
            input.add(p);
		}
		sc.close();
		
		if (input == solution) {
			return true;
		} else {
		return false;
		}
	}
}
