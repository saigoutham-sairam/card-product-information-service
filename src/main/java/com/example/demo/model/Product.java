package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public
record Product(
        @JsonProperty("product_uid") String productUid,
        @JsonProperty("product_type") String productType,
        @JsonProperty("name") String name,
        @JsonProperty("full_url") String fullUrl
) {
}
