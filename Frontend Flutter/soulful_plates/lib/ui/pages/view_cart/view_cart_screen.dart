import 'package:flutter/material.dart';
import 'package:get/get.dart';
import '../../../constants/size_config.dart';
import '../../../constants/app_colors.dart';
import '../../../constants/app_sized_box.dart';
import '../../../constants/app_text_styles.dart';
import '../../../constants/enums/view_state.dart';
import '../../../utils/extensions.dart';
import '../../widgets/base_common_widget.dart';
import 'view_cart_controller.dart';

class ViewCartScreen extends GetView<ViewCartController> with BaseCommonWidget {
  ViewCartScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("View Cart"),
      ),
      backgroundColor: AppColor.whiteColor,
      body: SafeArea(
        child: GetBuilder(
          init: controller,
          initState: (state) async {},
          builder: (ViewCartController model) {
            return model.viewState == ViewStateEnum.busy
                ? const Center(child: CircularProgressIndicator())
                : getBody(context);
          },
        ),
      ),
    );
  }

  Widget getBody(BuildContext context) {
    List<CartItem> cartItems = [
      CartItem(
        storeName: 'Gujarati Tiffin Service',
        itemCount: 3,
        totalPrice: 39.54,
        deliveryAddress: 'Deliver to 1333 South Park Street',
      ),
      CartItem(
        storeName: 'Non Veg Burger',
        itemCount: 2,
        totalPrice: 25.78,
        deliveryAddress: 'Deliver to 1333 South Park Street',
      ),
    ];

    return Column(
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
      ],
    );
  }
}

class CartItem {
  String storeName;
  int itemCount;
  double totalPrice;
  String deliveryAddress;

  CartItem({
    required this.storeName,
    required this.itemCount,
    required this.totalPrice,
    required this.deliveryAddress,
  });
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
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(cartItem.storeName, style: AppTextStyles.titleStyle),
            SizedBox(
                height: SizeConfig
                    .heightMultiplier), // Adjust the height using SizeConfig
            Text(
                '${cartItem.itemCount} items • CA\$${cartItem.totalPrice.toStringAsFixed(2)}'),
            SizedBox(height: SizeConfig.heightMultiplier),
            Text(cartItem.deliveryAddress),
            SizedBox(height: SizeConfig.heightMultiplier),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: () {
                    // TODO: Navigate to cart detail
                  },
                  child: Text('View cart'),
                ),
                TextButton(
                  onPressed: () {
                    // TODO: Navigate to store
                  },
                  child: Text('View store'),
                  // style: TextButton.styleFrom(primary: AppColor.primaryColor),
                ),
              ],
            )
          ],
        ),
      ),
    );
  }
}
