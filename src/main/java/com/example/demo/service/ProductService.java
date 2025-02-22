package com.example.demo.service;

import com.example.demo.model.UnifiedProduct;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<UnifiedProduct> getProductInformationList(String jsonResponse) throws IOException {
        return objectMapper.readValue(jsonResponse, new TypeReference<List<UnifiedProduct>>() {});
    }
}
