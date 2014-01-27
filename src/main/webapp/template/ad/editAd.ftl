<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${rc.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
<style>
	li.label {width:120px}
	li.sl {width:80%}
</style>
<title>后台综合管理系统-编辑账户</title>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/messages_cn.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/DatePicker/WdatePicker.js"></script>
<script>
	$(function() {
	
		$(":text:first").focus();
		$("form:first").validate({
			submitHandler : function(form) {
				var options = {
					success : function(data) {
						$("#res").html(data).show();
						alert(data);
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
	});

</script>
</head>
<body>
<#include "/include/header.ftl">
<h2 style="margin:20px 0 0 30px;">编辑广告</h2>
<form action="${rc.contextPath}/ad/editAd.act" name="inputform" method="post">
<input type="hidden" name="brandid" value="${ad.brandid}"> 
<input type="hidden" name="id" value="${ad.id}"> 
	<ul class="fix">
		<li class="nl label">品牌</li>
		<li class="sl">亿信</li>
		<li class="nl label">ID</li>
		<li class="sl">
		<select type="text" name="adpid" class="required ipb_l">
			<#if ad.adpid=='01'>
			<option value="01" selected>01</option>
			<option value="02">02</option>
			<option value="03">03</option>
			<#elseif ad.adpid=='02'>
			<option value="01">01</option>
			<option value="02" selected>02</option>
			<option value="03">03</option>
			<#else>
			<option value="01">01</option>
			<option value="02">02</option>
			<option value="03" selected>03</option>
			</#if>
		</select>
		
		</li>
		<li class="nl label">名称</li>
		<li class="sl"><input type="text" name="name" id="name" value="${ad.name!""}" class="required ipb_m" /></li>
		<li class="nl label">跳转地址</li>
		<li class="sl"><input type="text" name="url" id="url" value="${ad.url!""}" class="required ipb_m" /></li>
		<li class="nl label">图片地址</li>
		<li class="sl"><textarea name='img' id='img' rows='5' cols='50' maxlength='200'>${ad.img!""}</textarea></li>
		<li class="nl fix"><button type="submit" class="but_l mima">提交</button></li>
	</ul>
</form>
<div style="margin:20px 0 0 30px;color:blue;display:none;" id="res" ></div>

<#include "/include/footer.ftl">
</body>
</html>