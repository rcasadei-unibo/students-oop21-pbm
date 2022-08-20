package main.view;

import java.util.List;
import java.util.Queue;
import com.google.common.base.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.Controller;
import main.control.ControllerImpl;
import main.view.investment.InvestmentScene;
import main.view.profile.LoginScene;

public class JavaFxView extends Application implements View {

    private GUIFactory guiFactory;
    private BorderPane root;
    private Stage stage;
    private volatile Controller controller;
    private volatile CustomScene investScene;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        guiFactory = b.build();

        controller = new ControllerImpl(this);
        final Scene mainScene = getMainScene();
        stage = primaryStage;
        this.root = new BorderPane();
        primaryStage.setTitle("Bugmate - personal use");
        primaryStage.setScene(getLoginScene(primaryStage, mainScene));
        primaryStage.centerOnScreen();
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            controller.terminateApp();
        });

        investScene = new InvestmentScene(root, stage, createMenuBar(), controller);
    }

    private Pane createMenuBar() {
        final Pane menuBar = guiFactory.createHorizontalPanel();
        final Button investment = guiFactory.createButton("Investmenti"), profilo = guiFactory.createButton("Profilo"),
                bankAccount = guiFactory.createButton("Conti Bancari"), expenses = guiFactory.createButton("Spese"),
                savings = guiFactory.createButton("Salvadanai");

        investment.setOnAction(e -> investmentPage());
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
        return new MainScene(stage, controller).getScene();
    }

    private void investmentPage() {
        controller.updateMarketInfo();
    }

    private void getProfilePage() {
        controller.showProfile(stage, this.root);
        System.out.println(root);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final Controller observer) {
        controller = observer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String message) {
        Platform.runLater(() -> guiFactory.createInformationBox(message).showAndWait());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView(final Optional<Queue<List<?>>> queue, final PageState pageState) {
        switch (pageState) {
        case PROFILE:
            break;
        case BANKACCOUNT:
            break;
        case EXPENSE:
            break;
        case INVEST:
            investScene.updateEverythingNeeded(queue.get());
            break;
        default:
            break;
        }
    }

}
