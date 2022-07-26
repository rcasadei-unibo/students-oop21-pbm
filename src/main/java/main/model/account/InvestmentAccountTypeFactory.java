package main.model.account;

import com.google.common.base.Function;

/**
 * An interface to model a factory for various kinds of accounts.
 * Maybe in the future, based the on memberships or typeOfSubscription, we'll 
 * have different type of users:
 * 1. users can use the app for free because they pay monthly subscription fees.
 * 2. users use the app with limited access.
 * 3. normal users that pay operation fees.
 * 
 */
public interface InvestmentAccountTypeFactory {
	
	/**
	 * A free account.
	 * 
	 * @return InvestmentAccount instance
	 */
	InvestmentAccount createForFree();
	
	/**
	 * Each operation has a cost.
	 * 
	 * @param fees the applied operation fees
	 * 
	 * @return InvestmentAccount instance
	 */
	InvestmentAccount createWithOperationFees(Function<Double, Double> fees);
	
	/**
	 * Each buy or sells should not exceed a certain amount.
	 * 
	 * @param limit the operation times shouldn't be exceeded
	 * 
	 * @return InvestmentAccount instance
	 */
	InvestmentAccount createWithOperationLimitForFree(int limit);
	
	/**
	 * Each buy or sells should not exceed a certain amount.
	 * 
	 * @param amount the amount per operation shouldn't be exceeded
	 * 
	 * @return InvestmentAccount instance
	 */
	InvestmentAccount createWithAmountLimitForFree(double amount);
	
	

	
	
	
}
