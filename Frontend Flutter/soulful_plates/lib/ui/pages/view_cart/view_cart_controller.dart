import 'package:get/get.dart';
import 'package:soulful_plates/constants/enums/view_state.dart';
import 'package:soulful_plates/ui/pages/view_cart/view_cart_screen.dart';

import '../../../controller/base_controller.dart';

class ViewCartController extends BaseController {
  RxList<CartItem> cartItems = <CartItem>[].obs;

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
    // Fetch cart items when the controller is initialized
    fetchCartItems();
  }

  void fetchCartItems() async {
    setViewState(ViewStateEnum.busy);
    try {
      // Replace this with your actual API call to fetch cart items
      // For now, we'll use dummy data
      List<CartItem> dummyCartItems = [];
      //   CartItem(
      //     name: 'Gujarati Tiffin Service',
      //     details: 'Deliver to 1333 South Park Street',
      //     price: 39.54,
      //     quantity: 3,
      //   ),
      //   CartItem(
      //     name: 'Non Veg Burger',
      //     details: 'Deliver to 1333 South Park Street',
      //     price: 25.78,
      //     quantity: 2,
      //   ),
      // ];
      // Set the fetched cart items
      cartItems.value = dummyCartItems;
      setViewState(ViewStateEnum.idle);
    } catch (e) {
      setViewState(ViewStateEnum.error);
      handleError(e);
    }
  }

  void removeItem(CartItem item) {
    cartItems.remove(item);
  }

  // Method to decrease quantity for a cart item
  void decreaseQuantity(CartItem item) {
    if (item.quantity > 0) {
      item.quantity--;
      update(); // Notify listeners that the state has changed
    }
  }

  // Method to increase quantity for a cart item
  void increaseQuantity(CartItem item) {
    item.quantity++;
    update(); // Notify listeners that the state has changed
  }

  // Method to calculate the subtotal of all items in the cart
  double calculateSubtotal() {
    double subtotal = 0;
    for (var item in cartItems) {
      // Add the price of each item multiplied by its quantity to the subtotal
      // subtotal += item.price * item.quantity;
    }
    return subtotal;
  }

  void handleError(dynamic error) {
    print('Error occurred: $error');
  }
}
