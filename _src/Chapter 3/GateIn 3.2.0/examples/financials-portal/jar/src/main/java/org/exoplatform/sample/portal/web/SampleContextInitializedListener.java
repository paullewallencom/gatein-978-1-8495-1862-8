package org.exoplatform.sample.portal.web;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;

import javax.servlet.ServletContextEvent;

public class SampleContextInitializedListener extends Listener<PortalContainer, ServletContextEvent>
{

   @Override
   public void onEvent(Event<PortalContainer, ServletContextEvent> event) throws Exception
   {
      System.out.println("Initializing the context of the 'financials-portal'");
   }

}
