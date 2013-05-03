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
<h1 style="font-size: 20px;color: red;margin-bottom:30px;">手机查询页面(当前手机总量${totalPhoneCount!0})</h1>
<form action="/phone/list/" method="post" target="_self"/>
<div>
   <span>输入手机型号:</span>
   <input type="text" value="" name="phoneModel"/>
   <span> 或者 </span>
   <span>串号:</span>
   <input type="text" value="" name="phoneCode"/>
   <select name="status">
       <option value ="-1" >显示全部</option>  
       <option value ="0" >只显示未卖出的</option>  
       <option value ="1" >只显示已卖出的</option> 
   </select>
   <input type="submit" value="确定"/>
</div>
</form>

<#if phoneList?exists>
<p>
   <span style="color:red">当前第${nowPage!0}页，总共${totalPage!0}页,总共${searchPhonetotalCount!1}个手机</span>。
   <#if extPage gt 0><a href="/phone/list/?phoneModel=${phoneModel!""}&toPage=${extPage!0}">上一页</a></#if>
   <#if nextPage lt totalPage+1><a href="/phone/list/?phoneModel=${phoneModel!""}&toPage=${nextPage!0}">下一页</a></#if>
</p>
   <table id="phone_list_tb" >
			<thead>
				<tr>
				    <th width="100">品牌</th>
					<th width="100">手机型号</th>
					<th width="150">串号</th>
					<th width="100">进货价格</th>
					<th width="150">进货日期</th>
					<th width="100"">是否已经卖出</th>
					<th width="100">实际卖出价格</th>
					<th width="150">卖出日期</th>
					<th width="100">利润</th>
					<th width="150">操作</th>
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
					<td><#if phone.status == 0><span style="color:green">没有卖出</span><#elseif phone.status == 1><span style="color:red">已卖出</span></#if></td>
					<td>${phone.selledPrice!0}</td>
					<td>${phone.selledTimeStr!0}</td>
					<td>${phone.profit!0}</td>
					<td>
					   <a href="javascript:void(0);"  onClick="phoneDelete(${phone.phoneId});">删除</a>
					   <#if phone.status == 0><a href="javascript:void(0);"  onClick="phoneSell(${phone.phoneId},${phone.purchasePrice!0});">卖出</a></#if>
					   <#if phone.status == 0> <a href="javascript:void(0);"  onClick="phoneChange(${phone.phoneCode});">转移库存</a></#if>
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