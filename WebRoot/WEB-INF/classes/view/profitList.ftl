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

<h3 style="font-size: 25px;color: blue;margin-bottom:30px;">

<#if profitList?exists>
<p>
   <span style="color:red">当前第${nowPage+1!0}页，总共${totalPage!0}页</span>。
   <#if extPage+1 gt 0><a href="/phone/list/?phoneModel=${phoneModel!""}&toPage=${extPage!0}">上一页</a></#if>
   <#if extPage lt totalPage-2><a href="/phone/list/?phoneModel=${phoneModel!""}&toPage=${nextPage!0}">下一页</a></#if>
</p>
   <table id="phone_list_tb" >
			<thead>
				<tr>
				    <th width="100">品牌</th>
					<th width="100">手机型号</th>
					<th width="150">手机编码</th>
					<th width="100">进货价格</th>
					<th width="150">进货日期</th>
					<th width="100">计划卖出价格</th>
					<th width="100"">是否已经卖出</th>
					<th width="100">实际卖出价格</th>
					<th width="150">卖出日期</th>
					<th width="100">利润</th>
					<th width="操作">操作</th>
				</tr>
			</thead>
			<tbody>
			
			   <#list phoneList as phone>
				<tr >
				    <td>${phone.brand!""}</td>
					<td>${phone.phoneModel!""}</td>
					<td>${phone.phoneCode!""}</td>
					<td>${phone.purchasePrice!0}</td>
					<td>${phone.purchaseTimeStr!""}</td>
					<td>${phone.decideSellPrice!0}</td>
					<td><#if phone.status == 0><span style="color:green">没有卖出</span><#elseif phone.status == 1><span style="color:red">已卖出</span></#if></td>
					<td>${phone.selledPrice!0}</td>
					<td>${phone.selledTimeStr!0}</td>
					<td>${phone.profit!0}</td>
					<td>
					   <a href="javascript:void(0);"  onClick="phoneDelete(${phone.phoneId});">删除</a>
					   <a href="javascript:void(0);"  onClick="phoneSell(${phone.phoneId},${phone.purchasePrice!0});">卖出</a>
					</td>
				</tr>
			    </#list>

			</tbody>
		</table>
					</#if>
</body>
</html>
</#escape>
		
<#include "js.ftl">