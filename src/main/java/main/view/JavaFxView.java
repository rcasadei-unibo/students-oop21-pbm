package main.view;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.base.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.Controller;
import main.model.account.NotEnoughFundsException;
import main.model.market.Equity;
import main.model.market.OrderImpl;
import main.view.investment.InvestmentScene;
import main.view.profile.LoginScene;

public class JavaFxView extends Application implements View {

    //in order to avoid using static here, We need a way to call controller on the main application thread
    //otherwise it will be null for those components who is calling this object from the Javafx thread.
    private static volatile GUIFactory guiFactory;
    private BorderPane root;
    private static volatile Stage stage;
    private static volatile Controller controller;
    private static volatile CustomScene investScene;
    private Pane menuBar;

    public JavaFxView() {
        super();

    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        guiFactory = b.build();

        final Scene mainScene = getMainScene();
        stage = primaryStage;
        primaryStage.setTitle("Bugmate - personal use");
        primaryStage.setScene(getLoginScene(primaryStage, mainScene));
        primaryStage.centerOnScreen();
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
        });

        investScene = new InvestmentScene(mainScene, stage, createMenuBar(), Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight(), controller);
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

    private Scene getLoginScene(final Stage primaryStage, final Scene mainScene) {
        final LoginScene loginscene = new LoginScene(primaryStage, mainScene, controller);
        return loginscene.getScene();
    }

    private Scene getMainScene() {
        return new MainScene(controller).getScene();
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

        Platform.runLater(() -> {
            try {
                investScene.updateEverythingNeeded(queue);
                stage.setScene(investScene.getScene());
            } catch (IllegalArgumentException e) {
                showMessage("something went wrong, cound't update the market info, please check out your internet.");
            }
        });

    }

    @Override
    public void showMessage(final String message) {
        Platform.runLater(() -> guiFactory.createInformationBox(message).showAndWait());

    }

}
