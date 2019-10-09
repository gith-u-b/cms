
<div id="nav">
	  <div id="menu" class="page box relative">
		<dl id="topmenu">  
		<dt class="menu_first" style="width:${width}px"><a href="${path}/index.html" target="_self" ><span>首 页</span></a></dt>
		
		<#list cs as c>
       <dt ><a href="${path}/html_findByChannel.action?channel.channelId=${c.channelId}&page=1&rows=${c.page}" target="_self" ><span>${c.channelName}</span></a></dt>
       </#list>
		
	  </dl>
	  </div> 
</div>