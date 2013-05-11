<#-- css文件加载ftl -->
<#if pageName?exists>
     <link type="text/css" rel="stylesheet" href="/css/base.css"/>
     <link type="text/css" rel="stylesheet" href="/css/flexigrid.css" media="all"/>
	<#switch pageName>
    	<#case "phoneList">
    	    
    	    <link type="text/css" rel="stylesheet" href="/css/alert/jquery.alert.css" media="all"/>
    	<#break>
    	<#case "accessoryList">
    	    <link type="text/css" rel="stylesheet" href="/css/alert/jquery.alert.css" media="all"/>
    	<#break>
    	<#case "profitList">
    	<#break>
    	<#case "phoneAdd">
    	    <link type="text/css" rel="stylesheet" href="/css/alert/jquery.alert.css" media="all"/>
    	<#break>
    	<#case "accessoryprofitList">
    	<#break>
    </#switch>
</#if>