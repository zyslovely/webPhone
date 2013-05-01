//
//  PHProfitTotal.m
//  Phone
//
//  Created by zys on 13-4-30.
//  Copyright (c) 2013年 zys. All rights reserved.
//

#import "PHProfitTotal.h"

@implementation PHProfitTotal

- (id)initWithJSONDic:(NSDictionary *)jsonDic {
  
  self = [super init];
  if (self) {
    _profitArray=[[NSMutableArray alloc]init];
    NSArray *array=[jsonDic objectForKey:@"profitlist"];
    if(array){
      for(NSDictionary *dic in array){
        PHProfit *profit=[[PHProfit alloc]initWithJSONDic:dic];
        [_profitArray addObject:profit];
      }
    }
    _totalCount=[[jsonDic objectForKey:@"totalcount"]intValue];
    _saleTotal=[[jsonDic objectForKey:@"saletotal"]doubleValue];
    _profitTotal=[[jsonDic objectForKey:@"profittotal"]doubleValue];
  }
  return self;
}
@end
