package prototypes.PIN;

import java.util.ArrayList;

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

public class PINRegistration {
	
	public static void register(PIN p) {
		ArrayList<Integer> input = new ArrayList<Integer>();
		
		Display display = new Display();
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
        infoLabel.setText("Enter a PIN:");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 4;
        infoLabel.setLayoutData(gridData);
        
        
        Label errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.horizontalSpan = 4;
        errorLabel.setLayoutData(gridData);
        
        
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
            	// Check if P# are null first
            	
            	input.add(Integer.parseInt(p1.getText()));
            	input.add(Integer.parseInt(p2.getText()));
            	input.add(Integer.parseInt(p3.getText()));
            	input.add(Integer.parseInt(p4.getText()));
            	
            	if (input.size() != 4) {
//            		errorLabel.setText("Your password must be 4 digits long.");
//            		p1.setText("");
//            		p2.setText("");
//            		p3.setText("");
//            		p4.setText("");
//            		shell.pack();
            	} else {
            		p.setPassword(input);
					Popup.registrationSuccess(display);
					display.dispose();
            	}
            }
        });
        
        
        
        
        // for only allowing digits
// 		text.addListener(SWT.Verify, new Listener() {
// 		      public void handleEvent(Event e) {
// 		        String string = e.text;
// 		        char[] chars = new char[string.length()];
// 		        string.getChars(0, chars.length, chars, 0);
// 		        for (int i = 0; i < chars.length; i++) {
// 		          if (!('0' <= chars[i] && chars[i] <= '9')) {
// 		            e.doit = false;
// 		            return;
// 		          }
// 		        }
// 		      }
// 		    });
        
        

 		
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
