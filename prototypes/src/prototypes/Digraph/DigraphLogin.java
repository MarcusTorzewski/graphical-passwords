package prototypes.Digraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import prototypes.Popup;

public class DigraphLogin {
	static String input = null;
	
	/**
	 * Login for Digraph methodology. User selects one of two pass images depending on
	 * where their key images (chosen at registration) appear in the grid. If they are
	 * in the same row - the user selects one of the images directly to the right of 
	 * the key images; if they are in the same column -  the user selects one of the 
	 * images directly below the key images. These both wrap around to the first tile
	 * if a key image appears at the end of a row or column; the pass image can also
	 * be the same as one of the key images if they appear next to each other in the 
	 * grid. It the key images appear in neither the same row or image - the user 
	 * selects on of the images that form a rectangle with the pass images.
	 * 
	 * @param display the display in use created by a parent call
	 * @param password Digraph password set at registration - if the password is not
	 * set a pop-up is displayed saying as such 
	 */
	public static void login(Display display, Digraph password) {
		if (!password.isSet()) {
			Popup.passwordNotSet(display, 3);
			return;
		}
		input = null;
		
		
		// ============== Generating Images to Display ==============
		
		ArrayList<String> remaining = new ArrayList<String>(Digraph.ALL_TILES);
		ArrayList<String> toDisplay = new ArrayList<String>();
		toDisplay.add(password.getTiles().getX());
		toDisplay.add(password.getTiles().getY());
		remaining.removeAll(toDisplay); // removes users password from remaining (no duplicates)
		Random r = new Random();
		
		for (int i = 0; i < (Digraph.GRID_SIZE - 2); i++) {
			int n = r.nextInt(remaining.size() - 1);
			toDisplay.add(remaining.get(n));
			remaining.remove(n);
		}
		
		Collections.shuffle(toDisplay);
		
		ArrayList<ArrayList <String>> digraph = new ArrayList<ArrayList <String>>(); // this 2d array is used to find the solutions
		
		for (int i = 0; i < 5; i++) {
			digraph.add(new ArrayList<String>());
		}
		
		int x = 0; // in the 2d array
		int y = 0; // in the 1d array
		int aX = -1, aY = -1, bX = -1, bY = -1;
		
		for (String t : toDisplay) {
			if (y >= 5) {
				y = 0;
				x++;
			}
			
			digraph.get(x).add(t);
			
			
			if (t.equals(password.getTiles().getX())) {
				aX = x;
				aY = y;
			} else if (t.equals(password.getTiles().getY())) {
				bX = x;
				bY = y;
			}
			
			y++;
		}
		
		
		// in theory this shouldn't be necessary
		if (aX == -1 || aY == -1 || bX == -1 || bY == -1 ) {
			System.err.println("ax,ay,bx,by not initialised!");
			System.out.println(aX);
			System.out.println(aY);
			System.out.println(bX);
			System.out.println(bY);
			return;
		}
		
		
		// ============== Finding the answers ==============
		
		ArrayList<String> answers = new ArrayList<String>();
		int answer1X, answer1Y, answer2X, answer2Y;
		
		// if a and b are in the same row
		if (aX == bX) {
			answer1X = aX;
			answer2X = aX;
			answer1Y = aY + 1;
			answer2Y = bY + 1;
			
			if ((answer1Y) > 4) {
				answer1Y = 0;
			} else if ((answer2Y) > 4) {
				answer2Y = 0;
			}
		}
		
		// if a and b are in the same column
		else if (aY == bY) {
			answer1Y = aY;
			answer2Y = aY;
			answer1X = aX + 1;
			answer2X = bX + 1;
			
			if ((answer1X) > 4) {
				answer1X = 0;
			} else if ((answer2X + 1) > 4) {
				answer2X = 0;
			}
		}
		
		// if a and b are in separate rows and columns
		else {
			answer1X = aX;
			answer1Y = bY;
			answer2X = bX;
			answer2Y = aY;
		}
		
		answers.add(digraph.get(answer1X).get(answer1Y));
		answers.add(digraph.get(answer2X).get(answer2Y));
		
		
		// ============== Creating the display ==============
		
		Shell shell = new Shell(display);
 		shell.setText("Digraph Login");
		
		GridLayout gridLayout = new GridLayout();
		GridData gridData;
		gridLayout.numColumns = 5;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;

 		shell.setLayout(gridLayout);
 		
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
 		infoLabel.setText("Click on 1 pass image:");
 		gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
 		gridData.horizontalSpan = 3;
        infoLabel.setLayoutData(gridData);
        
        
        Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("Confirm");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.horizontalSpan = 2;
        gridData.verticalSpan = 2;
        confirmButton.setLayoutData(gridData);
        
        
        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.horizontalSpan = 3;
        errorLabel.setLayoutData(gridData);
        
        
        // array of buttons are procedurally generated        
        ArrayList<Button> tiles = new ArrayList<Button>();
        
        for (int i = 0; i < Digraph.GRID_SIZE; i++) {
        	tiles.add(new Button(shell, SWT.NONE));
        	Button tile = tiles.get(i);
        	gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        	tile.setLayoutData(gridData);
        	
        	// setting the image
        	String value = toDisplay.get(i);
        	Image image = new Image(display, DigraphLogin.class.getResourceAsStream("/icons/" + value + ".png"));
        	tile.setImage(image);
        	
        	tile.addSelectionListener(new SelectionAdapter() {
        		@Override
        		public void widgetSelected(SelectionEvent e) {
        			
        			if (DigraphLogin.input != null) {
    					errorLabel.setText("You only need to pick one image!");
    					shell.pack();
    					return;
        			}
        			
        			errorLabel.setText("");
        			
        			DigraphLogin.input = value;
        		}
        	});
        }
        
        
        Button cancelButton = new Button(shell, SWT.PUSH);
        cancelButton.setText("Cancel");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        cancelButton.setLayoutData(gridData);
        gridData.horizontalSpan = 2;
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		shell.dispose();
        	}
        });
        new Label(shell, SWT.NONE).setText("");  // spacer
        
        
        Button clearButton = new Button(shell, SWT.PUSH);
        clearButton.setText("Clear");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        clearButton.setLayoutData(gridData);
        gridData.horizontalSpan = 2;
        clearButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		DigraphLogin.input = null;
        		errorLabel.setText("Entry cleared.");
        	}
        });
        
        
        confirmButton.addSelectionListener(new SelectionAdapter() {
        	@Override
            public void widgetSelected(SelectionEvent e) {
        		if (input == null) {
        			errorLabel.setText("You have not selected a tile.");
        			shell.pack();
        			return;
        		}
        		
        		if (answers.contains(input)) {
        			Popup.loginSuccess(display);
            		shell.dispose();
            		return;
        		} else {
        			input = null;
            		errorLabel.setText("Your password did not match your entry. Please try again.");
            		shell.pack();
            		return;
        		}
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
