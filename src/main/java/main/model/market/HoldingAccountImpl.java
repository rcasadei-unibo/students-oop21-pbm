package main.model.market;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import main.model.account.NotEnoughSharesException;

public class HoldingAccountImpl implements HoldingAccount {

    private final Map<String, Double> holdings;
    private final EquityPool equityPool;
    private final String id;

    public HoldingAccountImpl(final Map<String, Double> holdings, final EquityPool ep, final String id) {
        super();
        this.holdings = holdings;
        equityPool = ep;
        this.id = id;
    }

    public HoldingAccountImpl(final EquityPool ep, final String id) {
        this(new HashMap<>(), ep, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getHoldingSymbols() {
        return holdings.entrySet().stream().map(x -> x.getKey()).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTotalValue() {
        /**
         * the x.getKey().get() might be absent. So I need to decide whether to tell the
         * user to wait or just show an error.
         */
        return holdings.entrySet().stream().mapToDouble(x -> equityPool.getEquityPrice(x.getKey()).get() * x.getValue())
                .sum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHoldingsForBuying(final Order order) {
        final String key = order.getEquity().getSymbol();
        final double shares = order.getShares();

        holdings.computeIfPresent(key, (k, val) -> val + shares);
        holdings.computeIfAbsent(key, k -> shares);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHoldingsForSelling(final Order order) {
        final String key = order.getEquity().getSymbol();
        final double shares = order.getShares();

        holdings.computeIfPresent(key, (k, val) -> val - shares);

        if (holdings.containsKey(key)) {
            throw new NotEnoughSharesException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEnoughShares(final Order order) {
        if (holdings.containsKey(order.getEquity().getSymbol())
                && holdings.get(order.getEquity().getSymbol()) >= order.getShares()) {
            return true;
        }
        throw new NotEnoughSharesException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double howManyShares(final String symbol) {
        return holdings.containsKey(symbol) ? holdings.get(symbol).doubleValue() : 0.0;
    }

    @Override
    public String getID() {
        return this.id;
    }

}
