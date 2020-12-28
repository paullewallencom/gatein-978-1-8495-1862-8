package chapter8;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.config.RepositoryConfigurationException;
import org.exoplatform.services.jcr.ext.distribution.DataDistributionMode;
import org.exoplatform.services.jcr.ext.distribution.DataDistributionType;
import org.exoplatform.services.jcr.ext.distribution.DataDistributionManager;
import org.exoplatform.webui.application.WebuiRequestContext;

public class DataDistributionPortlet extends GenericPortlet {

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, java.io.IOException {
		PortalRequestContext context = (PortalRequestContext) WebuiRequestContext
				.getCurrentInstance();
		ExoContainer pcontainer = context.getApplication()
				.getApplicationServiceContainer();
		RepositoryService repositoryService = (RepositoryService) pcontainer
				.getComponentInstanceOfType(RepositoryService.class);
		DataDistributionManager manager = (DataDistributionManager) pcontainer
				.getComponentInstanceOfType(DataDistributionManager.class);

		try {
			Repository repository = (Repository) repositoryService
					.getRepository("repository");

			Session session = (Session) repository.login();
			Node parentNode = (Node) session.getRootNode();

			// Get the data distribution corresponding to the readable mode

			DataDistributionType type = manager
					.getDataDistributionType(DataDistributionMode.READABLE);

			// Get or create the node corresponding to "john.smith"

			Node node = type.getOrCreateDataNode(parentNode, "john.smith");
			System.out.println("distributed node: " + node);
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (RepositoryConfigurationException e) {
			e.printStackTrace();
		}

	}
}
