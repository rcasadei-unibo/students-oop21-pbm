
package main.model.profile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProfileEconomy {

    private double totalBalance;
    private int nBankAaccounts = 0;
    private int nShares = 0;
    private int nCrypto = 0;
    private int nCoinBanks = 0;

    public ProfileEconomy(final double totalBalance) {
        this.totalBalance = totalBalance;
    }

    /** 
     * @return an organized map of what is inside ProfileEconomy.
     * */
    public Map<String, Integer> getEconomy() {
        return Collections.unmodifiableMap(mapEconomy());
    }

    private Map<String, Integer> mapEconomy() {
        final Map<String, Integer> map = new HashMap<>();
        map.put("nBankAaccounts", this.nBankAaccounts);
        map.put("nShares", this.nShares);
        map.put("nCrypto", this.nCrypto);
        map.put("nCoinBanks", this.nCoinBanks);
        return map;
    }

    /**
     * updates what is inside ProfileEconomy.
     */
    public void updateEconomy() {
        //to do
        /* this.nBankAaccounts = ?
         * this.nShares = ?
         * this.nCrypto = ?
         * this.nCoinBanks = ?
         */
    }

    /**
     * @return profile total balance.
     */
    public double getTotalBalance() {
        return this.totalBalance;
    }

    /**
     * Set totalBalance to new amount.
     * 
     * @param amount updated amount of totalBalance.
     */
    public void setTotalBalance(final double amount) {
         this.totalBalance = amount;
    }

}
