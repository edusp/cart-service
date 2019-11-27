package com.bt.au.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ItemCart extends PersistenceDomainObject {

    @ManyToOne
    private Product product;

    private int quantity;

    @Transient
    @JsonProperty("product_id")
    private long productId;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ShoppingCart shoppingCart;

    public ItemCart() {
    }

    public ItemCart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
