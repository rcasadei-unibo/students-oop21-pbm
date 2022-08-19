package main.model.account;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {

            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleAccount other = (SimpleAccount) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "SimpleAccount [id=" + id + "]";
    }

}
