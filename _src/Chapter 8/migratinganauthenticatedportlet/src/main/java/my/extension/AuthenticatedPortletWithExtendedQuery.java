package my.extension;

import java.io.IOException;
import java.util.Collection;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.services.organization.GroupHandler;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.webui.application.WebuiRequestContext;

public class AuthenticatedPortletWithExtendedQuery extends GenericPortlet {

	@Override
	protected void doDispatch(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		PortalRequestContext context = (PortalRequestContext) WebuiRequestContext
				.getCurrentInstance();
		ExoContainer pcontainer = context.getApplication()
				.getApplicationServiceContainer();
		OrganizationService securityService = (OrganizationService) pcontainer
				.getComponentInstanceOfType(OrganizationService.class);

		try {

			// Get the handlers for users and groups by the Organization Service
			UserHandler userHandler = securityService.getUserHandler();
			GroupHandler groupHandler = securityService.getGroupHandler();

			// Get the current user
			String currentUser = request.getRemoteUser();
			User user = userHandler.findUserByName(currentUser);

			// Get the roles for the current user
			Collection roles = groupHandler.findGroupsOfUser(currentUser);
			System.out.println(roles);

			// Get all users with the same email
			MyQuery query = new MyQuery("my organization id");
			ListAccess<User> users = userHandler.findUsersByQuery(query);
			System.out.println(users);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}