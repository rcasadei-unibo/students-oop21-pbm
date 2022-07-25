package main.model.market;

public abstract class EquityImpl implements Equity {

	
	private final String symbol;

	public EquityImpl(final String symbol) {
		super();
		this.symbol = symbol;
	}

	@Override
	public abstract double getPrice();

	@Override
	public final String getSymbol() {
		return this.symbol;
	}

}
