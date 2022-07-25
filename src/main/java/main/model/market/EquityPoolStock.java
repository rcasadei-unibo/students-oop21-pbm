package main.model.market;

public final class EquityPool {
	
	private EquityPool() {
		super();
	}

	public static Equity requestEquity(final String symbol) {
		return new EquityImpl(symbol);
	}
	
	public static double getEquityPrice(final String symbol) {
		return new EquityImpl(symbol).getPrice();
	}
}
