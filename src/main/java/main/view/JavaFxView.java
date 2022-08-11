package main.view;

import java.util.List;
import java.util.Queue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.Controller;
import main.view.investment.InvestmentScene;
import main.view.profile.LoginScene;

public class JavaFxView extends Application implements View {

    private GUIFactory guiFactory;
    private BorderPane root;
    private static Stage stage;
    private static Controller controller;
    private static CustomScene investScene;
    private Pane menuBar;

    public JavaFxView() {
        super();

    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();

        menuBar = createMenuBar();

        final Scene mainScene = getMainScene();
        stage = primaryStage;
        primaryStage.setTitle("Bugmate - personal use");
        primaryStage.setScene(getLoginScene(primaryStage, mainScene));
        primaryStage.centerOnScreen();
        primaryStage.show();

        investScene = new InvestmentScene(mainScene, stage, createMenuBar(), Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight(), controller);
    }

    private Scene getLoginScene(final Stage primaryStage, final Scene mainScene) {
        final LoginScene loginscene = new LoginScene(primaryStage, mainScene);

        return loginscene.getScene();
    }

    private Pane createMenuBar() {
        final Pane menuBar = guiFactory.createHorizontalPanel();
        final Button investment = guiFactory.createButton("Investmenti"), profilo = guiFactory.createButton("Profilo"),
                bankAccount = guiFactory.createButton("Conti Bancari"), expenses = guiFactory.createButton("Spese"),
                savings = guiFactory.createButton("Salvadanai");

        investment.setOnAction(e -> getInvestmentPage());
        profilo.setOnAction(e -> getProfilePage(root));
        bankAccount.setOnAction(e -> getBankAccountPage());
        expenses.setOnAction(e -> getExpenditurePage());
        savings.setOnAction(e -> getSavingPage());
        menuBar.getChildren().addAll(profilo, investment, expenses, bankAccount, savings);
        return menuBar;
    }

    private Scene getMainScene() {
        root = new BorderPane();
        root.setTop(menuBar);
        return guiFactory.createScene(root);
    }

    private void getInvestmentPage() {
        controller.updateMarketInfo();

    }

    private void getProfilePage(final BorderPane root) {
        this.controller.showProfile();;
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

    @Override
    public void setObserver(final Controller observer) {
        controller = observer;
        
    }

    @Override
    public void show(final String[] args) {
        launch(args);
    }

    @Override
    public void marketUpdates(final Queue<List<?>> queue) {
        investScene.updateEverythingNeeded(queue);
        try {
            Platform.runLater(() -> stage.setScene(investScene.getScene()));
        } catch (IllegalArgumentException e) {
        }
    }

}
