package main.model.profile;

public class SimplePassword implements Password {

    private final String password;

    public SimplePassword(final String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return this.password;
    }

}
