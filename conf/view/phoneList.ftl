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
<h2 style="font-size: 25px;color: blue;margin-bottom:30px;"><a href="/phone/index/">返回</a></h2>
<h1 style="font-size: 20px;color: red;margin-bottom:30px;">手机查询页面(未卖出的总量${totalPhoneCount!0})</h1>

<form action="/phone/list/" target="_self"/>
<div>
   <span>输入手机型号:</span>
   <input type="text" value="${phoneModel!""}" name="phoneModel"/>
   <span> 或者 </span>
   <span>串号:</span>
   <input type="text" value="" name="phoneCode"/>
   <span> 或者 </span>
   <span>品牌:</span>
   <input type="text" value="" name="brandName"/>
   <select name="status">
       <option value ="-1" >显示全部</option>  
       <option value ="0" >只显示未卖出的</option>  
       <option value ="1" >只显示已卖出的</option> 
   </select>
   <select name="inventory">
       <#if inventory==1>
           <option value ="1" >盘点</option>
           <option value ="0" >不盘点</option>
       <#else>
           <option value ="0" >不盘点</option>
           <option value ="1" >盘点</option>
       </#if>
   </select>

   <input style="margin-left:20px" type="submit" value="确定"/>
</div>
</form>

<div>
    <a style="font-size: 25px;" href="javascript:void(0);" onClick="resetAllInventory();">重置盘点数据</a>
</div>

<#if phoneList?exists>
<p>
   <span style="color:red">当前第${nowPage!0}页，总共${totalPage!0}页,总共${searchPhonetotalCount!0}个手机,未卖出手机成本总数为${totalCostOfNotSelled!1}</span>。
   <#if extPage gt 0><a href="/phone/list/?phoneModel=${phoneModel!""}&toPage=${extPage!0}&inventory=${inventory!0}&status=${status!-1}&brandName=${brandName!""}">上一页</a></#if>
   <#if nextPage lt totalPage+1><a href="/phone/list/?phoneModel=${phoneModel!""}&toPage=${nextPage!0}&inventory=${inventory!0}&status=${status!-1}&brandName=${brandName!""}">下一页</a></#if>
</p>
   <table id="phone_list_tb" >
			<thead>
				<tr>
				    <th width="50">品牌</th>
					<th width="50">手机型号</th>
					<th width="80">串号</th>
					<th width="80">进货价格</th>
					<th width="80">进货日期</th>
					<th width="50"">是否卖出</th>
					<th width="100">实际卖出价格</th>
					<th width="150">卖出日期</th>
					<th width="50">利润</th>
					<th width="100">是否盘点入库</th>
					<th width="150">操作</th>
					<th width="150">转移店铺 </th>
				</tr>
			</thead>
			<tbody>
			
			   <#list phoneList as phone>
				<tr >
				    <td>${phone.brand!""}</td>
					<td>${phone.phoneModel!""}</td>
					<td>${phone.phoneCode!""}</td>
					<td>${phone.purchasePrice!0}<#if level==3><a href="javascript:void(0);"  onClick="phonePurchasePriceChange(${phone.phoneId});"><修改></a></#if></td>
					<td>${phone.purchaseTimeStr!""}</td>
					<td><#if phone.status == 0><span style="color:green">否</span><#elseif phone.status == 1><span style="color:red">已卖出</span></#if></td>
					<td>${phone.selledPrice!0}<#if phone.status==1><#if level==3><a href="javascript:void(0);"  onClick="phoneSelledPriceChange(${phone.phoneId});"><修改></a></#if></#if></td>
					<td>${phone.selledTimeStr!0}</td>
					<td>${phone.profit!0}</td>
					<td><#if phone.inventory==0><span color="green">未入库</span><#else><span color="red">已入库</span></#if> </td>
					<td>
					   <#if phone.status == 0>
					      <#if phone.inventory==0&&phone.status == 0><a href="javascript:void(0);"  onClick="phoneInventory(${phone.phoneId});">盘点入库</a></#if>
					      <#if level==3><a href="javascript:void(0);"  onClick="phoneDelete(${phone.phoneId});">删除</a></#if>
					      <a href="javascript:void(0);"  onClick="phoneSell(${phone.phoneId},${phone.purchasePrice!0});">卖出</a>
			
					   <#elseif phone.status == 1>
					      <a href="javascript:void(0);"  onClick="phoneReturn(${phone.phoneId});">退货</a>
					   </#if>
					</td>
					<td>
					<#if level==3>
					    <#if phone.status == 0>
					    <select onChange="phoneChange(${phone.phoneId},this);return;">
					          <option value ="0" >转移店铺</option>
					          <option value ="1" >宜美店</option>
                              <option value ="2" >天宝店</option>
                              <option value ="3" >正泰店</option>
                          </select>
                         <#elseif phone.status==1>
                              已卖出
                         </#if>
                         </#if>
					</td>
				</tr>
			    </#list>

			</tbody>
		</table>
<#elseif noFound?exists>
<h3 color="red">找不到${phoneModel!""}</h3>
</#if>
</body>
</html>
</#escape>
		
<#include "js.ftl">
<script>
//<![CDATA[
       jQuery(document).ready(function($) {
			$('#phone_list_tb').flexigrid({
				 buttons : [{
				 name : '添加手机',
				 width : 50,
				 bclass : 'add',
				 onpress : onpressAdd
				 }],
				 width : 'auto', 
			});
		});
//]]>
</script>