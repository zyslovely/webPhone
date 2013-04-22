<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "phoneAdd" />
<#include "head.ftl">
<style type="text/css">
html,body,#gmap{height:100%; margin:0;}
body{font-size:83%;}
#help{padding-top:20%; text-align:center;}
img{max-width: 100%;max-height: 80%}
div.outset {border-style: none;width: 20%;height: 300px;float:left;clean:both}

</style>


<body style="height:1000px;">
<h2 style="font-size: 25px;color: blue;margin-bottom:30px;"><a href="/phone/index/">返回</a></h2>
<h1 style="font-size: 20px;color: red;margin-bottom:30px;">添加手机页面</h1>
<table border="1" cellspacing="0px">
<thead>
<tr>
<th width="150">手机型号</th><th width="150">手机条形码</th><th width="150">手机品牌</th><th width="150">进货价格</th><th width="150">计划卖出价格</th><th width="150">操作</th>
</tr>
</thead>
<tbody>
<form action="/purchase/add/show/" method="post" target="_self">
<th><input type="text" value="" name="phoneModel"/></th>
<th><input type="text" value="" name="phoneCode"/></th>
<th><input type="text" value="" name="brand"/></th>
<th><input type="text" value="" name="purchasePrice"/></th>
<th><input type="text" value="" name="DecideSellPrice"/></th>
<th><input type="submit" value="提交"/></th>
</form>
</tbody>
</table>

<#if phoneList?exists>
<div style="font-size: 20px;color: red;margin-top:30px;">${phoneModel!""}手机列表,${phoneModel!""}手机总量${phoneModelCount!0}部</div>
   <table id="phone_list_tb" >
			<thead>
				<tr>
					<th width="100">手机型号</th>
					<th width="150">手机编码</th>
					<th width="100">进货价格</th>
					<th width="150">进货日期</th>
					<th width="100">计划卖出价格</th>
					<th width="100">是否已经卖出</th>
					<th width="100">实际卖出价格</th>
					<th width="150">卖出日期</th>
					<th width="100">利润</th>
					<th width="操作">操作</th>
				</tr>
			</thead>
			<tbody>
			   <#list phoneList as phone>
				<tr <#if phoneCode == phone.phoneCode >style="color:red;"</#if>>
					<td>${phone.phoneModel!""}</td>
					<td>${phone.phoneCode!""}</td>
					<td>${phone.purchasePrice!0}</td>
					<td>${phone.purchaseTimeStr!""}</td>
					<td>${phone.DecideSellPrice!0}</td>
					<td><#if phone.status == 0>是<#else>否</#if></td>
					<td>${phone.SelledPrice!0}</td>
					<td>${phone.selledTimeStr!0}</td>
					<td>${phone.profit!0}</td>
					<td><a href="javascript:void(0);"  onClick="phoneDelete(${phone.phoneId!""});">删除</a></td>
				</tr>
			    </#list>
			</tbody>
		</table>
		</#if>
</body>
</html>
</#escape>
		
<#include "js.ftl">
<script>
//<![CDATA[
       jQuery(document).ready(function($) {
			$('#phone_list_tb').flexigrid({
				 usepager: true,
				 page: ${nowPage!0}, 
			     total: ${totalPage!0}, 
			});
		});
//]]>
</script>