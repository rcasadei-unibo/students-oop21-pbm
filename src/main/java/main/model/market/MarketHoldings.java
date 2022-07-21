package main.model.market;

/**
 * An interface that models a market to allow people to BUY or SELL equities
 * on the markets(stock, cryptocurrency, NFT, bonds, swaps, etc...).
 *	
 */
public interface MarketHoldings {
	
	/**
	 *	buy the specified asset on the market.
	 *
	 * @param ticker the thing you want to buy
	 * 
	 * @param shares the fraction of an equity
	 * 
	 * @throws IllegalArgumentException if the user is not allowed
	 * for this operation, be it not owning an equity or not enough shares.
	 */
	void buyAssets(Equity ticker, double shares);
	
	/**
	 *	buy the specified asset on the market.
	 *
	 * @param ticker the thing you want to buy
	 * 
	 * @param shares the fraction of an equity
	 * 
	 * @throws IllegalArgumentException if the user is not allowed
	 * for this operation, be it not owning an equity or not enough shares.
	 * 
	 */
	void sellAssets(Equity ticker, double shares);
	
	/**
	 *	get the total worth in this market.
	 *
	 * @return the value in USD.
	 */
	double getAssetsNetWorth();
}
