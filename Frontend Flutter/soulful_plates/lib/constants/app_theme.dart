import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'app_colors.dart';
import 'app_text_styles.dart';
import 'size_config.dart';

class AppTheme {
  static ThemeData getThemeData(ColorThemeModel colorThemeModel) {
    return ThemeData(
      primaryColor: colorThemeModel.primaryColor,
      primaryColorDark: colorThemeModel.primaryColorDark,
      // backgroundColor: colorThemeModel.backgroundColor,
      brightness: colorThemeModel.brightness,
      // errorColor: colorThemeModel.errorColor,
      // textTheme: GoogleFonts.rubikTextTheme(),
      appBarTheme: const AppBarTheme(
        titleTextStyle: AppTextStyles.textStyleAppbar,
        centerTitle: true,
        backgroundColor: AppColor.backgroundColor,
        elevation: 0,
        toolbarTextStyle: AppTextStyles.textStyleAppbar,
        iconTheme: IconThemeData(color: AppColor.blackTextColor),
      ),
      colorScheme: ColorScheme.fromSwatch(
          primarySwatch:
              generateMaterialColorFromColor(colorThemeModel.primaryColor),
          accentColor: colorThemeModel.secondaryColor,
          backgroundColor: colorThemeModel.backgroundColor,
          brightness: colorThemeModel.brightness,
          errorColor: colorThemeModel.errorColor),
      cardTheme: CardTheme(
        shadowColor: colorThemeModel.secondaryColor,
      ),
      fontFamily: colorThemeModel.fontFamily,
    );
  }

  static ThemeData baseTheme = getThemeData(ColorThemeModel(
      primaryColor: AppColor.primaryColor,
      primaryColorDark: AppColor.primaryColor,
      secondaryColor: AppColor.primaryColor,
      brightness: Brightness.light,
      fontFamily: 'Rubik'));

  static MaterialColor generateMaterialColorFromColor(Color color) {
    return MaterialColor(color.value, {
      50: Color.fromRGBO(color.red, color.green, color.blue, 0.1),
      100: Color.fromRGBO(color.red, color.green, color.blue, 0.2),
      200: Color.fromRGBO(color.red, color.green, color.blue, 0.3),
      300: Color.fromRGBO(color.red, color.green, color.blue, 0.4),
      400: Color.fromRGBO(color.red, color.green, color.blue, 0.5),
      500: Color.fromRGBO(color.red, color.green, color.blue, 0.6),
      600: Color.fromRGBO(color.red, color.green, color.blue, 0.7),
      700: Color.fromRGBO(color.red, color.green, color.blue, 0.8),
      800: Color.fromRGBO(color.red, color.green, color.blue, 0.9),
      900: Color.fromRGBO(color.red, color.green, color.blue, 1.0),
    });
  }

  static InputBorder borderWidget({double borderWidth = 1}) {
    return OutlineInputBorder(
      borderSide: BorderSide(
        color: AppColor.primaryColor,
        width: borderWidth,
      ),
    );
  }

  static InputBorder errorBorderWidget({double borderWidth = 1}) {
    return const OutlineInputBorder(
      borderRadius: BorderRadius.all(Radius.circular(4)),
      borderSide: BorderSide(
        width: 1,
        color: Colors.red,
      ),
    );
  }

  static InputBorder noBorder() {
    return InputBorder.none;
  }

  static const SystemUiOverlayStyle whiteSystemOverlayUi = SystemUiOverlayStyle(
      statusBarColor: AppColor.transparent,
      systemNavigationBarColor: AppColor.transparent,
      statusBarIconBrightness: Brightness.light,
      systemNavigationBarIconBrightness: Brightness.light,
      statusBarBrightness: Brightness.dark);

  static const SystemUiOverlayStyle blackSystemOverlayUi = SystemUiOverlayStyle(
      statusBarColor: AppColor.transparent,
      systemNavigationBarColor: AppColor.transparent,
      statusBarIconBrightness: Brightness.dark,
      systemNavigationBarIconBrightness: Brightness.dark,
      statusBarBrightness: Brightness.light);

  static PreferredSize appBarBottomDivider() {
    return PreferredSize(
        preferredSize: const Size.fromHeight(1.0),
        child: Container(
          color: AppColor.black3TextColor,
          height: 1.0,
        ));
  }

  static BoxDecoration boxGradientBackground = BoxDecoration(
      gradient: LinearGradient(colors: [
        AppColor.primaryColor.withOpacity(0.07),
        AppColor.primaryColorLight.withOpacity(0.08)
      ], begin: Alignment.topLeft, end: Alignment.bottomRight),
      borderRadius: BorderRadius.circular(10.rSize()));
}
