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
	 * Standard alphanumeric login. User enters their password and it is checked
	 * against the actual password. If it is incorrect the user must try again. 
	 * 
	 * @param display the display in use created by a parent call
	 * @param password alphanumeric password set at registration - if the password
	 * has not yet been set the user will receive a pop-up saying as such
	 */
	public static void login(Display display, AlphanumericPassword password) {
		if (!password.isSet()) {
			Popup.passwordNotSet(display, 0);
			return;
		}

		// ============== Creating the display ==============
		
 		Shell shell = new Shell(display);
 		shell.setText("Alphanumeric Login");
 		
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
        
 		
 		Text passwordLabel = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		passwordLabel.setText("");
 		passwordLabel.setTextLimit(10);
 		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
 		passwordLabel.setLayoutData(gridData);
 		

 		Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("Log In");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        confirmButton.setLayoutData(gridData);
        

        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        errorLabel.setLayoutData(gridData);
        
        
        confirmButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                input = passwordLabel.getText();
                if (password.checkMatch(input)) {
                	Popup.loginSuccess(display);
                	shell.dispose();
                } else {
                	errorLabel.setText("Incorrect! Please try again.");
                	passwordLabel.setText("");
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
	
	/**
	 * Alternative login methodology for alphanumeric passwords. User must enter 
	 * the characters corresponding to the digits requested. The digits are randomly
	 * generated inside this method.
	 * 
	 * @param display the display in use created by a parent call 
	 * @param password alphanumeric password set at registration - if the password has 
	 * not yet been set the user will receive a pop-up saying as such
	 */
	public static void bankStyleLogin(Display display, AlphanumericPassword password) {
		if (!password.isSet()) {
			Popup.passwordNotSet(display, 0);
			return;
		}
		
		// ============== Generating an answer ==============
		
		String answer = "";  // Seeing as we don't need to look at letters individually String is fine
		ArrayList<Integer> digits = new ArrayList<Integer>();
		Random rand = new Random();
		
		// Populates the digits array with every digit...
		for (int i = 0; i < password.getPassword().length(); i++) {
			digits.add(i);
		}
		
		// ...then pops random numbers from the digits array until it is the size of BANK_STYLE_ARRAY
		while(digits.size() > AlphanumericPassword.BANK_STYLE_SIZE) {
			int x = rand.nextInt(digits.size());
			digits.remove(x);
		}		
		
		for (int i = 0; i < digits.size(); i++) {
			answer += password.getPassword().charAt(digits.get(i));
		}
		
		System.out.println(digits);
		System.out.println(answer);
		
		
		// ============== Creating the display ==============
		
 		Shell shell = new Shell(display);
 		shell.setText("Alphanumeric Bank-Style Login");
 		
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
        
        
        ArrayList<Label> labels = new ArrayList<Label>();
        
        for (int i = 0; i < AlphanumericPassword.BANK_STYLE_SIZE; i ++) {
        	labels.add(new Label(shell, SWT.NONE));
        	Label label = labels.get(i);
        	gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        	label.setLayoutData(gridData);
        	
        	label.setText(Integer.toString(digits.get(i) + 1)); // to turn array index value to human index value
        }
        
        
        ArrayList<Text> textBoxes = new ArrayList<Text>();
        
        for (int i = 0; i < AlphanumericPassword.BANK_STYLE_SIZE; i ++) {
        	textBoxes.add(new Text(shell, SWT.BORDER | SWT.PASSWORD));
        	Text textBox = textBoxes.get(i);
        	textBox.setText("");
        	textBox.setTextLimit(1);
        	gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        	textBox.setLayoutData(gridData);
        }
        
 
 		Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("Confirm");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 4;
        confirmButton.setLayoutData(gridData);
        
        String a = answer;  // this is a hack. i do not like it

        confirmButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		input = "";
        		for (int i = 0; i < AlphanumericPassword.BANK_STYLE_SIZE; i ++) {
        			Text textBox = textBoxes.get(i);
        			if (textBox.getText().isEmpty()) {
        				errorLabel.setText("You have not inputted every character requested.");
        				emptyTextBoxes(textBoxes);
        				
        				shell.pack();
        				return;
        			} else {
        				input += textBox.getText();
        			}
        		}
        		
        		System.out.println(input);
        		if (a.equals(input)) {
        			Popup.loginSuccess(display);
            		shell.dispose();
            		return;
        		} else {
        			errorLabel.setText("The password is not correct.");
    				emptyTextBoxes(textBoxes);
    				
    				shell.pack();
    				return;
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
	
	
	public static void emptyTextBoxes(ArrayList<Text> textBoxes) {
		for (int i = 0; i < textBoxes.size(); i++) {
			Text textBox = textBoxes.get(i);
			textBox.setText("");
		}
	}
	
}