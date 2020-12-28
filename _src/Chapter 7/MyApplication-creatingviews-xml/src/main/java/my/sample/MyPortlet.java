package my.sample;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.exoplatform.portal.mop.navigation.GenericScope;
import org.exoplatform.portal.webui.navigation.UIPortalNavigation;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.application.portlet.PortletRequestContext;
import org.exoplatform.webui.core.UIPortletApplication;

public class MyPortlet extends UIPortletApplication {
	public MyPortlet() throws Exception {

		// Take the current PortletRequest
		PortletRequestContext context = (PortletRequestContext) WebuiRequestContext
				.getCurrentInstance();
		PortletRequest prequest = context.getRequest();

		/*
		 * Take the portlet preferences and find a preference called template.
		 * If it doesn’t exist add the default template UISitemapTree.gtmpl. It
		 * implements a navigable tree structure
		 */

		PortletPreferences prefers = prequest.getPreferences();
		String template = prefers.getValue("template",
				"system:/groovy/webui/core/UISitemapTree.gtmpl");

		/*
		 * Add the UIPortalNavigation as a child of the application and set two
		 * properties
		 */

		UIPortalNavigation uiPortalNavigation = addChild(
				UIPortalNavigation.class, "MyUISiteMap", null);
		uiPortalNavigation.setTemplate(template);
		uiPortalNavigation.setUseAjax(false);

		/*
		 * Set in memory the informations present in the tree. In this code are
		 * memorized the first 2 levels of the tree
		 */

		uiPortalNavigation.setScope(GenericScope.treeShape(2));

	}

}
