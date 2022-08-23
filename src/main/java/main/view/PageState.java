package main.view;

public enum PageState {

    /**
     * PROFILE.
     */
    PROFILE("PROFILE"),
    /**
     * INVEST.
     */
    INVEST("INVEST"),
    /**
     * EXPENSE.
     */
    EXPENSE("EXPENSE"),
    /**
     * BANKACCOUNT.
     */
    BANKACCOUNT("BANKACCOUNT");

    private String state;

    PageState(final String state) {
        this.state = state;
    }

    /**
     * @return a state of the user interface.
     */
    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

}
