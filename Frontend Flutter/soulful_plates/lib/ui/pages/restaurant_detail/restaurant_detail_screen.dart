import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_icons.dart';
import '../../widgets/base_common_widget.dart';
import 'restaurant_detail_controller.dart';

class RestaurantDetailScreen extends GetView<RestaurantDetailController>
    with BaseCommonWidget {
  RestaurantDetailScreen({Key? key}) : super(key: key);

  //Define restaurant data
  final List<Map<String, dynamic>> restaurantData = [
    {
      'name': 'Restaurant A',
      'image': 'assets/icons/drawables/service1.jpeg',
    },
    {
      'name': 'Restaurant B',
      'image': 'assets/icons/drawables/service2.jpg',
    },
    {
      'name': 'Restaurant C',
      'image': 'assets/icons/drawables/service3.jpg',
    },
    {
      'name': 'Restaurant D',
      'image': 'assets/icons/drawables/service4.jpg',
    },
    {
      'name': 'Restaurant E',
      'image': 'assets/icons/drawables/service5.jpg',
    },
    {
      'name': 'Restaurant F',
      'image': 'assets/icons/drawables/service6.jpg',
    },
    // Add more restaurants as needed
  ];

  @override
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Restaurant Detail",
          textAlign: TextAlign.center,
        ),
        leading: SizedBox(
          width: 40,
          height: 40,
          child: Image.asset(
            AppIcons.logo,
            fit: BoxFit.contain,
          ),
        ),
      ),
      backgroundColor: AppColor.whiteColor,
      body: SafeArea(
        child: SingleChildScrollView(
          // Wrap with SingleChildScrollView
          child: Column(
            children: [
              SizedBox(
                height: 200,
                child: RestaurantCarousel(restaurantData: restaurantData),
              ),
              const SizedBox(height: 10),
              const Text("Restaurant Detail Screen"),
              const SizedBox(height: 10),
            ],
          ),
        ),
      ),
    );
  }
}

class RestaurantCarousel extends StatelessWidget {
  final List<Map<String, dynamic>> restaurantData;

  const RestaurantCarousel({required this.restaurantData});

  @override
  Widget build(BuildContext context) {
    return CarouselSlider.builder(
      itemCount: restaurantData.length,
      itemBuilder: (BuildContext context, int index, int realIndex) {
        return Container(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Image.asset(
                restaurantData[index]['image'],
                fit: BoxFit.cover,
                width: double.infinity,
                height: 300,
              ),
              SizedBox(height: 10),
              Text(
                restaurantData[index]['name'],
                style: TextStyle(
                  fontSize: 12,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
        );
      },
      options: CarouselOptions(
        aspectRatio: 18 / 9,
        viewportFraction: 0.7,
        initialPage: 0,
        enableInfiniteScroll: true,
        autoPlay: true,
        autoPlayInterval: Duration(seconds: 4),
        autoPlayAnimationDuration: Duration(milliseconds: 800),
        autoPlayCurve: Curves.fastOutSlowIn,
        enlargeCenterPage: true,
      ),
    );
  }
}
