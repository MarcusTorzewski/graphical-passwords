package prototypes.Alphanumeric;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import prototypes.Popup;

import org.eclipse.swt.layout.*;

public class AlphaRegistration {
	
	/**
	 * Standard alphanumeric password registration. User enters a password. It is 
	 * checked against the conditions. If the password satisfies the conditions 
	 * it is accepted and the password is set. If not the user tries again.
	 * 
	 * @param display the display in use created by a parent call
	 * @param password alphanumeric password - isSet() can be either true or false; 
	 * any existing password will be overwritten
	 */
	public static void register(Display display, AlphanumericPassword password) {

		// ============== Creating the display ==============

		Shell shell = new Shell(display);
 		shell.setText("Alphanumeric Registration"); 		
 		
 		GridLayout gridLayout = new GridLayout();
 		gridLayout.numColumns = 1;
 		gridLayout.marginLeft = 15;
 		gridLayout.marginRight = 15;
 		gridLayout.marginTop = 15;
 		gridLayout.marginBottom = 15;
 		gridLayout.verticalSpacing = 5;
 		
 		GridData gridData = new GridData();
 		gridData.verticalAlignment = GridData.CENTER;
 		
 		shell.setLayout(gridLayout);
 		
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
        infoLabel.setText("Enter a password:");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        infoLabel.setLayoutData(gridData);
        
        
        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        errorLabel.setLayoutData(gridData);
        
 		
 		Text passwordLabel = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		passwordLabel.setText("");
 		passwordLabel.setTextLimit(10);
 		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
 		passwordLabel.setLayoutData(gridData);
 		
 		
 		Text passwordCheckLabel = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		passwordCheckLabel.setText("");
 		passwordCheckLabel.setTextLimit(10);
 		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
 		passwordCheckLabel.setLayoutData(gridData);
 		

 		Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("Confirm");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        confirmButton.setLayoutData(gridData);

        confirmButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if ((passwordLabel.getText().length() >= AlphanumericPassword.MINIMUM_SIZE) && (passwordLabel.getText().equals(passwordCheckLabel.getText()))) {
					password.setPassword(passwordLabel.getText());
					Popup.registrationSuccess(display);
					shell.dispose();
            	} else if (passwordLabel.getText().length() <= AlphanumericPassword.MINIMUM_SIZE) {
            		errorLabel.setText("Your password must be 6 characters long.");
            		passwordLabel.setText("");
            		passwordCheckLabel.setText("");
            		shell.pack();
            	} else {
            		errorLabel.setText("Your password does not match.");
            		passwordLabel.setText("");
            		passwordCheckLabel.setText("");
            		shell.pack();
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
        
        
        Button cancelButton = new Button(shell, SWT.PUSH);
        cancelButton.setText("Cancel");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        cancelButton.setLayoutData(gridData);
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		shell.dispose();
        	}
        });
        
 		
        shell.pack();
 		shell.open();
 		
 		while(!shell.isDisposed()) {
 			if (!display.readAndDispatch())
 				display.sleep();
 		}
 		
		return;
	}
}
