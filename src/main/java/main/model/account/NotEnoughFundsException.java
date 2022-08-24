package main.model.account;

/**
 * When there is no enough fund to get.
 *
 */
public class NotEnoughFundsException extends IllegalStateException {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return "Your money wan't enough! :(";
    }

}
