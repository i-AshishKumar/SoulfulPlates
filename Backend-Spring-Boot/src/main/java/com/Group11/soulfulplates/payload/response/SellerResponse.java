package com.Group11.soulfulplates.payload.response;

public class SellerResponse {

    private int code;
    private SellerData data;
    private String description;

    public SellerResponse(int code, SellerData data, String description) {
        this.code = code;
        this.data = data;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SellerData getData() {
        return data;
    }

    public void setData(SellerData data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class SellerData {
        private Long id;
        private String token;
        private String username;
        private String email;
        private String contactNumber;
        private String firstname;
        private String image;
        private SellerDetails seller;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public SellerDetails getSeller() {
            return seller;
        }

        public void setSeller(SellerDetails seller) {
            this.seller = seller;
        }

        public static class SellerDetails {
            private Long sellerId;
            private String storeImage;
            private String storeName;
            private String storeContactNumber;
            private String storeEmail;
            private StoreAddress storeAddress;

            public Long getSellerId() {
                return sellerId;
            }

            public void setSellerId(Long sellerId) {
                this.sellerId = sellerId;
            }

            public String getStoreImage() {
                return storeImage;
            }

            public void setStoreImage(String storeImage) {
                this.storeImage = storeImage;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreContactNumber() {
                return storeContactNumber;
            }

            public void setStoreContactNumber(String storeContactNumber) {
                this.storeContactNumber = storeContactNumber;
            }

            public String getStoreEmail() {
                return storeEmail;
            }

            public void setStoreEmail(String storeEmail) {
                this.storeEmail = storeEmail;
            }

            public StoreAddress getStoreAddress() {
                return storeAddress;
            }

            public void setStoreAddress(StoreAddress storeAddress) {
                this.storeAddress = storeAddress;
            }

            public static class StoreAddress {
                private Long locationId;
                private String locationName;
                private String address;
                private Double lat;
                private Double lon;
                private String postalCode;
                private String province;
                private String city;

                public Long getLocationId() {
                    return locationId;
                }

                public void setLocationId(Long locationId) {
                    this.locationId = locationId;
                }

                public String getLocationName() {
                    return locationName;
                }

                public void setLocationName(String locationName) {
                    this.locationName = locationName;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public Double getLat() {
                    return lat;
                }

                public void setLat(Double lat) {
                    this.lat = lat;
                }

                public Double getLon() {
                    return lon;
                }

                public void setLon(Double lon) {
                    this.lon = lon;
                }

                public String getPostalCode() {
                    return postalCode;
                }

                public void setPostalCode(String postalCode) {
                    this.postalCode = postalCode;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }
            }
        }
    }
}
