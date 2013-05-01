//
//  PHProfitCell.h
//  Phone
//
//  Created by zys on 13-4-30.
//  Copyright (c) 2013年 zys. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PHProfitCell : UITableViewCell

@property (nonatomic,retain) IBOutlet UILabel *modelLabel;
@property (nonatomic,retain) IBOutlet UILabel *codeLabel;
@property (nonatomic,retain) IBOutlet UILabel *profitLabel;
@property (nonatomic,retain) IBOutlet UILabel *purchaseLabel;
@property (nonatomic,retain) IBOutlet UILabel *soldLabel;
@property (nonatomic,retain) IBOutlet UILabel *soldTimeLabel;

@end
