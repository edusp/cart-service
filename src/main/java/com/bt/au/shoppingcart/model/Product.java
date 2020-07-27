package com.bt.au.shoppingcart.model;

import com.bt.au.shoppingcart.model.dtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends PersistenceDomainObject {

    @NotNull
    @Column(length = 150, nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Category category;

    public static ProductDto toDto(Product p) {
        return ProductDto.builder()
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .build();
    }

    public static Product fromDto(ProductDto dto) {
        return new Product(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getCategory());
    }

}
