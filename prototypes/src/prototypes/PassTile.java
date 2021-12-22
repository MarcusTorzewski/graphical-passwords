package prototypes;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PassTile {
	int capacity;
	ArrayList<Integer> tiles; // Discrete numbers represent tiles
	
	PassTile(){
		this.capacity = 0;
		this.tiles = null;
	}
	
	void registration() {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> input = new ArrayList<Integer>();
		
		System.out.println("Enter the length of your new password: ");
		capacity = Integer.parseInt(sc.next());
		
		for (int i = 1; i <= capacity; i++) {
			System.out.println("Enter a number between 1 - 99: ");
			input.add(Integer.parseInt(sc.next()));
		}
		
		sc.close();
		
		tiles = input;
		return;
	}
	
	// populates a grid of tiles with numbers some of which will be the password
	boolean loginAttempt() {
		ArrayList<Integer> numbersUsed = new ArrayList<Integer>(16); // arrayList of 
		
		ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>(); // the actual grid of numbers to be displayed
		
		for (int i = 0; i < 4; i++) {
			grid.add(new ArrayList<Integer>(4));
			for (int j = 0; j < 4; j++) {
				grid.get(i).add(0);
			}
		}
		
		int x = 0;
		int y = 0;
		
		// putting the password numbers into the grid first then populating afterwards
		for (int i=0; i < capacity; i++) {
			x = ThreadLocalRandom.current().nextInt(0, 4);
			y = ThreadLocalRandom.current().nextInt(0, 4);
			
			if (grid.get(x).get(y) == 0) {
				grid.get(x).set(y, tiles.get(i));
			}
			else {
				x = ThreadLocalRandom.current().nextInt(0, 4);
				y = ThreadLocalRandom.current().nextInt(0, 4);
				grid.get(x).set(y, tiles.get(i));
			}
		}
		
		numbersUsed.addAll(tiles);
		
		for (int i=0; i < 4; i++) {
			for (int j = 0; j < 4; i++) {
				if (grid.get(i).get(j) != 0) {
					continue;
				}
				
				while(true) {
					x = ThreadLocalRandom.current().nextInt(1, 100);
					
					if (numbersUsed.contains(x)) {
						continue;
					}
					else {
						numbersUsed.add(x);
						grid.get(i).set(j,  x);
						break;
					}
				}
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(grid.get(i).get(j));
			}
			System.out.print("\n");
		}
		
		return false;
	}
}
