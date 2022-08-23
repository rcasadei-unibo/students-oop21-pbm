package main.view.profile;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.Controller;
import main.view.GUIFactory;
import main.view.GUIFactoryImpl;
import main.view.SubscriptionPlans;

public class AddAccountView {
    private static final int W_RATIO = 5;
    private static final int H_RATIO = 3;

    private final GUIFactory guiFactory;

    public AddAccountView(final Controller controller) {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();

        final Stage stage = new Stage();
        final BorderPane layout = new BorderPane();

        //TOP
        final Pane top = this.guiFactory.createHorizontalPanel();
        final Text fee = this.guiFactory.createText("    Fee: 0.05", 6);
        final ComboBox<SubscriptionPlans> subPlan = new ComboBox<>();
        subPlan.getItems().addAll(
                SubscriptionPlans.BRONZE,
                SubscriptionPlans.SILVER,
                SubscriptionPlans.GOLD,
                SubscriptionPlans.PLATINUM,
                SubscriptionPlans.DIAMOND,
                SubscriptionPlans.CHALLENGER);
        subPlan.setOnAction(e -> {
            fee.setText("    Fee: " + subPlan.getValue().getFee().toString());
        });
        subPlan.getSelectionModel().selectFirst();

        top.getChildren().addAll(subPlan, fee);

        //CENTER
        final Pane center = this.guiFactory.createVerticalPanel();
        final TextField accName = new TextField();
        accName.setPromptText("Nome account");
        accName.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toLowerCase());
            return change;
        }));
        final TextField accWorth = new TextField();
        accWorth.setPromptText("Quantitativo da depositare");
 
        center.getChildren().addAll(accName, accWorth);

        //BOTTOM
        final Pane bot = this.guiFactory.createHorizontalPanel();
        final Button addAcc = this.guiFactory.createButton("Crea");
        addAcc.setOnAction(e -> {
            if (inputsOk(accName.getText(), accWorth.getText())) {
                if (controller.createAcc(accName.getText(), Double.parseDouble(accWorth.getText()), subPlan.getValue())) {
                    controller.showProfile();
                    stage.close();
                } else {
                    this.guiFactory.createInformationBox("Account Esistente").showAndWait();
                }
            } else {
                this.guiFactory.createInformationBox("Input Incorretti").showAndWait();
            }
        });
        bot.getChildren().add(addAcc);

        //SETUP
        layout.setTop(top);
        layout.setCenter(center);
        layout.setBottom(bot);

        final Scene scene = new Scene(layout, Screen.getPrimary().getBounds().getWidth() / W_RATIO, Screen.getPrimary().getBounds().getHeight() / H_RATIO);

        stage.setTitle("Aggiungi Account");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    private boolean inputsOk(final String name, final String money) {
        try {
            Double.parseDouble(money);
        } catch (NumberFormatException e) {
            return false;
        }
        return !name.isBlank();
    }
}
