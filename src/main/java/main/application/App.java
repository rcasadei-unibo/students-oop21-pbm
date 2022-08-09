package main.application;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import main.control.Controller;
import main.control.ControllerImpl;
import main.view.View;
import main.view.JavaFxView;

public final class App {
	
	//private static final Controller C = new ControllerImpl();
	//private static final View V = new ViewImpl();

	private App() {
	}

	public static void main(final String[] args) {
	    new ControllerImpl(args, new JavaFxView());
	   
	    
		
	}
}
