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
	InvestmentAccount createForFree(String id);
	
	/**
	 * Each operation has a cost. You pass the parameter as a lambda function
	 * such as (f -> f * feeRate) whereas the feeRate is like about 0.05 of the 
	 * total transaction. 
	 * 
	 * If the total amount of tansaction is let's say 100, feeRate = 0.05, then 
	 * it will charge a fee of 5. the remaining should be 95 
	 * 
	 * @param fees the applied operation fees
	 * 
	 * @return InvestmentAccount instance
	 */
	InvestmentAccount createWithOperationFees(Function<Double, Double> fees, String id);
	
	/**
	 * Each buy or sells should not exceed a certain amount.
	 * 
	 * @param limit the operation times shouldn't be exceeded
	 * 
	 * @return InvestmentAccount instance
	 */
	InvestmentAccount createWithOperationLimitForFree(int limit, String id);
	
	/**
	 * Each buy or sells should not exceed a certain amount.
	 * 
	 * @param amount the amount per operation shouldn't be exceeded
	 * 
	 * @return InvestmentAccount instance
	 */
	InvestmentAccount createWithAmountLimitForFree(double amount, String id);
	
	

	
	
	
}
