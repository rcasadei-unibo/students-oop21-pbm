package main.view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ProfilePage {

    private static final int TEXT_DIM = 10;
    private GUIFactory guiFactory;

    public ProfilePage(final Stage window, final BorderPane root) {
        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        this.guiFactory = b.build();

        final Pane rightLayout = this.guiFactory.createVerticalPanel();
        final Button changePassword = this.guiFactory.createButton("cambia password");
        final Button logOut = this.guiFactory.createButton("disconnettiti");

        final Pane leftLayout = this.guiFactory.createVerticalPanel();
        final Text name = this.guiFactory.createText("\ncontroller.getName()\n", TEXT_DIM);
        final Text surname = this.guiFactory.createText("\ncontroller.getSurname()\n", TEXT_DIM);
        final Text fc = this.guiFactory.createText("\ncontroller.getFC()\n", TEXT_DIM);
        final Text email = this.guiFactory.createText("\ncontroller.getEmail()\n", TEXT_DIM);

        final Pane centerLayout = this.guiFactory.createVerticalPanel();
        final Text nInvAcc = this.guiFactory.createText("\ncontroller.getInvAccs\n", TEXT_DIM);
        final Text nHolAcc = this.guiFactory.createText("\ncontroller.getHolAccs\n", TEXT_DIM);
 
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
