package com.bt.au.shoppingcart.controllers;


import com.bt.au.shoppingcart.model.ItemCart;
import com.bt.au.shoppingcart.model.Order;
import com.bt.au.shoppingcart.model.ShoppingCart;
import com.bt.au.shoppingcart.repositories.OrderRepository;
import com.bt.au.shoppingcart.repositories.ShoppingCartRepository;
import com.bt.au.shoppingcart.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

@RestController
@RequestMapping("orders")
public class OrderController {

    private static List<String> msgs = new ArrayList<>();
    
    private OrderRepository orderRepository;

    private ShoppingCartRepository cartRepository;

    private ShoppingCartService cartService;

    @Autowired
    public OrderController(OrderRepository orderRepository, ShoppingCartRepository cartRepository,
                           ShoppingCartService cartService) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    @PostMapping(path = "init-cart")
    public ShoppingCart createCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setItemsCart(Collections.EMPTY_SET);
        return cartRepository.save(shoppingCart);
    }

    @PostMapping(path = "/add-item/{cartId}")
    public ShoppingCart addItem(@PathVariable("cartId") long cartId, @RequestBody ItemCart item) {



        return cartService.addItemToCart(cartId, item);
    }

    @PostMapping(path = "/add-items/{cartId}")
    public ShoppingCart addItems(@PathVariable("cartId") long cartId, @RequestBody Set<ItemCart> items) {
        return cartService.addItemToCart(cartId, items);
    }

    @PostMapping
    public Order save(@RequestBody Order order) {
        return orderRepository.save(order);
    }


    @GetMapping(path = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> listByUser(@PathVariable long userId, @PathParam("page") Pageable page) {
        return orderRepository.findAll(page).getContent();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> list(@PathParam("page") Pageable page) {
        return orderRepository.findAll(page).getContent();
    }
    
    @GetMapping(path= "send/{msg}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void atack(@PathVariable("msg") String msg) {
        msgs.add(msg);
    }
    
    @GetMapping(path = "msgs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> list() {
        return msgs;
    }
    
}
