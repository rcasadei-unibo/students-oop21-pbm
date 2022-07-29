package main.application;

import main.control.Controller;
import main.control.ControllerImpl;
import main.view.View;
import main.view.ViewImpl;

public final class App {
	
	//private static final Controller C = new ControllerImpl();
	//private static final View V = new ViewImpl();

	private App() {
	}

	public static void main(final String[] args) {
		new ViewImpl().show(args);
	}

}
