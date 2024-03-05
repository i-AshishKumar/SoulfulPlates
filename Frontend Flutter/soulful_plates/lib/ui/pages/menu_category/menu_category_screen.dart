import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/constants/app_sized_box.dart';
import 'package:soulful_plates/constants/app_text_styles.dart';
import 'package:soulful_plates/constants/enums/view_state.dart';
import 'package:soulful_plates/constants/size_config.dart';
import 'package:soulful_plates/ui/widgets/app_text_field.dart';
import 'package:soulful_plates/ui/widgets/base_button.dart';
import 'package:soulful_plates/utils/extensions.dart';

import '../../../constants/app_colors.dart';
import '../../../model/menu/menu_item_model.dart';
import '../../widgets/base_common_widget.dart';
import 'menu_category_controller.dart';

class MenuCategoryScreen extends GetView<MenuCategoryController>
    with BaseCommonWidget {
  MenuCategoryScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("MenuCategory"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (MenuCategoryController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Text(
            "Category List",
            style: AppTextStyles.textStyleBlack16With700,
          ),
          ListView.separated(
            itemCount: controller.menuCategories.length,
            shrinkWrap: true,
            itemBuilder: (context, categoryIndex) {
              MenuCategory category = controller.menuCategories[categoryIndex];
              return ListTile(
                title: Text(category.name),
                onTap: () {
                  controller.selectedCategory = category;
                  // controller.categoryController.text = category.name;
                  controller.update();
                },
              );
            },
            separatorBuilder: (BuildContext context, int index) {
              return 1.rVerticalGreySizedBox();
            },
          ),
          AppTextField(
            controller: controller.categoryController,
            hintText: 'Add New Category Name',
          ).visibleWhen(isVisible: controller.selectedCategory == null),
          16
              .rVerticalSizedBox()
              .visibleWhen(isVisible: controller.selectedCategory == null),
          (controller.state == ViewStateEnum.busy
                  ? Center(
                      child: const CircularProgressIndicator(),
                    )
                  : BaseButton(
                      onSubmit: () {
                        controller.addCategory();
                      },
                      text: 'Add Category',
                    ))
              .visibleWhen(isVisible: controller.selectedCategory == null),
          16.rVerticalSizedBox(),
          ...getSubcategoryWidget(),
        ],
      ).paddingAll16(),
    );
  }

  List<Widget> getSubcategoryWidget() {
    if (controller.selectedCategory == null) {
      return [AppSizedBox.sizedBox0];
    }

    return [
      Row(
        children: [
          Expanded(
            child: Text(
              'Selected Categories for: ${controller.selectedCategory?.name}',
              style: AppTextStyles.textStyleBlack14With700,
            ),
          ),
          InkWell(
              onTap: () {
                controller.selectedCategory = null;
                controller.update();
              },
              child: Icon(
                Icons.close_outlined,
                size: 24.rSize(),
                color: AppColor.blackColor,
              ))
        ],
      ),
      16.rVerticalSizedBox(),
      ListView.separated(
        shrinkWrap: true,
        itemCount: controller.selectedCategory?.subcategories.length ?? 0,
        separatorBuilder: (BuildContext context, int index) {
          return 1.rVerticalGreySizedBox();
        },
        itemBuilder: (context, subcategoryIndex) {
          SubCategory subcategory =
              controller.selectedCategory!.subcategories[subcategoryIndex];
          return ListTile(
            title: Text(subcategory.name),
            // You can add more details or functionality here
          );
        },
      ),
      32.rVerticalSizedBox(),
      AppTextField(
        controller: controller.subCategoryController,
        hintText: 'Add New Subcategory Name',
      ),
      controller.state == ViewStateEnum.busy
          ? Center(
              child: const CircularProgressIndicator(),
            )
          : BaseButton(
              onSubmit: () {
                controller.addSubCategory();
              },
              text: 'Add Sub Category',
            ),
      16.rVerticalSizedBox(),
    ];
  }
}
