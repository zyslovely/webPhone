//
//  PHProfit.m
//  Phone
//
//  Created by zys on 13-4-30.
//  Copyright (c) 2013年 zys. All rights reserved.
//

#import "PHProfit.h"

@implementation PHProfit

- (id)initWithJSONDic:(NSDictionary *)jsonDic {
  
  self = [super init];
  if (self) {
    _brandName=[[jsonDic objectForKey:@"brandname"]copy];
    _phoneCode=[[jsonDic objectForKey:@"phonecode"]copy];
    _phoneModel=[[jsonDic objectForKey:@"phonemodel"]copy];
    _profit=[[jsonDic objectForKey:@"profit"]doubleValue];
    _purchasePrice=[[jsonDic objectForKey:@"purchaseprice"]doubleValue];
    _soldPrice=[[jsonDic objectForKey:@"selledprice"]doubleValue];
    _soldTime=[[jsonDic objectForKey:@"selledtimestr"]copy];
  }
  return self;
}
@end
