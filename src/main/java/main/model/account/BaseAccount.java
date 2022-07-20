package main.model.account;

/**
 * Class modeling a account with strict policies: getting money is allowed only
 * with enough funds.
 * 
 */
public abstract class BaseAccount implements Account {

	private double balance;

	@Override
	public void withdraw(final double amount) {
		if (checkWithdrawValidity(amount)) {
			this.balance -= amount;
		} else {
			throw new NotEnoughFundsException();
		}
	}

	@Override
	public void deposit(final double amount) {
		if (checkDepositValidity(amount)) {
			this.balance += amount;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public double getBalance() {
		return this.balance;
	}

	abstract boolean checkWithdrawValidity(double amount);

	abstract boolean checkDepositValidity(double amount);

}
