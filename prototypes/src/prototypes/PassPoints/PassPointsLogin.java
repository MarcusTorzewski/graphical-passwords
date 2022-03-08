package prototypes.PassPoints;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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

public class PassPointsLogin {
	
	/**
	 * The standard login method for PassPoints. User re-selects the rough locations 
	 * that they did in registration, order must be preserved for a successful attempt.
	 * Lee-way will be given to the user; points doesn't need to be pixel perfect.
	 * @param display the display in use created by a parent call
	 * @param password the PassPoints password set at registration - if the password 
	 * is not set a pop-up is displayed to say as such
	 */
	public static void login(Display display, PassPoints password) {
		if (!password.isSet()) {
			Popup.passwordNotSet(display, 1);
			return;
		}
		
		ArrayList<TuplePair<Integer>> input = new ArrayList<TuplePair<Integer>>();
		String imageFilePath = "./Images/PassPointsImage" + password.getImageCode() + ".jpg";
		
		Shell shell = new Shell(display);
 		shell.setText("PassPoints Login");
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;
 		GridData gridData;

 		shell.setLayout(gridLayout);
 		
		
		Label infoLabel = new Label(shell, SWT.NONE);
        infoLabel.setText("Double-click on " + PassPoints.CAPACITY + "  points in this image:");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.horizontalSpan = 2;
        infoLabel.setLayoutData(gridData);
        
        
        Button cancelButton = new Button(shell, SWT.PUSH);
        cancelButton.setText("Cancel");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        cancelButton.setLayoutData(gridData);
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		shell.dispose();
        	}
        });
        
        
        Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("Confirm");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.verticalSpan = 2;
        confirmButton.setLayoutData(gridData);
        
        
        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.horizontalSpan = 2;
        errorLabel.setLayoutData(gridData);
        

        Button clearButton = new Button(shell, SWT.PUSH);
        clearButton.setText("Clear");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        clearButton.setLayoutData(gridData);
        clearButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		input.clear();
        		errorLabel.setText("Entry cleared.");
        	}
        });
        
        
        Image image = new Image(display, PassPointsRegistration.class.getResourceAsStream(imageFilePath));
        Label photo = new Label (shell, SWT.BORDER);
        gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gridData.horizontalSpan = 4;
        photo.setLayoutData(gridData);
        photo.setImage(image);

        photo.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println(e.x + " " + e.y);
				
				if (input.size() == PassPoints.CAPACITY) {
					errorLabel.setText("You've already picked " + PassPoints.CAPACITY + " points!");
					shell.pack();
					return;
				}
				
				if ((e.x <= 0)|| (e.y <= 0)) {
					errorLabel.setText("You cannot pick a point along a side of the image! Try to pick a point away from the edges.");
					shell.pack();
					return;
				}
				
				errorLabel.setText("");
				
				TuplePair<Integer> t = new TuplePair<Integer>(e.x, e.y);
				
				input.add(t);
			}

			@Override
			public void mouseDown(MouseEvent e) { return; }

			@Override
			public void mouseUp(MouseEvent e) { return; }
        });
        
        
        confirmButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (input.size() != PassPoints.CAPACITY) {
            		input.clear();
            		errorLabel.setText("You selected too few points! You must select " + PassPoints.CAPACITY + " points. Your current input has been deleted.");
					shell.pack();
					return;
            	}
            	
            	int correctCounter = 0;
            	// needed?
            	if (input.size() == password.getSize()) {
            		// trad for loop over for each because i is needed to fetch both sets
            		for (int i = 0; i < input.size(); i ++) {
            			TuplePair<Integer> t = input.get(i);
            			TuplePair<Integer> u = password.getPoints().get(i);
            			if ((u.getX() - 10 <= t.getX()) && (t.getX() <= u.getX() + 10)) {
            				if ((u.getY() - 10 <= t.getY()) && (t.getY() <= u.getY() + 10)) {
            					correctCounter++;
            					continue;
            				}
            				continue;
            			}
            		}
            	}
            	
            	if (correctCounter == password.getSize()) {
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
