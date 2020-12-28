package org.exoplatform.sample.portal.web;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.listener.Event;
import org.exoplatform.services.listener.Listener;

import javax.servlet.http.HttpSessionEvent;

public class SampleHttpSessionDestroyedListener extends Listener<PortalContainer, HttpSessionEvent>
{

   @Override
   public void onEvent(Event<PortalContainer, HttpSessionEvent> event) throws Exception
   {
      System.out.println("Destroying a session of the 'financials-portal'");
   }

}
