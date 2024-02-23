import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../Utils/Validator.dart';
import '../../../constants/app_colors.dart';
import '../../../constants/app_text_styles.dart';
import '../../widgets/app_text_field.dart';
import '../../widgets/base_button.dart';
import '../../widgets/base_common_widget.dart';
import 'restaurant_detail_controller.dart';

class RestaurantDetailScreen extends GetView<RestaurantDetailController>
    with BaseCommonWidget {
  RestaurantDetailScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Store Details"),
          actions: [
            InkWell(
              onTap: () {
                controller.isEditable = !controller.isEditable;
                controller.update();
              },
              child: const Icon(
                Icons.edit,
                color: AppColor.blackColor,
                size: 24,
              ).paddingHorizontal16(),
            ).visibleWhen(isVisible: !controller.isEditable)
          ],
        ),
        backgroundColor: AppColor.whiteColor,
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
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          16.rVerticalSizedBox(),
          Center(
            child: ClipOval(
              child: SizedBox.fromSize(
                size: const Size.fromRadius(24), // Image radius
                child: CachedNetworkImage(
                    imageUrl: '${controller.userProfile?.image}',
                    height: 48.rSize(),
                    width: 48.rSize(),
                    fit: BoxFit.cover),
              ),
            ).visibleWhen(
                isVisible: controller.userProfile != null &&
                    controller.userProfile!.image.isNotNullOrEmpty),
          ),
          Center(
            child: CircleAvatar(
              backgroundColor: AppColor.profileBackground,
              radius: 48.rSize(),
              child: Text(
                controller.userProfile?.shortName() ?? 'SN',
                style: AppTextStyles.textStyleWhite22With700,
              ),
            ).visibleWhen(
                isVisible: !(controller.userProfile != null &&
                    controller.userProfile!.image.isNotNullOrEmpty)),
          ),
          16.rVerticalSizedBox(),
          Text(
            'Store name',
            style: AppTextStyles.textStyleBlackTwo12With400,
          ),
          8.rVerticalSizedBox(),
          controller.isEditable
              ? AppTextField(
                  focusNode: controller.firstNameFocusNode,
                  controller: controller.firstNameEditingController,
                  validator: (val) => Validations.emptyValidator(
                      val, "Please enter store name!"),
                  onSubmitted: (val) {
                    controller.emailFocusNode.requestFocus();
                  },
                  hintText: 'Store name',
                )
              : Text(
                  controller.userProfile?.username ?? 'Cloud Kitchen',
                  style: AppTextStyles.textStyleBlack16With700,
                ).paddingOnly(bottom: 8),
          16.rVerticalSizedBox(),
          Text(
            'Email address',
            style: AppTextStyles.textStyleBlackTwo12With400,
          ),
          8.rVerticalSizedBox(),
          controller.isEditable
              ? AppTextField(
                  controller: controller.emailEditingController,
                  focusNode: controller.emailFocusNode,
                  validator: Validations.emailValidator,
                  onSubmitted: (val) {
                    controller.mobileFocusNode.requestFocus();
                  },
                  hintText: 'Email address',
                )
              : Text(
                  controller.userProfile?.email ??
                      'cloudKitchen@cloudKitchen.ca',
                  style: AppTextStyles.textStyleBlack14With400,
                ).paddingOnly(bottom: 8),
          16.rVerticalSizedBox(),
          Text(
            'Mobile number',
            style: AppTextStyles.textStyleBlackTwo12With400,
          ),
          8.rVerticalSizedBox(),
          controller.isEditable
              ? AppTextField(
                  focusNode: controller.mobileFocusNode,
                  onSubmitted: (val) {
                    controller.streetFocusNode.requestFocus();
                  },
                  controller: controller.mobileEditingController,
                  validator: Validations.mobileValidator,
                  hintText: 'Mobile number',
                )
              : Text(
                  controller.userProfile?.phoneNumber ?? '+1 1234567890',
                  style: AppTextStyles.textStyleBlack14With400,
                ).paddingOnly(bottom: 8),
          16.rVerticalSizedBox(),
          Text(
            'Street Address',
            style: AppTextStyles.textStyleBlackTwo12With400,
          ),
          8.rVerticalSizedBox(),
          controller.isEditable
              ? AppTextField(
                  focusNode: controller.stateFocusNode,
                  onSubmitted: (val) {
                    controller.stateFocusNode.requestFocus();
                  },
                  controller: controller.streetEditingController,
                  validator: Validations.mobileValidator,
                  hintText: 'Street Address',
                )
              : Text(
                  controller.userProfile?.phoneNumber ?? '1414 Barrington str',
                  style: AppTextStyles.textStyleBlack14With400,
                ).paddingOnly(bottom: 8),
          16.rVerticalSizedBox(),
          Text(
            'Province',
            style: AppTextStyles.textStyleBlackTwo12With400,
          ),
          8.rVerticalSizedBox(),
          controller.isEditable
              ? AppTextField(
                  focusNode: controller.stateFocusNode,
                  onSubmitted: (val) {
                    controller.cityFocusNode.requestFocus();
                  },
                  controller: controller.stateEditingController,
                  validator: Validations.mobileValidator,
                  hintText: 'Province',
                )
              : Text(
                  controller.userProfile?.phoneNumber ?? 'Nova Scotia',
                  style: AppTextStyles.textStyleBlack14With400,
                ).paddingOnly(bottom: 8),
          16.rVerticalSizedBox(),
          Text(
            'City',
            style: AppTextStyles.textStyleBlackTwo12With400,
          ),
          8.rVerticalSizedBox(),
          controller.isEditable
              ? AppTextField(
                  focusNode: controller.cityFocusNode,
                  onSubmitted: (val) {
                    controller.postalCodeFocusNode.requestFocus();
                  },
                  controller: controller.cityEditingController,
                  validator: Validations.mobileValidator,
                  hintText: 'City',
                )
              : Text(
                  controller.userProfile?.phoneNumber ?? 'Halifax',
                  style: AppTextStyles.textStyleBlack14With400,
                ).paddingOnly(bottom: 8),
          16.rVerticalSizedBox(),
          Text(
            'Postal Code',
            style: AppTextStyles.textStyleBlackTwo12With400,
          ),
          8.rVerticalSizedBox(),
          controller.isEditable
              ? AppTextField(
                  focusNode: controller.postalCodeFocusNode,
                  onSubmitted: (val) {},
                  controller: controller.postalCodeEditingController,
                  validator: Validations.mobileValidator,
                  hintText: 'Postal Code',
                )
              : Text(
                  controller.userProfile?.phoneNumber ?? 'B3K2Z2',
                  style: AppTextStyles.textStyleBlack14With400,
                ).paddingOnly(bottom: 8),
          16.rVerticalSizedBox(),
          BaseButton(
              text: "Save",
              onSubmit: () {
                controller.isEditable = !controller.isEditable;
                controller.update();
              }).visibleWhen(isVisible: controller.isEditable),
          16.rVerticalSizedBox(),
        ],
      ).paddingHorizontal24(),
    );
  }
}
