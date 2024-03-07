import 'package:flutter/cupertino.dart';
import 'package:soulful_plates/constants/enums/view_state.dart';

import '../../../controller/base_controller.dart';
import '../../../model/menu/menu_item_model.dart';
import '../../../utils/utils.dart';

class MenuCategoryController extends BaseController {
  final TextEditingController categoryController = TextEditingController();
  final TextEditingController subCategoryController = TextEditingController();

  List<MenuCategory> menuCategories = [];

  MenuCategory? selectedCategory;

  @override
  void onInit() {
    super.onInit();
    menuCategories = Utils.menuCategory;
  }

  void addCategory() async {
    setLoaderState(ViewStateEnum.busy);
    await Future.delayed(Duration(seconds: 1));
    String categoryName = categoryController.text.trim();
    if (categoryName.isNotEmpty) {
      if (selectedCategory != null) {
        // Update the selected category if one is already selected
        selectedCategory!.name = categoryName;
        selectedCategory = null;
      } else {
        // Add a new category
        MenuCategory newCategory = MenuCategory(categoryName, []);
        if (!menuCategories.contains(newCategory)) {
          menuCategories.add(newCategory);
        }
      }
    }
    categoryController.clear();
    setLoaderState(ViewStateEnum.idle);
    update();
  }

  void addSubCategory() async {
    setLoaderState(ViewStateEnum.busy);
    await Future.delayed(Duration(seconds: 1));
    String subCategoryName = subCategoryController.text.trim();
    if (subCategoryName.isNotEmpty && selectedCategory != null) {
      // Add a new subcategory to the selected category
      SubCategory newSubCategory = SubCategory(subCategoryName, []);
      selectedCategory!.addSubCategory(newSubCategory);
    }
    subCategoryController.clear();
    setLoaderState(ViewStateEnum.idle);

    update();
  }
}
