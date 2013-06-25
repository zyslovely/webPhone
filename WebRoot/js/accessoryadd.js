$("#accessory_info_add").click(function(){
	_name=$("#accessoryInfo").val();
	
	dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'addAccessoryInfo',_name,submitCB);
});


function submitCB(_flag){
	if(_flag){
	    alert("添加配件类型成功");
	}else{
		alert("添加配件类型失败");
	}
	location.href=_cfg_host+"/accessory/add/show/";
};