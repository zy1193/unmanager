<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.list li {line-height:25px;color:#0099dd;font-weight:bold;}
.list li span{margin-left:10px;color:#000;font-size:12px;}
</style>
</head>

<body>
<ul class="list">
<li>订单号：<span>${Odetail.gameorderid!""}</span></li>
<li>应用名：<span>${Odetail.appName!""}</span></li>
<li>支付方式：<span>${Odetail.paytype!""}</span></li>
<li>有信账户：<span>${Odetail.uid!""}</span></li>
<li>订单金额：<span>${(Odetail.gordermoney?eval/100)?string.currency}</span></li>
<li>支付金额：<span>${(Odetail.paymoney?eval/100)?string.currency}</span></li>
<li>流水号：<span>${Odetail.orderid!"0"}</span></li>

<li>支付状态：<span>
	      <#if Odetail.payresult="success">
			success
			<#elseif  item.payresult="fail">
			fail
			<#elseif  item.gamenotifyresult="0">
			wait
			<#else>
			wait
			</#if>
</span></li>

<li>通知状态：<span>
	      <#if Odetail.gamenotifyresult="success">
			success
			<#elseif  item.gamenotifyresult="fail">
			fail
			<#elseif  item.gamenotifyresult="0">
			wait
			<#else>
			wait
			</#if>
</span></li>

<li>通知次数：<span>${Odetail.gamenotifycount!"0"}</span></li>

<li>下单时间：<span>${Odetail.gameordertime?substring(0,19)}</span></li>
</ul>
</body>

</html>