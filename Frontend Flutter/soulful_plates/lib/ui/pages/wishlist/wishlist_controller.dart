import 'package:soulful_plates/app_singleton.dart';
import 'package:soulful_plates/model/location/address_model.dart';

import '../../../constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';
import '../../../model/data_model.dart';
import '../../../network/network_interfaces/end_points.dart';
import '../../../network/network_interfaces/i_dio_singleton.dart';
import '../../../network/network_utils/api_call.dart';
import '../../../utils/pagination_utils.dart';
import '../../../utils/utils.dart';

class WishlistController extends BaseController
    with PaginationUtils<DataModel> {
  @override
  void onInit() {
    super.onInit();
    initPagination();
  }

  updateLoader(ViewStateEnum state) {
    if (pageNo >= 1) {
      moreLoading = state;
    } else {
      setLoaderState(state);
    }
  }

  void getDataFromAPI() async {
    updateLoader(ViewStateEnum.busy);
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getAddress() async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<AddressModel>(
      method: RequestMethod.get,
      endPoint:
          "${EndPoints.addAddress}/${AppSingleton.loggedInUserProfile?.id}",
      obj: AddressModel(),
      apiCallType: ApiCallType.seller,
    );
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void getNearByStores() async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call<AddressModel>(
      method: RequestMethod.get,
      endPoint: "${EndPoints.getNearByStores}/${6}/201",
      obj: AddressModel(),
      apiCallType: ApiCallType.seller,
    );
    print("Response $response ");
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void addAddress({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint:
            "${EndPoints.addAddress}/${AppSingleton.loggedInUserProfile?.id}",
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("Address created successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while creating Address. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void updateAddress({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.post,
        endPoint:
            "${EndPoints.addAddress}/${AppSingleton.loggedInUserProfile?.id}/${data['id']}",
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("Address update successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while update Address. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void deleteAddress({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.delete,
        endPoint:
            "${EndPoints.addAddress}/${AppSingleton.loggedInUserProfile?.id}/${data['id']}",
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("Address delete successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while delete Address. Please try again later.", false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  void updateNotificationStatus({data}) async {
    updateLoader(ViewStateEnum.busy);
    var response = await ApiCall().call(
        method: RequestMethod.put,
        endPoint:
            "${EndPoints.updateNotificationStatus}/${AppSingleton.loggedInUserProfile?.id}",
        apiCallType: ApiCallType.seller,
        parameters: data);
    print("Response $response ");
    if (response != null && response['code'] == 1) {
      Utils.showSuccessToast("updateNotificationStatus successfully.", true);
    } else {
      setLoaderState(ViewStateEnum.idle);
      Utils.showSuccessToast(
          "Issue while updateNotificationStatus. Please try again later.",
          false);
    }
    updateLoader(ViewStateEnum.idle);
    update();
  }

  @override
  bool isLoading() {
    return state == ViewStateEnum.busy || moreLoading == ViewStateEnum.busy;
  }

  @override
  void fetchData() {
    getDataFromAPI();
    update();
  }

  @override
  void loadMore() {
    getDataFromAPI();
    update();
  }
}
