$("#brandName_add").click(
		function() {
			_brandName = $("#brandName").val();
			if (_brandName == '') {
				alert('请输入品牌名称');
			}
			dwr.engine._execute("http://localhost:8080/WebPhone/dwr",
					'WebPhoneBean', 'addBrand', _brandName, submitCBBrand);
		});

function submitCBBrand(_flag) {
	if (_flag) {
		alert("添加成功");
	} else {
		alert("添加失败");
	}
	location.href = location.href;
};

function phoneReturn(id) {

	dwr.engine._execute("http://localhost:8080/WebPhone/dwr", 'WebPhoneBean',
			'returnPhone', id, submitReturnCB);
};

function submitReturnCB(_flag) {
	if (_flag) {
		alert("退货成功");
	} else {
		alert("退货失败");
	}
	location.href = location.href;
};

function phoneDelete(id) {

	dwr.engine._execute("http://localhost:8080/WebPhone/dwr", 'WebPhoneBean',
			'deletePhoneById', id, submitCB);
};

function phoneSell(id, purchasePrice) {

	jPrompt('请输入卖出价格：', '请输入', '', function(_price) {

		if (_price < purchasePrice && _price != null) {
			jConfirm('卖出价格低于进货价格，确定?', '请确定', function(res) {
				doSell(id, _price);
			});
		} else {

			doSell(id, _price);
		}
	});

};

function doSell(id, sellPrice) {
	dwr.engine._execute("http://localhost:8080/WebPhone/dwr", 'WebPhoneBean',
			'sellPhone', id, sellPrice, function(_flag) {
				if (_flag) {
					alert("卖出成功");
				} else {
					alert("卖出失败");
				}
				location.href = location.href;
			});
}

function onpressAdd() {
	window.open("/purchase/add/show/");
};

function submitCB(_flag) {
	if (_flag) {
		alert("删除成功");
	} else {
		alert("删除失败");
	}
	location.href = location.href;
};

function phonePurchasePriceChange(id) {
	jPrompt('请输入修改价格', '请输入', '', function(_price) {

		if (_price != null) {
			doChangePurchasePrice(id, _price);

		} else {

			doChangePurchasePrice(id, _price);
		}
	});
};

function doChangePurchasePrice(id, _price) {
	dwr.engine._execute("http://localhost:8080/WebPhone/dwr", 'WebPhoneBean',
			'purchasePriceChange', id, _price, function(_flag) {
				if (_flag) {
					alert("修改成功");
				} else {
					alert("修改失败");
				}
				location.href = location.href;
			});
};

function phoneSelledPriceChange(id) {
	jPrompt('请输入修改价格：', '请输入', '', function(_price) {

		if (_price != null) {
			doChangeSelledPrice(id, _price);
		} else {

			doChangeSelledPrice(id, _price);
		}
	});
};

function doChangeSelledPrice(id, _price) {
	dwr.engine._execute("http://shouji.qiqunar.com.cn/WebPhone/dwr/", 'WebPhoneBean',
			'sellPriceChange', id, _price, function(_flag) {
				if (_flag) {
					alert("修改成功");
				} else {
					alert("修改失败");
				}
				location.href = location.href;
			});
}
