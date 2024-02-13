import 'package:flutter/material.dart';
import 'package:soulful_plates/ui/pages/home/home_screen.dart';
import 'package:soulful_plates/ui/pages/live_orders/live_orders_screen.dart';
import 'package:soulful_plates/ui/pages/profile/profile_screen.dart';
import 'package:soulful_plates/ui/pages/settings/settings_screen.dart';
import 'package:soulful_plates/ui/pages/transactions/transactions_screen.dart';

import '../../../constants/app_colors.dart';
import '../../../controller/base_controller.dart';

class DashboardController extends BaseController {
  int currentIndex = 0;

  List pages = [
    const HomeScreen(),
    const LiveOrdersScreen(),
    const TransactionsScreen(),
    const ProfileScreen(),
    const SettingsScreen(),
  ];

  List<BottomNavigationBarItem> items = const [
    BottomNavigationBarItem(
        label: "Home",
        activeIcon: Icon(Icons.home_outlined, color: AppColor.primaryColor),
        icon: Icon(Icons.home_outlined, color: AppColor.blackTextColor)),
    BottomNavigationBarItem(
        label: "Orders",
        activeIcon:
            Icon(Icons.book_online_outlined, color: AppColor.primaryColor),
        icon: Icon(Icons.book_online_outlined, color: AppColor.blackTextColor)),
    BottomNavigationBarItem(
        label: "Transactions",
        activeIcon: Icon(Icons.payment_rounded, color: AppColor.primaryColor),
        icon: Icon(Icons.payment_rounded, color: AppColor.blackTextColor)),
    BottomNavigationBarItem(
        label: "Profile",
        activeIcon: Icon(Icons.account_circle, color: AppColor.primaryColor),
        icon: Icon(Icons.account_circle, color: AppColor.blackTextColor)),
    BottomNavigationBarItem(
        label: "More",
        activeIcon:
            Icon(Icons.more_horiz_outlined, color: AppColor.primaryColor),
        icon: Icon(
          Icons.more_horiz_outlined,
          color: AppColor.blackTextColor,
        )),
  ];

  void changeTab(int index) {
    currentIndex = index;
    update();
  }
}
