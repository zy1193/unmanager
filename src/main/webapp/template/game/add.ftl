<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${rc.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
<style>
	li.label {width:120px}
	li.sl {width:80%}
</style>
<title>后台综合管理系统-添加合作方</title>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/messages_cn.js"></script>
 
<script>

var select = new LinkedSelect(); 
 	function LinkedSelect(){  
		return this;  
	}  
		
	LinkedSelect.prototype.init = function(allSelectInputs){
		if(typeof allSelectInputs == 'undefined' || allSelectInputs.length == 0)
		{
			return;
		}
		initNextSelect = function(allSelectInputs, parentId, currentIndex){
			if(!allSelectInputs || allSelectInputs.length <= currentIndex){
				return;
			}
			var currentSelect = allSelectInputs[currentIndex];
			$("#"+currentSelect.id).empty();
			if(allSelectInputs.length > currentIndex + 1){
				$("#"+currentSelect.id).unbind("change");
				$("#"+currentSelect.id).bind("change", function(){
					initNextSelect(allSelectInputs, $(this).val(), currentIndex + 1);
				});
			}
			if(currentIndex != 0){
				if(!parentId || parentId == ''){
					$("#"+currentSelect.id).append("<option value=''></option>");
					initNextSelect(allSelectInputs, $("#"+currentSelect.id).val(), currentIndex + 1);
					return;
				}
			}
			// 如果不为空，则根据parentId取出所有的SelectItem初始化currentSelect
			$.post(currentSelect.url,
				{
				   	dpid:parentId
				},
				function(data){
					// 如果currentSelect在被定义时nullable为true，则说明可以为空，在第一个加上一个空的option
					if(currentSelect.nullable){
					    <#if goods.dpid??>$("#"+currentSelect.id).append("<option value='${goods.dpid}' selected='selected' >${goods.dpName}</option>");<#else> 
						$("#"+currentSelect.id).append("<option value=''></option>");
						</#if>
					}
					// 取出所有的selectItem加到currentSelect上
					$.each(data, function (index, selectItem) {
						// 如果currentSelect在被定义时的defaultValue等于当前selectItem的值，则选中它
						if(selectItem.value == currentSelect.defaultValue){
							$("#"+currentSelect.id).append("<option selected='selected' value='" + selectItem.id + "'>" + selectItem.name + "</option>");
						}
						else {
							$("#"+currentSelect.id).append("<option value='" + selectItem.id + "'>" + selectItem.name + "</option>");
						}
					});
					// 初始化完毕后，取出当前currentSelect选中的值，作为parentId初始化下一个select
					initNextSelect(allSelectInputs, $("#"+currentSelect.id).val(), currentIndex + 1);
				},"json"
			);
		};
	
		// 调用initNextSelect，启动第一个下拉框的加载
		initNextSelect(allSelectInputs, "", 0);
		
	};



	$(function() {
		$(":text:first").focus();
		$("form:first").validate({
			submitHandler : function(form) {
				var options = {
					success : function(data) {
						$("#res").html(data).show();
						//$(":text").attr("value","");
						//$(":text:first").focus();
					}
				};
				$(form).ajaxForm();
				$(form).ajaxSubmit(
					options
				);
			}
		});
		
		select.init([
   			{
   			 id:"dpid",
   			 url:"${rc.contextPath}/gameapp/getdp.act", 
   			 nullable:true,
   			 defaultValue:2 
   			},
   			{
   			 id:"appid", 
             url:"${rc.contextPath}/gameapp/getgame.act", 
             nullable:false 
            }
   		]);
   		
	});
	
	
</script>
</head>
<body>
<#include "/include/header.ftl">
<h2 style="margin:20px 0 0 30px;">添加合作方</h2>
<form action="${rc.contextPath}/gameapp/add.act" name="inputform" method="post">
<input type="hidden" name="flag" value='${flag!"add"}'/>
<input type="hidden" name="goodsId" value='${goods.goodsId}'/>
<ul class="fix">
<li class="nl label">开发商</li>
<li class="sl"><select  class="required ipb_l" name='dpid' id='dpid' style="width:200px" />
				<#if goods.dpid??><option value='${goods.dpid}' selected="selected" />${goods.dpName} </option><#else> </#if>
               </select></li>
<li class="nl label">应用</li>
<li class="sl"><select  class="required ipb_l" name='appid' id='appid'  style="width:150px" /></select></li>

<li class="nl label">商品名称</li>
<li class="sl"><input type="text" name="goodsName" class="required ipb_m" style="width:500px" value="${goods.goodsName!""}" /></li>
<li class="nl label">商品类型</li>
<li class="sl">
	<select  class="required ipb_l"  name='goodsType' id='goodsType'>
		<option value='货币' <#if goods.goodsType??><#if goods.goodsType=="货币"> selected="selected" <#else> </#if> </#if> >货币</option>
		<option value='道具' <#if goods.goodsType??><#if goods.goodsType=="道具"> selected="selected" <#else> </#if> </#if> >道具</option>
		<option value='宠物' <#if goods.goodsType??><#if goods.goodsType=="宠物"> selected="selected" <#else> </#if> </#if> >宠物</option>
    </select>
</li>
<li class="nl label">商品类别</li>
<li class="sl"><input type="text" name="goodsCategory" class="required ipb_m" style="width:500px" value="${goods.goodsCategory!""}" /></li>
<li class="nl label">货币类型</li>
<li class="sl"><input type="text" name="currencyType" class="required ipb_m" style="width:500px" value="${goods.currencyType!""}" /></li>
<li class="nl label">商品价格</li>
<li class="sl"><input type="text" name="goodsPrice" class="required digits ipb_m" style="width:500px" value="${goods.goodsPrice!""}" /></li>
<li class="nl label">原价</li>
<li class="sl"><input type="text" name="costPrice" class="required digits ipb_m" style="width:500px" value="${goods.costPrice!""}" /></li>
<li class="nl label">销售价格</li>
<li class="sl"><input type="text" name="salePrice" class="required digits ipb_m" style="width:500px" value="${goods.salePrice!""}" /></li>
<li class="nl label">计数单位</li>
<li class="sl"><input type="text" name="tally" class="required ipb_m" style="width:500px" value="${goods.tally!""}" /></li>
<li class="nl label">兑换比例</li>
<li class="sl"><input type="text" name="exchangeProportion" class="required digits ipb_m" style="width:500px" value="${goods.exchangeProportion!""}" /></li>
<li class="nl label">兑换单位</li>
<li class="sl"><input type="text" name="exchangeUnit" class="required ipb_m" style="width:500px" value="${goods.exchangeUnit!""}" /></li>
<li class="nl label">审核 </li>
<li class="sl">
	<input type="radio" name="examineStatus" value="1" id="radio_2" checked="checked" <#if goods.examineStatus??><#if goods.examineStatus=="1">  checked="checked" <#else> </#if> </#if>/> <label for="radio_1">已审核</label>
	<input type="radio" name="examineStatus" value="0" id="radio_1" <#if goods.examineStatus??><#if goods.examineStatus=="0">  checked="checked" <#else> </#if> </#if> /> <label for="radio_2">未审核</label>
</li>
<li class="nl label">商品状态 </li>
<li class="sl">
	<input type="radio" name="goodsStatus" value="1" id="radio_2" checked="checked" <#if goods.goodsStatus??><#if goods.goodsStatus=="1">  checked="checked" <#else> </#if> </#if> /> <label for="radio_1">已上架</label>
	<input type="radio" name="goodsStatus" value="0" id="radio_1" <#if goods.goodsStatus??><#if goods.goodsStatus=="0">  checked="checked" <#else> </#if> </#if> /> <label for="radio_2">未上架</label>
</li>
<li class="nl fix"><button type="submit" class="but_l mima">提交</button></li>
</ul>
</form>
<div style="margin:20px 0 0 30px;color:blue;display:none;" id="res" ></div>

<!--div style="margin:20px 0 0 30px;color:blue;">
角色说明<br/>
管理员：拥有所有权限<br/>
监察员：不可授信<br/>
拓展员：不可授信、审核、冻结、解冻
</div-->

<#include "/include/footer.ftl">
</body>
</html>