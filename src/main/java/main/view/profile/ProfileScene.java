package main.view.profile;

import java.text.DecimalFormat;
import java.util.Iterator;
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

/**
 * Creates ProfileScene. Shows informations about the profile credentials
 * and economy.
 *
 */
public class ProfileScene extends BaseScene {

    private static final int TEXT_DIM = 10;
    private static final int TITLE_DIM = 15;

    private final Scene scene;
    private final BorderPane root;
    private Queue<List<?>> updateables;
    private final DecimalFormat df = new DecimalFormat("###.##");

    public ProfileScene(final BorderPane root, final Stage primaryStage, final Controller controller) {
        super(primaryStage, controller);
        this.root = root;
        this.scene = getGadgets().createScene(root);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEverythingNeeded(final Queue<List<?>> things) {
        this.updateables = things;
        super.updateScene();
        super.getPrimaryStage().setScene(this.scene);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return this.scene;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateTop() {
        this.root.setTop(super.getMenuBar());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateBottom() {
        // nothing to be done here
        final Pane bottomLayout = getGadgets().createHorizontalPanel();
        this.root.setBottom(bottomLayout);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void updateCenter() {
        final Iterator<List<?>> iter = this.updateables.iterator();
        final List<String> invAccIds = (List<String>) iter.next();
        final Iterator<Double> invAccValues = (Iterator<Double>) iter.next().iterator();
        final List<String> holAccIds = (List<String>) iter.next();
        final Iterator<Double> holAccValues = (Iterator<Double>) iter.next().iterator();

        final Pane centerLayout = getGadgets().createVerticalPanel();

        final Text titleInv = getGadgets().createText("Investment Accounts", TITLE_DIM);
        final ListView<Object> listInvAccs = new ListView<>();
        invAccIds.forEach(id -> {
            listInvAccs.getItems().addAll(
                    "Name: " + id,
                    "Balance: " + this.df.format(invAccValues.next()) + " $",
                    "Invested Balance: " + this.df.format(invAccValues.next()) + " $",
                    "");
        });
        final Text titleHol = getGadgets().createText("Holding Accounts", TITLE_DIM);
        final ListView<Object> listHolAccs = new ListView<>();
        holAccIds.forEach(id -> {
            listHolAccs.getItems().addAll(
                    "Name: " + id,
                    "Value: " + this.df.format(holAccValues.next()) + " $",
                    "");
        });

        centerLayout.getChildren().addAll(titleInv, listInvAccs, titleHol, listHolAccs);
        this.root.setCenter(centerLayout);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateRight() {
        final BorderPane rightLayout = new BorderPane();

        final Pane usrBtns = getGadgets().createVerticalPanel();
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
        usrBtns.getChildren().addAll(changePassword, logOut);

        final Pane ecoBtns = getGadgets().createVerticalPanel();
        final Button newAcc = getGadgets().createButton("Nuovo Account");
        newAcc.setOnAction(e -> {
            getController().showAddAccountView();
        });
        ecoBtns.getChildren().add(newAcc);

        rightLayout.setTop(usrBtns);
        rightLayout.setBottom(ecoBtns);
        this.root.setRight(rightLayout);
    }
}
