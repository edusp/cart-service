package com.bt.au.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends PersistenceDomainObject {

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonProperty(value = "shopping_cart")
    private ShoppingCart shoppingCart;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(value = "user")
    private User user;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
