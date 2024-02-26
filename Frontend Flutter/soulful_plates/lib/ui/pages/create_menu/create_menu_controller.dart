import 'package:flutter/cupertino.dart';

import '../../../controller/base_controller.dart';

/*
Category - Starter or something
SubCategory - Veg Starter or Non Veg starter
Type: Veg, Non-veg, Eggs
ItemName
ItemPrice
Description
InStock
IsRecommended
ItemImage
ServingType - number 1-2-3-4 people
Portion - 3 Pieces or 250 ml text

//
ItemName
ItemPrice
Description
Recommended
InStock
ItemImage
 */

class CreateMenuController extends BaseController {
  TextEditingController itemName = TextEditingController();
  TextEditingController itemPrice = TextEditingController();
  TextEditingController description = TextEditingController();
  TextEditingController serviceType = TextEditingController();
  TextEditingController portion = TextEditingController();
  bool inStock = false;
  bool isRecommended = false;

  List<String> type = ["Veg", "Non-Veg", "Eggs"];
  String selectType = "Veg";

  List<String> category = ["Starter", "Main Course", "Salads"];
  String selectCategory = "Starter";

  List<String> subCategory = ["Veg Starter", "Non-Veg Starter"];
  String selectSubCategory = "Veg Starter";
}
