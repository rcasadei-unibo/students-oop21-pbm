//package main.view.profile;
//
//import java.text.DecimalFormat;
//
//import javafx.scene.control.Button;
//import javafx.scene.control.ListView;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.text.Text;
//import javafx.stage.Screen;
//import javafx.stage.Stage;
//import main.control.Controller;
//import main.view.GUIFactory;
//import main.view.GUIFactoryImpl;
//import main.view.MainScene;
//
//public class ProfilePage {
//
//    private static final int TEXT_DIM = 10;
//    private static final int TITLE_DIM = 15;
//
//    private final Controller controller;
//    private final DecimalFormat df = new DecimalFormat("###.##");
//
//    public ProfilePage(final Stage primaryStage, final BorderPane root, final Controller controller) {
//        final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
//                Screen.getPrimary().getBounds().getHeight());
//        final GUIFactory guiFactory = b.build();
//        this.controller = controller;
//
//        final Text titleInv = guiFactory.createText("Investment Accounts", TITLE_DIM);
//        final ListView<Object> listInvAccs = new ListView<>();
//        controller.getUsrEconomy().getInvestmentAccounts().forEach(acc -> {
//            listInvAccs.getItems().addAll(
//                    "Name: " + acc.getID(),
//                    "Balance: " + acc.getBalance(),
//                    "Invested Balance: " + acc.getInvestedBalance(),
//                    "");
//        });
//        final Text titleHol = guiFactory.createText("Holding Accounts", TITLE_DIM);
//        final ListView<Object> listHolAccs = new ListView<>();
//        controller.getUsrEconomy().getHoldingAccounts().forEach(acc -> {
//            listHolAccs.getItems().addAll(
//                    "Name: " + acc.getID(),
//                    "Value: " + this.df.format(acc.getTotalValue()),
//                    "");
//        });
//
//        final Pane rightLayout = guiFactory.createVerticalPanel();
//        final Button changePassword = guiFactory.createButton("Cambia Password");
//        changePassword.setOnAction(e -> {
//            this.controller.showPasswordChangeView();
//        });
//        final Button logOut = guiFactory.createButton("Disconnettiti");
//        logOut.setOnAction(e -> {
//            primaryStage.setScene(new LoginScene(primaryStage, new MainScene(primaryStage, this.controller).getScene(), controller).getScene());
//            primaryStage.centerOnScreen();
//        });
//
//        final Pane leftLayout = guiFactory.createVerticalPanel();
//        final Text name = guiFactory.createText("\n" + controller.getUsrInfo().getName() + "\n", TEXT_DIM);
//        final Text surname = guiFactory.createText("\n" + controller.getUsrInfo().getSurname() + "\n", TEXT_DIM);
//        final Text fc = guiFactory.createText("\n" + controller.getUsrInfo().getFc() + "\n", TEXT_DIM);
//        final Text email = guiFactory.createText("\n" + controller.getUsrInfo().getEMail() + "\n", TEXT_DIM);
//
//        final Pane centerLayout = guiFactory.createVerticalPanel();
// 
//        final Pane bottomLayout = guiFactory.createHorizontalPanel();
//
//        leftLayout.getChildren().addAll(name, surname, fc, email);
//        rightLayout.getChildren().addAll(changePassword, logOut);
//        centerLayout.getChildren().addAll(titleInv, listInvAccs, titleHol, listHolAccs);
//
//        root.setLeft(leftLayout);
//        root.setRight(rightLayout);
//        root.setCenter(centerLayout);
//        root.setBottom(bottomLayout);
//    }
//
//}
