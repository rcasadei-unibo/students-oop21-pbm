package main.application;

import org.apache.log4j.BasicConfigurator;

import javafx.application.Application;
import main.control.ControllerImpl;
import main.view.JavaFxView;

public final class App {

    private App() {
    }

    public static void main(final String[] args) {
        BasicConfigurator.configure();
        //new ControllerImpl(args, new JavaFxView());
        Application.launch(JavaFxView.class, args);

    }
}
