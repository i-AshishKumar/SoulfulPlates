import 'package:flutter/material.dart';
//import 'package:flutter_countdown_timer/flutter_countdown_timer.dart';
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
          "Soulful Plates",
          textAlign: TextAlign.center,
          style: TextStyle(
              color: Colors.white,
              fontSize: 25,
              fontWeight: FontWeight.bold), // Center the title
        ),
        // leadingWidth: 35.0,
        backgroundColor: Colors.green.shade800,
        leading: Padding(
          padding: const EdgeInsets.only(left: 15.0),
          child: Image.asset(
            AppIcons.appIconWhite,
          ),
        ),
        actions: [
          PopupMenuButton(
            iconColor: Colors.white,
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
              //10.rVerticalSizedBox(),
              Row(
                children: [
                  Expanded(
                    child: Divider(
                      color: Colors.black,
                      thickness: 1,
                    ),
                  ),
                  Padding(
                    padding: EdgeInsets.symmetric(horizontal: 10),
                    child: Text(
                      'ORDERS',
                      style: TextStyle(
                        fontSize: 25,
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
      constraints: BoxConstraints(maxWidth: 400),
      child: Card(
        color: Colors.green.shade800,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
          side: BorderSide(
              color: Colors.black, width: 1), // Border around the card
        ),
        elevation: 3, // Slight elevation
        child: Flex(
          direction: Axis.vertical,
          children: [
            Column(
              children: [
                Row(
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Text(
                          'Earnings',
                          // textAlign: TextAlign.center,
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 30,
                            color: Colors.white,
                          ),
                        ),
                        8.rVerticalSizedBox(),
                      ],
                    )
                  ],
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            Column(
                              children: [
                                Text(
                                  'Amount',
                                  style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 16,
                                      color: Colors.white),
                                ),
                              ],
                            )
                          ],
                        ),
                        Row(
                          children: [
                            Column(
                              children: [
                                Text(
                                  '\$1000.00 CAD',
                                  style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 24,
                                      color: Colors.white),
                                ),
                              ],
                            )
                          ],
                        )
                      ],
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.end,
                      children: [
                        Row(
                          children: [
                            Column(
                              children: [
                                Text(
                                  'Orders',
                                  style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 16,
                                      color: Colors.white),
                                ),
                              ],
                            )
                          ],
                        ),
                        Row(
                          children: [
                            Column(
                              children: [
                                Text(
                                  '#54',
                                  style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 24,
                                      color: Colors.white),
                                ),
                              ],
                            )
                          ],
                        )
                      ],
                    )
                  ],
                ),
              ],
            )
          ],
        ).paddingAll16(),

        // child: Row(
        //   mainAxisAlignment: MainAxisAlignment.spaceBetween,
        //   crossAxisAlignment: CrossAxisAlignment.start,
        //   children: <Widget>[
        //     // Left side
        //     Column(
        //       crossAxisAlignment: CrossAxisAlignment.start,
        //       children: <Widget>[
        //
        //
        //
        //       ],
        //     ),
        //     // Right side
        //     Column(
        //       crossAxisAlignment: CrossAxisAlignment.end,
        //       children: <Widget>[
        //         // Text(
        //         //   'Today',
        //         //   style: TextStyle(
        //         //       fontWeight: FontWeight.bold, color: Colors.white),
        //         // ),
        //         8.rVerticalSizedBox(),
        //
        //
        //       ],
        //     ),
        //   ],
        // ).paddingAll16(),
      ),
    );
  }
}

class CardTwo extends StatelessWidget {
  const CardTwo({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      // elevation: 3,
      shadowColor: Colors.teal,
      borderRadius: BorderRadius.circular(12),
      child: Container(
        width: double.infinity,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
            side: BorderSide(
                color: Colors.green.shade800,
                width: 1), // Border around the card
          ),
          elevation: 2, // Elevation from top
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    children: [
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
                      )
                    ],
                  ),
                  Column(
                    children: [
                      Row(
                        children: [
                          Column(
                            children: [
                              Text(
                                '5 mins left',
                                style: TextStyle(fontSize: 16),
                              ),
                            ],
                          ),
                          8.rHorizontalSizedBox(),
                          Column(
                            children: [
                              Icon(
                                Icons.hourglass_bottom,
                                color: Colors.grey,
                                // size: 28,
                              ),
                            ],
                          ),
                        ],
                      )
                    ],
                  )
                ],
              ),
              18.rVerticalSizedBox(),
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
                            //fontWeight: FontWeight.bold,
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
                      Text(
                        '123 Main St, City, Country',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                ],
              ),
              18.rVerticalSizedBox(), // SizedBox(height: 16),
              Row(
                children: [
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement call driver functionality
                      },
                      icon: Icon(Icons.phone), // Call icon
                      label: Text(
                        'Call Driver',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10, // Increased button size
                        ),
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(12),
                            side: BorderSide(
                                color: Colors.green.shade800, width: 1)),
                        elevation: 2, // Elevation for button
                      ),
                    ),
                  ),
                  12.rHorizontalSizedBox(), //SizedBox(width: 12),
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement track driver functionality
                      },
                      icon: Icon(Icons.my_location), // Track icon
                      label: Text(
                        'Track Order',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade100,
                        padding: EdgeInsets.symmetric(
                          vertical: 10, // Increased button size
                        ),
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(12),
                            side: BorderSide(
                                color: Colors.green.shade800, width: 1)),
                        elevation: 3, // Elevation for button
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ).paddingAll16(),
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
            side: BorderSide(
                color: Colors.black, width: 1), // Border around the card
          ),
          elevation: 5, // Elevation from top
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
              12.rVerticalSizedBox(), //SizedBox(height: 12),
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
              16.rHorizontalSizedBox(), //SizedBox(height: 16),
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
                      4.rHorizontalSizedBox(), //SizedBox(height: 4),
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
                      4.rVerticalSizedBox(), //SizedBox(height: 4),
                      Text(
                        '123 Main St, City, Country',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                ],
              ),
              16.rVerticalSizedBox(), // SizedBox(height: 16),
              Row(
                children: [
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement call driver functionality
                      },
                      icon: Icon(Icons.phone), // Call icon
                      label: Text(
                        'Call Driver',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade200,
                        padding: EdgeInsets.symmetric(
                          vertical: 10, // Increased button size
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                        elevation: 3, // Elevation for button
                      ),
                    ),
                  ),
                  12.rHorizontalSizedBox(), //SizedBox(width: 12),
                  Expanded(
                    child: ElevatedButton.icon(
                      onPressed: () {
                        // Implement track driver functionality
                      },
                      icon: Icon(Icons.location_on), // Track icon
                      label: Text(
                        'Track Driver',
                        style: TextStyle(fontSize: 16),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: Colors.green.shade200,
                        padding: EdgeInsets.symmetric(
                          vertical: 10, // Increased button size
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                        elevation: 3, // Elevation for button
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ).paddingAll16(),
        ),
      ),
    );
  }
}
