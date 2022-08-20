package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import main.model.profile.PasswordChangeByEmail;
import main.model.profile.PasswordChangeByFC;
import main.model.profile.PasswordChangeByOldPassword;
import main.model.profile.PasswordChanger;
import main.model.profile.ProfileCredentials;
import main.model.profile.SimplePassword;

public class TestProfile {
    private static final String PWORD = "SuperMario";
    private static final String NEW_PWORD = "SuperLuigi";
    private static final String RND_ERR = "A";

    private final ProfileCredentials pCred = new ProfileCredentials("Mario", "Rossi", "MRRSS10T01772T", "mario.rossi@studio.unibo.it", new SimplePassword("SuperMario"));

    @Test
    void testProfileInit() {
        assertEquals(this.pCred.getName(), "Mario");
        assertEquals(this.pCred.getSurname(), "Rossi");
        assertEquals(this.pCred.getFc(), "MRRSS10T01772T");
        assertEquals(this.pCred.getEMail(), "mario.rossi@studio.unibo.it");
        assertEquals(this.pCred.getPassword(), "SuperMario");
    }

    @Test
    void testPwordChange() {
        PasswordChanger pc = new PasswordChanger(new PasswordChangeByEmail(this.pCred));
        final String email = this.pCred.getEMail();
        //controllo congruenza password - password di conferma
        try {
            pc.changePassword(NEW_PWORD, RND_ERR, email);
            fail("Congruenza P-CP in ByEmail: ERR");
        } catch (IllegalArgumentException e) {
            System.out.println("Congruenza P-CP in ByEmail: OK");
        }
        //controllo congruenza ID
        try {
            pc.changePassword(NEW_PWORD, NEW_PWORD, RND_ERR);
            fail("Congruenza ID in ByEmail: ERR");
        } catch (IllegalArgumentException e) {
            System.out.println("Congruenza ID in ByEmail: OK");
        }
        //tutto ok
        try {
            pc.changePassword(NEW_PWORD, NEW_PWORD, email);
            //rimetto la pword originale
            pc.changePassword(PWORD, PWORD, email);
        } catch (IllegalArgumentException e) {
            fail("Qualcosa è andato storto in ByEmail");
        }

        pc = new PasswordChanger(new PasswordChangeByFC(this.pCred));
        final String fc = this.pCred.getFc();
        //controllo congruenza password - password di conferma
        try {
            pc.changePassword(NEW_PWORD, RND_ERR, fc);
            fail("Congruenza P-CP in ByEmail: ERR");
        } catch (IllegalArgumentException e) {
            System.out.println("Congruenza P-CP in ByFC: OK");
        }
        //controllo congruenza ID
        try {
            pc.changePassword(NEW_PWORD, NEW_PWORD, RND_ERR);
            fail("Congruenza ID in ByEmail: ERR");
        } catch (IllegalArgumentException e) {
            System.out.println("Congruenza ID in ByFC: OK");
        }
        //tutto ok
        try {
            pc.changePassword(NEW_PWORD, NEW_PWORD, fc);
            //rimetto la pword originale
            pc.changePassword(PWORD, PWORD, fc);
        } catch (IllegalArgumentException e) {
            fail("Qualcosa è andato storto in ByFC");
        }

        pc = new PasswordChanger(new PasswordChangeByOldPassword(this.pCred));
        //controllo congruenza password - password di conferma
        try {
            pc.changePassword(NEW_PWORD, RND_ERR, PWORD);
            fail("Congruenza P-CP in ByEmail: ERR");
        } catch (IllegalArgumentException e) {
            System.out.println("Congruenza P-CP in OldPassword: OK");
        }
        //controllo congruenza ID
        try {
            pc.changePassword(NEW_PWORD, NEW_PWORD, RND_ERR);
            fail("Congruenza ID in OldPassword: ERR");
        } catch (IllegalArgumentException e) {
            System.out.println("Congruenza ID in OldPassword: OK");
        }
        //tutto ok
        try {
            pc.changePassword(NEW_PWORD, NEW_PWORD, PWORD);
            //rimetto la pword originale
            pc.changePassword(PWORD, PWORD, NEW_PWORD);
        } catch (IllegalArgumentException e) {
            fail("Qualcosa è andato storto in OldPassword");
        }
    }
}
