package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public
record ProductPrice(
        @JsonProperty("product_uid") String productUid,
        @JsonProperty("unit_price") double unitPrice,
        @JsonProperty("unit_price_measure") String unitPriceMeasure,
        @JsonProperty("unit_price_measure_amount") int unitPriceMeasureAmount
) {
}
