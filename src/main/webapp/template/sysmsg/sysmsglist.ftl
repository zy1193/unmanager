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
  <title>账户列表 </title>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
 
 </head>
 <script type="text/javascript">
	  	
  	function doPage(did,id){
  		if(!confirm('确定删除吗？删除后不可恢复！')){
	  	 	return;
	  	}
	  	 
     	var url = "${rc.contextPath}/sysMsg/deleteSysMsg.act";
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
				var a = $("#del" + did);
				if(data=='success'){
				 	a.eq(0).hide(500);
				}else{
					alert('删除失败！');
				}
			}
		});
	
 	}
 	
 	function editSysMsg(id){
	  	 if(id==''){
	  	 	alert("编辑系统公告失败，请联系管理员！");
	  	 }
	  	 
		 window.location.href='${rc.contextPath}/sysMsg/toEditSysMsg.act?id='+id;
  	}
			
</script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr >
	        <th>序号</th>
	        <th>品牌</th>
	        <th>标题</th>
		  	<th>内容</th>
			<th>创建日期</th>
			<th>操作</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f6f6f6'" onmouseout="this.bgColor='#FFFFFF'" id="del${item_index}">
	        <td align="center">${item_index}</td>
	        <td align="center">${item.brandId}</td>
	        <td align="center">${item.title}</td>
		  	<td align="center">${item.msg}</td>
		  	<td align="center">${item.ctime?substring(0,10)!""}</td>
			<td align="center"><a href='#' onClick="editSysMsg('${item.id}')" >编辑</a>|<a href='#' onClick="doPage('${item_index}','${item.id}')" >删除</a></td>
	    </tr>
	    </#list>
	    </#if>
	    </table>
	   <div style="text-align:right "><@nav.pagenav /></div>
	  </div>
	  </div>
 </body>
</html>

