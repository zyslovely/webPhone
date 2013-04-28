<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "profitList" />
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
<h1 style="font-size: 20px;color: red;margin-bottom:30px;">利润查询页面</h1>

<a href="/profit/list/?today=1">今天的利润</a><a href="/profit/list/?yesterday=1">昨天的利润</a>

<#if profitList?exists>
<p>
   <span style="color:red">当前第${nowPage+1!0}页，总共${totalPage!0}页</span>。
   <#if extPage+1 gt 0><a href="/profit/list/?startTimeString=${}&endTimeString=${}&shopId=${}">上一页</a></#if>
   <#if extPage lt totalPage-2><a href="/profit/list/?startTimeString=${}&endTimeString=${}&shopId=${}">下一页</a></#if>
</p>
   <table id="profit_list_tb" >
			<thead>
				<tr>
				    <th width="100">phoneid</th>
					<th width="100">购入价格</th>
					<th width="150">计划卖出价格</th>
					<th width="100">实际卖出价格</th>
					<th width="150">利润</th>
					<th width="100">记录创建时间</th>
					<th width="100"">操作人ID</th>
					<th width="100">店铺ID</th>
				</tr>
			</thead>
			<tbody>
			
			   <#list profitList as profit>
				<tr >
				    <td>${profit.phoneid!0}</td>
					<td>${profit.purchasePrice!0}</td>
					<td>${profit.DecideSellPrice!0}</td>
					<td>${profit.SelledPrice!0}</td>
					<td>${profit.profit!0}</td>
					<td>${profit.CreateTime!0}</td>
					<td>${profit.operatorId!0}</td>
					<td>${profit.shopId!0}</td>
				</tr>
			    </#list>

			</tbody>
		</table>
					</#if>
</body>
</html>
</#escape>
		
<#include "js.ftl">