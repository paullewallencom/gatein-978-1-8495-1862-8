package chapter8;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.services.cache.CacheService;
import org.exoplatform.services.cache.ExoCache;
import org.exoplatform.webui.application.WebuiRequestContext;

public class ReadReplicationPortlet extends GenericPortlet {
	
	@Override
	protected void doView(RenderRequest request, RenderResponse response) throws PortletException, java.io.IOException {
		PortalRequestContext context = (PortalRequestContext) WebuiRequestContext
				.getCurrentInstance();
		ExoContainer pcontainer = context.getApplication()
				.getApplicationServiceContainer();
		CacheService cacheService = (CacheService) pcontainer
				.getComponentInstanceOfType(CacheService.class);
		ExoCache<String, String> cache = cacheService
				.getCacheInstance("/production");
		String value = cache.get("mynewkey");
		System.out.println("chapter 8 - replicated value = " + value);
	}
}
