package prototypes.PassPoints;

import java.util.ArrayList;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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

public class PassPointsRegistration {
	
	/**
	 * Standard registration method for PassPoints. User selects five points on
	 * a randomly selected image, order is preserved in the password.
	 * 
	 * @param display the display in use created by a parent call
	 * @param password PassPoints password - isSet() can be either true or false; 
	 * any existing password will be overwritten.
	 */
	public static void register(Display display, PassPoints password) {
		ArrayList<TuplePair<Integer>> input = new ArrayList<TuplePair<Integer>>();
		Random r = new Random();
		int n = r.nextInt(PassPoints.PASS_POINTS_IMAGES.length);
		
		String imageFilePath = "./Images/" + PassPoints.PASS_POINTS_IMAGES[n] + ".jpg";
		

		
		// ============== Creating the display ==============
		
		Shell shell = new Shell(display);
 		shell.setText("PassPoints Registration");
		
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
            	if (input.size() == PassPoints.CAPACITY) {
            		password.setImageCode(n);
            		password.setPoints(input);
            		Popup.registrationSuccess(display);
            		shell.dispose();
            		return;
            	} else {
            		input.clear();
            		errorLabel.setText("Your password must be " + PassPoints.CAPACITY + " points long. You're password so far has been deleted :)");
            	}
            }  
        });
        
        confirmButton.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.keyCode == SWT.CR) {
        			confirmButton.setSelection(true);
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
	 * Standard registration method for hybrid PassPoints. User selects three points on
	 * a randomly selected image, order is preserved in the password.
	 * 
	 * @param display the display in use created by a parent call
	 * @param password a PassPoints class the password - isSet() can be either true or false; 
	 * any existing password will be overwritten.
	 */
	public static void hybridRegister(Display display, PassPoints password) {
		ArrayList<TuplePair<Integer>> input = new ArrayList<TuplePair<Integer>>();
		Random r = new Random();
		int n = r.nextInt(PassPoints.HYBRID_KEY_IMAGES.length - 1);
		
		String imageFilePath = "./HybridImages/" + PassPoints.HYBRID_KEY_IMAGES[n] + ".jpg";
		
		
		// ============== Creating the display ==============
		
		Shell shell = new Shell(display);
 		shell.setText("Hybrid Registration");
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;
 		GridData gridData;

 		shell.setLayout(gridLayout);
 		
		
		Label infoLabel = new Label(shell, SWT.NONE);
        infoLabel.setText("Double-click on " + PassPoints.HYBRID_CAPACITY + "  points in this image:");
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
				
				if (input.size() == PassPoints.HYBRID_CAPACITY) {
					errorLabel.setText("You've already picked " + PassPoints.HYBRID_CAPACITY + " points!");
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
            	if (input.size() == PassPoints.HYBRID_CAPACITY) {
            		password.setImageCode(n);
            		password.setPoints(input);
            		Popup.registrationSuccess(display);
            		shell.dispose();
            		return;
            	} else {
            		input.clear();
            		errorLabel.setText("Your password must be " + PassPoints.HYBRID_CAPACITY + " points long. You're password so far has been deleted :)");
            	}
            }  
        });
        
        confirmButton.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.keyCode == SWT.CR) {
        			confirmButton.setSelection(true);
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
