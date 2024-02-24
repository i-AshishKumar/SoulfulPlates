import 'package:flutter/widgets.dart';
import 'package:get/get.dart';

import 'app_colors.dart';

class SizeConfig {
  static MediaQueryData? _mediaQueryData;
  static double? screenWidth;
  static double? screenHeight;
  // static double? _blockSizeHorizontal;
  // static double? _blockSizeVertical;

  static double? _safeAreaWidth;
  static double? _safeAreaHeight;
  static double? safeBlockHorizontal;
  static double? safeBlockVertical;

  // Define the height and width multipliers
  static double? heightMultiplier;
  static double? widthMultiplier;

  void init(BuildContext context, BoxConstraints safeAreaBox) {
    _mediaQueryData = MediaQuery.of(context);
    screenWidth = _mediaQueryData?.size.width;
    screenHeight = _mediaQueryData?.size.height;

    // _blockSizeHorizontal = screenWidth! / 100;
    // _blockSizeVertical = screenHeight! / 100;

    // Calculate the multipliers
    heightMultiplier = screenHeight! / 100;
    widthMultiplier = screenWidth! / 100;

    _safeAreaWidth = safeAreaBox.maxWidth;
    _safeAreaHeight = safeAreaBox.maxHeight;
    safeBlockHorizontal = _safeAreaWidth! / 100;
    safeBlockVertical = _safeAreaHeight! / 100;

    _textScaleFactor = _mediaQueryData?.textScaleFactor;

    // print(
    //     'This is the size configurations screen width/height $screenWidth/$screenHeight');
    // print(
    //     'This is the size configurations safe area width/height $_safeAreaWidth/$_safeAreaHeight');
    // print(
    //     'This is the size configurations safe block area width/height $safeBlockHorizontal/$safeBlockVertical');
    // print(
    //     'This is the size configurations safe block area scaleWidth/scaleHeight ${screenWidth! / uiWidthPx}/${screenHeight! / uiHeightPx} ');
    // print(
    //     'This is text scale factor for headers $_textScaleFactor so ${font14}  ');
    // print('This is vertical size of button 50  ${relativeHeight(6.1)}  ');
  }

  static double? _textScaleFactor;
  static num uiWidthPx = 375;
  static num uiHeightPx = 812;

  static double scaleWidth = screenWidth! / uiWidthPx;

  static double scaleHeight = screenHeight! / uiHeightPx;

  static double scaleText = 1; //scaleWidth;

  static getWidthPercentage(double percentage,
      {double? elseWidthPercentage, double? elseWidth}) {
    double width = Get.width * percentage;
    return Get.width > 720
        ? width
        : (elseWidth ?? Get.width * (elseWidthPercentage ?? 1));
  }

  static getWidth() {
    return Get.width;
  }

  static isTablet() {
    return Get.width > 720;
  }

  //Needs to remove the appTextWidget
  static double setSp(num fontSize) => (fontSize * scaleText);

  static final double font8 = 8 * scaleText / (_textScaleFactor ?? 1);
  static final double font10 = 10 * scaleText / (_textScaleFactor ?? 1);
  static final double font12 = 12 * scaleText / (_textScaleFactor ?? 1);
  static final double font13 = 13 * scaleText / (_textScaleFactor ?? 1);
  static final double font14 = 14 * scaleText / (_textScaleFactor ?? 1);
  static final double font15 = 15 * scaleText / (_textScaleFactor ?? 1);
  static final double font16 = 16 * scaleText / (_textScaleFactor ?? 1);
  static final double font17 = 17 * scaleText / (_textScaleFactor ?? 1);
  static final double font18 = 18 * scaleText / (_textScaleFactor ?? 1);
  static final double font20 = 20 * scaleText / (_textScaleFactor ?? 1);
  static final double font22 = 22 * scaleText / (_textScaleFactor ?? 1);
  static final double font25 = 25 * scaleText / (_textScaleFactor ?? 1);
  static final double font28 = 28 * scaleText / (_textScaleFactor ?? 1);
  static final double font32 = 32 * scaleText / (_textScaleFactor ?? 1);
  static final double font36 = 36 * scaleText / (_textScaleFactor ?? 1);
  static final double font40 = 40 * scaleText / (_textScaleFactor ?? 1);
  static final double font48 = 48 * scaleText / (_textScaleFactor ?? 1);
  static final double font60 = 60 * scaleText / (_textScaleFactor ?? 1);
}

extension ResponsiveIntExtension on int {
  double rHeight() {
    return toDouble();
  }

  Widget rVerticalSizedBox() {
    return SizedBox(
      height: toDouble(),
    );
  }

  Widget rVerticalGreySizedBox() {
    return Container(
      color: AppColor.black5TextColor,
      height: toDouble(),
    );
  }

  Widget rVerticalDarkGreySizedBox() {
    return Container(
      color: AppColor.black4TextColor,
      height: toDouble(),
    );
  }

  Widget rHorizontalSizedBox() {
    return SizedBox(
      width: toDouble(),
    );
  }

  double rWidth() {
    return toDouble();
  }

  double rSize() {
    return toDouble();
  }
}

extension ResponsiveDoubleExtension on double {
  double rHeight() {
    return toDouble();
  }

  double rWidth() {
    return toDouble();
  }

  double rSize() {
    return toDouble();
  }
}
