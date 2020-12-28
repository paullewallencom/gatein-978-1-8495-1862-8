package gatein.cookbook.chapter10;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class OrderList {

    @ManagedProperty(value = "#{orderManagerEvent}")
    private OrderManagerEvent orderManager;

    public void setOrderManager(OrderManagerEvent orderManager) {
        this.orderManager = orderManager;
    }

    public List<Order> getActiveOrders() {
        List<Order> active = new ArrayList<Order>();
        for (Order order : orderManager.getOrders()) {
            if (order.getStatus().equals(Status.WAITING)) {
                active.add(order);
            }
        }
        return active;
    }

}
