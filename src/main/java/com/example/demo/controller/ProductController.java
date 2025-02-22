package com.example.demo.controller;

import com.example.demo.model.UnifiedProduct;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/products")
public
class ProductController {

    private final ProductService productService;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public
    ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping
    public
    List<UnifiedProduct> getAllUnifiedProduct() throws IOException {
        return productService.getProductInformationList(parseListAsString());
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

    private
    String parseListAsString() {
        List<UnifiedProduct> productInformationList = printAllProductDetails();
        try {
            String jsonResponse = objectMapper.writeValueAsString(productInformationList);
            System.out.println(jsonResponse);
            List<UnifiedProduct> parsedList = objectMapper.readValue(jsonResponse, new TypeReference<List<UnifiedProduct>>() {
            });
            parsedList.forEach(System.out::println);
            System.out.println("Size of ParsedList: " + parsedList.size());
            return jsonResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
      return  null;
    }

}



