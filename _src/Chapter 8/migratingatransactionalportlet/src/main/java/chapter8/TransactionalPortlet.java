package chapter8;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.TransactionRequiredException;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.jboss.cache.transaction.TransactionManagerLookup;

public class TransactionalPortlet extends GenericPortlet {

	@Override
	protected void doDispatch(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		PortalRequestContext context = (PortalRequestContext) WebuiRequestContext
				.getCurrentInstance();
		ExoContainer pcontainer = context.getApplication()
				.getApplicationServiceContainer();
		TransactionManagerLookup tsLookup = (TransactionManagerLookup) pcontainer
				.getComponentInstanceOfType(TransactionManagerLookup.class);
		TransactionManager tManager;
		Transaction transaction = null;

		try {
			tManager = tsLookup.getTransactionManager();
			transaction = tManager.getTransaction();
			if (transaction == null)
				throw new TransactionRequiredException();
			super.doDispatch(request, response);
			transaction.commit();
		} catch (Exception e1) {
			try {
				if (transaction != null)
					transaction.rollback();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}
	}
}
