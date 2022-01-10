package main.layoutTutorial;

import org.eclipse.swt.layout.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

// following this - https://www.eclipse.org/articles/article.php?file=Article-Understanding-Layouts/index.html
public class SimpleRowLayout {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		// Create the layout
		RowLayout layout = new RowLayout();
		
		// Optionally set layout fields
		layout.wrap = true;
		
		// Set the layout with the composite
		shell.setLayout(layout);
		
		// Create the children of the composite
		new Button(shell, SWT.PUSH).setText("Button 1");
		new Button(shell, SWT.PUSH).setText("Wide Button 2");
		new Button(shell, SWT.PUSH).setText("B3");
		
		shell.pack();
		shell.open();
		
		while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
	}
}
