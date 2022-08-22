package main.json;

public class TransactionJson {
    
    String nameTransaction, date, time, currency;
    double amount;
    
    TransactionJson(String nameTransaction, String date, String time, double amount){
        this.amount = amount;
        this.nameTransaction = nameTransaction;
        this.date = date;
        this.time = time;
        this.currency = "euro";
    }
    
    TransactionJson(String nameTransaction, String date, String time, double amount, String currency){
        this.amount = amount;
        this.nameTransaction = nameTransaction;
        this.date = date;
        this.time = time;
        this.currency = currency;
    }
    

}
