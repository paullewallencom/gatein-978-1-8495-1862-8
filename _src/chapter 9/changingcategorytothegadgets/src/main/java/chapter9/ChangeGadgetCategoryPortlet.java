package chapter9;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.exoplatform.application.registry.Application;
import org.exoplatform.application.registry.ApplicationCategory;
import org.exoplatform.application.registry.ApplicationRegistryService;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.webui.application.WebuiRequestContext;

public class ChangeGadgetCategoryPortlet extends GenericPortlet {

	@Override
	protected void doDispatch(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		PortalRequestContext context = (PortalRequestContext) WebuiRequestContext
				.getCurrentInstance();
		ExoContainer pcontainer = context.getApplication()
				.getApplicationServiceContainer();

		ApplicationRegistryService applicationRegistryService = (ApplicationRegistryService) pcontainer
				.getComponentInstanceOfType(ApplicationRegistryService.class);

		try {
			if (applicationRegistryService
					.getApplication("GadgetsMine/SkypeTalk") == null) {
				ApplicationCategory applicationCategory = new ApplicationCategory();
				applicationCategory.setName("GadgetsMine");
				applicationCategory.setDisplayName("GadgetsMine");
				Application application = applicationRegistryService
						.getApplication("Gadgets/SkypeTalk");
				applicationRegistryService.save(applicationCategory,
						application);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
