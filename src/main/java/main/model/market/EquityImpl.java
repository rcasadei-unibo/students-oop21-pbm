package main.model.market;

public abstract class EquityImpl implements Equity {

	
	private final String symbol;

	public EquityImpl(final String symbol) {
		super();
		this.symbol = symbol;
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public abstract double getPrice();

	/**
     * {@inheritDoc}
     */
	@Override
	public final String getSymbol() {
		return this.symbol;
	}

}
