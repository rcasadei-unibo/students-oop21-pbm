package main.model.account;

public final class SimpleAccount extends BaseAccount {
	
	private final String id;
	public SimpleAccount(final double amount, final String id) {
		super();
		this.deposit(amount);
		this.id = id;
	}


	@Override
	boolean checkWithdrawValidity(final double amount) {
		return this.getBalance() >= amount;
	}

	@Override
	boolean checkDepositValidity(final double amount) {
		return true;
	}


    @Override
    public String getID() {
        return this.id;
    }
	

}
