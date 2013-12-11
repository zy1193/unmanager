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
	$(function() {
		
		jQuery.validator.addMethod("isCode", function(value, element) {
		    return this.optional(element) || /^[a-z]+$/i.test(value);
		}, "必须是小写字母");
		jQuery.validator.addMethod("mobile", function(value, element) {
			var pattern = /1[3|4|5|8][0-9]{9}$/;
		    return this.optional(element) || (pattern.test(value));
		}, "请输入正确的手机号码");
		
		
		$(":text:first").focus();
		$("form:first").validate({
			rules:{
				alias:{  
		            maxlength:15  
                }
			},
			messages:{
				alias:{  
		            maxlength:'长度不能超过15个字符'
                }
			},
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

/**	
 $("document").ready(function(){
 
		$("#alias").blur(function(){  
			var regex =/^[A-Za-z0-9]+$/;
		   	if(!regex.test($("#alias").val())){
		   		alert('别名只能是数字或者字母');
		   		$("#alias").focus();
		   	}
		 });
		 
	});
	 **/
</script>
</head>
<body>
<#include "/include/header.ftl">
<h2 style="margin:20px 0 0 30px;">添加代理商</h2>
<form action="${rc.contextPath}/addAgent.act" name="inputform" method="post">
<ul class="fix">
<li class="nl label">姓名</li>
<li class="sl"><input type="text" name="name" class="required ipb_m" style="width:300px"/></li>
<li class="nl label">别名（必须唯一）</li>
<li class="sl"><input type="text" name="alias" id="alias" class="required isCode ipb_m" style="width:300px"/></li>
<li class="nl label">座机</li>
<li class="sl"><input type="text" name="telNo" class="required ipb_m" style="width:300px"/></li>
<li class="nl label">手机</li>
<li class="sl"><input type="text" name="mobileNo" class="required mobile ipb_m" style="width:300px"/></li>
<li class="nl label">身份证</li>
<li class="sl"><input type="text" name="idCardNo" class="required ipb_m" style="width:300px"/></li>
<li class="nl label">QQ</li>
<li class="sl"><input type="text" name="qq" class="required ipb_m" style="width:300px"/></li>
<li class="nl label">备注</li>
<li class="sl"><input type="text" name="remarks" class="required ipb_m" style="width:300px"/></li>

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