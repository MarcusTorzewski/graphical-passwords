package prototypes;

public class Main {

	public static void main(String[] args) {
		// creates an empty instance of each password that will be used
		AlphanumericPassword ap = new AlphanumericPassword();
		PassPoints pp = new PassPoints();
		PassTile pt = new PassTile();
		
		AlphaRegistration.registration(ap);
		if (ap.isSet) {
			AlphaLogin.login(ap);
		}
	}

}
