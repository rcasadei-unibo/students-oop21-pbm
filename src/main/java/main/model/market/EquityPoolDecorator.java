package main.model.market;

import com.google.common.base.Optional;

public abstract class EquityPoolDecorator implements EquityPool {

    public abstract Optional<Double> getEquityPrice(String symbol);

    public abstract Optional<Equity> getEquity(String symbol);

}
