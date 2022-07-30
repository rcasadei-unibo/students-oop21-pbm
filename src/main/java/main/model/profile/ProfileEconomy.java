
package main.model.profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import main.model.account.InvestmentAccount;

public class ProfileEconomy {

    private double totalBalance;
    private final List<InvestmentAccount> invAccs = new ArrayList<>();
    //da aggiungere cose

    /**
     * 
     * @return InvestmentAccounts
     */
    public List<InvestmentAccount> getInvestmentAccounts() {
        return Collections.unmodifiableList(this.invAccs);
    }

    /**
     * 
     * @param newAccount
     */
    public void newInvestmentAccount(final InvestmentAccount newAccount) {
        this.invAccs.add(newAccount);
        setTotalBalance(newAccount.getBalance());
    }

    /**
     * Set totalBalance to new amount.
     * 
     * @param amount updated amount of totalBalance.
     */
    private void setTotalBalance(final double amount) {
         this.totalBalance += amount;
    }

    /**
     * @return profile total balance.
     */
    public double getTotalBalance() {
        return this.totalBalance;
    }

}
