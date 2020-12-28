package gatein.cookbook.chapter6;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.ProcessAction;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class StockSearchPortlet extends GenericPortlet {
    private String[] availableStocks = null;

    @Override
    public void init(PortletConfig config) throws PortletException {
        super.init(config);
        initStockList();
    }

    @RenderMode(name = "view")
    public void display(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        request.setAttribute("stockList", filterStocks(request));
        getPortletContext().getRequestDispatcher("/stockSearch.jsp").include(request, response);
    }

    @ProcessAction(name = "searchStocks")
    public void searchStocks(ActionRequest request, ActionResponse response) throws PortletException {
        response.setRenderParameter("ticker", request.getParameter("ticker"));
    }

    @ProcessAction(name = "watch")
    public void addWatch(ActionRequest request, ActionResponse response) throws PortletException {
        String stock = request.getParameter("stock");
        request.getPortletSession().setAttribute("watchStock", stock, PortletSession.APPLICATION_SCOPE);
    }

    private void initStockList() {
        availableStocks = new String[5];
        
        availableStocks[0] = "IBM:International Business Machines Corp.";
        availableStocks[1] = "IBN:Icici Bank Limited";
        availableStocks[2] = "REV:Revlon";
        availableStocks[3] = "RHI:Robert Half International Inc.";
        availableStocks[4] = "RHT:Red Hat";
    }

    private String[] filterStocks(RenderRequest request) {
        String filter = request.getParameter("ticker");
        if (null != filter && filter.trim().length() > 0) {
            filter = filter.trim().toLowerCase();
            StringBuffer filterStocks = new StringBuffer(60);
            boolean found = false;
            for (String stock : availableStocks) {
                if (stock.toLowerCase().startsWith(filter)) {
                    if (found) {
                        filterStocks.append(";");
                    }
                    filterStocks.append(stock);
                    found = true;
                }
            }
            return found == true ? filterStocks.toString().split(";") : null;
        }
        return availableStocks;
    }
}
