import 'package:get/get.dart';
import 'package:soulful_plates/ui/pages/home/home_controller.dart';
import 'package:soulful_plates/ui/pages/live_orders/live_orders_controller.dart';
import 'package:soulful_plates/ui/pages/profile/profile_controller.dart';
import 'package:soulful_plates/ui/pages/settings/settings_controller.dart';
import 'package:soulful_plates/ui/pages/transactions/transactions_controller.dart';

import 'dashboard_controller.dart';

class DashboardBinding extends Bindings {
  @override
  void dependencies() {
    Get.lazyPut(() => DashboardController());
    Get.lazyPut(() => HomeController());
    Get.lazyPut(() => LiveOrdersController());
    Get.lazyPut(() => TransactionsController());
    Get.lazyPut(() => ProfileController());
    Get.lazyPut(() => SettingsController());
  }
}
