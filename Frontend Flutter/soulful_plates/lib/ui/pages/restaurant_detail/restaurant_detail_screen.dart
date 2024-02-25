import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/routing/route_names.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/size_config.dart';
import '../../widgets/base_common_widget.dart';
import 'restaurant_detail_controller.dart';

class RestaurantDetailScreen extends GetView<RestaurantDetailController>
    with BaseCommonWidget {
  RestaurantDetailScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Restaurant Detail"),
        ),
        backgroundColor: AppColor.whiteColor,
        floatingActionButton: FloatingActionButton(
            tooltip: "Go to cart",
            onPressed: () {
              Get.toNamed(viewCartViewRoute);
            },
            child: const Icon(
              Icons.shopping_cart,
              size: 24,
              color: AppColor.whiteColor,
            )),
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (RestaurantDetailController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        const Text("RestaurantDetail Screen"),
        12.rVerticalSizedBox(),
      ],
    );
  }
}
