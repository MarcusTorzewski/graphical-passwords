package prototypes;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class AlphaRegistration {
	private static int xPosition = 40;
	private static int yPosition = 30;
 	private static int width = 200;
 	private static int height = 30;
 	private static int scrollWidth = 500;
 	private static int scrollHeight = 350;

 	private static int shellWidth = 300;
 	private static int shellHeight = 200;

	public static void registration(AlphanumericPassword p) {
		Display display = new Display();

 		Shell shell = new Shell(display);
 		shell.setSize(shellWidth, shellHeight);
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
        infoLabel.setBounds(xPosition,yPosition, width, height);
        infoLabel.setText("Enter a password:");
 		
 		yPosition += 30;
 		
 		Text password = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		password.setText("");
 		password.setBounds(xPosition,yPosition, width, height);
 		password.setTextLimit(10);
 		
 		yPosition += 40;
 		
 		Button button = new Button(shell, SWT.PUSH);
 		button.setBounds(xPosition,yPosition, width, height);
        button.setText("Show Password");

 		yPosition += 40;

        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setBounds(xPosition,yPosition, width, height);

        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                p.setPassword(password.getText());
                display.dispose();
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
