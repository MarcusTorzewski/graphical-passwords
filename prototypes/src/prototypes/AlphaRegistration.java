package prototypes;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class AlphaRegistration {
	private static int xPosition = 80;
	private static int yPosition = 30;
 	private static int width = 200;
 	private static int height = 30;
 	private static int scrollWidth = 500;
 	private static int scrollHeight = 350;

 	private static int shellWidth = 400;
 	private static int shellHeight = 200;

	public static void registration(AlphanumericPassword p) {
		Display display = new Display();

 		Shell shell = new Shell(display);
 		shell.setSize(shellWidth, shellHeight);
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
        infoLabel.setBounds(xPosition,yPosition, width, height);
        infoLabel.setText("Enter a password containing 5 or more characters:");
 		
 		yPosition += 30;
 		
 		Text password = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		password.setText("");
 		password.setBounds(xPosition,yPosition, width, height);
 		password.setTextLimit(10);
 		
 		yPosition += 40;
 		
 		Button button = new Button(shell, SWT.PUSH);
 		button.setBounds(xPosition,yPosition, width, height);
        button.setText("Show Password");

        xPosition -= 40;
 		yPosition += 40;

        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setBounds(xPosition,yPosition, 300, height);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));

        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (password.getText().length() > 5) {
					p.setPassword(password.getText());
					display.dispose();
            	} else {
            		errorLabel.setText("Your password must be over 5 characters long.");
            		password.setText("");
            	}
            }
        });
 		
 		
 		shell.open();
 		
 		while(!shell.isDisposed()) {
 			if (!display.readAndDispatch())
 				display.sleep();
 		}
 		display.dispose();
		return;
	}
}
