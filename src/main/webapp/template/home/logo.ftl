<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${rc.contextPath}/css/default.css" rel="stylesheet" type="text/css" media="all" />
<title>后台综合管理系统</title>
<base target="content" />
</head>
<body>
<div class="head">
<div class="logo"></div>

<div class="loding">您好，${(tuser.uname)!'那个谁？'} 
	<span>|</span><a href="${rc.contextPath}/content.act">首页</a>
	<span>|</span><a href="${rc.contextPath}/tabsFrame.act?id=100300">修改密码</a>
	<span>|</span><a target="_top" href="${rc.contextPath}/loginout.act">退出</a>
	</a></div>
<div class="clear"></div>
</div>
</body>
</html>