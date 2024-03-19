import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/Utils/Extensions.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_icons.dart';
import '../../../constants/size_config.dart';
import '../../widgets/base_common_widget.dart';
import 'transactions_controller.dart';

class TransactionsScreen extends GetView<TransactionsController>
    with BaseCommonWidget {
  const TransactionsScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Transactions"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (TransactionsController model) {
              return getBody(context);
            },
          ),
        ));
  }

//   Widget getBody(BuildContext context) {
//     return Column(
//       children: [
//         12.rVerticalSizedBox(),
//         const Text("Transactions Screen"),
//         12.rVerticalSizedBox(),
//       ],
//     );
//   }
// }

  Widget getBody(BuildContext context) {
    return ListView(
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            10.rVerticalSizedBox(),
            CardOne(),
            Padding(
              padding: EdgeInsets.all(14.0),
              child: Expanded(
                child: Container(
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(8.0),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey.withOpacity(0.3),
                        spreadRadius: 2,
                        blurRadius: 5,
                        offset: Offset(0, 3), // changes position of shadow
                      ),
                    ],
                  ),
                  child: TextField(
                    decoration: InputDecoration(
                      hintText: 'Search',
                      prefixIcon: Icon(Icons.search),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(8.0),
                        borderSide: BorderSide(
                          color: Colors.white, // specify border color here
                        ),
                      ),
                      focusedBorder: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(8.0),
                        borderSide: BorderSide(
                          color:
                              Colors.blue, // specify focused border color here
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ),
            10.rVerticalSizedBox(),
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
                    'Invoice',
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
            CardTwo(),
          ],
        ).paddingAll16(),
      ],
    );
  }
}

class CardOne extends StatelessWidget {
  const CardOne({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(8),
        gradient: LinearGradient(
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
          colors: [
            AppColor.emeraldGreen,
            Colors.green.shade700.withOpacity(0.6)
          ], // Change colors as needed
        ),
      ),
      width: double.infinity,
      constraints: BoxConstraints(maxWidth: 500),
      child: Card(
        color: Colors.transparent, // Set card color to transparent
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
          side: BorderSide(
            color: Colors.black,
            width: 1,
          ),
        ),
        elevation: 1,
        child: Flex(
          direction: Axis.vertical,
          children: [
            Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Text(
                          '12',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 30,
                            color: Colors.white,
                          ),
                        ),
                        SizedBox(
                            height: 12), // Add some space between the two texts
                        Text(
                          'Paid',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 16,
                            color: Colors.white,
                          ),
                        ),
                      ],
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Text(
                          '05',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 30,
                            color: Colors.white,
                          ),
                        ),
                        SizedBox(
                            height: 12), // Add some space between the two texts
                        Text(
                          'Unpaid',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 16,
                            color: Colors.white,
                          ),
                        ),
                      ],
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Text(
                          '02',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 30,
                            color: Colors.white,
                          ),
                        ),
                        SizedBox(
                            height: 12), // Add some space between the two texts
                        Text(
                          'Overdue',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 16,
                            color: Colors.white,
                          ),
                        ),
                      ],
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Text(
                          '03',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 30,
                            color: Colors.white,
                          ),
                        ),
                        SizedBox(
                            height: 12), // Add some space between the two texts
                        Text(
                          'Draft',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 16,
                            color: Colors.white,
                          ),
                        ),
                      ],
                    ),
                  ],
                )
              ],
            ).paddingAll16(),
          ],
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
      borderRadius: BorderRadius.circular(12),
      elevation: 3, // Added elevation
      child: Container(
        width: double.infinity,
        constraints: BoxConstraints(maxWidth: 400),
        child: Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
            side: BorderSide(
              color: Colors.green.shade800,
              width: 1,
            ),
          ),
          elevation: 0, // Removed elevation from the Card
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                children: [
                  // First Column for Profile pic and Store Name
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Row(
                        children: [
                          SizedBox(width: 8), // Add horizontal spacing
                          // Profile pic
                          CircleAvatar(
                            radius: 20,
                            backgroundColor: Colors.lightGreen
                                .shade200, // Change background color as needed
                            child: Image.asset(
                                AppIcons.appIcon), // Add your image asset here
                          ),
                          SizedBox(width: 8), // Add horizontal spacing
                          // Text for store name
                          Container(
                            padding: EdgeInsets.symmetric(
                              vertical: 8,
                              horizontal: 12,
                            ),
                            decoration: BoxDecoration(
                              color: Colors.purple.shade500,
                              borderRadius: BorderRadius.only(
                                topLeft: Radius.circular(12),
                                bottomRight: Radius.circular(12),
                              ),
                            ),
                            child: Text(
                              'Happy Foods',
                              style: TextStyle(color: Colors.white),
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                  SizedBox(width: 8), // Add horizontal spacing between columns
                  // Second Column for Paid Status
                  Expanded(
                    // Use Expanded to occupy remaining space
                    child: Column(
                      crossAxisAlignment:
                          CrossAxisAlignment.end, // Align children at the end
                      children: [
                        // Text for paid status
                        Container(
                          padding: EdgeInsets.symmetric(
                            vertical: 8,
                            horizontal: 16,
                          ),
                          decoration: BoxDecoration(
                            color: Colors
                                .teal.shade700, // Change button color as needed
                            borderRadius: BorderRadius.only(
                              topLeft: Radius.circular(8),
                              bottomRight: Radius.circular(8),
                            ),
                          ),
                          child: Text(
                            'Paid',
                            style: TextStyle(color: Colors.white),
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),

              SizedBox(height: 20), // Add vertical spacing
              Container(
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(8),
                  color: Colors.greenAccent.shade100,
                ),
                padding: EdgeInsets.all(8),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    // Amount Column
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'No',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        SizedBox(height: 4),
                        Text(
                          '#0023',
                          style: TextStyle(fontSize: 16),
                        ),
                      ],
                    ),
                    // Divider
                    Container(
                      width: 1,
                      height: 40,
                      color: Colors.black,
                    ),
                    // No Column
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Date',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        SizedBox(height: 4),
                        Text(
                          'Dec 4, 2022',
                          style: TextStyle(fontSize: 16),
                        ),
                      ],
                    ),
                    // Divider
                    Container(
                      width: 1,
                      height: 40,
                      color: Colors.black,
                    ),
                    // Date Column
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Amount',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        SizedBox(height: 4),
                        Text(
                          '\$50',
                          style: TextStyle(fontSize: 16),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
              SizedBox(height: 5), // Add vertical spacing
            ],
          ).paddingAll(16),
        ),
      ),
    );
  }
}
