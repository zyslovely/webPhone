$("#phone_delete").click(function(){
	_phoneCode=$("#phone_delete_text").val();
	dwr.engine._execute("http://127.0.0.1/WebPhone", 'WebPhoneBean', 'deletePhoneByCode',_phoneCode,submitCB);
});


function submitCB(_flag){
	if(_flag){
	    alert("删除成功");
	}else{
		alert("删除失败");
	}
	location.href=location.href;
};