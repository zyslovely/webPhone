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

<div style="margin-top:30px;"><h3>输入手机条形码</h3><input type="text" value="" id="phone_delete_text"/><input type="button" id="phone_delete" " value="删除"/></div>>

   <table id="phone_list_tb" >
			<thead>
				<tr>
					<th width="50">手机型号</th>
					<th width="150">手机编码</th>
					<th width="50">进货价格</th>
					<th width="150">进货日期</th>
					<th width="50">计划卖出价格</th>
					<th width="50">是否已经卖出</th>
					<th width="50">实际卖出价格</th>
					<th width="150">卖出日期</th>
					<th width="50">利润</th>
					<th width="操作">操作</th>
				</tr>
			</thead>
			<tbody>
			<#if phoneList?exists>
			   <#list phoneList as phone>
				<tr>
					<td>${phone.phoneModel!""}</td>
					<td>${phone.phoneCode!""}</td>
					<td>${phone.purchasePrice!0}</td>
					<td>${phone.purchaseTimeStr!""}</td>
					<td>${phone.DecideSellPrice!0}</td>
					<td><#if phone.status == 0>是<#else>否</#if></td>
					<td>${phone.SelledPrice!0}</td>
					<td>${phone.selledTimeStr!0}</td>
					<td>${phone.profit!0}</td>
					<td><a href="javascript:void(0);;" class="phone_delete" data-id="${ridding.id!0}">删除</a></td>
				</tr>
			    </#list>
			</#if>
			</tbody>
		</table>
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