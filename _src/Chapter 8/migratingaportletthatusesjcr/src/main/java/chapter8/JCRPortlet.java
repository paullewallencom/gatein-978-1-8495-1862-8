package chapter8;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
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
import org.exoplatform.webui.application.WebuiRequestContext;

public class JCRPortlet extends GenericPortlet {

	public void printAllNodes(Node root, String space)
			throws RepositoryException {
		NodeIterator nodeIterator = (NodeIterator) root.getNodes();
		while (nodeIterator.hasNext()) {
			Node node = (Node) nodeIterator.nextNode();
			System.out.println(space + node.getName());
			printAllNodes(node, space + "   ");
		}
	}
	
	@Override
	protected void doView(RenderRequest request, RenderResponse response) throws PortletException, java.io.IOException {
		PortalRequestContext context = (PortalRequestContext) WebuiRequestContext
				.getCurrentInstance();
		ExoContainer pcontainer = context.getApplication()
				.getApplicationServiceContainer();
		RepositoryService repositoryService = (RepositoryService) pcontainer
				.getComponentInstanceOfType(RepositoryService.class);
		try {
			Repository repository = (Repository) repositoryService
					.getRepository("repository");

			Session session = (Session) repository.login();
			Node root = (Node) session.getRootNode();
			Node newNode = (Node) root.addNode("my new node");
			newNode.setProperty("my property", "my value");
			session.save();
			printAllNodes(root, "");
			session.logout();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (RepositoryConfigurationException e) {
			e.printStackTrace();
		}
		
	}

}
