package gatein.cookbook.chapter6;

import java.io.IOException;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class WatchlistPortlet extends GenericPortlet {

    private String[] watchedStocks = new String[5];
    private int watchCount = 0;

    @ProcessEvent(qname = "{http://www.gatein.org/xml/ns/cookbook}watchStockEvent")
    public void processWatchStockEvent(EventRequest request, EventResponse response) throws PortletException {
        Event event = request.getEvent();
        StockEvent stockEvent = (StockEvent) event.getValue();
        String watchStock = stockEvent.getTicker();
        if (null != watchStock && watchStock.trim().length() > 0) {
            if (watchCount > 0) {
                boolean found = false;
                for (String stock : watchedStocks) {
                    if (null != stock && stock.equals(watchStock)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    watchedStocks[watchCount++] = watchStock + ":" + stockEvent.getName();
                }
            } else {
                watchedStocks[watchCount++] = watchStock + ":" + stockEvent.getName();
            }
        }
    }

    @RenderMode(name = "view")
    public void displayWatchList(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        request.setAttribute("watchList", watchedStocks);
        request.setAttribute("watchCount", watchCount);
        getPortletContext().getRequestDispatcher("/watchlist.jsp").include(request, response);
    }
}
