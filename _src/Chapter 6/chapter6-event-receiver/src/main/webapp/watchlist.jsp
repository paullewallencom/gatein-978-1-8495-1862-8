<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects/>

<div class="portlet-section-header">Stock Watchlist</div>
<br/>
<div class="portlet-section-body">
<%
  String[] stocks = (String[])renderRequest.getAttribute("watchList");
  Integer watchCount = (Integer)renderRequest.getAttribute("watchCount");
%>
  <c:choose>
    <c:when test="<%= watchCount > 0 %>">
        <table border="1">
          <thead class="portlet-table-header">
            <tr>
              <th>Ticker</th>
              <th>Company</th>
            </tr>
          </thead>
          <tbody class="portlet-table-body">
          <%
            for (String stock: stocks) {
              if (null != stock) {
                String[] split = stock.split(":");
          %>
                <tr>
                  <td align="center" class="portlet-table-text"><%= split[0] %></td>
                  <td align="center" class="portlet-table-text"><%= split[1] %></td>
                </tr>
          <%
              }
            }
          %>
          </tbody>
        </table>
    </c:when>
    <c:otherwise>
      <span>No stocks being watched at the moment.</span>
    </c:otherwise>
  </c:choose>
</div>
<br/>