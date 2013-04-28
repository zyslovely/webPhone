<!DOCTYPE HTML>
<#escape x as x?html>
<#assign pageName = "accessoryadd" />
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
<h1 style="font-size: 20px;color: red;margin-bottom:30px;">配件添加页面</h1>

<h3>添加配件</h3>
<table border="1" cellspacing="0px">
<thead>
<tr>
<th width="150">配件类型</th><th width="150">配件名称</th><th width="150">数量</th><th width="150">单价</th><th width="150">操作</th>
</tr>
</thead>
<tbody>
<form action="/accessory/add/" method="post" target="_self">
<th>
   <#if accessoryInfos?exists>
      <select name="accessoryInfoId">
      <#list accessoryInfos as info>
         <option value ="${info.id!0}" >${info.accessoryInfoName!""}</option>
      </#list>
      </select>
   </#if>
</th>
<th><input type="text" value="" name="name"/></th>
<th><input type="text" value="" name="count"/></th>
<th><input type="text" value="" name="unitPrice"/></th>
<th><input type="submit" value="提交"/></th>
</form>
</tbody>
</table>


<h3>添加配件类型</h3>
<div>
   <span>输入配件类型:</span>
   <input type="text" value="" id="accessoryInfo"/>
   <input type="button"  id="accessory_info_add" value="确定"/>
</div>

</body>
</html>
</#escape>
		
<#include "js.ftl">
<script>
   <#if succ == 1>
       alert("添加配件成功");
   <#elseif succ == 2>
      alert("添加配件失败");
   </#if>
</script>