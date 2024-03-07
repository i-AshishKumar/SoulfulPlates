// import 'package:flutter/material.dart';
// import 'package:get/get.dart';
//
// import '../../../constants/app_colors.dart';
// import '../../../constants/app_sized_box.dart';
// import '../../../constants/app_text_styles.dart';
// import '../../../constants/enums/view_state.dart';
// import '../../../constants/size_config.dart';
// import '../../../utils/extensions.dart';
// import '../../widgets/base_common_widget.dart';
// import 'home_seller_controller.dart';
//
// class HomeSellerScreen extends GetView<HomeSellerController>
//     with BaseCommonWidget {
//   const HomeSellerScreen({Key? key}) : super(key: key);
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//         appBar: AppBar(
//           title: const Text("Home Seller"),
//         ),
//         backgroundColor: AppColor.whiteColor,
//         body: SafeArea(
//           child: GetBuilder(
//             init: controller,
//             initState: (state) async {},
//             builder: (HomeSellerController model) {
//               return getBody(context);
//             },
//           ),
//         ));
//   }
//
//   Widget getBody(BuildContext context) {
//     return Column(
//       children: [
//         12.rVerticalSizedBox(),
//         Expanded(
//           child: Stack(children: [
//             controller.dataList.isNotEmpty
//                 ? RefreshIndicator(
//                     onRefresh: () async {
//                       controller.resetPagination();
//                     },
//                     child: NotificationListener<ScrollNotification>(
//                         onNotification: (scrollNotification) {
//                           if (scrollNotification.metrics.pixels >=
//                                   scrollNotification.metrics.maxScrollExtent &&
//                               !controller.hasReachedMax &&
//                               !controller.isLoading()) {
//                             controller.pageNo = (controller.pageNo + 1);
//                             controller.loadMore();
//                           }
//                           return false;
//                         },
//                         child: ListView.separated(
//                           padding: EdgeInsets.zero,
//                           shrinkWrap: true,
//                           physics: const AlwaysScrollableScrollPhysics(),
//                           itemCount: controller.dataList.length + 1,
//                           separatorBuilder: (context, index) {
//                             return 2.rVerticalGreySizedBox();
//                           },
//                           itemBuilder: (context, index) {
//                             if (index < controller.dataList.length) {
//                               return InkWell(
//                                 onTap: () async {
//                                   //todo tap on the item
//                                 },
//                                 child: //todo change widget with item widget
//                                     Text("Item number $index")
//                                         .paddingAllDefault(),
//                               );
//                             } else if (controller.moreLoading ==
//                                 ViewStateEnum.busy) {
//                               return controller.loadMoreLoader(
//                                   color: AppColor.blackColor);
//                             } else {
//                               return AppSizedBox.sizedBox0;
//                             }
//                           },
//                         )),
//                   )
//                 : Center(
//                     child: GestureDetector(
//                       behavior: HitTestBehavior.opaque,
//                       onTap: () {
//                         controller.resetPagination();
//                       },
//                       child: Column(
//                         mainAxisAlignment: MainAxisAlignment.center,
//                         crossAxisAlignment: CrossAxisAlignment.center,
//                         children: [
//                           Icon(
//                             Icons.refresh_outlined,
//                             size: 24.rSize(),
//                             color: AppColor.primaryColor,
//                           ),
//                           Text(
//                             'No data available!',
//                             style: AppTextStyles.textStyleBlack16With400,
//                           ),
//                         ],
//                       ).paddingAll12(),
//                     ),
//                   ),
//             controller.state == ViewStateEnum.busy
//                 ? const Center(child: CircularProgressIndicator())
//                 : AppSizedBox.sizedBox0
//           ]).paddingSymmetricSide(vertical: 8, horizontal: 16),
//         ),
//       ],
//     );
//   }
// }

import 'package:flutter/material.dart';
// import 'package:flutter_countdown_timer/flutter_countdown_timer.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/routing/route_names.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../constants/app_icons.dart';
import '../../widgets/base_common_widget.dart';
import '../order_detail/order_detail_screen.dart';
import 'home_seller_controller.dart';

class HomeSellerScreen extends GetView<HomeSellerController>
    with BaseCommonWidget {
  const HomeSellerScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Home",
          textAlign: TextAlign.center, // Center the title
        ),
        leading: Image.asset(
          AppIcons.logo,
          fit: BoxFit.contain,
          // width: 16,
          // height: 16,
        ),
        actions: [
          PopupMenuButton(
            itemBuilder: (BuildContext context) => <PopupMenuEntry>[
              PopupMenuItem(
                child: ListTile(
                  leading: Icon(Icons.history),
                  title: Text('Transaction History'),
                  onTap: () {
                    // Navigate to the Restaurant Details Screen
                    //Get.to(() => restaurantDetailViewRoute());
                    Get.toNamed(transactionHistorySellerViewRoute);
                  },
                ),
              ),
              PopupMenuItem(
                child: ListTile(
                  leading: Icon(Icons.store),
                  title: Text('Store Details'),
                  onTap: () {
                    // Navigate to the View Cart Screen
                    //Get.to(() => ViewCartScreen());
                    Get.toNamed(storeDetailsViewRoute);
                  },
                ),
              ),
            ],
          ),
        ],
      ),
      backgroundColor: Colors.white,
      body: SafeArea(
        child: ListView(children: [
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              20.rVerticalSizedBox(),
              CardOne(),
              20.rVerticalSizedBox(),
              Row(
                children: [
                  Expanded(
                    child: Divider(
                      color: Colors.black,
                      thickness: 1,
                    ),
                  ),
                  Padding(
                    padding: EdgeInsets.symmetric(horizontal: 8),
                    child: Text(
                      'Orders',
                      style: TextStyle(
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Cambria',
                      ),
                    ),
                  ),
                  Expanded(
                    child: Divider(
                      color: Colors.black,
                      thickness: 1,
                    ),
                  ),
                ],
              ),
              10.rVerticalSizedBox(),
              20.rVerticalSizedBox(),
              CardTwo(),
              20.rVerticalSizedBox(),
              CardThree(),
            ],
          ).paddingAll16(),
        ]),
      ),
    );
  }
}

class CardOne extends StatelessWidget {
  const CardOne({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: 150,
      constraints: BoxConstraints(maxWidth: 400),
      child: Card(
        color: Colors.green.shade200,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
        ),
        elevation: 2, // Slight elevation
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              // Left side
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(
                    'Total Collected',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  8.rVerticalSizedBox(),
                  Text(
                    '1000.00 CAD',
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 18,
                    ),
                  ),
                  12.rVerticalSizedBox(),
                  Text(
                    '54',
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: 18,
                    ),
                  ),
                ],
              ),
              // Right side
              Column(
                crossAxisAlignment: CrossAxisAlignment.end,
                children: <Widget>[
                  Text(
                    'Today',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  8.rVerticalSizedBox(),
                  Text(
                    'Amount',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  12.rVerticalSizedBox(),
                  Text(
                    'Number of Orders',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class CardTwo extends StatelessWidget {
  const CardTwo({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      elevation: 3,
      shadowColor: Colors.teal,
      borderRadius: BorderRadius.circular(12),
      child: Container(
        width: double.infinity,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
          elevation: 0,
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Container(
                  padding: EdgeInsets.symmetric(
                    vertical: 8,
                    horizontal: 16,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.orange.shade500,
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(12),
                      bottomRight: Radius.circular(12),
                    ),
                  ),
                  child: Text(
                    'Food Preparing',
                    style: TextStyle(color: Colors.white),
                  ),
                ),
                SizedBox(height: 12),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      '5 mins left',
                      style: TextStyle(fontSize: 16),
                    ),
                    Icon(
                      Icons.hourglass_bottom,
                      color: Colors.grey,
                      size: 28,
                    ),
                  ],
                ),
                SizedBox(height: 16),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Order ID',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        SizedBox(height: 4),
                        GestureDetector(
                          onTap: () {
                            // Navigate to order detail screen
                            Navigator.push(
                              context,
                              MaterialPageRoute(
                                builder: (context) => OrderDetailScreen(),
                              ),
                            );
                          },
                          child: Text(
                            '2141',
                            style: TextStyle(
                              color: Colors.black,
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ],
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Address',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        SizedBox(height: 4),
                        Text(
                          '123 Main St, City, Country',
                          style: TextStyle(fontSize: 16),
                        ),
                      ],
                    ),
                  ],
                ),
                SizedBox(height: 16),
                Row(
                  children: [
                    Expanded(
                      child: ElevatedButton(
                        onPressed: () {
                          // Implement call driver functionality
                        },
                        child: Text(
                          'Call Driver',
                          style: TextStyle(fontSize: 16),
                        ),
                        style: ElevatedButton.styleFrom(
                          primary: Colors.green.shade200,
                          padding: EdgeInsets.symmetric(
                            vertical: 16,
                          ),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(12),
                          ),
                        ),
                      ),
                    ),
                    SizedBox(width: 12),
                    Expanded(
                      child: ElevatedButton(
                        onPressed: () {
                          // Implement track driver functionality
                        },
                        child: Text(
                          'Track Driver',
                          style: TextStyle(fontSize: 16),
                        ),
                        style: ElevatedButton.styleFrom(
                          primary: Colors.green.shade200,
                          padding: EdgeInsets.symmetric(
                            vertical: 16,
                          ),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(12),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class CardThree extends StatelessWidget {
  const CardThree({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      elevation: 3,
      shadowColor: Colors.teal,
      borderRadius: BorderRadius.circular(12),
      child: Container(
        width: double.infinity,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
          elevation: 0,
          child: Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Container(
                  padding: EdgeInsets.symmetric(
                    vertical: 8,
                    horizontal: 16,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.purple.shade400,
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(12),
                      bottomRight: Radius.circular(12),
                    ),
                  ),
                  child: Text(
                    'Food Preparing',
                    style: TextStyle(color: Colors.white),
                  ),
                ),
                SizedBox(height: 12),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      '5 mins left',
                      style: TextStyle(fontSize: 16),
                    ),
                    Icon(
                      Icons.hourglass_empty_outlined,
                      color: Colors.grey,
                      size: 28,
                    ),
                  ],
                ),
                SizedBox(height: 16),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Order ID',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        SizedBox(height: 4),
                        GestureDetector(
                          onTap: () {
                            // Navigate to order detail screen
                            Navigator.push(
                              context,
                              MaterialPageRoute(
                                builder: (context) => OrderDetailScreen(),
                              ),
                            );
                          },
                          child: Text(
                            '2151',
                            style: TextStyle(
                              color: Colors.black,
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ],
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Address',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        SizedBox(height: 4),
                        Text(
                          '123 Main St, City, Country',
                          style: TextStyle(fontSize: 16),
                        ),
                      ],
                    ),
                  ],
                ),
                SizedBox(height: 16),
                Row(
                  children: [
                    Expanded(
                      child: ElevatedButton(
                        onPressed: () {
                          // Implement call driver functionality
                        },
                        child: Text(
                          'Call Driver',
                          style: TextStyle(fontSize: 16),
                        ),
                        style: ElevatedButton.styleFrom(
                          primary: Colors.green.shade200,
                          padding: EdgeInsets.symmetric(
                            vertical: 16,
                          ),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(12),
                          ),
                        ),
                      ),
                    ),
                    SizedBox(width: 12),
                    Expanded(
                      child: ElevatedButton(
                        onPressed: () {
                          // Implement track driver functionality
                        },
                        child: Text(
                          'Track Driver',
                          style: TextStyle(fontSize: 16),
                        ),
                        style: ElevatedButton.styleFrom(
                          primary: Colors.green.shade200,
                          padding: EdgeInsets.symmetric(
                            vertical: 16,
                          ),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(12),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
