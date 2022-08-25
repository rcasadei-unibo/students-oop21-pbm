package main.model.market;

import com.google.common.base.Optional;

public class EquityPoolAPITradingView extends EquityPoolDecorator {

    private final EquityPool basePool;

    public EquityPoolAPITradingView(final EquityPool basePool) {
        super();
        this.basePool = basePool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getEquityPrice(final String symbol) {
        final Optional<Double> price = this.basePool.getEquityPrice(symbol);
        if (!price.isPresent()) {
            // Access TradingView API to retrieve information
        }
        return price;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Equity> getEquity(final String symbol) {
        final Optional<Equity> equity = this.basePool.getEquity(symbol);
        if (!equity.isPresent()) {
            // Access TradingView API to retrieve information
        }
        return equity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.basePool.toString() + " --> TradingView";
    }

}
