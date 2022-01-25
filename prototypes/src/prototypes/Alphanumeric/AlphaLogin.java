package prototypes.Alphanumeric;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import prototypes.Popup;

public class AlphaLogin {
 	private static String input = "";
	
	/**
	 * Standard alphanumeric login. User enters their password and it is checked against the actual password. 
	 * If it is incorrect the user tries again. 
	 * @param p AlphanumericPassword class set at registration. If the password has not yet
	 * been set the user will receive a pop-up saying as such.
	 */
	public static void login(AlphanumericPassword p) {
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
                input = password.getText();
                if (p.checkMatch(input)) {
                	Popup.loginSuccess(display);
                	display.dispose();
                } else {
                	errorLabel.setText("Incorrect! Please try again.");
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