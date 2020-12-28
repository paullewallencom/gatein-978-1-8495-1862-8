package gatein.cookbook.chapter10;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

public class WatchlistPortlet extends GenericPortlet {
    private String[] watchedStocks = null;
    private BigDecimal[] watchedStockPrice = null;

    @Override
    public void init() throws PortletException {
        initStockList();
        initStockPrices();
    }

    private void initStockList() {
        watchedStocks = new String[3];
        
        watchedStocks[0] = "IBM:International Business Machines Corp.";
        watchedStocks[1] = "REV:Revlon";
        watchedStocks[2] = "RHT:Red Hat";
    }

    private void initStockPrices() {
        watchedStockPrice = new BigDecimal[3];

        watchedStockPrice[0] = new BigDecimal(32.10).setScale(2, RoundingMode.HALF_UP);
        watchedStockPrice[1] = new BigDecimal(12.54).setScale(2, RoundingMode.HALF_UP);
        watchedStockPrice[2] = new BigDecimal(57.01).setScale(2, RoundingMode.HALF_UP);
    }

    @RenderMode(name = "view")
    public void display(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        request.setAttribute("watchList", watchedStocks);
        request.setAttribute("prices", watchedStockPrice);
        getPortletContext().getRequestDispatcher("/watchlist.jsp").include(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
        String resourceID = request.getResourceID();
        PrintWriter writer = response.getWriter();

        if ("refreshPrice".equalsIgnoreCase(resourceID)) {
            int index = Integer.parseInt(request.getParameter("stock"));
            writer.write(updateStockPrice(index));
        }

        writer.flush();
        writer.close();
    }

    protected String updateStockPrice(int index) {
        Float increment = new Random(watchedStockPrice[index].longValue()).nextFloat();
        BigDecimal newValue = watchedStockPrice[index].add(new BigDecimal(increment.doubleValue()));
        newValue = newValue.setScale(2, RoundingMode.HALF_UP);
        watchedStockPrice[index] = newValue;
        return newValue.toString();
    }
}
