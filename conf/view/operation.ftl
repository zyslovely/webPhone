<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "operation" />
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
<h1 style="font-size: 20px;color: red;margin-bottom:30px;">手机利操作查询页面</h1>

<form action="/operation/list/" method="post" target="_self" >
<div style="margin-right:10px;">
    <div>
        <span>按日期进行查询</span>
        <input class="Wdate" name="beginDate" type="text" onClick="WdatePicker()" <#if beginDate?exists>value="${beginDate!""}"</#if>>
        <span style="margin:0 10px;">到</span>
        <input class="Wdate" name="endDate" type="text" onClick="WdatePicker()" <#if endDate?exists>value="${endDate!""}"</#if>>
        <input type="radio" name="type" value="1" <#if type==1>checked</#if> /> 删除手机记录
        <input type="radio" name="type" value="0" <#if type==0>checked</#if>/>  转移手机记录
        <input type="radio" name="type" value="2" <#if type==0>checked</#if>/>  退回手机记录
    </div>
    <input type="submit" value="提交"/>
</div>

</form>

<#if operationList?exists>

   <table id="profit_list_tb" >
			<thead>
				<tr>
				    <th width="100">时间</th>
				    <th width="500">内容</th>
				</tr>
			</thead>
			<tbody>
			   <#list operationList as operation>
				<tr >
				    <td>${operation.createTimeStr!""}</td>
				    <td>${operation.comment!""}</td>
					
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

</script>