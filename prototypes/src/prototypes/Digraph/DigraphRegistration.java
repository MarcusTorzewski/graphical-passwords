package prototypes.Digraph;

import java.util.ArrayList;
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
import prototypes.TuplePair;
import prototypes.PassTiles.PassTile;
import prototypes.PassTiles.PassTileRegistration;

public class DigraphRegistration {
	static int selectionSize = 2;
	static int toDisplaySize = 5;
	
	/**
	 * Registration for Digraph methodology. User selects two tiles from a small selection.
	 * Their order is unimportant. After the user confirms their selection, they are show
	 * their selection to help memorise it.
	 * 
	 * @param display the display in use created by a parent call
	 * @param password Digraph password - isSet() can be either true or false; any
	 * existing password will be overwritten
	 */
	public static void register(Display display, Digraph password) {
		
		// ============== Generating Images to Display ==============

		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> remaining = new ArrayList<String>(PassTile.ALL_TILES);
		ArrayList<String> toDisplay = new ArrayList<String>();
		Random r = new Random();
		
		// toDisplay is populated randomly with a selection of images
		// toDisplay is then used to populate the GUI
		for (int i = 0; i < toDisplaySize; i++) {
			int n = r.nextInt(remaining.size() - 1);
			toDisplay.add(remaining.get(n));
			remaining.remove(n);
		}
		
		
		// ============== Creating the display ==============
		
		Shell shell = new Shell(display);
 		shell.setText("Digraph Registration");
		
		GridLayout gridLayout = new GridLayout();
		GridData gridData;
		gridLayout.numColumns = 5;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;

 		shell.setLayout(gridLayout);
 		
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
 		infoLabel.setText("Click on 2 image:");
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
        
        for (int i = 0; i < toDisplaySize; i++) {
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
        			
        			if (input.size() == selectionSize) {
    					errorLabel.setText("You've already picked 2 points!");
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
        		if (input.size() != 2) {
        			input.clear();
        			errorLabel.setText("You did not select 2 images. Your password so far has been deleted");
        		} else {
        			TuplePair<String> t = new TuplePair<String>(input.get(0), input.get(1));
        			password.setTiles(t);
            		Popup.registrationSuccess(display);
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
        
        Popup.displaySelection(display, input);
        
        return;
	}
	
}
