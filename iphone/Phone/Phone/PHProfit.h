//
//  PHProfit.h
//  Phone
//
//  Created by zys on 13-4-30.
//  Copyright (c) 2013年 zys. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface PHProfit : NSObject


@property (nonatomic,copy) NSString *phoneCode;
@property (nonatomic,copy) NSString *brandName;
@property (nonatomic,copy) NSString *phoneModel;
@property (nonatomic) double profit;
@property (nonatomic) double purchasePrice;
@property (nonatomic) double soldPrice;
@property (nonatomic,copy) NSString *soldTime;

- (id)initWithJSONDic:(NSDictionary *)jsonDic;
@end
