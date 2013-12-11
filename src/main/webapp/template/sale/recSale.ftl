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
   		
 	});
 	
  </script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>
  <form name="queryform" namespace="/amp" action="${rc.contextPath}/sale/recSale.act" method="post">
  <div class="right_box">
  	<div id="titlediv">
  		<table>
  			<tr>
  				<th>合作方</th>
  				<th><select  class="ipb_l" name='gamedpid' id='gamedpid' style="width:200px" /></select></th>
  				<th>应用</th>
  				<th><select  class="ipb_l" name='appid' id='appid'  style="width:150px" /></select></th>
  				
  				<th>年</th>
  				<th><select  class="required ipb_l"  name='year' id='year'>
  						<option value='999' <#if year??><#if year=="999"> selected="selected" <#else> </#if> </#if> >全部</option>
  						<option value='2013' <#if year??><#if year=="2013"> selected="selected" <#else> </#if> </#if> >2013</option>
  						<option value='2014' <#if year??><#if year=="2014"> selected="selected" <#else> </#if> </#if> >2014</option>
  						<option value='2015' <#if year??><#if year=="2015"> selected="selected" <#else> </#if> </#if> >2015</option>
  						<option value='2016' <#if year??><#if year=="2016"> selected="selected" <#else> </#if> </#if> >2016</option>
  						<option value='2017' <#if year??><#if year=="2017"> selected="selected" <#else> </#if> </#if> >2017</option>
  						<option value='2018' <#if year??><#if year=="2018"> selected="selected" <#else> </#if> </#if> >2018</option>
  				    </select>
  				</th>
  				
  				<th>月</th>
  				<th><select  class="required ipb_l"  name='month' id='month'>
  						<option value='999' <#if month??><#if month=="999"> selected="selected" <#else> </#if> </#if> >全部</option>
  						<option value='1' <#if month??><#if month=="1"> selected="selected" <#else> </#if> </#if> >1</option>
  						<option value='2' <#if month??><#if month=="2"> selected="selected" <#else> </#if> </#if> >2</option>
  						<option value='3' <#if month??><#if month=="3"> selected="selected" <#else> </#if> </#if> >3</option>
  						<option value='4' <#if month??><#if month=="4"> selected="selected" <#else> </#if> </#if> >4</option>
  						<option value='5' <#if month??><#if month=="5"> selected="selected" <#else> </#if> </#if> >5</option>
  						<option value='6' <#if month??><#if month=="6"> selected="selected" <#else> </#if> </#if> >6</option>
  						<option value='7' <#if month??><#if month=="7"> selected="selected" <#else> </#if> </#if> >7</option>
  						<option value='8' <#if month??><#if month=="8"> selected="selected" <#else> </#if> </#if> >8</option>
  						<option value='9' <#if month??><#if month=="9"> selected="selected" <#else> </#if> </#if> >9</option>
  						<option value='10' <#if month??><#if month=="10"> selected="selected" <#else> </#if> </#if> >10</option>
  						<option value='11' <#if month??><#if month=="11"> selected="selected" <#else> </#if> </#if> >11</option>
  						<option value='12' <#if month??><#if month=="12"> selected="selected" <#else> </#if> </#if> >12</option>
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
	        <th>商户</th>
			<th>应用</th>
		  	<th>应用ID</th>
			<th>结算年度</th>
			<th>结算月</th>
			<th>实际订单总额</th>
			<th>渠道成本</th>
			<th>税费</th>
			<th>分成基数</th>
			<th>分成比例</th>
			<th>分成收入</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f1f1f1'" onmouseout="this.bgColor='#f9f9f9'" >
	        <td align="center">${item_index}</td>
		  	<td align="center">${item.dpName!""}</td>
		  	<td align="center">${item.appName!""}</td>
		  	<td align="center">${item.appid!""}</td>
			<td align="center">${item.year!""}</td>
			<td align="center">${item.month!""}</td>
			<td align="center">${item.orderMoney!""}</td>
			<td align="center">${item.channelCost}</td>
			<td align="center">${item.taxFee}</td>
			<td align="center">${item.cardinalNumber!""}</td>
			<td align="center">${item.scale!"0"}</td>
			<td align="center">${item.sharedRevenue!"0"}</td>
	    </tr>
	    </#list>
	    </#if>
	    </table>
	   <div style="text-align:right "><@nav.pagenav /></div>
	  </div>
	  </div>
 </body>
</html>

