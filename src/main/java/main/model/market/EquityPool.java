package main.model.market;

import com.google.common.base.Optional;

/**
 * This interface models a place to retrieve Equities and its price.
 * The implementation can vary a lot based on the third parties' API. 
 */
public interface EquityPool {
	
	/**
	 * Retrieve the requested Equity's price.
	 * @param symbol the equity's ticker.
	 * @return an optional of price in double, it might be absent.
	 */
	Optional<Double> getEquityPrice(String symbol);
	
	/**
	 * Retrieve the requested Equity itself.
	 * @param symbol the equity's ticker.
	 * @return an optional of equity, it might be absent.
	 */
	Optional<Equity> getEquity(String symbol);
}
