import 'dart:convert';

import 'package:soulful_plates/network/network_interfaces/generic_model.dart';

class UserProfile extends GenericModel {
  UserProfile({
    String? id,
    String? fullName,
    String? email,
    String? phoneNumber,
  }) {
    _id = id;
    _fullName = fullName;
    _email = email;
    _phoneNumber = phoneNumber;
  }

  UserProfile.fromJson(dynamic json) {
    _id = json['id'];
    _fullName = json['full_name'];
    _email = json['email'];
    _phoneNumber = json['phone_number'];
  }

  static UserProfile? fromRawJson(String str) {
    if (str.isNotEmpty) {
      return UserProfile.fromJson(jsonDecode(str));
    } else {
      return null;
    }
  }

  String toRawJson() => jsonEncode(toJson());

  String? _id;
  String? _fullName;
  String? _email;
  String? _phoneNumber;
  UserProfile copyWith({
    String? id,
    String? fullName,
    String? email,
    String? phoneNumber,
  }) =>
      UserProfile(
        id: id ?? _id,
        fullName: fullName ?? _fullName,
        email: email ?? _email,
        phoneNumber: phoneNumber ?? _phoneNumber,
      );
  String? get id => _id;
  String? get fullName => _fullName;
  String? get email => _email;
  String? get phoneNumber => _phoneNumber;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = _id;
    map['full_name'] = _fullName;
    map['email'] = _email;
    map['phone_number'] = _phoneNumber;
    return map;
  }

  @override
  String toString() {
    return 'UserProfile{_id: $_id, _fullName: $_fullName, _email: $_email, _phoneNumber: $_phoneNumber,}';
  }

  @override
  from(json) {
    return UserProfile.fromJson(json);
  }
}
