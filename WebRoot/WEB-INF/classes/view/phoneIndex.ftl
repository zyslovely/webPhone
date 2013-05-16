<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "phoneList" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{font-size:83%;}
#help{padding-top:20%; text-align:center;}
img{max-width: 100%;max-height: 80%}
div.outset {border-style: none;width: 20%;height: 300px;float:left;clean:both}

</style>
	
<body style="height:1000px;">
<#include "topbar.ftl">
<h3 style="margin-top:20px;font-size:20px;"><a href="/purchase/add/show/">添加手机信息</a></h3>
<h3 style="margin-top:20px;font-size:20px;"><a href="/phone/list/">手机查询\卖出</a></h3>
<#if level==3>
<h3 style="margin-top:20px;font-size:20px;"><a href="/profit/list/">手机利润查询</a></h3>
</#if>
<h3 style="margin-top:20px;font-size:20px;"><a href="/accessory/add/show/">添加配件信息</a></h3>
<h3 style="margin-top:20px;font-size:20px;"><a href="/accessory/list/">配件查询\卖出</a></h3>
<#if level==3>
<h3 style="margin-top:20px;font-size:20px;"><a href="/accessory/profit/list/">配件利润查询</a></h3>
</#if>
<h3 style="margin-top:20px;font-size:20px;"><a href="/profit/list/">保修查询</a></h3>
<h3 style="margin-top:20px;font-size:20px;"><a href="/logout/">退出</a></h3>

</body>
</html>
</#escape>
		
<#include "js.ftl">
<script>
//<![CDATA[
       jQuery(document).ready(function($) {
			$('#phone_list_tb').flexigrid();
		});
//]]>
</script>