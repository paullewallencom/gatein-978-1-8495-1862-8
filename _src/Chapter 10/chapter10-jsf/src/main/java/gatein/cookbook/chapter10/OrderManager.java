package gatein.cookbook.chapter10;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class OrderManager implements Serializable {

    private static final long serialVersionUID = -5776345927432051634L;

    Set<Order> orders = new HashSet<Order>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
