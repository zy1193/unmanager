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
 <title>后台综合管理系统-应用列表 </title>
 <script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
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
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>
  <form name="queryform" namespace="/amp" action="${rc.contextPath}/gameapp/listUse.act" method="post">
  <div class="right_box">
  	<div id="titlediv">
  		<table>
  			<tr>
  				<th>合作方</th>
  				<th><select  class="ipb_l" name='gamedpid' id='gamedpid' style="width:200px" /></select></th>
  				<th>应用</th>
  				<th><select  class="ipb_l" name='appid' id='appid'  style="width:150px" /></select></th>
  				<th>&nbsp;创建时间</th>
  				<th><input type="text" name="startTime" id="startTime" class="Wdate" type="text"  onclick="WdatePicker({startDate: '%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${startTime!''}"/></th>
  				<th>&nbsp;到 <input type="text" name="endTime" id="endTime" class="Wdate" type="text"   onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${endTime!''}"/></th>
  				<th>&nbsp;状态</th>
  				<th><select  class="required ipb_l"  name='status' id='status'>
  						<option value='all' <#if status??><#if status=="all"> selected="selected" <#else> </#if> </#if> >全部</option>
  						<option value='0' <#if status??><#if status=="0"> selected="selected" <#else> </#if> </#if> >未审核</option>
  						<option value='1' <#if status??><#if status=="1"> selected="selected" <#else> </#if> </#if> >已审核</option>
  				    </select>
  				</th>
  				<th style="padding-bottom:5px;"><button  class="btn but_s" type='submit' >查 询</button>
  				<button  class="btn1 but_s" type='button' >批量审核</button>
  				<button  class="btn2 but_s" type='button' >停止合作</button>
  				<!--input type='button' onClick='reset1()' value='重 置' /--></th>
  			</tr>
  		</table>
  	</div>
  </form>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr>
	        <th><input type="checkbox" id="ids" name=""/></th>
	        <th>序号</th>
	        <th>应用ID</th>
			<th>应用名</th>
		  	<th>公司名</th>
			<th>玩家数量</th>
			<th>创建时间</th>
			<th>状态</th>
			<th>渠道损耗</th>
			<th>商家分成</th>
	    </tr>    
	    <#if list??>
	    <#list list as game>
	    <tr onmouseover="this.bgColor='#f1f1f1'" onmouseout="this.bgColor='#f9f9f9'" >
	        <td align="center"><input type="checkbox" name="ids" value=${game.appid!""} /></td>
	        <td align="center">${game_index}</td>
		  	<td align="center">${game.appid!""}</td>
		  	<td>${game.appName!""}</td>
		  	<td>${game.gamedpName!""}</td>
			<td>${game.userCount!""}</td>
			<td>${game.createtime?substring(0,19)!""}</td>
			<#if game.status='0'>
			<td style="color:red">未审核</td>
			<#elseif game.status='1'>
			<td style="color:#3A00FF">已审核</td>
			<#else></#if>
			<td><input type='text' class="wastage" style="width:30px;" value="${game.wastage!""}" aid=${game.appid!""} />‰</td>
		  	<td><input type='text' class="incomerate" style="width:30px;" value="${game.incomerate!""}" aid=${game.appid!""} />%</td>
	    </tr>
	    </#list>
	    </#if>
	    </table>
	   <div style="text-align:right "><@nav.pagenav /></div>
	  </div>
	  </div>
 </body>
</html>

