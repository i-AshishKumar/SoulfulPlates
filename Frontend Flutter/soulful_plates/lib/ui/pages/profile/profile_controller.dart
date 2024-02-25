import 'package:flutter/cupertino.dart';
import 'package:soulful_plates/app_singleton.dart';
import 'package:soulful_plates/model/profile/user_profile.dart';

import '../../../controller/base_controller.dart';

class ProfileController extends BaseController {
  UserProfile? userProfile;

  bool isEditable = true;

  @override
  void onInit() {
    super.onInit();
    userProfile = AppSingleton.loggedInUserProfile;
  }

  TextEditingController emailEditingController = TextEditingController();
  TextEditingController mobileEditingController = TextEditingController();
  TextEditingController firstNameEditingController = TextEditingController();

  FocusNode emailFocusNode = FocusNode();
  FocusNode mobileFocusNode = FocusNode();
  FocusNode firstNameFocusNode = FocusNode();
}
