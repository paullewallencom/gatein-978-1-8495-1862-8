package gatein.cookbook.chapter6;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StockEvent implements Serializable {

    private static final long serialVersionUID = 4234197037147498216L;

    private String ticker;
    private String name;

    public String getTicker() {
        return ticker;
    }

    public StockEvent ticker(String ticker) {
        this.ticker = ticker;
        return this;
    }

    public String getName() {
        return name;
    }

    public StockEvent name(String name) {
        this.name = name;
        return this;
    }

}
