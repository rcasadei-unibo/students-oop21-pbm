package main.model.profile;

public class PasswordFactoryImpl implements PasswordFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Password firstPassword(final String password, final String confPassword) {
        if (password.equals(confPassword)) {
            return createPassword(password);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Password newPasswordFromCurrentPassword(final ProfileCredentials profile, final String currentPassword, 
            final String newPassword, final String confNewPassword) {
        if (profile.getPassword().equals(currentPassword)) {
            return firstPassword(newPassword, confNewPassword);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Password newPasswordFromEmail(final ProfileCredentials profile, final String email, final String newPassword, 
            final String confNewPassword) {
        if (profile.getEMail().equals(email)) {
            return firstPassword(newPassword, confNewPassword);
        } else {
            return null;
        }
     }

    private Password createPassword(final String password) {
        return new Password() {

            private final String pass = password;
            @Override
            public String getPassword() {
                return this.pass;
            }
        };
    }
}
