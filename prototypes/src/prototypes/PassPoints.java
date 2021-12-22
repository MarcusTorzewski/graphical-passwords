package prototypes;

import java.util.*;

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
		capacity = Integer.parseInt(sc.next());
		
		for (int i = 1; i <= capacity; i++) {
			ArrayList<Integer> p = new ArrayList<Integer>();
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
		
		System.out.println("Enter the length of your new password: ");
		int cap = Integer.parseInt(sc.next());
		
		System.out.println("Enter the points of your password: ");
		for (int i = 1; i <= cap; i++) {
			ArrayList<Integer> p = new ArrayList<Integer>();
            System.out.println("Please enter point " + i + " x: ");
            p.add(Integer.parseInt(sc.next()));
            System.out.println("Please enter point " + i + " y : ");
            p.add(Integer.parseInt(sc.next()));
            
            input.add(p);
        }
		
		
		sc.close();
		if (input == points) {
			return true;
		}
		
		
		return false;
	}
}
