<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${rc.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
<style>
	li.label {width:120px}
	span {color:red}
	li.sl {width:80%}
</style>
<title>后台综合管理系统-修改密码</title>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/messages_cn.js"></script>
<script>
	$(function() {
		$(":password:first").focus();
		$("form:first").validate({
			submitHandler : function(form) {
				var options = {
					success : function(data) {
						if (data) {
							alert("修改密码失败\r\n" + data);
							$(":password:first").focus();
						} else {
							alert("密码已成功修改，请牢记新密码");
						}
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
<form action="${rc.contextPath}/changepwd.act" name="inputform">
<div class="right_box">
<ul class="fix">
<li class="nl label">原密码 <span>*</span></li>
<li class="sl"><input type="password" name="oldpwd" class="required ipb_m"/></li>
<li class="nl label">新密码 <span>*</span></li>
<li class="sl"><input type="password" name="newpwd" class="required ipb_m" id="newpwd" minLength="6" maxLength="20"/></li>
<li class="nl label">确认新密码 <span>*</span></li>
<li class="sl"><input type="password" class="required ipb_m" equalTo="#newpwd"/></li>
<li class="nl fix"><button type="submit" class="but_l mima">提交</button></li>
</ul>
</div>
</form>
<#include "/include/footer.ftl">
</body>
</html>