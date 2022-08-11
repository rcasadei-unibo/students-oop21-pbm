package main.model.market;

import java.util.Set;

/**
 * This interface models a general holding accounts be it stocks,
 *  cryptocurrencies, maybe even real estates.
 */
public interface HoldingAccount {
	
	/**
	 * get the symbols of holding assets.
	 * @return a set of assets names in the form of strings 
	 */
	Set<String> getHoldingSymbols();
	
	/**
	 * get the total worth of this account.
	 * @return	the value of assets
	 */
	double getTotalValue();
	
	/**
	 * update the holdings after buying something.
	 * @param order a pair of equity and amount of shares.
	 */
	void updateHoldingsForBuying(Order order);
	
	/**
	 * update the holdings after selling something.
	 * @param order a pair of equity and amount of shares.
	 */
	void updateHoldingsForSelling(Order order);
	
	/**
	 * for check if the user has enough share.
	 * 
	 * @param order order a pair of equity and amount of shares.
	 * @return boolean true if the shares is enough, false otherwise.
	 */
	boolean hasEnoughShares(Order order);
	
	/**
	 * to look at how many shares i own over a company.
	 * 
	 * @param symbol the stock's ticker
	 * @return shares in double
	 */
	double howManyShares(String symbol);

	/**
	 * get id of the account.
	 * @return id
	 */
    String getID();
}
