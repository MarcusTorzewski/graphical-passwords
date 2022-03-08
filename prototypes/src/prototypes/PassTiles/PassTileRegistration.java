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
import org.eclipse.swt.widgets.*;

import prototypes.Popup;
import prototypes.Support;
import prototypes.Digraph.DigraphRegistration;

public class PassTileRegistration {
	
	public static void register(Display display, PassTile password) {
		
		// ============== Generating the tiles ==============
		
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> remaining = new ArrayList<String>(PassTile.ALL_TILES);
		ArrayList<String> toDisplay = new ArrayList<String>();
		Random r = new Random();
		
		// toDisplay is populated randomly with GRID_SIZE strings from remaining
		// toDisplay is then used to populate the GUI
		for (int i = 0; i < PassTile.GRID_SIZE; i++) {
			int n = r.nextInt(remaining.size() - 1);
			toDisplay.add(remaining.get(n));
			remaining.remove(n);
		}
		
		System.out.println(remaining);
		System.out.println(toDisplay);
		
		
		// ============== Creating the display ==============
		
		Shell shell = new Shell(display);
 		shell.setText("PassTile Registration");
		
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
        			
    				errorLabel.setText("");
        			
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
            	if (input.size() == PassTile.CAPACITY) {
            		Collections.sort(input);
            		password.setTiles(input);
            		Popup.registrationSuccess(display);
            		shell.dispose();
            		return;
            	} else {
            		input.clear();
            		errorLabel.setText("Your password must be " + PassTile.CAPACITY + " points long. You're password so far has been deleted :)");
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
        
        Support.displaySelection(password.getTiles(), display);
        
        return;
	}
	
}
