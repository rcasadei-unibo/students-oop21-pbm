package test.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
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

/**
 * JUnit basic evaluations to test with Gradle.
 */
public class TestMarket {

	private final InvestmentAccountTypeFactory accFactory = new InvestmentAccountTypeFactoryImpl();
	private final InvestmentAccount acc1 = accFactory.createForFree("Revolut");
	private final EquityPool ep = new EquityPoolStock();
	private final HoldingAccount hacc1 = new HoldingAccountImpl(ep, "Revolut");
	private final Market market = new MarketImpl();

	@Test
	void testMarketBuysSells() {
		// buying
		acc1.deposit(1000);
		final Order o1 = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("TSLA").get(), 0.2));
		market.buyAsset(acc1, hacc1, o1);
		Order o2 = new OrderImpl(new Pair<Equity, Double>(ep.getEquity("GME").get(), 5.0));
		market.buyAsset(acc1, hacc1, o2);
		Set<String> assets = new HashSet<>();
		assets.add("TSLA");
		assets.add("GME");
		assertEquals(assets, hacc1.getHoldingSymbols()); // {"TSLA", "GME"}
		assertTrue(acc1.getBalance() < 1000);
		assertTrue(hacc1.getTotalValue() > 0);

		// selling
		assertEquals(0, hacc1.howManyShares("OOP")); // 0.0
		assertEquals(5, hacc1.howManyShares("GME")); // 0.5
		assertEquals(0.2, hacc1.howManyShares("TSLA")); // 0.2

		Order o = new OrderImpl(ep.getEquity("AAPL").get(), 2.0);
		try {
			market.sellAsset(acc1, hacc1, o);
			fail();
		} catch (NullPointerException e) {
			//e.printStackTrace();
		}
		o = new OrderImpl(ep.getEquity("TSLA").get(), 0.2);
		market.sellAsset(acc1, hacc1, o);
		assertEquals(0, hacc1.howManyShares("TSLA"));

		o = new OrderImpl(ep.getEquity("GME").get(), 5.00001);
		market.sellAsset(acc1, hacc1, o);
		assertEquals(5, hacc1.howManyShares("GME"));
		o = new OrderImpl(ep.getEquity("GME").get(), 4.9);
		market.sellAsset(acc1, hacc1, o);
		assertTrue(hacc1.howManyShares("GME") < 0.2);
		o = new OrderImpl(ep.getEquity("GME").get(), hacc1.howManyShares("GME"));
		market.sellAsset(acc1, hacc1, o);
		assertEquals(0 , hacc1.howManyShares("GME")); //0.0
		
	}

}
