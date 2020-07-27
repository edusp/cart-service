package com.bt.au.shoppingcart.model.dtos;

import com.bt.au.shoppingcart.model.Category;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@JsonDeserialize(builder = ProductDto.ProductDtoBuilder.class)
@Value
public class ProductDto {

    private String name;
    private String description;
    private BigDecimal price;
    private Category category;


    public static class ProductDtoBuilder{}


}
