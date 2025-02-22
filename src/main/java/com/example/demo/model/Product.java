package com.example.demo.model;

public record Product(
        String productUid,
        double unitPrice,
        String unitPriceMeasure,
        int unitPriceMeasureAmount
) {}
