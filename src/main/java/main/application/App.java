package main.application;

import org.apache.log4j.BasicConfigurator;

import javafx.application.Application;
import main.view.JavaFxView;

/**
 * Main place for launch this app.
 *
 */
public final class App {

    private App() {
    }

    public static void main(final String[] args) {
        BasicConfigurator.configure();
        Application.launch(JavaFxView.class, args);

    }
}
