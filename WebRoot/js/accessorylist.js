function accessorySell(id, unitPrice) {

	jPrompt('请输入卖出价格：', '请输入', '', function(_price) {
		if (_price < unitPrice) {
			jConfirm('卖出价格低于进货价格，确定?', '请确定', function(res) {
				doSell(id, _price);
			});
		} else {

			doSell(id, _price);
		}
	});

};

function doSell(id, sellPrice) {
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean',
			'sellAccessory', id, sellPrice, submitCB);
}

function submitCB(_flag) {
	if (_flag) {
		alert("卖出成功");
	} else {
		alert("卖出失败");
	}
	location.href = location.href;
};

function onpressAdd() {
	window.open("/accessory/add/show/");
};

function accessoryPurchasePriceChange(id) {
	jPrompt('请输入修改价格', '请输入', '', function(_price) {
		if (_price != null) {
			doChangePurchasePrice(id, _price);
		} else {
			doChangePurchasePrice(id, _price);
		}
	});
};

function doChangePurchasePrice(id, _price) {
	dwr.engine._execute("http://shouji.qiqunar.com.cn/dwr/", 'WebPhoneBean',
			'accessoryPurchasePriceChange', id, _price, function(_flag) {
				if (_flag) {
					alert("修改成功");
				} else {
					alert("修改失败");
				}
				location.href = location.href;
			});
};
