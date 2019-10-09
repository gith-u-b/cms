
var powers = [];
$(function(){
	
	$.post("${path}/power!findPowerByUser.action",function(data){
			for(var i in data){
				if(data[i]._parentId == 0){
					powers.push(data[i]);
				}
			}
			
			for(var j in powers){
				var parent = powers[j];
				recursion(parent,data);
			}
			
			for(var l in powers){
				
				if(l == 1){
					
					$('#mainacc').accordion('add', {
						title: powers[l].text,
						content: "<ul id='tree"+powers[l].id+"' class='easyui-tree' data-options='data:powers["+l+"].children,onClick:addTab,animate:true'></ul>",
						selected:true
					});
				}else{
					$('#mainacc').accordion('add', {
					title: powers[l].text,
					content: "<ul id='tree"+powers[l].id+"' class='easyui-tree' data-options='data:powers["+l+"].children,onClick:addTab,animate:true'></ul>",
					selected:false
					
					});
				}
			}
			
		},"json");
});

function  recursion(parent,data){
	if(!parent.url){
		parent.children = [];
		for(var k in data){
			if(parent.id == data[k]._parentId){
				parent.children.push(data[k]);
				recursion(data[k],data);
			}
		}
		
	 }else{
		 parent.attributes = {};
		 parent.attributes.url = parent.url;
	 }
	
}


function addTab(node){
	
	if(node.attributes.url){
		if($("#tab").tabs("exists",node.text)){
			
			$("#tab").tabs("select",node.text);
			
		}else{
			
			$('#tab').tabs('add',{   
			    title:node.text,   
			    content:"<iframe src='"+node.attributes.url+"' width='99%' height='99%' scrolling='no' frameborder='0' ></iframe>",   
			    closable:true,
			    selected:true
			       
			});  
	
		}
	}
}


