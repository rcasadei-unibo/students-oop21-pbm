package main.model.profile;

public class PasswordChangeByFC implements PasswordChangeStrategy {

    private final ProfileCredentials profile;

    public PasswordChangeByFC(final ProfileCredentials profile) {
        this.profile = profile;
    }

    @Override
    public void changePassword(final String newPass, final String confNewPass, final String id) {
        if (id.equals(this.profile.getFc()) && newPass.equals(confNewPass)) {
            this.profile.updatePassword(new SimplePassword(newPass));
        } else {
            throw new IllegalArgumentException();
        }
    }

}
