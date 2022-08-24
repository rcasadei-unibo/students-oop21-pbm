package test.view;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import main.control.investment.InvestmentViewObserver;
import main.control.investment.InvestmentViewObserverimpl;
import main.model.account.InvestmentAccount;
import main.model.account.InvestmentAccountTypeFactory;
import main.model.account.InvestmentAccountTypeFactoryImpl;
import main.model.market.Equity;
import main.model.market.EquityPool;
import main.model.market.EquityPoolBasic;
import main.model.market.HoldingAccount;
import main.model.market.HoldingAccountImpl;
import main.model.market.Market;
import main.model.market.MarketImpl;
import main.model.market.Order;
import main.model.market.OrderImpl;
import main.model.profile.ProfileEconomy;
import main.model.profile.ProfileEconomyImpl;

public class TestGuiFactory {

    void testBlockScheda() {
//        ProfileEconomy p = new ProfileEconomyImpl();
//        InvestmentAccountTypeFactory f = new InvestmentAccountTypeFactoryImpl();
//        InvestmentAccount invAcc = f.createForFree();
//        HoldingAccount hAcc = new HoldingAccountImpl(new EquityPoolStock());
//        Market m = new MarketImpl();
//        EquityPool ep = new EquityPoolStock();
//        invAcc.deposit(10000);
//        Order o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("TSLA").get(), 0.7));
//        m.buyAsset(invAcc, hAcc, o);
//        o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("AAPL").get(), 0.3));
//        m.buyAsset(invAcc, hAcc, o);
//        o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("GME").get(), 3.0));
//        m.buyAsset(invAcc, hAcc, o);
//        o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("JNJ").get(), 5.0));
//        m.buyAsset(invAcc, hAcc, o);
//        o = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("ETH-USD").get(), 1.0));
//        m.buyAsset(invAcc, hAcc, o);
//        p.addHoldingAccount(hAcc);
//        p.addInvestmentAccount(invAcc);
//        List<String> desc = new ArrayList<>();
//        desc.add("Symbol   ");
//        desc.add("price    ");
//        desc.add("shares   ");
//        desc.add("Value    ");
//        InvestmentViewObserver ob = new InvestmentViewObserverimpl(p);
//        List<String> symbols = ob.getAllHoldingSymbols();
//        List<Double> prices = ob.getAllHoldingInPrices(symbols);
//        List<Double> shares = ob.getAllHoldingShares(symbols);
//        List<Double> value = ob.getAllHoldingInValue(prices, shares);
//        root.setLeft(guiFactory.createBlockScheda(guiFactory.createText("Stock", 30),
//                    guiFactory.transformStringIntoText(desc, 14), guiFactory.transformStringIntoText(symbols, 10)
//                    ,guiFactory.transformStringIntoText(prices, 10)
//                    ,guiFactory.transformStringIntoText(shares, 10)
//                    ,guiFactory.transformStringIntoText(value, 10)));
        
    }
}
