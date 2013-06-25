function resetAllInventory(){
	
	dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'resetAllInventory',submitReturnCB);
};


$("#brandName_add").click(function(){
	_brandName=$("#brandName").val();
	if(_brandName==''){
		alert('请输入品牌名称');
	}
	dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'addBrand',_brandName,submitCBBrand);
});

function submitCBBrand(_flag){
	if(_flag){
	    alert("添加成功");
	}else{
		alert("添加失败");
	}
	location.href=location.href;
};

function phoneReturn(id){
	
	dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'returnPhone',id,submitReturnCB);
};

function phoneInventory(id){
	dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'inventoryPhone',id,function cb(_flag){
	if(_flag){
		alert('成功');
	}else{
		alert('失败');
	}
	location.href=_cfg_host+"/phone/list/?inventory=1";
	});
};

function submitReturnCB(_flag){
	if(_flag){
	    alert("成功");
	}else{
		alert("失败");
	}
	location.href=location.href;
};

function phoneDelete(id){
	
	jConfirm('确定要删除么','删除操作',function(_flag){
	if(_flag){
		dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'deletePhoneById',id,submitCB);
	}else{
		
	}
	});
	
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
	dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'sellPhone',id,sellPrice,function(_flag){
	   	if(_flag){
	    alert("卖出成功");
	}else{
		alert("卖出失败");
	}
	location.href=location.href;
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

function phonePurchasePriceChange(id){
	  jPrompt('请输入修改价格', '请输入', '',function(_price){
    	
      if(_price!=null){
   	   doChangePurchasePrice(id,_price);
	
	}else{
		
		doChangePurchasePrice(id,_price);
	}
    });
};

function doChangePurchasePrice(id,_price){
	dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'purchasePriceChange',id,_price,function(_flag){
	   	if(_flag){
	    alert("修改成功");
	}else{
		alert("修改失败");
	}
	location.href=location.href;
	});
};



function phoneSelledPriceChange(id){
	  jPrompt('请输入修改价格：', '请输入', '',function(_price){
    	
    if(_price!=null){
   	    doChangeSelledPrice(id,_price);
	}else{
		
		doChangeSelledPrice(id,_price);
	}
    });
};

function doChangeSelledPrice(id,_price){
	dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'sellPriceChange',id,_price,function(_flag){
	   	if(_flag){
	    alert("修改成功");
	}else{
		alert("修改失败");
	}
	location.href=location.href;
	});
}

function phoneChange(id,opt){
	
	_shopId=$(opt).val();
	
	if(_shopId!=0){
		jConfirm('确定要转移到'+opt.options[_shopId].text+'吗?','转移操作',function(_flag){
	    if(_flag){
		   dwr.engine._execute(_cfg_host+"/dwr/", 'WebPhoneBean', 'changePhoneWithShop',id,_shopId,function(_flag){
		      if(_flag){
		      	alert("转移成功");
		      		location.href=location.href;
		      }else{
		      	alert("转移失败");
		      }
		   });
	    }
	    });
	}
}

