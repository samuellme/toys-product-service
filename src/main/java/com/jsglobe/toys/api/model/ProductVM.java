package com.jsglobe.toys.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonRootName("Product")
public class ProductVM implements Serializable {
    private final Long id;
    private final String name;
    private final Double price;

    @JsonProperty("old_price")
    private final Double oldPrice;
    private final Long stock;
    private final String brand;
}
