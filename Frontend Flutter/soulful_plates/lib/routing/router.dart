import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/ui/pages/dashboard/dashboard_binding.dart';
import 'package:soulful_plates/ui/pages/dashboard/dashboard_screen.dart';
import 'package:soulful_plates/ui/pages/forgot_password/forgot_password_binding.dart';
import 'package:soulful_plates/ui/pages/forgot_password/forgot_password_screen.dart';
import 'package:soulful_plates/ui/pages/introduction_screen.dart';
import 'package:soulful_plates/ui/pages/login/login_binding.dart';
import 'package:soulful_plates/ui/pages/login/login_screen.dart';
import 'package:soulful_plates/ui/pages/restaurant_detail/restaurant_detail_binding.dart';
import 'package:soulful_plates/ui/pages/restaurant_detail/restaurant_detail_screen.dart';
import 'package:soulful_plates/ui/pages/sign_up/sign_up_binding.dart';
import 'package:soulful_plates/ui/pages/sign_up/sign_up_screen.dart';
import 'package:soulful_plates/ui/pages/splash_screen.dart';
import 'package:soulful_plates/ui/pages/view_cart/view_cart_binding.dart';
import 'package:soulful_plates/ui/pages/view_cart/view_cart_screen.dart';

import '../ui/pages/create_menu/create_menu_binding.dart';
import '../ui/pages/create_menu/create_menu_screen.dart';
import '../ui/pages/home_seller/home_seller_binding.dart';
import '../ui/pages/home_seller/home_seller_screen.dart';
import '../ui/pages/internet_page/internet_page_view.dart';
import '../ui/pages/live_orders/live_orders_binding.dart';
import '../ui/pages/live_orders/live_orders_screen.dart';
import '../ui/pages/menu_list/menu_list_binding.dart';
import '../ui/pages/menu_list/menu_list_screen.dart';
import '../ui/pages/order_history_seller/order_history_seller_binding.dart';
import '../ui/pages/order_history_seller/order_history_seller_screen.dart';
import '../ui/pages/payout_seller/payout_seller_binding.dart';
import '../ui/pages/payout_seller/payout_seller_screen.dart';
import '../ui/pages/transaction_seller/transaction_seller_binding.dart';
import '../ui/pages/transaction_seller/transaction_seller_screen.dart';
import 'route_names.dart';

Route<dynamic>? generateRoute(RouteSettings settings) {
  switch (settings.name) {
    case internetPageViewRoute:
      return getPageRoutes(
        routeName: internetPageViewRoute,
        settings: settings,
        page: () => const InternetPageView(),
      );

    case splashViewRoute:
      return getPageRoutes(
        routeName: splashViewRoute,
        settings: settings,
        page: () => const SplashScreen(),
      );

    case introductionViewRoute:
      return getPageRoutes(
        routeName: introductionViewRoute,
        settings: settings,
        page: () => const IntroductionViewPage(),
      );

    case loginViewRoute:
      return getPageRoutes(
        routeName: loginViewRoute,
        settings: settings,
        bindings: [LoginBinding()],
        page: () => LoginScreen(),
      );
    case signUpViewRoute:
      return getPageRoutes(
        routeName: signUpViewRoute,
        settings: settings,
        bindings: [SignUpBinding()],
        page: () => SignUpScreen(),
      );
    case forgotPasswordPageViewRoute:
      return getPageRoutes(
        routeName: forgotPasswordPageViewRoute,
        settings: settings,
        bindings: [ForgotPasswordBinding()],
        page: () => ForgotPasswordScreen(),
      );

    case dashboardViewRoute:
      return getPageRoutes(
        routeName: dashboardViewRoute,
        settings: settings,
        bindings: [DashboardBinding()],
        page: () => DashboardScreen(),
      );

    case restaurantDetailViewRoute:
      return getPageRoutes(
        routeName: restaurantDetailViewRoute,
        settings: settings,
        bindings: [RestaurantDetailBinding()],
        page: () => RestaurantDetailScreen(),
      );

    case viewCartViewRoute:
      return getPageRoutes(
        routeName: viewCartViewRoute,
        settings: settings,
        bindings: [ViewCartBinding()],
        page: () => ViewCartScreen(),
      );

    // case storeDetailsViewRoute:
    //   return getPageRoutes(
    //     routeName: storeDetailsViewRoute,
    //     settings: settings,
    //     bindings: [StoreDetailsBinding()],
    //     page: () => StoreDetailsScreen(),
    //   );

    case homeSellerViewRoute:
      return getPageRoutes(
        routeName: homeSellerViewRoute,
        settings: settings,
        bindings: [HomeSellerBinding()],
        page: () => HomeSellerScreen(),
      );

    case liveOrdersViewRoute:
      return getPageRoutes(
        routeName: liveOrdersViewRoute,
        settings: settings,
        bindings: [LiveOrdersBinding()],
        page: () => const LiveOrdersScreen(),
      );

    case menuViewRoute:
      return getPageRoutes(
        routeName: menuViewRoute,
        settings: settings,
        bindings: [MenuListBinding()],
        page: () => MenuListScreen(),
      );

    case createMenuViewRoute:
      return getPageRoutes(
        routeName: createMenuViewRoute,
        settings: settings,
        bindings: [CreateMenuBinding()],
        page: () => CreateMenuScreen(),
      );

    case orderHistorySellerViewRoute:
      return getPageRoutes(
        routeName: orderHistorySellerViewRoute,
        settings: settings,
        bindings: [OrderHistorySellerBinding()],
        page: () => OrderHistorySellerScreen(),
      );

    case transactionHistorySellerViewRoute:
      return getPageRoutes(
        routeName: transactionHistorySellerViewRoute,
        settings: settings,
        bindings: [TransactionSellerBinding()],
        page: () => TransactionSellerScreen(),
      );

    case payoutHistoryViewRoute:
      return getPageRoutes(
        routeName: payoutHistoryViewRoute,
        settings: settings,
        bindings: [PayoutSellerBinding()],
        page: () => PayoutSellerScreen(),
      );
  }
  return null;
}

/// Use This Method To Call Pages
PageRoute getPageRoutes(
    {required String routeName,
    required Function page,
    required RouteSettings settings,
    List<Bindings>? bindings,
    Transition? transition,
    Duration? transitionDuration}) {
  return GetPageRoute(
    transition: transition,
    transitionDuration: transitionDuration ?? const Duration(milliseconds: 300),
    page: () => SafeArea(
      child: page(),
      bottom: false,
      top: false,
    ),
    // transition: Transition.rightToLeft,
    routeName: routeName,
    settings: settings,
    bindings: bindings,
  );
}
