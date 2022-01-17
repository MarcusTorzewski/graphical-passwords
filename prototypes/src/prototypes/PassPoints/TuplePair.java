package prototypes.PassPoints;

public class TuplePair {
	int x;
	int y;
	
	public TuplePair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
    @Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }
        
        if (!(o instanceof TuplePair)) {
            return false;
        }
        
        TuplePair t = (TuplePair) o;
         
        return Double.compare(x, t.x) == 0
                && Double.compare(y, t.y) == 0;
    }
    
    @Override
    public String toString() {
    	return "(" + x + ", " + y + ")";
    }

}
