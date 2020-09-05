package com.jsglobe.toys.service.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataSourceProduct {
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("NAME")
    private String name;

    @JsonProperty("PRICE")
    private Double price;

    @JsonProperty("OLD_PRICE")
    private Double oldPrice;

    @JsonProperty("STOCK")
    private Long stock;

    @JsonProperty("BRAND")
    private String brand;
}
