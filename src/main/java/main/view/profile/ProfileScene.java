package main.view.profile;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Queue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.control.Controller;
import main.view.BaseScene;
import main.view.MainScene;

public class ProfileScene extends BaseScene {

    private static final int TEXT_DIM = 10;
    private static final int TITLE_DIM = 15;

    private final Scene scene;
    private final BorderPane root;
    private final DecimalFormat df = new DecimalFormat("###.##");

    public ProfileScene(final BorderPane root, final Stage primaryStage, final Controller controller) {
        super(primaryStage, controller);
        this.root = root;
        this.scene = getGadgets().createScene(root);
    }

    @Override
    public void updateEverythingNeeded(final Queue<List<?>> things) {
        super.updateScene();
        super.getPrimaryStage().setScene(this.scene);
    }


    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    protected void updateTop() {
        this.root.setTop(super.getMenuBar());
    }

    @Override
    protected void updateBottom() {
        // nothing to be done here
        final Pane bottomLayout = getGadgets().createHorizontalPanel();
        this.root.setBottom(bottomLayout);
    }

    @Override
    protected void updateCenter() {
        final Pane centerLayout = getGadgets().createVerticalPanel();

        final Text titleInv = getGadgets().createText("Investment Accounts", TITLE_DIM);
        final ListView<Object> listInvAccs = new ListView<>();
        getController().getUsrEconomy().getInvestmentAccounts().forEach(acc -> {
            listInvAccs.getItems().addAll(
                    "Name: " + acc.getID(),
                    "Balance: " + this.df.format(acc.getBalance()) + " $",
                    "Invested Balance: " + this.df.format(acc.getInvestedBalance()) + " $",
                    "");
        });
        final Text titleHol = getGadgets().createText("Holding Accounts", TITLE_DIM);
        final ListView<Object> listHolAccs = new ListView<>();
        getController().getUsrEconomy().getHoldingAccounts().forEach(acc -> {
            listHolAccs.getItems().addAll(
                    "Name: " + acc.getID(),
                    "Value: " + this.df.format(acc.getTotalValue()) + " $",
                    "");
        });

        centerLayout.getChildren().addAll(titleInv, listInvAccs, titleHol, listHolAccs);
        this.root.setCenter(centerLayout);
    }

    @Override
    protected void updateLeft() {
        final Pane leftLayout = getGadgets().createVerticalPanel();
        final Text name = getGadgets().createText("\n" + getController().getUsrInfo().getName() + "\n", TEXT_DIM);
        final Text surname = getGadgets().createText("\n" + getController().getUsrInfo().getSurname() + "\n", TEXT_DIM);
        final Text fc = getGadgets().createText("\n" + getController().getUsrInfo().getFc() + "\n", TEXT_DIM);
        final Text email = getGadgets().createText("\n" + getController().getUsrInfo().getEMail() + "\n", TEXT_DIM);
        leftLayout.getChildren().addAll(name, surname, fc, email);
        this.root.setLeft(leftLayout);
    }

    @Override
    protected void updateRight() {
        final Pane rightLayout = getGadgets().createVerticalPanel();

        final Button changePassword = getGadgets().createButton("Cambia Password");
        changePassword.setOnAction(e -> {
            getController().showPasswordChangeView();
        });
        final Button logOut = getGadgets().createButton("Disconnettiti");
        logOut.setOnAction(e -> {
            getPrimaryStage().setScene(
                    new LoginScene(getPrimaryStage(), new MainScene(getPrimaryStage(), getController()).getScene(), getController())
                            .getScene());
            getPrimaryStage().centerOnScreen();
        });

        rightLayout.getChildren().addAll(changePassword, logOut);
        this.root.setRight(rightLayout);
    }
}
