package main.model.profile;

public class PasswordChangeByOldPassword implements PasswordChangeStrategy {

    private final ProfileCredentials profile;

    public PasswordChangeByOldPassword(final ProfileCredentials profile) {
        this.profile = profile;
    }
    @Override
    public void changePassword(final String newPass, final String confNewPass, final String id) {
        if (id.equals(this.profile.getPassword()) && newPass.equals(confNewPass)) {
            this.profile.updatePassword(new SimplePassword(newPass));
        } else {
            throw new IllegalArgumentException();
        }
    }

}
