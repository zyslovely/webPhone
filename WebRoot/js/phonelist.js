
$("#brandName_add").click(function(){
	_brandName=$("#brandName").val();
	if(_brandName==''){
		alert('请输入品牌名称');
	}
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'addBrand',_brandName,submitCBBrand);
});

function submitCBBrand(_flag){
	if(_flag){
	    alert("添加成功");
	}else{
		alert("添加失败");
	}
	location.href=location.href;
};

$("#phone_delete").click(function(){
	
});

function phoneDelete(id){
	
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'deletePhoneById',id,submitCB);
};

function phoneSell(id,purchasePrice){
	
    jPrompt('请输入卖出价格：', '请输入', '',function(_price){
    	
              if(_price<purchasePrice&&_price!=null){
   	   jConfirm('卖出价格低于进货价格，确定?', '请确定',function(res){
   	   	    doSell(id,_price);
   	   });
	}else{
		
		doSell(id,_price);
	}
    });

	
};


function doSell(id,sellPrice){
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean', 'sellPhone',id,sellPrice,function(_flag){
	   	if(_flag){
	    alert("卖出成功");
	}else{
		alert("卖出失败");
	}
	});
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