package main.control;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import main.model.profile.ProfileCredentials;

/**
 * This interface does all chores to interact with user.
 * Such as operation IO, calling model for calculations and etc.
 * 
 * All functions are kind of notification sent from View
 *
 */
public interface Controller {
	
	// Part of Song, All possible interactions from users.
	
	void buyStocks(String symbol, double shares, String id);
	
	void sellStocks(String symbol, double shares, String id);
	
	void updateMarketInfo();

	// Ale's part

	void showProfile(BorderPane root);

    void registerProfile(String name, String surname, String fc, String eMail, String password);

    void accessProfile(String eMail, String password);

    void showLoginScene();

    void showPasswordChangeView();

    ProfileCredentials getUsrInfo();

    void changePword(String strategy, String newPword, String confPword, String id);
}
