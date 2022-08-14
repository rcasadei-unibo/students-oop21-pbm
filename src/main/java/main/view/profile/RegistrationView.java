package main.view.profile;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.view.GUIFactory;
import main.view.GUIFactoryImpl;
import main.control.Controller;

public class RegistrationView {

    private static final int W_RATIO = 5;
    private static final int H_RATIO = 3;

    private final BorderPane root;
    private GUIFactory guiFactory;
    private final Controller controller;

    public RegistrationView(final Stage primaryStage, final Scene mainScene, final Controller controller) {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();

        this.controller = controller;
        this.root = new BorderPane();
        final Pane textFieldLayout = this.guiFactory.createVerticalPanel();

        final TextField name = new TextField();
        name.setPromptText("nome");
        final TextField surname = new TextField();
        surname.setPromptText("cognome");
        final TextField fc = new TextField();
        fc.setPromptText("codice fiscale");
        final TextField eMail = new TextField();
        eMail.setPromptText("e-Mail");
        final TextField password = new PasswordField();
        password.setPromptText("password");
        final TextField confPass = new PasswordField();
        confPass.setPromptText("conferma password");

        final Pane buttonLayout = this.guiFactory.createHorizontalPanel();
        final Button register = this.guiFactory.createButton("Registrati");
        register.setOnAction(e -> {
            if (checkInputs(eMail.getText(), password.getText(), confPass.getText())) {
                controller.registerProfile(name.getText(), surname.getText(), fc.getText(), eMail.getText(), password.getText());
                primaryStage.setScene(mainScene);
                primaryStage.centerOnScreen();
            } else {
                guiFactory.createInformationBox("Email non valida o Password incorretta").showAndWait();
                e.consume();
            }
        });

        buttonLayout.getChildren().addAll(register);
        textFieldLayout.getChildren().addAll(name, surname, fc, eMail, password, confPass);
        this.root.setBottom(buttonLayout);
        this.root.setCenter(textFieldLayout);
    }

    private boolean checkInputs(final String eMail, final String password, final String confPass) {
        return eMail.contains("@") && password.equals(confPass);
    }

    public Scene getScene() {
        return new Scene(this.root, Screen.getPrimary().getBounds().getWidth() / W_RATIO, Screen.getPrimary().getBounds().getHeight() / H_RATIO);
    }
}
