package main.control;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javafx.concurrent.Task;
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
import main.model.profile.ProfileEconomy;
import main.model.profile.ProfileEconomyImpl;
import main.view.View;

public class ControllerImpl implements Controller {

    private ProfileEconomy profile;
    private final List<View> views;
    private final Market market;
    private final EquityPool ep;
    private final InvestmentViewObserver ivo;
    private static final int refleshRate = 5000;

    public ControllerImpl(final String[] args, final View... views) {
        super();
        // profile should be read from somewhere...If nobody does this work, i will do
        // it later: such as reading from the configuration file
        // based on the configuration, reads from various platform, be it locally, from
        // a database or create a new one.
        profile = new ProfileEconomyImpl();
        market = new MarketImpl();
        ep = new EquityPoolStock();
        ivo = new InvestmentViewObserverimpl(profile);

//        update something every s seconds  
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                updateMarketInfo();
//            }
//        }, 0, refleshRate);

        InvestmentAccountTypeFactory f = new InvestmentAccountTypeFactoryImpl();
        InvestmentAccount invAcc = f.createForFree("Etoro");
        HoldingAccount hAcc = new HoldingAccountImpl(new EquityPoolStock(), "Etoro");
        invAcc.deposit(10000);
        Order o = new OrderImpl(ep.getEquity("TSLA").get(), 0.7);
        market.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(ep.getEquity("AAPL").get(), 0.3);
        market.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(ep.getEquity("GME").get(), 3.0);
        market.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(ep.getEquity("JNJ").get(), 5.0);
        market.buyAsset(invAcc, hAcc, o);
        o = new OrderImpl(ep.getEquity("ETH-USD").get(), 1.0);
        market.buyAsset(invAcc, hAcc, o);
        profile.addHoldingAccount(hAcc);
        profile.addInvestmentAccount(invAcc);
        System.out.println(invAcc.getID());

        this.views = List.of(Arrays.copyOf(views, views.length));
        for (final var view : views) {
            view.setObserver(this);
            view.show(args);
        }

    }

    @Override
    public void buyStocks(final String symbol, final double shares, final String accountID) {
        try {
            market.buyAsset(getInvAccountById(accountID), getHolAccountById(accountID),
                    new OrderImpl(ep.getEquity(symbol).get(), shares));
        } catch (final IllegalArgumentException e) {
            views.forEach(x -> x.showMoneyNotEnoughMessage());
        }
        updateMarketInfo();
    }

    @Override
    public void sellStocks(final String symbol, final double shares, final String accountID) {
        try {
            market.sellAsset(getInvAccountById(accountID), getHolAccountById(accountID),
                    new OrderImpl(ep.getEquity(symbol).get(), shares));
        } catch (final IllegalArgumentException e) {
            views.forEach(x -> x.showMoneyNotEnoughMessage());
        }
        updateMarketInfo();
    }

    private InvestmentAccount getInvAccountById(final String accountID) {
        return profile.getInvestmentAccounts().stream().filter(x -> x.getID().equals(accountID)).findFirst().get();
    }

    private HoldingAccount getHolAccountById(final String accountID) {
        return profile.getHoldingAccounts().stream().filter(x -> x.getID().equals(accountID)).findFirst().get();
    }

    @Override
    public void updateMarketInfo() {

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
                queue.add(ivo.getAllInvAccountIDs());
                return queue;
            }
        };

        task.setOnSucceeded(e -> {
            views.forEach(v -> v.marketUpdates(task.getValue()));
        });
        new Thread(task).start();
    }

    @Override
    public void showProfile() {
        // TODO Auto-generated method stub

    }

}