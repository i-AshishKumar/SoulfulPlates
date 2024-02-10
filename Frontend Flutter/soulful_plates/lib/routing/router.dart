import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/ui/pages/introduction_screen.dart';
import 'package:soulful_plates/ui/pages/splash_screen.dart';

import '../ui/pages/internet_page/internet_page_view.dart';
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
        page: () => const InternetPageView(),
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
