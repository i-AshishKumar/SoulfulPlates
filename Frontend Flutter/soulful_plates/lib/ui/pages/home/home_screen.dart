import 'package:cached_network_image/cached_network_image.dart';
import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/app_text_styles.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_paddings.dart';
import '../../../constants/size_config.dart';
import '../../../routing/route_names.dart';
import '../../widgets/base_common_widget.dart';
import 'home_controller.dart';

class HomeScreen extends GetView<HomeController> with BaseCommonWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Home"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (HomeController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          CarouselSlider(
            options: CarouselOptions(
              autoPlay: false,
              enlargeCenterPage: true,
              viewportFraction: .9,
              aspectRatio: 16 / 9,
              initialPage: 0,
              onPageChanged: (index, reason) {
                controller.currentIndex = index;
              },
            ),
            items: controller.imagesList
                .map(
                  (item) => Card(
                    margin: AppPaddings.defaultPadding,
                    elevation: 6.0,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(16.0),
                    ),
                    child: Stack(children: [
                      ClipRRect(
                        borderRadius: const BorderRadius.all(
                          Radius.circular(16.0),
                        ),
                        child: CachedNetworkImage(
                          imageUrl: item.name,
                          fit: BoxFit.fill,
                          color: AppColor.blackTextColor.withOpacity(0.1),
                          colorBlendMode: BlendMode.color,
                          width: double.infinity,
                        ),
                      ),
                      Align(
                        alignment: Alignment.bottomLeft,
                        child: Container(
                          decoration: BoxDecoration(
                              color: AppColor.blackTextColor.withOpacity(0.4),
                              borderRadius: BorderRadius.circular(8)),
                          child: Text(
                            item.image,
                            style: AppTextStyles.textStyleWhite18With600,
                          ).paddingAllDefault().paddingHorizontal8(),
                        ).paddingAllDefault(),
                      )
                    ]),
                  ),
                )
                .toList(),
          ),
          16.rVerticalSizedBox(),
          Text(
            "Restaurants near you",
            style: AppTextStyles.textStyleBlack22With700,
          ).paddingHorizontal16(),
          16.rVerticalSizedBox(),
          ListView.builder(
              shrinkWrap: true,
              physics: const NeverScrollableScrollPhysics(),
              itemCount: 4,
              itemBuilder: (context, index) {
                return InkWell(
                    onTap: () {
                      Get.toNamed(restaurantDetailViewRoute);
                    },
                    child: getRestaurantCard());
              })

          // BaseButton(
          //     text: "Go to restaurant details",
          //     onSubmit: () {
          //       Get.toNamed(restaurantDetailViewRoute);
          //     }).paddingHorizontal16(),
          // 16.rVerticalSizedBox(),
          // 12.rVerticalSizedBox(),
        ],
      ),
    );
  }

  static Widget getRestaurantCard() {
    return Container(
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(8),
          color: AppColor.black5TextColor),
      child: Row(
        mainAxisSize: MainAxisSize.max,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          8.rHorizontalSizedBox(),
          Expanded(
              child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "Limon\'s Foods",
                style: AppTextStyles.textStyleBlack16With700,
              ),
              2.rVerticalSizedBox(),
              Text(
                "Fast Food, Burgers, Snacks, Juices",
                style: AppTextStyles.textStyleBlack14With400,
              ),
              2.rVerticalSizedBox(),
              Text(
                "2 kms away",
                style: AppTextStyles.textStyleBlackTwo14With400,
              ),
              4.rVerticalSizedBox(),
              Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                mainAxisSize: MainAxisSize.max,
                children: [
                  Container(
                    decoration: BoxDecoration(
                        color: AppColor.primaryColor.withOpacity(0.75),
                        borderRadius: BorderRadius.circular(8)),
                    child: Text(
                      "15 Min",
                      style: AppTextStyles.textStyleWhite14With400,
                    ).paddingUpSide412(),
                  ),
                  8.rHorizontalSizedBox(),
                  Icon(
                    Icons.star_border,
                    color: AppColor.primaryColorLight,
                    size: 24,
                  ),
                  4.rHorizontalSizedBox(),
                  Text(
                    "4.5",
                    style: AppTextStyles.textStylePrimaryLight14With700,
                  )
                ],
              ),
              4.rVerticalSizedBox(),
            ],
          ).paddingAllDefault()),
          ClipRRect(
            borderRadius: const BorderRadius.all(
              Radius.circular(8.0),
            ),
            child: CachedNetworkImage(
              imageUrl:
                  "https://media.istockphoto.com/id/1457979959/photo/snack-junk-fast-food-on-table-in-restaurant-soup-sauce-ornament-grill-hamburger-french-fries.webp?b=1&s=170667a&w=0&k=20&c=A_MdmsSdkTspk9Mum_bDVB2ko0RKoyjB7ZXQUnSOHl0=",
              fit: BoxFit.fill,
              color: AppColor.blackTextColor.withOpacity(0.1),
              colorBlendMode: BlendMode.color,
              width: 80,
              height: 60,
            ),
          ).paddingUpSide412(),
        ],
      ),
    ).paddingUpSide816();
  }
}
