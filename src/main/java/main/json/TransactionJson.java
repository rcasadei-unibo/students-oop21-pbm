package main.json;

public class TransactionJson {
    
<<<<<<< HEAD
    private String nameTransaction;
=======
    String nameTransaction;
>>>>>>> c8d3b6d (a horrible bug, application doesn't read utente.json anymore)
    private String date;
    private String time;
    private String currency;
    private double amount;
    
<<<<<<< HEAD
    public TransactionJson(String nameTransaction, String date, String time, double amount){
        this.setAmount(amount);
        this.setNameTransaction(nameTransaction);
=======
    TransactionJson(String nameTransaction, String date, String time, double amount){
        this.setAmount(amount);
        this.nameTransaction = nameTransaction;
>>>>>>> c8d3b6d (a horrible bug, application doesn't read utente.json anymore)
        this.setDate(date);
        this.setTime(time);
        this.setCurrency("euro");
    }
    
<<<<<<< HEAD
    public TransactionJson(String nameTransaction, String date, String time, double amount, String currency){
        this.setAmount(amount);
        this.setNameTransaction(nameTransaction);
=======
    TransactionJson(String nameTransaction, String date, String time, double amount, String currency){
        this.setAmount(amount);
        this.nameTransaction = nameTransaction;
>>>>>>> c8d3b6d (a horrible bug, application doesn't read utente.json anymore)
        this.setDate(date);
        this.setTime(time);
        this.setCurrency(currency);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
<<<<<<< HEAD

    public String getNameTransaction() {
        return nameTransaction;
    }

    public void setNameTransaction(String nameTransaction) {
        this.nameTransaction = nameTransaction;
    }
=======
>>>>>>> c8d3b6d (a horrible bug, application doesn't read utente.json anymore)
    

}
