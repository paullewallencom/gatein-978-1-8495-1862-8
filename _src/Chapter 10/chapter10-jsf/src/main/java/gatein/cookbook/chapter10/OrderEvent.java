package gatein.cookbook.chapter10;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

@SuppressWarnings("serial")
@XmlRootElement
public class OrderEvent implements Serializable {

    private Order order;

    public static final QName QNAME = new QName("urn:chapter10:jsf:order:event", "OrderEvent");

    public OrderEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

}
