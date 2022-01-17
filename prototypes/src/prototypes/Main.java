package prototypes;

import prototypes.Alphanumeric.*;
import prototypes.PassPoints.*;

public class Main {
	// used as the testing bed for implementations.
	// not sure if this file will actually appear in the final implementation
	public static void main(String[] args) {
		// creates an empty instance of each password that will be used
		AlphanumericPassword ap = new AlphanumericPassword();
		PassPoints pp = new PassPoints();
		PassTile pt = new PassTile();
		
		AlphaRegistration.register(ap);
		if (ap.isSet()) {
			AlphaLogin.login2(ap);
		}
		
//		PassPointsRegistration.registration(pp);
//		System.out.println(pp.getPoints());
	}

}
