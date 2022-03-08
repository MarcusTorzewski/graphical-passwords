package prototypes;

public class TuplePair<T> {
	T x;
	T y;
	
	public TuplePair() {
		return;
	}
	
	public TuplePair(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	public T getX() {
		return x;
	}

	public T getY() {
		return y;
	}
	
//	public boolean contains(T value) {
//		if (x == value || y == value) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	public int size() {
//		if (x == null && y == null) {
//			return 0;
//		} if (x == null || y == null) {
//			return 1;
//		} else {
//			return 2;
//		}
//	}
	
    @Override
    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }
        
        if (!(o instanceof TuplePair)) {
            return false;
        }
        
        try {
			@SuppressWarnings("unchecked")
			TuplePair<T> t = (TuplePair<T>) o;
		     
		    return (this.getX() == t.getX()) && (this.getY() == t.getY());
        } catch(Exception e) {
        	return false;
        }
    }
    
    @Override
    public String toString() {
    	return "(" + x + ", " + y + ")";
    }

}
