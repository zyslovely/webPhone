$("#phone_delete").click(function(){
	
});

function phoneDelete(id){
	
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'deletePhoneById',id,submitCB);
};

function phoneSell(id,purchasePrice){
	
	var _sellPrice=prompt("请输入卖出价格","");
	if(_sellPrice<purchasePrice){
		if(confirm("卖去价格低于进货价格，确定?")){
			doSell(id,_sellPrice);
		}
	}else{
		doSell(id,_sellPrice);
	}
	
};

function doSell(id,sellPrice){
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'sellPhone',id,sellPrice,submitCB);
}

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