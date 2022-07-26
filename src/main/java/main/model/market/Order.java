package main.model.market;

/**
 *This interface models an order for trading.
 *
 */
public interface Order {
	
	/**
	 * get the equity constructing the order.
	 * @return an Equity, something models an asset.
	 */
	Equity getEquity();
	
	/**
	 * get the number of shares for transaction.
	 * @return the number of shares.
	 */
	double getShares();
}
