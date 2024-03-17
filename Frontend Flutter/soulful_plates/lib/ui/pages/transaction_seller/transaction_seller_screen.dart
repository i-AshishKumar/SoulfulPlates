import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/size_config.dart';
import '../../../utils/extensions.dart';
import '../../widgets/base_common_widget.dart';
import 'transaction_seller_controller.dart';

class TransactionSellerScreen extends GetView<TransactionSellerController>
    with BaseCommonWidget {
  TransactionSellerScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Transaction Seller"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (TransactionSellerController model) {
              return getBody(context);
            },
          ),
        ));
  }
/*

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        12.rVerticalSizedBox(),
        Expanded(
          child: Stack(children: [
            controller.dataList.isNotEmpty
                ? RefreshIndicator(
                    onRefresh: () async {
                      controller.resetPagination();
                    },
                    child: NotificationListener<ScrollNotification>(
                        onNotification: (scrollNotification) {
                          if (scrollNotification.metrics.pixels >=
                                  scrollNotification.metrics.maxScrollExtent &&
                              !controller.hasReachedMax &&
                              !controller.isLoading()) {
                            controller.pageNo = (controller.pageNo + 1);
                            controller.loadMore();
                          }
                          return false;
                        },
                        child: ListView.separated(
                          padding: EdgeInsets.zero,
                          shrinkWrap: true,
                          physics: const AlwaysScrollableScrollPhysics(),
                          itemCount: controller.dataList.length + 1,
                          separatorBuilder: (context, index) {
                            return 2.rVerticalGreySizedBox();
                          },
                          itemBuilder: (context, index) {
                            if (index < controller.dataList.length) {
                              return InkWell(
                                onTap: () async {
                                  //todo tap on the item
                                },
                                child: //todo change widget with item widget
                                    Text("Item number $index")
                                        .paddingAllDefault(),
                              );
                            } else if (controller.moreLoading ==
                                ViewStateEnum.busy) {
                              return controller.loadMoreLoader(
                                  color: AppColor.blackColor);
                            } else {
                              return AppSizedBox.sizedBox0;
                            }
                          },
                        )),
                  )
                : Center(
                    child: GestureDetector(
                      behavior: HitTestBehavior.opaque,
                      onTap: () {
                        controller.resetPagination();
                      },
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Icon(
                            Icons.refresh_outlined,
                            size: 24.rSize(),
                            color: AppColor.primaryColor,
                          ),
                          Text(
                            'No data available!',
                            style: AppTextStyles.textStyleBlack16With400,
                          ),
                        ],
                      ).paddingAll12(),
                    ),
                  ),
            controller.state == ViewStateEnum.busy
                ? const Center(child: CircularProgressIndicator())
                : AppSizedBox.sizedBox0
          ]).paddingSymmetricSide(vertical: 8, horizontal: 16),
        ),
        CardOne(),
      ],
      //10.rVerticalSizedBox(),
    );
  }
}
*/

  Widget getBody(BuildContext context) {
    return ListView(
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            10.rVerticalSizedBox(),
            CardOne(),
            Padding(
              padding: EdgeInsets.all(16.0),
              child: TextField(
                decoration: InputDecoration(
                  hintText: 'Search',
                  prefixIcon: Icon(Icons.search),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(8.0),
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
            // 20.rVerticalSizedBox(),
            // CardThree(),
            // 20.rVerticalSizedBox(),
            // CardFour(),
          ],
        ).paddingAll16(),
      ],
    );
  }
}

class CardOne extends StatelessWidget {
  const CardOne({Key? key}) : super(key: key);
  static Color get emeraldGreen => const Color(0xFF183D3D);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(8),
        gradient: LinearGradient(
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
          colors: [
            emeraldGreen,
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
          elevation: 2,
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
                          'Happy Foods',
                          style: TextStyle(color: Colors.white),
                        ),
                      )
                    ],
                  ),
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
                          'Paid',
                          style: TextStyle(color: Colors.white),
                        ),
                      )
                    ],
                  ),
                ],
              ),
              20.rVerticalSizedBox(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Amount',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        '\$5',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'No',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        '#0023',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Date',
                        style: TextStyle(fontWeight: FontWeight.bold),
                      ),
                      Text(
                        'Dec 4, 2022',
                        style: TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                ],
              ).paddingAll4(),
              18.rVerticalSizedBox(),
            ],
          ).paddingAll16(),
        ),
      ),
    );
  }
}
