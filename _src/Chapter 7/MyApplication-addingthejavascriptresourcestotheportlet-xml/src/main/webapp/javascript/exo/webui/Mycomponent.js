function Mycomponent() {};

Mycomponent.prototype.updateTreeNode = function (nodeToUpdate, getNodeURL) {
	if (!nodeToUpdate || ! getNodeURL) return;
	
	var subGroup = eXo.core.DOMUtil.findFirstChildByClass(nodeToUpdate.parentNode, "div", "ChildrenContainer") ;
	if (!subGroup || subGroup.innerHTML.trim() !== "") return;	
		
	var jsChilds = ajaxAsyncGetRequest(getNodeURL, false);	
	try {
		var data = eXo.core.JSON.parse(jsChilds);				
	} catch (e) {		
	}	
	if (data && data.length) {
		eXo.webui.Mycomponent.generateHtml(data, nodeToUpdate, subGroup);			
		return;
	}
	eXo.core.DOMUtil.removeClass(nodeToUpdate, "CollapseIcon");
	eXo.core.DOMUtil.addClass(nodeToUpdate, "NullItem");
};

Mycomponent.prototype.generateHtml = function(data, nodeToUpdate, subGroup) {						
	function toHtml(node, isLast) {
		if (!node) return;
		var lastNode = isLast ? "LastNode" : "";
		var actionLink = node.actionLink ? node.actionLink : "javascript:void(0);";
		
		var actionExpand = 'eXo.webui.Mycomponent.updateTreeNode(this, "' + node.getNodeURL + '")';
		var actionCollapse = 'ajaxAsyncGetRequest("' + node.collapseURL + '", true)'; 		 
			
		var str = "";			
		if (node.hasChild) {
			str += "<div class='" + lastNode + " Node'>";			
			if (node.isExpanded) {
				str += "<div class='CollapseIcon ClearFix' onclick='eXo.portal.UIPortal.collapseExpand(this); " + actionCollapse + "'>";
				str += "<a class='NodeIcon DefaultPageIcon' href='" + actionLink + "'>" + node.label + "</a>";
				str += "</div><div class='ChildrenContainer' style='display: block'>";
				for (var idx = 0; idx < node.childs.length; idx++) {
					str += toHtml(node.childs[idx], idx == node.childs.length - 1);
				}				
			} else {
				str += "<div class='ExpandIcon ClearFix' onclick='eXo.portal.UIPortal.collapseExpand(this); " + actionExpand + "'>";
				str += "<a class='NodeIcon DefaultPageIcon' href='" + actionLink + "'>" + node.label + "</a>";
				str += "</div><div class='ChildrenContainer' style='display: none'>";
				for (var idx = 0; idx < node.childs.length; idx++) {
					str += toHtml(node.childs[idx], idx == node.childs.length - 1);
				}	
			}
			str += "</div></div>";
		} else {
			str += "<div class='" + lastNode + " Node ClearFix'><div class='NullItem'><div class='ClearFix'>";
			str += "<a class='NodeIcon DefaultPageIcon' href='" + actionLink + "'>" + node.label + "</a></div></div></div>";			
		}
		return str;
	}
	
	var htmlFrags = "";	
	for (var i = 0; i < data.length; i++) {
		htmlFrags += toHtml(data[i], i == data.length - 1);
	}
	
	subGroup.innerHTML = htmlFrags;
	subGroup.style.display = "block";
};

eXo.webui.Mycomponent = new Mycomponent();
