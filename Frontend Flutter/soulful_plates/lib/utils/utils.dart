import 'dart:async';

import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:get/get.dart';

import '../constants/app_colors.dart';
import '../constants/language/language_constants.dart';
import '../constants/size_config.dart';
import '../ui/widgets/base_loading_widget.dart';
import 'extensions.dart';

class Utils {
  static FilteringTextInputFormatter numberFormatter =
      FilteringTextInputFormatter.allow(RegExp("[0-9]"));
  static FilteringTextInputFormatter textFormatter =
      FilteringTextInputFormatter.allow(RegExp("[0-9]"));
  static FilteringTextInputFormatter emailRules =
      FilteringTextInputFormatter.allow(RegExp(r'[+~/>*<!#$%^&()=?/;:,€]'));

  static int timeDuration = 300;
  static Duration duration300 = Duration(milliseconds: timeDuration);

  static Future<bool> checkInternetConnectionAndShowMessage() async {
    ConnectivityResult connectivityResult =
        await Connectivity().checkConnectivity();
    if (connectivityResult == ConnectivityResult.none) {
      showSuccessToast(LanguageConst.internetNotAvailable, true);
      return false;
    } else {
      return true;
    }
  }

  static String padString(String input, int targetLength, String padChar) {
    if (input.length >= targetLength) {
      return input;
    } else {
      int padLength = targetLength - input.length;
      String padding = padChar * padLength;
      return input + padding;
    }
  }

  static showSuccessToast(String message, bool isError) {
    Fluttertoast.showToast(
      gravity: ToastGravity.BOTTOM,
      backgroundColor: isError ? AppColor.redColor : AppColor.blackTextColor,
      textColor: AppColor.whiteTextColor,
      msg: message.capitalizeFirst ?? message,
    );
  }

  static BoxBorder customBorderWidget() {
    return Border.all(
      color: AppColor.primaryColor,
      width: 1.5,
    );
  }

  static Widget emptyRefreshListWidget(
      {required String listEmptyMessage,
      Color? color,
      bool isLoading = false,
      required Function() onTap}) {
    return Container(
      width: double.infinity,
      alignment: Alignment.center,
      child: isLoading
          ? const Center(child: BaseLoadingWidget()).paddingAll16()
          : GestureDetector(
              onTap: onTap,
              child: SizedBox(
                width: double.infinity,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Icon(
                      Icons.refresh,
                      color: AppColor.blackColor,
                      size: 24,
                    ),
                    8.rVerticalSizedBox(),
                    Text(
                      listEmptyMessage,
                      textAlign: TextAlign.center,
                    ),
                  ],
                ),
              ),
            ).paddingAll16(),
    );
  }
}
