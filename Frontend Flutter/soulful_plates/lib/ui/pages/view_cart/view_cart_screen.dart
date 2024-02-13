import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../../../constants/size_config.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_sized_box.dart';
import '../../../constants/app_text_styles.dart';
import '../../../constants/enums/view_state.dart';
import '../../../utils/extensions.dart';
import '../../widgets/base_common_widget.dart';
import 'view_cart_controller.dart';

class ViewCartScreen extends GetView<ViewCartController>
    with BaseCommonWidget {
  ViewCartScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("ViewCart"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (ViewCartController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        const Text("ViewCart Screen"),
        12.rVerticalSizedBox(),

      ],
    );
  }
}
