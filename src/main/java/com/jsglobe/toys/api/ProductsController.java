package com.jsglobe.toys.api;

import com.jsglobe.toys.api.model.ProductVM;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.emptyList;

@RestController("Products Controller")
public class ProductsController {

    @GetMapping("/product")
    public List<ProductVM> getAllProduct() {
        return emptyList();
    }
}
