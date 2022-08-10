package main.control;

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

	void showProfile();
}
