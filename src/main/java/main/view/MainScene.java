package main.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.Controller;

public class MainScene {

    private final Controller controller;
    private GUIFactory guiFactory;
    private final BorderPane root;
    private final Stage stage;

    public MainScene(final Stage stage, final Controller controller) {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();
        this.controller = controller;
        this.root = new BorderPane();
        this.stage = stage;

        this.root.setTop(createMenuBar());
        this.stage.centerOnScreen();
    }

    /**
     * 
     * @return Scene of MainScene
     */
    public Scene getScene() {
        return this.guiFactory.createScene(this.root);
    }

    private Pane createMenuBar() {
        final Pane menuBar = guiFactory.createHorizontalPanel();
        final Button investment = guiFactory.createButton("Investmenti");
        final Button profilo = guiFactory.createButton("Profilo");
        final Button bankAccount = guiFactory.createButton("Conti Bancari");
        final Button expenses = guiFactory.createButton("Spese");
        final Button savings = guiFactory.createButton("Salvadanai");

        investment.setOnAction(e -> getInvestmentPage());
        profilo.setOnAction(e -> getProfilePage());
        bankAccount.setOnAction(e -> getBankAccountPage());
        expenses.setOnAction(e -> getExpenditurePage());
        savings.setOnAction(e -> getSavingPage());
        menuBar.getChildren().addAll(profilo, investment, expenses, bankAccount, savings);
        return menuBar;
    }

    private void getInvestmentPage() {
        this.controller.updateMarketInfo();

    }

    private void getProfilePage() {
        this.controller.showProfile();
    }

    private void getBankAccountPage() {
        this.guiFactory.createInformationBox("da implementare giulio").showAndWait();
    }

    private void getExpenditurePage() {
        this.guiFactory.createInformationBox("da implementare paolo").showAndWait();
    }

    private void getSavingPage() {
        this.guiFactory.createInformationBox("da implementare giulio").showAndWait();
    }

    public final Pane getMenuBar() {
        return createMenuBar();
    }
}
