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

public class PassPointsRegistration {
	
	public static void registration(PassPoints password) {
		ArrayList<TuplePair> input = new ArrayList<TuplePair>();
		
		Display display = new Display();
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
 		gridLayout.marginLeft = 5;
 		gridLayout.marginRight = 5;
 		gridLayout.marginTop = 5;
 		gridLayout.marginBottom = 5;
 		GridData gridData;

 		shell.setLayout(gridLayout);
 		
		
		Label infoLabel = new Label(shell, SWT.NONE);
        infoLabel.setText("Double-click on " + PassPoints.CAPACITY + "  points in this image:");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        infoLabel.setLayoutData(gridData);
        
        
        Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("Confirm");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.verticalSpan = 2;
        confirmButton.setLayoutData(gridData);
        
        
        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        errorLabel.setLayoutData(gridData);
        
        
        Image image = new Image(display, PassPointsRegistration.class.getResourceAsStream("./Images/PassPointsImage0.jpg"));
        Label photo = new Label (shell, SWT.BORDER);
        gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
        gridData.horizontalSpan = 2;
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
				
				TuplePair t = new TuplePair(e.x, e.y);
				
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
            		password.setImageCode(0);
            		password.setPoints(input);
            		Popup.registrationSuccess(display);
            		display.dispose();
            		return;
            	} else {
            		input.clear();
            		errorLabel.setText("Your password must be " + PassPoints.CAPACITY + " points long. You're password so far has been deleted :)");
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
