package main.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import main.control.investment.InvestmentViewObserver;
import main.control.investment.InvestmentViewObserverimpl;
import main.model.account.InvestmentAccount;
import main.model.account.InvestmentAccountTypeFactory;
import main.model.account.InvestmentAccountTypeFactoryImpl;
import main.model.market.Equity;
import main.model.market.EquityImpl;
import main.model.market.EquityPool;
import main.model.market.EquityPoolStock;
import main.model.market.HoldingAccount;
import main.model.market.HoldingAccountImpl;
import main.model.market.Market;
import main.model.market.MarketImpl;
import main.model.market.Order;
import main.model.market.OrderImpl;
import main.model.profile.ProfileEconomy;
import main.model.profile.ProfileEconomyImpl;

public class ViewImpl extends Application implements View {

	private GUIFactory guiFactory;
	private BorderPane root;

	@Override
	public void show(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final GUIFactoryImpl.Builder b = new GUIFactoryImpl.Builder(Screen.getPrimary().getBounds().getWidth(),
		        Screen.getPrimary().getBounds().getHeight());
		this.guiFactory = b.build();

		primaryStage.setTitle("Bugmate - personal use");
		primaryStage.setScene(getLoginScene(primaryStage, getMainScene()));
		//primaryStage.setScene(getMainScene());
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

	private Scene getLoginScene(final Stage primaryStage, final Scene mainScene) {
	    final LoginScene loginscene = new LoginScene(primaryStage, mainScene);

        return loginscene.getScene();
    }

	protected Scene getMainScene() {
		root = new BorderPane();
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
		root.setTop(menuBar);
		return guiFactory.createScene(root);
	}

	private void getInvestmentPage() {

	    ProfileEconomy p = new ProfileEconomyImpl();
	    InvestmentAccountTypeFactory f = new InvestmentAccountTypeFactoryImpl();
	    InvestmentAccount invAcc = f.createForFree();
	    HoldingAccount hAcc = new HoldingAccountImpl(new EquityPoolStock());
	    Market m = new MarketImpl();
	    EquityPool ep = new EquityPoolStock();
	    invAcc.deposit(10000);
	    Order o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("TSLA").get(), 0.7));
	    m.buyAsset(invAcc, hAcc, o);
	    o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("AAPL").get(), 0.3));
	    m.buyAsset(invAcc, hAcc, o);
	    o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("GME").get(), 3.0));
        m.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("JNJ").get(), 5.0));
        m.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("ETH-USD").get(), 1.0));
        m.buyAsset(invAcc, hAcc, o);
	    p.addHoldingAccount(hAcc);
	    p.addInvestmentAccount(invAcc);
	    List<String> desc = new ArrayList<>();
	    desc.add("Symbol   ");
	    desc.add("price    ");
	    desc.add("shares   ");
	    desc.add("Value    ");
	    InvestmentViewObserver ob = new InvestmentViewObserverimpl(p);
	    List<String> symbols = ob.getAllHoldingSymbols();
	    List<Double> prices = ob.getAllHoldingInPrices(symbols);
	    List<Double> shares = ob.getAllHoldingShares(symbols);
	    List<Double> value = ob.getAllHoldingInValue(prices, shares);
	    root.setLeft(guiFactory.createBlockScheda(guiFactory.createText("Stock", 30),
	                guiFactory.transformStringIntoText(desc, 14), guiFactory.transformStringIntoText(symbols, 10)
	                ,guiFactory.transformStringIntoText(prices, 10)
	                ,guiFactory.transformStringIntoText(shares, 10)
	                ,guiFactory.transformStringIntoText(value, 10)));
	    
		final Pane topBar = guiFactory.createHorizontalPanel();
		final Button buy = guiFactory.createButton("Buy"), sell = guiFactory.createButton("Sell");
		topBar.getChildren().addAll(buy, sell);
		root.setBottom(topBar);

	}

	private void getProfilePage(final BorderPane root) {
		//guiFactory.createInformationBox("da implementare alessandro").showAndWait();
        final Pane profilePage = new StackPane();
        final Label sium = new Label("SIUUUUM");
        profilePage.getChildren().add(sium);
        root.setCenter(profilePage);
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

}
