import 'package:get/get.dart';
import 'package:soulful_plates/Utils/Extensions.dart';

class StoreDetails {
  String email;
  String mobile;
  String firstName;
  String street;
  String city;
  String state;
  String postalCode;

  StoreDetails({
    required this.email,
    required this.mobile,
    required this.firstName,
    required this.street,
    required this.city,
    required this.state,
    required this.postalCode,
  });

  String shortName() {
    if (firstName.isNotNullOrEmpty) {
      return firstName.substring(0, 1).capitalizeFirst ?? '';
    }
    return '-';
  }
}
