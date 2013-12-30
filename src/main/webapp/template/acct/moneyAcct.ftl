<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="${rc.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${rc.contextPath}/css/tabs.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${rc.contextPath}/css/ui-lightness/jquery.ui.css" rel="stylesheet" type="text/css" media="all" />
	<style>
		#div {
			width: auto;
			height: 400px;
			
			padding: 20px 0 0 0px;
		}
		#div li{line-height:30px;}
		li.sl {width:80%}
	</style>
  <title>生成余额帐号 </title>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.js"></script>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.ui.js"></script>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.metadata.js"></script>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.tools.js"></script>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.form.js"></script>
  <script type="text/javascript" src="${rc.contextPath}/js/jquery.validate.js"></script>
  <script type="text/javascript" src="${rc.contextPath}/js/messages_cn.js"></script>
 </head>
 <script type="text/javascript">
		 $(function() {
			 var dialog=$("#dialog");
			 var dialog_content=$("#dialog_content");
			 var doing=dialog_content.html();
			
			 var close_able = false;	
		
			 dialog.dialog({
				autoOpen: false,
				width:300,
				modal: true,
				beforeClose: function(event, ui) { return close_able; }
			 });
			 
		 	 $("form:first").validate({
		 		submitHandler : function(form) {
				var options = {
				
					beforeSubmit : function(arr,form,options) {
						if (confirm("确定提交吗？")) {
							close_able=false;
							dialog_content.html(doing);
							dialog.dialog("option","title","处理中");
							dialog.dialog("open");
							return true;
						} else {
							return false;
						}
					},
				
					success : function(data) {
						close_able = true;
						dialog.dialog("option","title","处理完成");
						if (data) {
							dialog_content.html("操作失败！<br>" + data);
						} else {
							dialog_content.html("操作成功！");
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
 <body>
  <form name="queryform" namespace="/acct" action="createMoneyAcct.act" >
  <div class="right_box">
  	<div id="div">
  		<ul class="fix">
  			<li class="nl label">说明：个数--表示当前生成包月帐号的个数，一次最多填写6位数字；</li>
  			<li class="sl"></li>
  			<li class="nl label">年、月、天数--表示当前生成帐号的有效时长，例如年数是1，则表示生成帐号的有效时长是1年；</li>
			<li class="sl">&nbsp;</li>
  		</ul>
  		<ul class="fix" style="margin-top:0px;">
  			<li class="sl">
  				代理商：
	  				<select name="agent" id="agent" width="200px" class="required ipb_l" >
	  				    <option value=''>--请选择--</option>
					  <#if list??>
	   				  <#list list as item>
	   				    <option value='${item.alias!""}'>${item.name!""}</option>
	   				  </#list>
	   				  </#if>
	  				</select>
	  			个数：<input type='text' name='number' id='number' maxlength='6' class="required number ipb_m" />
  				<!--密码：<input type='text' name='pwd' id='pwd' maxlength='6' class="required number ipb_m" /-->
  				钱数（分）：<input type='text' name='money' id='money' maxlength='6' class="required number ipb_m" />
  			</li>
  			<li class="sl">&nbsp;</li>
  			<li class="sl">
  				年数：<input type='text' name='year' id='year' maxlength='2' class="required number ipb_m" />
  				月数：<input type='text' name='month' id='month' maxlength='2' class="required number ipb_m" />
  				天数：<input type='text' name='day' id='day' maxlength='2' class="required number ipb_m" />
  			</li>
  			<li class="sl">备注：</li>
  			<li class="sl"><textarea name='remarks' id='remarks' rows='5' cols='50' maxlength='100'></textarea></li>
  			<li class="sl">&nbsp;</li>
  			<li class="nl label"><button type='submit' class="btn but_l shouxin ">提 交</button></li>
  		</ul>
  	</div>
  	</div>
  </form>
  <!-- jquery对话框 -->
  <div id="dialog" title="处理中">
  <p id="dialog_content"><img src="${rc.contextPath}/images/loading.gif" width="16" height="16" border="0" align="absmiddle" /> 正在处理,请稍候...</p>
  </div>
  
 </body>
</html>
