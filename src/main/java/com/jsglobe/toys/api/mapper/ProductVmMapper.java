package com.jsglobe.toys.api.mapper;

import com.jsglobe.toys.api.model.ProductVM;
import com.jsglobe.toys.service.products.Product;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ProductVmMapper {
    public List<ProductVM> map(@NonNull List<Product> products) {
        return products
                .stream()
                .map(this::map)
                .collect(toList());
    }

    private ProductVM map(@NonNull Product product) {
        return new ProductVM(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getOldPrice(),
                product.getStock(),
                product.getBrand()
        );
    }
}
