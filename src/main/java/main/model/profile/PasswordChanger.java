package main.model.profile;

public class PasswordChanger {

    private final PasswordChangeStrategy strategy;

    public PasswordChanger(final PasswordChangeStrategy strategy) {
        this.strategy = strategy;
    }
 
    /**
     * {@inheritDoc}
     */
    public void changePassword(final String newPass, final String confNewPass, final String id) {
        this.strategy.changePassword(newPass, confNewPass, id);
    }
}
