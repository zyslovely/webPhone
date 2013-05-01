//
//  PHProfitTotal.h
//  Phone
//
//  Created by zys on 13-4-30.
//  Copyright (c) 2013年 zys. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "PHProfit.h"
@interface PHProfitTotal : NSObject

@property (nonatomic,retain) NSMutableArray *profitArray;
@property (nonatomic) double saleTotal;
@property (nonatomic) double profitTotal;
@property (nonatomic) int totalCount;

- (id)initWithJSONDic:(NSDictionary *)jsonDic;
@end
