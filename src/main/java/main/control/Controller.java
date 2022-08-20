package main.control;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.model.profile.ProfileCredentials;
import main.model.profile.ProfileEconomy;

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

    void showProfile(Stage stage, BorderPane root);

    void registerProfile(String name, String surname, String fc, String eMail, String password);

    void accessProfile(String eMail, String password);

    void showLoginScene();

    void showPasswordChangeView();

    ProfileCredentials getUsrInfo();

    void changePword(String strategy, String newPword, String confPword, String id);

    ProfileEconomy getUsrEconomy();
}
