import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/routing/route_names.dart';
import 'package:soulful_plates/ui/pages/view_cart/view_cart_controller.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/size_config.dart';
import '../../widgets/base_common_widget.dart';

class ViewCartScreen extends GetView<ViewCartController> with BaseCommonWidget {
  ViewCartScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("View Cart"),
        ),
        backgroundColor: AppColor.whiteColor,
        floatingActionButton: FloatingActionButton(
            tooltip: "Go to Payment",
            onPressed: () {
              Get.toNamed(cartPaymentViewRoute);
            },
            child: const Icon(
              Icons.payments_outlined,
              size: 24,
              color: AppColor.whiteColor,
            )),
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (ViewCartController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    List<CartItem> cartItems = controller.cartItems;

    // Calculate subtotal
    double subtotal = controller.calculateSubtotal();

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        12.rVerticalSizedBox(),
        Expanded(
          child: ListView.builder(
            itemCount: cartItems.length,
            itemBuilder: (context, index) {
              return CartItemWidget(cartItem: cartItems[index]);
            },
          ),
        ),
        SizedBox(height: 16),
        Padding(
          padding: EdgeInsets.symmetric(horizontal: 16),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              ElevatedButton(
                onPressed: () {
                  // Logic for adding items
                },
                child: Text('Add Items'),
              ),
              ElevatedButton(
                onPressed: () {
                  // Logic for checkout
                },
                child: Text('Checkout'),
              ),
            ],
          ),
        ),
        SizedBox(height: 16),
        Padding(
          padding: EdgeInsets.symmetric(horizontal: 16),
          child: Text(
            'Subtotal: CA\$${subtotal.toStringAsFixed(2)}',
            style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
          ),
        ),
      ],
    );
  }
}

class CartItemWidget extends StatelessWidget {
  final CartItem cartItem;

  CartItemWidget({required this.cartItem});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.all(8.0),
      child: Padding(
        padding: EdgeInsets.all(16.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(cartItem.storeName),
                  SizedBox(height: 8),
                  Text(
                    '${cartItem.quantity} items • CA\$${cartItem.totalPrice.toStringAsFixed(2)}',
                  ),
                  SizedBox(height: 8),
                  Text(cartItem.deliveryAddress),
                ],
              ),
            ),
            Row(
              children: [
                IconButton(
                  icon: Icon(Icons.remove_circle),
                  onPressed: () {
                    // Remove item logic
                    cartItem.decreaseQuantity();
                  },
                ),
                Text(
                  cartItem.quantity.toString(),
                  style: TextStyle(fontSize: 16),
                ),
                IconButton(
                  icon: Icon(Icons.add_circle),
                  onPressed: () {
                    // Add item logic
                    cartItem.increaseQuantity();
                  },
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

// CartItem class
class CartItem {
  final String storeName;
  final double totalPrice;
  final String deliveryAddress;
  int quantity;

  CartItem({
    required this.storeName,
    required this.totalPrice,
    required this.deliveryAddress,
    required this.quantity,
    required int itemCount,
  });

  // Method to decrease the quantity of the item
  void decreaseQuantity() {
    if (quantity > 0) {
      quantity--;
    }
  }

  // Method to increase the quantity of the item
  void increaseQuantity() {
    quantity++;
  }
}
