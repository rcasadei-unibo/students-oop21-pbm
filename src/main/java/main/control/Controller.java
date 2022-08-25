package main.control;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.model.profile.ProfileCredentials;
import main.model.profile.ProfileEconomy;
import main.view.SubscriptionPlans;

/**
 * This interface does all chores to interact with user. Such as operation IO,
 * calling model for calculations and etc.
 * 
 * All functions are kind of notification sent from View
 *
 */
public interface Controller {

    // Part of Song, All possible interactions from users.

    /**
     * Buy stocks..
     * 
     * @param symbol the symbol in string
     * @param shares number of shares
     * @param id     the broker platform
     */
    void buyStocks(String symbol, double shares, String id);

    /**
     * Sell stocks..
     * 
     * @param symbol the symbol in string
     * @param shares number of shares
     * @param id     the broker platform
     */
    void sellStocks(String symbol, double shares, String id);

    /**
     * update the stock market info.
     */
    void updateMarketInfo();

    /**
     * do everything that's needed before closing the app, such as cleaning thread
     * pools and saves.
     */
    void terminateApp();

    // Ale's part

    /**
     * updates and shows what's inside ProfileScene.
     */
    void showProfile();

    /**
     * creates a new profile that should be saved in json (not implemented).
     * 
     * @param name
     * @param surname
     * @param fc
     * @param eMail
     * @param password
     */
    void registerProfile(String name, String surname, String fc, String eMail, String password);

    /**
     * should retrieve the correct profile data from json (not implemented)
     * a demo account will be displayed instead.
     * 
     * @param eMail
     * @param password
     */
    void accessProfile(String eMail, String password);

    /**
     * shows a view to change user password.
     */
    void showPasswordChangeView();

    /**
     * 
     * @return user credentials info
     */
    ProfileCredentials getUsrInfo();

    /**
     * changes user password with the given strategy.
     * 
     * @param strategy
     * @param newPword
     * @param confPword
     * @param id
     */
    void changePword(String strategy, String newPword, String confPword, String id);

    /**
     * 
     * @return user economy info
     */
    ProfileEconomy getUsrEconomy();

    /**
     * shows a view to add a new account.
     */
    void showAddAccountView();

    /**
     * creates a new Investment and Holding account.
     * 
     * @param name
     * @param value
     * @param subPlan
     * @return true if the account is successfully created, false otherwise
     */
    boolean createAcc(String name, double value, SubscriptionPlans subPlan);
    
    //Paolo's part

    void showExpenditure();
}
