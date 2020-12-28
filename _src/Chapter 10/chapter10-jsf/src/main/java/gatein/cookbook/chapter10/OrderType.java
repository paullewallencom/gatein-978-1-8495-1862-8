package gatein.cookbook.chapter10;

public enum OrderType {

    BUY("Buy"),
    SELL("Sell");

    private String name;

    OrderType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
