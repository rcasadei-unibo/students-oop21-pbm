package main.model.profile;

public class PasswordChanger {

    private final PasswordChangeStrategy strategy;

    public PasswordChanger(final PasswordChangeStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * changes password if @param newPass and @param confNewPass are equal
     * and if @param id is correct.
     * 
     * @param newPass
     * @param confNewPass
     * @param id
     */
    public void changePassword(final String newPass, final String confNewPass, final String id) {
        this.strategy.changePassword(newPass, confNewPass, id);
    }
}
