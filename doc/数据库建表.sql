CREATE TABLE TB_Phone_Purchase (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `brandId` bigint(20) NOT NULL DEFAULT '0' COMMENT '品牌',
  `phoneCode` varchar(127) NOT NULL DEFAULT '' COMMENT '手机编码(唯一)',
  `phoneModel` varchar(127) NOT NULL DEFAULT '' COMMENT '手机型号',
  `purchasePrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '进货价格',
  `DecideSellPrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '计划卖出价格',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '纪录创建时间',
  `Status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前状态,0未卖出,1已卖出',
  `operatorId` tinyint(4) NOT NULL DEFAULT '0' COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='手机进货表'


CREATE TABLE `TB_Phone_Selled` (
  `phoneid` bigint(20) NOT NULL DEFAULT '0' COMMENT 'id',
  `SelledPrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '实际卖出价格',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '纪录创建时间',
  `operatorId` tinyint(4) NOT NULL DEFAULT '0' COMMENT '操作人id',
  PRIMARY KEY (`phoneid`)
) ENGINE=InnoDB AUTO_INCREMENT=13580 DEFAULT CHARSET=utf8 COMMENT='手机卖出表'



CREATE TABLE `TB_Phone_Profit` (
  `phoneid` bigint(20) NOT NULL DEFAULT '0' COMMENT 'id',
  `purchasePrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '进货价格',
  `DecideSellPrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '计划卖出价格',
  `SelledPrice` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '实际卖出价格',
  `profit` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '利润',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '纪录创建时间',
  `operatorId` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作人id',
  `shopId` bigint(20) NOT NULL DEFAULT '0' COMMENT '店铺id',
  PRIMARY KEY (`phoneid`)
) ENGINE=InnoDB AUTO_INCREMENT=13580 DEFAULT CHARSET=utf8 COMMENT='手机利润表'


CREATE TABLE `TB_Phone_Brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `brand` varchar(127) NOT NULL DEFAULT '' COMMENT '手机品牌',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13580 DEFAULT CHARSET=utf8 COMMENT='手机品牌表'

CREATE TABLE `TB_Phone_Maintenance` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'id',
  `desc` varchar(512) NOT NULL DEFAULT '' COMMENT '保修描述',
  `finishedTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '保修预计完成时间',
  `Price` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `Status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前状态,0保修中,1已完成保修,2记录删除',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `operatorId` tinyint(4) NOT NULL DEFAULT '0' COMMENT '最后操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13580 DEFAULT CHARSET=utf8 COMMENT='手机保修表'



CREATE TABLE TB_Profile (
UserId bigint(20) NOT NULL  AUTO_INCREMENT  COMMENT '用户id',
UserName varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
Password varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
CreateTime bigint(20) NOT NULL default '0' COMMENT '创建时间',
level int(11) NOT NULL DEFAULT '0' COMMENT '用户登录等级.0是店员,1店长',
PRIMARY KEY  (`UserId`)
)   DEFAULT CHARSET=UTF8 COMMENT '用户信息表';