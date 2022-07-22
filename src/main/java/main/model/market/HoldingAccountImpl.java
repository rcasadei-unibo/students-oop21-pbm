package main.model.market;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class HoldingAccountImpl implements HoldingAccount {

	private Map<String, Double> holdings;

	public HoldingAccountImpl(final Map<String, Double> holdings) {
		super();
		this.holdings = holdings;
	}

	public HoldingAccountImpl() {
		holdings = new HashMap<>();
	}

	@Override
	public Set<String> getHoldingSymbols() {
		return holdings.entrySet().stream().map(x -> x.getKey()).collect(Collectors.toSet());
	}

	@Override
	public double getTotalValue() {
		return holdings.entrySet().stream().mapToDouble(x -> EquityPool
				.getEquityPrice(x.getKey()) * x.getValue())
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
		return holdings.containsKey(order.getEquity().getSymbol());
	}

}
