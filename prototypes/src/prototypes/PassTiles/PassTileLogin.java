package prototypes.PassTiles;

import java.util.ArrayList;
import java.util.Collections;

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

public class PassTileLogin {
	
	/**
	 * Standard log-in method for PassTile. User re-selects all five of the images
	 * they chose in registration. Order is not preserved and the user can select
	 * them in any order.
	 * 
	 * @param display the display in use created by a parent call
	 * @param password the PassTile password set at registration -  if the password 
	 * is not set a pop-up is displayed to saying as such
	 */
	public static void login(Display display, PassTile password) {
		if (!password.isSet()) {
			Popup.passwordNotSet(display, 2);
			return;
		}
		
		// ============== Values & Display Set-up ==============
		
		ArrayList<String> input = new ArrayList<String>();
		
		ArrayList<String> toDisplay = PassTile.generateGrid(password.getTiles());
		
		
		// ============== Creating the display ==============
		
		Shell shell = new Shell(display);
 		shell.setText("PassTile Login");
		
		GridLayout gridLayout = new GridLayout();
		GridData gridData;
		gridLayout.numColumns = 5;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;

 		shell.setLayout(gridLayout);
 		
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
 		infoLabel.setText("Click on the " + PassTile.CAPACITY + "  images in your password:");
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
        
        for (int i = 0; i < PassTile.GRID_SIZE; i++) {
        	tiles.add(new Button(shell, SWT.NONE));
        	Button tile = tiles.get(i);
        	gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        	tile.setLayoutData(gridData);
        	
        	// setting the image
        	String value = toDisplay.get(i);
        	Image image = new Image(display, PassTileLogin.class.getResourceAsStream("./Images/" + value + ".png"));
        	tile.setImage(image);
        	
        	tile.addSelectionListener(new SelectionAdapter() {
        		@Override
        		public void widgetSelected(SelectionEvent e) {
        			if (input.contains(value)) {
        				errorLabel.setText("You cannot select the same image twice!");
    					shell.pack();
    					return;
        			}
        			
        			if (input.size() == PassTile.CAPACITY) {
    					errorLabel.setText("You've already picked " + PassTile.CAPACITY + " Images!");
    					shell.pack();
    					return;
        			}

    				errorLabel.setText("");
        			input.add(value);
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
        		input.clear();
        		errorLabel.setText("Entry cleared.");
        	}
        });
        

        confirmButton.addSelectionListener(new SelectionAdapter() {
        	@Override
            public void widgetSelected(SelectionEvent e) {
        		
        		switch (password.checkMatch(input)) {
        		case -1:
        			input.clear();
        			errorLabel.setText("Your password must be " + PassTile.CAPACITY + " points long. You're password so far has been deleted :)");
        			shell.pack();
        			return;
        		case 0:
        			input.clear();
        			errorLabel.setText("Your password did not match your entry. Please try again.");
        			shell.pack();
        			return;
        		case 1:
        			Popup.loginSuccess(display);
        			shell.dispose();
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
	
	/**
	 * Alternative login methodology for PassTile passwords. User must select a subset
	 * of images from their password specified by the window. Order is not preserved and
	 * they can select them in any order.
	 * 
	 * @param display the display in use created by a parent call
	 * @param password PassTile password set at registration - if the password has not 
	 * yet been set the user will receive a pop-up saying as such
	 */
	public static void loginBankStyle(Display display, PassTile password) {
		if (!password.isSet()) {
			Popup.passwordNotSet(display, 2);
			return;
		}
		
		// ============== Values & Display Set-up ==============
		
		ArrayList<String> input = new ArrayList<String>();
		
		ArrayList<String> answer = password.generateBankStyleSelection();
		
		ArrayList<String> toDisplay = PassTile.generateBankStyleGrid(answer, password.getTiles());
		
		// ============== Creating the display ==============
		
		Shell shell = new Shell(display);
 		shell.setText("PassTile Bank-Style Login");
		
		GridLayout gridLayout = new GridLayout();
		GridData gridData;
		gridLayout.numColumns = 5;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;

 		shell.setLayout(gridLayout);
 		
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
 		infoLabel.setText("Select " + PassTile.BANK_STYLE_SIZE + "  images:");
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
        
        for (int i = 0; i < PassTile.GRID_SIZE; i++) {
        	tiles.add(new Button(shell, SWT.NONE));
        	Button tile = tiles.get(i);
        	gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        	tile.setLayoutData(gridData);
        	
        	// setting the image
        	String value = toDisplay.get(i);
        	Image image = new Image(display, PassTileRegistration.class.getResourceAsStream("./Images/" + value + ".png"));
        	tile.setImage(image);
        	
        	tile.addSelectionListener(new SelectionAdapter() {
        		@Override
        		public void widgetSelected(SelectionEvent e) {
        			if (input.contains(value)) {
        				errorLabel.setText("You cannot select the same image twice!");
    					shell.pack();
    					return;
        			}
        			
        			if (input.size() == PassTile.BANK_STYLE_SIZE) {
    					errorLabel.setText("You've already picked " + PassTile.BANK_STYLE_SIZE + " points!");
    					shell.pack();
    					return;
        			}
        			
        			input.add(value);
        			System.out.println(input);
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
        		input.clear();
        		errorLabel.setText("Entry cleared.");
        	}
        });
        

        confirmButton.addSelectionListener(new SelectionAdapter() {
        	@Override
            public void widgetSelected(SelectionEvent e) {
            	if (input.size() != PassTile.BANK_STYLE_SIZE) {
            		input.clear();
            		errorLabel.setText("Your password must be " + PassTile.BANK_STYLE_SIZE + " points long. You're password so far has been deleted :)");
            		shell.pack();
            		return;
            	} 
            	
            	Collections.sort(input);
            	if (answer.equals(input)){
            		Popup.loginSuccess(display);
            		shell.dispose();
            		return;
            	} else {
            		input.clear();
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
