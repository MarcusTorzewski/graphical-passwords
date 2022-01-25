package prototypes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Popup {
	static Label infoLabel;
	static Button confirmButton;
	
	public static void loginSuccess(Display display) {
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginLeft = 15;
 		gridLayout.marginRight = 15;
 		gridLayout.marginTop = 15;
 		gridLayout.marginBottom = 15;
		GridData gridData;
		
		shell.setLayout(gridLayout);
		
		infoLabel = new Label(shell, SWT.NONE);
		infoLabel.setText("Success! You have logged in.");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		infoLabel.setLayoutData(gridData);
		
		confirmButton = new Button(shell, SWT.PUSH);
		confirmButton.setText("OK");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		confirmButton.setLayoutData(gridData);
		
		confirmButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				display.dispose();
				return;
			}
		});
		
		shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
        
        display.dispose();
	}

	public static void registrationSuccess(Display display) {
		Shell shell = new Shell(display);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginLeft = 15;
 		gridLayout.marginRight = 15;
 		gridLayout.marginTop = 15;
 		gridLayout.marginBottom = 15;
		GridData gridData;
		
		shell.setLayout(gridLayout);
		
		infoLabel = new Label(shell, SWT.NONE);
		infoLabel.setText("Success! You have created a password.");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		infoLabel.setLayoutData(gridData);
		
		confirmButton = new Button(shell, SWT.PUSH);
		confirmButton.setText("OK");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		confirmButton.setLayoutData(gridData);
		
		confirmButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				display.dispose();
				return;
			}
		});
		
		shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
        	if (!display.readAndDispatch()) {
        		display.sleep();
        	}
        }
        
        display.dispose();
	}
	

}
