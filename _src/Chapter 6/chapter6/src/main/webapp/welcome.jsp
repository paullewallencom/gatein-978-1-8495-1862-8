<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<portlet:defineObjects/>
<fmt:setBundle basename="gatein.cookbook.chapter6.HelloWorldPortlet" />

<div>
  <div class="portlet-section-header"><fmt:message key="welcome.heading" /></div>
  <br/>
  <div class="portlet-section-body">
    <form action='<portlet:actionURL name="nameAction"/>' method="post">
      <span class="portlet-form-label"><fmt:message key="welcome.form.name" />:</span>
      <input class="portlet-form-input-field" type="text" name="yourname"/>
      <input class="portlet-form-button" type="Submit" value="<fmt:message key="welcome.form.submit" />"/>
    </form>
  </div>
  <br/>
</div>
