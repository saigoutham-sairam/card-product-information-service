package com.example.demo.model;

public record ProductPrice(
        String productUid,
        double unitPrice,
        String unitPriceMeasure,
        int unitPriceMeasureAmount
) {}
