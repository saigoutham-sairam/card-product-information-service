package com.example.demo.model;

public record UnifiedProduct(
        String productUid,
        String productType,
        String name,
        String fullUrl,
        double unitPrice,
        String unitPriceMeasure,
        int unitPriceMeasureAmount
) {}

