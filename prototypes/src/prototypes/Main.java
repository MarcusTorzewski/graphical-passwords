package prototypes;

import prototypes.Alphanumeric.*;
import prototypes.PIN.*;
import prototypes.PassPoints.*;

public class Main {
	// used as the testing bed for implementations.
	// not sure if this file will actually appear in the final implementation
	public static void main(String[] args) {
		// creates an empty instance of each password that will be used
		AlphanumericPassword ap = new AlphanumericPassword();
		PIN pin = new PIN();
		PassPoints pp = new PassPoints();
		
//		AlphaRegistration.register(ap);
//		if (ap.isSet()) {
//			AlphaLogin.login(ap);
//		}
		
		PINRegistration.register(pin);
		if (pin.isSet()) {
			PINLogin.login(pin);
		}
		
//		PassPointsRegistration.registration(pp);
//		PassPointsLogin.login(pp);
	}

}
