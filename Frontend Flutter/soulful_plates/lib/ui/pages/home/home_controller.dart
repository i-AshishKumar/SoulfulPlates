import 'package:flutter/cupertino.dart';

import '../../../controller/base_controller.dart';

class HomeController extends BaseController {
  final formKey = GlobalKey<FormState>();

  int currentIndex = 0;

  final List<Items> imagesList = [
    Items(
      "https://media.istockphoto.com/id/1457979959/photo/snack-junk-fast-food-on-table-in-restaurant-soup-sauce-ornament-grill-hamburger-french-fries.webp?b=1&s=170667a&w=0&k=20&c=A_MdmsSdkTspk9Mum_bDVB2ko0RKoyjB7ZXQUnSOHl0=",
      "Limon\'s Foods",
    ),
    Items(
      "https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg?cs=srgb&dl=pexels-ella-olsson-1640777.jpg&fm=jpg",
      "HomeFreshy",
    ),
    Items(
      "https://www.eatingwell.com/thmb/m5xUzIOmhWSoXZnY-oZcO9SdArQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/article_291139_the-top-10-healthiest-foods-for-kids_-02-4b745e57928c4786a61b47d8ba920058.jpg",
      "GoodFoods",
    ),
  ];

  final List<String> imagesDetails = [
    "Limon\'s Foods",
    "HomeFreshy",
    "GoodFoods",
  ];
}

class Items {
  String image;
  String name;
  Items(this.name, this.image);
}
