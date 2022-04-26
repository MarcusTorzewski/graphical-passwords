package prototypes.Digraph;

import java.util.ArrayList;
import java.util.Arrays;

import prototypes.TuplePair;

public class Digraph {
	public static final int GRID_SIZE = 25;
	public static final ArrayList<String> ALL_TILES = new ArrayList<String>(Arrays.asList("airplane",
			"alarm","attachment","basketball","bicycle","bluetooth","bucket","bug","camera",
			"car","coffee","diamond","emoji","factory","fish","food","gift","headphone",
			"health","key","light","lightning","lock","microphone","moon","music","notification",
			"phone","piggy-bank","pin","rocket","snow","sun","tool","trash","trophy","umbrella",
			"user","volume","weather-partly-night","weather-shower"));// the array of all the available images' names
	
	
	private TuplePair<String> tiles;
	private boolean isSet = false;
	
	public Digraph() {
		tiles = null;
	}
	
	public TuplePair<String> getTiles() {
		return tiles;
	}
	
	public void setTiles(TuplePair<String> tiles) {
		this.tiles = tiles;
		this.isSet = true;
	}
	
	public boolean isSet() {
		return isSet;
	}
	
	public void clearPassword() {
		this.tiles = null;
		this.isSet = false;
	}
}
