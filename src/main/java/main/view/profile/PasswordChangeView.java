package main.view.profile;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.Controller;
import main.model.profile.PasswordChangeByEmail;
import main.model.profile.PasswordChangeByFC;
import main.model.profile.PasswordChangeByOldPassword;
import main.model.profile.PasswordChanger;
import main.model.profile.ProfileCredentials;
import main.view.GUIFactory;
import main.view.GUIFactoryImpl;

public class PasswordChangeView {

    private static final int W_RATIO = 5;
    private static final int H_RATIO = 3;
    private static final String EMAIL = "Email";
    private static final String CURRENT_PASSWORD = "Password Attuale";
    private static final String FISCAL_CODE = "Codice Fiscale";

    private final GUIFactory guiFactory;

    public PasswordChangeView(final Controller controller) {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();

        final Stage stage = new Stage();
        final BorderPane layout = new BorderPane();

        final ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(EMAIL, CURRENT_PASSWORD, FISCAL_CODE);

        final Pane bot = this.guiFactory.createHorizontalPanel();
        final Button confirm = this.guiFactory.createButton("Conferma");
        bot.getChildren().add(confirm);

        final Pane top = this.guiFactory.createVerticalPanel();
        final TextField id = new TextField();
        final TextField password = new PasswordField();
        password.setPromptText("Nuova Password");
        final TextField confirmPass = new PasswordField();
        confirmPass.setPromptText("Conferma Password");
        top.getChildren().addAll(choiceBox, id, password, confirmPass);

        choiceBox.setOnAction(e -> {
            final String value = choiceBox.getValue();
            if (EMAIL.equals(value)) {
                id.setPromptText(EMAIL);
            }
            if (CURRENT_PASSWORD.equals(value)) {
                id.setPromptText(CURRENT_PASSWORD);
            }
            if (FISCAL_CODE.equals(value)) {
                id.setPromptText(FISCAL_CODE);
            }
        });
        confirm.setOnAction(e -> {
            final String choice = choiceBox.getValue();
            try {
               if (EMAIL.equals(choice) || CURRENT_PASSWORD.equals(choice) || FISCAL_CODE.equals(choice)) {
                   controller.changePword(choice, password.getText(), confirmPass.getText(), id.getText());
                   this.guiFactory.createInformationBox("Password cambiata con successo").showAndWait();
                   stage.close();
               } else {
                   this.guiFactory.createInformationBox("Selezionare la metodologia di sostituzione della password").showAndWait();
               }
            } catch (IllegalArgumentException exception) {
                this.guiFactory.createInformationBox("I dati inseriti non sono corretti").showAndWait();
              }
        });

        layout.setTop(top);
        layout.setBottom(bot);
        final Scene scene = new Scene(layout, Screen.getPrimary().getBounds().getWidth() / W_RATIO, Screen.getPrimary().getBounds().getHeight() / H_RATIO);
        stage.setTitle("Cambia Password");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.showAndWait();
    }
}
