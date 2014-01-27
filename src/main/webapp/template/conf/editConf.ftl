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
<h2 style="margin:20px 0 0 30px;">编辑配置</h2>
<form action="${rc.contextPath}/conf/editConf.act" name="inputform" method="post">
<input type="hidden" name="brandid" value="${conf.brandid}"> 
<input type="hidden" name="id" value="${conf.id}"> 
	<ul class="fix">
		<li class="nl label">品牌</li>
		<li class="sl">亿信</li>
		<li class="nl label">名称</li>
		<li class="sl">${conf.param!""}</li>
		<li class="nl label">值</li>
		<li class="sl"><textarea name='value' id='value' rows='5' cols='50' maxlength='200' class="required" >${conf.value!""}</textarea></li>
		<li class="nl label">备注</li>
		<li class="sl">${conf.remark!""}</li>
		<li class="nl fix"><button type="submit" class="but_l mima">提交</button></li>
	</ul>
</form>
<div style="margin:20px 0 0 30px;color:blue;display:none;" id="res" ></div>

<#include "/include/footer.ftl">
</body>
</html>