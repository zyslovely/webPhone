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
<h1 style="font-size: 20px;color: red;margin-bottom:30px;">手机利润查询页面</h1>

<form action="/profit/list/?toPage=0" method="post" target="_self" >
<div style="margin-right:10px;">
    <input type="radio" name="profitDate" value="1" <#if profitDate==1>checked</#if>/>今天的利润
    <input type="radio" name="profitDate" value="2" <#if profitDate==2>checked</#if>/>昨天的利润
    <input type="radio" name="profitDate" value="3" <#if profitDate==3>checked</#if>/>当月的利润
    <input type="radio" name="profitDate" value="4" <#if profitDate==4>checked</#if>/>按时间查询利润
    <div>
        <span>按日期进行查询</span>
        <input class="Wdate" name="beginDate" type="text" onClick="WdatePicker()" <#if beginDate?exists>value="${beginDate!""}"</#if>>
        <span style="margin:0 10px;">到</span>
        <input class="Wdate" name="endDate" type="text" onClick="WdatePicker()" <#if endDate?exists>value="${endDate!""}"</#if>>
    </div>
    <input type="submit" value="提交"/>
</div>

</form>

<div style="margin-top:30px;color:red;font-size:18px;">
   <span>总销售:${saleTotal!0}</span><span>总利润:${profitTotal!0}</span>
</div>
<#if profitVoList?exists>
<p style="margin-top:30px;">
   <span style="color:red">当前第${nowPage!0}页，总共${totalPage!0}页</span>。
   <#if extPage gt 0><a href="/profit/list/?toPage=${extPage!0}&profitDate=${profitDate!0}&beginDate=${beginDate!""}&endDate=${endDate!""}">上一页</a></#if>
   <#if nextPage lt totalPage+1><a href="/profit/list/?toPage=${nextPage!0}&profitDate=${profitDate!0}&beginDate=${beginDate!""}&endDate=${endDate!""}">下一页</a></#if>
</p>
   <table id="profit_list_tb" >
			<thead>
				<tr>
				    <th width="100">手机型号</th>
				    <th width="100">手机条形码</th>
					<th width="100">购入价格</th>
					<th width="100">实际卖出价格</th>
					<th width="150">利润</th>
					<th width="150">进货日期</th>
					<th width="150">卖出日期</th>
					
				</tr>
			</thead>
			<tbody>
			   <#list profitVoList as profit>
				<tr >
				    <td>${profit.brandName!""} ${profit.phoneModel!""}</td>
				    <td>${profit.phoneCode!""}</td>
					<td>${profit.purchasePrice!0}</td>
					<td>${profit.selledPrice!0}</td>
					<td>${profit.profit!0}</td>
					<td>${profit.purchaseTimeStr!0}</td>
					<td>${profit.selledTimeStr!0}</td>
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
			$('#profit_list_tb').flexigrid({
				 width : 'auto', 
			});
		});
//]]>
</script>