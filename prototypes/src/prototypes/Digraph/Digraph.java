package prototypes.Digraph;

import java.util.ArrayList;
import java.util.Arrays;

import prototypes.TuplePair;

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
	
	private TuplePair<String> tiles;
	private boolean isSet = false;
	
	public Digraph() {
		tiles = null;
	}
	
	public void setTiles(TuplePair<String> tiles) {
		this.tiles = tiles;
		this.isSet = true;
	}
	
	public TuplePair<String> getTiles() {
		return tiles;
	}
	
	public boolean isSet() {
		return isSet;
	}
	
	public void clearPassword() {
		this.tiles = null;
		this.isSet = false;
	}
}
