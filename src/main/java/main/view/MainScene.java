package main.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import main.control.Controller;

public class MainScene {

    private final Controller controller;
    private GUIFactory guiFactory;
    private final BorderPane root;

    public MainScene(final Controller controller) {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();
        this.controller = controller;
        this.root = new BorderPane();

        this.root.setTop(createMenuBar());
    }

    public Scene getScene() {
        return this.guiFactory.createScene(this.root);
    }

    private Pane createMenuBar() {
        final Pane menuBar = guiFactory.createHorizontalPanel();
        final Button investment = guiFactory.createButton("Investmenti"), profilo = guiFactory.createButton("Profilo"),
                bankAccount = guiFactory.createButton("Conti Bancari"), expenses = guiFactory.createButton("Spese"),
                savings = guiFactory.createButton("Salvadanai");

        investment.setOnAction(e -> getInvestmentPage());
        profilo.setOnAction(e -> getProfilePage());
        bankAccount.setOnAction(e -> getBankAccountPage());
        expenses.setOnAction(e -> getExpenditurePage());
        savings.setOnAction(e -> getSavingPage());
        menuBar.getChildren().addAll(profilo, investment, expenses, bankAccount, savings);
        return menuBar;
    }

    private void getInvestmentPage() {
        controller.updateMarketInfo();

    }

    private void getProfilePage() {
        controller.showProfile(this.root);
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
