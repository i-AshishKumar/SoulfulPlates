import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:soulful_plates/routing/route_names.dart';
import 'package:soulful_plates/ui/widgets/base_button.dart';

import '../../../constants/app_colors.dart';
import '../../../constants/app_sized_box.dart';
import '../../../constants/app_text_styles.dart';
import '../../../constants/enums/view_state.dart';
import '../../../constants/size_config.dart';
import '../../../model/menu/menu_item_model.dart';
import '../../../utils/extensions.dart';
import '../../widgets/base_common_widget.dart';
import 'menu_list_controller.dart';

class MenuListScreen extends GetView<MenuListController> with BaseCommonWidget {
  const MenuListScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Menu List"),
        ),
        backgroundColor: AppColor.whiteColor,
        body: SafeArea(
          child: GetBuilder(
            init: controller,
            initState: (state) async {},
            builder: (MenuListController model) {
              return getBody(context);
            },
          ),
        ));
  }

  Widget getBody(BuildContext context) {
    return Column(
      children: [
        16.rVerticalSizedBox(),
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
                              print(
                                  "This is menu item ${controller.dataList[index]}");

                              return InkWell(
                                onTap: () async {
                                  //todo tap on the item
                                },
                                child: getMenuItem(controller.dataList[index])
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
        16.rVerticalSizedBox(),
        BaseButton(
            text: 'Create menu',
            onSubmit: () {
              Get.toNamed(createMenuViewRoute);
            }),
        16.rVerticalSizedBox(),
      ],
    ).paddingAll16();
  }

  Widget getMenuItem(MenuItemModel menuItem) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          menuItem.itemName ?? '',
          style: AppTextStyles.textStyleBlack14With700,
        ),
        Text(
          menuItem.description ?? "",
          style: AppTextStyles.textStyleBlackTwo14With400,
          maxLines: 2,
        ),
        Text(
          "\$ ${menuItem.itemPrice}",
          style: AppTextStyles.textStyleBlack14With400,
        ),
        Text(
          "Servings: ${menuItem.servingType} People",
          style: AppTextStyles.textStyleBlack14With400,
        ),
        Text(
          "Portion: ${menuItem.portion}",
          style: AppTextStyles.textStyleBlack14With400,
        ),
        Text(
          "Type: ${menuItem.type}",
          style: AppTextStyles.textStyleBlack14With400,
        ),
        Text(
          "Category: ${menuItem.category}",
          style: AppTextStyles.textStyleBlack14With400,
        ),
        Text(
          "Sub Category: ${menuItem.subCategory}",
          style: AppTextStyles.textStyleBlack14With400,
        ),
        Row(
          children: [
            Expanded(
                child: InkWell(
              onTap: () {
                menuItem.setRecommended(!menuItem.isRecommended);
                controller.update();
              },
              child: Row(
                children: [
                  Icon(
                    menuItem.isRecommended
                        ? Icons.thumb_up_sharp
                        : Icons.thumb_up_off_alt,
                    color: AppColor.primaryColor,
                    size: 24,
                  ),
                  4.rHorizontalSizedBox(),
                  Text(
                    "Recommended",
                    style: AppTextStyles.textStyleBlackThree14With400,
                  )
                ],
              ),
            )),
            Expanded(
                child: InkWell(
              onTap: () {
                menuItem.setInStock(!menuItem.inStock);

                controller.update();
              },
              child: Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Expanded(
                    child: Text(
                      'In stock',
                      style: AppTextStyles.textStyleBlackThree14With400,
                      textAlign: TextAlign.end,
                    ),
                  ),
                  SizedBox(
                    height: 24,
                    child: Transform.scale(
                        scale: .75,
                        child: Switch(
                            inactiveTrackColor: AppColor.black4TextColor,
                            activeColor: AppColor.primaryColor,
                            thumbColor: const MaterialStatePropertyAll<Color>(
                                Colors.black),
                            value: menuItem.inStock,
                            onChanged: (val) {
                              menuItem.setInStock(val);
                              controller.update();
                            })),
                  )
                ],
              ).paddingSymmetricSide(vertical: 16, horizontal: 12),
            ))
          ],
        )
      ],
    );
  }

  Widget getCategoryWidget(MenuCategory category) {
    return Container(
      color: AppColor.whiteColor,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            category.name,
            style: AppTextStyles.textStyleBlack16With700,
          ),
          4.rVerticalSizedBox(),
          ListView.builder(
            itemCount: category.subcategories.length,
            shrinkWrap: true,
            itemBuilder: (BuildContext context, int index) {
              SubCategory subCategory = category.subcategories[index];
              return Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    subCategory.name,
                    style: AppTextStyles.textStyleBlack14With700,
                  ),
                  4.rVerticalSizedBox(),
                  ListView.separated(
                    itemCount: subCategory.items.length,
                    shrinkWrap: true,
                    separatorBuilder: (BuildContext context, int index) {
                      return 1.rVerticalGreySizedBox();
                    },
                    itemBuilder: (BuildContext context, int index) {
                      MenuItemModel menuItem = subCategory.items[index];
                      return Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            menuItem.itemName ?? '',
                            style: AppTextStyles.textStyleBlack14With700,
                          ),
                          Text(
                            menuItem.description ?? "",
                            style: AppTextStyles.textStyleBlackTwo14With400,
                            maxLines: 2,
                          ),
                          Text(
                            "\$ ${menuItem.itemPrice}",
                            style: AppTextStyles.textStyleBlack14With400,
                          ),
                          Text(
                            "Servings: ${menuItem.servingType} People",
                            style: AppTextStyles.textStyleBlack14With400,
                          ),
                          Text(
                            "Portion: ${menuItem.portion}",
                            style: AppTextStyles.textStyleBlack14With400,
                          ),
                          Text(
                            "Type: ${menuItem.type}",
                            style: AppTextStyles.textStyleBlack14With400,
                          ),
                          Row(
                            children: [
                              Expanded(
                                  child: InkWell(
                                onTap: () {
                                  menuItem
                                      .setRecommended(!menuItem.isRecommended);
                                  controller.update();
                                },
                                child: Row(
                                  children: [
                                    Icon(
                                      menuItem.isRecommended
                                          ? Icons.thumb_up_sharp
                                          : Icons.thumb_up_off_alt,
                                      color: AppColor.primaryColor,
                                      size: 24,
                                    ),
                                    4.rHorizontalSizedBox(),
                                    Text(
                                      "Recommended",
                                      style: AppTextStyles
                                          .textStyleBlackThree14With400,
                                    )
                                  ],
                                ),
                              )),
                              Expanded(
                                  child: InkWell(
                                onTap: () {
                                  menuItem.setInStock(!menuItem.inStock);

                                  controller.update();
                                },
                                child: Row(
                                  mainAxisAlignment: MainAxisAlignment.end,
                                  children: [
                                    Expanded(
                                      child: Text(
                                        'In stock',
                                        style: AppTextStyles
                                            .textStyleBlackThree14With400,
                                        textAlign: TextAlign.end,
                                      ),
                                    ),
                                    SizedBox(
                                      height: 24,
                                      child: Transform.scale(
                                          scale: .75,
                                          child: Switch(
                                              inactiveTrackColor:
                                                  AppColor.black4TextColor,
                                              activeColor:
                                                  AppColor.primaryColor,
                                              thumbColor:
                                                  const MaterialStatePropertyAll<
                                                      Color>(Colors.black),
                                              value: menuItem.inStock,
                                              onChanged: (val) {
                                                menuItem.setInStock(val);
                                                controller.update();
                                              })),
                                    )
                                  ],
                                ).paddingSymmetricSide(
                                    vertical: 16, horizontal: 12),
                              ))
                            ],
                          )
                        ],
                      );
                    },
                  ).paddingHorizontal4()
                ],
              );
            },
          ).paddingHorizontal4()
        ],
      ),
    );
  }
}
