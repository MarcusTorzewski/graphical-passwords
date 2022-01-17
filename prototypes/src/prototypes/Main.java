package prototypes;

import prototypes.Alphanumeric.AlphaLogin;
import prototypes.Alphanumeric.AlphaRegistration;
import prototypes.Alphanumeric.AlphanumericPassword;
import prototypes.PassPoints.*;

public class Main {

	public static void main(String[] args) {
		// creates an empty instance of each password that will be used
		AlphanumericPassword ap = new AlphanumericPassword();
		PassPoints pp = new PassPoints();
		PassTile pt = new PassTile();
		
		AlphaRegistration.register(ap);
		if (ap.isSet()) {
			AlphaLogin.login2(ap);
		}
	}

}
