package com.bt.au.shoppingcart.services;


import com.bt.au.shoppingcart.model.ItemCart;
import com.bt.au.shoppingcart.repositories.ShoppingCartRepository;
import com.bt.au.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShoppingCartService {

    private ShoppingCartRepository cartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public ShoppingCart addItemToCart(long shoppingCartId, Set<ItemCart>  items) {
        ShoppingCart currentShoppingCart = findById(shoppingCartId);

        items.forEach(i -> currentShoppingCart.addItemCart(i));

        return cartRepository.save(currentShoppingCart);
    }

    private ShoppingCart findById(long shoppingCartId) {
        return cartRepository.getOne(shoppingCartId);
    }




}
