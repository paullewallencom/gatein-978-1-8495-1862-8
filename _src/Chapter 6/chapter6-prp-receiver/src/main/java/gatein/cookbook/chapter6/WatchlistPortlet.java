package gatein.cookbook.chapter6;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class WatchlistPortlet extends GenericPortlet {

    private String[] watchedStocks = new String[5];
    private int watchCount = 0;

    @RenderMode(name = "view")
    public void displayWatchList(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        String watchStock = (String)request.getParameter("watch_stock");
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
                    watchedStocks[watchCount++] = watchStock;
                }
            } else {
                watchedStocks[watchCount++] = watchStock;
            }
        }
        request.setAttribute("watchList", watchedStocks);
        request.setAttribute("watchCount", watchCount);
        getPortletContext().getRequestDispatcher("/watchlist.jsp").include(request, response);
    }
}
