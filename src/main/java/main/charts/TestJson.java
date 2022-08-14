package main.charts;
import main.charts.OperationJSONUtente;

public class TestJson {
    
    public static void proviamo() {
        OperationJSONUtente prova = new OperationJSONUtente();
        if (prova.userPasswordCheck("Gin","cacca")==true) {
            System.out.println("utente password corretti");
        } else {
            System.out.println("you asshole");
        }
        if (prova.userPasswordCheck("Gin","merda")==false) {
            System.out.println("utente corretto password sbagliata ");
        } else {
            System.out.println("you asshole");
        }
        if (prova.userExist("Gin")==true && prova.userPasswordCheck("Gin","merda")==false) {
            System.out.println("password non corretta");
        } else {
            System.out.println("you asshole");
        }
    }

    

}
