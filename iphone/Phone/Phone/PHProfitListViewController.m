//
//  PHProfitListViewController.m
//  Phone
//
//  Created by zys on 13-4-30.
//  Copyright (c) 2013年 zys. All rights reserved.
//

#import "PHProfitListViewController.h"
#import "SVProgressHUD.h"
#import "ASIHTTPRequest.h"
#import "JSON20.h"
#import "PHProfitTotal.h"
#import "PHProfitCell.h"
#define dataLimit 10
@interface PHProfitListViewController ()

@end

@implementation PHProfitListViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
  self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
  if (self) {
    // Custom initialization
  }
  return self;
}

- (void)viewDidLoad
{
  [super viewDidLoad];
  _dataSource = [[NSMutableArray alloc] init];
  _lastPage = -1;
  _isTheEnd = FALSE;
  _isLoadOld = FALSE;
  _isLoading = FALSE;
  [self download];
}

- (void)didReceiveMemoryWarning
{
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

- (id)initWithIndex:(int)profitDate shopId:(int)shopId{
  self=[super init];
  if(self){
    _profitDate=profitDate;
    _shopId=shopId;
  }
  return self;
}


//时间是用在进行中的列表，weight是用在推荐列表
- (void)download {
  
  if (_isLoading) {
    return;
  }
  _isLoading = TRUE;
  [SVProgressHUD showWithStatus:@"加载中"];
  
  dispatch_async(dispatch_queue_create("download", NULL), ^{
    NSURL *url = [[NSURL alloc] initWithString:[NSString stringWithFormat:@"http://shouji.qiqunar.com.cn/app/profit/list/?key=zystest123&shopId=%d&profitDate=%d",_shopId,_profitDate]];
    ASIHTTPRequest *asiRequest = [ASIHTTPRequest requestWithURL:url];
    [asiRequest startSynchronous];
    NSString *apiResponse = [asiRequest responseString];
    NSDictionary *responseDic = [apiResponse JSONValue];
    NSDictionary *dataDic=[responseDic objectForKey:@"data"];
    NSLog(@"%@",dataDic);
    PHProfitTotal *profitTotal=[[PHProfitTotal alloc]initWithJSONDic:dataDic];
    if (!_isLoadOld) {
      [_dataSource removeAllObjects];
      _offset=0;
    }
    _offset+=[profitTotal.profitArray count];
    dispatch_async(dispatch_get_main_queue(), ^{
      [self.tv reloadData];
      if ([profitTotal.profitArray count] < dataLimit) {
        _isTheEnd = TRUE;
        [_ego setHidden:YES];
      } else {
        [_ego setHidden:NO];
      }
      [self doneLoadingTableViewData];
      [self doneDOWNLoadingTableViewData];
      _isLoading = FALSE;
      [SVProgressHUD dismiss];
    });
  });
}

- (void)addTableHeader {
  
  _top_Ego = [[EGORefreshTableHeaderView alloc] initWithFrame:CGRectMake(0.0f, -45, 320, 45)];
  _top_Ego.delegate = self;
  _top_Ego.backgroundColor = [UIColor clearColor];
  [self.tv setTableHeaderView:_top_Ego];
}

- (void)addTableFooter {
  
  _ego = [[UP_EGORefreshTableHeaderView alloc] initWithFrame:CGRectMake(0.0f, 10, 320, 45) withBackgroundColor:[UIColor whiteColor]];
  _ego.delegate = self;
  _ego.backgroundColor = [UIColor clearColor];
  [self.tv setTableFooterView:_ego];
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
  
  return 1;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
  
  return 170;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
  
  return [_dataSource count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
  
  
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
  
 
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
  
  if (!_isTheEnd) {
    [_ego egoRefreshScrollViewDidScroll:scrollView];
  }
  [_top_Ego egoRefreshScrollViewDidScroll:scrollView];
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView {
  
}

- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView {
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate {
  
  if (!_isTheEnd) {
    [_ego egoRefreshScrollViewDidEndDragging:scrollView];
  }
  [_top_Ego egoRefreshScrollViewDidEndDragging:scrollView];
  if (!decelerate) {
    //在滚动停止是加载图片
  }
  
}

#pragma mark - EGO delegate
- (void)reloadTableViewDataSource {
  
  if (!_isTheEnd) {
    _isLoadOld = TRUE;
    [self download];
  }
}

- (void)doneLoadingTableViewData {
  //  model should call this when its done loading
  if (!_isTheEnd) {
    [_ego egoRefreshScrollViewDataSourceDidFinishedLoading:self.tv];
  }
}

- (void)egoRefreshTableHeaderDidTriggerRefresh:(UP_EGORefreshTableHeaderView *)view {
  
  [self reloadTableViewDataSource];
}

- (BOOL)egoRefreshTableHeaderDataSourceIsLoading:(UP_EGORefreshTableHeaderView *)view {
  
  return _isLoading; // should return if data source model is reloading
}

#pragma mark - DownEGO
- (void)doneDOWNLoadingTableViewData {
  //  model should call this when its done loading
  [_top_Ego egoRefreshScrollViewDataSourceDidFinishedLoading:self.tv];
  
}

- (void)downEGOReload {
  
  if (!_isLoading) {
    _isLoadOld = FALSE;
    _isTheEnd = FALSE;
    _offset=-1;
    [self download];
  }
}

- (void)down_egoRefreshTableHeaderDidTriggerRefresh:(EGORefreshTableHeaderView *)view {
  
  [self downEGOReload];
}

- (BOOL)down_egoRefreshTableHeaderDataSourceIsLoading:(EGORefreshTableHeaderView *)view {
  
  return _isLoading; // should return if data source model is reloading
}

@end
