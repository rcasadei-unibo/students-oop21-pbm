package main.model.account;

public class NotEnoughSharesException extends IllegalStateException {

    private static final long serialVersionUID = 1L;

    @Override
    public final String toString() {
        return "Your shares wan't enough! :(";
    }

}
