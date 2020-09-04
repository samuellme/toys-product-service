package com.jsglobe.toys.service.products;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class ProductService {

    @NonNull
    public List<Product> getProducts() {
        return emptyList();
    }
}
