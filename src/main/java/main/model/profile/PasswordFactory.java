package main.model.profile;

public interface PasswordFactory {

    /**
     * returns a Password if the password and confPassword 
     * are the same.
     * 
     * @param password
     * @param confPassword
     * 
     * @return new Password
     */
    Password firstPassword(String password, String confPassword);

    /**
     * returns a Password if the current password is inputed correctly
     * and the new password and his confirmation are the same.
     * 
     * @param profile
     * @param currentPassword
     * @param newPassword
     * @param confNewPassword
     * 
     * @return new Password
     */
    Password newPasswordFromCurrentPassword(ProfileCredentials profile, String currentPassword, String newPassword, String confNewPassword);

    /**
     * returns a Password if the email is inputed correctly
     * and the new password and his confirmation are the same.
     * 
     * @param profile
     * @param email
     * @param newPassword
     * @param confNewPassword
     * 
     * @return new Password
     */
    Password newPasswordFromEmail(ProfileCredentials profile, String email, String newPassword, String confNewPassword);
}
