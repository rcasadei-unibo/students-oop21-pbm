package main.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class RegistrationView {

    private static final int W_RATIO = 5;
    private static final int H_RATIO = 3;

    private final BorderPane root;

    public RegistrationView(final Stage primaryStage, final Scene mainScene) {
        this.root = new BorderPane();
        final VBox layout = new VBox();

        final TextField name = new TextField();
        name.setPromptText("nome");
        final TextField surName = new TextField();
        surName.setPromptText("cognome");
        final TextField fc = new TextField();
        fc.setPromptText("codice fiscale");
        final TextField eMail = new TextField();
        eMail.setPromptText("e-Mail");
        final TextField password = new TextField();
        password.setPromptText("password");
        final TextField confPass = new TextField();
        confPass.setPromptText("conferma password");

        final Button confirm = new Button("conferma");
        confirm.setOnAction(e -> {
            primaryStage.setScene(mainScene);
            primaryStage.centerOnScreen();
        });

        layout.getChildren().addAll(name, surName, fc, eMail, password, confPass, confirm);
        this.root.setCenter(layout);
    }

    public Scene getScene() {
        return new Scene(this.root, Screen.getPrimary().getBounds().getWidth() / W_RATIO, Screen.getPrimary().getBounds().getHeight() / H_RATIO);
    }
}
