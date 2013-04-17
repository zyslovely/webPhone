CREATE TABLE TB_Phone_Purchase (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `brandId` bigint(20) NOT NULL DEFAULT '0' COMMENT '品牌',
  `phoneCode` varchar(127) NOT NULL DEFAULT '' COMMENT '手机编码(唯一)',
  `phoneModel` varchar(127) NOT NULL DEFAULT '' COMMENT '手机型号',
  `purchasePrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '进货价格',
  `DecideSellPrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '计划卖出价格',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '纪录创建时间',
  `Status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前状态,0未卖出,1已卖出',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='手机进货表'


CREATE TABLE `TB_Phone_Selled` (
  `phoneid` bigint(20) NOT NULL DEFAULT '0' COMMENT 'id',
  `SelledPrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '实际卖出价格',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '纪录创建时间',
  PRIMARY KEY (`phoneid`)
) ENGINE=InnoDB AUTO_INCREMENT=13580 DEFAULT CHARSET=utf8 COMMENT='手机卖出表'



CREATE TABLE `TB_Phone_Profit` (
  `phoneid` bigint(20) NOT NULL DEFAULT '0' COMMENT 'id',
  `purchasePrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '进货价格',
  `DecideSellPrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '计划卖出价格',
  `SelledPrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '实际卖出价格',
  `profit` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '利润',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '纪录创建时间',
  PRIMARY KEY (`phoneid`)
) ENGINE=InnoDB AUTO_INCREMENT=13580 DEFAULT CHARSET=utf8 COMMENT='手机利润表'


CREATE TABLE `TB_Phone_Brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `brand` varchar(127) NOT NULL DEFAULT '' COMMENT '手机品牌',
  PRIMARY KEY (`phoneid`)
) ENGINE=InnoDB AUTO_INCREMENT=13580 DEFAULT CHARSET=utf8 COMMENT='手机品牌表'
