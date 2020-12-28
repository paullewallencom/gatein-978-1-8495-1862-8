package chapter9;

import java.io.IOException;
import java.util.List;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.exoplatform.application.gadget.Gadget;
import org.exoplatform.application.gadget.GadgetRegistryService;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.webui.application.WebuiRequestContext;

public class GadgetRegistryPortlet extends GenericPortlet {

	@Override
	protected void doDispatch(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		PortalRequestContext context = (PortalRequestContext) WebuiRequestContext
				.getCurrentInstance();
		ExoContainer pcontainer = context.getApplication()
				.getApplicationServiceContainer();
		GadgetRegistryService registryService = (GadgetRegistryService) pcontainer
				.getComponentInstanceOfType(GadgetRegistryService.class);
		try {
			List<Gadget> gadgets = registryService.getAllGadgets();
			for (Gadget gadget : gadgets)
				System.out.println(gadget.getTitle());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
