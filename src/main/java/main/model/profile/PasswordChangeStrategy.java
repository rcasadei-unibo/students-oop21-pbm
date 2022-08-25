package main.model.profile;

/**
 * there are different methods to change a password, but almost every time
 * you need to select a method to identify yourself other than choosing an confirming
 * a new password.
 */
public interface PasswordChangeStrategy {

    /**
     * changes the password of a profile.
     * @param newPass
     * @param confNewPass
     * @param id
     */
    void changePassword(String newPass, String confNewPass, String id);
}
