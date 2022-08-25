package main.model.account;

/**
 * When there is not enough shares to get.
 *
 */
public class NotEnoughSharesException extends IllegalStateException {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return "Your shares wan't enough! :(";
    }

}
