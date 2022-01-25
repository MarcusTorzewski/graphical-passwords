package prototypes.Alphanumeric;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import prototypes.Popup;

import org.eclipse.swt.layout.*;

public class AlphaRegistration {
	
	/**
	 * Standard alphanumeric password registration. User enters a password. It is checked against the conditions.
	 * If the password satisfies the conditions it is accepted and the password is set. If not the user tries again.
	 * @param p AlphanumericPassword class. isSet() can be either true or false. 
	 * The existing password will be overwritten.
	 */
	public static void register(AlphanumericPassword p) {
		Display display = new Display();

 		Shell shell = new Shell(display);
 		
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
 		
 		Text password = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		password.setText("");
 		password.setTextLimit(10);
 		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
 		password.setLayoutData(gridData);

 		Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("Show Password");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        confirmButton.setLayoutData(gridData);

        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        errorLabel.setLayoutData(gridData);

        confirmButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (password.getText().length() > 5) {
					p.setPassword(password.getText());
					Popup.registrationSuccess(display);
					display.dispose();
            	} else {
            		errorLabel.setText("Your password must be over 5 characters long.");
            		password.setText("");
            		shell.pack();
            	}
            }
        });
 		
        shell.pack();
 		shell.open();
 		
 		while(!shell.isDisposed()) {
 			if (!display.readAndDispatch())
 				display.sleep();
 		}
 		display.dispose();
		return;
	}
}
