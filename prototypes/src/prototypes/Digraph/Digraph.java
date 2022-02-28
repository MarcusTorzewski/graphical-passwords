package prototypes.Digraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Digraph {
	public static int GRID_SIZE = 25;
	public static ArrayList<String> ALL_TILES = new ArrayList<String>(Arrays.asList("phone",
			"phone-hang-up","phone-incoming","phone-missed","phone-off", "phone-outgoing",
			"phone-talking","text-align-center","text-align-justify","text-align-left",
			"text-align-right","user","user-add","user-check","user-error","user-list",
			"user-love","users-add","volume-down","volume-off","volume-up","weather-downpour",
			"weather-night","weather-partly-night","weather-partly-sunny","weather-shower",
			"weather-sunny","weather-stormy","weather-windy","weather-windy-cloudy",
			"wifi","wifi-2","wifi-low","wifi-off","zoom-in","zoom-out"));
	
	private String tileA; // tile A and B are arbitrary it doesn't matter which is where
	private String tileB;
	private boolean isSet;
	
	public Digraph() {
		tileA = null;
		tileB = null;
	}
	
	public String getTileA() {
		return tileA;
	}
	
	public void setTileA(String tileA) {
		this.tileA = tileA;
	}
	
	public String getTileB() {
		return tileB;
	}
	
	public void setTileB(String tileB) {
		this.tileB = tileB;
	}
	
	public void setTiles(ArrayList<String> tiles) {
		this.tileA = tiles.get(0);
		this.tileB = tiles.get(1);
		this.isSet = true;
	}
	
	public ArrayList<String> getTiles() {
		ArrayList<String> tiles = new ArrayList<String>();
		tiles.add(tileA);
		tiles.add(tileB);
		return tiles;
	}
	
	public boolean isSet() {
		return isSet;
	}
	
	public void isSet(boolean isSet) {
		this.isSet = isSet;
	}
}
