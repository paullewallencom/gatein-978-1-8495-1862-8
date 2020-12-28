package gatein.cookbook.chapter6;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class HelloWorldPortlet extends GenericPortlet {

    @RenderMode(name = "view")
    public void display(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        if (null == request.getParameter("yourname") || "".equals(request.getParameter("yourname"))) {
            getPortletContext().getRequestDispatcher("/welcome.jsp").include(request, response);
        } else {
            getPortletContext().getRequestDispatcher("/helloWorld.jsp").include(request, response);
        }
    }

    @ProcessAction(name = "nameAction")
    public void nameAction(ActionRequest request, ActionResponse response) throws PortletException {
        response.setRenderParameter("yourname", request.getParameter("yourname"));
    }
}
