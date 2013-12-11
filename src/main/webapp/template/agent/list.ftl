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
  <title> 后台综合管理系统--列表 </title>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
 
 </head>
 <script type="text/javascript">
		function showDetail(divid){			//隐藏层，传入每行的序号
				var len = ($("#listTable tr").length-1)/2;	//总行数
		  		var d = document.getElementById("div"+divid);	//隐藏层id
		  		var img = document.getElementById("img"+divid);	//按钮id
		  		if(d.style.display=='none'){
		  			d.style.display='';//'block';IE浏览器的时候写成block,隐藏的层显示正常，但是其他浏览器不正常。写成空值全正常
		  			img.src="..${rc.contextPath}/images/b.gif";	//+号变-号
		  	  		for(var i=divid-len;i<divid;i++){	//点击其他按钮时隐藏前隐藏层(以上的层)
		  	  	  		if(document.getElementById("div"+i)!= null && document.getElementById("img"+i) !=null){
		  	  		       document.getElementById("div"+i).style.display='none';	
		  	  		       document.getElementById("img"+i).src="..${rc.contextPath}/images/a.gif";
		  	  		         }
		  	  		       }
	  	  		    for(var j=divid+len;j>divid;j--){	//点击其他按钮时隐藏前隐藏层(以下的层)
		  	  	  		if(document.getElementById("div"+j)!= null && document.getElementById("img"+j) !=null){
		  	  	  		document.getElementById("div"+j).style.display='none';
		  	  	  		document.getElementById("img"+j).src="..${rc.contextPath}/images/a.gif";
		  	  	  		  }
		  	  	  		}
		         }else{
			  	     d.style.display='none';
			  		 img.src="..${rc.contextPath}/images/a.gif";
		  	           }
		  	}
		  	
		  	function reset1(){	
			  document.queryform.name.value="";
		  	}
		  	
			
  	  </script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>
  <form name="queryform" namespace="/amp" action="${rc.contextPath}/agentList.act" method="post">
  <div class="right_box">
  	<div id="titlediv">
  		<table>
  			<tr>
  				<th>代理商</th>
  				<th><input  class="required ipb_s" type='text' name='name' id='name' value='${name!""}' /></th>
  				<!--th>&nbsp;创建时间</th>
  				<th><input type="text" name="startTime" id="startTime" class="Wdate" type="text"  onclick="WdatePicker({startDate: '%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${startTime!''}"/></th>
  				<th>&nbsp;到 <input type="text" name="endTime" id="endTime" class="Wdate" type="text"   onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${endTime!''}"/></th>
  				<th>&nbsp;状态</th>
  				<th><select  class="required ipb_l"  name='status' id='status'>
  						<option value='999' <#if status??><#if status=="999"> selected="selected" <#else> </#if> </#if> >全部</option>
  						<option value='0' <#if status??><#if status=="0"> selected="selected" <#else> </#if> </#if> >未审核</option>
  						<option value='1' <#if status??><#if status=="1"> selected="selected" <#else> </#if> </#if> >已审核</option>
  				    </select>
  				</th-->
  				<th style="padding-bottom:5px;"><button  class="btn but_s" type='submit' >查 询</button>
  				<!--button  class="btn but_s" type='submit' >批量审核</button-->
  				<!--button  class="btn but_s" type='submit' >停止合作</button-->
  				<input  class="btn but_s" type='button' onClick='reset1()' value='重 置' /></th>
  			</tr>
  		</table>
  	</div>
  </form>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr >
	        <th>序号</th>
	        <th>别名</th>
		  	<th>姓名</th>
			<th>座机</th>
			<th>手机</th>
			<!--th>身份证</th-->
			<th>QQ</th>
			<th>创建时间</th>
			<!--th>操作</th-->
			<th width="4%">详情</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f6f6f6'" onmouseout="this.bgColor='#FFFFFF'" >
	        <td align="center">${item_index}</td>
	        <td align="center">${item.alias}</td>
		  	<td align="center">${item.name}</td>
		  	<td align="center">${item.telNo}</td>
		  	<td align="center">${item.mobileNo}</td>
			<!--td align="center">${item.idCardNo!""}</td-->
			<td align="center">${item.qq!""}</td>
			<td align="center">${item.createTime?substring(0,19)!""}</td>
		  	<td align="center"><img src="..${rc.contextPath}/images/a.gif" id ='img${item_index}' title="点击展开" onclick='showDetail(${item_index});'></td>
	    </tr>
	    <!--以下为隐藏的层-->
	    <tr style="display:none;" id="div${item_index}">
	    	<td colSpan='10'>
	    	   <div>
	    	   		<table width="100%" border='0' id="popup">
	    	   		    <tr>
	    	   		       <td style="width:100px">身份证</td>
	    	   		       <td>${item.idCardNo!""}</td>
	    	   		       <td style="width:100px">备注</td>
	    	   		       <td>${item.remarks!""}</td>
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

