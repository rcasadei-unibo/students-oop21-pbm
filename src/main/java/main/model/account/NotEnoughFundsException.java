package main.model.account;

public class NotEnoughFundsException extends IllegalStateException {

    private static final long serialVersionUID = 1L;

    @Override
    public final String toString() {
        return "Your money wan't enough! :(";
    }

}
