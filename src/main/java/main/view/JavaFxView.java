package main.view;

import java.util.List;
import java.util.Queue;
import com.google.common.base.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.Controller;
import main.control.ControllerImpl;
import main.view.investment.InvestmentScene;
import main.view.profile.LoginScene;
import main.view.profile.ProfileScene;

public class JavaFxView extends Application implements View {

    private GUIFactory guiFactory;
    private volatile Controller controller;
    private volatile CustomScene investScene;
    private volatile CustomScene profileScene;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();

        this.controller = new ControllerImpl(this, new Logger());

        primaryStage.setTitle("Bugmate - personal use");
        primaryStage.setScene(getLoginScene(primaryStage, getMainScene(primaryStage)));
        primaryStage.centerOnScreen();
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            this.controller.terminateApp();
        });

        this.investScene = new InvestmentScene(new BorderPane(), primaryStage, this.controller);
        this.profileScene = new ProfileScene(new BorderPane(), primaryStage, this.controller);
    }

    private Scene getLoginScene(final Stage primaryStage, final Scene mainScene) {
        final LoginScene loginscene = new LoginScene(primaryStage, mainScene, this.controller);
        return loginscene.getScene();
    }

    private Scene getMainScene(final Stage stage) {
        return new MainScene(stage, this.controller).getScene();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final Controller observer) {
        this.controller = observer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String message) {
        Platform.runLater(() -> this.guiFactory.createInformationBox(message).showAndWait());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView(final Optional<Queue<List<?>>> queue, final PageState pageState) {
        switch (pageState) {
        case PROFILE:
            this.profileScene.updateEverythingNeeded(queue.get());
            break;
        case BANKACCOUNT:
            break;
        case EXPENSE:
            break;
        case INVEST:
            this.investScene.updateEverythingNeeded(queue.get());
            break;
        default:
            break;
        }
    }

}
