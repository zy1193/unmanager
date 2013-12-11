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
		
		#chartDIV {
			width: 100%;	
			padding-top: 15px;			
		}
		
		#titlediv {
			width: 100%;			
			padding-top: 15px;
		}
	</style>
	<title>后台综合管理系统-销售总览 </title>
	<script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/plugins/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 	<script type="text/javascript" src="${rc.contextPath}/js/selectd.js"></script>
 </head>
 <body>
  <div class="right_box">
  <form name="queryform" action="${rc.contextPath}/sellrpt/paysum.act" method="post" onsubmit="return checkForm();">
  	<div id="titlediv">
  		<table>
  			<tr>
  				<th>合作方</th>
  				<th><select class="required ipb_l" name='dpid' id='dpid' style="width:200px" /></select></th>
  				<th>应用</th>
  				<th><select class="required ipb_l" name='appid' id='appid'  style="width:150px" /></select></th>
  				<th>&nbsp;查询范围</th>
  				<th><input type="text" name="startTime" id="startTime" class="Wdate" type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  value="${startTime!''}"/></th>
  				<th>&nbsp;到 <input type="text" name="endTime" id="endTime" class="Wdate" type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  value="${endTime!''}"/></th>
  				<th style="padding-bottom:5px;"><button  class="btn but_s" type='submit' >查 询</button></th>
  				<th style="color:red;">${msg!""}</th>
  			</tr>
  		</table>
  	</div>
  </form>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr>
	        <th>时间段</th>
	        <th>销售总额</th>
	        <th>订单数</th>
	    </tr>    
	    <#assign rptxml="<chart caption='销售总览图' yAxisName='单位：次' lineThickness='1' showValues='0' formatNumberScale='0' anchorRadius='5' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40' labelStep='2' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10'>">
	    <#assign categories="<categories>">
	    <#assign paySumData="<dataset color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1' seriesName='销售总额'>">
	    <#assign orderCountData="<dataset seriesName='订单数'>">
	    <#if payRpts??>
	    <#list payRpts as game>
	    <tr onmouseover="this.bgColor='#f1f1f1'" onmouseout="this.bgColor='#f9f9f9'" >
		  	<td align="center">${game.rptDay!""}</td>
		  	<td align="center">${game.payMoneySum!""}</td>
		  	<td align="center">${game.orderCount!""}</td>
	    </tr>
	    <#assign categories=categories + "<category label='" + game.rptDay + "'/>">
	    <#assign paySumData=paySumData + "<set value='" + game.payMoneySum + "'/>">
	    <#assign orderCountData=orderCountData + "<set value='" + game.orderCount + "'/>">
	    </#list>
	    </#if>
	    <#assign categories=categories + "</categories>">
	    <#assign paySumData=paySumData + "</dataset>">
	    <#assign orderCountData=orderCountData + "</dataset>">
	    <#assign rptxml=rptxml + categories + paySumData + orderCountData + "</chart>">
	    </table>
	   <div style="text-align:right "><@nav.pagenav /></div>
	</div>
	  <div id="chartDIV"></div>
  </div>
 </body>
 <script type="text/javascript">
 	var rptxml="${rptxml}";
 	// 初始化报表对象
    var myChart = new FusionCharts("${rc.contextPath}/plugins/FusionCharts/MSLine.swf", "myChartId", "100%", 300);
 	$("document").ready(function(){
   		 select.init([
   		 	<#if dpid??>
   			{
   			 id:"dpid",
   			 url:"${rc.contextPath}/gameapp/getdp.act", 
   			 nullable:false,
   			 defaultValue:${dpid}
   			},
   			<#else>
   			{
   			 id:"dpid",
   			 url:"${rc.contextPath}/gameapp/getdp.act", 
   			 nullable:true,
   			 defaultValue:0
   			},
   			</#if>
   			<#if appid??>
   			{
   			 id:"appid", 
             url:"${rc.contextPath}/gameapp/getgame.act", 
             nullable:false ,
             defaultValue:${appid}
            }
            <#else>
            {
   			 id:"appid", 
             url:"${rc.contextPath}/gameapp/getgame.act", 
             nullable:true 
            }
            </#if>
   		]);
   		 
   		 // 加载图形
		 if(rptxml && rptxml != '')
    	 {
			myChart.setDataXML(rptxml);
    	 }
		 myChart.render("chartDIV");
 	});
 
	function changeStatus(url){
		$.ajax({
	    	url: url,
	    	type: 'GET',
	    	dataType: 'html',
	    	timeout: 1000,
		    error: function(){
		        alert('更改失败！');
		    },
		    success: function(xml){
		       alert('更改成功！');
		       history.go(0);
		    }
	    });
	}
	
	function checkForm()
	{
		var dpid = $("#dpid").val();
		if(typeof dpid == 'undefined' || dpid == '')
		{
			alert('请选择联运游戏商');
			return false;
		}
		return true;
	}
  </script>
</html>