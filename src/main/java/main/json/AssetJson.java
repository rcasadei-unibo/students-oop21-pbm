package main.json;

public class AssetJson {
    
    double amount;
    String assetSymbol;
    
    public AssetJson() {
        super();
        this.amount = 0;
        this.assetSymbol = "";
    }
    
    public AssetJson(double amount, String assetSymbol) {
        super();
        this.amount = amount;
        this.assetSymbol = assetSymbol;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getAssetSymbol() {
        return assetSymbol;
    }
    
    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }
}
