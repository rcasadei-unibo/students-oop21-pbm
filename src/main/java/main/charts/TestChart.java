package main.charts;

import main.json.TransactionJson;

public class TestChart {
    
    public static TransactionJson[] esempioTransaction() {
        TransactionJson tr1 = new TransactionJson("deposito", "21/12/2021", "10:10", 1000);
        TransactionJson tr2 = new TransactionJson("comet", "01/01/2022", "01:10", -100);
        TransactionJson tr3 = new TransactionJson("prelievo bancomat", "10/01/2022", "01:10", -100);
        TransactionJson tr4 = new TransactionJson("affitto", "10/01/2022", "01:12", 500);
        TransactionJson tr5 = new TransactionJson("conad", "14/01/2022", "16:10", -50);
        TransactionJson tr6 = new TransactionJson("deposito", "17/01/2022", "12:10", 500);
        TransactionJson tr7 = new TransactionJson("Atm", "20/01/2022", "16:10", -50);
        TransactionJson tr8 = new TransactionJson("stipendio", "21/01/2022", "10:10", 1000);
        TransactionJson tr9 = new TransactionJson("stipendio", "26/01/2022", "10:10", 1000);
        TransactionJson tr10 = new TransactionJson("deposito", "21/02/2022", "10:10", -1000);
        
        TransactionJson[] fakedb = {tr1, tr2,tr3, tr4, tr5, tr6, tr7, tr8, tr9, tr10};
        
        return fakedb;
    }

}
