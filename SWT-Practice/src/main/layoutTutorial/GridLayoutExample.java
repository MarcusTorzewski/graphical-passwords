package main.layoutTutorial;

import org.eclipse.swt.layout.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GridLayoutExample {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout);
		new Button(shell, SWT.PUSH).setText("B1");
		new Button(shell, SWT.PUSH).setText("Wide Button 2");
		new Button(shell, SWT.PUSH).setText("Button 3");
		new Button(shell, SWT.PUSH).setText("B4");
		new Button(shell, SWT.PUSH).setText("Button 5");
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
	}

}
