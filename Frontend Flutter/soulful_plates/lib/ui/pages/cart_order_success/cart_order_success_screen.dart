import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../../../constants/size_config.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_sized_box.dart';
import '../../../constants/app_text_styles.dart';
import '../../../constants/enums/view_state.dart';
import '../../../utils/extensions.dart';
import '../../widgets/base_common_widget.dart';
import 'cart_order_success_controller.dart';

class CartOrderSuccessScreen extends GetView<CartOrderSuccessController>
    with BaseCommonWidget {
  CartOrderSuccessScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("CartOrderSuccess"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (CartOrderSuccessController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        const Text("CartOrderSuccess Screen"),
        12.rVerticalSizedBox(),

      ],
    );
  }
}
