<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "webIndex" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{font-size:83%;}
#help{padding-top:20%; text-align:center;}
img{max-width: 100%;max-height: 80%}
div.outset {border-style: none;width: 20%;height: 300px;float:left;clean:both}

</style>
	
<body style="height:1000px;">

<form action="/login/">
<div>
<span>管理员账号:</span>
<input type="text" value="" name="j_username"/>
</div>
<div>
<span>管理员密码:</span>
<input type="text" value="" name="j_password"/>
<input type="checkbox" name="_spring_security_remember_me" />两周之内不必登陆<br/>

<input type="submit" value="登录"/> 
</form>


</body>
</html>
</#escape>
		
<#include "js.ftl">

</script>