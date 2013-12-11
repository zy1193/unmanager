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
 })
 	
 	function update(goodsid){
 	
 		location.href = '${rc.contextPath}/gameapp/toEditPage.act?goodsid='+goodsid;
 	}
 	
 	function del(goodsid){
 	
	 	var returnVal = window.confirm("确定删除该商品？");
		if(returnVal) {
		    location.href = '${rc.contextPath}/gameapp/delete.act?goodsId='+goodsid;
 			history.go(0);
		}

 		history.go(0);
 	}
 	
 	function changeExamineStatus(status,id,divid){
 	
 		var url = '${rc.contextPath}/gameapp/updateExamineStatus.act?examineStatus='+status+'&goodsId='+id;
 	    changeStatus(status,url,divid);
 	}
 	
 	function changeGoodsStatus(status,id,divid){
 	
 		var url = '${rc.contextPath}/gameapp/updateGoodsStatus.act?goodsStatus='+status+'&goodsId='+id;
 	    changeStatus(status,url,divid);
 	}
 	
 	function changeStatus(status,url,divid){
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
			        var a = $(".a_" + divid);
			        if('0'==status){
			          a.eq(1).hide(500);
			          a.eq(0).show(200);
			        }else if('1'==status){
			          a.eq(1).show(200);
			          a.eq(0).hide(500);
			        }
			    }
		});
	}
  </script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>
  <form name="queryform" namespace="/amp" action="${rc.contextPath}/gameapp/shopUse.act" method="post">
  <div class="right_box">
  	<div id="titlediv">
  		<table>
  			<tr>
  				<th>合作方</th>
  				<th><select  class="required ipb_l" name='gamedpid' id='gamedpid' style="width:200px" /></select>
  				</th>
  				<th>应用</th>
  				<th><select  class="required ipb_l" name='appid' id='appid'  style="width:150px" /></select>
  				<th>&nbsp;商品分类</th>
  				<th><select  class="required ipb_l"  name='goodsType' id='goodsType'>
  						<option value='999' <#if goodsType??><#if goodsType=="999"> selected="selected" <#else> </#if> </#if> >全部</option>
  						<option value='货币' <#if goodsType??><#if goodsType=="货币"> selected="selected" <#else> </#if> </#if> >货币</option>
  						<option value='道具' <#if goodsType??><#if goodsType=="道具"> selected="selected" <#else> </#if> </#if> >道具</option>
  						<option value='宠物' <#if goodsType??><#if goodsType=="宠物"> selected="selected" <#else> </#if> </#if> >宠物</option>
  				    </select>
  				</th>
  				<th>&nbsp;商品状态</th>
  				<th><select  class="required ipb_l"  name='goodsStatus' id='goodsStatus'>
  						<option value='999' <#if goodsStatus??><#if goodsStatus=="999"> selected="selected" <#else> </#if> </#if> >全部</option>
  						<option value='0' <#if goodsStatus??><#if goodsStatus=="0"> selected="selected" <#else> </#if> </#if> >未上架</option>
  						<option value='1' <#if goodsStatus??><#if goodsStatus=="1"> selected="selected" <#else> </#if> </#if> >已上架</option>
  				    </select>
  				</th>
  				<th style="padding-bottom:5px;">
  					<button  class="btn but_s" type='submit' >查 询</button>
  					<button  class="btn1 but_s" type='button' >增加商品</button>
  				</th>
  			</tr>
  		</table>
  	</div>
  </form>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr>
	        <th>序号</th>
	        <th>商品ID</th>
			<th>应用</th>
		  	<th>商品</th>
			<th>价格</th>
			<th>类型</th>
			<th>类别</th>
			<th>审核</th>
			<th>状态</th>
			<th>操作</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f1f1f1'" onmouseout="this.bgColor='#f9f9f9'" >
	        <td align="center">${item_index}</td>
		  	<td align="center">${item.goodsId!""}</td>
		  	<td align="center">${item.appName!""}</td>
		  	<td align="center">${item.goodsName!""}</td>
			<td align="center">${item.goodsPrice!""}</td>
			<td align="center">${item.goodsType!""}</td>
			<td align="center">${item.goodsCategory!""}</td>
			<td align="center">
		  		<a class="a_${item_index}_1" style="display:<#if item.examineStatus!='0'>none</#if>;color:red" href='#' onClick="changeExamineStatus('1','${item.goodsId}','${item_index}_1')" >未审核</a>
		  		<a class="a_${item_index}_1" style="display:<#if item.examineStatus!='1'>none</#if>" href='#' onClick="changeExamineStatus('0','${item.goodsId}','${item_index}_1')" >已审核</a>
			</td>
			<td align="center">
		  		<a class="a_${item_index}_2" style="display:<#if item.goodsStatus!='0'>none</#if>;color:red" href='#' onClick="changeGoodsStatus('1','${item.goodsId}','${item_index}_2')" >未上架</a>
		  		<a class="a_${item_index}_2" style="display:<#if item.goodsStatus!='1'>none</#if>" href='#' onClick="changeGoodsStatus('0','${item.goodsId}','${item_index}_2')" >已上架</a>
			</td>
			
			<td align="center">
		  		<a href='#' onClick="update('${item.goodsId}')" >修改</a>
		  		<a href='#' onClick="del('${item.goodsId}')" >删除</a>
			</td>
	    </tr>
	    </#list>
	    </#if>
	    </table>
	   <div style="text-align:right "><@nav.pagenav /></div>
	  </div>
	  </div>
 </body>
</html>

