<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<portlet:defineObjects/>
<fmt:setBundle basename="gatein.cookbook.chapter6.HelloWorldPortlet" />

<div>
  <div class="portlet-section-header"><fmt:message key="helloworld.heading" /></div>
  <br/>
  <div class="portlet-section-body"><fmt:message key="helloworld.text.hello" /> <%= renderRequest.getParameter("yourname") %> <fmt:message key="helloworld.text.message" /></div>
  <br/>
  <a href="<portlet:renderURL portletMode='view' />"><fmt:message key="link.reset" /></a>
  <br/>
</div>