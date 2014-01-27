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
  <title>配置列表 </title>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
 
 </head>
 <script type="text/javascript">
 	
 	function editAcct(id){
	  	 if(!id){
	  	 	alert("编辑失败，请联系管理员！");
	  	 }
	  	 
		 window.location.href='${rc.contextPath}/conf/toEditConf.act?id='+id;
  	}
			
</script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>
  <form name="queryform" namespace="/conf" action="" method="post">
  <div class="right_box">
  	<div id="titlediv">
  	</div>
  </form>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr >
	        <th width='5%'>序号</th>
	        <th width='5%'>品牌</th>
	        <th>名称</th>
		  	<th>值</th>
		  	<th>备注</th>
			<th width='8%'>操作</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f6f6f6'" onmouseout="this.bgColor='#FFFFFF'"  id="del${item_index}">
	        <td align="center">${item_index}</td>
	        <td align="center">${item.brandid}</td>
	        <td align="center">${item.param}</td>
		  	<td align="center">${item.value}</td>
		  	<td align="center">${item.remark}</td>
			<td align="center"><a href='#' onClick="editAcct('${item.id}')" >编辑</a></td>
	    </tr>
	    <!--以下为隐藏的层-->
	    </#list>
	    </#if>
	    </table>
	   <div style="text-align:right "><@nav.pagenav /></div>
	  </div>
	  </div>
 </body>
</html>

