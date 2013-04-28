function accessorySell(id,unitPrice){
	
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
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'sellAccessory',id,sellPrice,submitCB);
}


function submitCB(_flag){
	if(_flag){
	    alert("出售成功");
	}else{
		alert("出手失败");
	}
	location.href="http://shouji.qiqunar.com.cn/accessory/list/";
};

function onpressAdd(){
	window.open("/accessory/add/show/");
};
