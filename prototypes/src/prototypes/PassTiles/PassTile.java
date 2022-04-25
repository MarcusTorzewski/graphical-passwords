package prototypes.PassTiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class PassTile {
	public static final int BANK_STYLE_SIZE = 3;
	public static final int CAPACITY = 5;
	public static final int GRID_SIZE = 25;
	public static final ArrayList<String> ALL_TILES = new ArrayList<String>(Arrays.asList("airplane",
			"alarm","attachment","basketball","bicycle","bluetooth","bucket","bug","camera",
			"car","coffee","diamond","emoji","factory","fish","food","gift","headphone",
			"health","key","light","lightning","lock","microphone","moon","music","notification",
			"phone","piggy-bank","pin","rocket","snow","sun","tool","trash","trophy","umbrella",
			"user","volume","weather-partly-night","weather-shower"));// the array of all the available images' names
	
	private int size = 0;
	private ArrayList<String> tiles;
	private boolean isSet = false;
	
	public PassTile() {
		this.tiles = null;
	}

	public int getSize() {
		return size;
	}

	public ArrayList<String> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<String> tiles) {
		this.tiles = tiles;
		this.size = tiles.size();
		this.isSet = true;
	}

	public boolean isSet() {
		return isSet;
	}
	
	public void clearPassword() {
		this.tiles = null;
		this.size = 0;
		this.isSet = false;
	}
	
	/**
	 * Generates a grid of GRID_SIZE random images from the selection available.
	 * This one is used for registration as it does not take in a password ArrayList.
	 * @return toDisplay the ArrayList of images for the GUI to display 
	 */
	public static ArrayList<String> generateGrid() {
		ArrayList<String> remaining = new ArrayList<String>(PassTile.ALL_TILES);
		ArrayList<String> toDisplay = new ArrayList<String>();
		Random r = new Random();
		
		// toDisplay is populated randomly with GRID_SIZE strings from remaining
		// toDisplay is then used to populate the GUI
		for (int i = 0; i < PassTile.GRID_SIZE; i++) {
			int n = r.nextInt(remaining.size() - 1);
			toDisplay.add(remaining.get(n));
			remaining.remove(n);
		}
		
		System.out.println(remaining);
		System.out.println(toDisplay);
		
		return toDisplay;
	}
	
	/**
	 * Generates a grid of GRID_SIZE random images from the selection available.
	 * This one is used for registration as it does not take in a password ArrayList.
	 * @param p the password attribute of a PassTile class
	 * @return toDisplay the ArrayList of images for the GUI to display
	 */
	public static ArrayList<String> generateGrid(ArrayList<String> password) {
		ArrayList<String> remaining = new ArrayList<String>(PassTile.ALL_TILES);
		ArrayList<String> toDisplay = new ArrayList<String>(password);
		
		remaining.removeAll(toDisplay); // removes users password from remaining (no duplicates)
		Random r = new Random();
		
		// (PassTiles.GRID_SIZE - p.size() should account for the initial size of toDisplay
		for (int i = 0; i < (PassTile.GRID_SIZE - password.size()); i++) {
			int n = r.nextInt(remaining.size() - 1);
			toDisplay.add(remaining.get(n));
			remaining.remove(n);
		}
		
		Collections.shuffle(toDisplay); // Makes sure the users password isn't just the first 5 icons
		
		System.out.println(remaining);
		System.out.println(toDisplay);
		
		return toDisplay;
	}
	
	public static ArrayList<String> generateBankStyleGrid(ArrayList<String> selection, ArrayList<String> password) {
		ArrayList<String> remaining = new ArrayList<String>(PassTile.ALL_TILES);
		ArrayList<String> toDisplay = new ArrayList<String>(selection);
		remaining.removeAll(password);
		Random r = new Random();
		
		for (int i = 0; i < (PassTile.GRID_SIZE - selection.size()); i++) {
			int n = r.nextInt(remaining.size() - 1);
			toDisplay.add(remaining.get(n));
			remaining.remove(n);
		}
		
		System.out.println(remaining);
		System.out.println(toDisplay);
		
		Collections.shuffle(toDisplay);
		return toDisplay;
	}
	
	public ArrayList<String> generateBankStyleSelection() {
		ArrayList<String> solution = new ArrayList<String>();
		ArrayList<String> remaining = new ArrayList<String>(tiles);
		Random r = new Random();
		
		for (int i = 0; i < PassTile.BANK_STYLE_SIZE; i++) {
			int n = r.nextInt(remaining.size() - 1);
			solution.add(remaining.get(n));
			remaining.remove(n);
		}
		
		System.out.println(solution);
		
		Collections.sort(solution);
		return solution;
	}
	

}
