<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="java.math.BigDecimal" %>

<portlet:defineObjects/>

<div class="portlet-section-header">Stock Watchlist</div>
<br/>
<div class="portlet-section-body">
<%
  String[] stocks = (String[])renderRequest.getAttribute("watchList");
  BigDecimal[] prices = (BigDecimal[])renderRequest.getAttribute("prices");
%>
        <table border="1">
          <thead class="portlet-table-header">
            <tr>
              <th>Ticker</th>
              <th>Company</th>
              <th>Stock Price</th>
              <th></th>
            </tr>
          </thead>
          <tbody class="portlet-table-body">
          <%
            int count = 0;
            for (String stock: stocks) {
              if (null != stock) {
                String[] split = stock.split(":");
          %>
          	    <portlet:resourceURL var="refreshPriceUrl" id="refreshPrice">
          	      <portlet:param name="stock" value="<%= Integer.toString(count) %>" />
          	    </portlet:resourceURL>

                <tr>
                  <td align="center" class="portlet-table-text"><%= split[0] %></td>
                  <td align="center" class="portlet-table-text"><%= split[1] %></td>
                  <td align="center" class="portlet-table-text"><span id="<portlet:namespace/>price<%= count %>"><%= prices[count] %></span></td>
                  <td align="center" class="portlet-table-text"><a class="portlet-font-dim" href="#" onclick="<portlet:namespace/>updatePrice('${refreshPriceUrl}', <%= count++ %>);">Refresh Price</a></td>
                </tr>
          <%
              }
            }
          %>
          </tbody>
        </table>
</div>
<br/>
<script type="text/javascript" src="<%= renderRequest.getContextPath() %>/js/jquery-1.7.2.min.js"></script>
<script type='text/javascript'>
  function <portlet:namespace/>updatePrice(updatePriceUrl, index) {
    $.ajax(
    	      {
       	        type: "POST",
       	        url: updatePriceUrl,
       	        cache: false,
       	        success: function (data) {
           	      $('#<portlet:namespace/>price'+index).html(data);
           	    }
        	  }
    	    );
  }
</script>