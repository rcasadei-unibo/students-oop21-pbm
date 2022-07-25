package main.model.market;

import java.io.IOException;

import yahoofinance.Stock;

public class EquityStock extends EquityImpl {

	
	private final Stock stock;
	public EquityStock(final String symbol) throws IOException {
		super(symbol);
		stock = yahoofinance.YahooFinance.get(getSymbol());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getPrice() {
		return stock.getQuote().getPrice().doubleValue();
	}

}
