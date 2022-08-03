package main.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.view.GUIFactoryImpl.Builder;

public class ViewImpl extends Application implements View {

    private GUIFactory guiFactory;


	@Override
	public void show(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
		        Screen.getPrimary().getBounds().getHeight());
		this.guiFactory = b.build();

		primaryStage.setTitle("Bugmate - personal use");
		primaryStage.setScene(getLoginScene(primaryStage));
		//primaryStage.setScene(getMainScene());
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

	private Scene getLoginScene(final Stage primaryStage) {
	    final int wRatio = 5;
	    final int hRatio = 3;
	    final BorderPane root = new BorderPane();
	    final Pane layout = guiFactory.createVerticalPanel();

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

        final Button confirm = guiFactory.createButton("conferma");
        confirm.setOnAction(e -> {
            primaryStage.setScene(getMainScene());
            primaryStage.centerOnScreen();
        });

	    layout.getChildren().addAll(name, surName, fc, eMail, password, confPass, confirm);
	    root.setCenter(layout);
        return new Scene(root, Screen.getPrimary().getBounds().getWidth() / wRatio, Screen.getPrimary().getBounds().getHeight() / hRatio);
    }

    protected Scene getMainScene() {
		final BorderPane root = new BorderPane();
		final Pane bottomBar = guiFactory.createHorizontalPanel();
		final Button investment = guiFactory.createButton("Investmenti"), profilo = guiFactory.createButton("Profilo"),
				bankAccount = guiFactory.createButton("Conti Bancari"), expenses = guiFactory.createButton("Spese"),
				savings = guiFactory.createButton("Salvadanai");

		investment.setOnAction(e -> getInvestmentPage());
		profilo.setOnAction(e -> getProfilePage(root));
		bankAccount.setOnAction(e -> getBankAccountPage());
		expenses.setOnAction(e -> getExpenditurePage());
		savings.setOnAction(e -> getSavingPage());

		bottomBar.getChildren().addAll(profilo, investment, expenses, bankAccount, savings);
		root.setBottom(bottomBar);
		return guiFactory.createScene(root);
	}

	private void getInvestmentPage() {
		System.out.println("hehe");
	}

	private void getProfilePage(final BorderPane root) {
		//guiFactory.createInformationBox("da implementare alessandro").showAndWait();
        final Pane profilePage = new StackPane();
        final Label sium = new Label("SIUUUUM");
        profilePage.getChildren().add(sium);
        root.setCenter(profilePage);
	}

	private void getBankAccountPage() {
		guiFactory.createInformationBox("da implementare giulio").showAndWait();
	}

	private void getExpenditurePage() {
		guiFactory.createInformationBox("da implementare paolo").showAndWait();
	}

	private void getSavingPage() {
		guiFactory.createInformationBox("da implementare giulio").showAndWait();
	}

}
