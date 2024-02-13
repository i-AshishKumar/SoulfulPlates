import 'extensions.dart';

class Validations {
  static bool isValidEmail(String val) {
    return RegExp(
            r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$')
        .hasMatch(val);
  }

  static validatePassword(String value) {
    // Pattern passwordPattern = r'^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#\$&*~₹?/:;,.]).{8,}$';
    Pattern passwordPattern = r'^(?=.*?[A-Z])(?=.*?[0-9]).{8,}$';
    RegExp passwordRegExp = RegExp(passwordPattern.toString());

    if (!passwordRegExp.hasMatch(value)) {
      return false;
    } else {
      return true;
    }
  }

  static String? emailValidator(String? val) {
    if (!val.isNotNullOrEmpty) {
      return "Email address is required";
    } else if (val != null && !isValidEmail(val)) {
      return "Enter valid email address!";
    } else {
      return null;
    }
  }

  static String? mobileValidator(String? val) {
    if (!val.isNotNullOrEmpty) {
      return "Mobile number is required field";
    } else if (val != null && val.length != 10) {
      return "Enter valid mobile number!";
    } else {
      return null;
    }
  }

  static String? passwordValidator(String? val) {
    if (!val.isNotNullOrEmpty) {
      return "Password is required";
    } else {
      return null;
    }
  }

  static String? isNotEmpty(String? val) {
    if (!val.isNotNullOrEmpty) {
      return "Required field.";
    } else {
      return null;
    }
  }

  static String? emptyValidator(String? val, String message) {
    if (!val.isNotNullOrEmpty) {
      return message;
    } else {
      return null;
    }
  }
}
