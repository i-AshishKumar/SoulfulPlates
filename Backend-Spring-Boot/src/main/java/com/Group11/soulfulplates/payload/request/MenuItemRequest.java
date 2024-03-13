package com.Group11.soulfulplates.payload.request;

import com.Group11.soulfulplates.models.Category;
import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.models.SubCategory;
import lombok.Data;

@Data
public class MenuItemRequest {
    private Long storeId;
    private String itemName;
    private String itemImage;
    private String itemPrice;
    private String type;
    private Long categoryId;
    private String category;
    private Long subCategoryId;
    private String subCategory;
    private Integer servingType;
    private String portion;
    private Boolean inStock;
    private Boolean isRecommended;
    private String description;

}
