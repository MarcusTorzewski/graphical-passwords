package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

// from: https://self-learning-java-tutorial.blogspot.com/2016/11/eclipse-swt-tutorial.html
public class TextBox {
	private static int xPosition = 30;
	private static int yPosition = 30;
 	private static int width = 500;
 	private static int height = 30;
 	private static int scrollWidth = 500;
 	private static int scrollHeight = 350;

 	private static int shellWidth = 600;
 	private static int shellHeight = 600;
 	
 	private static void addTextToShell(Display display, Shell shell) {
 		Text text1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
 		text1.setText("Bordered text box");
 		text1.setBounds(xPosition,yPosition, width, height);
 		text1.setTextLimit(10);
 		
 		yPosition += 40;
 		
 		Text text2 = new Text(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
 		text2.setText("Bordered scrolled text box");
 		text2.setBounds(xPosition, yPosition, scrollWidth, scrollHeight);
 		text2.setEditable(false);
 	}
 	
 	public static void main(String[] args) {
 		Display display = new Display();
 		
 		Shell shell = new Shell(display);
 		shell.setSize(shellWidth, shellHeight);
 		
 		addTextToShell(display, shell);
 		
 		shell.open();
 		
 		while(!shell.isDisposed()) {
 			if (!display.readAndDispatch())
 				display.sleep();
 		}
 		display.dispose();
 	}
}
