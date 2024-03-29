package prototypes.PIN;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import prototypes.Popup;

public class PINRegistration {
	
	public static void register(Display display, PIN password) {
		ArrayList<Integer> input = new ArrayList<Integer>();
		
		
		// ============== Creating the display ==============
		
 		Shell shell = new Shell(display);
 		shell.setText("Alphanumeric Registration");
 		
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
 		infoLabel.setText("Enter a PIN (one number per box):");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 4;
        infoLabel.setLayoutData(gridData);
        
        
        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 4;
        errorLabel.setLayoutData(gridData);
        
        
        // Listener which limits text entry to digits:
        VerifyListener digitsOnlyListener = new VerifyListener() {
        	@Override
        	public void verifyText(VerifyEvent e) {
        		String string = e.text;
        		char[] chars = new char[string.length()];
        		if (chars.length < 1) {
        			return;
        		}
        		string.getChars(0, chars.length, chars, 0);
        		if (!('0' <= chars[0] && chars[0] <= '9')) {
        			e.doit = false;
        			errorLabel.setText("Only enter numbers.");
        			return;
        		}
        	}
        };
        
        ArrayList<Text> textBoxes = new ArrayList<Text>();
        
        for (int i = 0; i < PIN.LENGTH; i++) {
        	textBoxes.add(new Text(shell, SWT.BORDER | SWT.PASSWORD));
        	Text textBox = textBoxes.get(i);
        	textBox.setText("");
        	textBox.setTextLimit(1);
        	gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
     		textBox.setLayoutData(gridData);
     		textBox.addVerifyListener(digitsOnlyListener);
        }
 		
        
        Button cancelButton = new Button(shell, SWT.PUSH);
        cancelButton.setText("Cancel");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 2;
        cancelButton.setLayoutData(gridData);
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		shell.dispose();
        	}
        });
 		
 		
 		Button confirmButton = new Button(shell, SWT.PUSH);
        confirmButton.setText("Confirm");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 2;
        confirmButton.setLayoutData(gridData);
        
        confirmButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	// Check if text boxes are null first:
            	for (int i = 0; i < PIN.LENGTH; i++) {
            		Text textBox = textBoxes.get(i);
            		if (textBox.getText().isEmpty()) {
            			errorLabel.setText("You have not inputted " + PIN.LENGTH + " digits.");
        				emptyTextBoxes(textBoxes);
        				
        				shell.pack();
        				return;
            		} else {
            			input.add(Integer.parseInt(textBox.getText()));
            		}
            	}
            	
        		password.setPassword(input);
				Popup.registrationSuccess(display);
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
	
	/**
	 * Simple methodology which empties each text box when the user presses clear
	 * @param textBoxes the array of textBoxes (which contain numbers) to be cleared
	 */
	public static void emptyTextBoxes(ArrayList<Text> textBoxes) {
		for (int i = 0; i < textBoxes.size(); i++) {
			Text textBox = textBoxes.get(i);
			textBox.setText("");
		}
	}

}
