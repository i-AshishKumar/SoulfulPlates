import 'package:get/get.dart';
import 'package:soulful_plates/model/cart/cart_item_model.dart';
import 'package:soulful_plates/network/network_interfaces/i_dio_singleton.dart';
import 'package:soulful_plates/network/network_utils/api_call.dart';
import 'package:soulful_plates/constants/enums/view_state.dart';
import '../../../controller/base_controller.dart';

class ViewCartController extends BaseController {
  RxList<CartItemModel> cartItems = <CartItemModel>[].obs;

  ViewStateEnum _viewState = ViewStateEnum.idle;

  // Getter for _viewState
  ViewStateEnum get viewState => _viewState;

  // Method to update _viewState and notify listeners
  void setViewState(ViewStateEnum state) {
    _viewState = state;
    update();
  }

  @override
  void onInit() {
    super.onInit();
    fetchCartItems();
  }

  void fetchCartItems() async {
    setLoaderState(ViewStateEnum.busy);
    try {
      final response = await ApiCall().call<List<dynamic>>(
        method: RequestMethod.get,
        endPoint: '/api/cart',
        obj: CartItemModel.empty(),
      );

      // Parse the response
      cartItems.value = List<CartItemModel>.from(response.map((itemData) =>
          CartItemModel.fromJson(itemData as Map<String, dynamic>)));
      setLoaderState(ViewStateEnum.idle);
    } catch (e) {
      setLoaderState(ViewStateEnum.error);
      handleError(e);
    }
  }

  void removeItemFromCart(CartItemModel item) async {
    setLoaderState(ViewStateEnum.busy);
    try {
      await ApiCall().call(
        method: RequestMethod.delete,
        endPoint: '/api/cart/${item.id}',
      );

      cartItems.remove(item);
      setLoaderState(ViewStateEnum.idle);
    } catch (e) {
      setLoaderState(ViewStateEnum.error);
      handleError(e);
    }
  }

  // void updateItemQuantity(CartItemModel item, int quantity) async {
  //   if (quantity < 0) {
  //     return; // Optionally handle this case with an error message to the user
  //   }

  //   setLoaderState(ViewStateEnum.busy);
  //   try {
  //     final updatedItem = await ApiCall().call<CartItemModel>(
  //       method: RequestMethod.patch,
  //       endPoint: '/api/cart/${item.id}',
  //       parameters: {'quantity': quantity},
  //       obj: CartItemModel
  //           .fromJson, // Assuming CartItemModel has a fromJson constructor
  //     );

  //     // Find the item in the list and replace it with the updated item
  //     final index = cartItems.indexWhere((element) => element.id == item.id);
  //     if (index != -1) {
  //       cartItems[index] = updatedItem;
  //       cartItems.refresh(); // This updates the observable list
  //     }
  //     setLoaderState(ViewStateEnum.idle);
  //   } catch (e) {
  //     setLoaderState(ViewStateEnum.error);
  //     handleError(e); // Implement this method to handle errors as needed.
  //   }
  // }

  void handleError(dynamic error) {
    print('Error occurred: $error');
  }
}
