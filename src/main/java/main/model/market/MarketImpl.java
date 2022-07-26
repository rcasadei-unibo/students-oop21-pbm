package main.model.market;

import main.model.account.InvestmentAccount;

public class MarketImpl implements Market {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void buyAsset(final InvestmentAccount invAcc, final HoldingAccount holdAcc, final Order order) {
		if (!callThirdApiForBuying(order)) {
			return;
		}
		invAcc.invest(getAssetsNetWorth(order));
		holdAcc.updateHoldingsForBuying(order);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sellAsset(final InvestmentAccount invAcc, final HoldingAccount holdAcc, final Order order) {
		if (!callThirdApiForSelling(order)) {
			return;
		}
		invAcc.cashout(getAssetsNetWorth(order));
		holdAcc.updateHoldingsForSelling(order);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getAssetsNetWorth(final Order order) {
		return order.getEquity().getPrice() * order.getShares();
	}

	/**
	 * Maybe one day This app will be able to operate accounts on the other
	 * platforms, buying the real stocks.
	 * 
	 * @param order the order for transaction.
	 * 
	 * @return true if the operation went successful.
	 */
	protected boolean callThirdApiForBuying(final Order order) {
		return true;
	}

	/**
	 * Maybe one day This app will be able to operate accounts on the other
	 * platforms, selling the real stocks.
	 * 
	 * @param order the order for transaction.
	 * 
	 * @return true if the operation went successful.
	 */
	protected boolean callThirdApiForSelling(final Order order) {
		return true;
	}
}
