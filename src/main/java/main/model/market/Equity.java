package main.model.market;

/**
 * This interface models an equity.
 * It can be everything that consists of a name and a price.
 */
public interface Equity {
	
	/**
	 * Get the price of the asset.
	 * @return the price in double.
	 */
	double getPrice();
	
	/**
	 * Get the symbol of the asset.
	 * @return the name in string.
	 */
	String getSymbol();
	
}
