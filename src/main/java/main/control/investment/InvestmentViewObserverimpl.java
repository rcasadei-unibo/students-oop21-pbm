package main.control.investment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import main.model.market.EquityPool;
import main.model.market.EquityPoolAPICoinBase;
import main.model.market.EquityPoolAPITradingView;
import main.model.market.EquityPoolBasic;
import main.model.profile.ProfileEconomy;

public final class InvestmentViewObserverimpl implements InvestmentViewObserver {

    private final ProfileEconomy profile;
    private final EquityPool ep;

    public InvestmentViewObserverimpl(final ProfileEconomy profile) {
        super();
        this.profile = profile;
        // let's always try to use the maximum research level
        ep = new EquityPoolAPITradingView(new EquityPoolAPICoinBase(new EquityPoolBasic()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAllHoldingSymbols() {
        return profile.getHoldingAccounts().stream().map(x -> x.getHoldingSymbols()).flatMap(Collection::stream)
                .distinct().collect(Collectors.toUnmodifiableList());
    }

    // for the sake of performance and less redundancy, let's get the symbols from
    // parameters.
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Double> getAllHoldingInPrices(final List<String> symbols) {
        final List<Double> prices = new ArrayList<>();
        symbols.forEach(x -> prices.add(ep.getEquityPrice(x).get()));

        return Collections.unmodifiableList(prices);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Double> getAllHoldingInValue(final List<Double> prices, final List<Double> shares) {
        if (prices.size() != shares.size()) {
            return Collections.emptyList();
        }
        return IntStream.iterate(0, i -> i + 1).limit(prices.size()).mapToObj(i -> i)
                .map(i -> prices.get(i) * shares.get(i)).collect(Collectors.toUnmodifiableList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Double> getAllHoldingShares(final List<String> symbols) {
        // maybe later i will change it to tree map, so the ticker can be
        // ordered in a way defined by a comparator.
        final Map<String, Double> map = new LinkedHashMap<>();
        symbols.forEach(x -> map.put(x, 0.0));
        profile.getHoldingAccounts()
                .forEach(x -> symbols.forEach(s -> map.computeIfPresent(s, (k, v) -> v + x.howManyShares(s))));
        return map.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<String> getAllInvAccountIDs() {
        return profile.getInvestmentAccounts().stream().map(x -> x.getID()).collect(Collectors.toUnmodifiableList());
    }

}
