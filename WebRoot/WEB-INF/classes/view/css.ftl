<#-- css文件加载ftl -->
<#if pageName?exists>
     <link type="text/css" rel="stylesheet" href="/css/base.css"/>
	<#switch pageName>
    	<#case "phoneList">
    	    <link type="text/css" rel="stylesheet" href="/css/flexigrid.css" media="all"/>
    	    <link type="text/css" rel="stylesheet" href="/css/alert/jquery.alert.css" media="all"/>
    	<#break>
    	<#case "accessoryList">
    	    <link type="text/css" rel="stylesheet" href="/css/flexigrid.css" media="all"/>
    	    <link type="text/css" rel="stylesheet" href="/css/alert/jquery.alert.css" media="all"/>
    	<#break>
    	<#case "profitList">
    	    <link type="text/css" rel="stylesheet" href="/css/flexigrid.css" media="all"/>
    	<#break>
    	<#case "phoneAdd">
    	    <link type="text/css" rel="stylesheet" href="/css/flexigrid.css" media="all"/>
    	    <link type="text/css" rel="stylesheet" href="/css/alert/jquery.alert.css" media="all"/>
    	<#break>
    </#switch>
</#if>