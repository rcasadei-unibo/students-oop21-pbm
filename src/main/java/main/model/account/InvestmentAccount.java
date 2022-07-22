package main.model.account;

/**
 * Models a generic fund account for investment, once user transfer some
 * funds in this account, a part of free money can be invested into assets,
 * the other part is invested worth.
 * 
 */
public interface InvestmentAccount extends Account{
	

	/**
	 *	get the invested amount of money.
	 *
	 * @return the money user invested
	 */
	double getInvestedBalance();
	

	/**
	 *	shows how much did user earn from investing, 
	 *	it's the worth in Equity minus the money invested.
	 *
	 * @param netWorthInvested the total value in equity
	 * 
	 * @return the total earning
	 */
	double getEarnings(double netWorthInvested);
	

	/**
	 *	shows how much did user earn from investing in percentage.
	 *
	 * @param netWorthInvested the total value in equity
	 * 
	 * @return the total earning in percentage
	 */
	double getEarningsInPercentage(double netWorthInvested);
	
	/**
	 * When a user buys an asset, it will be considered as invested money.
	 * 
	 * @param moneyBought the money used to buy assets.
	 */
	void increaseInvestedMoney(double moneyBought);
	
	/**
	 *  When a user sells an asset, invested money will decrease.
	 * 
	 * @param moneySold
	 */
	void decreaseInvestedMoney(double moneySold);
}
