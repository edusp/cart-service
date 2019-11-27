package com.bt.au.shoppingcart.services;


import com.bt.au.shoppingcart.model.ItemCart;
import com.bt.au.shoppingcart.model.Product;
import com.bt.au.shoppingcart.repositories.ProductRepository;
import com.bt.au.shoppingcart.repositories.ShoppingCartRepository;
import com.bt.au.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartService {

    private ShoppingCartRepository cartRepository;
    private ProductRepository productRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public ShoppingCart addItemToCart(long shoppingCartId, Set<ItemCart>  items) {
        ShoppingCart currentShoppingCart = findById(shoppingCartId);
        currentShoppingCart.getItemsCart().addAll(items);

        return cartRepository.save(currentShoppingCart);
    }

    public ShoppingCart addItemToCart(long shoppingCartId, ItemCart  item) {
        ShoppingCart currentShoppingCart = findById(shoppingCartId);

        Optional<Product> product = productRepository.findById(item.getProductId());

        ItemCart itemCart = new ItemCart(product.get(), item.getQuantity());

        currentShoppingCart.addItemCart(itemCart);

        return cartRepository.save(currentShoppingCart);
    }

    private ShoppingCart findById(long shoppingCartId) {
        return cartRepository.getOne(shoppingCartId);
    }




}
