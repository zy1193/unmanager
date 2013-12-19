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
	  	
  	function reset1(){	
		document.queryform.uid.value="";
		document.queryform.phone.value="";
		document.queryform.agent.value="";
		document.queryform.taskname.value="";
		document.queryform.startTime.value="";
		document.queryform.endTime.value="";
		
		document.queryform.acctType.value="";
		document.queryform.isvalid.value="";
		document.queryform.first.value="";
  	}
  	
  	function test(){
	  	if(!confirm('导出数据，根据数据量的大小需要的时间不同，请耐心等待，切勿重复操作！')){
	  	 	return;
	  	 }
	  	 var uid=$('#uid').val();
	  	 var phone=$('#phone').val();
	  	 var agent=$('#agent').val();
	  	 var taskname=$('#taskname').val();
	  	 var startTime=$('#startTime').val();
	  	 var endTime=$('#endTime').val();
	  	 if(uid=='' && phone=='' && agent=='' && taskname=='' && startTime=='' && endTime==''){
	  	 	if(!confirm('确实要导出所有账户吗？最好选择条件导出，重新选择请点“取消”！')){
	  	 		return;
	  	 	}
	  	 }
	  	 
		 window.location.href='${rc.contextPath}/acct/exportExcel.act?uid='+uid+'&phone='+phone+'&agent='+agent+'&taskname='+taskname+'&startTime='+startTime+'&endTime='+endTime;
  	}
  	
  	function doPage(id,uid){
  		if(!confirm('确定删除吗？删除后不可恢复！')){
	  	 	return;
	  	}
	  	 
     	var url = "${rc.contextPath}/acct/deleteAcct.act";
		$.ajax({
			url : url,
			dataType : "html",
			type : "POST",
			async : false,
			data : {
				uid : $.trim(uid)
			},
			error: function(){
		        alert('删除失败！');
		    },
			success : function(data) {
				var a = $("#del" + id);
				if(data=='success'){
				 	a.eq(0).hide(500);
				}else{
					alert('删除失败！');
				}
			}
		});
	
 	}
 	
 	function editAcct(brandId, uid){
	  	 if(uid=='' || brandId==''){
	  	 	alert("编辑账户失败，请联系管理员！");
	  	 }
	  	 
		 window.location.href='${rc.contextPath}/acct/toEditAcct.act?uid='+uid+'&brandId='+brandId;
  	}
			
</script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>
  <form name="queryform" namespace="/acct" action="${rc.contextPath}/acct/acctList.act" method="post">
  <div class="right_box">
  	<div id="titlediv">
  		<table border='0'>
  			<tr>
  				<th align="right">帐号：</th>
  				<th align="left"><input  class="required ipb_s" type='text' name='uid' id='uid' value='${uid!""}' /></th>
  				<th align="right">手机：</th>
  				<th align="left"><input  class="required ipb_s" type='text' name='phone' id='phone' value='${phone!""}' /></th>
  				<th align="right">代理商：</th>
	  			<th align="left"><input  class="required ipb_s" type='text' name='agent' id='agent' value='${agent!""}' /></th>
  				<th align="right">批次：</th>
  				<th align="left"><input  class="required ipb_s" type='text' name='taskname' id='taskname' value='${taskname!""}' /></th>
  				<th align="right">注册：</th>
  				<th align="left"><select name="first" id="first" width="200px" class="required ipb_l" >
	  				    <#if first=='y'>
	  				    <option value=''>--请选择--</option>
	   				    <option value='y' selected>未注册</option>
	   				    <option value='n'>已注册</option>
	   				    <#elseif first=='n'>
	   				    <option value=''>--请选择--</option>
	   				    <option value='y'>未注册</option>
	   				    <option value='n' selected>已注册</option>
	   				    <#else>
	   				    <option value='' selected>--请选择--</option>
	   				    <option value='y'>未注册</option>
	   				    <option value='n'>已注册</option>
	   				    </#if>
	  				</select>
	  			</th>
	  			<th align="right">过期：</th>
  				<th align="left"><select name="isvalid" id="isvalid" width="200px" class="required ipb_l" >
	  				    <#if isvalid=='y'>
	  				    <option value=''>--请选择--</option>
	   				    <option value='y' selected>未过期</option>
	   				    <option value='n'>已过期</option>
	   				    <#elseif isvalid=='n'>
	   				    <option value=''>--请选择--</option>
	   				    <option value='y'>未过期</option>
	   				    <option value='n' selected>已过期</option>
	   				    <#else>
	   				    <option value='' selected>--请选择--</option>
	   				    <option value='y'>未过期</option>
	   				    <option value='n'>已过期</option>
	   				    </#if>
	  				</select>
	  			</th>
  				</tr>
  				<tr>
  				<th align="right">创建时间：</th>
  				<th align="left"><input type="text" name="startTime" id="startTime" class="Wdate" type="text"  onclick="WdatePicker({startDate: '%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${startTime!''}"/></th>
  				<th align="right">到：</th>
  				<th align="left"><input type="text" name="endTime" id="endTime" class="Wdate" type="text"   onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${endTime!''}"/></th>
  				
  				<th align="right">账号类型：</th>
  				<th align="left">
  				<select name="acctType" id="acctType" width="200px" class="required ipb_l" >
  					<#if acctType=='year'>
  				    <option value=''>--请选择--</option>
   				    <option value='year' selected>年卡</option>
   				    <option value='month'>月卡</option>
   				    <option value='day'>日卡</option>
   				    <#elseif acctType=='month'>
   				    <option value=''>--请选择--</option>
   				    <option value='year'>年卡</option>
   				    <option value='month' selected>月卡</option>
   				    <option value='day'>日卡</option>
   				    <#elseif acctType=='day'>
   				    <option value=''>--请选择--</option>
   				    <option value='year'>年卡</option>
   				    <option value='month'>月卡</option>
   				    <option value='day' selected>日卡</option>
   				    <#else>
   				    <option value=''>--请选择--</option>
   				    <option value='year'>年卡</option>
   				    <option value='month'>月卡</option>
   				    <option value='day'>日卡</option>
   				    </#if>
  				</select>
  				</th>
  				
  				<th style="padding-bottom:5px;" ></th>
  				<th style="padding-bottom:5px;" >
  				<button  class="btn but_s" type='submit' >查 询</button>
  				<!--button  class="btn but_s" type='submit' >批量审核</button-->
  				<!--button  class="btn but_s" type='submit' >停止合作</button-->
  				<input  class="btn but_s" type='button' onClick='test()' value='导 出' />
  				<input  class="btn but_s" type='button' onClick='reset1()' value='重 置' />
  				</th>
  			</tr>
  		</table>
  	</div>
  </form>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr >
	        <th>序号</th>
	        <th>帐号</th>
	        <th>手机</th>
		  	<th>密码</th>
			<th>余额</th>
			<th>创建日期</th>
			<th>余额有效期</th>
			<th>代理商</th>
			<th>帐号类型</th>
			<th>操作</th>
			<th width="4%">详情</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f6f6f6'" onmouseout="this.bgColor='#FFFFFF'"  id="del${item_index}">
	        <td align="center">${item_index}</td>
	        <td align="center">${item.uid}</td>
	        <td align="center">${item.phone}</td>
		  	<td align="center">${item.pwd}</td>
		  	<td align="center">${(item.balance/1000000)?string.currency}</td>
		  	<td align="center">${item.createTime?substring(0,10)!""}</td>
		  	<td align="center">${item.validDate?substring(0,10)!""}</td>
			<td align="center">${item.agent!""}</td>
			
			<#if item.acctType=='year'>
			<td align="center">年卡</td>
			<#elseif item.acctType=='month'>
			<td align="center">月卡</td>
			<#else>
			<td align="center">日卡</td>
			</#if>
			
			<td align="center"><a href='#' onClick="editAcct('${item.brandId}','${item.uid}')" >编辑</a>|<a href='#' onClick="doPage('${item_index}','${item.uid}')" >删除</a></td>
		  	<td align="center"><img src="${rc.contextPath}/images/a.gif" id ='img${item_index}' title="点击展开" onclick='showDetail(${item_index});'></td>
	    </tr>
	    <!--以下为隐藏的层-->
	    <tr style="display:none;" id="div${item_index}">
	    	<td colSpan='10'>
	    	   <div>
	    	   		<table width="100%" border='0' id="popup">
	    	   		    <tr>
	    	   		       <td style="width:100px">是否冻结：</td>
	    	   		       <#if item.enableFlag=='1'>
	    	   		       <td>未冻结</td>
	    	   		       <#else>
	    	   		       <td>已冻结</td>
	    	   		       </#if>
	    	   		       <td style="width:100px">批次：</td>
	    	   		       <td>${item.taskname!""}</td>
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

