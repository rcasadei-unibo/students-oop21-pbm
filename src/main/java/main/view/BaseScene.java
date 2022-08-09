package main.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public abstract class BaseScene implements CustomScene {

    private final Scene mainScene;
    private final Stage primaryStage;
    private final GUIFactory gadgets;

    /**
     * The button to go back to home page.
     */
    protected final Button back;

    public BaseScene(final Scene mainScene, final Stage primaryStage, final double x, final double y) {
        super();
        this.mainScene = mainScene;
        this.primaryStage = primaryStage;
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(x, y);
        this.gadgets = b.build();

        // create some common UI for everybody :)
        back = getGadgets().createButton("<-");
        back.setOnAction(e -> {
            primaryStage.setScene(getMainScene());
            primaryStage.centerOnScreen();
        });
    }

    @Override
    public abstract Scene getScene();

    protected Scene getMainScene() {
        return mainScene;
    }

    protected Stage getPrimaryStage() {
        return primaryStage;
    }

    protected GUIFactory getGadgets() {
        return gadgets;
    }

}
