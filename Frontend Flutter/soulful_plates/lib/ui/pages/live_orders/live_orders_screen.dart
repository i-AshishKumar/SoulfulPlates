import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/size_config.dart';
import '../../widgets/base_common_widget.dart';
import 'live_orders_controller.dart';

class LiveOrdersScreen extends GetView<LiveOrdersController>
    with BaseCommonWidget {
  const LiveOrdersScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("LiveOrders"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (LiveOrdersController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        const Text("LiveOrders Screen"),
        12.rVerticalSizedBox(),
      ],
    );
  }
}
