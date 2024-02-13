import 'package:flutter/cupertino.dart';
import 'package:soulful_plates/model/profile/user_profile.dart';

import '../../../constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';
import '../../../network/network_interfaces/i_dio_singleton.dart';
import '../../../network/network_utils/api_call.dart';

class SignUpController extends BaseController {
  TextEditingController emailEditingController = TextEditingController();
  TextEditingController mobileEditingController = TextEditingController();
  TextEditingController passwordEditingController = TextEditingController();
  TextEditingController confirmPasswordEditingController =
      TextEditingController();
  TextEditingController firstNameEditingController = TextEditingController();
  TextEditingController lastNameEditingController = TextEditingController();
  TextEditingController addressEditingController = TextEditingController();

  FocusNode emailFocusNode = FocusNode();
  FocusNode mobileFocusNode = FocusNode();
  FocusNode passwordFocusNode = FocusNode();
  FocusNode confirmPasswordFocusNode = FocusNode();
  FocusNode firstNameFocusNode = FocusNode();
  FocusNode lastNameFocusNode = FocusNode();
  FocusNode addressFocusNode = FocusNode();

  bool obscureTwoText = true;
  bool obscureText = true;

  final formKey = GlobalKey<FormState>();

  @override
  void onInit() {
    super.onInit();
  }

  Future<void> signUpUser({data}) async {
    try {
      setLoaderState(ViewStateEnum.busy);

      final UserProfile? userModel = await ApiCall().call<UserProfile>(
          method: RequestMethod.post,
          endPoint: "/api/addUserDetails",
          obj: UserProfile(),
          apiCallType: ApiCallType.simple,
          parameters: data);
      if (userModel != null) {
        // await UserPreference.setValue(
        //     key: SharedPrefKey.userModel.name, value: userModel.toJson());
        // await  UserPreference.setValue(key: SharedPrefKey.token.name, value: userModel.token);
        // AppSingleton.userModel = userModel;
        // setLoaderState(ViewStateEnum.idle);
        // AppSingleton.showToastMessage(message: "Account created successfully.");
        // onWidgetDidBuild(callback: () {
        //   Get.offAllNamed(dashboardViewRoute);
        // });
      } else {
        setLoaderState(ViewStateEnum.idle);
        // AppSingleton.showToastMessage(
        //     message: "Issue while creating user. Please try again later.");
      }
    } catch (e) {
      setLoaderState(ViewStateEnum.idle);
      print('This is error $e');
      // AppSingleton.showToastMessage(message: e.toString());
    }
  }
}
