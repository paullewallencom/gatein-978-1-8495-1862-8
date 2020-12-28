package gatein.cookbook.chapter10;

import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.portlet.Event;
import javax.portlet.faces.BridgeEventHandler;
import javax.portlet.faces.event.EventNavigationResult;

public class OrderEventHandler implements BridgeEventHandler {

    public EventNavigationResult handleEvent(FacesContext context, Event event) {
        ELResolver facesResolver = context.getELContext().getELResolver();
        OrderManagerEvent mgrBean = (OrderManagerEvent) facesResolver.getValue(context.getELContext(), null, "orderManagerEvent");
        mgrBean.addOrder(((OrderEvent)event.getValue()).getOrder());
        return new EventNavigationResult("/", "/orderList.xhtml");
    }

}
