import 'dart:convert';

import 'package:soulful_plates/network/network_interfaces/generic_model.dart';

class UserProfile extends GenericModel {
  UserProfile({
    String? id,
    String? username,
    String? email,
    String? phoneNumber,
  }) {
    _id = id;
    _username = username;
    _email = email;
    _phoneNumber = phoneNumber;
  }

  UserProfile.fromJson(dynamic json) {
    _id = json['id'];
    _username = json['username'];
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
  String? _username;
  String? _email;
  String? _phoneNumber;
  UserProfile copyWith({
    String? id,
    String? username,
    String? email,
    String? phoneNumber,
  }) =>
      UserProfile(
        id: id ?? _id,
        username: username ?? _username,
        email: email ?? _email,
        phoneNumber: phoneNumber ?? _phoneNumber,
      );
  String? get id => _id;
  String? get username => _username;
  String? get email => _email;
  String? get phoneNumber => _phoneNumber;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = _id;
    map['username'] = _username;
    map['email'] = _email;
    map['phone_number'] = _phoneNumber;
    return map;
  }

  @override
  String toString() {
    return 'UserProfile{_id: $_id, _username: $_username, _email: $_email, _phoneNumber: $_phoneNumber,}';
  }

  @override
  from(json) {
    return UserProfile.fromJson(json);
  }
}
