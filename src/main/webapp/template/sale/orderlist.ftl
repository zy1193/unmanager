<#import "/include/navbar.ftl" as nav>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache"/> 
	<meta http-equiv="Cache-Control" content="no-store, must-revalidate"/> 
	<meta http-equiv="expires" content="0"/>
    <link href="${rc.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${rc.contextPath}/css/pop.css" rel="stylesheet" type="text/css" media="all" />
    
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
		.co{ color:green;}	
	</style>
 <title>后台综合管理系统-应用列表 </title>
 <script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
 <script type="text/javascript" src="${rc.contextPath}/js/selectd.js"></script>
  <script type="text/javascript" src="${rc.contextPath}/js/tipswindown.js"></script>
 
 </head>
<script type="text/javascript">

/*
*弹出本页指定ID的内容于窗口
*id 指定的元素的id
*title:	window弹出窗的标题
*width:	窗口的宽,height:窗口的高
*/
function showTipsWindown(title,id,width,height){
	tipsWindown(title,"id:"+id,width,height,"true","","true",id);
}

function confirmTerm(s) {
	parent.closeWindown();
	if(s == 1) {
		parent.document.getElementById("isread").checked = true;
	}
}

	//弹出层调用
	function popTips(){
		showTipsWindown("基本详情信息", 'simTestContent', 600, 370);
		$("#isread").attr("checked", false);
	}
	
	
	
	// 获取订单详情信息
	function getOrderDetail(orderid){
     	var url = "../sale/orderInfoDetail.act";
     	
		$.ajax({
		url : url,
		dataType : "html",
		type : "POST",
		async : false,
		data : {
			id : $.trim(orderid)
		},
		success : function(data) {
			if (data != '') { // get orderDetail success
			//console.info(data);
            $(".mainlist").html(data);		
 
			} else { // get orderDetail fail
			
			}
		
		
		}
	});
	
	}
	
	/*
	*弹出订单详情窗口 orderId表示订单主键ID
	*/
	function popOrderDetail(orderId){

	getOrderDetail(orderId); // 获取订单详情
	// 显示弹出框
	popTips();
	}
 
	$("document").ready(function(){
   		select.init([
   		    <#if gamedpid??>
   			{
   			 id:"gamedpid",
   			 url:"${rc.contextPath}/gameapp/getdp.act", 
   			 nullable:true,
   			 defaultValue:${gamedpid}
   			},
   			<#else>
   			{
   			 id:"gamedpid",
   			 url:"${rc.contextPath}/gameapp/getdp.act", 
   			 nullable:true,
   			 defaultValue:0
   			},
   			</#if>
   			<#if appid??>
   			{
   			 id:"appid", 
             url:"${rc.contextPath}/gameapp/getgame.act", 
             nullable:true,
             defaultValue:${appid}
            }
            <#else>
            {
   			 id:"appid", 
             url:"${rc.contextPath}/gameapp/getgame.act", 
             nullable:true,
             defaultValue:0
            }
            </#if>
           
   		]);
   		
   		 $(".btn1").click(function(){
		    location.href = '${rc.contextPath}/gameapp/toAddPage.act'
   		 });
   		 // 绑定流水号弹出框绑定事件
   		// $('label[id^=isread-]').click(popOrderDetail);
   		 
   		 
   		 $("#isread-text").live("click",function(){ 
          popOrderDetail($(this).attr("value"));
          }) 
   		 
   		 
 	});
 	
  </script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>
  <form name="queryform" namespace="/amp" action="${rc.contextPath}/sale/orderInfoList.act" method="post">
  <div class="right_box">
  	<div id="titlediv">
  		<table>
  			<tr>
  				<th>合作方</th>
  				<th><select  class="ipb_l" name='gamedpid' id='gamedpid' style="width:200px" /></select></th>
  				<th>应用</th>
  				<th><select  class="ipb_l" name='appid' id='appid'  style="width:150px" /></select></th>
  				<th>用户帐号</th>
  				<th><input  class="ipb_s" type='text' name='openid' id='openid' value='${openid!""}' /></th>
  				<th>帐号</th>
  				<th><input class="ipb_s" type='text' name='uid' id='uid' value='${uid!""}' /></th>
  				<th>商户订单号</th>
  				<th><input class="ipb_s" type='text' name='gameorderid' id='gameorderid' value='${gameorderid!""}' /></th>
  			</tr>
  			<tr>
  				<th>时间</th>
  				<th><input type="text" name="startTime" id="startTime" class="Wdate" type="text"  onclick="WdatePicker({startDate: '%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${startTime!''}"/></th>
  				<th>到</th>
  				<th><input type="text" name="endTime" id="endTime" class="Wdate" type="text"   onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${endTime!''}"/></th>
  				<!--th>支付状态</th-->
  				<!--th><select  class="required ipb_l"  name='tradetype' id='tradetype'>
  						<option value='999' <#if tradetype??><#if tradetype=="999"> selected="selected" <#else> </#if> </#if> >全部</option>
  						<option value='1' <#if tradetype??><#if tradetype=="1"> selected="selected" <#else> </#if> </#if> >已支付</option>
  						<option value='0' <#if tradetype??><#if tradetype=="0"> selected="selected" <#elseif tradetype=="2"> selected="selected"  </#if> </#if> >未支付</option>
  				    </select>
  				</th-->
  				<th>通知状态</th>
  				<th><select  class="required ipb_l"  name='gamenotifyresult' id='gamenotifyresult'>
  						<option value='999' <#if gamenotifyresult??><#if gamenotifyresult=="999"> selected="selected" <#else> </#if> </#if> >全部</option>
  						<option value='success' <#if gamenotifyresult??><#if gamenotifyresult=="success"> selected="selected" <#else> </#if> </#if> >success</option>
  						<option value='fail' <#if gamenotifyresult??><#if gamenotifyresult=="fail"> selected="selected" <#else> </#if> </#if> >fail</option>
  						<option value='0' <#if gamenotifyresult??><#if gamenotifyresult=="0"> selected="selected" <#else> </#if> </#if> >wait</option>
  				    </select>
  				</th>
  				
  				<th>支付状态</th>
  				<th><select  class="required ipb_l"  name='payresult' id='payresult'>
  						<option value='999' <#if payresult??><#if payresult=="999"> selected="selected" <#else> </#if> </#if> >全部</option>
  						<option value='success' <#if payresult??><#if payresult=="success"> selected="selected" <#else> </#if> </#if> >success</option>
  						<option value='fail' <#if payresult??><#if payresult=="fail"> selected="selected" <#else> </#if> </#if> >fail</option>
  						<option value='0' <#if payresult??><#if payresult=="0"> selected="selected" <#else> </#if> </#if> >wait</option>
  				    </select>
  				</th>
  				
  				<th style="padding-bottom:5px;">
  					<button  class="btn but_s" type='submit' >查 询</button>
  				</th>
  				
  			</tr>
  		</table>
  	</div>
  </form>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr>
	        <th>序号</th>
	        <th>应用</th>
			<th>支付方式</th>
			<th>有信账户</th>
		  	<th>商户订单号</th>
			<th>下单时间</th>
			<th>订单金额</th>
			<th>支付金额</th>
			<!--th>通知金额</th-->
			<th>流水号</th>
			<th>通知状态</th>
			<th>通知次数</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f1f1f1'" onmouseout="this.bgColor='#f9f9f9'" >
	        <td align="center">${item_index}</td>
		  	<td align="center">${item.appName!""}</td>
		  	<td align="center">${item.paytype!""}</td>
		  	<td align="center">${item.uid!""}</td>
		  	<td align="center">${item.gameorderid!""}</td>
			<td align="center">${item.gameordertime?substring(0,19)}</td>
			<td align="center">${(item.gordermoney?eval/100)?string.currency}</td>
			<td align="center">${(item.paymoney?eval/100)?string.currency}</td>
			<!--td align="center"></td-->
			<td align="center"><label id="isread-text"  class="co" value="${item.id!""}">${item.orderid!"0"}</lable></td>
			
			<#if item.gamenotifyresult="success">
			<td align="center" style="color:#3A00FF">success</td>
			<#elseif  item.gamenotifyresult="fail">
			<td align="center">fail</td>
			<#elseif  item.gamenotifyresult="0">
			<td align="center">wait</td>
			<#else>
			<td align="center">wait</td>
			</#if>
			<td align="center">${item.gamenotifycount!"0"}</td>
	    </tr>
	    </#list>
	    </#if>
	    </table>
	   <div style="text-align:right "><@nav.pagenav /></div>
	  </div>
	  </div>
	  
	  <div style="display:none;">

	<div id="simTestContent" class="simScrollCont">
	
		<div class="mainlist">

		</div>
		
	</div><!--simTestContent end-->
	
</div>
 </body>
</html>

