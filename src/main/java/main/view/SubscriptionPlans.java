package main.view;

public enum SubscriptionPlans {

    /**
     * 0.05 fee.
     */
    BRONZE("BRONZE", 0.05),
    /**
     * 0.04 fee.
     */
    SILVER("SILVER", 0.04),
    /**
     * 0.03 fee.
     */
    GOLD("GOLD", 0.03),
    /**
     * 0.02 fee.
     */
    PLATINUM("PLATINUM", 0.02),
    /**
     * 0.01 fee.
     */
    DIAMOND("DIAMOND", 0.01),
    /**
     * No fee.
     */
    CHALLENGER("CHALLENGER", 0.00);

    private final String state;
    private final Double fee;

    SubscriptionPlans(final String state, final Double fee) {
        this.state = state;
        this.fee = fee;
    }

    /**
     * @return state.
     */
    public String getState() {
        return state;
    }

    /**
     * @return fee.
     */
    public Double getFee() {
        return fee;
    }
}
