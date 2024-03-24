class EndPoints {
  static const appToken = 'oauth/token';
  static const login = 'auth/signin';
  static const signup = 'auth/signup';
  static const forgotPassword = 'auth/forget-password';
  static const resetPassword = 'auth/reset-password';

  static const sellerUpdateDetails = 'stores/updateStore';

  static const addAddress = 'users/addresses'; // append userid
  static const getNearByStores = 'users/latlong'; // append userid
  static const updateNotificationStatus =
      'users/toggle-notification'; // append userid
}
