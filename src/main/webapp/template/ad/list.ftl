<#import "/include/navbar.ftl" as nav>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache"/> 
	<meta http-equiv="Cache-Control" content="no-store, must-revalidate"/> 
	<meta http-equiv="expires" content="0"/>
    <link href="${rc.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
	<style>
		#div {
			width: auto;
			height:1%;
			padding: 5px 0px;;			
		}
		
		#titlediv {
			width: 100%;			
			padding-top: 15px;
			
		}
	</style>
  <title>广告列表 </title>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
 
 </head>
 <script type="text/javascript">
 
 function showDetail(divid){			//隐藏层，传入每行的序号
		var len = ($("#listTable tr").length-1)/2;	//总行数
  		var d = document.getElementById("div"+divid);	//隐藏层id
  		var img = document.getElementById("img"+divid);	//按钮id
  		if(d.style.display=='none'){
  			d.style.display='';//'block';IE浏览器的时候写成block,隐藏的层显示正常，但是其他浏览器不正常。写成空值全正常
  			img.src="${rc.contextPath}/images/b.gif";	//+号变-号
  	  		for(var i=divid-len;i<divid;i++){	//点击其他按钮时隐藏前隐藏层(以上的层)
  	  	  		if(document.getElementById("div"+i)!= null && document.getElementById("img"+i) !=null){
  	  		       document.getElementById("div"+i).style.display='none';	
  	  		       document.getElementById("img"+i).src="${rc.contextPath}/images/a.gif";
  	  		         }
  	  		       }
  		    for(var j=divid+len;j>divid;j--){	//点击其他按钮时隐藏前隐藏层(以下的层)
  	  	  		if(document.getElementById("div"+j)!= null && document.getElementById("img"+j) !=null){
  	  	  		document.getElementById("div"+j).style.display='none';
  	  	  		document.getElementById("img"+j).src="${rc.contextPath}/images/a.gif";
  	  	  		  }
  	  	  		}
         }else{
	  	     d.style.display='none';
	  		 img.src="${rc.contextPath}/images/a.gif";
  	           }
	 }
  	
  	function doPage(id,cid){
  		if(!confirm('确定删除吗？删除后不可恢复！')){
	  	 	return;
	  	}
	  	 
     	var url = "${rc.contextPath}/ad/deleteAd.act";
		$.ajax({
			url : url,
			dataType : "html",
			type : "POST",
			async : false,
			data : {
				id : $.trim(id)
			},
			error: function(){
		        alert('删除失败！');
		    },
			success : function(data) {
				var a = $("#del" + cid);
				if(data=='success'){
				 	a.eq(0).hide(500);
				}else{
					alert('删除失败！');
				}
			}
		});
	
 	}
 	
 	function editAcct(id){
	  	 if(!id){
	  	 	alert("编辑失败，请联系管理员！");
	  	 }
	  	 
		 window.location.href='${rc.contextPath}/ad/toEditAd.act?id='+id;
  	}
			
</script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>
  <form name="queryform" namespace="/ad" action="" method="post">
  <div class="right_box">
  	<div id="titlediv">
  	</div>
  </form>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr >
	        <th width='5%'>序号</th>
	        <th width='5%'>ID</th>
	        <th>名称</th>
		  	<th>跳转地址</th>
			<th width='8%'>操作</th>
			<th width='5%'>详情</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f6f6f6'" onmouseout="this.bgColor='#FFFFFF'"  id="del${item_index}">
	        <td align="center">${item_index}</td>
	        <td align="center">${item.adpid}</td>
	        <td align="center">${item.name}</td>
		  	<td align="center">${item.url}</td>
			<td align="center"><a href='#' onClick="editAcct('${item.id}')" >编辑</a>|<a href='#' onClick="doPage('${item.id}','${item_index}')" >删除</a></td>
		  	<td align="center"><img src="${rc.contextPath}/images/a.gif" id ='img${item_index}' title="点击展开" onclick='showDetail(${item_index});'></td>
	    </tr>
	    <!--以下为隐藏的层-->
	    <tr style="display:none;" id="div${item_index}">
	    	<td colSpan='10'>
	    	   <div>
	    	   		<table width="100%" border='0' id="popup">
	    	   		    <tr>
	    	   		       <td style="width:100px">图片地址：</td>
	    	   		       <td>${item.img!""}</td>
	    	   		       <td style="width:100px">品牌：</td>
	    	   		       <td>亿信</td>
	    	   		    </tr>
	    	   		</table>
	    	   </div>
	    	</th>
	    </tr>
	    </#list>
	    </#if>
	    </table>
	   <div style="text-align:right "><@nav.pagenav /></div>
	  </div>
	  </div>
 </body>
</html>

