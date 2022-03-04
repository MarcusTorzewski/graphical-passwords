package prototypes.PassTiles;

import java.util.ArrayList;
import java.util.Arrays;

public class PassTile {
	public static int CAPACITY = 5;
	public static int GRID_SIZE = 25;
	public static ArrayList<String> ALL_TILES = new ArrayList<String>(Arrays.asList("phone",
			"phone-hang-up","phone-incoming","phone-missed","phone-off", "phone-outgoing",
			"phone-talking","text-align-center","text-align-justify","text-align-left",
			"text-align-right","user","user-add","user-check","user-error","user-list",
			"user-love","users-add","volume-down","volume-mute","volume-off","volume-up",
			"weather-downpour","weather-night","weather-partly-night","weather-partly-sunny",
			"weather-shower","weather-sunny","weather-stormy","weather-windy",
			"weather-windy-cloudy","wifi","wifi-2","wifi-low","wifi-off","zoom-in","zoom-out")); // the array of all the available images' names
	
	private int size;
	private ArrayList<String> tiles;
	private boolean isSet;
	
	public PassTile() {
		this.size = 0;
		this.tiles = null;
		this.isSet = false;
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
}
