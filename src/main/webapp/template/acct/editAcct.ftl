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
	
		jQuery.validator.addMethod("mobile", function(value, element) {
			var pattern = /1[3|4|5|8][0-9]{9}$/;
		    return this.optional(element) || (pattern.test(value));
		}, "请输入正确的手机号码");
		
		jQuery.validator.addMethod("onlyLetterAndDigit",function(value, element){   
		    var pattern = /^[0-9a-zA-Z]+$/;
		    return this.optional(element) || (pattern.test(value));
		},"只能输入字母或数字");
	
		$(":text:first").focus();
		$("form:first").validate({
			rules:{
				pwd:{  
		            rangelength:[6,10]  
                }
			},
			messages:{
				pwd:{  
		            rangelength:'密码长度必须在6-10位之间'
                }
			},
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
<h2 style="margin:20px 0 0 30px;">编辑账户</h2>
<form action="${rc.contextPath}/acct/editAcct.act" name="inputform" method="post">
<input type="hidden" name="brandId" value="${acct.brandId}"> 
<input type="hidden" name="uid" value="${acct.uid}"> 
	<ul class="fix">
		<li class="nl label">品牌</li>
		<li class="sl">亿信</li>
		<li class="nl label">账户号</li>
		<li class="sl">${acct.uid!""}</li>
		<li class="nl label">账户创建日期</li>
		<li class="sl">${acct.createTime?substring(0,19)!""}</li>
		<li class="nl label">所属代理商</li>
		<li class="sl">${acct.agent!""}</li>
		<li class="nl label">登录历史</li>
		<li class="sl">
		<#if acct.first=='n'>
		登陆过
		<#else>
		未登陆
		</#if>
		</li>
		
		<li class="nl label">账户类型</li>
		<li class="sl">
		<#if acct.acctType=='year'>
		年卡
		<#elseif acct.acctType=='month'>
		月卡
		<#else>
		日卡
		</#if>
		</li>
		
		<li class="nl label">密码</li>
		<li class="sl"><input type="text" name="pwd" id="pwd" value="${acct.pwd!""}" class="required onlyLetterAndDigit ipb_m" /></li>
		<li class="nl label">余额（分）</li>
		<li class="sl"><input type="text" name="balance" value="${(acct.balance/10000)}" class="required digits ipb_m" /></li>
		<li class="nl label">手机</li>
		<li class="sl"><input type="text" name="phone" value="${acct.phone!""}" class="required mobile ipb_m" /></li>
		<li class="nl label">是否冻结</li>
		
		<li class="sl">
		<select type="text" name="enableFlag" class="required ipb_l">
			<#if acct.enableFlag=='1'>
			<option value="0">冻结</option>
			<option value="1" selected>未冻结</option>
			<#else>
			<option value="1">未冻结</option>
			<option value="0" selected>冻结</option>
			</#if>
		</select>
		</li>
		
		<li class="nl label">限定绑定次数</li>
		<li class="sl"><input type="text" name="bindLimit" value="${acct.enableFlag!""}" class="required digits ipb_m" /></li>
		<li class="nl label">账户有效期</li>
		<li class="sl"><input type="text" name="validDate" id="validDate" class="Wdate required ipb_m" type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value="${acct.validDate!''}"/></li>
		
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