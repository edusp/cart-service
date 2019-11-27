package com.bt.au.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class ShoppingCart extends PersistenceDomainObject {

    @OneToMany(mappedBy = "shoppingCart", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(value = "items")
    private Set<ItemCart> itemsCart;

    public void setItemsCart(Set<ItemCart> itemsCart) {
        this.itemsCart = itemsCart;
    }

    public Set<ItemCart> getItemsCart() {
        return itemsCart;
    }

    public boolean addItemCart(ItemCart itemCart) {
        return this.itemsCart.add(itemCart);
     }

    public boolean removeItemCart(ItemCart itemCart) {
        return this.itemsCart.remove(itemCart);
    }

}
