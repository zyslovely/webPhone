$("#phone_delete").click(function(){
	
});

function phoneDelete(id){
	
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'deletePhoneById',id,submitCB);
};

function onpressAdd(){
	window.open("/purchase/add/show/");
};

function submitCB(_flag){
	if(_flag){
	    alert("删除成功");
	}else{
		alert("删除失败");
	}
	location.href=location.href;
};