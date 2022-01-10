package prototypes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AlphaLogin {
	private static int xPosition = 40;
	private static int yPosition = 30;
 	private static int width = 200;
 	private static int height = 30;
 	private static int scrollWidth = 500;
 	private static int scrollHeight = 350;

 	private static int shellWidth = 300;
 	private static int shellHeight = 200;
 	
 	private static String input = "";
	
	/**
	 * Standard alphanumeric login. User enters their password and it is checked against the actual password. 
	 * If it is incorrect the user tries again. 
	 * @param p
	 */
	public static void login(AlphanumericPassword p) {
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
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        

        button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                input = password.getText();
                if (p.checkMatch(input)) {
                	display.dispose();
                } else {
                	errorLabel.setText("Incorrect! Please try again");
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