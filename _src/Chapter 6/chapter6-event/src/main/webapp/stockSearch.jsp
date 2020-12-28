<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects/>

<div class="portlet-section-header">Search Stocks</div>
<br/>
<div class="portlet-section-body">
  <form action='<portlet:actionURL name="searchStocks"/>' method="post">
    <span class="portlet-form-label">Ticker:</span>
    <input class="portlet-form-input-field" type="text" name="ticker"/>
    <input class="portlet-form-button" type="Submit" value="Search"/>
  </form>
  <br/>
<%
  String ticker = (String)renderRequest.getParameter("ticker");
  String[] results = (String[])renderRequest.getAttribute("stockList");
%>
  <c:choose>
    <c:when test="<%= results != null && results.length > 0 %>">
        <table border="1">
          <thead class="portlet-table-header">
            <tr>
              <th>Ticker</th>
              <th>Company</th>
              <th></th>
            </tr>
          </thead>
          <tbody class="portlet-table-body">
          <%
            for (String stock: results) {
              if (null != stock) {
                String[] split = stock.split(":");
          %>
                <tr>
                  <td align="center" class="portlet-table-text"><%= split[0] %></td>
                  <td align="center" class="portlet-table-text"><%= split[1] %></td>
                  <td align="center" class="portlet-table-text"><a class="portlet-font-dim" href="<portlet:actionURL name='watch'><portlet:param name='stock' value='<%= split[0] %>' /></portlet:actionURL>">Watch!</a></td>
                </tr>
          <%
              }
            }
          %>
          </tbody>
        </table>
    </c:when>
    <c:otherwise>
      <c:if test="<%= ticker != null && ticker.trim().length() > 0 %>">
        <span>No results found for ticker: <%= ticker %></span>
      </c:if>
    </c:otherwise>
  </c:choose>
</div>
<br/>
