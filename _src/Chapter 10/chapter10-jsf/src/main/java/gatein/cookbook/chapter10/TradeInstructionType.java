package gatein.cookbook.chapter10;

public enum TradeInstructionType {

    MARKET("Market"),
    LIMIT("Limit"),
    STOP_LOSS("Stop Loss"),
    TRAILING_STOP("Trailing Stop"),
    GOOD_TILL_CANCELLED("Good Till Cancelled"),
    DAY_ORDER("Day Order");

    private String name;

    TradeInstructionType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
