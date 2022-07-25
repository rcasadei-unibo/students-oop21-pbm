package main.model.market;

import main.model.account.InvestmentAccount;

/**
 * An interface that models a market to allow people to BUY or SELL equities
 * on the markets(stock, cryptocurrency, NFT, bonds, swaps, etc...).
 */
public interface Market{
	
	/**
	 *	buy the specified assert on the market.
	 * 
	 * @param invAcc the investing account
	 * @param holdAcc the holding account
	 * @param order transaction order
	 * @throws IllegalArgumentException if the user is not allowed
	 * for this operation, be it not owning an equity or not enough shares.
	 */
	void buyAsset(InvestmentAccount invAcc, HoldingAccount holdAcc, Order order);
	
	/**
	 * sell the specified assert on the market.
	 * 
	 * @param invAcc the investing account
	 * @param holdAcc the holding account
	 * @param order transaction order
	 * @throws IllegalArgumentException if the user is not allowed
	 * for this operation, be it not owning an equity or not enough shares.
	 */
	void sellAsset(InvestmentAccount invAcc, HoldingAccount holdAcc, Order order);
	
	/**
	 *	get the total worth in this market.
	 *
	 * @return the value in USD.
	 */
	double getAssetsNetWorth();
	
}
