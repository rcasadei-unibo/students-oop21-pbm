package main.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginScene{

    private static final int W_RATIO = 5;
    private static final int H_RATIO = 3;

    private final BorderPane root;

    public LoginScene(final Stage primaryStage, final Scene mainScene) {
        this.root = new BorderPane();
        final VBox layout = new VBox();
        final TextField eMail = new TextField();
        eMail.setPromptText("e-Mail");
        final TextField password = new TextField();
        password.setPromptText("password");

        final Button access = new Button("accedi");
        access.setOnAction(e -> {
            primaryStage.setScene(mainScene);
            primaryStage.centerOnScreen();
        });

        final Button register = new Button("registrati");
        register.setOnAction(e -> {
            primaryStage.setScene(new RegistrationView(primaryStage, mainScene).getScene());
            primaryStage.centerOnScreen();
        });

        layout.getChildren().addAll(eMail, password, access, register);
        this.root.setCenter(layout);
    }

    public Scene getScene() {
        return new Scene(this.root, Screen.getPrimary().getBounds().getWidth() / W_RATIO, Screen.getPrimary().getBounds().getHeight() / H_RATIO);
    }
}
