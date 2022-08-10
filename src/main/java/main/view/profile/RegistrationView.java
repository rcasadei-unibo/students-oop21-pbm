package main.view.profile;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.view.GUIFactory;
import main.view.GUIFactoryImpl;

public class RegistrationView {

    private static final int W_RATIO = 5;
    private static final int H_RATIO = 3;

    private final BorderPane root;
    private GUIFactory guiFactory;

    public RegistrationView(final Stage primaryStage, final Scene mainScene) {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();

        this.root = new BorderPane();
        final Pane textFieldLayout = this.guiFactory.createVerticalPanel();

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

        final Pane buttonLayout = this.guiFactory.createHorizontalPanel();
        final Button register = this.guiFactory.createButton("Registrati");
        register.setOnAction(e -> {
            primaryStage.setScene(mainScene);
            primaryStage.centerOnScreen();
        });

        buttonLayout.getChildren().addAll(register);
        textFieldLayout.getChildren().addAll(name, surName, fc, eMail, password, confPass);
        this.root.setBottom(buttonLayout);
        this.root.setCenter(textFieldLayout);
    }

    public Scene getScene() {
        return new Scene(this.root, Screen.getPrimary().getBounds().getWidth() / W_RATIO, Screen.getPrimary().getBounds().getHeight() / H_RATIO);
    }
}
