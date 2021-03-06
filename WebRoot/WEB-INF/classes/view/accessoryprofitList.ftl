<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "accessoryprofitList" />
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
<h1 style="font-size: 20px;color: red;margin-bottom:30px;">配件利润查询页面</h1>

<form action="/accessory/profit/list/?toPage=0" method="post" target="_self">
<div style="margin-right:10px;">
    <input type="radio" name="accessoryprofitDate" value="1" <#if accessoryprofitDate==1>checked</#if>/>今天的利润
    <input type="radio" name="accessoryprofitDate" value="2" <#if accessoryprofitDate==2>checked</#if>/>昨天的利润
    <input type="radio" name="accessoryprofitDate" value="3" <#if accessoryprofitDate==3>checked</#if>/>当月的利润
    <input type="submit" value="提交"/>
</div>

</form>

<div style="margin-top:30px;color:red;font-size:18px;">
   <span>总销售:${accessorysaleTotal!0}</span><span>总利润:${accessoryprofitTotal!0}</span>
</div>
<#if accessoryProfitVOList?exists>
<p style="margin-top:30px;">
   <span style="color:red">当前第${nowPage!0}页，总共${totalPage!0}页,总共#{totalCount!0}条数据</span>。
   <#if extPage gt 0><a href="/accessory/profit/list/?toPage=${extPage!0}&accessoryprofitDate=${accessoryprofitDate!0}">上一页</a></#if>
   <#if nextPage lt totalPage+1><a href="/accessory/profit/list/?toPage=${nextPage!0}&accessoryprofitDate=${accessoryprofitDate!0}">下一页</a></#if>
</p>
   <table id="accessory_profit_list_tb" >
				<tr>
				    <th width="100">配件类型</th>
					<th width="100">配件名称</th>
					<th width="100">单价</th>
					<th width="100">售价</th>
					<th width="100">利润</th>
					<th width="150">更新时间</th>
				</tr>
			<tbody>
			   <#list accessoryProfitVOList as accessoryProfitVO>
				<tr >
				    <td>${accessoryProfitVO.accessoryInfoName!0}</td>
					<td>${accessoryProfitVO.name!0}</td>
					<td>${accessoryProfitVO.unitPrice!0}</td>
					<td>${accessoryProfitVO.soldPrice!0}</td>
					<td>${accessoryProfitVO.profit!0}</td>
					<td>${accessoryProfitVO.lastUpdateTime!0}</td>
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
			$('#accessory_profit_list_tb').flexigrid({
				 width : 'auto', 
			});
		});
//]]>
</script>