import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_icons.dart';
import '../../../constants/app_text_styles.dart';
import '../../../constants/enums/view_state.dart';
import '../../../constants/size_config.dart';
import '../../../routing/route_names.dart';
import '../../../utils/extensions.dart';
import '../../../utils/validator.dart';
import '../../widgets/app_text_field.dart';
import '../../widgets/base_button.dart';
import '../../widgets/base_common_widget.dart';
import 'sign_up_controller.dart';

class SignUpScreen extends GetView<SignUpController> with BaseCommonWidget {
  SignUpScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (SignUpController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Center(
      child: SingleChildScrollView(
        child: Form(
          key: controller.formKey,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Center(
                child: Image.asset(
                  AppIcons.logo,
                  width: 150,
                ),
              ),
              24.rVerticalSizedBox(),
              Text(
                'Create your\naccount to Soulful Plates',
                style: AppTextStyles.textStyleBlack22With700,
              ),
              16.rVerticalSizedBox(),
              Text(
                'First name',
                style: AppTextStyles.textStyleBlackTwo12With400,
              ),
              8.rVerticalSizedBox(),
              AppTextField(
                focusNode: controller.firstNameFocusNode,
                controller: controller.firstNameEditingController,
                validator: (val) =>
                    Validations.emptyValidator(val, "Please enter first name!"),
                onSubmitted: (val) {
                  controller.lastNameFocusNode.requestFocus();
                },
                hintText: 'First name',
              ),
              Text(
                'Last name',
                style: AppTextStyles.textStyleBlackTwo12With400,
              ),
              8.rVerticalSizedBox(),
              AppTextField(
                focusNode: controller.lastNameFocusNode,
                onSubmitted: (val) {
                  controller.emailFocusNode.requestFocus();
                },
                controller: controller.lastNameEditingController,
                validator: (val) =>
                    Validations.emptyValidator(val, "Please enter last name!"),
                hintText: 'Last name',
              ),
              Text(
                'Mobile number',
                style: AppTextStyles.textStyleBlackTwo12With400,
              ),
              8.rVerticalSizedBox(),
              AppTextField(
                focusNode: controller.mobileFocusNode,
                onSubmitted: (val) {
                  controller.passwordFocusNode.requestFocus();
                },
                controller: controller.mobileEditingController,
                validator: Validations.mobileValidator,
                hintText: 'Mobile number',
              ),
              Text(
                'Email address',
                style: AppTextStyles.textStyleBlackTwo12With400,
              ),
              8.rVerticalSizedBox(),
              AppTextField(
                controller: controller.emailEditingController,
                focusNode: controller.emailFocusNode,
                validator: Validations.emailValidator,
                onSubmitted: (val) {
                  controller.passwordFocusNode.requestFocus();
                },
                hintText: 'Email address',
              ),
              Text(
                'Password',
                style: AppTextStyles.textStyleBlackTwo12With400,
              ),
              8.rVerticalSizedBox(),
              AppTextField(
                controller: controller.passwordEditingController,
                focusNode: controller.passwordFocusNode,
                validator: Validations.passwordValidator,
                onSubmitted: (val) {
                  if (controller.formKey.currentState!.validate()) {
                    controller.signUpUser();
                  }
                },
                obscureText: controller.obscureText,
                hintText: 'Password',
                prefixWidget: const Icon(
                  Icons.lock_outline,
                  size: 20,
                  color: AppColor.blackTextColor,
                ),
                suffixWidget: InkWell(
                  onTap: () {
                    controller.obscureText = !controller.obscureText;
                    controller.update();
                  },
                  child: Icon(
                    controller.obscureText
                        ? Icons.visibility_off_rounded
                        : Icons.visibility_rounded,
                    size: 20,
                    color: AppColor.blackTextColor,
                  ),
                ),
              ),
              Text(
                'Confirm Password',
                style: AppTextStyles.textStyleBlackTwo12With400,
              ),
              8.rVerticalSizedBox(),
              AppTextField(
                focusNode: controller.confirmPasswordFocusNode,
                controller: controller.confirmPasswordEditingController,
                obscureText: controller.obscureTwoText,
                validator: (val) {
                  if (val.isNotNullOrEmpty) {
                    if (val ==
                        controller.passwordEditingController.text.trim()) {
                      return null;
                    } else {
                      return "Password and confirm password should be same.";
                    }
                  } else {
                    return "Please enter confirm password.";
                  }
                },
                onSubmitted: (val) {
                  if (controller.formKey.currentState!.validate()) {
                    // * You can use functions of Custom Button Cubit as follow:
                    controller.signUpUser(data: {
                      'email': controller.emailEditingController.text.trim(),
                      'password':
                          controller.passwordEditingController.text.trim(),
                      "first_name":
                          controller.firstNameEditingController.text.trim(),
                      "last_name":
                          controller.lastNameEditingController.text.trim(),
                      "mobile_number":
                          controller.mobileEditingController.text.trim(),
                      "user_type": 'role_buyer',
                    });
                  }
                },
                hintText: 'Confirm Password',
                prefixWidget: const Icon(
                  Icons.lock_outline,
                  size: 20,
                  color: AppColor.blackTextColor,
                ),
                suffixWidget: InkWell(
                  onTap: () {
                    controller.obscureTwoText = !controller.obscureTwoText;
                    controller.update();
                  },
                  child: Icon(
                    controller.obscureTwoText
                        ? Icons.visibility_off_rounded
                        : Icons.visibility_rounded,
                    size: 20,
                    color: AppColor.blackTextColor,
                  ),
                ),
              ),
              Row(
                mainAxisSize: MainAxisSize.max,
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  InkWell(
                      child: Text(
                        'Forgot password?',
                        style: AppTextStyles.textStyleBlue14With400,
                      ),
                      onTap: () async {
                        Get.toNamed(forgotPasswordPageViewRoute);
                      })
                ],
              ),
              40.rVerticalSizedBox(),
              getSignInButton(),
              20.rVerticalSizedBox(),
              Row(
                mainAxisSize: MainAxisSize.max,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  InkWell(
                    onTap: () {
                      Get.offAllNamed(loginViewRoute);
                    },
                    child: Text(
                      'Already have account? Sign in',
                      style: AppTextStyles.textStylePrimary14With400,
                    ),
                  ),
                ],
              ),
              16.rVerticalSizedBox(),
            ],
          ).paddingAll16(),
        ),
      ),
    );
  }

  getSignInButton() {
    if (controller.state == ViewStateEnum.busy) {
      return const Center(child: CircularProgressIndicator());
    }
    return BaseButton(
        text: 'Sign In',
        onSubmit: () async {
          // * You can use functions of Custom Button Cubit as follow:
          if (controller.formKey.currentState!.validate()) {
            // * You can use functions of Custom Button Cubit as follow:
            controller.signUpUser(data: {
              'email': controller.emailEditingController.text.trim(),
              'password': controller.passwordEditingController.text.trim(),
              "first_name": controller.firstNameEditingController.text.trim(),
              "last_name": controller.lastNameEditingController.text.trim(),
              "mobile_number": controller.mobileEditingController.text.trim(),
              "user_type": 'role_buyer',
            });
          }
        });
    // });
  }
}
