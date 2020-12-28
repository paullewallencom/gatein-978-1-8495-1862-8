package gatein.cookbook.chapter10;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.StateAwareResponse;

@ManagedBean
@RequestScoped
public class Order implements Serializable {

    private static final long serialVersionUID = -1343926602596381203L;

    @ManagedProperty(value = "#{orderManager}")
    private OrderManager orderManager;

    public Order() {
        this.status = Status.WAITING;
        this.entryDate = new Date();
    }

    private OrderType type;

    private TradeInstructionType tradeInstruction;

    private String accountNumber;

    private Integer numberStocks;

    private Date entryDate;

    private Status status;

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public String create() {
        orderManager.addOrder(this);

        Object response = FacesContext.getCurrentInstance().getExternalContext().getResponse();
        if (response instanceof StateAwareResponse) {
            StateAwareResponse stateResponse = (StateAwareResponse) response;
            stateResponse.setEvent(OrderEvent.QNAME, new OrderEvent(this));
        }
        return "orderSuccess";
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public TradeInstructionType getTradeInstruction() {
        return tradeInstruction;
    }

    public void setTradeInstruction(TradeInstructionType tradeInstruction) {
        this.tradeInstruction = tradeInstruction;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getNumberStocks() {
        return numberStocks;
    }

    public void setNumberStocks(Integer numberStocks) {
        this.numberStocks = numberStocks;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SelectItem[] getOrderTypeValues() {
        SelectItem[] items = new SelectItem[OrderType.values().length];
        int i=0;
        for (OrderType type : OrderType.values()) {
            items[i++] = new SelectItem(type, type.getName());
        }
        return items;
    }

    public SelectItem[] getTradeInstructionValues() {
        SelectItem[] items = new SelectItem[TradeInstructionType.values().length];
        int i=0;
        for (TradeInstructionType type : TradeInstructionType.values()) {
            items[i++] = new SelectItem(type, type.getName());
        }
        return items;
    }

    public SelectItem[] getStatusValues() {
        SelectItem[] items = new SelectItem[Status.values().length];
        int i=0;
        for (Status status : Status.values()) {
            items[i++] = new SelectItem(status, status.getName());
        }
        return items;
    }
}
