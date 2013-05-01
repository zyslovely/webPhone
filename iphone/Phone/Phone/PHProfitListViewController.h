//
//  PHProfitListViewController.h
//  Phone
//
//  Created by zys on 13-4-30.
//  Copyright (c) 2013年 zys. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "EGORefreshTableHeaderView.h"
#import "UP_EGORefreshTableHeaderView.h"
@interface PHProfitListViewController : UIViewController<EGORefreshTableHeaderDelegate,UP_EGORefreshTableHeaderDelegate>{
  BOOL _isTheEnd;
  BOOL _isLoading;
  BOOL _isLoadOld;
  int  _offset;
  int  _profitDate;
  int  _shopId;
  UP_EGORefreshTableHeaderView *_ego;
  EGORefreshTableHeaderView *_top_Ego;
  NSMutableArray *_dataSource;
}

@property (nonatomic,retain) IBOutlet UITableView *tv;

- (id)initWithIndex:(int)profitDate shopId:(int)shopId;
@end
