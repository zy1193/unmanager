<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.list li {line-height:25px;color:#0099dd;font-weight:bold;}
.list li span{margin-left:10px;color:#000;font-size:12px;}
</style>
</head>

<body>
<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr >
	        <th align="center">卡号</th>
		  	<th align="center">密码</th>
			<th align="center">商品</th>
			<th align="center">帐号</th>
			<th align="center">时间</th>
			<th align="center">状态</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f6f6f6'" onmouseout="this.bgColor='#FFFFFF'" >
	        <td align="center">${item.cno!""}</td>
	        <td align="center">${item.cps!""}</td>
		  	<td align="center">${item.goodsName!""}</td>
		  	<td align="center">${item.uid!""}</td>
		  	<td align="center">${item.time?substring(0,19)!""}</td>
		  	<#if item.status=='success'>
		  	<td align="center">成功</td>
		  	<#else>
		  	<td align="center">失败</td>
		  	</#if>
		</tr>
		</#list>
	    </#if>
	  </table>
</div>
</body>

</html>