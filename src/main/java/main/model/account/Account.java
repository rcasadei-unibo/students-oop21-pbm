package main.model.account;

/**
 * Models a generic fund account.
 * 
 */

public interface Account {
	
	/**
	 *	withdraw money from here.
	 *
	 * @param amount amount to be withdrawn
	 * 
	 * @throws NotEnoughFoundsException if the balance is less than the amount to
	 *                                  withdraw
	 */
	void withdraw(double amount);

	/**
	 *	deposit money from here.
	 *
	 * @param amount amount to be credited
	 */
	void deposit(double amount);

	/**
	 * get the total money.
	 * 
	 * @return The current balance
	 */
	double getBalance();

}
