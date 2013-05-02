<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "accessoryList" />
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
<h1 style="font-size: 20px;color: red;margin-bottom:30px;">配件查询页面</h1>
<form action="" method="post" target="_self"/>
<div>
   <span>配件类型:</span>
   <div>
   <#if accessoryInfos?exists>
      <select name="accessoryInfoId">
      <#list accessoryInfos as info>
         <option value ="${info.id!0}" >${info.accessoryInfoName!""}</option>
      </#list>
      </select>
   </#if>
   </div>
   <span>配件名称:</span>
   <input type="text" value="" name="accessoryName"/>
   <input type="submit" value="确定"/>
</div>
</form>

<#if accessorysList?exists>
<p>
   <span style="color:red">当前第${nowPage!0}页，总共${totalPage!0}页</span>。
   <#if extPage gt 0><a href="/accessory/list/?accessoryName=${accessoryName!""}&toPage=${extPage!0}">上一页</a></#if>
   <#if nextPage lt totalPage+1><a href="/accessory/list/?accessoryName=${accessoryName!""}&toPage=${nextPage!0}">下一页</a></#if>
</p>
   <table id="accessory_list_tb" >
			<thead>
				<tr>
				    <th width="100">配件类型</th>
					<th width="100">配件名称</th>
					<th width="150">剩余数量</th>
					<th width="100">单价</th>
					<th width="100">进货日期</th>
					<th width="150">操作</th>
				</tr>
			</thead>
			<tbody>
			
				<#list accessorysList as accessory>
				<tr style="text-align: center;">
				    <td>${accessory.accessoryInfoName!""}</td>
					<td>${accessory.name!""}</td>
					<td>${accessory.count!0}</td>
					<td>${accessory.unitPrice!0}</td>
					<td>${accessory.createTimeStr!""}</td>
					<td>
					   <a href="javascript:void(0);"  onClick="accessorySell(${accessory.id},${accessory.unitPrice!0});">卖出</a>
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
<script>
//<![CDATA[
       jQuery(document).ready(function($) {
			$('#accessory_list_tb').flexigrid({
				 width : 'auto', 
			});
		});
//]]>
</script>