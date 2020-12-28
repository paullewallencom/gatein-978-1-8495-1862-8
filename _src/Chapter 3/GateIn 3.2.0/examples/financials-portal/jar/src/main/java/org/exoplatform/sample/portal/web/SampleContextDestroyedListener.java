package org.exoplatform.sample.portal.web;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;

import javax.servlet.ServletContextEvent;

public class SampleContextDestroyedListener extends Listener<PortalContainer, ServletContextEvent>
{

   @Override
   public void onEvent(Event<PortalContainer, ServletContextEvent> event) throws Exception
   {
      System.out.println("Destroying the context of the 'financials-portal'");
   }

}
