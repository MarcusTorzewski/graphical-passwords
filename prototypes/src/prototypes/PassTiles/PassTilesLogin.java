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
import prototypes.PassPoints.PassPoints;

public class PassTilesLogin {
	
	public static void login(PassTiles password) {
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> remaining = new ArrayList<String>(PassTiles.ALL_TILES); // remove users tiles
		ArrayList<String> toDisplay = new ArrayList<String>(password.getTiles());
		remaining.removeAll(toDisplay); // should remove users password from remaining (no duplicates)
		Random r = new Random();
		
		// (PassTiles.GRID_SIZE - password.getSize()) should account for the initial size of toDisplay
		for (int i = 0; i < (PassTiles.GRID_SIZE - password.getSize()); i++) {
			int n = r.nextInt(remaining.size() - 1);
			toDisplay.add(remaining.get(n));
			remaining.remove(n);
		}
		
		Collections.shuffle(toDisplay); // needed to make sure the users password isn't just the first 5 icons
		
		System.out.println(remaining);
		System.out.println(toDisplay);
		
		Display display = new Display();
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
 		infoLabel.setText("Double-click on " + PassPoints.CAPACITY + "  image:");
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
        
        for (int i = 0; i < PassTiles.GRID_SIZE; i++) {
        	tiles.add(new Button(shell, SWT.NONE));
        	Button tile = tiles.get(i);
        	gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        	tile.setLayoutData(gridData);
        	
        	// setting the image
        	String value = toDisplay.get(i);
        	System.out.println(value);
        	Image image = new Image(display, PassTilesRegistration.class.getResourceAsStream("./Images/" + value + ".png"));
        	tile.setImage(image);
        	
        	tile.addSelectionListener(new SelectionAdapter() {
        		@Override
        		public void widgetSelected(SelectionEvent e) {
        			if (input.contains(value)) {
        				errorLabel.setText("You cannot select the same image twice!");
    					shell.pack();
    					return;
        			}
        			
        			if (input.size() == PassTiles.CAPACITY) {
    					errorLabel.setText("You've already picked " + PassTiles.CAPACITY + " points!");
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
            	if (input.size() != PassTiles.CAPACITY) {
            		input.clear();
            		errorLabel.setText("Your password must be " + PassTiles.CAPACITY + " points long. You're password so far has been deleted :)");
            		shell.pack();
            		return;
            	} 
            	
            	Collections.sort(input);
            	if (password.getTiles().equals(input)){
            		Popup.loginSuccess(display);
            		display.dispose();
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
        
        display.dispose();
	}
}