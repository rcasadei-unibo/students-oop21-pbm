package main.model.account;

public final class SimpleAccount extends BaseAccount {
	
	private final String descrption;
	
	public SimpleAccount(final double amount, final String description) {
		super();
		this.descrption = description;
		this.deposit(amount);
	}

	@Override
	public String toString() {
		return "SimpleAccount [descrption=" + descrption + "]";
	}

	@Override
	boolean checkWithdrawValidity(final double amount) {
		return this.getBalance() >= amount;
	}

	@Override
	boolean checkDepositValidity(final double amount) {
		return true;
	}
	

}
