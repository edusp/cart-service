package com.bt.au.shoppingcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Product extends PersistenceDomainObject {

    public Product() {
    }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @NotNull
    @Column(length = 150, nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false)
    private String description;
    private BigDecimal price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
