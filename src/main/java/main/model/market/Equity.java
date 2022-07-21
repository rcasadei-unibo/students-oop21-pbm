package main.model.market;

import java.math.BigDecimal;

public interface Equity {
	
	BigDecimal getPrice();
	
	BigDecimal getSymbol();
	
	String getFullName();
	
	String getCurrency();
		
}
