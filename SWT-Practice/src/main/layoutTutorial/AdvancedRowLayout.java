package main.layoutTutorial;

import org.eclipse.swt.layout.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

// following this - https://www.eclipse.org/articles/article.php?file=Article-Understanding-Layouts/index.html
public class AdvancedRowLayout {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		
		// Create the layout
		RowLayout layout = new RowLayout();
		
		layout.wrap = false;
		layout.pack = false;
		layout.justify = true;
		layout.type = SWT.VERTICAL;
		layout.marginLeft = 5;
		layout.marginTop = 5;
		layout.marginRight = 5;
		layout.marginBottom = 5;
		layout.spacing = 0;

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
