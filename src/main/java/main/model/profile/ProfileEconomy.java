package main.model.profile;

import java.util.List;

import main.model.account.InvestmentAccount;
import main.model.market.HoldingAccount;

public interface ProfileEconomy {
    /**
     * get all Investment Accounts.
     * @return List of InvestmentAccounts
     */
    List<InvestmentAccount> getInvestmentAccounts();

    /**
     * get all Holding Accounts.
     * @return List of HoldingAccounts
     */
    List<HoldingAccount> getHoldingAccounts();

    /**
     * add a new Investment Account.
     * @param newAccount
     */
    void newInvestmentAccount(InvestmentAccount newAccount);

    /**
     * add new Holding Account.
     * @param newAccount
     */
    void newHoldingAccount(HoldingAccount newAccount);

    /**
     * return overall profile Balance that is the sum of
     * the worth all Accounts.
     * @return profile total balance.
     */
    double getTotalBalance();
}
