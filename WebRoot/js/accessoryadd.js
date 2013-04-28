$("#accessory_info_add").click(function(){
	_name=$("#accessoryInfo").val();
	
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'addAccessoryInfo',_name,submitCB);
});


function submitCB(_flag){
	if(_flag){
	    alert("添加配件类型成功");
	}else{
		alert("添加配件类型失败");
	}
	location.href="http://shouji.qiqunar.com.cn/accessory/add/show/";
};