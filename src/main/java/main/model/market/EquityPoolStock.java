package main.model.market;

import java.io.IOException;

import com.google.common.base.Optional;

public final class EquityPoolStock implements EquityPool {

	
	private EquityPoolStock() {
		super();
	}

	public Optional<Equity> getEquity(final String symbol) {
		try {
			return Optional.fromNullable(new EquityStock(symbol));
		} catch (IOException e) {
			// Logger.log(e.print....);
			e.printStackTrace();
		}
		return Optional.absent();
	}

	public Optional<Double> getEquityPrice(final String symbol) {
		final Optional<Equity> eq = getEquity(symbol);
		return eq.isPresent() ? Optional.fromNullable(eq.get().getPrice()) :
			Optional.absent();
	}
}
