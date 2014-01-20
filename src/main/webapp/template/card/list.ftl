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
	</style>
  <title> 卡列表 </title>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
  <script type="text/javascript" src="${rc.contextPath}/js/tipswindown.js"></script>
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
		  document.queryform.agent.value="";
		  document.queryform.goodsId.value="";
		  document.queryform.taskid.value="";
		  document.queryform.startTime.value="";
		  document.queryform.endTime.value="";
		  
		  document.queryform.enableFlag.value="";
		  document.queryform.cardno.value="";
		  document.queryform.status.value="";
		  document.queryform.isvalid.value="";
	  	}
	  	
	  	function test(){
		  	if(!confirm('导出数据，根据数据量的大小需要的时间不同，请耐心等待，切勿重复操作！')){
		  	 	return;
		  	 }
		  	 var agent=$('#agent').val();
		  	 var goodsId=$('#goodsId').val();
		  	 var taskid=$('#taskid').val();
		  	 var startTime=$('#startTime').val();
		  	 var endTime=$('#endTime').val();
		  	 
		  	 var enableFlag=$('#enableFlag').val();
		  	 var cardno=$('#cardno').val();
		  	 var status=$('#status').val();
		  	 var isvalid=$('#isvalid').val();
		  	 if(agent=='' && goodsId=='' && taskid=='' && startTime=='' && endTime==''&& enableFlag=='' && cardno=='' && status=='' && isvalid==''){
		  	 	if(!confirm('确实要导出所有充值卡吗？最好选择条件导出，重新选择请点“取消”！')){
		  	 		return;
		  	 	}
		  	 }
		  	 
			 window.location.href='${rc.contextPath}/card/exportExcel.act?goodsId='+goodsId+'&agent='+agent+'&taskid='+taskid+'&startTime='+startTime+'&endTime='+endTime+'&enableFlag='+enableFlag+'&cardno='+cardno+'&status='+status+'&isvalid='+isvalid;
		 }
		  	
		  	
		 $("document").ready(function(){
		 	 $("#isread-text").live("click",function(){ 
	           popOrderDetail($(this).attr("value"));
	         }) 
		 });
		 
		 /*
		 *弹出订单详情窗口 orderId表示订单主键ID
		 */
		function popOrderDetail(cardno){
			getOrderDetail(cardno); // 获取订单详情
			// 显示弹出框
			popTips();
		}
		
		//弹出层调用
		function popTips(){
			showTipsWindown("基本详情信息", 'simTestContent', 700, 270);
		}
		
		/*
		*弹出本页指定ID的内容于窗口
		*id 指定的元素的id
		*title:	window弹出窗的标题
		*width:	窗口的宽,height:窗口的高
		*/
		function showTipsWindown(title,id,width,height){
			tipsWindown(title,"id:"+id,width,height,"true","","true",id);
		}
		
		// 获取订单详情信息
	function getOrderDetail(cardno){
     	var url = "${rc.contextPath}/order/orderList4Card.act";
     	
		$.ajax({
		url : url,
		dataType : "html",
		type : "POST",
		async : false,
		data : {
			cno : $.trim(cardno)
		},
		success : function(data) {
			    if (data != '') {
	                $(".mainlist").html(data);		
				} else {
				
				}
			}
		});
	 }
	 
	function doPage(id,cardno){
  		if(!confirm('确定删除吗？删除后不可恢复！')){
	  	 	return;
	  	}
	  	 
     	var url = "${rc.contextPath}/card/deleteCard.act";
		$.ajax({
			url : url,
			dataType : "html",
			type : "POST",
			async : false,
			data : {
				cardno : $.trim(cardno)
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
</script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
 <body>
  <form name="queryform" namespace="/card" action="${rc.contextPath}/card/cardList.act" method="post">
  <div class="right_box">
  	<div id="titlediv">
  		<table border='0'>
  			<tr>
  				<th align="right">代理商：</th>
  				<th align="left"><select name="agent" id="agent" width="200px" class="required ipb_l" >
	  				    <option value=''>--请选择--</option>
					  <#if agentlist??>
	   				  <#list agentlist as item>
	   				    <option value='${item.alias!""}'>${item.name!""}</option>
	   				  </#list>
	   				  </#if>
	  				</select>
	  			</th>
  				<th align="right">商品：</th>
	  			<th align="left"><select name="goodsId" id="goodsId" width="200px" class="required ipb_l" >
	  				    <option value=''>--请选择--</option>
					  <#if goodslist??>
	   				  <#list goodslist as item>
	   				    <option value='${item.id!""}'>${item.name!""}</option>
	   				  </#list>
	   				  </#if>
	  				</select>
	  			</th>
  				<th align="right">批次：</th>
  				<th align="left"><input  class="required ipb_s" type='text' name='taskid' id='taskid' value='${taskid!""}' /></th>
  				<th align="right">冻结：</th>
  				<th align="left"><select name="enableFlag" id="enableFlag" width="200px" class="required ipb_l" >
	  				    <#if enableFlag=='y'>
	  				    <option value=''>--请选择--</option>
	   				    <option value='y' selected>未冻结</option>
	   				    <option value='n'>已冻结</option>
	   				    <#elseif enableFlag=='n'>
	   				    <option value=''>--请选择--</option>
	   				    <option value='y'>未冻结</option>
	   				    <option value='n' selected>已冻结</option>
	   				    <#else>
	   				    <option value='' selected>--请选择--</option>
	   				    <option value='y'>未冻结</option>
	   				    <option value='n'>已冻结</option>
	   				    </#if>
	  				</select>
	  			</th>
	  			<th align="right">状态：</th>
  				<th align="left"><select name="status" id="status" width="200px" class="required ipb_l" >
	  				    <#if status=='y'>
	  				    <option value=''>--请选择--</option>
	   				    <option value='y' selected>未使用</option>
	   				    <option value='n'>已使用</option>
	   				    <#elseif status=='n'>
	   				    <option value=''>--请选择--</option>
	   				    <option value='y'>未使用</option>
	   				    <option value='n' selected>已使用</option>
	   				    <#else>
	   				    <option value='' selected>--请选择--</option>
	   				    <option value='y'>未使用</option>
	   				    <option value='n'>已使用</option>
	   				    </#if>
	  				</select>
	  			</th>
	  			<th align="right">失效：</th>
  				<th align="left"><select name="isvalid" id="isvalid" width="200px" class="required ipb_l" >
	  				    <#if isvalid=='y'>
	  				    <option value=''>--请选择--</option>
	   				    <option value='y' selected>未失效</option>
	   				    <option value='n'>已失效</option>
	   				    <#elseif isvalid=='n'>
	   				    <option value=''>--请选择--</option>
	   				    <option value='y'>未失效</option>
	   				    <option value='n' selected>已失效</option>
	   				    <#else>
	   				    <option value='' selected>--请选择--</option>
	   				    <option value='y'>未失效</option>
	   				    <option value='n'>已失效</option>
	   				    </#if>
	  				</select>
	  			</th>
  				</tr>
  				<tr>
  				<th align="right">创建时间：</th>
  				<th align="left" colspan="1"><input type="text" name="startTime" id="startTime" class="Wdate" type="text"  onclick="WdatePicker({startDate: '%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${startTime!''}"/></th>
  				<th align="right">到：</th>
  				<th align="left" colspan="1"> <input type="text" name="endTime" id="endTime" class="Wdate" type="text"   onclick="WdatePicker({startDate: '%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${endTime!''}"/></th>
  				
  				<th align="right">卡号：</th>
  				<th align="left" colspan="1"><input class="required ipb_s" type='text' name='cardno' id='cardno' value='${cardno!""}' /></th>
  				<th style="padding-bottom:5px;"  colspan="3"><button  class="btn but_s" type='submit' >查 询</button>
  				<!--button  class="btn but_s" type='submit' >批量审核</button-->
  				<!--button  class="btn but_s" type='submit' >停止合作</button-->
  				<input  class="btn but_s" type='button' onClick='test()' value='导 出' />
  				<input  class="btn but_s" type='button' onClick='reset1()' value='重 置' /></th>
  			</tr>
  		</table>
  	</div>
  </form>

	<div id="div">
	  <table id="listTable" border='0'  width="100%">
	    <tr >
	        <th>序号</th>
	        <th>卡号</th>
		  	<th>密码</th>
			<th>商品</th>
			<th>代理商</th>
			<th>创建时间</th>
			<th>到期时间</th>
			<th>使用时间</th>
			<th>状态</th>
			<th>操作</th>
			<th width="4%">详情</th>
	    </tr>    
	    <#if list??>
	    <#list list as item>
	    <tr onmouseover="this.bgColor='#f6f6f6'" onmouseout="this.bgColor='#FFFFFF'" id="del${item_index}">
	        <td align="center">${item_index}</td>
	        <td align="center">${item.cardno}</td>
		  	<td align="center">${item.cardpwd}</td>
		  	<td align="center">${(item.goodsName)}</td>
		  	<td align="center">${item.agent}</td>
		  	<td align="center">${item.ctime?substring(0,10)!""}</td>
		  	<td align="center">${item.endTime?substring(0,10)!""}</td>
		  	<#if item.mtime==''>
	        <td align="center">未使用</td>
	        <#else>
	        <td align="center">${item.mtime?substring(0,19)!""}</td>
	        </#if>
		  	<#if item.status=='y'>
			<td align="center">未使用</td>
			<td align="center"><a href='#' onClick="doPage('${item_index}','${item.cardno}')" >删除</a></td>
			<#else>
			<td align="center"><a href='#' id="isread-text" class="co" value="${item.cardno!""}">已使用</a></td>
			<td align="center"></td>
			</#if>
			
		  	<td align="center"><img src="${rc.contextPath}/images/a.gif" id ='img${item_index}' title="点击展开" onclick='showDetail(${item_index});'></td>
	    </tr>
	    <!--以下为隐藏的层-->
	    <tr style="display:none;" id="div${item_index}">
	    	<td colSpan='10'>
	    	   <div>
	    	   		<table width="100%" border='0' id="popup">
	    	   		    <tr>
	    	   		       <td style="width:100px">品牌：</td>
	    	   		       <td style="width:200px">亿信</td>
	    	   		       <td style="width:100px">是否冻结：</td>
	    	   		       <#if item.enableFlag=='y'>
	    	   		       <td style="width:200px">可用</td>
	    	   		       <#else>
	    	   		       <td style="width:200px">已冻结</td>
	    	   		       </#if>
	    	   		       <td style="width:100px">批次：</td>
	    	   		       <td style="width:200px">${item.taskid!""}</td>
	    	   		    </tr>
	    	   		</table>
	    	   </div>
	    	</td>
	    </tr>
	    </#list>
	    </#if>
	    </table>
	   <div style="text-align:right "><@nav.pagenav /></div>
	  </div>
	  </div>
	  
	  <div style="display:none;">
		<div id="simTestContent" class="simScrollCont">
			<div class="mainlist"></div>
	    </div>
	  </div>
 </body>
</html>

