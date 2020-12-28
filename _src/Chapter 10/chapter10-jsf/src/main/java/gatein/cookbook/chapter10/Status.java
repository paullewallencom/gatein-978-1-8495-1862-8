package gatein.cookbook.chapter10;

public enum Status {

    WAITING("Waiting"),
    COMPLETE("Complete"),
    CANCELLED("Cancelled");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
