package my.sample;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

@ComponentConfig(type = MyPortlet.class, lifecycle = UIApplicationLifecycle.class, template = "app:/groovy/MyApplication/webui/component/MyPortlet.gtmpl")
public class MyPortlet extends UIPortletApplication {
	public MyPortlet() throws Exception {

	}
}
