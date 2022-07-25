package main.model.market;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Optional;

public class HoldingAccountImpl implements HoldingAccount {

	private Map<String, Double> holdings;
	private EquityPool equityPool;

	public HoldingAccountImpl(final Map<String, Double> holdings, final EquityPool ep) {
		super();
		this.holdings = holdings;
		equityPool = ep;
	}

	public HoldingAccountImpl(final EquityPool ep) {
		this(new HashMap<>(), ep);
	}

	@Override
	public Set<String> getHoldingSymbols() {
		return holdings.entrySet().stream().map(x -> x.getKey()).collect(Collectors.toSet());
	}
	@Override
	public double getTotalValue(){
		/**
		 * the x.getKey().get() might be absent. 
		 * So I need to decide whether to tell the user to wait or 
		 * just show an error.
		 */
		return holdings.entrySet().stream().mapToDouble(x -> equityPool
				.getEquityPrice(x.getKey()).get() * x.getValue())
				.sum();
	}
	
	@Override
	public void updateHoldingsForBuying(final Order order) {
		final String key = order.getEquity().getSymbol();
		final double shares = order.getShares();

		holdings.computeIfPresent(key, (k, val) -> val + shares);
		holdings.computeIfAbsent(key, k -> shares);

	}

	@Override
	public void updateHoldingsForSelling(final Order order) {
		final String key = order.getEquity().getSymbol();
		final double shares = order.getShares();

		holdings.computeIfPresent(key, (k, val) -> val - shares);

	}

	@Override
	public boolean hasEnoughShares(final Order order) {
		return holdings.get(order.getEquity().getSymbol()) >= order.getShares();
	}

}
