
package main.model.profile;

public class ProfileCredentials {

    private final String name;
    private final String surname;
    private final String fc;
    private final String eMail;
    private String password;

    public ProfileCredentials(final String name, final String surname, final String fc, final String eMail, final String password) {
        this.name = name;
        this.surname = surname;
        this.fc = fc;
        this.eMail = eMail;
        this.password = password;
    }

    /**
     * 
     * @return Name
     */
    public String getName() {
        return this.name;
    }
    /**
     * 
     * @return Surname
     */
    public String getSurName() {
        return this.surname;
    }

    /**
     * 
     * @return fiscal code
     */
    public String getFc() {
        return this.fc;
    }

    /**
     * 
     * @return email
     */
    public String getEMail() {
        return this.eMail;
    }

    /**
     * 
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * updates old password to new password.
     * @param newPassword
     */
    public void updatePassword(final String newPassword) {
        this.password = newPassword;
    }
}
