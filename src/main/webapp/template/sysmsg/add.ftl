<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${rc.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
<style>
	li.label {width:120px}
	li.sl {width:80%}
</style>
<title>后台综合管理系统-添加系统公告</title>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/messages_cn.js"></script>
<script>
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
	});

</script>
</head>
<body>
<#include "/include/header.ftl">
<h2 style="margin:20px 0 0 30px;">添加系统公告</h2>
<form action="${rc.contextPath}/sysMsg/addSysMsg.act" name="inputform" method="post">
<ul class="fix">
<li class="nl label">标题</li>
<li class="sl"><input type="text" name="title" class="required ipb_m" style="width:300px"/></li>
<li class="nl label">内容</li>
<li class="sl"><textarea name='msg' id='msg' rows='5' cols='50' class="required" maxlength='50'></textarea></li>
<li class="nl fix"><button type="submit" class="but_l mima">提交</button></li>
</ul>
</form>
<div style="margin:20px 0 0 30px;color:blue;display:none;" id="res" ></div>

<#include "/include/footer.ftl">
</body>
</html>