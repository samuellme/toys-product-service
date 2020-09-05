package com.jsglobe.toys.api;

import com.jsglobe.toys.api.mapper.ProductVmMapper;
import com.jsglobe.toys.api.model.ProductVM;
import com.jsglobe.toys.service.products.Product;
import com.jsglobe.toys.service.products.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {
    private final ProductService productService;
    private final ProductVmMapper productVmMapper;

    public ProductsController(
            ProductService productService,
            ProductVmMapper productVmMapper
    ) {
        this.productService = productService;
        this.productVmMapper = productVmMapper;
    }

    @GetMapping("/product")
    @Cacheable("product")
    public List<ProductVM> getAllProduct() {
        final List<Product> products = productService.getProducts();
        return productVmMapper.map(products);
    }
}
