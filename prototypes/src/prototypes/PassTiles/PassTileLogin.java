package prototypes.PassTiles;

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

public class PassTileLogin {
	
	public static void login(Display display, PassTile password) {
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> remaining = new ArrayList<String>(PassTile.ALL_TILES);
		ArrayList<String> toDisplay = new ArrayList<String>(password.getTiles());
		remaining.removeAll(toDisplay); // removes users password from remaining (no duplicates)
		Random r = new Random();
		
		// (PassTiles.GRID_SIZE - password.getSize()) should account for the initial size of toDisplay
		for (int i = 0; i < (PassTile.GRID_SIZE - password.getSize()); i++) {
			int n = r.nextInt(remaining.size() - 1);
			toDisplay.add(remaining.get(n));
			remaining.remove(n);
		}
		
		Collections.shuffle(toDisplay); // needed to make sure the users password isn't just the first 5 icons
		
		System.out.println(remaining);
		System.out.println(toDisplay);
		
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		GridData gridData;
		gridLayout.numColumns = 5;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;

 		shell.setLayout(gridLayout);
 		
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
 		infoLabel.setText("Click on " + PassTile.CAPACITY + "  image:");
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
        	System.out.println(value);
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
        			
        			input.add(value);
        			System.out.println(input);
        		}
        	});
        }
        

        confirmButton.addSelectionListener(new SelectionAdapter() {
        	@Override
            public void widgetSelected(SelectionEvent e) {
            	if (input.size() != PassTile.CAPACITY) {
            		input.clear();
            		errorLabel.setText("Your password must be " + PassTile.CAPACITY + " points long. You're password so far has been deleted :)");
            		shell.pack();
            		return;
            	} 
            	
            	Collections.sort(input);
            	if (password.getTiles().equals(input)){
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
	
	public static void loginBankStyle(Display display, PassTile password) {
		int noOfIcons = 3; // in future could be a parameter
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> remaining = new ArrayList<String>(PassTile.ALL_TILES); // remove users tiles
		remaining.removeAll(password.getTiles()); // should remove users password from remaining (no duplicates)
		
		// to display is initially empty then filled with 3 random icons from the users password
		ArrayList<String> toDisplay = new ArrayList<String>();
		ArrayList<String> userPassword = new ArrayList<String>(password.getTiles());
		Random r = new Random();
		for (int i = 0; i < noOfIcons; i++) {
			int n = r.nextInt(userPassword.size() - 1);
			toDisplay.add(userPassword.get(n));
			userPassword.remove(n);
		}
		
		ArrayList<String> answer = new ArrayList<String>(toDisplay);
		
		
		
		// (PassTiles.GRID_SIZE - password.getSize()) should account for the initial size of toDisplay
		for (int i = 0; i < (PassTile.GRID_SIZE - answer.size()); i++) {
			int n = r.nextInt(remaining.size() - 1);
			toDisplay.add(remaining.get(n));
			remaining.remove(n);
		}
		
		Collections.shuffle(toDisplay); // needed to make sure the users password isn't just the first 5 icons
		
		System.out.println(remaining);
		System.out.println(toDisplay);
		
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		GridData gridData;
		gridLayout.numColumns = 5;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;

 		shell.setLayout(gridLayout);
 		
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
 		infoLabel.setText("Double-click on " + PassTile.CAPACITY + "  image:");
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
        	System.out.println(value);
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
        			
        			if (input.size() == PassTile.CAPACITY) {
    					errorLabel.setText("You've already picked " + PassTile.CAPACITY + " points!");
    					shell.pack();
    					return;
        			}
        			
        			input.add(value);
        			System.out.println(input);
        		}
        	});
        }
        

        confirmButton.addSelectionListener(new SelectionAdapter() {
        	@Override
            public void widgetSelected(SelectionEvent e) {
            	if (input.size() != PassTile.CAPACITY) {
            		input.clear();
            		errorLabel.setText("Your password must be " + PassTile.CAPACITY + " points long. You're password so far has been deleted :)");
            		shell.pack();
            		return;
            	} 
            	
            	Collections.sort(input);
            	if (password.getTiles().equals(input)){
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
