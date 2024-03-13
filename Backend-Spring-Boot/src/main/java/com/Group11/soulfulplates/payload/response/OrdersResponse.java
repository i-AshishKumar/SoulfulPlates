package com.Group11.soulfulplates.payload.response;

import java.util.Date;
import java.util.List;

public class OrdersResponse {
    private Integer code;
    private String description;
    private List<OrderData> data;

    public OrdersResponse(Integer code, String description, List<OrderData> data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OrderData> getData() {
        return data;
    }

    public void setData(List<OrderData> data) {
        this.data = data;
    }

    public static class OrderData {
        private Long orderId;
        private String orderStatus;
        private Date createdDate;
        private Long userId;
        private Long storeId;
        private Integer rating;
        private String feedback;
        private String paymentStatus;
        private List<ItemData> items;

        public OrderData() {}

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getStoreId() {
            return storeId;
        }

        public void setStoreId(Long storeId) {
            this.storeId = storeId;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public List<ItemData> getItems() {
            return items;
        }

        public void setItems(List<ItemData> items) {
            this.items = items;
        }

        public static class ItemData {
            private Long itemId;
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

            public ItemData() {}

            public Long getItemId() {
                return itemId;
            }

            public void setItemId(Long itemId) {
                this.itemId = itemId;
            }

            public Long getStoreId() {
                return storeId;
            }

            public void setStoreId(Long storeId) {
                this.storeId = storeId;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getItemImage() {
                return itemImage;
            }

            public void setItemImage(String itemImage) {
                this.itemImage = itemImage;
            }

            public String getItemPrice() {
                return itemPrice;
            }

            public void setItemPrice(String itemPrice) {
                this.itemPrice = itemPrice;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Long getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Long categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public Long getSubCategoryId() {
                return subCategoryId;
            }

            public void setSubCategoryId(Long subCategoryId) {
                this.subCategoryId = subCategoryId;
            }

            public String getSubCategory() {
                return subCategory;
            }

            public void setSubCategory(String subCategory) {
                this.subCategory = subCategory;
            }

            public Integer getServingType() {
                return servingType;
            }

            public void setServingType(Integer servingType) {
                this.servingType = servingType;
            }

            public String getPortion() {
                return portion;
            }

            public void setPortion(String portion) {
                this.portion = portion;
            }

            public Boolean getInStock() {
                return inStock;
            }

            public void setInStock(Boolean inStock) {
                this.inStock = inStock;
            }

            public Boolean getIsRecommended() {
                return isRecommended;
            }

            public void setIsRecommended(Boolean isRecommended) {
                this.isRecommended = isRecommended;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
