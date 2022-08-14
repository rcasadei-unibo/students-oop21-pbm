package main.control;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.control.investment.InvestmentViewObserver;
import main.control.investment.InvestmentViewObserverimpl;
import main.model.account.InvestmentAccount;
import main.model.account.InvestmentAccountTypeFactory;
import main.model.account.InvestmentAccountTypeFactoryImpl;
import main.model.market.Equity;
import main.model.market.EquityPool;
import main.model.market.EquityPoolStock;
import main.model.market.HoldingAccount;
import main.model.market.HoldingAccountImpl;
import main.model.market.Market;
import main.model.market.MarketImpl;
import main.model.market.Order;
import main.model.market.OrderImpl;
import main.model.profile.PasswordChangeByEmail;
import main.model.profile.PasswordChangeByFC;
import main.model.profile.PasswordChangeByOldPassword;
import main.model.profile.PasswordChanger;
import main.model.profile.ProfileCredentials;
import main.model.profile.ProfileEconomy;
import main.model.profile.ProfileEconomyImpl;
import main.model.profile.SimplePassword;
import main.view.GUIFactory;
import main.view.GUIFactoryImpl;
import main.view.View;
import main.view.profile.LoginScene;
import main.view.profile.PasswordChangeView;
import main.view.profile.ProfilePage;

public class ControllerImpl implements Controller {

    private ProfileEconomy profile;
    private ProfileCredentials profileCred; //these two cannot be final if we want to change profile
    private final List<View> views;

    public ControllerImpl(final String[] args, final View... views) {
        super();
        // profile should be read from somewhere...If nobody does this work, i will do
        // it later: such as reading from the configuration file
        // based on the configuration, reads from various platform, be it locally, from
        // a database or create a new one.
        profile = new ProfileEconomyImpl();
        this.profileCred = new ProfileCredentials("Mario", "Rossi", "MRRSS10T99533K", "mariorossi@studio.unibo.it", new SimplePassword("SuperMario"));

        InvestmentAccountTypeFactory f = new InvestmentAccountTypeFactoryImpl();
        InvestmentAccount invAcc = f.createForFree();
        HoldingAccount hAcc = new HoldingAccountImpl(new EquityPoolStock());
        Market m = new MarketImpl();
        EquityPool ep = new EquityPoolStock();
        invAcc.deposit(10000);
        Order o = new OrderImpl(ep.getEquity("TSLA").get(), 0.7);
        m.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(ep.getEquity("AAPL").get(), 0.3);
        m.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(ep.getEquity("GME").get(), 3.0);
        m.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(ep.getEquity("JNJ").get(), 5.0);
        m.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(ep.getEquity("ETH-USD").get(), 1.0);
        m.buyAsset(invAcc, hAcc, o);
        profile.addHoldingAccount(hAcc);
        profile.addInvestmentAccount(invAcc);

        this.views = List.of(Arrays.copyOf(views, views.length));
        for (final var view : views) {
            view.setObserver(this);
            view.show(args);
        }

    }

    @Override
    public void buyStocks(final String symbol, final double shares, final String accountID) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sellStocks(final String symbol, final double shares, final String accountID) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateMarketInfo() {
        final InvestmentViewObserver ivo = new InvestmentViewObserverimpl(this.profile);

        Task<Queue<List<?>>> task = new Task<Queue<List<?>>>() {
            @Override
            public Queue<List<?>> call() {
                final Queue<List<?>> queue = new LinkedList<>();
                final List<String> symbols = ivo.getAllHoldingSymbols();
                final List<Double> shares = ivo.getAllHoldingShares(symbols);
                final List<Double> prices = ivo.getAllHoldingInPrices(symbols);
                queue.add(symbols);
                queue.add(prices);
                queue.add(shares);
                queue.add(ivo.getAllHoldingInValue(prices, shares));
                return queue;
            }
        };

        task.setOnSucceeded(e -> {
            views.forEach(v -> v.marketUpdates(task.getValue()));
        });
        new Thread(task).start();
    }

    @Override
    public void showProfile(final BorderPane root) {
        final ProfilePage profile = new ProfilePage(root, this);
    }

    @Override
    public void registerProfile(final String name, final String surname, final String fc, final String eMail, final String password) {
        this.profileCred = new ProfileCredentials(name, surname, fc, eMail, new SimplePassword(password));
        this.profile = new ProfileEconomyImpl();
    }

    @Override
    public void accessProfile(final String eMail, final String password) {
        // this is just a version to let the application run
        //this method needs to retrieve the user profile from json database

    }

    @Override
    public void showLoginScene() {
        // TODO Auto-generated method stub
    }

    @Override
    public void showPasswordChangeView() {
        new PasswordChangeView(this);
    }

    @Override
    public ProfileCredentials getUsrInfo() {
        return this.profileCred;
    }

    @Override
    public void changePword(final String strategy, final String newPword, final String confPword, final String id) {
        final PasswordChanger changer;
        if ("Email".equals(strategy)) {
            changer = new PasswordChanger(new PasswordChangeByEmail(this.profileCred));
            changer.changePassword(newPword, confPword, id);
        } else if ("Password Attuale".equals(strategy)) {
            changer = new PasswordChanger(new PasswordChangeByOldPassword(this.profileCred));
            changer.changePassword(newPword, confPword, id);
        } else {
            changer = new PasswordChanger(new PasswordChangeByFC(this.profileCred));
            changer.changePassword(newPword, confPword, id);
        }
    }

}
