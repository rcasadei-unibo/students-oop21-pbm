package main.view.profile;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import main.control.Controller;
import main.control.ControllerImpl;
import main.view.GUIFactory;
import main.view.GUIFactoryImpl;

public class ProfilePage {

    private static final int TEXT_DIM = 10;

    private GUIFactory guiFactory;
    private final BorderPane root;
    private final Controller controller;

    public ProfilePage(final BorderPane root, final Controller controller) {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();
        this.root = root;
        this.controller = controller;

        final Pane rightLayout = this.guiFactory.createVerticalPanel();
        final Button changePassword = this.guiFactory.createButton("Cambia Password");
        changePassword.setOnAction(e -> this.controller.changePassword());
        final Button logOut = this.guiFactory.createButton("Disconnettiti");
        logOut.setOnAction(e -> this.controller.showLoginScene());

        final Pane leftLayout = this.guiFactory.createVerticalPanel();
        final Text name = this.guiFactory.createText("\n" + controller.getUsrInfo().getName() + "\n", TEXT_DIM);
        final Text surname = this.guiFactory.createText("\n" + controller.getUsrInfo().getSurname() + "\n", TEXT_DIM);
        final Text fc = this.guiFactory.createText("\n" + controller.getUsrInfo().getFc() + "\n", TEXT_DIM);
        final Text email = this.guiFactory.createText("\n" + controller.getUsrInfo().getEMail() + "\n", TEXT_DIM);

        final Pane centerLayout = this.guiFactory.createVerticalPanel();
        final Text nInvAcc = this.guiFactory.createText("\ncontroller.getInvAccs()\n", TEXT_DIM);
        final Text nHolAcc = this.guiFactory.createText("\ncontroller.getHolAccs()\n", TEXT_DIM);
 
        final Pane bottomLayout = this.guiFactory.createHorizontalPanel();

        leftLayout.getChildren().addAll(name, surname, fc, email);
        rightLayout.getChildren().addAll(changePassword, logOut);
        centerLayout.getChildren().addAll(nInvAcc, nHolAcc);

        root.setLeft(leftLayout);
        root.setRight(rightLayout);
        root.setCenter(centerLayout);
        root.setBottom(bottomLayout);
    }

}
