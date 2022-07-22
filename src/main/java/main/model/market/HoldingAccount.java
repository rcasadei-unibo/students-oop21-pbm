package main.model.market;

import java.util.Set;

public interface HoldingAccount {
	
	Set<String> getHoldingSymbols();
	
	double getTotalValue();
	
	void updateHoldingsForBuying(Order order);
	
	void updateHoldingsForSelling(Order order);
	
	boolean hasEnoughShares(Order order);
}
