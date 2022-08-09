package main.application;

import org.apache.log4j.BasicConfigurator;

import main.control.ControllerImpl;
import main.view.JavaFxView;

public final class App {

    // private static final Controller C = new ControllerImpl();
    // private static final View V = new ViewImpl();

    private App() {
    }

    public static void main(final String[] args) {
        BasicConfigurator.configure();
        new ControllerImpl(args, new JavaFxView());

    }
}
