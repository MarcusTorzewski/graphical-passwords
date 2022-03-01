package prototypes.Alphanumeric;

import java.util.ArrayList;
import java.util.Random;

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
	public static void login(Display display, AlphanumericPassword p) {
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
                	shell.dispose();
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
 		
		return;
	}
	
	public static void bankStyleLogin(Display display, AlphanumericPassword p) {
		String answer;
		ArrayList<Integer> digits = new ArrayList<Integer>();
		Random rand = new Random();
		
		for (int i = 0; i < 4; i++) {
			int x = rand.nextInt(p.getPassword().length());
			if (!digits.contains(x)) {
				digits.add(x);
			} else {
				i--;
			}
		}
		
		digits.sort(null);
		
		answer = "" + p.getPassword().charAt(digits.get(0)) + p.getPassword().charAt(digits.get(1)) 
				+ p.getPassword().charAt(digits.get(2)) + p.getPassword().charAt(digits.get(3));
		
		System.out.println(answer);
		System.out.println(digits);
		
 		Shell shell = new Shell(display);
 		
 		GridLayout gridLayout = new GridLayout();
 		gridLayout.numColumns = 4;
 		gridLayout.marginLeft = 15;
 		gridLayout.marginRight = 15;
 		gridLayout.marginTop = 15;
 		gridLayout.marginBottom = 15;
 		gridLayout.verticalSpacing = 5;
 		
 		GridData gridData = new GridData();
 		gridData.verticalAlignment = GridData.CENTER;
 		
 		shell.setLayout(gridLayout);
 		
 		
 		Label infoLabel = new Label(shell, SWT.NONE);
        infoLabel.setText("Enter the following characters of your password:");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 4;
        infoLabel.setLayoutData(gridData);
        
        
        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 4;
        errorLabel.setLayoutData(gridData);
        
        
        Label l1 = new Label(shell, SWT.NONE);
        l1.setText(digits.get(0).toString());
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        l1.setLayoutData(gridData);
        
        
        Label l2 = new Label(shell, SWT.NONE);
        l2.setText(digits.get(1).toString());
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        l2.setLayoutData(gridData);
        
        
        Label l3 = new Label(shell, SWT.NONE);
        l3.setText(digits.get(2).toString());
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        l3.setLayoutData(gridData);
        
        
        Label l4 = new Label(shell, SWT.NONE);
        l4.setText(digits.get(3).toString());
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        l4.setLayoutData(gridData);
        
        
        Text p1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		p1.setText("");
 		p1.setTextLimit(1);
 		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
 		p1.setLayoutData(gridData);
 		
 		
 		Text p2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		p2.setText("");
 		p2.setTextLimit(1);
 		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
 		p2.setLayoutData(gridData);
 		
 		
 		Text p3 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		p3.setText("");
 		p3.setTextLimit(1);
 		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
 		p3.setLayoutData(gridData);
 		
 		
 		Text p4 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		p4.setText("");
 		p4.setTextLimit(1);
 		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
 		p4.setLayoutData(gridData);
 		
 		
 		Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("Confirm");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 4;
        confirmButton.setLayoutData(gridData);
 		
 		
        
        confirmButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	String input = p1.getText() + p2.getText() + p3.getText() + p4.getText();
            	System.out.println(input);
            	
            	if (input.equals(answer)) {
            		Popup.loginSuccess(display);
            		shell.dispose();
            	} else {
            		errorLabel.setText("Incorrect.");
            		p1.setText("");
            		p2.setText("");
            		p3.setText("");
            		p4.setText("");
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
 		
		return;
	}
	
}