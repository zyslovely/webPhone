//
//  PHViewController.m
//  Phone
//
//  Created by zys on 13-4-30.
//  Copyright (c) 2013年 zys. All rights reserved.
//

#import "PHViewController.h"
#import "PHProfitListViewController.h"
@interface PHViewController ()

@end

@implementation PHViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)profit:(id)sender{
  
  int profitDate=_control.selectedSegmentIndex;
  int shopId=_shopControl.selectedSegmentIndex;
  
  PHProfitListViewController *profitController=[[PHProfitListViewController alloc]initWithIndex:profitDate shopId:shopId];
  [self.navigationController pushViewController:profitController animated:YES];
}


@end
