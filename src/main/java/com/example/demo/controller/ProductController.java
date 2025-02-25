package com.example.demo.controller;

import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
    ResponseEntity<?> getProduct(@RequestParam(required = false) String productUid,@RequestParam(required = false) String productType) throws IOException {
        return new ResponseEntity<>(productService.getProductInformationList(productUid,productType ), HttpStatus.OK);
    }

    @GetMapping(path = "/string")
    public
    ResponseEntity<?> getProductThroughStringParser() throws IOException {
        return new ResponseEntity<>(productService.getProductInfoListAsStringParser(), HttpStatus.OK);
    }

    @GetMapping(path = "/readFile")
    public
    ResponseEntity<?> getProductThroughFiles() throws IOException {
        return new ResponseEntity<>(productService.readFromFile(), HttpStatus.OK);
    }



}



