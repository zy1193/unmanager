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
	<title>后台综合管理系统-应用列表 </title>
	<script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/plugins/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/js/selectd.js"></script>
 </head>
 <script type="text/javascript">
 	 
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
 })
 
 </script>
 <body>
  <form name="queryform" action="${rc.contextPath}/gameapp/appdatastat.act" method="POST">
  <div class="right_box">
  	<div id="titlediv">
  		<table>
  			<tr>
  				<th>合作方</th>
  				<th><select  class="required ipb_l" name='gamedpid' id='gamedpid' style="width:200px" /></select></th>
  				<th>应用</th>
  				<th><select  class="required ipb_l" name='appid' id='appid' value='${appid!""}'/></select></th>
  				<th>&nbsp;创建时间</th>
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
	        <th>序号</th>
	        <th>时间</th>
			<th>应用名</th>
		  	<th>新注册</th>
			<th>登录</th>
			<th>7日留存</th>
			<th>15日留存</th>
			<th>付费用户</th>
			<th>新增付费用户</th>
	    </tr>    
	    <#assign rptxml="<chart caption='游戏包数据线性图' yAxisName='单位：次' lineThickness='1' showValues='0' formatNumberScale='0' anchorRadius='5' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40' labelStep='2' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10'>">
	    <#assign categories="<categories>">
	    <#assign newUserdata="<dataset color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1' seriesName='新增用户数'>">
	    <#assign loginUserdata="<dataset seriesName='活跃用户数'>">
	    <#if list??>
	    <#list list as game>
	    <tr onmouseover="this.bgColor='#f1f1f1'" onmouseout="this.bgColor='#f9f9f9'" >
	        <td align="center">${game_index+1}</td>
		  	<td align="center">${game.rptDay!""}</td>
		  	<td align="center">${game.gameName!""}</td>
		  	<td align="center">${game.regCount!""}</td>
			<td align="center">${game.loginCount!""}</td>
			<td align="center">${game.remain7+"%"}</td>
			<td align="center">${game.remain15+"%"}</td>
			<td align="center">${game.payUserCount!""}</td>
		  	<td align="center">${game.newPayUserCount!""}</td>
	    </tr>
	    <#assign categories=categories + "<category label='" + game.rptDay + "'/>">
	    <#assign newUserdata=newUserdata + "<set value='" + game.regCount + "'/>">
	    <#assign loginUserdata=loginUserdata + "<set value='" + game.loginCount + "'/>">
	    </#list>
	    </#if>
	    <#assign categories=categories + "</categories>">
	    <#assign newUserdata=newUserdata + "</dataset>">
	    <#assign loginUserdata=loginUserdata + "</dataset>">
	    <#assign rptxml=rptxml + categories + newUserdata + loginUserdata + "</chart>">
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
   		 $("#ids").click(function(){
   			if($("#ids").attr("checked")==true){
   				$("[name='ids']").attr("checked",'true');//全选
   			}
   			if($("#ids").attr("checked")==false){
   				$("[name='ids']").attr("checked",false);//全不选
   			}
   		 });
   		 
   		 $(".btn1").click(function(){
   		    if($("[name='ids']:checked").length<1){
		    		alert("请选择要修改的行！");
		    		return;
		    	}
   		 	var str="";
		    $("[name='ids']:checked").each(function(){
		     	str+=$(this).val()+',';
		    })
		   
		    url = '${rc.contextPath}/gameapp/examineStatus.act?status=1&ids='+str
		    changeStatus(url);
   		 });
   		 
   		 $(".btn2").click(function(){
   		    if($("[name='ids']:checked").length<1){
		    		alert("请选择要修改的行！");
		    		return;
		    	}
   		 	var str="";
		    $("[name='ids']:checked").each(function(){
		     	str+=$(this).val()+',';
		    })
		    url = '${rc.contextPath}/gameapp/examineStatus.act?status=0&ids='+str
		    changeStatus(url);
   		 });
   		 
   		 $(".wastage").attr("readOnly",true);
   		 $(".wastage").dblclick(function(){
   		 	$(this).removeAttr("readOnly");
   		 });
   		 
   		 $(".wastage").blur(function(){
   		 	if($(this).attr("readOnly")==true){
   		 		return;
   		 	}else{
   		 		$(this).attr("readOnly",true);
   		 		url = '${rc.contextPath}/gameapp/updateWastage.act?wastage='+$(this).val()+'&appid='+$(this).attr('aid')
		        changeStatus(url);
   		 	}
   		 });
   		 
   		 $(".incomerate").attr("readOnly",true);
   		 $(".incomerate").dblclick(function(){
   		 	$(this).removeAttr("readOnly");
   		 });
   		 
   		 $(".incomerate").blur(function(){
   		 	if($(this).attr("readOnly")==true){
   		 		return;
   		 	}else{
   		 		$(this).attr("readOnly",true);
   		 		url = '${rc.contextPath}/gameapp/updateIncomerate.act?incomerate='+$(this).val()+'&appid='+$(this).attr('aid')
		        changeStatus(url);
   		 	}
   		 });
   		 
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
  </script>
</html>