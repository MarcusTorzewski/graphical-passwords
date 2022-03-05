package prototypes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import prototypes.Alphanumeric.*;
import prototypes.Digraph.*;
import prototypes.PIN.*;
import prototypes.PassPoints.*;
import prototypes.PassTiles.*;

public class Homepage {
	
	/**
	 * The home-page for the program which is the first thing that the user sees when
	 * they start it. It allows users to register for, login using and get explanations
	 * for each methodology implemented. 
	 * @param None
	 * @return None
	 */
	public static void homepage() {
		// creates an empty instance of each password that will be used
		AlphanumericPassword ap = new AlphanumericPassword();
		PIN pin = new PIN();
		PassPoints pp = new PassPoints();
		PassPoints hpp = new PassPoints();
		PassTile pt = new PassTile();
		Digraph dp = new Digraph();
		
		Display display = new Display();
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		GridData gridData;
		FontData[] fD;
		gridLayout.numColumns = 4;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;
 		gridLayout.makeColumnsEqualWidth = true;

 		shell.setLayout(gridLayout);
 		
 		
 		Label titleLabel = new Label(shell, SWT.NONE);
 		titleLabel.setText("Graphical Passwords!");
 		gridData = new GridData(SWT.CENTER, SWT.FILL, true, true);
 		fD = titleLabel.getFont().getFontData();
 		fD[0].setHeight(20);
 		titleLabel.setFont( new Font(display,fD[0]));
 		gridData.horizontalSpan = 4;
 		gridData.verticalSpan = 2;
 		titleLabel.setLayoutData(gridData);
 		
 		
 		Label alphaTitleLabel = new Label(shell, SWT.NONE);
 		alphaTitleLabel.setText("Alphanumeric");
 		gridData = new GridData(SWT.CENTER, SWT.FILL, true, true);
 		fD = alphaTitleLabel.getFont().getFontData();
 		fD[0].setHeight(16);
 		alphaTitleLabel.setFont( new Font(display,fD[0]));
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		alphaTitleLabel.setLayoutData(gridData);
 		
 		
 		Label pinTitleLabel = new Label(shell, SWT.NONE);
 		pinTitleLabel.setText("PIN");
 		gridData = new GridData(SWT.CENTER, SWT.FILL, true, true);
 		fD = pinTitleLabel.getFont().getFontData();
 		fD[0].setHeight(16);
 		pinTitleLabel.setFont( new Font(display,fD[0]));
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		pinTitleLabel.setLayoutData(gridData);
		
 		
 		Button alphaRegistrationButton = new Button(shell, SWT.PUSH);
 		alphaRegistrationButton.setText("Register");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		alphaRegistrationButton.setLayoutData(gridData);
 		alphaRegistrationButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				AlphaRegistration.register(display, ap);
 			}
 		});
 		
 		
 		Button pinRegistrationButton = new Button(shell, SWT.PUSH);
 		pinRegistrationButton.setText("Register");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		pinRegistrationButton.setLayoutData(gridData);
 		pinRegistrationButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				PINRegistration.register(display, pin);
 			}
 		});
 		
 		
 		Button alphaLoginButton = new Button(shell, SWT.PUSH);
 		alphaLoginButton.setText("Log In");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 1;
 		gridData.verticalSpan = 2;
 		alphaLoginButton.setLayoutData(gridData);
 		alphaLoginButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				if (!ap.isSet()) {
 					Popup.passwordNotSet(display, 0);
 				} else {
 					AlphaLogin.login(display, ap);
 				}
 			}
 		});
 		
 		
 		Button alphaBankLoginButton = new Button(shell, SWT.PUSH);
 		alphaBankLoginButton.setText("Bank-Style Login");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 1;
 		gridData.verticalSpan = 2;
 		alphaBankLoginButton.setLayoutData(gridData);
 		alphaBankLoginButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				if (!ap.isSet()) {
 					Popup.passwordNotSet(display, 0);
 				} else {
// 					AlphaLogin.bankStyleLogin(display, ap);
 				}
 			}
 		});
 		
 		
 		Button pinLoginButton = new Button(shell, SWT.PUSH);
 		pinLoginButton.setText("Log In");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		pinLoginButton.setLayoutData(gridData);
 		pinLoginButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				if (!pin.isSet()) {
 					Popup.passwordNotSet(display, 4);
 				} else {
 					PINLogin.login(display, pin);
 				}
 			}
 		});
 		
 		
 		Button alphaHelpButton = new Button(shell, SWT.PUSH);
 		alphaHelpButton.setText("Help");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 1;
 		alphaHelpButton.setLayoutData(gridData);
 		alphaHelpButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				Popup.passwordHelp(display, 0);
 			}
 		});
 		
 		
 		Button pinHelpButton = new Button(shell, SWT.PUSH);
 		pinHelpButton.setText("Help");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 1;
 		pinHelpButton.setLayoutData(gridData);
 		pinHelpButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				Popup.passwordHelp(display, 4);
 			}
 		});
 		
 		
 		Label passPointsTitleLabel = new Label(shell, SWT.NONE);
 		passPointsTitleLabel.setText("PassPoints");
 		gridData = new GridData(SWT.CENTER, SWT.FILL, true, true);
 		fD = passPointsTitleLabel.getFont().getFontData();
 		fD[0].setHeight(16);
 		passPointsTitleLabel.setFont( new Font(display,fD[0]));
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		passPointsTitleLabel.setLayoutData(gridData);
 		
 		
 		Label passTileTitleLabel = new Label(shell, SWT.NONE);
 		passTileTitleLabel.setText("PassTile");
 		gridData = new GridData(SWT.CENTER, SWT.FILL, true, true);
 		fD = passTileTitleLabel.getFont().getFontData();
 		fD[0].setHeight(16);
 		passTileTitleLabel.setFont( new Font(display,fD[0]));
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		passTileTitleLabel.setLayoutData(gridData);
		
 		
 		Button passPointsRegistrationButton = new Button(shell, SWT.PUSH);
 		passPointsRegistrationButton.setText("Register");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 1;
 		gridData.verticalSpan = 2;
 		passPointsRegistrationButton.setLayoutData(gridData);
 		passPointsRegistrationButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				PassPointsRegistration.register(display, pp);
 			}
 		});

 		
 		Button hybridRegistrationButton = new Button(shell, SWT.PUSH);
 		hybridRegistrationButton.setText("Hybrid Image Register");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 1;
 		gridData.verticalSpan = 2;
 		hybridRegistrationButton.setLayoutData(gridData);
 		hybridRegistrationButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				return;
 			}
 		});
 		
 		
 		Button passTileRegistrationButton = new Button(shell, SWT.PUSH);
 		passTileRegistrationButton.setText("Register");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		passTileRegistrationButton.setLayoutData(gridData);
 		passTileRegistrationButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				PassTileRegistration.register(display, pt);
 			}
 		});
 		
 		
 		Button passPointsLoginButton = new Button(shell, SWT.PUSH);
 		passPointsLoginButton.setText("Log In");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 1;
 		gridData.verticalSpan = 2;
 		passPointsLoginButton.setLayoutData(gridData);
 		passPointsLoginButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				if (!pp.isSet()) {
 					Popup.passwordNotSet(display, 1);
 				} else {
 					PassPointsLogin.login(display, pp);
 				}
 			}
 		});
 		
 		
 		Button passPointsHybridLoginButton = new Button(shell, SWT.PUSH);
 		passPointsHybridLoginButton.setText("Hybrid Image Login");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 1;
 		gridData.verticalSpan = 2;
 		passPointsHybridLoginButton.setLayoutData(gridData);
 		passPointsHybridLoginButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				if (!hpp.isSet()) {
 					Popup.passwordNotSet(display, 5);
 				} else {
 					return;
 				}
 			}
 		});
 		
 		
 		Button passTileLoginButton = new Button(shell, SWT.PUSH);
 		passTileLoginButton.setText("Log In");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 1;
 		gridData.verticalSpan = 2;
 		passTileLoginButton.setLayoutData(gridData);
 		passTileLoginButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				if (!pt.isSet()) {
 					Popup.passwordNotSet(display, 2);
 				} else {
 					PassTileLogin.login(display, pt);
 				}
 			}
 		});
 		
 		
 		Button passTileBankLoginButton = new Button(shell, SWT.PUSH);
 		passTileBankLoginButton.setText("Bank-Style Login");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 1;
 		gridData.verticalSpan = 2;
 		passTileBankLoginButton.setLayoutData(gridData);
 		passTileBankLoginButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				if (!pt.isSet()) {
 					Popup.passwordNotSet(display, 2);
 				} else {
// 					PassTileLogin.loginBankStyle(display, pt);
 				}
 			}
 		});
 		
 		
 		Button passPointsHelpButton = new Button(shell, SWT.PUSH);
 		passPointsHelpButton.setText("Help");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 1;
 		passPointsHelpButton.setLayoutData(gridData);
 		passPointsHelpButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				Popup.passwordHelp(display, 1);
 			}
 		});
 		
 		
 		Button passTileHelpButton = new Button(shell, SWT.PUSH);
 		passTileHelpButton.setText("Help");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 1;
 		passTileHelpButton.setLayoutData(gridData);
 		passTileHelpButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				Popup.passwordHelp(display, 2);
 			}
 		});
 		
 		
 		Label digraphTitleLabel = new Label(shell, SWT.NONE);
 		digraphTitleLabel.setText("Digraph");
 		gridData = new GridData(SWT.CENTER, SWT.FILL, true, true);
 		fD = digraphTitleLabel.getFont().getFontData();
 		fD[0].setHeight(16);
 		digraphTitleLabel.setFont( new Font(display,fD[0]));
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		digraphTitleLabel.setLayoutData(gridData);
 		
 		
 		Button digraphRegistrationButton = new Button(shell, SWT.PUSH);
 		digraphRegistrationButton.setText("Register");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		digraphRegistrationButton.setLayoutData(gridData);
 		digraphRegistrationButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				DigraphRegistration.register(display, dp);
 			}
 		});
 		
 		
 		Button digraphLoginButton = new Button(shell, SWT.PUSH);
 		digraphLoginButton.setText("Log In");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 2;
 		digraphLoginButton.setLayoutData(gridData);
 		digraphLoginButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				if (!dp.isSet()) {
 					Popup.passwordNotSet(display, 3);
 				} else {
 					DigraphLogin.login(display, dp);
 				}
 			}
 		});
 		
 		
 		Button digraphHelpButton = new Button(shell, SWT.PUSH);
 		digraphHelpButton.setText("Help");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
 		gridData.horizontalSpan = 2;
 		gridData.verticalSpan = 1;
 		digraphHelpButton.setLayoutData(gridData);
 		digraphHelpButton.addSelectionListener(new SelectionAdapter() {
 			@Override
 			public void widgetSelected(SelectionEvent e) {
 				Popup.passwordHelp(display, 3);
 			}
 		});
 		
 		
 		shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
        
        display.dispose();
	}
	
	
//	public static void registration(SelectionEvent e) {
//		
//	}

}
