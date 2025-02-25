package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.ProductPrice;
import com.example.demo.model.UnifiedProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public
class ProductService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public
    List<UnifiedProduct> getProductInfoListAsStringParser() throws IOException {
        return objectMapper.readValue(parseListAsString(), new TypeReference<>() {
        });
    }

    public
    List<UnifiedProduct> getProductInformationList(String productUid, String productType) throws IOException {
        List<UnifiedProduct> productInfoCompleteList = printAllProductDetails();
        if (productUid != null) {
            return productInfoCompleteList.stream()
                    .filter(product -> product.productUid().equalsIgnoreCase(productUid))
                    .collect(Collectors.toList());
        } else if (productType != null) {
            List<UnifiedProduct> filteredProducts = productInfoCompleteList.stream()
                    .filter(product -> product.productType().equalsIgnoreCase(productType))
                    .collect(Collectors.toList());
            return filteredProducts.isEmpty() ? null : filteredProducts;
        } else {
            return productInfoCompleteList;
        }
    }

    private
    String parseListAsString() {
        List<UnifiedProduct> productInformationList = printAllProductDetails();
        try {
            String jsonResponse = objectMapper.writeValueAsString(productInformationList);
            System.out.println(jsonResponse);
            List<UnifiedProduct> parsedList = objectMapper.readValue(jsonResponse, new TypeReference<>() {
            });
            parsedList.forEach(System.out::println);
            System.out.println("Size of ParsedList: " + parsedList.size());
            return jsonResponse;
        } catch (JsonProcessingException e) {
            System.out.println("parseListAsString Error message: " + e.getMessage());
        }
        return null;
    }

    private
    List<UnifiedProduct> printAllProductDetails() {
        Set<UnifiedProduct> UnifiedProductSet = new HashSet<>();
        UnifiedProductSet.add(new UnifiedProduct("6447344", "BASIC", "Sainsbury's Skin on ASC Scottish Salmon Fillets x2 240g", "https://www.sainsburys.co.uk/gol-ui/product/sainsburys-responsibly-sourced-scottish-salmon-fillet-x2-240g", 15.63, "kg", 1));
        UnifiedProductSet.add(new UnifiedProduct("7947559", "BASIC", "Sainsbury's Houmous 200g", "https://www.sainsburys.co.uk/gol-ui/product/sainsburys-houmous-200g", 0.5, "g", 100));
        UnifiedProductSet.add(new UnifiedProduct("3052068", "BASIC", "Sainsbury's Mixed Peppers 3 pack", "https://www.sainsburys.co.uk/gol-ui/product/sainsburys-mixed-peppers-3-pack", 1.5, "each", 1));

        //UnifiedProductList.forEach(System.out::println);
        return new ArrayList<>(UnifiedProductSet);
    }

    public
    List<UnifiedProduct> readFromFile() throws IOException {


        TypeReference<Set<Product>> productTypeRef = new TypeReference<>() {};
        Set<Product> productSet = readJsonFile("products_v2.json", productTypeRef);
        productSet.forEach(System.out::println);


        Set<ProductPrice> productPriceSet = readJsonFile("product_price_v2.json", new TypeReference<Set<ProductPrice>>() {
        });
        productPriceSet.forEach(System.out::println);

        return mergeProducts(productSet, productPriceSet);
    }

    public static
    List<UnifiedProduct> mergeProducts(Set<Product> productSet, Set<ProductPrice> priceSet) {
        Map<String, Product> productMap = new HashMap<>();
        for (Product product : productSet) {
            productMap.put(product.productUid(), product);
        }

        List<UnifiedProduct> unifiedProductList = new ArrayList<>();
        for (ProductPrice price : priceSet) {
            Product product = productMap.get(price.productUid());
            if (product != null) {
                UnifiedProduct unifiedProduct = new UnifiedProduct(
                        product.productUid(),
                        product.productType(),
                        product.name(),
                        product.fullUrl(),
                        price.unitPrice(),
                        price.unitPriceMeasure(),
                        price.unitPriceMeasureAmount()
                );
                unifiedProductList.add(unifiedProduct);
            }
        }
        return unifiedProductList;
    }


    public static <T> Set<T> readJsonFile(String fileName, TypeReference<Set<T>> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        Set<T> resultSet = new HashSet<>();
        try (InputStream inputStream = ProductService.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                resultSet = mapper.readValue(inputStream, typeReference);
            } else {
                System.out.println("File not found: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("readJsonFile Error message: " + e.getMessage());
        }
        return resultSet;
    }
}

