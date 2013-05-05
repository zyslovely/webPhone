function accessorySell(id,unitPrice){
	
	jPrompt('请输入卖出价格：', '请输入', '',function(_price){
              if(_price<unitPrice){
   	   jConfirm('卖出价格低于进货价格，确定?', '请确定',function(res){
   	   	    doSell(id,_price);
   	   });
	}else{
		
		doSell(id,_price);
	}
    });

};

function doSell(id,sellPrice){
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'sellAccessory',id,sellPrice,submitCB);
}


function submitCB(_flag){
	if(_flag){
	    alert("卖出成功");
	}else{
		alert("卖出失败");
	}
	location.href=location.href;
};

function accessoryDelete(id){
	
dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'deleteAccessory',id,submitDeleteCB);
};

function submitDeleteCB(_flag){
	if(_flag){
	    alert("删除成功");
	}else{
		alert("删除失败");
	}
	location.href=location.href;
};


function onpressAdd(){
	window.open("/accessory/add/show/");
};
