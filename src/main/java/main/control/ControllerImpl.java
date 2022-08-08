package main.control;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    private final ProfileEconomy profile;
    private final List<View> views;

    public ControllerImpl(final String[] args, final View... views) {
        super();
        // profile should be read from somewhere...If nobody does this work, i will do
        // it later: such as reading from the configuration file
        // based on the configuration, reads from various platform, be it locally, from
        // a database or create a new one.
        profile = new ProfileEconomyImpl();

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
        final InvestmentViewObserver ivo = new InvestmentViewObserverimpl(profile);

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

}