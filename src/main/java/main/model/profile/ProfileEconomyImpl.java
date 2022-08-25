
package main.model.profile;

import java.util.ArrayList;
import java.util.List;
import main.model.account.InvestmentAccount;
import main.model.market.HoldingAccount;

public class ProfileEconomyImpl implements ProfileEconomy {

    private double totalBalance;
    private final List<InvestmentAccount> invAccs = new ArrayList<>();
    private final List<HoldingAccount> holAccs = new ArrayList<>();
    //da aggiungere cose probabilmente, ma a buon punto.

    /**
     * {@inheritDoc}
     */
    public List<InvestmentAccount> getInvestmentAccounts() {
        return this.invAccs;
    }

    /**
     * {@inheritDoc}
     */
    public List<HoldingAccount> getHoldingAccounts() {
        return this.holAccs;
    }

    /**
     * {@inheritDoc}
     */
    public void addInvestmentAccount(final InvestmentAccount newAccount) {
        this.invAccs.add(newAccount);
    }

    /**
     * {@inheritDoc}
     */
    public void addHoldingAccount(final HoldingAccount newAccount) {
        this.holAccs.add(newAccount);
    }

    /**
     * Set totalBalance to new amount.
     * @param amount updated amount of totalBalance.
     */
    private void updateTotalBalance() {
        this.totalBalance = 0.0;
        this.invAccs.forEach(acc -> this.totalBalance += acc.getBalance());
        this.holAccs.forEach(acc -> this.totalBalance += acc.getTotalValue());
    }

    /**
     * {@inheritDoc}
     */
    public double getTotalBalance() {
        updateTotalBalance();
        return this.totalBalance;
    }

}
