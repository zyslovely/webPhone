//
//  PHAppDelegate.h
//  Phone
//
//  Created by zys on 13-4-30.
//  Copyright (c) 2013年 zys. All rights reserved.
//

#import <UIKit/UIKit.h>

@class PHViewController;

@interface PHAppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (strong, nonatomic) PHViewController *viewController;

@property (retain, nonatomic) UINavigationController *navController;

@end
