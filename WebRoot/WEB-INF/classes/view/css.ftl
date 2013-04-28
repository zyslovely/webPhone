<#-- css文件加载ftl -->
<#if pageName?exists>
     <link type="text/css" rel="stylesheet" href="/css/base.css"/>
	<#switch pageName>
    	<#case "phoneList">
    	    <link type="text/css" rel="stylesheet" href="/css/flexigrid.css" media="all"/>
    	<#break>
    	<#case "phoneAdd">
    	    <link type="text/css" rel="stylesheet" href="/css/flexigrid.css" media="all"/>
    	<#break>
    </#switch>
</#if>