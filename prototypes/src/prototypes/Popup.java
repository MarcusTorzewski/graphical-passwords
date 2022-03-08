package prototypes;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import prototypes.Digraph.DigraphRegistration;

public class Popup {
	static Label titleLabel;
	static Label infoLabel;
	static Button confirmButton;
	
	/**
	 * Creates pop-up window for login success. Simply says "Success! You have logged in."
	 * @param display the display in use created by a parent call.
	 */
	public static void loginSuccess(Display display) {
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginLeft = 15;
 		gridLayout.marginRight = 15;
 		gridLayout.marginTop = 15;
 		gridLayout.marginBottom = 15;
		GridData gridData;
		
		shell.setLayout(gridLayout);
		
		
		infoLabel = new Label(shell, SWT.NONE);
		infoLabel.setText("Success! You have logged in.");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		infoLabel.setLayoutData(gridData);
		
		
		confirmButton = new Button(shell, SWT.PUSH);
		confirmButton.setText("OK");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		confirmButton.setLayoutData(gridData);
		
		confirmButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				return;
			}
		});
		
		
		shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
        
        return;
	}

	/**
	 * Creates pop-up window for registration success. Simply says "Success! You have created a password."
	 * @param display the display in use created by a parent call.
	 */
	public static void registrationSuccess(Display display) {
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginLeft = 15;
 		gridLayout.marginRight = 15;
 		gridLayout.marginTop = 15;
 		gridLayout.marginBottom = 15;
		GridData gridData;
		
		shell.setLayout(gridLayout);
		
		
		infoLabel = new Label(shell, SWT.NONE);
		infoLabel.setText("Success! You have created a password.");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		infoLabel.setLayoutData(gridData);
		
		
		confirmButton = new Button(shell, SWT.PUSH);
		confirmButton.setText("OK");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		confirmButton.setLayoutData(gridData);
		
		confirmButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				return;
			}
		});
		
		
		shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
        
       	return;
	}
	
	
	/**
	 * Creates pop-up window informing the user how the methodology selected works. 
	 * @param display the display in use created by a parent call.
	 * @param methodologyType int corresponding to the methodology 0 - alphanumeric 1 - PassPoints/Hybrid
	 * 2 - PassTile 3 - Digraph 4 - PIN
	 */
	public static void passwordHelp(Display display, int methodologyType) {		
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginLeft = 15;
 		gridLayout.marginRight = 15;
 		gridLayout.marginTop = 15;
 		gridLayout.marginBottom = 15;
		GridData gridData;
		
		shell.setLayout(gridLayout);
		
		
		Label titleLabel = new Label(shell, SWT.NONE);
 		gridData = new GridData(SWT.CENTER, SWT.FILL, true, true);
 		FontData[] fD = titleLabel.getFont().getFontData();
 		fD[0].setHeight(20);
 		titleLabel.setFont( new Font(display,fD[0]));
 		gridData.horizontalSpan = 4;
 		gridData.verticalSpan = 2;
 		titleLabel.setLayoutData(gridData);
		
		
		infoLabel = new Label(shell, SWT.NONE);
		gridData = new GridData(SWT.CENTER, SWT.FILL, true, true);
		infoLabel.setLayoutData(gridData);
		
		switch (methodologyType) {
			case 1:
				// PassPoints
		 		titleLabel.setText("PassPoints");
				infoLabel.setText("PassPoints is a graphical password which makes a password from an ordered series of points on an image\n"
						+ "selected by the user.\n"
						+ "In this implementation, you will be asked to select five points on an image. The order of selection matters.\n\n"
						+ "Register - to register double click five memorable points on the image shown to you and remember the order.\n\n"
						+ "Login - to log in select the same five points in the order you originally chose them.\n\n"
						+ "Hybrid - the hybrid variant is almost exactly the same from the users perspective. The only difference is that\n"
						+ "the image will actually be two images superimposed on each other.\n"
						+ "You should choose five points based on the harder to see image.\n\n"
						+ "To clear your password simply register again.");
				break;
			case 2:
				// PassTile
		 		titleLabel.setText("PassTile");
				infoLabel.setText("PassTile is a graphical password which makes a password from an unordered selection of\n"
						+ "pictures or icons selected by the user.\n"
						+ "In this implementation, you will be asked to select five icons. The order of selection does not matter.\n\n"
						+ "Register - to register click five memorable icons from the selection offered to you and remember them.\n\n"
						+ "Login - to log in select the same five tiles in any order. The tiles may not appear in the same\n"
						+ "location as they did at registration.\n\n"
						+ "Bank-Style - to log in select the amount of icons from your password indicated at the top (e.g. select 3).\n"
						+ "There will only be that amount of your password present in the selection shown.\n\n"
						+ "To clear your password simply register again.");
				break;
			case 3:
				// Digraph
		 		titleLabel.setText("Digraph");
				infoLabel.setText("The digraph substitution methodology is a methodology which makes use of the relation between two 'key' images\n"
						+ "(the ones which you will select at registration) to form unique passwords at login.\n"
						+ "There are specific rules for how the key images interact but they are relatively straightforward.\n\n"
						+ "Registration - to register select two memorable images from the selection shown to you. The order does not matter.\n\n"
						+ "Login - to log in regardless of where the two key images appear, only select ONE 'pass' image. The rules are as follows:\n\n"
						+ "In the same row - if both key images occurr in the same row, select either image directly to the right of the key images.\n"
						+ "If a key image appears at the end of a row, it wraps around and the first image of the same row will be a pass image.\n\n"
						+ "In the same column - if both key images occurr in the same column, select either image directly below the key images.\n"
						+ "If a key image appears at the bottom of a column, it wraps around and the top image of the same column will be a pass image.\n\n"
						+ "Do not share a row or column - if the two key images do not share a column, select either of the two images\n"
						+ "which form a rectangle with the key images.\n\n"
						+ "In the first two instances (same row or column), it is possible for a key image to also be a pass image if it occurrs\n"
						+ "directly after the other key image.\n\n"
						+ "To clear your password simply register again.");
				break;
			case 4:
				// PIN
		 		titleLabel.setText("PIN");
				infoLabel.setText("A PIN is a 4-digit code most often used in bank card transactions.\n"
						+ "There are no rules for this methodology other than that numbers are required.\n\n"
						+ "Registration - to register simply input four digits.\n\n"
						+ "Login - to log in simply re-type the same four digits you registered with in order.\n\n"
						+ "To clear your password simply register again.");
				break;
			case 0:
				// alphanumeric
		 		titleLabel.setText("Alphanumeric");
				infoLabel.setText("An alphanumeric password is a traditional password.\n"
						+ "The only rules for this methodology are that the password must be over 5 characters and\n"
						+ "case (e.g., 'E' vs 'e') matters.\n\n"
						+ "Registration - to register simply type in the password twice.\n\n"
						+ "Login - to log in simply re-type the same password you registered with.\n\n"
						+ "Bank-Style Login - to log in type in the characters coresponding to the numbers it requests.\n"
						+ "For example: if the password is 'Password', and it asks for the 1st, 3rd, 4th, and 6th \n"
						+ "characters, you would enter 'P', 's', 'w', and 'r'.\n\n"
						+ "To clear your password simply register again.");
				break;
			default:
				System.err.println("error");
				break;
		}
		
		
		confirmButton = new Button(shell, SWT.PUSH);
		confirmButton.setText("OK");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		confirmButton.setLayoutData(gridData);
		
		confirmButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				return;
			}
		});
		
		
		shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
        
        return;
	}
	
	/**
	 * Creates pop-up window informing the user they have not set a password for the methodology
	 * and therefore cannot perform a log-in attempt. 
	 * @param display the display in use created by a parent call.
	 * @param methodologyType int corresponding to the methodology 0 - alphanumeric 1 - PassPoints
	 * 2 - PassTile 3 - Digraph 4 - PIN 5 - Hybrid
	 */
	public static void passwordNotSet(Display display, int methodologyType) {
		String password;
		switch (methodologyType) {
		case 1:
			password = "PassPoints";
			break;
		case 2:
			password = "PassTile";
			break;
		case 3:
			password = "Digraph";
			break;
		case 4:
			password = "PIN";
			break;
		case 5:
			password = "hybrid PassPoints";
			break;
		default:
			password = "alphanumeric";
			break;
		}
		
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginLeft = 15;
 		gridLayout.marginRight = 15;
 		gridLayout.marginTop = 15;
 		gridLayout.marginBottom = 15;
		GridData gridData;
		
		shell.setLayout(gridLayout);
		
		
		infoLabel = new Label(shell, SWT.NONE);
		infoLabel.setText("You have not set a " + password + " password!\nClick the register button above to set one up.");
		gridData = new GridData(SWT.CENTER, SWT.FILL, true, true);
		infoLabel.setLayoutData(gridData);
		
		
		confirmButton = new Button(shell, SWT.PUSH);
		confirmButton.setText("OK");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		confirmButton.setLayoutData(gridData);
		
		confirmButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				return;
			}
		});
		
		
		shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
        
        return;
	}
	
	/**
	 * Creates a pop-up window showing the user their selection, it is shown just after the complete
	 * registration. This is to make their selection clear and easier to memorise. It is only for
	 * tile based implementations (PassTiles & Digraph)
	 * @param selection the arrayList of image names to be displayed
	 * @param display the display in use created by a parent call
	 */
	public static void displaySelection(ArrayList<String> selection, Display display) {
		Shell shell = new Shell(display);
 		shell.setText("Digraph Registration");
		
		GridLayout gridLayout = new GridLayout();
		GridData gridData;
		gridLayout.numColumns = selection.size();
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;

 		shell.setLayout(gridLayout);
 		
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
 		infoLabel.setText("Here is your password. Try to remember it.\nIf you forget your password you can reset it by registering again.");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
 		gridData.horizontalSpan = selection.size();
        infoLabel.setLayoutData(gridData);
        
        
        ArrayList<Button> tiles = new ArrayList<Button>();
        
        for (int i = 0; i < selection.size(); i++) {
        	tiles.add(new Button(shell, SWT.NONE));
        	Button tile = tiles.get(i);
        	gridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
        	tile.setLayoutData(gridData);
        	
        	String value = selection.get(i);
        	Image image = new Image(display, DigraphRegistration.class.getResourceAsStream("./Images/" + value + ".png"));
        	tile.setImage(image);
        }
        
        
        Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("OK");
        gridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
        confirmButton.setLayoutData(gridData);
        gridData.horizontalSpan = selection.size();
        confirmButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		shell.dispose();
        	}
        });
        
        
        shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
        
		return;
	}

}
